package org.openelisglobal.common.rest.provider;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openelisglobal.analysis.service.AnalysisService;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.rest.provider.bean.homedashboard.DashBoardTile;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.StatusService.AnalysisStatus;
import org.openelisglobal.common.services.StatusService.ExternalOrderStatus;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.service.order.ElectronicOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rest/")
public class patientDashBoardProvider {
    
    @Autowired
    AnalysisService analysisService;
    
    @Autowired
    IStatusService iStatusService;
    
    @Autowired
    ElectronicOrderService electronicOrderService;
    
    @GetMapping(value = "dashboard-tiles", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<DashBoardTile> getDasBoardTiles() {
        
        List<DashBoardTile> dashBoardTiles = new ArrayList<>();
        DashBoardTile.TileType.stream().forEach(type -> {
            switch (type) {
                case ORDERS_IN_PROGRESS:
                    // in Progress
                    dashBoardTiles.add(new DashBoardTile("In Progress", "Awaiting Result Entry", analysisService
                            .getAnalysesForStatusId(iStatusService.getStatusID(AnalysisStatus.NotStarted)).size()));
                    break;
                case ORDERS_READY_FOR_VALIDATION:
                    //Ready for validation
                    dashBoardTiles.add(new DashBoardTile("Ready For Validation", "Awaiting Review", analysisService
                            .getAnalysesForStatusId(iStatusService.getStatusID(AnalysisStatus.TechnicalAcceptance)).size()));
                    break;
                case ORDERS_COMPLETED_TODAY:
                    //Completed Today
                    dashBoardTiles.add(new DashBoardTile("Orders Completed Today", "Total Orders Completed Today",
                            analysisService
                                    .getAnalysisStartedOnRangeByStatusId(DateUtil.getNowAsSqlDate(),
                                        DateUtil.getNowAsSqlDate(), iStatusService.getStatusID(AnalysisStatus.Finalized))
                                    .size()));
                    break;
                case ORDERS_PATIALLY_COMPLETED_TODAY:
                    // Partially Completed Today
                    dashBoardTiles.add(new DashBoardTile("Patiallly Completed Today", "Total Orders Completed Today",
                            analysisService
                                    .getAnalysisStartedOnRangeByStatusId(DateUtil.getNowAsSqlDate(),
                                        DateUtil.getNowAsSqlDate(), iStatusService.getStatusID(AnalysisStatus.Finalized))
                                    .size()));
                    break;
                case ORDERS_ENTERED_BY_USER_TODAY:
                    //orders entered By user Today
                    Set<Integer> statusIds = new HashSet<>();
                    statusIds.add(Integer.parseInt(iStatusService.getStatusID(AnalysisStatus.SampleRejected)));
                    dashBoardTiles.add(new DashBoardTile("Orders Entered By User", "Entered by user Today", analysisService
                            .getAnalysisStartedOnExcludedByStatusId(DateUtil.getNowAsSqlDate(), statusIds).size()));
                    break;
                case ORDERS_REJECTED_TODAY:
                    //Orders Rejected by Lab Today
                    dashBoardTiles.add(new DashBoardTile("Orders Rejected", "Orders Rejected By Lab Today",
                            analysisService.getAnalysisStartedOnRangeByStatusId(DateUtil.getNowAsSqlDate(),
                                DateUtil.getNowAsSqlDate(), iStatusService.getStatusID(AnalysisStatus.SampleRejected))
                                    .size()));
                    break;
                case UN_PRINTED_RESULTS:
                    //Un printed 
                    dashBoardTiles
                            .add(new DashBoardTile("Un Printed Results", "Un Printed Results", unprintedResults().size()));
                    break;
                case INCOMING_ORDERS:
                    //Incoming order
                    dashBoardTiles.add(new DashBoardTile("Incoming Orders", "Electronic Orders'",
                            electronicOrderService.getAllElectronicOrdersByDateAndStatus(null, null,
                                iStatusService.getStatusID(ExternalOrderStatus.Entered),
                                ElectronicOrder.SortOrder.EXTERNAL_ID).size()));
                    break;
                case AVERAGE_TURN_AROUND_TIME:
                    dashBoardTiles.add(new DashBoardTile("Average Turn Around time", "Reception to Validation",
                            String.valueOf(calculateAverageTime())));
                    break;
                case DELAYED_TURN_AROUND:
                    dashBoardTiles.add(
                        new DashBoardTile("Delayed Turn Around", "More Than 96 hours", analysesWithDelayedTurnAroundTime()));
                    break;
            }
        });
        
        return dashBoardTiles;
    }
    
    private double calculateAverageTime() {
        List<Analysis> analyses = analysisService.getAnalysisStartedOnRangeByStatusId(DateUtil.getNowAsSqlDate(),
            DateUtil.getNowAsSqlDate(), iStatusService.getStatusID(AnalysisStatus.Finalized));
        
        List<Long> hours = new ArrayList<>();
        analyses.forEach(analysis -> {
            // Convert java.sql.Date to java.time.LocalDate
            LocalDate localStartDate = analysis.getStartedDate().toLocalDate();
            LocalDate localEndDate = analysis.getReleasedDate().toLocalDate();
            // Calculate time difference in hours
            Long hoursDiff = Duration.between(localStartDate.atStartOfDay(), localEndDate.atStartOfDay()).toHours();
            hours.add(hoursDiff);
        });
        
        long sum = 0;
        if (!hours.isEmpty()) {
            for (Long h : hours) {
                sum += h;
            }
            
        }
        return (double) sum / hours.size();
    }
    
    private int analysesWithDelayedTurnAroundTime() {
        List<Analysis> analyses = analysisService.getAnalysisStartedOnRangeByStatusId(DateUtil.getNowAsSqlDate(),
            DateUtil.getNowAsSqlDate(), iStatusService.getStatusID(AnalysisStatus.Finalized));
        
        List<Analysis> delayedAnalyses = new ArrayList<>();
        analyses.forEach(analysis -> {
            // Convert java.sql.Date to java.time.LocalDate
            LocalDate localStartDate = analysis.getStartedDate().toLocalDate();
            LocalDate localEndDate = analysis.getReleasedDate().toLocalDate();
            // Calculate time difference in hours
            Long hoursDiff = Duration.between(localStartDate.atStartOfDay(), localEndDate.atStartOfDay()).toHours();
            if (hoursDiff > 96) {
                delayedAnalyses.add(analysis);
            }
            
        });
        return delayedAnalyses.size();
    }
    
    private List<Analysis> unprintedResults() {
        List<Analysis> analyses = analysisService
                .getAnalysesForStatusId(iStatusService.getStatusID(AnalysisStatus.Finalized));
        List<Analysis> unprintedAnalyses = new ArrayList<>();
        analyses.forEach(a -> {
            if (a.getPrintedDate() == null) {
                unprintedAnalyses.add(a);
            }
        });
        return unprintedAnalyses;
    }
    
}
