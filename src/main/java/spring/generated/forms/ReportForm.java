package spring.generated.forms;

import java.lang.Boolean;
import java.lang.String;
import java.sql.Timestamp;
import java.util.Collection;
import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.reports.action.implementation.ReportSpecificationList;

public class ReportForm extends BaseForm {
  private Timestamp lastupdated;

  private Boolean noRequestSpecifications = Boolean.FALSE;

  private String reportType = "";

  private String reportName = "";

  private String reportRequest = "glen";

  private Boolean useAccessionDirect = Boolean.FALSE;

  private String accessionDirect = "";

  private Collection selectedHighAccession;

  private Boolean useHighAccessionDirect = Boolean.FALSE;

  private String highAccessionDirect = "";

  private Collection patientNumberList;

  private Boolean usePatientNumberDirect = Boolean.FALSE;

  private String patientNumberDirect = "";

  private Boolean useUpperPatientNumberDirect = Boolean.FALSE;

  private String patientUpperNumberDirect = "";

  private Boolean useLowerDateRange = Boolean.FALSE;

  private String lowerDateRange = "";

  private Boolean useUpperDateRange = Boolean.FALSE;

  private String upperDateRange = "";

  private Boolean useLocationCode = Boolean.FALSE;

  private String locationCode = "";

  private Collection locationCodeList;

  private Boolean useProjectCode = Boolean.FALSE;

  private Boolean useDashboard = Boolean.FALSE;

  private String projectCode = "";

  private Boolean usePredefinedDateRanges = Boolean.FALSE;

  private String datePeriod = "";

  private Collection monthList;

  private Collection yearList;

  private String lowerMonth = "";

  private String lowerYear = "";

  private String upperMonth = "";

  private String upperYear = "";

  private ReportSpecificationList selectList;

  private Collection projectCodeList;

