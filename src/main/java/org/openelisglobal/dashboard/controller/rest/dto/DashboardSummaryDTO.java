package org.openelisglobal.dashboard.controller.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;

/**
 * Lab Management Dashboard summary payload (OGC-485 Slice 1).
 *
 * <p>
 * Six KPIs rendered as tiles. Prereq-dependent values (TAT compliance, QC pass
 * rate, stock alerts) are nullable; the UI renders a sentinel Tag when they are
 * absent. Deltas are percent change vs the prior equivalent period and may be
 * null when the prior period has no data.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardSummaryDTO {

    private Instant generatedAt;

    private Kpi testsToday;
    private Kpi pending;
    private Kpi tatCompliance;
    private Kpi qcPassRate;
    private AnalyzersKpi analyzersOnline;
    private Kpi stockAlerts;

    public static class Kpi {
        private Double value;
        private Double deltaPct;

        public Kpi() {
        }

        public Kpi(Double value, Double deltaPct) {
            this.value = value;
            this.deltaPct = deltaPct;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        public Double getDeltaPct() {
            return deltaPct;
        }

        public void setDeltaPct(Double deltaPct) {
            this.deltaPct = deltaPct;
        }
    }

    public static class AnalyzersKpi {
        private Integer online;
        private Integer total;

        public AnalyzersKpi() {
        }

        public AnalyzersKpi(Integer online, Integer total) {
            this.online = online;
            this.total = total;
        }

        public Integer getOnline() {
            return online;
        }

        public void setOnline(Integer online) {
            this.online = online;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }
    }

    public Instant getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(Instant generatedAt) {
        this.generatedAt = generatedAt;
    }

    public Kpi getTestsToday() {
        return testsToday;
    }

    public void setTestsToday(Kpi testsToday) {
        this.testsToday = testsToday;
    }

    public Kpi getPending() {
        return pending;
    }

    public void setPending(Kpi pending) {
        this.pending = pending;
    }

    public Kpi getTatCompliance() {
        return tatCompliance;
    }

    public void setTatCompliance(Kpi tatCompliance) {
        this.tatCompliance = tatCompliance;
    }

    public Kpi getQcPassRate() {
        return qcPassRate;
    }

    public void setQcPassRate(Kpi qcPassRate) {
        this.qcPassRate = qcPassRate;
    }

    public AnalyzersKpi getAnalyzersOnline() {
        return analyzersOnline;
    }

    public void setAnalyzersOnline(AnalyzersKpi analyzersOnline) {
        this.analyzersOnline = analyzersOnline;
    }

    public Kpi getStockAlerts() {
        return stockAlerts;
    }

    public void setStockAlerts(Kpi stockAlerts) {
        this.stockAlerts = stockAlerts;
    }
}
