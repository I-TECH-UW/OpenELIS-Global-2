package org.openelisglobal.reports.action.implementation;

import java.util.ArrayList;
import java.util.List;

import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.reports.action.implementation.reportBeans.StatisticsReportData;
import org.openelisglobal.reports.form.ReportForm;
import org.openelisglobal.sample.valueholder.OrderPriority;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import net.sf.jasperreports.engine.JRDataSource;

public class StatisticsReport extends IndicatorReport implements IReportCreator, IReportParameterSetter {

    private List<StatisticsReportData> reportItems;
    
    @Override
    public void initializeReport(ReportForm form) {
        super.initializeReport();
        createReportParameters() ;
        setTestandSample();
        
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
        List<String> labUnits = new ArrayList<>();
        labUnits.add("Bio Chem");
        labUnits.add("Serorlogy");
       // data.setLabSections(labUnits);
       // data.setPriority(OrderPriority.FUTURE_STAT);
        data.setSamples(4);
        data.setTests(4);
        data.setTestName("CD4 count");
        //data.setYear("2022");

        StatisticsReportData data2 = new StatisticsReportData();
       // data2.setLabSections(labUnits);
       // data2.setPriority(OrderPriority.FUTURE_STAT);
        data2.setSamples(6);
        data2.setTests(6);
        data2.setTestName("CD4 percent");
        //data2.setYear("2022");

        reportItems.add(data);
        reportItems.add(data2);
        
    }

    @Override
    protected void createReportParameters() {
        super.createReportParameters();

        reportParameters.put("startDate", lowerDateRange);
        reportParameters.put("stopDate", upperDateRange);
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

    
}
