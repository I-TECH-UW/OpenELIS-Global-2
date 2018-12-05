package spring.generated.forms;

import java.lang.Boolean;
import java.lang.String;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.paging.PagingBean;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;

public class PatientResultsForm extends BaseForm {
  private PagingBean paging;

  private Boolean singlePatient = true;

  private Timestamp lastupdated;

  private String firstName = "";

  private String lastName = "";

  private String dob = "";

  private String gender = "";

  private String st = "";

  private String subjectNumber = "";

  private String nationalId = "";

  private Boolean displayTestMethod = true;

  private Boolean displayTestKit = false;

  private ArrayList testResult;

  private List inventoryItems;

  private Boolean searchFinished = false;

  private String logbookType = "";

  private Collection referralReasons;

  private PatientSearch patientSearch;

  private Collection rejectReasons;

  private Boolean displayTestSections = false;

  private String testSectionId;

  public PagingBean getPaging() {
    return this.paging;
  }

  public void setPaging(PagingBean paging) {
    this.paging = paging;
  }

  public Boolean getSinglePatient() {
    return this.singlePatient;
  }

  public void setSinglePatient(Boolean singlePatient) {
    this.singlePatient = singlePatient;
  }

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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

  public String getSt() {
    return this.st;
  }

  public void setSt(String st) {
    this.st = st;
  }

  public String getSubjectNumber() {
    return this.subjectNumber;
  }

  public void setSubjectNumber(String subjectNumber) {
    this.subjectNumber = subjectNumber;
  }

  public String getNationalId() {
    return this.nationalId;
  }

  public void setNationalId(String nationalId) {
    this.nationalId = nationalId;
  }

  public Boolean getDisplayTestMethod() {
    return this.displayTestMethod;
  }

  public void setDisplayTestMethod(Boolean displayTestMethod) {
    this.displayTestMethod = displayTestMethod;
  }

  public Boolean getDisplayTestKit() {
    return this.displayTestKit;
  }

  public void setDisplayTestKit(Boolean displayTestKit) {
    this.displayTestKit = displayTestKit;
  }

  public ArrayList getTestResult() {
    return this.testResult;
  }

  public void setTestResult(ArrayList testResult) {
    this.testResult = testResult;
  }

  public List getInventoryItems() {
    return this.inventoryItems;
  }

  public void setInventoryItems(List inventoryItems) {
    this.inventoryItems = inventoryItems;
  }

  public Boolean getSearchFinished() {
    return this.searchFinished;
  }

  public void setSearchFinished(Boolean searchFinished) {
    this.searchFinished = searchFinished;
  }

  public String getLogbookType() {
    return this.logbookType;
  }

  public void setLogbookType(String logbookType) {
    this.logbookType = logbookType;
  }

  public Collection getReferralReasons() {
    return this.referralReasons;
  }

  public void setReferralReasons(Collection referralReasons) {
    this.referralReasons = referralReasons;
  }

  public PatientSearch getPatientSearch() {
    return this.patientSearch;
  }

  public void setPatientSearch(PatientSearch patientSearch) {
    this.patientSearch = patientSearch;
  }

  public Collection getRejectReasons() {
    return this.rejectReasons;
  }

  public void setRejectReasons(Collection rejectReasons) {
    this.rejectReasons = rejectReasons;
  }

  public Boolean getDisplayTestSections() {
    return this.displayTestSections;
  }

  public void setDisplayTestSections(Boolean displayTestSections) {
    this.displayTestSections = displayTestSections;
  }

  public String getTestSectionId() {
    return this.testSectionId;
  }

  public void setTestSectionId(String testSectionId) {
    this.testSectionId = testSectionId;
  }
}
