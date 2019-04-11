package spring.mine.samplebatchentry.form;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.ValidationHelper;
import spring.mine.validation.annotations.ValidAccessionNumber;
import spring.mine.validation.annotations.ValidDate;
import spring.mine.validation.annotations.ValidTime;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator.DateRelation;
import us.mn.state.health.lims.organization.util.OrganizationTypeList;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.patient.valueholder.ObservationData;
import us.mn.state.health.lims.project.valueholder.Project;
import us.mn.state.health.lims.sample.bean.SampleOrderItem;
import us.mn.state.health.lims.sample.form.ProjectData;

public class SampleBatchEntryForm extends BaseForm {

	@ValidDate(relative = DateRelation.TODAY)
	private String currentDate = "";

	@ValidTime
	private String currentTime = "";

	// for display
	private String project = "";

	// for display
	private List<Project> projects;

	// for display
	private List<IdValuePair> sampleTypes;

	private String sampleXML = "";

	// TODO
	@Valid
	private SampleOrderItem sampleOrderItems;

	// for display
	private List<IdValuePair> initialSampleConditionList;

	// for display
	private List<IdValuePair> testSectionList;

	private boolean patientInfoCheck = false;

	private boolean facilityIDCheck = false;

	@Pattern(regexp = ValidationHelper.ID_REGEX)
	private String facilityID;

	@Pattern(regexp = "On Demand|Pre-Printed")
	private String method;

	@Pattern(regexp = "routine|viralLoad|EID")
	private String study;

	@ValidAccessionNumber
	private String labNo;

	// TODO
	@Valid
	private PatientManagementInfo patientProperties;

	/// for display
	private PatientSearch patientSearch;

	// in validator
	private String programCode;

	// TODO
	@Valid
	private ProjectData projectDataVL;

	// TODO
	@Valid
	private ProjectData projectDataEID;

	// TODO
	@Valid
	private ProjectData projectData;

	// TODO
	@Valid
	private ObservationData observations;

	// for display
	private Map<String, OrganizationTypeList> organizationTypeLists;

	private String sampleTypeSelect;

	private boolean localDBOnly;

	public SampleBatchEntryForm() {
		setFormName("sampleBatchEntryForm");
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<IdValuePair> getSampleTypes() {
		return sampleTypes;
	}

	public void setSampleTypes(List<IdValuePair> sampleTypes) {
		this.sampleTypes = sampleTypes;
	}

	public String getSampleXML() {
		return sampleXML;
	}

	public void setSampleXML(String sampleXML) {
		this.sampleXML = sampleXML;
	}

	public SampleOrderItem getSampleOrderItems() {
		return sampleOrderItems;
	}

	public void setSampleOrderItems(SampleOrderItem sampleOrderItems) {
		this.sampleOrderItems = sampleOrderItems;
	}

	public List<IdValuePair> getInitialSampleConditionList() {
		return initialSampleConditionList;
	}

	public void setInitialSampleConditionList(List<IdValuePair> initialSampleConditionList) {
		this.initialSampleConditionList = initialSampleConditionList;
	}

	public List<IdValuePair> getTestSectionList() {
		return testSectionList;
	}

	public void setTestSectionList(List<IdValuePair> testSectionList) {
		this.testSectionList = testSectionList;
	}

	public Boolean getPatientInfoCheck() {
		return patientInfoCheck;
	}

	public void setPatientInfoCheck(Boolean patientInfoCheck) {
		this.patientInfoCheck = patientInfoCheck;
	}

	public Boolean getFacilityIDCheck() {
		return facilityIDCheck;
	}

	public void setFacilityIDCheck(Boolean facilityIDCheck) {
		this.facilityIDCheck = facilityIDCheck;
	}

	public String getFacilityID() {
		return facilityID;
	}

	public void setFacilityID(String facilityID) {
		this.facilityID = facilityID;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getStudy() {
		return study;
	}

	public void setStudy(String study) {
		this.study = study;
	}

	public String getLabNo() {
		return labNo;
	}

	public void setLabNo(String labNo) {
		this.labNo = labNo;
	}

	public PatientManagementInfo getPatientProperties() {
		return patientProperties;
	}

	public void setPatientProperties(PatientManagementInfo patientProperties) {
		this.patientProperties = patientProperties;
	}

	public PatientSearch getPatientSearch() {
		return patientSearch;
	}

	public void setPatientSearch(PatientSearch patientSearch) {
		this.patientSearch = patientSearch;
	}

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public ProjectData getProjectDataVL() {
		return projectDataVL;
	}

	public void setProjectDataVL(ProjectData projectDataVL) {
		this.projectDataVL = projectDataVL;
	}

	public ProjectData getProjectDataEID() {
		return projectDataEID;
	}

	public void setProjectDataEID(ProjectData projectDataEID) {
		this.projectDataEID = projectDataEID;
	}

	public ProjectData getProjectData() {
		return projectData;
	}

	public void setProjectData(ProjectData projectData) {
		this.projectData = projectData;
	}

	public ObservationData getObservations() {
		return observations;
	}

	public void setObservations(ObservationData observations) {
		this.observations = observations;
	}

	public Map<String, OrganizationTypeList> getOrganizationTypeLists() {
		return organizationTypeLists;
	}

	public void setOrganizationTypeLists(Map<String, OrganizationTypeList> organizationTypeLists) {
		this.organizationTypeLists = organizationTypeLists;
	}

	public String getSampleTypeSelect() {
		return sampleTypeSelect;
	}

	public void setSampleTypeSelect(String sampleTypeSelect) {
		this.sampleTypeSelect = sampleTypeSelect;
	}

	public boolean isLocalDBOnly() {
		return localDBOnly;
	}

	public void setLocalDBOnly(boolean localDBOnly) {
		this.localDBOnly = localDBOnly;
	}

}
