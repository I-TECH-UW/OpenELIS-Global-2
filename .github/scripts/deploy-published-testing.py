#!/usr/bin/env python3
"""Deploy published images using the server's existing Compose configuration."""

import argparse
import datetime
import fcntl
import importlib.util
import json
import pathlib
import re
import shutil
import subprocess
import sys


APP_REPOSITORY = "https://github.com/DIGI-UW/OpenELIS-Global-2.git"
SERVICES = {
    "oe.openelis.org": "itechuw/openelis-global-2",
    "db.openelis.org": "itechuw/openelis-global-2-database",
    "fhir.openelis.org": "itechuw/openelis-global-2-fhir",
    "frontend.openelis.org": "itechuw/openelis-global-2-frontend",
    "proxy": "itechuw/openelis-global-2-proxy",
}


def validate_manifest(manifest):
    if not re.fullmatch(r"[0-9a-f]{40}", manifest.get("appSha", "")):
        raise ValueError("Manifest must identify the tested application commit")
    if manifest.get("appBranch") != "develop":
        raise ValueError("Testing requires a develop image manifest")
    if set(manifest.get("images", {})) != set(SERVICES):
        raise ValueError("Manifest must include exactly the five application images")
    for service, repository in SERVICES.items():
        if not re.fullmatch(re.escape(repository) + r"@sha256:[0-9a-f]{64}", manifest["images"][service]):
            raise ValueError(f"{service} must use a published DockerHub image digest")
    return manifest


def run(args, cwd, capture=False):
    return subprocess.run(args, cwd=cwd, check=True, text=True,
                          stdout=subprocess.PIPE if capture else None).stdout


def require_current_candidate(sha, cwd):
    head = run(["git", "ls-remote", APP_REPOSITORY, "refs/heads/develop"], cwd, True).split()
    if len(head) != 2 or head != [sha, "refs/heads/develop"]:
        raise ValueError("Candidate is no longer develop HEAD; refusing an obsolete deployment")


def write_json(path, value):
    temporary = path.with_suffix(path.suffix + ".tmp")
    temporary.write_text(json.dumps(value, indent=2) + "\n", encoding="utf-8")
    temporary.replace(path)


def deploy(request, diagnostics):
    manifest = validate_manifest(request["manifest"])
    spec = importlib.util.spec_from_file_location("readiness", pathlib.Path(__file__).with_name("check-readiness.py"))
    readiness = importlib.util.module_from_spec(spec)
    spec.loader.exec_module(readiness)
    contract = request["readiness"]
    readiness.validate_contract(**contract)
    app_dir = pathlib.Path(request["deploy_path"]).resolve()
    if not (app_dir / ".env").is_file():
        raise ValueError("The server must have an existing configured .env")
    state_dir = app_dir / ".openelis-ci"
    override = state_dir / "deployment-images.json"
    base_compose = ["docker", "compose", "-f", "docker-compose.yml"]
    compose = base_compose + ["-f", str(override)]
    try:
        # Called under the host deployment lock, then repeated after slow pulls.
        require_current_candidate(manifest["appSha"], app_dir)
        run(["git", "fetch", "origin", "main"], app_dir)
        run(["git", "merge", "--ff-only", "origin/main"], app_dir)
        infra_sha = run(["git", "rev-parse", "HEAD"], app_dir, True).strip()
        state_dir.mkdir(exist_ok=True)
        write_json(override, {"services": {service: {"image": image}
                                          for service, image in manifest["images"].items()}})
        run(compose + ["pull", *SERVICES], app_dir)
        require_current_candidate(manifest["appSha"], app_dir)
        target_path = state_dir / "target.json"
        if target_path.is_file():
            shutil.copy2(target_path, diagnostics / "previous-target.json")
            target_path.unlink()
        run(compose + ["up", "-d"], app_dir)
        images = {}
        for service, reference in manifest["images"].items():
            container = run(compose + ["ps", "-q", service], app_dir, True).strip()
            if not container or "\n" in container:
                raise ValueError(f"Expected one running container for {service}")
            actual = json.loads(run(["docker", "inspect", container], app_dir, True))[0]
            expected = json.loads(run(["docker", "image", "inspect", reference], app_dir, True))[0]
            if not actual["State"]["Running"] or actual["Image"] != expected["Id"]:
                raise ValueError(f"Running {service} does not match the published image")
            images[service] = {"reference": reference, "imageId": actual["Image"]}
        report = readiness.wait_until_ready(**contract)
        write_json(diagnostics / "readiness.json", report)
        if not report["ready"]:
            raise RuntimeError("Application did not become ready; inspect deployment diagnostics")
        target = {"instance": "testing", "state": "ready", "appSha": manifest["appSha"],
                  "appBranch": manifest["appBranch"], "infraSha": infra_sha, "images": images,
                  "deploymentId": request["run_id"],
                  "deployedAt": datetime.datetime.now(datetime.timezone.utc).isoformat(),
                  "verification": {"readiness": report, "url": contract["url"]}}
        write_json(target_path, target)
        write_json(diagnostics / "target.json", target)
        print(f"Testing is ready at {manifest['appSha']}", flush=True)
    finally:
        # The base configuration also works when validation/pulling failed before
        # an override was available. Status/logs do not need the candidate images.
        for filename, args in [("compose-status.txt", ["ps", "--all"]),
                               ("service-logs.txt", ["logs", "--no-color", "--tail", "250", "oe.openelis.org", "proxy"])]:
            with (diagnostics / filename).open("w", encoding="utf-8") as output:
                subprocess.run(base_compose + args, cwd=app_dir, stdout=output, stderr=subprocess.STDOUT, check=False)


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("request", type=pathlib.Path)
    args = parser.parse_args()
    diagnostics = args.request.resolve().parent / "diagnostics"
    diagnostics.mkdir(exist_ok=True)
    try:
        request = json.loads(args.request.read_text(encoding="utf-8"))
        with open("/tmp/openelis-testing-deploy.lock", "w", encoding="utf-8") as lock:
            fcntl.flock(lock, fcntl.LOCK_EX | fcntl.LOCK_NB)
            deploy(request, diagnostics)
    except Exception as error:
        (diagnostics / "failure.txt").write_text(str(error) + "\n", encoding="utf-8")
        print(f"Deployment failed: {error}", file=sys.stderr)
        return 1
    return 0


if __name__ == "__main__":
    sys.exit(main())
