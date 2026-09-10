"""Guard the candidate publication workflow, which workflow_run PR checks do not execute."""

import os
from pathlib import Path
import subprocess
import unittest

import yaml


ROOT = Path(__file__).resolve().parents[2]


class PublicationWorkflowTest(unittest.TestCase):
    def test_publication_does_not_require_an_external_workflow_interface(self):
        workflow = yaml.load((ROOT / ".github/workflows/publish-images.yml").read_text(), Loader=yaml.BaseLoader)
        for name, job in workflow["jobs"].items():
            with self.subTest(job=name):
                if "uses" in job:
                    self.assertTrue(job["uses"].startswith("./.github/workflows/"),
                                    "External workflow inputs must not prevent publication from loading")

    def test_reporter_posts_the_build_status_consumed_by_publication(self):
        workflow = yaml.load((ROOT / ".github/workflows/e2e-tests.yml").read_text(), Loader=yaml.BaseLoader)
        for job in ("set-pending-status", "report-status"):
            script = workflow["jobs"][job]["steps"][0]["with"]["script"]
            script = script.replace("${{ github.event.workflow_run.id }}", "123")
            script = script.replace("${{ github.event.workflow_run.run_attempt }}", "2")
            script = script.replace("${{ needs.e2e-gate.result }}", "success")
            script = script.replace("${{ needs.setup.outputs.artifact_source_mode }}", "cross_run")
            script = script.replace("${{ needs.setup.outputs.is_fork }}", "false")
            harness = """
const assert = require('node:assert/strict');
const posted = [];
const context = {serverUrl:'https://github.com', repo:{owner:'test',repo:'test'}, runId:456};
const github = {rest:{repos:{createCommitStatus:async status => posted.push(status)}}};
(async () => {
""" + script + """
assert.deepEqual(posted.map(status => status.context),
  ['03 Checkpoint - E2E', '03 Checkpoint - E2E / build-123-2']);
assert.ok(posted.every(status => status.state === process.env.EXPECTED_STATE));
})().catch(error => { console.error(error); process.exitCode = 1; });
"""
            with self.subTest(job=job):
                subprocess.run([os.environ.get("NODE_BINARY", "node"), "-e", harness], check=True,
                               env={**os.environ, "EXPECTED_STATE": "pending" if job == "set-pending-status" else "success"})


if __name__ == "__main__":
    unittest.main()
