#!/usr/bin/env node
// Help Refresh Pipeline (OGC help ticket, FRS §13).
//
// Walks the Confluence folders named in help-refresh.config.json, converts
// each page's ADF to sanitized HTML, downloads attachments, resolves cross-page
// links, applies version/legacy filters, and emits a per-locale manifest and
// FlexSearch index into the configured outputDir. Run by the release manager
// at release cut; the output is reviewed as a normal PR.
//
// Usage:
//   set -a; source .env; set +a
//   npm run help:refresh                 # live run against Confluence
//   npm run help:refresh -- --fixtures   # deterministic run against the
//                                        # dev-bundle fixtures (FRS AC 1)
//
// Phases below are stubs. Each one lands as its own commit so the diff stays
// reviewable. Dependencies (dompurify, flexsearch, sharp) are installed only
// as each phase needs them.

import fs from "node:fs/promises";
import path from "node:path";
import { fileURLToPath } from "node:url";

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const configPath = path.join(__dirname, "help-refresh.config.json");

const args = new Set(process.argv.slice(2));
const useFixtures = args.has("--fixtures");

const loadConfig = async () => {
  const raw = await fs.readFile(configPath, "utf8");
  const config = JSON.parse(raw);
  if (!config.corpora?.length) {
    throw new Error("config.corpora is empty — nothing to walk");
  }
  return config;
};

const requireEnv = (name) => {
  const v = process.env[name];
  if (!v) throw new Error(`missing env var ${name}`);
  return v;
};

const confluenceAuth = () => {
  const email = requireEnv("CONFLUENCE_EMAIL");
  const token = requireEnv("CONFLUENCE_TOKEN");
  const host = requireEnv("CONFLUENCE_HOST");
  const auth = "Basic " + Buffer.from(`${email}:${token}`).toString("base64");
  return { host, headers: { Authorization: auth, Accept: "application/json" } };
};

// Confluence v2 GET helper. Serial by design — a release-time pipeline running
// against ~100 pages does not need parallelism, and serial keeps us well under
// Cloud's per-user rate limit.
const confluenceGet = async (host, headers, pathOrUrl) => {
  const url = pathOrUrl.startsWith("http")
    ? pathOrUrl
    : `https://${host}${pathOrUrl}`;
  const res = await fetch(url, { headers });
  if (!res.ok) {
    throw new Error(`${res.status} ${res.statusText} ${pathOrUrl}`);
  }
  return res.json();
};

// Every page descended from a root, via CQL. Returns id, title, labels, and
// ancestor titles in one call per batch — no N+1 label lookups, and the
// ancestor chain carries LEGACY Versions folders transparently so folder vs
// page content type does not need special-casing.
const listCorpusPages = async (host, headers, spaceKey, rootPageId) => {
  const cql = encodeURIComponent(
    `space=${spaceKey} AND type=page AND ancestor=${rootPageId}`,
  );
  const expand = encodeURIComponent("metadata.labels,ancestors");
  const limit = 50;
  const results = [];
  let start = 0;
  while (true) {
    const page = await confluenceGet(
      host,
      headers,
      `/wiki/rest/api/content/search?cql=${cql}&expand=${expand}&limit=${limit}&start=${start}`,
    );
    for (const r of page.results || []) {
      results.push({
        id: r.id,
        title: r.title,
        labels: (r.metadata?.labels?.results || []).map((l) => l.name),
        ancestorTitles: (r.ancestors || []).map((a) => a.title),
      });
    }
    if ((page.results?.length ?? 0) < limit) break;
    start += limit;
  }
  return results;
};

const parseVersion = (s) => s.split(".").map(Number);
const compareVersions = (a, b) => {
  const av = parseVersion(a);
  const bv = parseVersion(b);
  for (let i = 0; i < 3; i += 1) {
    if ((av[i] || 0) !== (bv[i] || 0)) return (av[i] || 0) - (bv[i] || 0);
  }
  return 0;
};

