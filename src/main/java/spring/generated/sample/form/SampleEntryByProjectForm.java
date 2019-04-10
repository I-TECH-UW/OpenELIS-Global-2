package spring.generated.sample.form;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.patient.action.bean.PatientClinicalInfo;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.patient.valueholder.ObservationData;
import us.mn.state.health.lims.sample.bean.SampleOrderItem;
import us.mn.state.health.lims.sample.form.ProjectData;

public class SampleEntryByProjectForm extends BaseForm {
	private String currentDate = "";

	private String receivedDateForDisplay = "";

	private String receivedTimeForDisplay = "";

	private String interviewDate = "";

	private String interviewTime = "";

	private String project = "";

	private String labNo = "";

	private Timestamp lastupdated;

	private String doctor = "";

	private String subjectNumber = "";

	private String siteSubjectNumber = "";

	private String gender = "";

	private List<IdValuePair> genders;

	private String birthDateForDisplay = "";

	private String samplePK = "";

	private String patientPK = "";

	private String patientProcessingStatus = "Add";

	private String patientLastUpdated = "";

	private String personLastUpdated = "";

	private ProjectData ProjectData;

	private ObservationData observations;

	private Map<String, List<Organization>> organizationTypeLists;

	private Map<String, List<Dictionary>> dictionaryLists;

	private Map<String, List<Dictionary>> formLists;

	private PatientManagementInfo patientProperties;

	private PatientSearch patientSearch;

	private PatientClinicalInfo patientClinicalProperties;

	private SampleOrderItem sampleOrderItems;

	private String domain = "";

	public SampleEntryByProjectForm() {
		setFormName("sampleEntryByProjectForm");
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getReceivedDateForDisplay() {
		return receivedDateForDisplay;
	}

	public void setReceivedDateForDisplay(String receivedDateForDisplay) {
		this.receivedDateForDisplay = receivedDateForDisplay;
	}

	public String getReceivedTimeForDisplay() {
		return receivedTimeForDisplay;
	}

	public void setReceivedTimeForDisplay(String receivedTimeForDisplay) {
		this.receivedTimeForDisplay = receivedTimeForDisplay;
	}

	public String getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(String interviewDate) {
		this.interviewDate = interviewDate;
	}

	public String getInterviewTime() {
		return interviewTime;
	}

	public void setInterviewTime(String interviewTime) {
		this.interviewTime = interviewTime;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getLabNo() {
		return labNo;
	}

	public void setLabNo(String labNo) {
		this.labNo = labNo;
	}

	public Timestamp getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getSubjectNumber() {
		return subjectNumber;
	}

	public void setSubjectNumber(String subjectNumber) {
		this.subjectNumber = subjectNumber;
	}

	public String getSiteSubjectNumber() {
		return siteSubjectNumber;
	}

	public void setSiteSubjectNumber(String siteSubjectNumber) {
		this.siteSubjectNumber = siteSubjectNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<IdValuePair> getGenders() {
		return genders;
	}

	public void setGenders(List<IdValuePair> genders) {
		this.genders = genders;
	}

	public String getBirthDateForDisplay() {
		return birthDateForDisplay;
	}

	public void setBirthDateForDisplay(String birthDateForDisplay) {
		this.birthDateForDisplay = birthDateForDisplay;
	}

	public String getSamplePK() {
		return samplePK;
	}

	public void setSamplePK(String samplePK) {
		this.samplePK = samplePK;
	}

	public String getPatientPK() {
		return patientPK;
	}

	public void setPatientPK(String patientPK) {
		this.patientPK = patientPK;
	}

	public String getPatientProcessingStatus() {
		return patientProcessingStatus;
	}

	public void setPatientProcessingStatus(String patientProcessingStatus) {
		this.patientProcessingStatus = patientProcessingStatus;
	}

	public String getPatientLastUpdated() {
		return patientLastUpdated;
	}

	public void setPatientLastUpdated(String patientLastUpdated) {
		this.patientLastUpdated = patientLastUpdated;
	}

	public String getPersonLastUpdated() {
		return personLastUpdated;
	}

	public void setPersonLastUpdated(String personLastUpdated) {
		this.personLastUpdated = personLastUpdated;
	}

	public ProjectData getProjectData() {
		return ProjectData;
	}

	public void setProjectData(ProjectData projectData) {
		ProjectData = projectData;
	}

	public ObservationData getObservations() {
		return observations;
	}

	public void setObservations(ObservationData observations) {
		this.observations = observations;
	}

	public Map<String, List<Organization>> getOrganizationTypeLists() {
		return organizationTypeLists;
	}

	public void setOrganizationTypeLists(Map<String, List<Organization>> organizationTypeLists) {
		this.organizationTypeLists = organizationTypeLists;
	}

	public Map<String, List<Dictionary>> getDictionaryLists() {
		return dictionaryLists;
	}

	public void setDictionaryLists(Map<String, List<Dictionary>> dictionaryLists) {
		this.dictionaryLists = dictionaryLists;
	}

	public Map<String, List<Dictionary>> getFormLists() {
		return formLists;
	}

	public void setFormLists(Map<String, List<Dictionary>> formLists) {
		this.formLists = formLists;
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

	public PatientClinicalInfo getPatientClinicalProperties() {
		return patientClinicalProperties;
	}

	public void setPatientClinicalProperties(PatientClinicalInfo patientClinicalProperties) {
		this.patientClinicalProperties = patientClinicalProperties;
	}

	public SampleOrderItem getSampleOrderItems() {
		return sampleOrderItems;
	}

	public void setSampleOrderItems(SampleOrderItem sampleOrderItems) {
		this.sampleOrderItems = sampleOrderItems;
	}
}
