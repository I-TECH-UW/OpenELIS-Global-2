package spring.mine.reports.form;

import java.sql.Timestamp;
import java.util.List;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.project.valueholder.Project;
import us.mn.state.health.lims.reports.action.implementation.ReportSpecificationList;

public class ReportForm extends BaseForm {
	private Timestamp lastupdated;

	private Boolean noRequestSpecifications = Boolean.FALSE;

	private String type = "";

	private String reportName = "";

	private String report = "glen";

	private Boolean useAccessionDirect = Boolean.FALSE;

	private String accessionDirect = "";

	// private Collection selectedHighAccession;

	private Boolean useHighAccessionDirect = Boolean.FALSE;

	private String highAccessionDirect = "";

	// private Collection patientNumberList;

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

	private List<Organization> locationCodeList;

	private Boolean useProjectCode = Boolean.FALSE;

	private Boolean useDashboard = Boolean.FALSE;

	private String projectCode = "";

	private Boolean usePredefinedDateRanges = Boolean.FALSE;

	private String datePeriod = "";

	private List<IdValuePair> monthList;

	private List<IdValuePair> yearList;

	private String lowerMonth = "";

	private String lowerYear = "";

	private String upperMonth = "";

	private String upperYear = "";

	private ReportSpecificationList selectList;

	private List<Project> projectCodeList;

	private String instructions = "";

	public ReportForm() {
		setFormName("ReportForm");
	}

	public Timestamp getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
	}

	public Boolean getNoRequestSpecifications() {
		return noRequestSpecifications;
	}

	public void setNoRequestSpecifications(Boolean noRequestSpecifications) {
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

	public Boolean getUseAccessionDirect() {
		return useAccessionDirect;
	}

	public void setUseAccessionDirect(Boolean useAccessionDirect) {
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

	public Boolean getUseHighAccessionDirect() {
		return useHighAccessionDirect;
	}

	public void setUseHighAccessionDirect(Boolean useHighAccessionDirect) {
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

	public Boolean getUsePatientNumberDirect() {
		return usePatientNumberDirect;
	}

	public void setUsePatientNumberDirect(Boolean usePatientNumberDirect) {
		this.usePatientNumberDirect = usePatientNumberDirect;
	}

	public String getPatientNumberDirect() {
		return patientNumberDirect;
	}

	public void setPatientNumberDirect(String patientNumberDirect) {
		this.patientNumberDirect = patientNumberDirect;
	}

	public Boolean getUseUpperPatientNumberDirect() {
		return useUpperPatientNumberDirect;
	}

	public void setUseUpperPatientNumberDirect(Boolean useUpperPatientNumberDirect) {
		this.useUpperPatientNumberDirect = useUpperPatientNumberDirect;
	}

	public String getPatientUpperNumberDirect() {
		return patientUpperNumberDirect;
	}

	public void setPatientUpperNumberDirect(String patientUpperNumberDirect) {
		this.patientUpperNumberDirect = patientUpperNumberDirect;
	}

	public Boolean getUseLowerDateRange() {
		return useLowerDateRange;
	}

	public void setUseLowerDateRange(Boolean useLowerDateRange) {
		this.useLowerDateRange = useLowerDateRange;
	}

	public String getLowerDateRange() {
		return lowerDateRange;
	}

	public void setLowerDateRange(String lowerDateRange) {
		this.lowerDateRange = lowerDateRange;
	}

	public Boolean getUseUpperDateRange() {
		return useUpperDateRange;
	}

	public void setUseUpperDateRange(Boolean useUpperDateRange) {
		this.useUpperDateRange = useUpperDateRange;
	}

	public String getUpperDateRange() {
		return upperDateRange;
	}

	public void setUpperDateRange(String upperDateRange) {
		this.upperDateRange = upperDateRange;
	}

	public Boolean getUseLocationCode() {
		return useLocationCode;
	}

	public void setUseLocationCode(Boolean useLocationCode) {
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

	public Boolean getUseProjectCode() {
		return useProjectCode;
	}

	public void setUseProjectCode(Boolean useProjectCode) {
		this.useProjectCode = useProjectCode;
	}

	public Boolean getUseDashboard() {
		return useDashboard;
	}

	public void setUseDashboard(Boolean useDashboard) {
		this.useDashboard = useDashboard;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public Boolean getUsePredefinedDateRanges() {
		return usePredefinedDateRanges;
	}

	public void setUsePredefinedDateRanges(Boolean usePredefinedDateRanges) {
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
}
