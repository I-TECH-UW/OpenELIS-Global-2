package org.openelisglobal.common.rest.provider;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.openelisglobal.analysis.service.AnalysisService;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.rest.provider.bean.homedashboard.AverageTimeDisplayBean;
import org.openelisglobal.common.rest.provider.bean.homedashboard.DashBoardMetrics;
import org.openelisglobal.common.rest.provider.bean.homedashboard.DashBoardTile;
import org.openelisglobal.common.rest.provider.bean.homedashboard.OrderDisplayBean;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.StatusService.AnalysisStatus;
import org.openelisglobal.common.services.StatusService.ExternalOrderStatus;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.dataexchange.fhir.FhirConfig;
import org.openelisglobal.dataexchange.fhir.FhirUtil;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.service.order.ElectronicOrderService;
import org.openelisglobal.sample.service.SampleService;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.test.valueholder.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.uhn.fhir.rest.client.api.IGenericClient;

@Controller
@RequestMapping(value = "/rest/")
public class PatientDashBoardProvider {
    
    @Autowired
    AnalysisService analysisService;
    
    @Autowired
    IStatusService iStatusService;
    
    @Autowired
    ElectronicOrderService electronicOrderService;
    
    @Autowired
    SampleHumanService sampleHumanService;
    
    @Autowired
    private SampleService sampleService;
    
    @Autowired
    private FhirUtil fhirUtil;
    
    @Autowired
    private FhirConfig fhirConfig;
    
    @Autowired
    private TestService testService;
    
