package spring.mine.samplebatchentry.form;

import java.util.List;
import java.util.Map;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.organization.util.OrganizationTypeList;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.patient.valueholder.ObservationData;
import us.mn.state.health.lims.project.valueholder.Project;
import us.mn.state.health.lims.sample.bean.SampleOrderItem;
import us.mn.state.health.lims.sample.form.ProjectData;

public class SampleBatchEntryForm extends BaseForm {

	private String currentDate = "";

	private String currentTime = "";

	private String project = "";

	private List<Project> projects;

	private List<IdValuePair> sampleTypes;

	private String sampleXML = "";

	private SampleOrderItem sampleOrderItems;

	private List<IdValuePair> initialSampleConditionList;

	private List<IdValuePair> testSectionList;

	private boolean patientInfoCheck = false;

	private boolean facilityIDCheck = false;

	private String facilityID;

	private String method;

	private String study;

	private String labNo;

	private PatientManagementInfo patientProperties;

	private PatientSearch patientSearch;

	private String programCode;

	private ProjectData projectDataVL;

	private ProjectData projectDataEID;

	private ProjectData projectData;

	private ObservationData observations;

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
