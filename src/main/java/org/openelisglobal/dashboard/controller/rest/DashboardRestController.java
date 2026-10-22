package org.openelisglobal.dashboard.controller.rest;

import org.openelisglobal.dashboard.controller.rest.dto.DashboardSummaryDTO;
import org.openelisglobal.dashboard.service.DashboardSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/dashboard")
@PreAuthorize("hasAnyRole('ADMIN', 'GLOBAL_ADMIN', 'REPORTS')")
public class DashboardRestController {

    @Autowired
    private DashboardSummaryService summaryService;

    @GetMapping("/summary")
    public ResponseEntity<DashboardSummaryDTO> getSummary(@RequestParam(required = false) String section,
            @RequestParam(required = false, defaultValue = "today") String dateRange) {
        return ResponseEntity.ok(summaryService.getSummary(section, dateRange));
    }
}
