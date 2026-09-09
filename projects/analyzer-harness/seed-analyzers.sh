#!/usr/bin/env bash
# Seed the M1 priority analyzer instances through the OpenELIS REST API.
#
# Profile content, revisions, and defaults come from the Bridge catalog. This
# script owns only harness instance names and explicit connection values.

set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/../.." && pwd)"

if [ -f "$REPO_ROOT/.env" ]; then
  set -a
  . "$REPO_ROOT/.env"
  set +a
fi

BASE_URL="${BASE_URL:-https://localhost}"
MOCK_URL="${MOCK_URL:-http://localhost:8085}"
ANALYZER_API="$BASE_URL/api/OpenELIS-Global/rest/analyzer/analyzers"
TYPE_API="$BASE_URL/api/OpenELIS-Global/rest/analyzer-types"
LAB_UNITS_API="$BASE_URL/api/OpenELIS-Global/rest/test-catalog/lab-units"

TEST_USER="${TEST_USER:-admin}"
TEST_PASS="${TEST_PASS:-adminADMIN!}"

GENEXPERT_PROFILE_ID="genexpert-astm"
FLUOROCYCLER_PROFILE_ID="fluorocycler-xt"
QUANTSTUDIO_PROFILE_ID="quantstudio"

CATALOG_FILE="$(mktemp)"
ANALYZERS_FILE="$(mktemp)"
LAB_UNITS_FILE="$(mktemp)"
RESPONSE_FILE="$(mktemp)"
MAPPING_DIR="$(mktemp -d)"
trap 'rm -f "$CATALOG_FILE" "$ANALYZERS_FILE" "$LAB_UNITS_FILE" "$RESPONSE_FILE"; rm -rf "$MAPPING_DIR"' EXIT

fetch_json() {
  local url="$1"
  local output="$2"
  local label="$3"
  local attempt
  local status
  for attempt in 1 2 3 4 5; do
    status="$(curl -sk --connect-timeout 5 --max-time 30 -o "$output" -w "%{http_code}" -u "$TEST_USER:$TEST_PASS" "$url" || true)"
    if [ "$status" = "200" ]; then
      return 0
    fi
    [ "$attempt" -lt 5 ] && sleep "$attempt"
  done
  echo "ERROR: $label returned HTTP $status after $attempt attempts" >&2
  return 1
}

resolve_active_revision() {
  local profile_id="$1"
  python3 - "$CATALOG_FILE" "$profile_id" <<'PY'
import json
import sys

catalog_path, profile_id = sys.argv[1:]
with open(catalog_path, encoding="utf-8") as handle:
    catalog = json.load(handle)

matches = [
    item
    for item in catalog.get("types", [])
    if item.get("profileId") == profile_id and item.get("status") == "ACTIVE"
]
if len(matches) != 1:
    raise SystemExit(
        f"expected exactly one active revision for {profile_id}; found {len(matches)}"
    )
revision = matches[0].get("revision")
if not isinstance(revision, int) or revision < 1:
    raise SystemExit(f"active revision for {profile_id} is invalid: {revision!r}")
print(revision)
PY
}

resolve_lab_unit_id() {
  python3 - "$LAB_UNITS_FILE" <<'PY'
import json
import sys

with open(sys.argv[1], encoding="utf-8") as handle:
    lab_units = json.load(handle)

if not isinstance(lab_units, list) or not lab_units:
    raise SystemExit("expected at least one active lab unit")
lab_unit_id = lab_units[0].get("id")
if lab_unit_id is None or str(lab_unit_id).strip() == "":
    raise SystemExit("first active lab unit has no ID")
print(lab_unit_id)
PY
}

find_analyzer_id() {
  local analyzer_name="$1"
  fetch_json "$ANALYZER_API" "$ANALYZERS_FILE" "Analyzer list"
  python3 - "$ANALYZERS_FILE" "$analyzer_name" <<'PY'
import json
import sys

with open(sys.argv[1], encoding="utf-8") as handle:
    analyzers = json.load(handle).get("analyzers", [])
matches = [item for item in analyzers if item.get("name") == sys.argv[2]]
if len(matches) > 1:
    raise SystemExit(f"expected at most one analyzer named {sys.argv[2]!r}; found {len(matches)}")
if matches:
    analyzer_id = matches[0].get("id")
    if analyzer_id is None:
        raise SystemExit(f"analyzer named {sys.argv[2]!r} has no ID")
    print(analyzer_id)
PY
}

