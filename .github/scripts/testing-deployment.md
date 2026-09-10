Testing deployment is owned by the application's `Publish Images` workflow. It
uses the existing `/home/ubuntu/openelis-docker/docker-compose.yml` on the VM;
no reusable workflow or pending change in that repository is required.

The publication gate requires backend success for the application commit and E2E
success for the particular image build run and attempt. Testing deployment
checks that the candidate is still `develop` HEAD under a host lock, deploys the
five published image digests, verifies the running images, then requires JSON
application health. A candidate superseded during image pulls fails before
restarting containers. The server's configured `.env` and data volumes are
preserved; a conflicting infrastructure fast-forward fails without resetting
local files.

The existing `TESTING_VM_SSH_KEY` secret and `DEPLOY_HOST`, `TESTING_VM_USER`,
`DEPLOY_PORT`, and `DEPLOY_PATH` variables still configure access. Optional
readiness variables are:

| Variable                     | Default                                                          |
| ---------------------------- | ---------------------------------------------------------------- |
| `TESTING_READINESS_URL`      | `https://testing.openelis-global.org/api/OpenELIS-Global/health` |
| `TESTING_READINESS_TIMEOUT`  | `300` seconds                                                    |
| `TESTING_READINESS_JSON_KEY` | `status` (supports dotted nested keys)                           |
| `TESTING_READINESS_EXPECTED` | `"UP"` (JSON-encoded value)                                      |

Redirects, HTML responses, and unexpected JSON values fail readiness. Compose
status, bounded backend/proxy logs, and readiness results are attached to the
workflow. A verified `target.json` is retained in the artifact and on the host
at `.openelis-ci/target.json`; it is not a public endpoint. Review widget
installation is a separate follow-up.

Run the focused checks from the repository root:

```sh
node --test .github/scripts/publish-checkpoints.test.cjs
python3 -m unittest discover -s .github/scripts -p 'test_*.py' -v
```

The Python tests require PyYAML and use temporary localhost HTTP servers. Docker
operations are mocked; the tests do not deploy to the testing VM.
