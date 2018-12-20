package spring.mine.samplebatchentry.form;

import java.lang.Boolean;
import java.lang.String;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.patient.valueholder.ObservationData;
import us.mn.state.health.lims.sample.bean.SampleOrderItem;
import us.mn.state.health.lims.sample.form.ProjectData;

public class SampleBatchEntryForm extends BaseForm {
	private Timestamp lastupdated;

	private String currentDate = "";

	private String currentTime = "";

	private String project = "";

	private Collection projects;

	private List<IdValuePair> sampleTypes;

	private String sampleXML = "";

	private SampleOrderItem sampleOrderItem;

	private List<IdValuePair> initialSampleConditionList;

	private Collection testSectionList;

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

	private Map organizationTypeLists;

	private String receivedDateForDisplay;

	private String receivedTimeForDisplay;

	private String sampleTypeSelect;
	
	private boolean localDBOnly;
	
	private String warning;

	public SampleBatchEntryForm() {
		setFormName("sampleBatchEntryForm");
	}

	public Timestamp getLastupdated() {
		return this.lastupdated;
	}

	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
	}

	public String getCurrentDate() {
		return this.currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getCurrentTime() {
		return this.currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	public String getProject() {
		return this.project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Collection getProjects() {
		return this.projects;
	}

	public void setProjects(Collection projects) {
		this.projects = projects;
	}

	public List<IdValuePair> getSampleTypes() {
		return this.sampleTypes;
	}

	public void setSampleTypes(List<IdValuePair> sampleTypes) {
		this.sampleTypes = sampleTypes;
	}

	public String getSampleXML() {
		return this.sampleXML;
	}

	public void setSampleXML(String sampleXML) {
		this.sampleXML = sampleXML;
	}

	public SampleOrderItem getSampleOrderItem() {
		return this.sampleOrderItem;
	}

	public void setSampleOrderItem(SampleOrderItem sampleOrderItem) {
		this.sampleOrderItem = sampleOrderItem;
	}

	public List<IdValuePair> getInitialSampleConditionList() {
		return this.initialSampleConditionList;
	}

	public void setInitialSampleConditionList(List<IdValuePair> initialSampleConditionList) {
		this.initialSampleConditionList = initialSampleConditionList;
	}

	public Collection getTestSectionList() {
		return this.testSectionList;
	}

	public void setTestSectionList(Collection testSectionList) {
		this.testSectionList = testSectionList;
	}

	public Boolean getPatientInfoCheck() {
		return this.patientInfoCheck;
	}

	public void setPatientInfoCheck(Boolean patientInfoCheck) {
		this.patientInfoCheck = patientInfoCheck;
	}

	public Boolean getFacilityIDCheck() {
		return this.facilityIDCheck;
	}

	public void setFacilityIDCheck(Boolean facilityIDCheck) {
		this.facilityIDCheck = facilityIDCheck;
	}

	public String getFacilityID() {
		return this.facilityID;
	}

	public void setFacilityID(String facilityID) {
		this.facilityID = facilityID;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getStudy() {
		return this.study;
	}

	public void setStudy(String study) {
		this.study = study;
	}

	public String getLabNo() {
		return this.labNo;
	}

	public void setLabNo(String labNo) {
		this.labNo = labNo;
	}

	public PatientManagementInfo getPatientProperties() {
		return this.patientProperties;
	}

	public void setPatientProperties(PatientManagementInfo patientProperties) {
		this.patientProperties = patientProperties;
	}

	public PatientSearch getPatientSearch() {
		return this.patientSearch;
	}

	public void setPatientSearch(PatientSearch patientSearch) {
		this.patientSearch = patientSearch;
	}

	public String getProgramCode() {
		return this.programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public ProjectData getProjectDataVL() {
		return this.projectDataVL;
	}

	public void setProjectDataVL(ProjectData projectDataVL) {
		this.projectDataVL = projectDataVL;
	}

	public ProjectData getProjectDataEID() {
		return this.projectDataEID;
	}

	public void setProjectDataEID(ProjectData projectDataEID) {
		this.projectDataEID = projectDataEID;
	}

	public ProjectData getProjectData() {
		return this.projectData;
	}

	public void setProjectData(ProjectData projectData) {
		this.projectData = projectData;
	}

	public ObservationData getObservations() {
		return this.observations;
	}

	public void setObservations(ObservationData observations) {
		this.observations = observations;
	}

	public Map getOrganizationTypeLists() {
		return this.organizationTypeLists;
	}

	public void setOrganizationTypeLists(Map organizationTypeLists) {
		this.organizationTypeLists = organizationTypeLists;
	}

	public String getReceivedDateForDisplay() {
		return this.receivedDateForDisplay;
	}

	public void setReceivedDateForDisplay(String receivedDateForDisplay) {
		this.receivedDateForDisplay = receivedDateForDisplay;
	}

	public String getReceivedTimeForDisplay() {
		return this.receivedTimeForDisplay;
	}

	public void setReceivedTimeForDisplay(String receivedTimeForDisplay) {
		this.receivedTimeForDisplay = receivedTimeForDisplay;
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

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}
}