reconcile_profile_analyzer() {
  local name="$1"
  local profile_id="$2"
  local profile_revision="$3"
  local connection_values="{}"
  [ "$#" -ge 4 ] && connection_values="$4"

  local payload
  payload="$(
    python3 - "$name" "$profile_id" "$profile_revision" "$LAB_UNIT_ID" "$connection_values" <<'PY'
import json
import sys

name, profile_id, revision, lab_unit_id, connection_values = sys.argv[1:]
payload = {
    "name": name,
    "profileId": profile_id,
    "profileRevision": int(revision),
    "testUnitIds": [lab_unit_id],
    "connectionValues": json.loads(connection_values),
}
print(json.dumps(payload, separators=(",", ":")))
PY
  )"

  local analyzer_id
  analyzer_id="$(find_analyzer_id "$name")"
  local method="POST"
  local url="$ANALYZER_API"
  local expected_status="201"
  local action="create"
  local action_label="Created"
  if [ -n "$analyzer_id" ]; then
    method="PUT"
    url="$ANALYZER_API/$analyzer_id"
    expected_status="200"
    action="update"
    action_label="Updated"
  fi

  local status
  status="$(curl -sk --connect-timeout 5 --max-time 45 -o "$RESPONSE_FILE" -w "%{http_code}" -X "$method" "$url" -u "$TEST_USER:$TEST_PASS" -H "Content-Type: application/json" -d "$payload")"
  if [ "$status" != "$expected_status" ]; then
    echo "ERROR: Failed to $action $name (HTTP $status)" >&2
    sed 's/^/  /' "$RESPONSE_FILE" >&2
    return 1
  fi
  echo "  $action_label: $name ($profile_id@$profile_revision)"
}

lookup_mock_network_ip() {
  local name="$1"
  curl -sk --connect-timeout 3 --max-time 15 "$MOCK_URL/analyzers" |
    python3 -c '
import json
import sys

name = sys.argv[1]
for analyzer in json.load(sys.stdin).get("analyzers", []):
    if analyzer.get("name") == name:
        print(analyzer.get("ip", ""))
        break
' "$name"
}

create_mock_network() {
  local name="$1"
  local template="$2"
  local port="$3"
  local attempt
  local status
  local payload
  payload="$(python3 -c 'import json,sys; print(json.dumps({"name":sys.argv[1],"template":sys.argv[2],"port":int(sys.argv[3])}))' "$name" "$template" "$port")"

  for attempt in 1 2 3 4 5; do
    status="$(curl -sk --connect-timeout 3 --max-time 20 -o "$RESPONSE_FILE" -w "%{http_code}" -X POST "$MOCK_URL/analyzers" -H "Content-Type: application/json" -d "$payload" || true)"
    if [ "$status" = "200" ] || [ "$status" = "201" ]; then
      python3 - "$RESPONSE_FILE" <<'PY'
import json
import sys

with open(sys.argv[1], encoding="utf-8") as handle:
    print(json.load(handle).get("ip", ""))
PY
      return 0
    fi
    if [ "$status" = "409" ]; then
      lookup_mock_network_ip "$name"
      return 0
    fi
    sleep "$attempt"
  done

  echo "ERROR: Mock network $name was not created (last HTTP $status)" >&2
  sed 's/^/  /' "$RESPONSE_FILE" >&2
  return 1
}

send_mapping_json() {
  local method="$1"
  local url="$2"
  local payload_file="$3"
  local label="$4"
  local expected="${5:-200}"
  local status
  status="$(curl -sk --connect-timeout 5 --max-time 60 -o "$RESPONSE_FILE" -w "%{http_code}" \
    -u "$TEST_USER:$TEST_PASS" -X "$method" -H "Content-Type: application/json" \
    --data-binary "@$payload_file" "$url" || true)"
  if [ "$status" != "$expected" ]; then
    echo "ERROR: $label returned HTTP $status (expected $expected)" >&2
    sed 's/^/  /' "$RESPONSE_FILE" >&2
    return 1
  fi
}

