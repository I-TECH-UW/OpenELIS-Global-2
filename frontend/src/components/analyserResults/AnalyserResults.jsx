import React, {
  useContext,
  useEffect,
  useMemo,
  useReducer,
  useRef,
  useState,
} from "react";
import {
  Accordion,
  AccordionItem,
  Button,
  DataTable,
  InlineNotification,
  Link as CarbonLink,
  OverflowMenu,
  OverflowMenuItem,
  Pagination,
  RadioButton,
  RadioButtonGroup,
  Select,
  SelectItem,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableExpandHeader,
  TableExpandRow,
  TableExpandedRow,
  TableHead,
  TableHeader,
  TableRow,
  Tag,
  TextArea,
  TextInput,
} from "@carbon/react";
import { Copy, Edit, WarningAltFilled } from "@carbon/icons-react";
import { FormattedMessage, useIntl } from "react-intl";
import { Link as RouterLink, useHistory, useLocation } from "react-router-dom";
import { NotificationKinds } from "../common/CustomNotification";
import { postToOpenElisServerFullResponse } from "../utils/Utils";
import {
  hasServerPages,
  serverPageArrowsProps,
  serverPaginationProps,
} from "../utils/serverPaging";
import ServerPageArrows from "../common/ServerPageArrows";
import { NotificationContext } from "../layout/Layout";
import { ConfigurationContext } from "../layout/Layout";
import { convertAlphaNumLabNumForDisplay } from "../utils/Utils";
import ResultAlertModal, {
  acknowledgementRefusal,
} from "../resultPage/ResultAlertModal";
import PlacementNotice, {
  AnalysisChooser,
  PlacementStateTag,
} from "./PlacementNotice";
import InstrumentReported from "./InstrumentReported";
import ResultParts, { decisionKey, groupTestParts } from "./ResultParts";
import DeliveryBundleModal from "./DeliveryBundleModal";
import RedirectControl from "./RedirectControl";
import { analyzerResultsPath } from "./analyzerResultsRoutes.jsx";
import "./AnalyserResults.css";

// Held for a decision the reviewer can make on the page, not for a mapping fix.
const REVIEWABLE_HOLDS = ["awaiting_specimen", "awaiting_placement"];

// The reasons a result can be held for; any other reason shows its code.
const HOLD_REASONS = [
  "arrived_under_other_revision",
  "assay_not_enabled",
  "invalid_result_mapping",
  "qc_target_missing",
  "result_mapping_not_ready",
  "run_failed",
  "test_mapping_not_ready",
  "unknown_analyzer_result_value",
  "unknown_analyzer_test",
];

const DECISION_FIELDS = {
  accept: "isAccepted",
  retest: "isRejected",
  ignore: "isDeleted",
};

// HL7 and ASTM flag a result within its reference range as N; only another
// flag needs the reviewer's eye.
const NORMAL_FLAGS = ["N", ""];

export const buildHeldResultResolutionUrl = (row, analyzerId) => {
  const mappingIssues = [
    "unknown_analyzer_test",
    "test_mapping_not_ready",
    "unknown_analyzer_result_value",
    "result_mapping_not_ready",
    "invalid_result_mapping",
  ];
  if (
    !mappingIssues.includes(row.importIssueReason) ||
    !row.sourceProfileId ||
    !row.sourceProfileRevision ||
    !row.rawTestCode ||
    !analyzerId
  ) {
    return null;
  }

  const query = new URLSearchParams({
    returnTo: analyzerResultsPath(analyzerId),
    focusTest: row.rawTestCode,
  });
  if (row.rawResultValue) {
    query.set("focusValue", row.rawResultValue);
  }
  return `/analyzers/${encodeURIComponent(analyzerId)}/mapping?${query.toString()}`;
};

/** The decision a row carries: accept, retest, ignore or none. */
export const decisionOf = (row) =>
  Object.keys(DECISION_FIELDS).find((decision) =>
    Boolean(row[DECISION_FIELDS[decision]]),
  ) || "";

const reviewPriority = (row) =>
  !row.importIssueReason
    ? 2
    : REVIEWABLE_HOLDS.includes(row.importIssueReason)
      ? 1
      : 0;

/**
 * The rows of a page, one group per sample in the order the page lists them.
 * Each group splits into the rows the reviewer decides here and the rows held
 * for a fix made elsewhere, names the row that speaks for the sample (the one
 * the reviewer can act on first), and the placement state its reviewable rows
 * share, when they share one.
 */
