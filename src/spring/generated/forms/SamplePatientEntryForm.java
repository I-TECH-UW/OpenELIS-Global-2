package spring.generated.forms;

import java.lang.String;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.patient.action.bean.PatientClinicalInfo;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.sample.bean.SampleOrderItem;

public class SamplePatientEntryForm extends BaseForm {
  private Timestamp lastupdated;

  private String currentDate = "";

  private String project = "";

  private Collection projects;

  private String patientProcessingStatus = "Add";

  private String patientPK = "";

  private List sampleTypes;

  private String sampleXML = "";

  private PatientManagementInfo patientProperties;

  private PatientSearch patientSearch;

  private PatientClinicalInfo patientClinicalProperties;

  private SampleOrderItem sampleOrderItems;

  private Collection initialSampleConditionList;

  private Collection testSectionList;

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

  public String getPatientProcessingStatus() {
    return this.patientProcessingStatus;
  }

  public void setPatientProcessingStatus(String patientProcessingStatus) {
    this.patientProcessingStatus = patientProcessingStatus;
  }

  public String getPatientPK() {
    return this.patientPK;
  }

  public void setPatientPK(String patientPK) {
    this.patientPK = patientPK;
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
