package org.openelisglobal.reportconfiguration.form;

import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.reportconfiguration.valueholder.Report;
import org.openelisglobal.reportconfiguration.valueholder.ReportCategory;

import java.util.List;

public class ReportConfigurationForm extends BaseForm {

    private List<Report> reportList;

    private List<ReportCategory> reportCategoryList;

    private Report currentReport;

    private String idOrder;

    public ReportConfigurationForm() {
        this.currentReport = new Report();
    }

    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    public Report getCurrentReport() {
        return currentReport;
    }

    public void setCurrentReport(Report currentReport) {
        this.currentReport = currentReport;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public List<ReportCategory> getReportCategoryList() {
        return reportCategoryList;
    }

    public void setReportCategoryList(List<ReportCategory> reportCategoryList) {
        this.reportCategoryList = reportCategoryList;
    }
}
