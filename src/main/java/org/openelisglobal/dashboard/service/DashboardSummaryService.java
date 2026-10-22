package org.openelisglobal.dashboard.service;

import org.openelisglobal.dashboard.controller.rest.dto.DashboardSummaryDTO;

public interface DashboardSummaryService {

    /**
     * Build the KPI summary payload for the Lab Management Dashboard.
     *
     * @param sectionId section filter; {@code null} or {@code "all"} aggregates
     *                  across sections
     * @param dateRange one of {@code today}, {@code 7d}, {@code 30d}, {@code 90d}
     */
    DashboardSummaryDTO getSummary(String sectionId, String dateRange);
}