  private String instructions = "";

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }

  public Boolean getNoRequestSpecifications() {
    return this.noRequestSpecifications;
  }

  public void setNoRequestSpecifications(Boolean noRequestSpecifications) {
    this.noRequestSpecifications = noRequestSpecifications;
  }

  public String getReportType() {
    return this.reportType;
  }

  public void setReportType(String reportType) {
    this.reportType = reportType;
  }

  public String getReportName() {
    return this.reportName;
  }

  public void setReportName(String reportName) {
    this.reportName = reportName;
  }

  public String getReportRequest() {
    return this.reportRequest;
  }

  public void setReportRequest(String reportRequest) {
    this.reportRequest = reportRequest;
  }

  public Boolean getUseAccessionDirect() {
    return this.useAccessionDirect;
  }

  public void setUseAccessionDirect(Boolean useAccessionDirect) {
    this.useAccessionDirect = useAccessionDirect;
  }

  public String getAccessionDirect() {
    return this.accessionDirect;
  }

  public void setAccessionDirect(String accessionDirect) {
    this.accessionDirect = accessionDirect;
  }

  public Collection getSelectedHighAccession() {
    return this.selectedHighAccession;
  }

  public void setSelectedHighAccession(Collection selectedHighAccession) {
    this.selectedHighAccession = selectedHighAccession;
  }

  public Boolean getUseHighAccessionDirect() {
    return this.useHighAccessionDirect;
  }

  public void setUseHighAccessionDirect(Boolean useHighAccessionDirect) {
    this.useHighAccessionDirect = useHighAccessionDirect;
  }

  public String getHighAccessionDirect() {
    return this.highAccessionDirect;
  }

  public void setHighAccessionDirect(String highAccessionDirect) {
    this.highAccessionDirect = highAccessionDirect;
  }

  public Collection getPatientNumberList() {
    return this.patientNumberList;
  }

  public void setPatientNumberList(Collection patientNumberList) {
    this.patientNumberList = patientNumberList;
  }

  public Boolean getUsePatientNumberDirect() {
    return this.usePatientNumberDirect;
  }

  public void setUsePatientNumberDirect(Boolean usePatientNumberDirect) {
    this.usePatientNumberDirect = usePatientNumberDirect;
  }

  public String getPatientNumberDirect() {
    return this.patientNumberDirect;
  }

  public void setPatientNumberDirect(String patientNumberDirect) {
    this.patientNumberDirect = patientNumberDirect;
  }

  public Boolean getUseUpperPatientNumberDirect() {
    return this.useUpperPatientNumberDirect;
  }

  public void setUseUpperPatientNumberDirect(Boolean useUpperPatientNumberDirect) {
    this.useUpperPatientNumberDirect = useUpperPatientNumberDirect;
  }

  public String getPatientUpperNumberDirect() {
    return this.patientUpperNumberDirect;
  }

  public void setPatientUpperNumberDirect(String patientUpperNumberDirect) {
    this.patientUpperNumberDirect = patientUpperNumberDirect;
  }

  public Boolean getUseLowerDateRange() {
    return this.useLowerDateRange;
  }

  public void setUseLowerDateRange(Boolean useLowerDateRange) {
    this.useLowerDateRange = useLowerDateRange;
  }

  public String getLowerDateRange() {
    return this.lowerDateRange;
  }

  public void setLowerDateRange(String lowerDateRange) {
    this.lowerDateRange = lowerDateRange;
  }

  public Boolean getUseUpperDateRange() {
    return this.useUpperDateRange;
  }

  public void setUseUpperDateRange(Boolean useUpperDateRange) {
    this.useUpperDateRange = useUpperDateRange;
  }

  public String getUpperDateRange() {
    return this.upperDateRange;
  }

  public void setUpperDateRange(String upperDateRange) {
    this.upperDateRange = upperDateRange;
  }

  public Boolean getUseLocationCode() {
    return this.useLocationCode;
  }

  public void setUseLocationCode(Boolean useLocationCode) {
    this.useLocationCode = useLocationCode;
  }

  public String getLocationCode() {
    return this.locationCode;
  }

  public void setLocationCode(String locationCode) {
    this.locationCode = locationCode;
  }

  public Collection getLocationCodeList() {
    return this.locationCodeList;
  }

  public void setLocationCodeList(Collection locationCodeList) {
    this.locationCodeList = locationCodeList;
  }

  public Boolean getUseProjectCode() {
    return this.useProjectCode;
  }

  public void setUseProjectCode(Boolean useProjectCode) {
    this.useProjectCode = useProjectCode;
  }

  public Boolean getUseDashboard() {
    return this.useDashboard;
  }

  public void setUseDashboard(Boolean useDashboard) {
    this.useDashboard = useDashboard;
  }

  public String getProjectCode() {
    return this.projectCode;
  }

  public void setProjectCode(String projectCode) {
    this.projectCode = projectCode;
  }

  public Boolean getUsePredefinedDateRanges() {
    return this.usePredefinedDateRanges;
  }

  public void setUsePredefinedDateRanges(Boolean usePredefinedDateRanges) {
    this.usePredefinedDateRanges = usePredefinedDateRanges;
  }

  public String getDatePeriod() {
    return this.datePeriod;
  }

  public void setDatePeriod(String datePeriod) {
    this.datePeriod = datePeriod;
  }

  public Collection getMonthList() {
    return this.monthList;
  }

  public void setMonthList(Collection monthList) {
    this.monthList = monthList;
  }

  public Collection getYearList() {
    return this.yearList;
  }

  public void setYearList(Collection yearList) {
    this.yearList = yearList;
  }

  public String getLowerMonth() {
    return this.lowerMonth;
  }

  public void setLowerMonth(String lowerMonth) {
    this.lowerMonth = lowerMonth;
  }

  public String getLowerYear() {
    return this.lowerYear;
  }

  public void setLowerYear(String lowerYear) {
    this.lowerYear = lowerYear;
  }

  public String getUpperMonth() {
    return this.upperMonth;
  }

  public void setUpperMonth(String upperMonth) {
    this.upperMonth = upperMonth;
  }

  public String getUpperYear() {
    return this.upperYear;
  }

  public void setUpperYear(String upperYear) {
    this.upperYear = upperYear;
  }

  public ReportSpecificationList getSelectList() {
    return this.selectList;
  }

  public void setSelectList(ReportSpecificationList selectList) {
    this.selectList = selectList;
  }

  public Collection getProjectCodeList() {
    return this.projectCodeList;
  }

  public void setProjectCodeList(Collection projectCodeList) {
    this.projectCodeList = projectCodeList;
  }

  public String getInstructions() {
    return this.instructions;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }
}