// "(before X.Y.Z)" marker pages document pre-X.Y.Z behavior; they are kept
// only while the build still predates X.Y.Z (FRS AC 3). "(legacy screen)"
// pages document unchanged legacy UI and stay in every build.
const legacyBeforeMarker = /\(before (\d+\.\d+\.\d+)\)/i;

const isSupersededLegacy = (title, releaseVersion) => {
  const m = title.match(legacyBeforeMarker);
  if (!m) return false;
  return compareVersions(m[1], releaseVersion) <= 0;
};

// Pages tagged applies-vX-Y are version-scoped; a page tagged only
// applies-v3-2 is excluded from a 3.3 build (FRS AC 14). A page with no
// applies-* labels is always included.
const releaseAppliesLabel = (releaseVersion) => {
  const [maj, min] = parseVersion(releaseVersion);
  return `applies-v${maj}-${min}`;
};

const matchesReleaseLabel = (labels, releaseVersion) => {
  const applies = labels.filter((l) => l.startsWith("applies-v"));
  if (applies.length === 0) return true;
  return applies.includes(releaseAppliesLabel(releaseVersion));
};

// A page is "under a LEGACY Versions folder" when any ancestor's title matches,
// regardless of folder vs page content type (FRS AC 3).
const isUnderLegacyFolder = (ancestorTitles) =>
  ancestorTitles.some((t) => /^legacy versions?$/i.test(t));

const walkCorpus = async (corpus, source, releaseVersion, spaceKey) => {
  const { host, headers } = source;
  const all = await listCorpusPages(host, headers, spaceKey, corpus.rootPageId);

  const kept = [];
  let droppedLegacyFolder = 0;
  let droppedLegacyTitle = 0;
  let droppedByLabel = 0;

  for (const p of all) {
    if (isUnderLegacyFolder(p.ancestorTitles)) {
      droppedLegacyFolder += 1;
      continue;
    }
    if (isSupersededLegacy(p.title, releaseVersion)) {
      droppedLegacyTitle += 1;
      continue;
    }
    if (!matchesReleaseLabel(p.labels, releaseVersion)) {
      droppedByLabel += 1;
      continue;
    }
    kept.push({ id: p.id, title: p.title, labels: p.labels });
  }

  return {
    pages: kept,
    counts: {
      total: all.length,
      kept: kept.length,
      droppedLegacyFolder,
      droppedLegacyTitle,
      droppedByLabel,
    },
  };
};

// Phase 1: walk — list every page under each corpus root, dropping legacy
// pages (title contains "(before X.Y.Z)" below the release version) and
// filtering by applies-vX-Y labels (FRS AC 3, AC 14).
const walk = async (config, source) => {
  if (source.kind === "fixtures") {
    throw new Error("walk: fixtures mode lands with the deterministic-run commit");
  }
  const result = {};
  for (const corpus of config.corpora) {
    const key = `${corpus.guide}/${corpus.locale}`;
    console.log(`help-refresh: walking ${key} root=${corpus.rootPageId}`);
    const { pages, counts } = await walkCorpus(
      corpus,
      source,
      config.releaseVersion,
      config.spaceKey,
    );
    console.log(
      `help-refresh:   ${counts.kept}/${counts.total} kept ` +
        `(legacy folder:${counts.droppedLegacyFolder}, ` +
        `legacy title:${counts.droppedLegacyTitle}, ` +
        `label:${counts.droppedByLabel})`,
    );
    result[key] = pages;
  }
  return result;
};

// Phase 2: fetch — download each page's ADF body, current version, and
// web-UI path. The web-UI path becomes the "View the latest version online"
// link (FRS AC 17); the version supports the deferred stale-translation Tag.
// Mutates each page in place to keep the pipeline a flat sequence.
const fetchPageBody = async (host, headers, pageId) => {
  const data = await confluenceGet(
    host,
    headers,
    `/wiki/api/v2/pages/${pageId}?body-format=atlas_doc_format`,
  );
  const adfJson = data.body?.atlas_doc_format?.value || "{}";
  return {
    adf: JSON.parse(adfJson),
    version: data.version?.number ?? null,
    webuiPath: data._links?.webui || null,
  };
};

