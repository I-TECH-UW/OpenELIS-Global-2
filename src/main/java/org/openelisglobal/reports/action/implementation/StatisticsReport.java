package org.openelisglobal.reports.action.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.reports.action.implementation.reportBeans.StatisticsReportData;
import org.openelisglobal.reports.form.ReportForm;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.analysis.service.AnalysisService;
import org.openelisglobal.analysis.valueholder.Analysis;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


public class StatisticsReport extends IndicatorReport implements IReportCreator, IReportParameterSetter {

    private List<StatisticsReportData> reportItems;

    @Override
    public void setRequestParameters(ReportForm form) {
        form.setUseStatisticsParams(true);
        new ReportSpecificationList(DisplayListService.getInstance().getList(DisplayListService.ListType.TEST_SECTION),
                MessageUtil.getMessage("workplan.unit.types")).setRequestParameters(form);
        form.setYearList(getYearList());
        form.setPriorityList(DisplayListService.getInstance().getList(DisplayListService.ListType.ORDER_PRIORITY)); 
        form.setReceptionTimeList(getReceptionTimeList());       
    }
    
    @Override
    public void initializeReport(ReportForm form) {
        super.initializeReport();
        createReportParameters() ;
        setTestandSample();
        createReportData(form); 
    }

    @Override
    protected String getNameForReportRequest() {
        return "null";
    }

    @Override
    protected String getNameForReport() {
        // TODO Auto-generated method stub
        return "Mozzy Statistics Report Name";
    }

    @Override
    protected String getLabNameLine1() {
        // TODO Auto-generated method stub
        return ConfigurationProperties.getInstance().getPropertyValue(Property.SiteName);
    }

    @Override
    protected String getLabNameLine2() {
        // TODO Auto-generated method stub
        return "Statistics Report 2";
    }

    @Override
    public JRDataSource getReportDataSource() throws IllegalStateException {
        // TODO Auto-generated method stub
        return errorFound ? new JRBeanCollectionDataSource(errorMsgs) : new JRBeanCollectionDataSource(reportItems);
    }

    @Override
    protected String reportFileName() {
        // TODO Auto-generated method stub
        return "StatisticsReport";
    }

    private void setTestandSample(){
        reportItems = new ArrayList<>();
        StatisticsReportData data = new StatisticsReportData();
        data.setTestName("CD4 count");
        data.setTestsJan(1);
        data.setSamplesJan(1);
        data.setTestsFeb(2);
        data.setSamplesFeb(2);
        data.setTestsMar(3);
        data.setSamplesMar(3);
        data.setTestsApr(4);
        data.setSamplesApr(4);
        data.setTestsMay(5);
        data.setSamplesMay(5);
        data.setTestsJun(6);
        data.setSamplesJun(6);
        data.setTestsJul(7);
        data.setSamplesJul(7);
        data.setTestsAug(8);
        data.setSamplesAug(8);
        data.setTestsSep(9);
        data.setSamplesSep(9);
        data.setTestsOct(10);
        data.setSamplesOct(10);
        data.setTestsNov(11);
        data.setSamplesNov(11);
        data.setTestsDec(12);
        data.setSamplesDec(12);
        
        //data.setYear("2022");

        StatisticsReportData data2 = new StatisticsReportData();
        data2.setTestName("CD4 percent");
        data2.setTestsJan(10);
        data2.setSamplesJan(10);
        data2.setTestsFeb(20);
        data2.setSamplesFeb(20);
        data2.setTestsMar(30);
        data2.setSamplesMar(30);
        data2.setTestsApr(40);
        data2.setSamplesApr(40);
        data2.setTestsMay(50);
        data2.setSamplesMay(50);
        data2.setTestsJun(60);
        data2.setSamplesJun(60);
        data2.setTestsJul(70);
        data2.setSamplesJul(70);
        data2.setTestsAug(80);
        data2.setSamplesAug(80);
        data2.setTestsSep(90);
        data2.setSamplesSep(90);
        data2.setTestsOct(100);
        data2.setSamplesOct(100);
        data2.setTestsNov(110);
        data2.setSamplesNov(110);
        data2.setTestsDec(120);
        data2.setSamplesDec(120);

        reportItems.add(data);
        reportItems.add(data2);
        
    }
    public void createReportData(ReportForm form){
        AnalysisService analysisService = SpringContext.getBean(AnalysisService.class);
        Date firstDate = DateUtil.getFistDayOfTheYear(Integer.valueOf(form.getUpperYear()));

        Date lastDate = DateUtil.getLastDayOfTheYear(Integer.valueOf(form.getUpperYear()));
        List<Analysis> yearAnalysis = analysisService.getAnalysisStartedOrCompletedInDateRange(DateUtil.convertDateTimeToSqlDate(firstDate), DateUtil.convertDateTimeToSqlDate(lastDate));
        
    }

    @Override
    protected void createReportParameters() {
        super.createReportParameters();

        reportParameters.put("startDate", "12-12-12");
        reportParameters.put("stopDate","14-14-14");
        reportParameters.put("siteId", ConfigurationProperties.getInstance().getPropertyValue(Property.SiteCode));
        reportParameters.put("directorName",
                ConfigurationProperties.getInstance().getPropertyValue(Property.labDirectorName));
        reportParameters.put("labName1", getLabNameLine1());
        reportParameters.put("labName2", getLabNameLine2());
        reportParameters.put("reportTitle", getNameForReport());
        if (ConfigurationProperties.getInstance().isPropertyValueEqual(Property.configurationName, "CI LNSP")) {
            reportParameters.put("headerName", "CILNSPHeader.jasper");
        } else {
            reportParameters.put("headerName", "GeneralHeader.jasper");
        }
        reportParameters.put("year", "year - 2022");
        reportParameters.put("labUnits", "serum , serology");
        reportParameters.put("workHours", "Normal work");
        reportParameters.put("priority", "ASAP_FUTURE");
    }

    private List<IdValuePair> getYearList() {
        List<IdValuePair> list = new ArrayList<>();
        int currentYear = DateUtil.getCurrentYear();
        for (int i = 15; i >= 0; i--) {
            String year = String.valueOf(currentYear - i);
            list.add(new IdValuePair(year, year));
        }
        Collections.reverse(list);
        return list;
    }

    private List<IdValuePair> getReceptionTimeList() {
        List<IdValuePair> list = new ArrayList<>();
        list.add(new IdValuePair(ReportForm.RecetionTime.NORMAL_WORK_HOURS.name(),  MessageUtil.getMessage("report.normalWorkingHours")));
        list.add(new IdValuePair(ReportForm.RecetionTime.OUT_OF_NORMAL_WORK_HOURS.name(),  MessageUtil.getMessage("report.outofnormalWorkingHours")));
        return list;
    }
}
