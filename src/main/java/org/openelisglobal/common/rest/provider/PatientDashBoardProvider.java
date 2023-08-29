package org.openelisglobal.common.rest.provider;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openelisglobal.analysis.service.AnalysisService;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.rest.provider.bean.homedashboard.DashBoardCount;
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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rest/")
public class PatientDashBoardProvider {
    
    @Autowired
    AnalysisService analysisService;
    
    @Autowired
    IStatusService iStatusService;
    
    @Autowired
    ElectronicOrderService electronicOrderService;
    
    @GetMapping(value = "home-dashboard/counts", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DashBoardCount getDasBoardTiles() {
        
        DashBoardCount count = new DashBoardCount();
        DashBoardTile.TileType.stream().forEach(type -> {
            switch (type) {
                case ORDERS_IN_PROGRESS:
                    count.setOrdersInProgress(analysisService
                            .getAnalysesForStatusId(iStatusService.getStatusID(AnalysisStatus.NotStarted)).size());
                    break;
                case ORDERS_READY_FOR_VALIDATION:
                    count.setOrdersReadyForValidation(analysisService
                            .getAnalysesForStatusId(iStatusService.getStatusID(AnalysisStatus.TechnicalAcceptance)).size());
                    break;
                case ORDERS_COMPLETED_TODAY:
                    count.setOrdersCompletedToday(analysisService.getAnalysisCompletedOnByStatusId(
                        DateUtil.getNowAsSqlDate(), iStatusService.getStatusID(AnalysisStatus.Finalized)).size());
                    break;
                case ORDERS_PATIALLY_COMPLETED_TODAY:
                    Set<Integer> statusIds2 = new HashSet<>();
                    statusIds2.add(Integer.parseInt(iStatusService.getStatusID(AnalysisStatus.SampleRejected)));
                    statusIds2.add(Integer.parseInt(iStatusService.getStatusID(AnalysisStatus.Finalized)));
                    count.setPatiallyCompletedToday(analysisService
                            .getAnalysisStartedOnExcludedByStatusId(DateUtil.getNowAsSqlDate(), statusIds2).size());
                    break;
                
                case ORDERS_ENTERED_BY_USER_TODAY:
                    Set<Integer> statusIds = new HashSet<>();
                    statusIds.add(Integer.parseInt(iStatusService.getStatusID(AnalysisStatus.SampleRejected)));
                    count.setOrderEnterdByUserToday(analysisService
                            .getAnalysisStartedOnExcludedByStatusId(DateUtil.getNowAsSqlDate(), statusIds).size());
                    break;
                case ORDERS_REJECTED_TODAY:
                    count.setOrdersRejectedToday(
                        analysisService.getAnalysisStartedOnRangeByStatusId(DateUtil.getNowAsSqlDate(),
                            DateUtil.getNowAsSqlDate(), iStatusService.getStatusID(AnalysisStatus.SampleRejected)).size());
                    break;
                case UN_PRINTED_RESULTS:
                    count.setUnPritendResults(unprintedResults().size());
                    break;
                case INCOMING_ORDERS:
                    count.setIncomigOrders(electronicOrderService.getAllElectronicOrdersByDateAndStatus(null, null,
                        iStatusService.getStatusID(ExternalOrderStatus.Entered), ElectronicOrder.SortOrder.EXTERNAL_ID)
                            .size());
                    break;
                case AVERAGE_TURN_AROUND_TIME:
                    count.setAverageTurnAroudTime(calculateAverageTime());
                    break;
                case DELAYED_TURN_AROUND:
                    
                    count.setDelayedTurnAround(analysesWithDelayedTurnAroundTime().size());
                    break;
            }
        });
        
        return count;
    }
    
    private double calculateAverageTime() {
        List<Analysis> analyses = analysisService.getAnalysisCompletedOnByStatusId(DateUtil.getNowAsSqlDate(),
            iStatusService.getStatusID(AnalysisStatus.Finalized));
        
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
    
    private List<Analysis> analysesWithDelayedTurnAroundTime() {
        List<Analysis> analyses = analysisService.getAnalysisCompletedOnByStatusId(DateUtil.getNowAsSqlDate(),
            iStatusService.getStatusID(AnalysisStatus.Finalized));
        
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
        return delayedAnalyses;
    }
    
    private List<Analysis> unprintedResults() {
        List<Analysis> analyses = analysisService.getAnalysisCompletedOnByStatusId(DateUtil.getNowAsSqlDate(),
            iStatusService.getStatusID(AnalysisStatus.Finalized));
        
        List<Analysis> unprintedAnalyses = new ArrayList<>();
        if(analyses == null){
           return unprintedAnalyses;
        }
        analyses.forEach(a -> {
            if (a.getPrintedDate() == null) {
                unprintedAnalyses.add(a);
            }
        });
        return unprintedAnalyses;
    }
    
}
