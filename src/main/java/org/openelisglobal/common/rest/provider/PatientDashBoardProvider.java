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
    
    @GetMapping(value = "home-dashboard/metrics", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DashBoardCount getDasBoardTiles() {
        
        DashBoardCount count = new DashBoardCount();
        DashBoardTile.TileType.stream().forEach(type -> {
             List<Integer> statusIdList;
             Set<Integer> statusIdSet;
            switch (type) {
                case ORDERS_IN_PROGRESS:
                      statusIdList = new ArrayList<>();
                    statusIdList.add(Integer.parseInt(iStatusService.getStatusID(AnalysisStatus.NotStarted)));
                    count.setOrdersInProgress(analysisService
                            .getCountOfAnalysesForStatusIds(statusIdList));
                    break;
                case ORDERS_READY_FOR_VALIDATION:
                     statusIdList = new ArrayList<>();
                    statusIdList.add(Integer.parseInt(iStatusService.getStatusID(AnalysisStatus.TechnicalAcceptance)));
                    count.setOrdersReadyForValidation(analysisService
                            .getCountOfAnalysesForStatusIds(statusIdList));
                    break;
                case ORDERS_COMPLETED_TODAY:
                    statusIdList = new ArrayList<>();
                    statusIdList.add(Integer.parseInt(iStatusService.getStatusID(AnalysisStatus.Finalized)));
                    count.setOrdersCompletedToday(analysisService.getCountOfAnalysisCompletedOnByStatusId(
                        DateUtil.getNowAsSqlDate(), statusIdList));
                    break;
                case ORDERS_PATIALLY_COMPLETED_TODAY:
                    statusIdSet = new HashSet<>();
                    statusIdSet.add(Integer.parseInt(iStatusService.getStatusID(AnalysisStatus.SampleRejected)));
                    statusIdSet.add(Integer.parseInt(iStatusService.getStatusID(AnalysisStatus.Finalized)));
                    count.setPatiallyCompletedToday(analysisService
                            .getCountOfAnalysisStartedOnExcludedByStatusId(DateUtil.getNowAsSqlDate(), statusIdSet));
                    break;
                
                case ORDERS_ENTERED_BY_USER_TODAY:
                    statusIdSet = new HashSet<>();
                    statusIdSet.add(Integer.parseInt(iStatusService.getStatusID(AnalysisStatus.SampleRejected)));
                    count.setOrderEnterdByUserToday(analysisService
                            .getCountOfAnalysisStartedOnExcludedByStatusId(DateUtil.getNowAsSqlDate(), statusIdSet));
                    break;
                case ORDERS_REJECTED_TODAY:
                    statusIdList = new ArrayList<>();
                    statusIdList.add(Integer.parseInt(iStatusService.getStatusID(AnalysisStatus.SampleRejected)));
                    count.setOrdersRejectedToday(
                        analysisService.getCountOfAnalysisStartedOnByStatusId(DateUtil.getNowAsSqlDate(),statusIdList));
                    break;
                case UN_PRINTED_RESULTS:
                    count.setUnPritendResults(unprintedResults().size());
                    break;
                case INCOMING_ORDERS:
                    count.setIncomigOrders(electronicOrderService.getCountOfAllElectronicOrdersByDateAndStatus(null, null,
                        iStatusService.getStatusID(ExternalOrderStatus.Entered))
                           );
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
