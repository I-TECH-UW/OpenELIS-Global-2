package spring.generated.sample.form;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.SafeHtml;

import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.ValidationHelper;
import spring.mine.validation.annotations.ValidAccessionNumber;
import spring.mine.validation.annotations.ValidDate;
import spring.mine.validation.annotations.ValidTime;
import us.mn.state.health.lims.common.provider.validation.AccessionNumberValidatorFactory.AccessionFormat;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator.DateRelation;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.patient.action.bean.PatientClinicalInfo;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.patient.valueholder.ObservationData;
import us.mn.state.health.lims.sample.bean.SampleOrderItem;
import us.mn.state.health.lims.sample.form.ProjectData;

public class SampleEntryByProjectForm extends BaseForm {

	public interface SampleEntryByProject {
	}

	@ValidDate(relative = DateRelation.TODAY)
	private String currentDate = "";

	@ValidDate(relative = DateRelation.PAST)
	private String receivedDateForDisplay = "";

	@ValidDate(relative = DateRelation.PAST)
	private String receivedTimeForDisplay = "";

	@ValidDate(relative = DateRelation.PAST)
	private String interviewDate = "";

	@ValidTime
	private String interviewTime = "";

	@SafeHtml
	private String project = "";

	@ValidAccessionNumber(format = AccessionFormat.PROGRAM, dateValidate = true)
	private String labNo = "";

	private Timestamp lastupdated;

	@SafeHtml
	private String doctor = "";

	@NotBlank
	@Size(max = 7)
	@Pattern(regexp = ValidationHelper.PATIENT_ID_REGEX)
	private String subjectNumber = "";

	@NotBlank
	@Pattern(regexp = ValidationHelper.PATIENT_ID_REGEX)
	private String siteSubjectNumber = "";

	@NotBlank
	@Pattern(regexp = ValidationHelper.GENDER_REGEX)
	private String gender = "";

	@ValidDate(relative = DateRelation.PAST)
	private String birthDateForDisplay = "";

	@Pattern(regexp = ValidationHelper.ID_REGEX)
	private String samplePK = "";

	@Pattern(regexp = ValidationHelper.ID_REGEX)
	private String patientPK = "";

	@Pattern(regexp = "^Add$|^update$|^noAction$")
	private String patientProcessingStatus = "Add";

	@SafeHtml
	private String patientLastUpdated = "";

	@SafeHtml
	private String personLastUpdated = "";

	// TODO
	@Valid
	private ProjectData ProjectData;

	// TODO
	@Valid
	private ObservationData observations;

	// for display
	private Map<String, List<Organization>> organizationTypeLists;

	// for display
	private Map<String, List<Dictionary>> dictionaryLists;

	// for display
	private Map<String, List<Dictionary>> formLists;

	// for display
	private List<IdValuePair> genders;

	// TODO
	@Valid
	private PatientManagementInfo patientProperties;

	// for display
	private PatientSearch patientSearch;

	// TODO
	@Valid
	private PatientClinicalInfo patientClinicalProperties;

	// TODO
	@Valid
	private SampleOrderItem sampleOrderItems;

	@SafeHtml
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
