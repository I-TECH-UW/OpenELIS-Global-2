"""The Linux installer retains one site key and mounts it into the application."""

import importlib.util
import os
from pathlib import Path
import secrets
import tempfile
import unittest
from unittest.mock import patch

import yaml

ROOT = Path(__file__).resolve().parents[2]


class LinuxInstallerKeyTest(unittest.TestCase):
    def test_fresh_and_existing_installs_mount_the_same_persisted_key(self):
        source = ROOT / "install/installerTemplate/linux/setup_OpenELIS.py"
        spec = importlib.util.spec_from_file_location("linux_installer", source)
        installer = importlib.util.module_from_spec(spec)
        spec.loader.exec_module(installer)
        with tempfile.TemporaryDirectory() as directory:
            root = Path(directory)
            config = root / "config"
            config.mkdir()
            deployment = root / "deployment"
            deployment.mkdir()
            installer.CONFIG_DIR = str(config) + "/"
            installer.OE_VAR_DIR = str(deployment) + "/"
            installer.INSTALLER_TEMPLATE_DIR = str(source.parent / "templates") + "/"
            installer.DB_HOST = "database.example.test"
            site_key = secrets.token_hex(32)
            with patch.object(installer, "getpass", side_effect=[site_key, site_key]):
                installer.get_set_encryption_key()
            key_file = config / "ENCRYPTION_KEY"
            original = key_file.read_bytes()
            self.assertEqual(site_key, installer.ENCRYPTION_KEY)
            with patch.object(installer, "getpass", side_effect=AssertionError("must retain existing key")):
                installer.get_set_encryption_key()
            self.assertEqual(original, key_file.read_bytes())
            previous_directory = Path.cwd()
            try:
                os.chdir(root)
                installer.create_docker_compose_file()
            finally:
                os.chdir(previous_directory)
            rendered = (deployment / "docker-compose.yml").read_text()
            compose = yaml.safe_load(rendered)
            mount = next(item for item in compose["services"]["oe.openelis.org"]["secrets"]
                         if item["source"] == "encryption.key")
            self.assertEqual("/etc/openelis-global/install/encryption.key", mount["target"])
            self.assertEqual(str(key_file), compose["secrets"]["encryption.key"]["file"])
            self.assertNotIn(site_key, rendered)
            self.assertEqual(original, key_file.read_bytes())


if __name__ == "__main__":
    unittest.main()