export const groupBySample = (rows) => {
  const groups = new Map();
  rows.forEach((row) => {
    const key = String(row.sampleGroupingNumber ?? row.accessionNumber);
    if (!groups.has(key)) {
      groups.set(key, { key, rows: [] });
    }
    groups.get(key).rows.push(row);
  });
  return Array.from(groups.values()).map((group) => {
    const reviewRows = group.rows.filter((row) => reviewPriority(row) > 0);
    const heldRows = group.rows.filter((row) => reviewPriority(row) === 0);
    const representative = group.rows.reduce((best, row) =>
      reviewPriority(row) > reviewPriority(best) ? row : best,
    );
    const states = new Set(reviewRows.map((row) => row.placement?.state));
    const uniformState =
      reviewRows.length > 0 && states.size === 1 && !states.has(undefined)
        ? reviewRows[0].placement.state
        : null;
    return { ...group, reviewRows, heldRows, representative, uniformState };
  });
};

// "Accept all" on a sample takes only one whose every result has exactly one
// analysis waiting for it and a patient that matches the order or was not
// reported; anything else is decided result by result, with its placement
// in view.
export const sampleIsMatched = (group) =>
  group.reviewRows.length > 0 &&
  group.reviewRows.every(
    (result) =>
      result.placement?.state === "RESOLVED" &&
      ["NOT_REPORTED", "MATCH"].includes(
        result.placement?.patient?.status ?? "NOT_REPORTED",
      ),
  );

