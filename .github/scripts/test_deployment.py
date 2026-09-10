import importlib.util
import json
from pathlib import Path
import tempfile
import unittest
from unittest.mock import patch

import test_readiness

spec = importlib.util.spec_from_file_location("deployment", Path(__file__).with_name("deploy-published-testing.py"))
deployment = importlib.util.module_from_spec(spec)
spec.loader.exec_module(deployment)


class DeploymentTest(unittest.TestCase):
    def setUp(self):
        self.directory = tempfile.TemporaryDirectory()
        self.addCleanup(self.directory.cleanup)
        self.root = Path(self.directory.name)
        (self.root / ".env").write_text("SERVER_SECRET=keep-existing-value\n")
        self.diagnostics = self.root / "diagnostics"
        self.diagnostics.mkdir()
        self.target = self.root / ".openelis-ci/target.json"
        self.target.parent.mkdir(parents=True)
        self.target.write_text('{"deploymentId":"previous-ready"}')
        self.override = self.target.with_name("deployment-images.json")
        self.previous_override = json.dumps({"services": {
            service: {"image": repository + "@sha256:" + "d" * 64}
            for service, repository in deployment.SERVICES.items()}})
        self.override.write_text(self.previous_override)
        self.sha = "a" * 40
        self.request = {"manifest": {"appSha": self.sha, "appBranch": "develop", "images": {
            service: repository + "@sha256:" + "b" * 64 for service, repository in deployment.SERVICES.items()}},
            "deploy_path": str(self.root), "run_id": "123-1",
            "readiness": {"url": "http://127.0.0.1:1/health", "timeout": 0.02, "interval": 0.01}}
        self.wrong_image = False
        self.branch_head = self.sha
        self.commands = []

    def command(self, args, _cwd, capture=False):
        self.commands.append(args)
        if args[:2] == ["git", "ls-remote"]:
            return self.branch_head + "\trefs/heads/develop\n"
        if args == ["git", "rev-parse", "HEAD"]:
            return self.sha
        if "ps" in args:
            return "container-id"
        if args[:2] == ["docker", "inspect"]:
            return json.dumps([{"State": {"Running": True}, "Image": "wrong" if self.wrong_image else "sha256:actual"}])
        if args[:3] == ["docker", "image", "inspect"]:
            return json.dumps([{"Id": "sha256:actual"}])
        return ""

    def test_mutable_missing_and_foreign_images_are_rejected(self):
        manifest = self.request["manifest"]
        for value in ["itechuw/openelis-global-2:develop", "attacker/image@sha256:" + "b" * 64]:
            manifest["images"]["oe.openelis.org"] = value
            with self.assertRaises(ValueError):
                deployment.validate_manifest(manifest)
        del manifest["images"]["oe.openelis.org"]
        with self.assertRaises(ValueError):
            deployment.validate_manifest(manifest)

    def test_invalid_readiness_contract_is_rejected_before_deployment(self):
        self.request["readiness"]["timeout"] = 0
        with patch.object(deployment, "run") as command:
            with self.assertRaises(ValueError):
                deployment.deploy(self.request, self.diagnostics)
            command.assert_not_called()

    def test_old_build_cannot_overwrite_a_newer_deployment(self):
        self.branch_head = "c" * 40
        with patch.object(deployment, "run", side_effect=self.command), patch.object(deployment.subprocess, "run"):
            with self.assertRaisesRegex(ValueError, "obsolete deployment"):
                deployment.deploy(self.request, self.diagnostics)
        self.assertFalse(any("up" in command or "pull" in command for command in self.commands))
        self.assertEqual("previous-ready", json.loads(self.target.read_text())["deploymentId"])
        self.assertTrue((self.diagnostics / "compose-status.txt").exists())

    def test_candidate_is_rechecked_after_image_pull_before_restart(self):
        def advance_during_pull(args, cwd, capture=False):
            result = self.command(args, cwd, capture)
            if "pull" in args:
                self.branch_head = "c" * 40
            return result

        for existing_override in (True, False):
            with self.subTest(existing_override=existing_override):
                if not existing_override:
                    self.override.unlink()
                self.branch_head = self.sha
                self.commands.clear()
                with patch.object(deployment, "run", side_effect=advance_during_pull), patch.object(deployment.subprocess, "run"):
                    with self.assertRaisesRegex(ValueError, "obsolete deployment"):
                        deployment.deploy(self.request, self.diagnostics)
                self.assertFalse(any("up" in command for command in self.commands))
                self.assertEqual("previous-ready", json.loads(self.target.read_text())["deploymentId"])
                if existing_override:
                    self.assertEqual(self.previous_override, self.override.read_text())
                else:
                    self.assertFalse(self.override.exists())
                self.assertEqual({"target.json"} | ({"deployment-images.json"} if existing_override else set()),
                                 {path.name for path in self.target.parent.iterdir()})

    def test_failed_image_pull_preserves_the_previous_override(self):
        def fail_pull(args, cwd, capture=False):
            result = self.command(args, cwd, capture)
            if "pull" in args:
                raise deployment.subprocess.CalledProcessError(1, args)
            return result

        with patch.object(deployment, "run", side_effect=fail_pull), patch.object(deployment.subprocess, "run"):
            with self.assertRaises(deployment.subprocess.CalledProcessError):
                deployment.deploy(self.request, self.diagnostics)
        self.assertFalse(any("up" in command for command in self.commands))
        self.assertEqual(self.previous_override, self.override.read_text())
        self.assertEqual("previous-ready", json.loads(self.target.read_text())["deploymentId"])
        self.assertEqual({"target.json", "deployment-images.json"},
                         {path.name for path in self.target.parent.iterdir()})

    def test_failed_startup_withdraws_stale_ready_identity_and_keeps_it_in_diagnostics(self):
        with patch.object(deployment, "run", side_effect=self.command), patch.object(deployment.subprocess, "run"):
            with self.assertRaisesRegex(RuntimeError, "did not become ready"):
                deployment.deploy(self.request, self.diagnostics)
        self.assertFalse(self.target.exists())
        self.assertEqual("previous-ready",
                         json.loads((self.diagnostics / "previous-target.json").read_text())["deploymentId"])
        self.assertFalse(json.loads((self.diagnostics / "readiness.json").read_text())["ready"])
        self.assertTrue((self.diagnostics / "compose-status.txt").exists())
        self.assertTrue((self.diagnostics / "service-logs.txt").exists())
        self.assertIn("keep-existing-value", (self.root / ".env").read_text())

    def test_wrong_running_image_cannot_publish_ready(self):
        self.wrong_image = True
        with patch.object(deployment, "run", side_effect=self.command), patch.object(deployment.subprocess, "run"):
            with self.assertRaisesRegex(ValueError, "does not match"):
                deployment.deploy(self.request, self.diagnostics)
        self.assertFalse(self.target.exists())

    def test_ready_identity_requires_both_running_digests_and_health(self):
        # Exercise the same HTTP probe as deployment, without a live VM or Docker.
        test_readiness.ReadinessTest.setUpClass()
        self.addCleanup(test_readiness.ReadinessTest.tearDownClass)
        test_readiness.ReadinessTest.server.response = (200, "application/json", b'{"status": "UP"}')
        self.request["readiness"].update(url=test_readiness.ReadinessTest.url, timeout=1)
        expected_override = {"services": {service: {"image": reference}
                                          for service, reference in self.request["manifest"]["images"].items()}}

        def check_selected_images(args, cwd, capture=False):
            if "pull" in args or "up" in args:
                selected_override = Path(args[args.index("-f", 4) + 1])
                self.assertEqual(expected_override, json.loads(selected_override.read_text()))
                if "pull" in args:
                    self.assertEqual(self.previous_override, self.override.read_text())
                else:
                    self.assertEqual(self.override, selected_override)
            return self.command(args, cwd, capture)

        with patch.object(deployment, "run", side_effect=check_selected_images), patch.object(deployment.subprocess, "run"):
            deployment.deploy(self.request, self.diagnostics)
        target = json.loads(self.target.read_text())
        self.assertEqual(self.sha, target["appSha"])
        self.assertEqual(5, len(target["images"]))
        self.assertTrue(target["verification"]["readiness"]["ready"])
        self.assertEqual(expected_override, json.loads(self.override.read_text()))
        self.assertEqual({"target.json", "deployment-images.json"},
                         {path.name for path in self.target.parent.iterdir()})


if __name__ == "__main__":
    unittest.main()
