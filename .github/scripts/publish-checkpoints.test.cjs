const test = require("node:test");
const assert = require("node:assert/strict");
const wait = require("./publish-checkpoints.cjs");
const sha = "a".repeat(40);
const backend = (conclusion, id = 1, head_sha = sha) => ({
  name: "01 Checkpoint - Backend",
  id,
  head_sha,
  status: "completed",
  conclusion,
});

async function run(checks, state = "success") {
  let clock = 0;
  return wait({
    owner: "test",
    repo: "test",
    sha,
    core: { info() {} },
    github: {
      rest: {
        repos: {
          async listCommitStatusesForRef(request) {
            assert.equal(request.ref, sha);
            return {
              data: state ? [{ context: "03 Checkpoint - E2E", state }] : [],
            };
          },
        },
        checks: {
          async listForRef(request) {
            assert.equal(request.ref, sha);
            return { data: { check_runs: checks } };
          },
        },
      },
    },
    timeoutMs: 2,
    pollMs: 1,
    now: () => clock,
    sleep: async () => {
      clock++;
    },
  });
}

test("both checkpoints for the candidate allow publication", () =>
  run([backend("success")]));
test("an upgrade/backend failure blocks publication even when E2E passes", async () => {
  await assert.rejects(run([backend("failure")]), /Backend.*failure/);
});
test("the latest backend result overrides an earlier successful run", async () => {
  await assert.rejects(
    run([backend("success"), backend("failure", 2)]),
    /Backend.*failure/,
  );
});
test("a checkpoint from another commit cannot authorize publication", async () => {
  await assert.rejects(
    run([backend("success", 1, "b".repeat(40))]),
    /Timed out/,
  );
});
test("missing, pending, and skipped backend checks cannot authorize publication", async () => {
  await assert.rejects(run([]), /Timed out/);
  await assert.rejects(
    run([{ ...backend(null), status: "in_progress" }]),
    /Timed out/,
  );
  await assert.rejects(run([backend("skipped")]), /Backend.*skipped/);
});
test("failed or missing E2E checks cannot authorize publication", async () => {
  await assert.rejects(run([backend("success")], "failure"), /E2E.*failure/);
  await assert.rejects(run([backend("success")], null), /Timed out/);
});
