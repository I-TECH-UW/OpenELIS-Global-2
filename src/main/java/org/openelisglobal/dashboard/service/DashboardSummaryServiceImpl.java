package org.openelisglobal.dashboard.service;

import java.time.Instant;
import org.openelisglobal.dashboard.controller.rest.dto.DashboardSummaryDTO;
import org.openelisglobal.dashboard.controller.rest.dto.DashboardSummaryDTO.AnalyzersKpi;
import org.openelisglobal.dashboard.controller.rest.dto.DashboardSummaryDTO.Kpi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * OGC-485 Slice 1: structural implementation. Returns a well-formed KPI payload
 * with placeholder values. Real aggregation queries (sample, analysis,
 * qc_result, analyzer_connection, stock_level) land in follow-up slices; the UI
 * and permission wiring are proven end-to-end here.
 *
 * <p>
 * TAT compliance stays null while prerequisite A (tatTargetHours on the test
 * entity) is outstanding; the UI shows a "No target set" Tag for null values.
 */
@Service
@Transactional(readOnly = true)
public class DashboardSummaryServiceImpl implements DashboardSummaryService {

    @Override
    public DashboardSummaryDTO getSummary(String sectionId, String dateRange) {
        DashboardSummaryDTO dto = new DashboardSummaryDTO();
        dto.setGeneratedAt(Instant.now());
        dto.setTestsToday(new Kpi(0.0, null));
        dto.setPending(new Kpi(0.0, null));
        dto.setTatCompliance(null);
        dto.setQcPassRate(new Kpi(null, null));
        dto.setAnalyzersOnline(new AnalyzersKpi(0, 0));
        dto.setStockAlerts(new Kpi(0.0, null));
        return dto;
    }
}