const AnalyserResults = (props) => {
  const componentMounted = useRef(false);
  const draftEdits = useRef({});
  const history = useHistory();
  const location = useLocation();

  const { setNotificationVisible, addNotification } =
    useContext(NotificationContext);
  const { configurationProperties } = useContext(ConfigurationContext);

  const intl = useIntl();

  const [isSubmitting, setIsSubmitting] = useState(false);
  // OGC-1417: retyped values the server will not accept until the reviewer
  // acknowledges them as critical, or confirms them outside the valid range
  const [resultAlert, setResultAlert] = useState(null);
  const [bundleReceiptId, setBundleReceiptId] = useState(null);
  const [dismissingRunIds, setDismissingRunIds] = useState([]);
  // Rows whose value the reviewer opened for editing.
  const [editingIds, setEditingIds] = useState(() => new Set());
  // The rows are the server's objects, edited in place so one save posts them
  // all; an edit asks for a render so the controls show the row's new state.
  const [, rowsEdited] = useReducer((n) => n + 1, 0);

  useEffect(() => {
    componentMounted.current = true;
    return () => {
      componentMounted.current = false;
    };
  }, []);

  // Edits restored after a mapping visit stay unsaved drafts, so the next
  // visit carries them again; a retyped value stays open for editing.
  useEffect(() => {
    draftEdits.current = Object.fromEntries(
      Object.entries(props.restoredEdits ?? {}).map(([id, fields]) => [
        id,
        { ...fields },
      ]),
    );
    setEditingIds(
      new Set(
        Object.entries(props.restoredEdits ?? {})
          .filter(([, fields]) => "result" in fields)
          .map(([id]) => String(id)),
      ),
    );
  }, [props.results, props.restoredEdits]);

  const rememberEdit = (rowId, field, value) => {
    const id = String(rowId);
    draftEdits.current[id] = { ...draftEdits.current[id], [field]: value };
  };

  const openMappingWithDraft = (event, resolutionUrl) => {
    if (
      event.button !== 0 ||
      event.metaKey ||
      event.ctrlKey ||
      event.shiftKey ||
      event.altKey
    ) {
      return;
    }
    event.preventDefault();
    const edits = Object.fromEntries(
      Object.entries(draftEdits.current).map(([id, fields]) => [
        id,
        { ...fields },
      ]),
    );
    const worklistDraft = {
      analyzerId: String(props.analyzerId),
      page: Number(props.results?.paging?.currentPage) || 1,
      edits,
    };
    history.replace({
      pathname: location.pathname,
      search: location.search,
      state: { ...location.state, worklistDraft },
    });
    history.push(resolutionUrl, { worklistDraft });
  };

  const allResults = props.results?.resultList ?? [];
  const patientResults = allResults.filter((r) => !r.isControl);
  // Accept, retest and ignore are decided per test: its first row carries the
  // decision, and the rows on its components show beneath its main result.
  const { decisionHeadIds, partsByHeadId, partIds } =
    groupTestParts(patientResults);
  const tableRows = patientResults.filter((row) => !partIds.has(row.id));
  const sampleGroups = groupBySample(tableRows);
  const decisionRows = tableRows.filter(
    (row) => reviewPriority(row) > 0 && decisionHeadIds.has(row.id),
  );
  const arrows = serverPageArrowsProps({
    paging: props.results?.paging,
    onPageRequest: (pageNumber) => props.loadPage?.(pageNumber),
  });
  const heldPatientResults = patientResults.filter(
    (result) => result.importIssueReason,
  );
  const qcResults = allResults.filter((r) => r.isControl);
  const hasQcFailures = qcResults.some(
    (r) =>
      r.result && (/failed/i.test(r.result) || /\binvalid\b/i.test(r.result)),
  );
  const formatLabNum = (accessionNumber) =>
    configurationProperties?.AccessionFormat === "ALPHANUM"
      ? convertAlphaNumLabNumForDisplay(accessionNumber)
      : accessionNumber;

  const handleSave = () => {
    if (isSubmitting) {
      return;
    }
    setIsSubmitting(true);
    postToOpenElisServerFullResponse(
      "/rest/AnalyzerResults",
      JSON.stringify(props.results),
      handleResponse,
    );
  };
  const handleResponse = async (response) => {
    let message = intl.formatMessage({ id: "validation.save.error" });
    let kind = NotificationKinds.error;
    setIsSubmitting(false);
    if (response.status == 422) {
      const body = await response.json().catch(() => null);
      const refusal = acknowledgementRefusal(
        body ? { ...body, status: 422 } : null,
      );
      if (refusal) {
        setResultAlert(refusal);
        return;
      }
    }
    if (response.status == 200) {
      message = intl.formatMessage({ id: "validation.save.success" });
      kind = NotificationKinds.success;
      props.refreshResults?.(Number(props.results?.paging?.currentPage) || 1);
    } else {
      const detail = await response.text().catch(() => "");
      if (detail) {
        message = message + ": " + detail.substring(0, 200);
      }
    }
    addNotification({
      kind: kind,
      title: intl.formatMessage({ id: "notification.title" }),
      message: message,
    });
    setNotificationVisible(true);
  };

  const confirmResultAlert = () => {
    const pending = resultAlert;
    setResultAlert(null);
    if (!pending) {
      return;
    }
    const rows = props.results?.resultList || [];
    pending.alerts.forEach((alert) => {
      const row = rows.find(
        (candidate) => String(candidate.id) === String(alert.rowId),
      );
      if (!row) {
        return;
      }
      if (alert.kind === "CRITICAL") {
        row.criticalAcknowledged = true;
      } else {
        row.invalidResultConfirmed = true;
      }
    });
    handleSave();
  };

  const findRow = (rowId) =>
    (props.results?.resultList || []).find(
      (candidate) => String(candidate.id) === String(rowId),
    );

  const handleFieldChange = (rowId, field, value) => {
    const row = findRow(rowId);
    if (!row) return;
    row[field] = value;
    rememberEdit(rowId, field, value);
    if (field === "result") {
      row.criticalAcknowledged = false;
      row.invalidResultConfirmed = false;
    }
    rowsEdited();
  };

  const startEditing = (rowId) =>
    setEditingIds((ids) => new Set([...ids, String(rowId)]));

  // A test is decided on its head row; its parts and duplicates carry the same
  // decision, so a tick they loaded with cannot outvote it on save. One
  // decision replaces another: a result is accepted, retested or ignored.
  const setDecision = (rowId, decision) => {
    const row = findRow(rowId);
    if (!row) return;
    const rows = props.results.resultList || [];
    rows
      .filter((other) => decisionKey(other) === decisionKey(row))
      .forEach((other) => {
        Object.entries(DECISION_FIELDS).forEach(([name, field]) => {
          const value = name === decision;
          if (Boolean(other[field]) !== value) {
            other[field] = value;
            rememberEdit(other.id, field, value);
          }
        });
      });
    rowsEdited();
  };

  const decideSample = (group, decision) =>
    group.reviewRows
      .filter((row) => decisionHeadIds.has(row.id))
      .forEach((row) => setDecision(row.id, decision));

  // The run produced no result: the reviewer records the failure on the order's
  // waiting test, which stays open for the repeat.
  const dismissFailedRun = (row) => {
    setDismissingRunIds((ids) => [...ids, row.id]);
    postToOpenElisServerFullResponse(
      `/rest/analyzer/results/${encodeURIComponent(row.id)}/failed-run`,
      JSON.stringify({}),
      // The response is undefined when the request never reached the server.
      async (response) => {
        setDismissingRunIds((ids) => ids.filter((id) => id !== row.id));
        const succeeded = response?.status == 200;
        let message = intl.formatMessage({
          id: succeeded
            ? "analyzer.results.failedRun.dismissed"
            : "analyzer.results.failedRun.error",
        });
        if (!succeeded) {
          const body = await response?.json?.().catch(() => null);
          if (body?.error) {
            message = message + ": " + body.error;
          }
        }
        addNotification({
          kind: succeeded ? NotificationKinds.success : NotificationKinds.error,
          title: intl.formatMessage({ id: "notification.title" }),
          message,
        });
        setNotificationVisible(true);
        if (succeeded) {
          props.refreshResults?.(
            Number(props.results?.paging?.currentPage) || 1,
          );
        }
      },
    );
  };

  const heldReasonLabel = (reason) =>
    HOLD_REASONS.includes(reason)
      ? intl.formatMessage({ id: `analyzer.results.reason.${reason}` })
      : intl.formatMessage({ id: "analyzer.results.reason.other" }, { reason });

  /** What a held row says about itself beyond its reason. */
  const renderHeldExplanation = (row) => (
    <>
      {row.instrumentNote && (
        <div data-testid={`instrument-note-${row.id}`}>
          <FormattedMessage
            id="analyzer.results.held.instrumentNote"
            values={{ note: row.instrumentNote }}
          />
        </div>
      )}
      {row.importIssueReason === "assay_not_enabled" && (
        <div>
          <FormattedMessage id="analyzer.results.held.assayNotEnabled" />
        </div>
      )}
      {row.importIssueReason === "arrived_under_other_revision" && (
        <div>
          <FormattedMessage
            id="analyzer.results.held.otherRevision"
            values={{ revision: row.sourceProfileRevision }}
          />
        </div>
      )}
      {row.importIssueReason === "qc_target_missing" && (
        <div>
          <FormattedMessage id="analyzer.results.held.qcTargetMissing" />
        </div>
      )}
    </>
  );

  /** The one thing the reviewer can do about a held row, if anything. */
  const renderHeldAction = (row) => {
    const resolutionUrl = buildHeldResultResolutionUrl(row, props.analyzerId);
    if (resolutionUrl) {
      return (
        <CarbonLink
          as={RouterLink}
          to={resolutionUrl}
          onClick={(event) => openMappingWithDraft(event, resolutionUrl)}
        >
          <FormattedMessage id="analyzer.results.held.reviewMapping" />
        </CarbonLink>
      );
    }
    if (row.importIssueReason === "run_failed" && !row.isControl) {
      return (
        <Button
          kind="tertiary"
          size="sm"
          disabled={dismissingRunIds.includes(row.id)}
          onClick={() => dismissFailedRun(row)}
        >
          <FormattedMessage id="analyzer.results.failedRun.dismiss" />
        </Button>
      );
    }
    if (row.importIssueReason === "assay_not_enabled" && props.analyzerId) {
      return (
        <CarbonLink
          as={RouterLink}
          to={`/analyzers?setup=assays&analyzerId=${encodeURIComponent(props.analyzerId)}`}
        >
          <FormattedMessage id="analyzer.results.held.enableAssay" />
        </CarbonLink>
      );
    }
    if (row.importIssueReason === "qc_target_missing" && row.testId) {
      return (
        <CarbonLink
          as={RouterLink}
          to={`/MasterListsPage/TestCatalogEditor/${encodeURIComponent(row.testId)}/qc-targets`}
        >
          <FormattedMessage id="analyzer.results.held.setQcTarget" />
        </CarbonLink>
      );
    }
    return null;
  };

  /** A held result in a cell of its own: beneath a test, or among the controls. */
  const renderHeldResult = (row) => (
    <div
      className="analyzerResults__heldInline"
      data-testid={`held-analyzer-result-${row.id}`}
    >
      <Tag type="warm-gray" size="sm">
        <FormattedMessage id="analyzer.results.held.tag" />
      </Tag>
      <div>
        <strong>{row.rawResultValue || row.result}</strong>
      </div>
      <div>
        <FormattedMessage
          id="analyzer.results.held.code"
          values={{ code: row.rawTestCode || row.testName }}
        />
      </div>
      <div>{heldReasonLabel(row.importIssueReason)}</div>
      {renderHeldExplanation(row)}
      {renderHeldAction(row)}
    </div>
  );

  /** The sample's held results, by reason, each with its one next step. */
  const renderHeldRows = (group) => {
    const byReason = new Map();
    group.heldRows.forEach((row) => {
      const reason = row.importIssueReason;
      byReason.set(reason, [...(byReason.get(reason) || []), row]);
    });
    return (
      <div
        className="analyzerResults__held"
        data-testid={`sample-held-${group.key}`}
      >
        <h4 className="analyzerResults__heldTitle">
          <FormattedMessage
            id="analyzer.results.held.sectionTitle"
            values={{ count: group.heldRows.length }}
          />
        </h4>
        {Array.from(byReason.entries()).map(([reason, rows]) => (
          <div className="analyzerResults__heldGroup" key={reason}>
            <Tag type="warm-gray" size="sm">
              {heldReasonLabel(reason)}
            </Tag>
            <Table size="sm" className="analyzerResults__heldTable">
              <TableHead>
                <TableRow>
                  <TableHeader>
                    <FormattedMessage id="analyzer.results.column.code" />
                  </TableHeader>
                  <TableHeader>
                    <FormattedMessage id="analyzer.results.column.reported" />
                  </TableHeader>
                  <TableHeader>
                    <FormattedMessage id="analyzer.results.column.nextStep" />
                  </TableHeader>
                </TableRow>
              </TableHead>
              <TableBody>
                {rows.map((row) => (
                  <TableRow
                    key={row.id}
                    data-testid={`held-analyzer-result-${row.id}`}
                  >
                    <TableCell>{row.rawTestCode || row.testName}</TableCell>
                    <TableCell>{row.rawResultValue || row.result}</TableCell>
                    <TableCell>
                      <div className="analyzerResults__nextStep">
                        {renderHeldExplanation(row)}
                        {renderHeldAction(row)}
                      </div>
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </div>
        ))}
      </div>
    );
  };

  const renderResultValue = (row) => {
    switch (row.testResultType) {
      case "M":
      case "C":
      case "D":
        return (
          <>
            {
              row.dictionaryResultList.find((result) => result.id == row.result)
                ?.displayValue
            }
          </>
        );
      default:
        if (row.readOnly) {
          return row.result;
        }
        if (editingIds.has(String(row.id))) {
          return (
            <TextInput
              id={"resultList" + row.id + ".result"}
              size="sm"
              type="text"
              value={row.result ?? ""}
              labelText={intl.formatMessage({
                id: "analyzer.results.column.result",
              })}
              hideLabel
              autoFocus
              onChange={(e) =>
                handleFieldChange(row.id, "result", e.target.value)
              }
            />
          );
        }
        return (
          <span className="analyzerResults__value">
            <span>{row.result}</span>
            <Button
              kind="ghost"
              size="sm"
              hasIconOnly
              renderIcon={Edit}
              iconDescription={intl.formatMessage(
                { id: "analyzer.results.edit" },
                { test: row.testName },
              )}
              onClick={() => startEditing(row.id)}
            />
          </span>
        );
    }
  };

  const copyLabNumber = async (accessionNumber) => {
    if ("clipboard" in navigator) {
      return await navigator.clipboard.writeText(accessionNumber);
    }
    return document.execCommand("copy", true, accessionNumber);
  };

  /** The sample's own header: lab number, what the instrument said about it, where its results go, and its actions. */
  const renderSampleHeader = (group) => {
    const row = group.representative;
    const decidable = group.reviewRows.filter((r) => decisionHeadIds.has(r.id));
    const awaitingSpecimen = group.reviewRows.some(
      (r) => r.sampleTypeOptions && r.sampleTypeOptions.length > 0,
    );
    return (
      <header className="analyzerResults__sampleHeader">
        <div className="analyzerResults__sampleMain">
          <div className="analyzerResults__sampleId">
            <span className="analyzerResults__labNumber" data-testid="LabNo">
              {formatLabNum(row.accessionNumber)}
            </span>
            <Button
              onClick={() => copyLabNumber(row.accessionNumber)}
              kind="ghost"
              size="sm"
              iconDescription={intl.formatMessage({
                id: "instructions.copy.labnum",
              })}
              hasIconOnly
              renderIcon={Copy}
            />
            {row.nonconforming && (
              <Tag type="red" size="sm" renderIcon={WarningAltFilled}>
                <FormattedMessage id="analyzer.results.nonconforming" />
              </Tag>
            )}
            <span className="analyzerResults__counts">
              {decidable.length > 0 && (
                <FormattedMessage
                  id="analyzer.results.sample.toDecide"
                  values={{ count: decidable.length }}
                />
              )}
              {decidable.length > 0 && group.heldRows.length > 0 && " · "}
              {group.heldRows.length > 0 && (
                <FormattedMessage
                  id="analyzer.results.sample.held"
                  values={{ count: group.heldRows.length }}
                />
              )}
            </span>
          </div>
          {row.instrumentSpecimenId &&
            row.instrumentSpecimenId !== row.accessionNumber && (
              <div
                className="analyzerResults__secondary"
                data-testid="InstrumentSpecimenId"
              >
                <FormattedMessage
                  id="analyzer.placement.instrumentSpecimen"
                  values={{ id: row.instrumentSpecimenId }}
                />
              </div>
            )}
          {row.instrumentSampleFlags && (
            <div
              className="analyzerResults__secondary"
              data-testid="InstrumentSampleFlags"
            >
              <FormattedMessage
                id="analyzer.results.sampleFlags"
                values={{ flags: row.instrumentSampleFlags }}
              />
            </div>
          )}
          <PlacementNotice
            row={row}
            showChooser={false}
            showState={Boolean(group.uniformState)}
            showBundle={false}
          />
          {awaitingSpecimen && (
            <div className="analyzerResults__secondary">
              <FormattedMessage id="notice.testCatalog.intake.awaitingSpecimen" />
            </div>
          )}
        </div>
        <div className="analyzerResults__sampleActions">
          {row.placement && (
            <RedirectControl
              row={row}
              onChange={(field, value, rowId) =>
                handleFieldChange(rowId, field, value)
              }
            />
          )}
          {row.deliveryReceiptId && (
            <Button
              kind="ghost"
              size="sm"
              onClick={() => setBundleReceiptId(row.deliveryReceiptId)}
            >
              <FormattedMessage id="analyzer.placement.viewBundle" />
            </Button>
          )}
          {decidable.length > 0 && sampleIsMatched(group) && (
            <Button
              kind="tertiary"
              size="sm"
              onClick={() => decideSample(group, "accept")}
            >
              <FormattedMessage id="analyzer.results.sample.acceptAll" />
            </Button>
          )}
          {decidable.length > 0 && (
            <OverflowMenu
              size="sm"
              flipped
              aria-label={intl.formatMessage({
                id: "analyzer.results.sample.moreDecisions",
              })}
              iconDescription={intl.formatMessage({
                id: "analyzer.results.sample.moreDecisions",
              })}
            >
              <OverflowMenuItem
                itemText={intl.formatMessage({
                  id: "analyzer.results.sample.retestAll",
                })}
                onClick={() => decideSample(group, "retest")}
              />
              <OverflowMenuItem
                itemText={intl.formatMessage({
                  id: "analyzer.results.sample.ignoreAll",
                })}
                onClick={() => decideSample(group, "ignore")}
              />
              <OverflowMenuItem
                itemText={intl.formatMessage({
                  id: "analyzer.results.sample.clearAll",
                })}
                onClick={() => decideSample(group, "")}
              />
            </OverflowMenu>
          )}
        </div>
      </header>
    );
  };

  const renderTest = (row, group) => {
    const parts = partsByHeadId.get(row.id) || [];
    const secondary = [
      row.rawTestCode && row.rawTestCode !== row.testName
        ? row.rawTestCode
        : null,
      row.units,
    ].filter(Boolean);
    return (
      <div className="analyzerResults__test" data-testid="sampleInfo">
        <span>{row.testName}</span>
        {secondary.length > 0 && (
          <span className="analyzerResults__secondary">
            {secondary.join(" · ")}
          </span>
        )}
        {(row.importIssueReason ||
          (!group.uniformState && row.placement) ||
          parts.length > 0) && (
          <span className="analyzerResults__tags">
            {row.importIssueReason && (
              <Tag type="warm-gray" size="sm">
                <FormattedMessage id="analyzer.results.held.tag" />
              </Tag>
            )}
            {!group.uniformState && row.placement && (
              <PlacementStateTag placement={row.placement} />
            )}
            {parts.length > 0 && (
              <span className="analyzerResults__secondary">
                <FormattedMessage
                  id="analyzer.results.parts.count"
                  values={{ count: parts.length }}
                />
              </span>
            )}
          </span>
        )}
        {row.placement?.state === "MULTI_TUBE" && (
          <AnalysisChooser
            row={row}
            onChooseAnalysis={(analysisId, rowId) =>
              handleFieldChange(rowId, "chosenAnalysisId", analysisId)
            }
          />
        )}
        {/* OGC-1145 FR-8 — specimen-ambiguous row: the reviewer picks the
            sample type; accepting without a choice keeps the row staged
            (awaiting specimen) instead of guessing. */}
        {row.sampleTypeOptions && row.sampleTypeOptions.length > 0 && (
          <Select
            id={"resultList" + row.id + ".typeOfSampleId"}
            size="sm"
            labelText={intl.formatMessage({
              id: "label.testCatalog.specimenType",
            })}
            aria-label={intl.formatMessage({
              id: "label.testCatalog.specimenType",
            })}
            hideLabel
            defaultValue={row.typeOfSampleId || ""}
            onChange={(e) =>
              handleFieldChange(row.id, "typeOfSampleId", e.target.value)
            }
          >
            <SelectItem
              value=""
              text={intl.formatMessage({
                id: "label.testCatalog.specimenType",
              })}
            />
            {row.sampleTypeOptions.map((option) => (
              <SelectItem
                key={option.id}
                value={option.id}
                text={option.value}
              />
            ))}
          </Select>
        )}
      </div>
    );
  };

  const renderFlag = (row) => {
    const flag = (row.instrumentFlags || "").trim();
    if (NORMAL_FLAGS.includes(flag)) {
      return <span className="analyzerResults__secondary">{flag}</span>;
    }
    return (
      <Tag type="magenta" size="sm">
        {flag}
      </Tag>
    );
  };

  const renderDecision = (row) => {
    if (!decisionHeadIds.has(row.id)) {
      return null;
    }
    return (
      <RadioButtonGroup
        name={"resultList" + row.id + ".decision"}
        orientation="horizontal"
        aria-label={intl.formatMessage(
          { id: "analyzer.results.decision.legend" },
          { test: row.testName },
        )}
        valueSelected={decisionOf(row)}
        onChange={(decision) => setDecision(row.id, decision)}
      >
        <RadioButton
          id={"resultList" + row.id + ".isAccepted"}
          value="accept"
          labelText={intl.formatMessage({
            id: "analyzer.results.decision.accept",
          })}
        />
        <RadioButton
          id={"resultList" + row.id + ".isRejected"}
          value="retest"
          labelText={intl.formatMessage({
            id: "analyzer.results.decision.retest",
          })}
        />
        <RadioButton
          id={"resultList" + row.id + ".isDeleted"}
          value="ignore"
          labelText={intl.formatMessage({
            id: "analyzer.results.decision.ignore",
          })}
        />
      </RadioButtonGroup>
    );
  };

  const renderDetails = (row, group) => (
    <div className="analyzerResults__details">
      {!group.uniformState && row.placement && (
        <PlacementNotice
          row={row}
          showChooser={false}
          showPatient={false}
          showBundle={false}
        />
      )}
      <InstrumentReported row={row} />
      <ResultParts
        headId={row.id}
        parts={partsByHeadId.get(row.id) || []}
        renderHeld={renderHeldResult}
      />
      <TextArea
        id={"resultList" + row.id + ".note"}
        className="analyzerResults__note"
        labelText={intl.formatMessage({ id: "analyzer.results.note.label" })}
        rows={2}
        defaultValue={row.note || ""}
        onChange={(e) => handleFieldChange(row.id, "note", e.target.value)}
      />
    </div>
  );

  // Carbon's table keeps its expanded rows by id across the rows it is
  // handed on every render.
  const reviewHeaders = useMemo(
    () =>
      ["test", "result", "flag", "runDate", "decision"].map((key) => ({
        key,
        header: intl.formatMessage({ id: `analyzer.results.column.${key}` }),
      })),
    [intl],
  );

  const renderSampleTable = (group) => {
    const byId = new Map(group.reviewRows.map((row) => [String(row.id), row]));
    return (
      <DataTable
        rows={group.reviewRows.map((row) => ({ id: String(row.id) }))}
        headers={reviewHeaders}
      >
        {({
          rows,
          headers,
          getHeaderProps,
          getRowProps,
          getExpandedRowProps,
          getExpandHeaderProps,
          getTableProps,
          getTableContainerProps,
        }) => (
          <TableContainer {...getTableContainerProps()}>
            <Table
              {...getTableProps()}
              size="md"
              data-testid="analyzer-results-table"
            >
              <TableHead>
                <TableRow>
                  <TableExpandHeader enableToggle {...getExpandHeaderProps()} />
                  {headers.map((header) => (
                    <TableHeader
                      key={header.key}
                      {...getHeaderProps({ header, isSortable: false })}
                      className={`analyzerResults__col--${header.key}`}
                    >
                      {header.header}
                    </TableHeader>
                  ))}
                </TableRow>
              </TableHead>
              <TableBody>
                {rows.map((row) => {
                  const data = byId.get(row.id);
                  if (!data) return null;
                  return (
                    <React.Fragment key={row.id}>
                      <TableExpandRow
                        {...getRowProps({ row })}
                        data-testid={`result-row-${data.id}`}
                      >
                        <TableCell>{renderTest(data, group)}</TableCell>
                        <TableCell>
                          {renderResultValue(data)}
                          {data.note && (
                            <div>
                              <Tag type="outline" size="sm">
                                <FormattedMessage id="analyzer.results.note.tag" />
                              </Tag>
                            </div>
                          )}
                        </TableCell>
                        <TableCell>{renderFlag(data)}</TableCell>
                        <TableCell>{data.completeDate}</TableCell>
                        <TableCell>{renderDecision(data)}</TableCell>
                      </TableExpandRow>
                      {row.isExpanded && (
                        <TableExpandedRow
                          colSpan={headers.length + 1}
                          {...getExpandedRowProps({ row })}
                        >
                          {renderDetails(data, group)}
                        </TableExpandedRow>
                      )}
                    </React.Fragment>
                  );
                })}
              </TableBody>
            </Table>
          </TableContainer>
        )}
      </DataTable>
    );
  };

  const qcHeaders = [
    {
      key: "accessionNumber",
      header: intl.formatMessage({ id: "column.name.sampleInfo" }),
    },
    {
      key: "testName",
      header: intl.formatMessage({ id: "column.name.testName" }),
    },
    { key: "result", header: intl.formatMessage({ id: "column.name.result" }) },
  ];

  const decidedCount = decisionRows.filter((row) => decisionOf(row)).length;

  return (
    <div className="analyzerResults">
      {patientResults.length === 0 && qcResults.length === 0 && (
        <div
          className="analyzerResults__empty"
          data-testid="analyzer-results-empty"
        >
          <h4>
            <FormattedMessage id="analyzer.results.empty.title" />
          </h4>
          <p>
            <FormattedMessage id="analyzer.results.empty.subtitle" />
          </p>
        </div>
      )}
      {heldPatientResults.length > 0 && (
        <InlineNotification
          kind="warning"
          title={intl.formatMessage(
            { id: "analyzer.results.held.title" },
            { count: heldPatientResults.length },
          )}
          subtitle={intl.formatMessage({
            id: "analyzer.results.held.subtitle",
          })}
          lowContrast
          hideCloseButton
        />
      )}
      {hasQcFailures && (
        <InlineNotification
          kind="warning"
          title={intl.formatMessage({
            id: "analyzer.qc.batch.failure.title",
          })}
          subtitle={intl.formatMessage({
            id: "analyzer.qc.batch.failure.subtitle",
          })}
          lowContrast
          hideCloseButton
        />
      )}
      {tableRows.length > 0 && (
        <div className="analyzerResults__summary">
          <span data-testid="analyzer-results-summary">
            <FormattedMessage
              id="analyzer.results.summary.samples"
              values={{ count: sampleGroups.length }}
            />
            {" · "}
            <FormattedMessage
              id="analyzer.results.summary.toDecide"
              values={{ count: decisionRows.length }}
            />
            {" · "}
            <FormattedMessage
              id="analyzer.results.summary.held"
              values={{ count: heldPatientResults.length }}
            />
          </span>
          {arrows.show && <ServerPageArrows {...arrows} />}
        </div>
      )}
      {sampleGroups.map((group) => (
        <section
          key={group.key}
          className="analyzerResults__sample"
          data-testid={`sample-group-${group.key}`}
          aria-label={formatLabNum(group.representative.accessionNumber)}
        >
          {renderSampleHeader(group)}
          {group.reviewRows.length > 0 && renderSampleTable(group)}
          {group.heldRows.length > 0 && renderHeldRows(group)}
        </section>
      ))}
      {tableRows.length > 0 && hasServerPages(props.results?.paging) && (
        <Pagination
          {...serverPaginationProps({
            paging: props.results?.paging,
            rowsOnPage: tableRows.length,
            pageSize: props.serverPageSize,
            onPageRequest: (pageNumber) => props.loadPage?.(pageNumber),
            intl,
          })}
          pageSizeInputDisabled
        />
      )}
      {qcResults.length > 0 && (
        <Accordion className="analyzerResults__qc">
          <AccordionItem
            title={intl.formatMessage(
              { id: "analyzer.qc.controls.section.title" },
              { count: qcResults.length },
            )}
          >
            <Table size="md">
              <TableHead>
                <TableRow>
                  {qcHeaders.map((header) => (
                    <TableHeader key={header.key}>{header.header}</TableHeader>
                  ))}
                </TableRow>
              </TableHead>
              <TableBody>
                {qcResults.map((row) => (
                  <TableRow key={row.id}>
                    <TableCell>{row.accessionNumber}</TableCell>
                    <TableCell>{row.testName}</TableCell>
                    <TableCell>
                      {row.importIssueReason
                        ? renderHeldResult(row)
                        : row.result}
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </AccordionItem>
        </Accordion>
      )}
      {decisionRows.length > 0 && (
        <div className="analyzerResults__footer">
          <Button
            type="button"
            onClick={handleSave}
            id="submit"
            data-testid="Save-btn"
            disabled={isSubmitting}
          >
            <FormattedMessage id="label.button.save" />
          </Button>
          <span className="analyzerResults__decided">
            <FormattedMessage
              id="analyzer.results.decided"
              values={{ decided: decidedCount, total: decisionRows.length }}
            />
          </span>
          {isSubmitting && (
            <span data-testid="analyzer-results-save-in-progress" />
          )}
        </div>
      )}
      <ResultAlertModal
        open={Boolean(resultAlert)}
        alerts={resultAlert?.alerts || []}
        mode="save"
        customCriticalMessage={resultAlert?.customCriticalMessage}
        onConfirm={confirmResultAlert}
        onCorrect={() => setResultAlert(null)}
      />
      <DeliveryBundleModal
        receiptId={bundleReceiptId}
        onClose={() => setBundleReceiptId(null)}
      />
    </div>
  );
};

export default AnalyserResults;
