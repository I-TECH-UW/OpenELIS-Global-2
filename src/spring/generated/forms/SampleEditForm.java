package spring.generated.forms;

import java.lang.Boolean;
import java.lang.String;
import java.sql.Timestamp;
import java.util.Collection;
import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.sample.bean.SampleOrderItem;

public class SampleEditForm extends BaseForm {
  private Boolean noSampleFound = Boolean.FALSE;

  private Boolean isConfirmationSample = Boolean.FALSE;

  private Boolean isEditable = Boolean.TRUE;

  private String patientName = "";

  private String dob = "";

  private String gender = "";

  private String nationalId = "";

  private String accessionNumber;

  private String newAccessionNumber = "";

  private Timestamp lastupdated;

  private Collection existingTests;

  private Collection possibleTests;

  private Boolean searchFinished = false;

  private Collection sampleTypes;

  private String maxAccessionNumber = "";

  private String sampleXML = "";

  private Collection initialSampleConditionList;

  private String currentDate = "";

  private Collection testSectionList;

  private SampleOrderItem sampleOrderItems;

  private PatientSearch patientSearch;

  private Boolean ableToCancelResults = false;

  private String warning = "";

  public Boolean getNoSampleFound() {
    return this.noSampleFound;
  }

  public void setNoSampleFound(Boolean noSampleFound) {
    this.noSampleFound = noSampleFound;
  }

  public Boolean getIsConfirmationSample() {
    return this.isConfirmationSample;
  }

  public void setIsConfirmationSample(Boolean isConfirmationSample) {
    this.isConfirmationSample = isConfirmationSample;
  }

  public Boolean getIsEditable() {
    return this.isEditable;
  }

  public void setIsEditable(Boolean isEditable) {
    this.isEditable = isEditable;
  }

  public String getPatientName() {
    return this.patientName;
  }

  public void setPatientName(String patientName) {
    this.patientName = patientName;
  }

  public String getDob() {
    return this.dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public String getGender() {
    return this.gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getNationalId() {
    return this.nationalId;
  }

  public void setNationalId(String nationalId) {
    this.nationalId = nationalId;
  }

  public String getAccessionNumber() {
    return this.accessionNumber;
  }

  public void setAccessionNumber(String accessionNumber) {
    this.accessionNumber = accessionNumber;
  }

  public String getNewAccessionNumber() {
    return this.newAccessionNumber;
  }

  public void setNewAccessionNumber(String newAccessionNumber) {
    this.newAccessionNumber = newAccessionNumber;
  }

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }

  public Collection getExistingTests() {
    return this.existingTests;
  }

  public void setExistingTests(Collection existingTests) {
    this.existingTests = existingTests;
  }

  public Collection getPossibleTests() {
    return this.possibleTests;
  }

  public void setPossibleTests(Collection possibleTests) {
    this.possibleTests = possibleTests;
  }

  public Boolean getSearchFinished() {
    return this.searchFinished;
  }

  public void setSearchFinished(Boolean searchFinished) {
    this.searchFinished = searchFinished;
  }

  public Collection getSampleTypes() {
    return this.sampleTypes;
  }

  public void setSampleTypes(Collection sampleTypes) {
    this.sampleTypes = sampleTypes;
  }

  public String getMaxAccessionNumber() {
    return this.maxAccessionNumber;
  }

  public void setMaxAccessionNumber(String maxAccessionNumber) {
    this.maxAccessionNumber = maxAccessionNumber;
  }

  public String getSampleXML() {
    return this.sampleXML;
  }

  public void setSampleXML(String sampleXML) {
    this.sampleXML = sampleXML;
  }

  public Collection getInitialSampleConditionList() {
    return this.initialSampleConditionList;
  }

  public void setInitialSampleConditionList(Collection initialSampleConditionList) {
    this.initialSampleConditionList = initialSampleConditionList;
  }

  public String getCurrentDate() {
    return this.currentDate;
  }

  public void setCurrentDate(String currentDate) {
    this.currentDate = currentDate;
  }

  public Collection getTestSectionList() {
    return this.testSectionList;
  }

  public void setTestSectionList(Collection testSectionList) {
    this.testSectionList = testSectionList;
  }

  public SampleOrderItem getSampleOrderItems() {
    return this.sampleOrderItems;
  }

  public void setSampleOrderItems(SampleOrderItem sampleOrderItems) {
    this.sampleOrderItems = sampleOrderItems;
  }

  public PatientSearch getPatientSearch() {
    return this.patientSearch;
  }

  public void setPatientSearch(PatientSearch patientSearch) {
    this.patientSearch = patientSearch;
  }

  public Boolean getAbleToCancelResults() {
    return this.ableToCancelResults;
  }

  public void setAbleToCancelResults(Boolean ableToCancelResults) {
    this.ableToCancelResults = ableToCancelResults;
  }

  public String getWarning() {
    return this.warning;
  }

  public void setWarning(String warning) {
    this.warning = warning;
  }
}