prepare_current_mapping() {
  local profile_id="$1"
  local profile_revision="$2"
  local mapping_file="$MAPPING_DIR/$profile_id-mapping.json"
  local catalog_file="$MAPPING_DIR/catalog.json"
  local selection_file="$MAPPING_DIR/$profile_id-selections.json"
  local update_file="$MAPPING_DIR/$profile_id-update.json"
  local confirm_file="$MAPPING_DIR/$profile_id-confirm.json"
  local mapping_url="$TYPE_API/$profile_id/mapping?revision=$profile_revision"
  local mapping_action
  local test_id

  fetch_json "$mapping_url" "$mapping_file" "$profile_id mapping"
  fetch_json "$TYPE_API/mapping-catalog/tests" "$catalog_file" "OpenELIS mapping catalog"
  python3 - "$mapping_file" "$catalog_file" "$selection_file" <<'PY'
import json
import sys

mapping_path, catalog_path, destination = sys.argv[1:]
with open(mapping_path, encoding="utf-8") as handle:
    mapping = json.load(handle)
with open(catalog_path, encoding="utf-8") as handle:
    catalog = json.load(handle)

selections = {}
for test in mapping.get("tests", []):
    loinc = test.get("loinc")
    candidates = [
        item for item in catalog if loinc and loinc in item.get("loincCodes", [])
    ]
    candidates.sort(key=lambda item: int(item["id"]))
    selections[test["sourceRowKey"]] = candidates[0]["id"] if candidates else None

with open(destination, "w", encoding="utf-8") as handle:
    json.dump(selections, handle, separators=(",", ":"))
PY

  while IFS= read -r test_id; do
    fetch_json "$TYPE_API/mapping-catalog/tests/$test_id/result-options" \
      "$MAPPING_DIR/result-options-$test_id.json" \
      "OpenELIS result options for test $test_id"
  done < <(jq -r 'to_entries[].value // empty' "$selection_file" | sort -u)

  mapping_action="$(python3 - "$mapping_file" "$selection_file" "$MAPPING_DIR" "$update_file" <<'PY'
import json
import os
import sys

mapping_path, selection_path, options_dir, destination = sys.argv[1:]
with open(mapping_path, encoding="utf-8") as handle:
    mapping = json.load(handle)
with open(selection_path, encoding="utf-8") as handle:
    selections = json.load(handle)

tests = []
results = []
changed = False
for test in mapping.get("tests", []):
    test_id = selections.get(test["sourceRowKey"])
    test_state = "BOUND" if test_id else "EXCLUDED"
    if test.get("mappingState") != test_state or str(test.get("testId") or "") != str(test_id or ""):
        changed = True
    tests.append({
        "sourceRowKey": test["sourceRowKey"],
        "mappingState": test_state,
        "testId": test_id,
    })

    options = []
    if test_id:
        with open(os.path.join(options_dir, f"result-options-{test_id}.json"), encoding="utf-8") as handle:
            options = json.load(handle)
    for result in test.get("results", []):
        matches = [
            option for option in options
            if option.get("label", "").strip().casefold() == result["rawValue"].strip().casefold()
        ]
        result_state = "BOUND" if len(matches) == 1 else "EXCLUDED"
        result_option_id = matches[0]["id"] if len(matches) == 1 else None
        if (
            result.get("mappingState") != result_state
            or str(result.get("resultOptionId") or "") != str(result_option_id or "")
        ):
            changed = True
        results.append({
            "sourceRowKey": test["sourceRowKey"],
            "rawValue": result["rawValue"],
            "mappingState": result_state,
            "testResultId": result_option_id,
        })

payload = {
    "baseBindingFingerprint": mapping.get("bindingFingerprint"),
    "tests": tests,
    "results": results,
}
with open(destination, "w", encoding="utf-8") as handle:
    json.dump(payload, handle, separators=(",", ":"))
print("update" if changed else "current")
PY
)"

  if [ "$mapping_action" = "update" ]; then
    send_mapping_json PUT "$mapping_url" "$update_file" \
      "Complete $profile_id site mapping"
    fetch_json "$mapping_url" "$mapping_file" "$profile_id updated mapping"
  fi

  python3 - "$mapping_file" "$confirm_file" <<'PY'
import json
import sys

source, destination = sys.argv[1:]
with open(source, encoding="utf-8") as handle:
    mapping = json.load(handle)

confirmed = []
excluded = []
for test in mapping.get("tests", []):
    target = confirmed if test.get("mappingState") == "BOUND" else excluded
    target.append({"sourceRowKey": test["sourceRowKey"], "rawValue": None})
    for result in test.get("results", []):
        target = confirmed if result.get("mappingState") == "BOUND" else excluded
        target.append({"sourceRowKey": test["sourceRowKey"], "rawValue": result["rawValue"]})

payload = {
    "baseBindingFingerprint": mapping["bindingFingerprint"],
    "recognitionFingerprint": mapping["controlRecognition"]["recognitionFingerprint"],
    "confirmedRows": confirmed,
    "excludedRows": excluded,
}
with open(destination, "w", encoding="utf-8") as handle:
    json.dump(payload, handle, separators=(",", ":"))