    private double calculateAverageReceptionToValidationTime() {
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
    
    private double calculateAverageReceptionToResultTime() {
        List<Analysis> analyses = analysisService
                .getAnalysesForStatusId(iStatusService.getStatusID(AnalysisStatus.TechnicalAcceptance));
        
        List<Long> hours = new ArrayList<>();
        analyses.forEach(analysis -> {
            // Convert java.sql.Date to java.time.LocalDate
            LocalDate localStartDate = analysis.getStartedDate().toLocalDate();
            LocalDate localEndDate = analysis.getCompletedDate().toLocalDate();
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
    
    private double calculateAverageResultToValidationTime() {
        List<Analysis> analyses = analysisService.getAnalysisCompletedOnByStatusId(DateUtil.getNowAsSqlDate(),
            iStatusService.getStatusID(AnalysisStatus.Finalized));
        
        List<Long> hours = new ArrayList<>();
        analyses.forEach(analysis -> {
            // Convert java.sql.Date to java.time.LocalDate
            LocalDate localStartDate = analysis.getCompletedDate().toLocalDate();
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
        if (analyses == null) {
            return unprintedAnalyses;
        }
        analyses.forEach(a -> {
            if (a.getPrintedDate() == null) {
                unprintedAnalyses.add(a);
            }
        });
        return unprintedAnalyses;
    }
    
    private List<OrderDisplayBean> convertAnalysesToOrderBean(List<Analysis> analyses) {
        List<OrderDisplayBean> orderBeanList = new ArrayList<>();
        if (analyses != null) {
            analyses.forEach(analysis -> {
                if (analysis != null) {
                    OrderDisplayBean orderBean = new OrderDisplayBean();
                    Sample sample = analysis.getSampleItem() != null ? analysis.getSampleItem().getSample() : null;
                    if (sample != null) {
                        orderBean.setPriority(sample.getPriority() != null ?sample.getPriority().toString() : "");
                        orderBean.setLabNumber(sample.getAccessionNumber() != null ? sample.getAccessionNumber() : "");
                        orderBean.setPatientId(sampleHumanService.getPatientForSample(sample).getNationalId());
                    }
                    orderBean.setOrderDate(analysis.getStartedDateForDisplay());
                    orderBean.setTestName(analysis.getTest() != null ? analysis.getTest().getLocalizedName() : "");
                    orderBeanList.add(orderBean);
                }
            });
        }
        
        return orderBeanList;
    }
    
    private List<OrderDisplayBean> convertElectronicToOrderBean(List<ElectronicOrder> eOrders) {
        List<OrderDisplayBean> orderBeanList = new ArrayList<>();
        eOrders.forEach(eOrder -> {
            OrderDisplayBean orderBean = new OrderDisplayBean();
            orderBean.setPriority(eOrder.getPriority().toString());
            orderBean.setOrderDate(DateUtil.convertTimestampToStringDate(eOrder.getOrderTimestamp()));
            Sample sample = sampleService.getSampleByReferringId(eOrder.getExternalId());
            if (sample != null) {
                orderBean.setLabNumber(sample.getAccessionNumber());
            }
            
            IGenericClient fhirClient = fhirUtil.getFhirClient(fhirConfig.getLocalFhirStorePath());
            ServiceRequest serviceRequest = fhirClient.read().resource(ServiceRequest.class).withId(eOrder.getExternalId())
                    .execute();
            
            Test test = null;
            for (Coding coding : serviceRequest.getCode().getCoding()) {
                if (coding.hasSystem()) {
                    if (coding.getSystem().equalsIgnoreCase("http://loinc.org")) {
                        List<Test> tests = testService.getActiveTestsByLoinc(coding.getCode());
                        if (tests.size() != 0) {
                            test = tests.get(0);
                            break;
                        }
                    }
                }
            }
            if (test != null) {
                orderBean.setTestName(test.getLocalizedTestName().getLocalizedValue());
            }
            
            orderBean.setPatientId(eOrder.getPatient().getNationalId());
            orderBeanList.add(orderBean);
        });
        
        return orderBeanList;
    }
    
    @GetMapping(value = "home-dashboard/metrics", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DashBoardMetrics getDasBoardTiles() {
        
        DashBoardMetrics metrics = new DashBoardMetrics();
        DashBoardTile.TileType.stream().forEach(type -> {
            List<Integer> statusIdList;
            Set<Integer> statusIdSet;
            switch (type) {
                case ORDERS_IN_PROGRESS:
                    statusIdList = new ArrayList<>();
                    statusIdList.add(Integer.parseInt(iStatusService.getStatusID(AnalysisStatus.NotStarted)));
                    metrics.setOrdersInProgress(analysisService.getCountOfAnalysesForStatusIds(statusIdList));
                    break;
                case ORDERS_READY_FOR_VALIDATION:
                    statusIdList = new ArrayList<>();
                    statusIdList.add(Integer.parseInt(iStatusService.getStatusID(AnalysisStatus.TechnicalAcceptance)));
                    metrics.setOrdersReadyForValidation(analysisService.getCountOfAnalysesForStatusIds(statusIdList));
                    break;
                case ORDERS_COMPLETED_TODAY:
                    statusIdList = new ArrayList<>();
                    statusIdList.add(Integer.parseInt(iStatusService.getStatusID(AnalysisStatus.Finalized)));
                    metrics.setOrdersCompletedToday(
                        analysisService.getCountOfAnalysisCompletedOnByStatusId(DateUtil.getNowAsSqlDate(), statusIdList));
                    break;
                case ORDERS_PATIALLY_COMPLETED_TODAY:
                    statusIdSet = new HashSet<>();
                    statusIdSet.add(Integer.parseInt(iStatusService.getStatusID(AnalysisStatus.SampleRejected)));
                    statusIdSet.add(Integer.parseInt(iStatusService.getStatusID(AnalysisStatus.Finalized)));
                    metrics.setPatiallyCompletedToday(analysisService
                            .getCountOfAnalysisStartedOnExcludedByStatusId(DateUtil.getNowAsSqlDate(), statusIdSet));
                    break;
                
                case ORDERS_ENTERED_BY_USER_TODAY:
                    statusIdSet = new HashSet<>();
                    statusIdSet.add(Integer.parseInt(iStatusService.getStatusID(AnalysisStatus.SampleRejected)));
                    metrics.setOrderEnterdByUserToday(analysisService
                            .getCountOfAnalysisStartedOnExcludedByStatusId(DateUtil.getNowAsSqlDate(), statusIdSet));
                    break;
                case ORDERS_REJECTED_TODAY:
                    statusIdList = new ArrayList<>();
                    statusIdList.add(Integer.parseInt(iStatusService.getStatusID(AnalysisStatus.SampleRejected)));
                    metrics.setOrdersRejectedToday(
                        analysisService.getCountOfAnalysisStartedOnByStatusId(DateUtil.getNowAsSqlDate(), statusIdList));
                    break;
                case UN_PRINTED_RESULTS:
                    metrics.setUnPritendResults(unprintedResults().size());
                    break;
                case INCOMING_ORDERS:
                    metrics.setIncomigOrders(electronicOrderService.getCountOfAllElectronicOrdersByDateAndStatus(null, null,
                        iStatusService.getStatusID(ExternalOrderStatus.Entered)));
                    break;
                case AVERAGE_TURN_AROUND_TIME:
                    metrics.setAverageTurnAroudTime(calculateAverageReceptionToValidationTime());
                    break;
                case DELAYED_TURN_AROUND:
                    
                    metrics.setDelayedTurnAround(analysesWithDelayedTurnAroundTime().size());
                    break;
            }
        });
        
        return metrics;
    }
    
    @GetMapping(value = "home-dashboard/{listType}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<OrderDisplayBean> getDashBoardDisplayList(@PathVariable DashBoardTile.TileType listType) {
        
        Set<Integer> statusIdSet;
        List<Analysis> analyses;
        switch (listType) {
            case ORDERS_IN_PROGRESS:
                analyses = analysisService.getAnalysesForStatusId(iStatusService.getStatusID(AnalysisStatus.NotStarted));
                return convertAnalysesToOrderBean(analyses);
            case ORDERS_READY_FOR_VALIDATION:
                
                analyses = analysisService
                        .getAnalysesForStatusId(iStatusService.getStatusID(AnalysisStatus.TechnicalAcceptance));
                return convertAnalysesToOrderBean(analyses);
            case ORDERS_COMPLETED_TODAY:
                analyses = analysisService.getAnalysisCompletedOnByStatusId(DateUtil.getNowAsSqlDate(),
                    iStatusService.getStatusID(AnalysisStatus.Finalized));
                return convertAnalysesToOrderBean(analyses);
            case ORDERS_PATIALLY_COMPLETED_TODAY:
                statusIdSet = new HashSet<>();
                statusIdSet.add(Integer.parseInt(iStatusService.getStatusID(AnalysisStatus.SampleRejected)));
                statusIdSet.add(Integer.parseInt(iStatusService.getStatusID(AnalysisStatus.Finalized)));
                analyses = analysisService.getAnalysisStartedOnExcludedByStatusId(DateUtil.getNowAsSqlDate(), statusIdSet);
                return convertAnalysesToOrderBean(analyses);
            
            case ORDERS_ENTERED_BY_USER_TODAY:
                statusIdSet = new HashSet<>();
                statusIdSet.add(Integer.parseInt(iStatusService.getStatusID(AnalysisStatus.SampleRejected)));
                analyses = analysisService.getAnalysisStartedOnExcludedByStatusId(DateUtil.getNowAsSqlDate(), statusIdSet);
                return convertAnalysesToOrderBean(analyses);
            case ORDERS_REJECTED_TODAY:
                analyses = analysisService.getAnalysisStartedOnRangeByStatusId(DateUtil.getNowAsSqlDate(),
                    DateUtil.getNowAsSqlDate(), iStatusService.getStatusID(AnalysisStatus.SampleRejected));
                return convertAnalysesToOrderBean(analyses);
            case UN_PRINTED_RESULTS:
                return convertAnalysesToOrderBean(unprintedResults());
            case INCOMING_ORDERS:
                List<ElectronicOrder> eOrders = electronicOrderService.getAllElectronicOrdersByDateAndStatus(null, null,
                    iStatusService.getStatusID(ExternalOrderStatus.Entered), ElectronicOrder.SortOrder.EXTERNAL_ID);
                return convertElectronicToOrderBean(eOrders);
            case AVERAGE_TURN_AROUND_TIME:
                return new ArrayList<>();
            case DELAYED_TURN_AROUND:
                return convertAnalysesToOrderBean(analysesWithDelayedTurnAroundTime());
            
        }
        return new ArrayList<>();
    }
    
    @GetMapping(value = "home-dashboard/turn-around-time-metrics", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AverageTimeDisplayBean getDasBoardAverageTurnAroundTime() {
        AverageTimeDisplayBean timeBean = new AverageTimeDisplayBean();
        timeBean.setReceptionToResult(calculateAverageReceptionToResultTime());
        timeBean.setReceptionToValidation(calculateAverageReceptionToValidationTime());
        timeBean.setResultToValidation(calculateAverageResultToValidationTime());
        return timeBean;
    }
}
