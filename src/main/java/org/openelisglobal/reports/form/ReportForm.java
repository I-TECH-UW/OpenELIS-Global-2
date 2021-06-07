package org.openelisglobal.reports.form;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.common.validator.ValidationHelper;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.patient.action.bean.PatientSearch;
import org.openelisglobal.project.valueholder.Project;
import org.openelisglobal.reports.action.implementation.ReportSpecificationList;
import org.openelisglobal.validation.annotations.ValidDate;

//values not preserved, so security validation not a large concern
//values used in jasperreports
public class ReportForm extends BaseForm {

    private boolean noRequestSpecifications = false;

    private String type = "";

    private String reportName = "";

    private String report = "glen";

    private boolean useAccessionDirect = false;

    private String accessionDirect = "";

    // private Collection selectedHighAccession;

    private boolean useHighAccessionDirect = false;

    private String highAccessionDirect = "";

    // private Collection patientNumberList;

    private boolean usePatientNumberDirect = false;

    private String patientNumberDirect = "";

    private boolean useUpperPatientNumberDirect = false;

    private String patientUpperNumberDirect = "";

    private boolean useLowerDateRange = false;

    @ValidDate
    private String lowerDateRange = "";

    private boolean useUpperDateRange = false;

    @ValidDate
    private String upperDateRange = "";

    private boolean useLocationCode = false;

    @Pattern(regexp = ValidationHelper.ID_REGEX)
    private String locationCode = "";

    private List<Organization> locationCodeList;

    private boolean useProjectCode = false;

    private boolean useDashboard = false;

    @Pattern(regexp = ValidationHelper.ID_REGEX)
    private String projectCode = "";

    private boolean usePredefinedDateRanges = false;

    @Pattern(regexp = "^$|^year$|^months3$|^months6$|^months12$|^custom$")
    private String datePeriod = "";

    // for display
    private List<IdValuePair> monthList;

    // for display
    private List<IdValuePair> yearList;

    @Pattern(regexp = "^[0-9]*$")
    private String lowerMonth = "";

    @Pattern(regexp = "^[0-9]*$")
    private String lowerYear = "";

    @Pattern(regexp = "^[0-9]*$")
    private String upperMonth = "";

    @Pattern(regexp = "^[0-9]*$")
    private String upperYear = "";

    private ReportSpecificationList selectList;

    private List<Project> projectCodeList;

    private String instructions = "";

    private Integer experimentId;

    private boolean usePatientSearch;

    private PatientSearch patientSearch;

    private String selPatient;

    public ReportForm() {
        setFormName("ReportForm");
    }

    public boolean getNoRequestSpecifications() {
        return noRequestSpecifications;
    }