PY

  if [ "$(python3 - "$mapping_file" <<'PY'
import json
import sys

with open(sys.argv[1], encoding="utf-8") as handle:
    print(json.load(handle).get("confirmation", {}).get("state", "UNCONFIRMED"))
PY
)" != "CURRENT" ]; then
    send_mapping_json POST "$TYPE_API/$profile_id/mapping/confirm?revision=$profile_revision" \
      "$confirm_file" "Confirm $profile_id site mapping"
  fi

  echo "  Confirmed current shared mapping for $profile_id@$profile_revision"
}

verify_profile_pins() {
  fetch_json "$ANALYZER_API" "$ANALYZERS_FILE" "Analyzer list"
  python3 - "$ANALYZERS_FILE" "$GENEXPERT_REVISION" "$QUANTSTUDIO_REVISION" "$FLUOROCYCLER_REVISION" <<'PY'
import json
import sys

with open(sys.argv[1], encoding="utf-8") as handle:
    analyzers = json.load(handle).get("analyzers", [])

expected = {
    "Cepheid GeneXpert (ASTM Mode)": ("genexpert-astm", int(sys.argv[2])),
    "QuantStudio 5": ("quantstudio", int(sys.argv[3])),
    "QuantStudio 7": ("quantstudio", int(sys.argv[3])),
    "FluoroCycler XT": ("fluorocycler-xt", int(sys.argv[4])),
}
problems = []
for name, pin in expected.items():
    matches = [
        item
        for item in analyzers
        if item.get("name") == name
        and (item.get("profileId"), item.get("profileRevision")) == pin
    ]
    if len(matches) != 1:
        problems.append(f"{name}: expected one {pin[0]}@{pin[1]} instance, found {len(matches)}")

if problems:
    raise SystemExit("\n".join(problems))
print("  Verified: all M1 harness analyzers persist their exact Bridge profile pins")
PY
}

echo "Resolving priority profiles from $TYPE_API..."
fetch_json "$TYPE_API" "$CATALOG_FILE" "Analyzer Types catalog"
GENEXPERT_REVISION="$(resolve_active_revision "$GENEXPERT_PROFILE_ID")"
FLUOROCYCLER_REVISION="$(resolve_active_revision "$FLUOROCYCLER_PROFILE_ID")"
QUANTSTUDIO_REVISION="$(resolve_active_revision "$QUANTSTUDIO_PROFILE_ID")"
echo "  $GENEXPERT_PROFILE_ID@$GENEXPERT_REVISION"
echo "  $FLUOROCYCLER_PROFILE_ID@$FLUOROCYCLER_REVISION"
echo "  $QUANTSTUDIO_PROFILE_ID@$QUANTSTUDIO_REVISION"

echo "Resolving an active lab unit from $LAB_UNITS_API..."
fetch_json "$LAB_UNITS_API" "$LAB_UNITS_FILE" "Active lab units"
LAB_UNIT_ID="$(resolve_lab_unit_id)"
echo "  lab unit $LAB_UNIT_ID"

curl -sk --connect-timeout 3 --max-time 10 -X DELETE "$MOCK_URL/analyzers/genexpert" >/dev/null 2>&1 || true

echo "Creating GeneXpert mock transport..."
GENEXPERT_IP="$(create_mock_network "genexpert" "genexpert_astm" 9600)"
if [ -z "$GENEXPERT_IP" ]; then
  echo "ERROR: GeneXpert mock transport returned no IP address" >&2
  exit 1
fi
echo "  genexpert -> $GENEXPERT_IP:9600"

echo "Creating profile-pinned analyzer instances..."
reconcile_profile_analyzer "Cepheid GeneXpert (ASTM Mode)" "$GENEXPERT_PROFILE_ID" "$GENEXPERT_REVISION" '{"port":9600}'
reconcile_profile_analyzer "QuantStudio 5" "$QUANTSTUDIO_PROFILE_ID" "$QUANTSTUDIO_REVISION" '{"directory":"/data/analyzer-imports/quantstudio-5/incoming"}'
reconcile_profile_analyzer "QuantStudio 7" "$QUANTSTUDIO_PROFILE_ID" "$QUANTSTUDIO_REVISION" '{"directory":"/data/analyzer-imports/quantstudio-7/incoming"}'
reconcile_profile_analyzer "FluoroCycler XT" "$FLUOROCYCLER_PROFILE_ID" "$FLUOROCYCLER_REVISION" '{"directory":"/data/analyzer-imports/fluorocycler-xt/incoming"}'

verify_profile_pins
echo "Preparing the M2 mapping prerequisite for guided setup..."
prepare_current_mapping "$GENEXPERT_PROFILE_ID" "$GENEXPERT_REVISION"
echo "Done. Four instances use the validated M1 Bridge profile families and the M2 mapping is current."
