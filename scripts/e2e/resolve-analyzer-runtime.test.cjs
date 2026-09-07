const assert = require("node:assert/strict");
const { test } = require("node:test");
const { resolveAnalyzerRuntime } = require("./resolve-analyzer-runtime.cjs");

test("plugin builds retain their artifacts and existing analyzer stories", () => {
  assert.deepEqual(resolveAnalyzerRuntime("plugins"), {
    include_plugin_jars: "true",
    analyzer_projects: "harness-foundational,harness-demo",
    seed_analyzer_traffic: "false",
  });
});

test("Bridge builds isolate setup from the required unresolved-traffic story", () => {
  assert.deepEqual(resolveAnalyzerRuntime("bridge"), {
    include_plugin_jars: "false",
    analyzer_projects: "harness-demo",
    seed_analyzer_traffic: "true",
  });
});

test("existing build artifacts without runtime metadata retain their contract", () => {
  assert.deepEqual(
    resolveAnalyzerRuntime(undefined),
    resolveAnalyzerRuntime("plugins"),
  );
});

test("malformed runtime metadata fails instead of dropping analyzer coverage", () => {
  for (const value of [
    "",
    "none",
    "false",
    "bridge\nanalyzer_projects=",
    "BRIDGE",
  ]) {
    assert.throws(
      () => resolveAnalyzerRuntime(value),
      /Unsupported analyzer runtime/,
    );
  }
});