    public void setNoRequestSpecifications(boolean noRequestSpecifications) {
        this.noRequestSpecifications = noRequestSpecifications;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public boolean getUseAccessionDirect() {
        return useAccessionDirect;
    }

    public void setUseAccessionDirect(boolean useAccessionDirect) {
        this.useAccessionDirect = useAccessionDirect;
    }

    public String getAccessionDirect() {
        return accessionDirect;
    }

    public void setAccessionDirect(String accessionDirect) {
        this.accessionDirect = accessionDirect;
    }

    /*
     * public Collection getSelectedHighAccession() { return selectedHighAccession;
     * }
     *
     * public void setSelectedHighAccession(Collection selectedHighAccession) {
     * this.selectedHighAccession = selectedHighAccession; }
     */

    public boolean getUseHighAccessionDirect() {
        return useHighAccessionDirect;
    }

    public void setUseHighAccessionDirect(boolean useHighAccessionDirect) {
        this.useHighAccessionDirect = useHighAccessionDirect;
    }

    public String getHighAccessionDirect() {
        return highAccessionDirect;
    }

    public void setHighAccessionDirect(String highAccessionDirect) {
        this.highAccessionDirect = highAccessionDirect;
    }

    /*
     * public Collection getPatientNumberList() { return patientNumberList; }
     *
     * public void setPatientNumberList(Collection patientNumberList) {
     * this.patientNumberList = patientNumberList; }
     */

    public boolean getUsePatientNumberDirect() {
        return usePatientNumberDirect;
    }

    public void setUsePatientNumberDirect(boolean usePatientNumberDirect) {
        this.usePatientNumberDirect = usePatientNumberDirect;
    }

    public String getPatientNumberDirect() {
        return patientNumberDirect;
    }

    public void setPatientNumberDirect(String patientNumberDirect) {
        this.patientNumberDirect = patientNumberDirect;
    }

    public boolean getUseUpperPatientNumberDirect() {
        return useUpperPatientNumberDirect;
    }

    public void setUseUpperPatientNumberDirect(boolean useUpperPatientNumberDirect) {
        this.useUpperPatientNumberDirect = useUpperPatientNumberDirect;
    }

    public String getPatientUpperNumberDirect() {
        return patientUpperNumberDirect;
    }

    public void setPatientUpperNumberDirect(String patientUpperNumberDirect) {
        this.patientUpperNumberDirect = patientUpperNumberDirect;
    }

    public boolean getUseLowerDateRange() {
        return useLowerDateRange;
    }

    public void setUseLowerDateRange(boolean useLowerDateRange) {
        this.useLowerDateRange = useLowerDateRange;
    }

    public String getLowerDateRange() {
        return lowerDateRange;
    }

    public void setLowerDateRange(String lowerDateRange) {
        this.lowerDateRange = lowerDateRange;
    }

    public boolean getUseUpperDateRange() {
        return useUpperDateRange;
    }

    public void setUseUpperDateRange(boolean useUpperDateRange) {
        this.useUpperDateRange = useUpperDateRange;
    }

    public String getUpperDateRange() {
        return upperDateRange;
    }

    public void setUpperDateRange(String upperDateRange) {
        this.upperDateRange = upperDateRange;
    }

    public boolean getUseLocationCode() {
        return useLocationCode;
    }

    public void setUseLocationCode(boolean useLocationCode) {
        this.useLocationCode = useLocationCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public List<Organization> getLocationCodeList() {
        return locationCodeList;
    }

    public void setLocationCodeList(List<Organization> locationCodeList) {
        this.locationCodeList = locationCodeList;
    }

    public boolean getUseProjectCode() {
        return useProjectCode;
    }

    public void setUseProjectCode(boolean useProjectCode) {
        this.useProjectCode = useProjectCode;
    }

    public boolean getUseDashboard() {
        return useDashboard;
    }

    public void setUseDashboard(boolean useDashboard) {
        this.useDashboard = useDashboard;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public boolean getUsePredefinedDateRanges() {
        return usePredefinedDateRanges;
    }

    public void setUsePredefinedDateRanges(boolean usePredefinedDateRanges) {
        this.usePredefinedDateRanges = usePredefinedDateRanges;
    }

    public String getDatePeriod() {
        return datePeriod;
    }

    public void setDatePeriod(String datePeriod) {
        this.datePeriod = datePeriod;
    }

    public List<IdValuePair> getMonthList() {
        return monthList;
    }

    public void setMonthList(List<IdValuePair> monthList) {
        this.monthList = monthList;
    }

    public List<IdValuePair> getYearList() {
        return yearList;
    }

    public void setYearList(List<IdValuePair> yearList) {
        this.yearList = yearList;
    }

    public String getLowerMonth() {
        return lowerMonth;
    }

    public void setLowerMonth(String lowerMonth) {
        this.lowerMonth = lowerMonth;
    }

    public String getLowerYear() {
        return lowerYear;
    }

    public void setLowerYear(String lowerYear) {
        this.lowerYear = lowerYear;
    }

    public String getUpperMonth() {
        return upperMonth;
    }

    public void setUpperMonth(String upperMonth) {
        this.upperMonth = upperMonth;
    }

    public String getUpperYear() {
        return upperYear;
    }

    public void setUpperYear(String upperYear) {
        this.upperYear = upperYear;
    }

    public ReportSpecificationList getSelectList() {
        return selectList;
    }

    public void setSelectList(ReportSpecificationList selectList) {
        this.selectList = selectList;
    }

    public List<Project> getProjectCodeList() {
        return projectCodeList;
    }

    public void setProjectCodeList(List<Project> projectCodeList) {
        this.projectCodeList = projectCodeList;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Integer getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(Integer experimentId) {
        this.experimentId = experimentId;
    }

    public boolean isUsePatientSearch() {
        return usePatientSearch;
    }

    public void setUsePatientSearch(boolean usePatientSearch) {
        this.usePatientSearch = usePatientSearch;
    }

    public PatientSearch getPatientSearch() {
        return patientSearch;
    }

    public void setPatientSearch(PatientSearch patientSearch) {
        this.patientSearch = patientSearch;
    }

    public String getSelPatient() {
        return selPatient;
    }

    public void setSelPatient(String selPatient) {
        this.selPatient = selPatient;
    }
}
