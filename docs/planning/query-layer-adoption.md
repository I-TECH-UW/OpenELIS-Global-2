# Query layer adoption — TanStack Query v4

Branch: `feat/tanstack-query-layer`, off `develop`, in its own worktree
(`~/code/worktrees/tanstack-query-layer`). The reloads are legacy admin code
that predates the microbiology stack, so this PR does not depend on it.

**Baseline for criterion 6:** develop @ `d6bab7a5a`, E2E run `33887282951`
(2026-09-04): every job green — Cypress Admin / Independent / Core, Playwright
Core 1/2 and 2/2, Playwright Harness 1/2 and 2/2.

## Goal

Give the frontend one way to fetch, cache and refetch server data, and use it to
remove every `window.location.reload()` and same-route `assign()` that today
stands in for "show me this screen's data again".

## Why

The constitution and AGENTS.md declared SWR 2.0.3 as the data layer, but it was
never installed and no file uses it. All 735 `getFromOpenElisServer` calls in
273 files hand-roll fetch inside `useEffect`. With no cache to invalidate, a
document reload was the only refetch primitive anyone had, which is why 84
screens reload after a save and 13 more navigate to their own URL to the same
end. A naive replacement (remount the routed subtree) was tried on #4196 and
reverted: it fixed nothing and broke the analyzer accept-results flows, because
a remount does not refetch a screen whose state lives above it. The fix has to
give each screen a real refetch.

TanStack Query v4 over SWR: hierarchical query keys let one
`invalidateQueries(["case", id])` refresh every panel of a microbiology case
after a write, and `useMutation` replaces the `saving` / `.finally` state every
form re-implements (the missing `.catch` handling fixed on #4196 was a symptom).
v4 is the React 17 line; v5 needs React 18.

## Acceptance criteria

1. `@tanstack/react-query` v4 in `package.json`; one `QueryClientProvider` at
   the app root; `getFromOpenElisServer` wrapped once as the shared query
   function so no other call site changes to opt in.
2. Every `window.location.reload()` outside the session/login/CSRF set is gone,
   replaced by one of: `invalidateQueries` on a converted screen, or `resetForm`
   on a form-only screen. **No screen reloads on error** (today several do,
   destroying user input).
3. Every same-route `window.location.assign()` (Validation, AnalyserResults,
   SearchResultForm, TestOrderability, BatchTestReassignment, report indexes) is
   a refetch, not a navigation. Genuine cross-screen moves use `history.push`.
4. Login, `SecureRoute`, `LandingPage`, `RedirectOldUI`, the CSRF-expiry reload
   in `Utils.ts` and the recovery button in `RouteErrorBoundary` are unchanged.
   The last one reloads after an error boundary has tripped, when component
   state is no longer usable and there is nothing to refetch into.
5. Each converted screen keeps a unit test proving the write triggers the
   refetch (or the form reset) — Red first.
6. **Per-job E2E comparison against the pre-branch baseline**: no job that was
   green goes red. The analyzer accept-results flows and Cypress admin are the
   canaries; both broke last time.
7. Constitution and AGENTS.md describe the layer that is actually installed.

## Status

Draft PR #4213, opened once the pattern was fixed so per-job E2E accumulates
while the conversion continues. The E2E workflow sets `cancel-in-progress: true`
keyed on the PR, so every push kills the run before it. Pushes are therefore
grouped into checkpoints and each run is allowed to finish, otherwise criterion
6 collects nothing.

| Step                                                        | State                           |
| ----------------------------------------------------------- | ------------------------------- |
| 1 Dependency, provider, shared query function, first screen | done                            |
| 2 Stop reloading on error                                   | done — 20 sites across 17 files |
| 3 List/queue screens refetch after a write                  | in progress                     |
| 4 Same-route `assign()` becomes a refetch                   | not started                     |
| 5 Cross-screen `assign()` becomes `history.push`            | not started                     |
| 6 Per-job E2E comparison                                    | accumulating on #4213           |

Counts, non-test source:

|                                                  | Start | Now | In scope |
| ------------------------------------------------ | ----- | --- | -------- |
| `window.location.reload()`                       | 84    | 27  | 25       |
| same-route / cross-screen `assign()` or `href =` | 85    | 72  | 55       |

Converted: `UserManagement`, the three order screens, the five rename screens,
the four create screens (`Panel`, `SampleType`, `TestSection`, `Uom`) and the
three test-assign screens and `TestActivation`. The one
`window.location.replace` in the frontend went with them.

### One constraint the conversion has to respect

TanStack's structural sharing returns the same object reference when a refetch
produces deep-equal data, so an effect keyed on that data does not run again
after `invalidateQueries`. Seeding local state from a read that way therefore
never re-seeds. What a screen shows has to be derived — the pending edit on top
of the read, `pendingEdits ?? data?.field` — and the edit cleared explicitly on
save and on discard. Reloading the document used to clear it.

The three order screens were converted the mirrored way first and lost the
ability to throw a pending reorder away: discarding is exactly the case where
the stored order has not changed. Their test passed because it changed the
stored order first, which is the one thing discarding never does.

### What E2E can and cannot say here

No Cypress or Playwright spec opens any of the screens converted so far. The
specs that reach admin go to `/MasterListsPage` and from there to the test
catalog editor, label presets, calendar management and the site menus. So the
per-job comparison is a regression check on what every screen now shares — the
query client at the app root and the reloads removed from error paths in 17
files — and says nothing about the converted screens themselves. Their unit
tests are the only guard, which is why each is written to fail when the
behaviour is reverted.

### Per-job E2E, branch vs baseline

Baseline is run `33887282951` on develop `d6bab7a5a`, every job green.

| Job           | Baseline | Branch | Checkpoint |
| ------------- | -------- | ------ | ---------- |
| Shared Build  | green    | green  | `fbab7a6`  |
| Static        | green    | green  | `fbab7a6`  |
| Image         | green    | green  | `fbab7a6`  |
| Cypress ×3    | green    | queued | `fbab7a6`  |
| Playwright ×4 | green    | queued | `fbab7a6`  |

`E2E / Tests` runs as a `workflow_run`, so it reports develop's branch and sha
and never appears against the PR's own commit: read it through the
`03 Checkpoint - E2E` status instead of `gh pr checks`. Its concurrency is keyed
on the triggering run's sha, so a run already started survives a later push;
only `03 - E2E` itself cancels per PR.

## Plan

| Step | Work                                                                                                                                       |
| ---- | ------------------------------------------------------------------------------------------------------------------------------------------ |
| 1    | Add the dependency, provider and shared query function; convert one list screen (UserManagement) end to end with tests, to fix the pattern |
| 2    | Form-only screens (TestAdd and siblings): `resetForm`, stop reloading on error                                                             |
| 3    | List/queue screens: `useQuery` + `invalidateQueries` after each write                                                                      |
| 4    | Same-route navigations: refetch instead of navigate                                                                                        |
| 5    | Cross-screen `assign()`/`href=`: `history.push`                                                                                            |
| 6    | Baseline vs branch E2E comparison per job; fix anything that moved                                                                         |

## Out of scope

Migrating the other ~240 files that fetch but never reload. They convert as they
are touched.
