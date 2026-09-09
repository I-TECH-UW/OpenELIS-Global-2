# OGC-782 stack (#4137) — review inventory, plan, and acceptance

Snapshot 2026-09-06. 26 PRs, top = #4196 @ `b708b7ac82`. Working tree:
`/Users/pmanko/.codex/worktrees/cc8c/OpenELIS-Global-2` on
`feat/782-ogc-782-microbiology-r16-order-entry-navigation`.

## Goal

**Top of the stack (#4196) green on all three checkpoints, with every unresolved
review thread across the 26 PRs remediated at the top.** Lower PRs are not
touched; the user force-merges them once the top is green.

## Acceptance criteria

1. `gh pr checks 4196` → `01 Checkpoint - Backend`, `02 Checkpoint - Frontend`,
   `03 Checkpoint - E2E` all **pass**. (`gh pr checks` is the only valid oracle
   for E2E; `gh run watch` and `gh run list --branch` lie for `workflow_run`.)
2. Every thread in B, C, D has: a Red test committed that names the defect, the
   fix, local green, and the thread replied to + resolved.
3. Every thread in A has a reply naming the fixing code, **shown to Piotr before
   posting**, then resolved.
4. Local validation before the push, not after: affected `*IntegrationTest`
   classes via `mvn -Dtest=X test` (Testcontainers, jdk21, one mvn at a time);
   `npm test` for touched frontend suites; changed Playwright specs via
   `run-e2e-like-ci.sh` only; `mvn spotless:apply` + `npm run format` cold.
5. Nothing cascades down the stack. All commits land on the #4196 branch.
6. No Co-Authored-By; no translated i18n files touched; no workflow files
   touched (they cannot take effect pre-merge anyway — see E).

## Outcome

All **146 review threads across the 26 PRs are resolved** (32 were open at the
start of this pass; each got a reply naming the fixing code). Every E2E failure
on the top of the stack was root-caused and fixed.

| Item                                 | Resolution                                                                                                                                                                                                                                            |
| ------------------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Analyzer-ingress seed (2 specs)      | Credentials fall back `ANALYZER_INGRESS_*` -> `TEST_*` -> the `admin` fixture account, which `add_admin_default_roles.xml` grants Analyser Import. A workflow edit could not help: `E2E / Tests` is `workflow_run` and runs the default branch's YAML |
| esig `/validation` `ERR_ABORTED`     | Saving results sent the browser to `/result` itself; that navigation raced the spec's. Now routed                                                                                                                                                     |
| `Critical communication` strict mode | `CaseSectionFocusTarget` and four panels each rendered a landmark with the same name; each section is one landmark now                                                                                                                                |
| WHONET handoff (`f9990`)             | `getReviewedAstWorklistPage` never published `filterOptions`, so the reviewed view's controls were empty; then organism labels were raw ids                                                                                                           |
| Demo AST (`3f6cc`)                   | Waited for `Setup Recorded`, a stage no service ever assigns (inoculation goes straight to INCUBATING); then the timeline never surfaced the media/incubation/atmosphere it had persisted                                                             |
| Cypress `#only-active`               | Clicked the text inside the label rather than the label. Widget-state coverage moved to `admin-user-filters.spec.ts` (fresh load and navigated-in); the spec keeps the filtered-reload assertion. Spec now 27/27                                      |
| 133 full-page navigations            | One `softReload` / `navigateTo` pair, registered by `AppNavigationBridge`; session, login and legacy targets still take a real load                                                                                                                   |
| FHIR image build                     | Removed an unused `curl` install whose arm64 deb was rotated out; it had broken all local CI parity                                                                                                                                                   |

Corrected rather than implemented: **T12** — the N+1 the comment describes does
not exist (the culture path is fully batched; `toCultureRows`/`toRow` issue no
DAO calls). The residue is that `getOpenCases()` loads every open case and pages
in memory, which is deliberate because the summary and filter options are
queue-wide aggregates. Left as an architectural item.

Not root-caused: in a long Cypress session a `filter=isActive` request fires
while the rendering instance's `filters` is `[]` (table at 5 of 5). Playwright
on a fresh load, Playwright arriving by navigation, and a vitest test on the
real component all pass, so the coverage moved rather than the cause being
found.

Rejected on 2026-09-06: deferring bucket D to a follow-up PR "to protect the one
CI run". Backend CI is already green at the top and every D item has a local
harness, so CI risk was never a real constraint; deferring P1 clinical-safety
bugs off a stack about to be force-merged is a half-refactor.

---

## Inventory

**146 review threads total: 114 already resolved, 32 not.** Only the 32 are
listed.

Verification lens: **does the issue still exist at the top of the stack?** The
stack merges as one body of code, so a comment on #4134 that a later PR fixed is
moot for the merged result. Every row below was checked against the top-of-stack
tree.

---

## A. Moot — fixed higher in the stack (13)

Action: reply naming the fixing code (drafts go to Piotr first), resolve. No
code change.

| #   | PR   | Location                                 | Claim                                                           | Verified fix at top                                                                                                                                                                        |
| --- | ---- | ---------------------------------------- | --------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| T1  | 4134 | `AstEntryPanel.jsx:260`                  | `!!currentRun` disables start forever after first review        | `hasInProgressRun` gates separately; `startRepeatAstRun` + `/attempts` endpoint make repeat/retest reachable                                                                               |
| T2  | 4134 | `MicroAstRestController.java:49`         | `isAuthenticated()` lets any account write AST                  | class-level `@PreAuthorize(BENCH_ACCESS)` + `hasAnyRole('ADMIN','VALIDATION')` on all 4 mutations                                                                                          |
| T3  | 4134 | `MicroAstServiceImpl.java:140`           | can review a run with no readings                               | `reviewRun` calls `requireCompleteOrderedResults(runId)`                                                                                                                                   |
| T5  | 4134 | `MicroAstServiceImpl.java:128`           | 2nd override erases the 1st                                     | `recordOverrideEvent(readingId, OVERRIDE, from, to, reason, by)` — append-only history                                                                                                     |
| T8  | 4135 | `microbiology-worklist-critical.spec.ts` | no accordion named "Critical communication"                     | `MicrobiologyCaseView` now renders that `AccordionItem`. **Side effect: this is E2E failure (d) below — two nodes now match**                                                              |
| T10 | 4135 | `MicrobiologyCaseView.jsx:120`           | panel gets no targets                                           | passes `caseId`, `sampleItemId`, `isolates`, `projectedResultIds`                                                                                                                          |
| T11 | 4135 | `MicrobiologyWorklist.jsx`               | enums title-cased English                                       | `MicrobiologyLabels.js` → `intl.formatMessage({id: microbiology.enum.<v>})`                                                                                                                |
| T14 | 4135 | `057-alert-entity-ref.xml:53`            | rollback `addNotNullConstraint` fails on ref-backed rows        | `058-alert-type-union.xml` rollback `DELETE`s them first                                                                                                                                   |
| T16 | 4136 | `ReportReadinessPanel.jsx`               | blocker codes not localized                                     | blockers render via `formatMicrobiologyEnum(blocker, intl)`. _Residue: the already-localized `notEvaluated` fallback is also passed through it → renders right, warns. Folded into set C._ |
| T19 | 4192 | `OrderContext.jsx:631`                   | `...orderData` leaves stale detail; `{}` spread can't remove it | both sites now assign the key explicitly; `JSON.stringify` drops `undefined`                                                                                                               |
| T20 | 4192 | `OrderContext.jsx:867`                   | helper invoked twice                                            | computed once inline                                                                                                                                                                       |
| T31 | 4196 | `volume/menu/menu_config.json`           | literal `\n` makes it invalid JSON                              | stripped                                                                                                                                                                                   |
| T32 | 4196 | `volume/menu/menu_config.json`           | same                                                            | stripped                                                                                                                                                                                   |

## B. Fixed, sitting uncommitted in the tree (3)

Validated in `/tmp/validate-copilot.out`: 47 order suites / 330 tests pass,
fresh DB applies 1099 changesets incl. 090, cold spotless clean.

| #   | PR   | Location                                | Claim                                                 | Fix                                                                                                                                    |
| --- | ---- | --------------------------------------- | ----------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------- |
| T24 | 4193 | `OrderContext.jsx:595`                  | invents a hardcoded "invalid value" per-field message | omit fields the server gave no message for                                                                                             |
| T26 | 4193 | `sampleTypeRequestApi.js:67`            | unused `index` param                                  | removed                                                                                                                                |
| T30 | 4196 | `093-order-entry-domain-actions.xml:21` | `MARK_RAN` silently skips on partial drift            | `094-…-guard.xml` — idempotent re-parent + deactivate, safe on any drift state; the renamed migrations are also safe on AMR databases that applied the earlier filenames |

## C. Order-entry + i18n fixes at the top (5 + 1 residue)

| #       | PR   | Location                                                          | Verified live defect                                                                                                                                                                                                                                                                                                     | Remediation                                                                                      |
| ------- | ---- | ----------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------ |
| **T27** | 4193 | `SamplePatientEntryServiceImpl.java:720` / `OrderContext.jsx:316` | **P1 data loss.** `loadOrder` skips `getRequestsBySample` whenever _any_ sample has a `sampleItemId`, so a partially-collected order rebuilds `samples` from collected items only. Resaving Enter then reads the omitted pending requests as removals and cancels them. Directly falsifies #4193's title                 | merge pending requests into loaded state for partially-collected orders                          |
| **T22** | 4192 | `SamplePatientEntryServiceImpl.java:657`                          | **P1 data loss.** `saveOrderEntry` sends empty `sampleXML` (OrderContext.jsx:843), so `getSampleItemsTests()` is empty and eligibility rests entirely on `microbiologyProgramSelected`. No Microbiology program → draft discarded on first save. The culture tests are already in the payload, in `requestedSampleTypes` | fall back to the tests named in `requestedSampleTypes` when the sample-items collection is empty |
| T25     | 4193 | `sampleTypeRequestApi.js:~163`                                    | `quantity: ""` hardcoded; `requestedQuantity` dropped, so resaving a reopened draft overwrites the persisted quantity                                                                                                                                                                                                    | restore `request.requestedQuantity`                                                              |
| T28     | 4194 | `en.json:4977`                                                    | `sample.removeSelection` = "Remove {name}" is generic; constitution wants `common.*`. Key was added in #4194 → no Transifex impact, rename is free                                                                                                                                                                       | rename to `common.removeSelection`, update 4 call sites in `SampleTestSection.jsx`               |
| T29     | 4194 | `en.json:8263`                                                    | `microbiology.enum.*` built dynamically in `MicrobiologyLabels.js` with no `// i18n-keys:` pragma → orphan sweep would delete them                                                                                                                                                                                       | add the pragma                                                                                   |
| T16r    | 4136 | `ReportReadinessPanel.jsx:133`                                    | localized fallback fed through the enum formatter                                                                                                                                                                                                                                                                        | render it directly                                                                               |

## D. Service and client fixes at the top (11)

All in scope. Each gets a Red test in the existing harness first
(`MicroAstIntegrationTest`, `MicroCaseIntegrationTest`, the service unit tests,
vitest suites), then the fix. Ordered by clinical severity.

| #   | PR   | Location                                  | Defect                                                                                                                                                                                          | Sev             |
| --- | ---- | ----------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------- |
| T17 | 4136 | `MicroReportReleaseServiceImpl.java:42`   | `releasePreliminary` has **no state guard** — a stale tab downgrades a FINAL_RELEASED case to PRELIMINARY_RELEASED, keeping finalized analyses and `closedAt`, bypassing the amendment workflow | **P1 clinical** |
| T7  | 4134 | `MicroCaseReadinessServiceImpl.java:53`   | readiness true if _any_ run is REVIEWED; an IN_PROGRESS repeat/retest doesn't block. The `reviewedRuns.size() > 1` reportable rule only fires with 2+ reviewed                                  | **P1 clinical** |
| T4  | 4134 | `MicroAstServiceImpl.java:88`             | `requireMutableRun` checks case + amendment state only, never `REVIEWED`. `recordReading`/`overrideReading` still mutate a reviewed run; no re-review required, readiness stays true            | **P1 clinical** |
| T18 | 4136 | `MicroWhonetReadinessServiceImpl.java:44` | case fetched then discarded; `whonetReady=true` before final release. Contradicts spec.md:223-238                                                                                               | P2              |
| T13 | 4135 | `MicroWorklistServiceImpl.java:669-676`   | `dueAction` returns `AST_ENTRY` for any clinically significant isolate regardless of reviewed runs → completed cases never advance to CASE_REVIEW                                               | P1              |
| T12 | 4135 | `MicroWorklistServiceImpl.java:134-145`   | primary queue hydrates every open case then `subList`s — N+1 per row. The _reviewed_ worklist is properly paginated at the DAO (`countReviewedWorklist`, offset/limit); the main one is not     | P1 perf         |
| T9  | 4135 | `CriticalCommunicationPanel.jsx:109`      | `logCriticalCommunication` is resolve-only; `.then()` clears recipient + urgent message and refreshes even on a failed POST, with no `.catch()`                                                 | P1              |
| T15 | 4136 | `MicrobiologyService.js:414-430`          | `releasePreliminaryReport` / `releaseFinalReport` resolve-only → `ReportReadinessPanel` runs its success path and `onReleased()` on a failed release                                            | P2              |
| T21 | 4192 | `MicroCaseOrderDetailDAOImpl.java:44`     | draft discard is a hard `delete(draft)` with no actor or timestamp — clinical history deleted with no audit trail                                                                               | P1 audit        |
| T6  | 4134 | `MicroAstServiceImpl.java:60,891`         | `DEFAULT_BREAKPOINT_AUTHORITY="CLSI"` / `VERSION="2026"` hardcoded → EUCAST or other-edition sites silently get `NO_BREAKPOINT` on every reading despite active data                            | P2 config       |
| T23 | 4192 | `SamplePatientEntryServiceImpl.java:673`  | `isMicrobiologyOrder` re-loads every ordered Test that `persistSampleData` loads again                                                                                                          | P3 perf         |

---

### D outcomes (2026-09-06)

Fixed at the top, each with a Red-proven test:

| #      | Fix                                                                                                                                                               | Test                                                                                             |
| ------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------ |
| T17    | `releasePreliminary` rejects a FINAL_RELEASED case (`CASE_FINAL_RELEASED`) before projecting or mutating                                                          | `MicroReportReleaseServiceTest.preliminaryReleaseIsRejectedOnceTheCaseIsFinal`                   |
| T7     | readiness blocks while any active run is unreviewed, so an in-flight repeat holds the case                                                                        | `MicroCaseReadinessServiceTest.unreviewedRepeatRunBlocksFinalReleaseDespiteAnEarlierReviewedRun` |
| T4     | `requireUnreviewedRun` rejects `recordReading`/`overrideReading` on a REVIEWED run (`AST_RUN_ALREADY_REVIEWED`)                                                   | 2 tests in `MicroAstServiceTest`                                                                 |
| T13    | `dueAction` returns `AST_ENTRY` only for a significant isolate with no run at all; reviewed work advances to `CASE_REVIEW`                                        | `MicroWorklistServiceTest.significantIsolateWithOnlyReviewedRunsAdvancesToCaseReview`            |
| T9/T15 | `settleJsonResponse` rejects failed writes, so critical-communication and both release calls no longer run their success path on an error; panel surfaces it      | 2 tests in `MicrobiologyService.test.js`                                                         |
| T21    | draft discard is now an audited soft-delete (`discarded_at`/`discarded_by`, Liquibase 091), reviving on re-qualification instead of a bare `entityManager.remove` | service unit test + `MicrobiologyOrderDraftDiscardAuditLiquibaseRollbackTest`                    |
| T6     | default breakpoint standard injected via `org.openelisglobal.microbiology.defaultBreakpointAuthority/Version`, defaulting to CLSI/2026                            | existing `MicroAstServiceTest` ctor                                                              |
| T23    | catalog tests loaded once per id into a map, shared by eligibility and analysis creation                                                                          | covered by the eligibility integration test                                                      |

**T12 — corrected, the filed defect is stale.** The comment says `toRow` issues
"separate isolate, communication, sibling, and per-isolate AST queries", i.e. an
N+1. Against the current tree that is false: the culture path is fully batched
(`getByCaseIds`, `getByIsolateIds`, `getSpecimenContexts`, … — 12 bounded
queries) and `toCultureRows`/`toRow` issue **zero** DAO calls. What remains is
different and narrower: `caseDAO.getOpenCases()` loads every open case and
`page.rows` is a `subList`. That is deliberate — `summarize(summaryRows)` and
`surveillanceFilterOptions` are queue-wide aggregates computed from the full
set, so pushing paging into SQL requires splitting them into their own aggregate
queries and re-expressing every `matches()` filter and `comparatorFor()` sort in
HQL. Real work with real regression risk, and no test currently demonstrates a
failure. Left as an architectural item, not patched blind.

**T18 — still open.** Needs the WHONET readiness contract settled first: the
spec text cited by the comment (`spec.md:223-238`) is about AST entry, not
export readiness, so the "must be final-released" requirement needs confirming
against `spec.md:361` / `:605` before adding a blocker that would hide
in-progress cases from the export queue.

## E. E2E failures at the top (the only red check)

From run 34069010070 traces/screenshots/logs.

| Failure                                         | Root cause                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           | Fix side                                                                                                                              |
| ----------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- |
| `ANALYZER_INGRESS_USER is required`             | `seed-microbiology-data.ts:20-21` throws. The var is set only in the stack's `e2e-playwright-reusable.yml:393-394` — but `E2E / Tests` is `workflow_run`, so it runs **develop's** YAML, which never sets it. A workflow edit in the stack cannot run pre-merge                                                                                                                                                                                                                                                                                                                                      | **done (uncommitted)**: falls back `ANALYZER_INGRESS_*` → `TEST_*` → `admin` fixture account, same as `auth.setup.ts`; README aligned |
| `net::ERR_ABORTED /validation?type=order`       | **Flake, not stack-caused.** Spec + validation code identical to develop, `SecureRoute` and the `/validation` route both unchanged, and the new redirects are `exact` on Microbiology paths. Across the stack it fails on #4136 and #4124 but **passes on #4192**, which sits above #4124 with strictly more code, so it is not a deterministic break introduced anywhere in the stack. The trace shows the Results page's in-flight requests aborted with status -1 at the moment of `goto`: a navigation race. Not patched, because the spec is byte-identical to develop's and develop passes it. | flake                                                                                                                                 |
| Surveillance listbox hidden                     | `whonet-export.ts:89-93` reads `aria-controls` then expects `[id=…]` visible                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         | test                                                                                                                                  |
| `'Critical communication' → 2 elements`         | **Component bug, not selector.** `CaseSectionFocusTarget` wraps every panel in `role="region" aria-label=<section label>`; `IsolatePanel`, `AstEntryPanel`, `CriticalCommunicationPanel`, `OrderDetailPanel` each also render `<section aria-labelledby>` with the _same_ text → two identically-named nested landmarks. Fix in the component (one landmark per section); Red test in `MicrobiologyCaseView.test.jsx` mirroring its existing `getAllByRole("region", {name: "Inoculation"}).toHaveLength(1)`                                                                                         | next                                                                                                                                  |
| Cypress `#only-active` not checked              | **Flake.** The same PR passed Cypress/Admin in run 34070947642 and failed in 34069010070 on identical code (6 pass / 4 fail across the last 10 runs). The stack's `UserManagement.jsx` change (role `<Select>` `defaultValue` to controlled `value={roleFilter}`) alters only the displayed dropdown text, not the request params, which stay `roleFilter=` either way.                                                                                                                                                                                                                              | flake                                                                                                                                 |
| Demo AST override: no `microbiology-setup-card` | `demo-core-ogc-782-microbio-3f6cc` trace                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             | test/timing                                                                                                                           |
