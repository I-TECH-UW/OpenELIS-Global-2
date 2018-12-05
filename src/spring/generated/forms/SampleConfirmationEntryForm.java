package spring.generated.forms;

import java.lang.String;
import java.sql.Timestamp;
import java.util.Collection;
import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.sample.bean.SampleOrderItem;

public class SampleConfirmationEntryForm extends BaseForm {
  private Timestamp lastupdated;

  private String interviewDate = "";

  private String interviewTime = "";

  private String currentDate = "";

  private String patientLastUpdated = "";

  private String personLastUpdated = "";

  private Collection confirmationItems;

  private Collection sampleTypes;

  private String patientPK = "";

  private PatientManagementInfo patientProperties;

  private PatientSearch patientSearch;

  private Collection requestingOrganizationList;

  private String requestAsXML;

  private Collection initialSampleConditionList;

  private SampleOrderItem sampleOrderItems;

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
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

  public String getCurrentDate() {
    return this.currentDate;
  }

  public void setCurrentDate(String currentDate) {
    this.currentDate = currentDate;
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

  public Collection getConfirmationItems() {
    return this.confirmationItems;
  }

  public void setConfirmationItems(Collection confirmationItems) {
    this.confirmationItems = confirmationItems;
  }

  public Collection getSampleTypes() {
    return this.sampleTypes;
  }

  public void setSampleTypes(Collection sampleTypes) {
    this.sampleTypes = sampleTypes;
  }

  public String getPatientPK() {
    return this.patientPK;
  }

  public void setPatientPK(String patientPK) {
    this.patientPK = patientPK;
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

  public Collection getRequestingOrganizationList() {
    return this.requestingOrganizationList;
  }

  public void setRequestingOrganizationList(Collection requestingOrganizationList) {
    this.requestingOrganizationList = requestingOrganizationList;
  }

  public String getRequestAsXML() {
    return this.requestAsXML;
  }

  public void setRequestAsXML(String requestAsXML) {
    this.requestAsXML = requestAsXML;
  }

  public Collection getInitialSampleConditionList() {
    return this.initialSampleConditionList;
  }

  public void setInitialSampleConditionList(Collection initialSampleConditionList) {
    this.initialSampleConditionList = initialSampleConditionList;
  }

  public SampleOrderItem getSampleOrderItems() {
    return this.sampleOrderItems;
  }

  public void setSampleOrderItems(SampleOrderItem sampleOrderItems) {
    this.sampleOrderItems = sampleOrderItems;
  }
}