const fetchPages = async (pagesByCorpus, source) => {
  if (source.kind === "fixtures") {
    throw new Error(
      "fetchPages: fixtures mode lands with the deterministic-run commit",
    );
  }
  const { host, headers } = source;
  for (const [key, pages] of Object.entries(pagesByCorpus)) {
    console.log(
      `help-refresh: fetching ADF for ${key} (${pages.length} pages)`,
    );
    let i = 0;
    for (const p of pages) {
      i += 1;
      const body = await fetchPageBody(host, headers, p.id);
      Object.assign(p, body);
      if (i % 10 === 0 || i === pages.length) {
        console.log(`help-refresh:   ${i}/${pages.length}`);
      }
    }
  }
  return pagesByCorpus;
};

// Phase 3: locale match — for each non-English page, read the configured
// content property (openelis-translation.sourceId per the Lango spike) and
// record its English source page id. sourceVersion is kept so a future stale-
// translation Tag can compare it against the English page's current version.
const fetchContentProperty = async (host, headers, pageId, key) => {
  const data = await confluenceGet(
    host,
    headers,
    `/wiki/api/v2/pages/${pageId}/properties`,
  );
  const prop = (data.results || []).find((r) => r.key === key);
  return prop?.value ?? null;
};

const matchLocales = async (pagesByCorpus, source, config) => {
  if (source.kind === "fixtures") {
    throw new Error(
      "matchLocales: fixtures mode lands with the deterministic-run commit",
    );
  }
  const { contentPropertyKey, sourceIdField, sourceVersionField } =
    config.localeMatcher || {};
  if (!contentPropertyKey) {
    console.log(
      "help-refresh: no localeMatcher.contentPropertyKey; skipping locale join",
    );
    return;
  }
  const { host, headers } = source;
  for (const [key, pages] of Object.entries(pagesByCorpus)) {
    const [, locale] = key.split("/");
    if (locale === "en") continue;
    let joined = 0;
    for (const p of pages) {
      const value = await fetchContentProperty(
        host,
        headers,
        p.id,
        contentPropertyKey,
      );
      if (value && typeof value === "object") {
        p.sourceId = value[sourceIdField] ?? null;
        p.sourceVersion = value[sourceVersionField] ?? null;
        if (p.sourceId) joined += 1;
      }
    }
    console.log(
      `help-refresh: locale join ${key}: ${joined}/${pages.length} matched to en`,
    );
  }
};

// Phase 4: convert — ADF → sanitized HTML. Handles panel, codeBlock,
// mediaSingle, inlineCard. DOMPurify allowlist enforces §7 security.
const convert = async (_pages) => {
  throw new Error("convert: not implemented");
};

// Phase 5: assets — download attachments, sharp → WebP, rewrite <img src>.
const processAssets = async (_pages, _source) => {
  throw new Error("processAssets: not implemented");
};

// Phase 6: emit — manifest.json, index.json (FlexSearch), pages/<slug>.html.
// Enforces the sizeBudgetBytes ceiling before writing anything.
const emit = async (_pages, _config) => {
  throw new Error("emit: not implemented");
};

const main = async () => {
  const config = await loadConfig();
  const source = useFixtures
    ? { kind: "fixtures", dir: "designs/system/in-app-help-dev-bundle" }
    : { kind: "live", ...confluenceAuth() };

  console.log(`help-refresh: release ${config.releaseVersion}, source=${source.kind}`);
  console.log(`help-refresh: ${config.corpora.length} corpora to walk`);
  for (const c of config.corpora) {
    console.log(`  - ${c.guide}/${c.locale} rootPageId=${c.rootPageId}`);
  }

  const pages = await walk(config, source);
  await fetchPages(pages, source);
  await matchLocales(pages, source, config);
  await convert(pages);
  await processAssets(pages, source);
  await emit(pages, config);
};

main().catch((err) => {
  console.error(err.message);
  process.exit(1);
});
