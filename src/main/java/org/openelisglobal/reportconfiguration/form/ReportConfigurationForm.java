package org.openelisglobal.reportconfiguration.form;

import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.reportconfiguration.valueholder.Report;

import java.util.List;

public class ReportConfigurationForm extends BaseForm {

    private List<Report> reportList;

    private List<IdValuePair> types;

    private List<IdValuePair> categories;

    private Report currentReport;

    public ReportConfigurationForm() {
        this.currentReport = new Report();
    }

    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    public List<IdValuePair> getTypes() {
        return types;
    }

    public void setTypes(List<IdValuePair> types) {
        this.types = types;
    }

    public List<IdValuePair> getCategories() {
        return categories;
    }

    public void setCategories(List<IdValuePair> categories) {
        this.categories = categories;
    }

    public Report getCurrentReport() {
        return currentReport;
    }

    public void setCurrentReport(Report currentReport) {
        this.currentReport = currentReport;
    }
}
