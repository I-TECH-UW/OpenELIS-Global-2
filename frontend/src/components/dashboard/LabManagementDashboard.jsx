import React, {
  useCallback,
  useEffect,
  useMemo,
  useRef,
  useState,
} from "react";
import {
  Button,
  Column,
  Grid,
  Loading,
  NumberInput,
  Section,
  Select,
  SelectItem,
  Tag,
  Tile,
} from "@carbon/react";
import { Renew } from "@carbon/react/icons";
import { FormattedMessage, useIntl } from "react-intl";
import PageBreadCrumb from "../common/PageBreadCrumb";
import { getFromOpenElisServer } from "../utils/Utils";

const BREADCRUMBS = [
  { label: "home.label", link: "/" },
  { label: "nav.dashboard.labManagement" },
];

const DATE_RANGES = [
  { value: "today", labelKey: "label.dashboard.today" },
  { value: "7d", labelKey: "label.dashboard.last7days" },
  { value: "30d", labelKey: "label.dashboard.last30days" },
  { value: "90d", labelKey: "label.dashboard.last90days" },
];

const REFRESH_MIN_MINUTES = 5;
const REFRESH_MAX_MINUTES = 30;
const DEFAULT_REFRESH_MINUTES = 10;

// Thresholds from FR-2-003. A null KPI value always renders as the gray
// "No target set" Tag — it is a signal, not a bad grade.
const tatComplianceTag = (value) => {
  if (value == null)
    return { kind: "gray", labelKey: "label.dashboard.noTarget" };
  if (value >= 95) return { kind: "green", valueText: `${value.toFixed(1)}%` };
  if (value >= 85)
    return { kind: "warm-gray", valueText: `${value.toFixed(1)}%` };
  return { kind: "red", valueText: `${value.toFixed(1)}%` };
};

const qcPassRateTag = (value) => {
  if (value == null) return { kind: "gray", labelKey: "label.dashboard.na" };
  if (value >= 95) return { kind: "green", valueText: `${value.toFixed(1)}%` };
  if (value >= 90)
    return { kind: "warm-gray", valueText: `${value.toFixed(1)}%` };
  return { kind: "red", valueText: `${value.toFixed(1)}%` };
};

const analyzersTag = (online, total) => {
  if (online == null || total == null)
    return { kind: "gray", labelKey: "label.dashboard.na" };
  const offline = total - online;
  if (offline === 0)
    return { kind: "green", valueText: `${online} / ${total}` };
  if (offline === 1)
    return { kind: "warm-gray", valueText: `${online} / ${total}` };
  return { kind: "red", valueText: `${online} / ${total}` };
};

const stockAlertsTag = (count) => {
  if (count == null) return { kind: "gray", labelKey: "label.dashboard.na" };
  if (count === 0) return { kind: "green", valueText: `${count}` };
  if (count <= 2) return { kind: "warm-gray", valueText: `${count}` };
  return { kind: "red", valueText: `${count}` };
};

const formatLastUpdated = (date, intl) => {
  if (!date) return "";
  return intl.formatTime(date, {
    hour: "numeric",
    minute: "2-digit",
    hour12: true,
  });
};

const formatDelta = (deltaPct) => {
  if (deltaPct == null) return null;
  const sign = deltaPct > 0 ? "↑" : deltaPct < 0 ? "↓" : "·";
  return `${sign} ${Math.abs(deltaPct).toFixed(0)}%`;
};

const KpiTile = ({ titleKey, value, deltaPct, tag, intl }) => (
  <Tile
    className="dashboard-kpi-tile"
    data-testid={`dashboard-kpi-${titleKey}`}
  >
    <div className="dashboard-kpi-title">
      <FormattedMessage id={titleKey} />
    </div>
    <div className="dashboard-kpi-value">{value != null ? value : "–"}</div>
    {deltaPct != null && (
      <div className="dashboard-kpi-delta">
        {formatDelta(deltaPct)} <FormattedMessage id="label.dashboard.vs" />
      </div>
    )}
    {tag && (
      <Tag type={tag.kind} data-testid={`dashboard-tag-${titleKey}`}>
        {tag.labelKey
          ? intl.formatMessage({ id: tag.labelKey })
          : tag.valueText}
      </Tag>
    )}
  </Tile>
);

