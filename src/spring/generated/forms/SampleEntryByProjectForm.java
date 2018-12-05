package spring.generated.forms;

import java.lang.String;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import spring.mine.common.form.BaseForm;
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

  private Collection projects;

  private String labNo = "";

  private Timestamp lastupdated;

  private String doctor = "";

  private String subjectNumber = "";

  private String siteSubjectNumber = "";

  private String gender = "";

  private Collection genders;

  private String birthDateForDisplay = "";

  private String samplePK = "";

  private String patientPK = "";

  private String patientProcessingStatus = "Add";

  private String patientLastUpdated = "";

  private String personLastUpdated = "";

  private ProjectData ProjectData;

  private ObservationData observations;

  private Map organizationTypeLists;

  private Map dictionaryLists;

  private Map formLists;

  private List sampleTypes;

  private String sampleXML = "";

  private PatientManagementInfo patientProperties;

  private PatientSearch patientSearch;

  private PatientClinicalInfo patientClinicalProperties;

  private SampleOrderItem sampleOrderItems;

  private Collection initialSampleConditionList;

  private Collection testSectionList;

  public String getCurrentDate() {
    return this.currentDate;
  }

  public void setCurrentDate(String currentDate) {
    this.currentDate = currentDate;
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

  public String getInterviewDate() {
    return this.interviewDate;
  }

  public void setInterviewDate(String interviewDate) {
    this.interviewDate = interviewDate;
  }

  public String getInterviewTime() {
    return this.interviewTime;
  }

  public void setInterviewTime(String interviewTime) {
    this.interviewTime = interviewTime;
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

  public String getLabNo() {
    return this.labNo;
  }

  public void setLabNo(String labNo) {
    this.labNo = labNo;
  }

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }

  public String getDoctor() {
    return this.doctor;
  }

  public void setDoctor(String doctor) {
    this.doctor = doctor;
  }

  public String getSubjectNumber() {
    return this.subjectNumber;
  }

  public void setSubjectNumber(String subjectNumber) {
    this.subjectNumber = subjectNumber;
  }

  public String getSiteSubjectNumber() {
    return this.siteSubjectNumber;
  }

  public void setSiteSubjectNumber(String siteSubjectNumber) {
    this.siteSubjectNumber = siteSubjectNumber;
  }

  public String getGender() {
    return this.gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Collection getGenders() {
    return this.genders;
  }

  public void setGenders(Collection genders) {
    this.genders = genders;
  }

  public String getBirthDateForDisplay() {
    return this.birthDateForDisplay;
  }

  public void setBirthDateForDisplay(String birthDateForDisplay) {
    this.birthDateForDisplay = birthDateForDisplay;
  }

  public String getSamplePK() {
    return this.samplePK;
  }

  public void setSamplePK(String samplePK) {
    this.samplePK = samplePK;
  }

  public String getPatientPK() {
    return this.patientPK;
  }

  public void setPatientPK(String patientPK) {
    this.patientPK = patientPK;
  }

  public String getPatientProcessingStatus() {
    return this.patientProcessingStatus;
  }

  public void setPatientProcessingStatus(String patientProcessingStatus) {
    this.patientProcessingStatus = patientProcessingStatus;
  }

  public String getPatientLastUpdated() {
    return this.patientLastUpdated;
  }

  public void setPatientLastUpdated(String patientLastUpdated) {
    this.patientLastUpdated = patientLastUpdated;
  }

  public String getPersonLastUpdated() {
    return this.personLastUpdated;
  }

  public void setPersonLastUpdated(String personLastUpdated) {
    this.personLastUpdated = personLastUpdated;
  }

  public ProjectData getProjectData() {
    return this.ProjectData;
  }

  public void setProjectData(ProjectData ProjectData) {
    this.ProjectData = ProjectData;
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

  public Map getDictionaryLists() {
    return this.dictionaryLists;
  }

  public void setDictionaryLists(Map dictionaryLists) {
    this.dictionaryLists = dictionaryLists;
  }

  public Map getFormLists() {
    return this.formLists;
  }

  public void setFormLists(Map formLists) {
    this.formLists = formLists;
  }

  public List getSampleTypes() {
    return this.sampleTypes;
  }

  public void setSampleTypes(List sampleTypes) {
    this.sampleTypes = sampleTypes;
  }

  public String getSampleXML() {
    return this.sampleXML;
  }

  public void setSampleXML(String sampleXML) {
    this.sampleXML = sampleXML;
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

  public PatientClinicalInfo getPatientClinicalProperties() {
    return this.patientClinicalProperties;
  }

  public void setPatientClinicalProperties(PatientClinicalInfo patientClinicalProperties) {
    this.patientClinicalProperties = patientClinicalProperties;
  }

  public SampleOrderItem getSampleOrderItems() {
    return this.sampleOrderItems;
  }

  public void setSampleOrderItems(SampleOrderItem sampleOrderItems) {
    this.sampleOrderItems = sampleOrderItems;
  }

  public Collection getInitialSampleConditionList() {
    return this.initialSampleConditionList;
  }

  public void setInitialSampleConditionList(Collection initialSampleConditionList) {
    this.initialSampleConditionList = initialSampleConditionList;
  }

  public Collection getTestSectionList() {
    return this.testSectionList;
  }

  public void setTestSectionList(Collection testSectionList) {
    this.testSectionList = testSectionList;
  }
}