const LabManagementDashboard = () => {
  const intl = useIntl();
  const [section, setSection] = useState("all");
  const [dateRange, setDateRange] = useState("today");
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [lastUpdated, setLastUpdated] = useState(null);
  const [refreshMinutes, setRefreshMinutes] = useState(DEFAULT_REFRESH_MINUTES);
  const [intervalError, setIntervalError] = useState(false);

  const abortRef = useRef(null);
  const timerRef = useRef(null);

  const loadSummary = useCallback(() => {
    if (abortRef.current) abortRef.current.abort();
    const controller = new AbortController();
    abortRef.current = controller;
    setLoading(true);
    const params = new URLSearchParams({ section, dateRange }).toString();
    getFromOpenElisServer(
      `/rest/dashboard/summary?${params}`,
      (resp) => {
        if (controller.signal.aborted) return;
        setData(resp || null);
        setLastUpdated(new Date());
        setLoading(false);
      },
      controller.signal,
    );
  }, [section, dateRange]);

  useEffect(() => {
    loadSummary();
    return () => {
      if (abortRef.current) abortRef.current.abort();
    };
  }, [loadSummary]);

  useEffect(() => {
    if (intervalError) return undefined;
    if (timerRef.current) clearInterval(timerRef.current);
    const ms = refreshMinutes * 60 * 1000;
    timerRef.current = setInterval(loadSummary, ms);
    return () => {
      if (timerRef.current) clearInterval(timerRef.current);
    };
  }, [loadSummary, refreshMinutes, intervalError]);

  const handleRefreshIntervalChange = (event, { value }) => {
    const n = Number(value);
    if (
      !Number.isFinite(n) ||
      n < REFRESH_MIN_MINUTES ||
      n > REFRESH_MAX_MINUTES
    ) {
      setIntervalError(true);
      return;
    }
    setIntervalError(false);
    setRefreshMinutes(n);
  };

  const kpis = useMemo(() => {
    if (!data) return null;
    const tatValue = data.tatCompliance?.value;
    const qcValue = data.qcPassRate?.value;
    const analyzers = data.analyzersOnline || {};
    const stock = data.stockAlerts?.value;
    return {
      testsToday: {
        value: data.testsToday?.value ?? 0,
        deltaPct: data.testsToday?.deltaPct,
      },
      pending: {
        value: data.pending?.value ?? 0,
        deltaPct: data.pending?.deltaPct,
      },
      tat: {
        value: tatValue != null ? `${tatValue.toFixed(1)}%` : null,
        deltaPct: data.tatCompliance?.deltaPct,
        tag: tatComplianceTag(tatValue ?? null),
      },
      qc: {
        value: qcValue != null ? `${qcValue.toFixed(1)}%` : null,
        deltaPct: data.qcPassRate?.deltaPct,
        tag: qcPassRateTag(qcValue ?? null),
      },
      analyzers: {
        value:
          analyzers.online != null && analyzers.total != null
            ? `${analyzers.online} / ${analyzers.total}`
            : null,
        tag: analyzersTag(analyzers.online ?? null, analyzers.total ?? null),
      },
      stock: {
        value: stock ?? 0,
        tag: stockAlertsTag(stock ?? 0),
      },
    };
  }, [data]);

  return (
    <>
      {loading && (
        <Loading
          description={intl.formatMessage({
            id: "message.dashboard.refreshing",
          })}
        />
      )}
      <PageBreadCrumb breadcrumbs={BREADCRUMBS} />
      <Grid fullWidth>
        <Column lg={16} md={8} sm={4}>
          <Section>
            <h2 data-testid="dashboard-title">
              <FormattedMessage id="heading.dashboard.title" />
            </h2>
          </Section>
        </Column>
      </Grid>
      <Grid fullWidth className="dashboard-filters">
        <Column lg={4} md={4} sm={4}>
          <Select
            id="dashboard-section-filter"
            labelText={intl.formatMessage({ id: "label.dashboard.section" })}
            value={section}
            onChange={(e) => setSection(e.target.value)}
          >
            <SelectItem
              value="all"
              text={intl.formatMessage({ id: "label.dashboard.allSections" })}
            />
          </Select>
        </Column>
        <Column lg={4} md={4} sm={4}>
          <Select
            id="dashboard-date-range-filter"
            labelText={intl.formatMessage({ id: "label.dashboard.dateRange" })}
            value={dateRange}
            onChange={(e) => setDateRange(e.target.value)}
          >
            {DATE_RANGES.map((o) => (
              <SelectItem
                key={o.value}
                value={o.value}
                text={intl.formatMessage({ id: o.labelKey })}
              />
            ))}
          </Select>
        </Column>
        <Column lg={4} md={4} sm={4}>
          <NumberInput
            id="dashboard-refresh-interval"
            label={intl.formatMessage({
              id: "label.dashboard.refreshInterval",
            })}
            min={REFRESH_MIN_MINUTES}
            max={REFRESH_MAX_MINUTES}
            step={1}
            value={refreshMinutes}
            invalid={intervalError}
            invalidText={intl.formatMessage({
              id: "error.dashboard.refreshInterval",
            })}
            onChange={handleRefreshIntervalChange}
          />
        </Column>
        <Column lg={4} md={4} sm={4} className="dashboard-last-updated">
          <div data-testid="dashboard-last-updated">
            <FormattedMessage id="label.dashboard.lastUpdated" />
            {": "}
            {formatLastUpdated(lastUpdated, intl)}
          </div>
          <Button
            kind="tertiary"
            renderIcon={Renew}
            onClick={loadSummary}
            disabled={loading}
            data-testid="dashboard-refresh-button"
          >
            <FormattedMessage id="button.dashboard.refresh" />
          </Button>
        </Column>
      </Grid>
      <Grid fullWidth className="dashboard-kpi-row">
        <Column lg={16} md={8} sm={4}>
          <Section>
            <h3>
              <FormattedMessage id="heading.dashboard.kpi" />
            </h3>
          </Section>
        </Column>
        {kpis && (
          <>
            <Column lg={4} md={4} sm={2}>
              <KpiTile
                titleKey="label.dashboard.testsToday"
                value={kpis.testsToday.value}
                deltaPct={kpis.testsToday.deltaPct}
                intl={intl}
              />
            </Column>
            <Column lg={4} md={4} sm={2}>
              <KpiTile
                titleKey="label.dashboard.pending"
                value={kpis.pending.value}
                deltaPct={kpis.pending.deltaPct}
                intl={intl}
              />
            </Column>
            <Column lg={4} md={4} sm={2}>
              <KpiTile
                titleKey="label.dashboard.tatCompliance"
                value={kpis.tat.value}
                deltaPct={kpis.tat.deltaPct}
                tag={kpis.tat.tag}
                intl={intl}
              />
            </Column>
            <Column lg={4} md={4} sm={2}>
              <KpiTile
                titleKey="label.dashboard.qcPassRate"
                value={kpis.qc.value}
                deltaPct={kpis.qc.deltaPct}
                tag={kpis.qc.tag}
                intl={intl}
              />
            </Column>
            <Column lg={4} md={4} sm={2}>
              <KpiTile
                titleKey="label.dashboard.analyzersOnline"
                value={kpis.analyzers.value}
                tag={kpis.analyzers.tag}
                intl={intl}
              />
            </Column>
            <Column lg={4} md={4} sm={2}>
              <KpiTile
                titleKey="label.dashboard.stockAlerts"
                value={kpis.stock.value}
                tag={kpis.stock.tag}
                intl={intl}
              />
            </Column>
          </>
        )}
      </Grid>
    </>
  );
};

export default LabManagementDashboard;
