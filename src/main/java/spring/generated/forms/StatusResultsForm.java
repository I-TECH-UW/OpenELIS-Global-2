package spring.generated.forms;

import java.lang.Boolean;
import java.lang.String;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.paging.PagingBean;

public class StatusResultsForm extends BaseForm {
  private PagingBean paging;

  private Boolean singlePatient = false;

  private String currentDate = "";

  private Timestamp lastupdated;

  private String collectionDate = "";

  private String recievedDate = "";

  private String selectedTest = "";

  private List testSelections;

  private String selectedAnalysisStatus;

  private List analysisStatusSelections;

  private String selectedSampleStatus;

  private List sampleStatusSelections;

  private Boolean displayTestMethod = true;

  private Boolean displayTestKit = true;

  private ArrayList testResult;

  private List inventoryItems;

  private Boolean searchFinished = false;

  private String logbookType = "";

  private Collection referralReasons;

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

  public String getCurrentDate() {
    return this.currentDate;
  }

  public void setCurrentDate(String currentDate) {
    this.currentDate = currentDate;
  }

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }

  public String getCollectionDate() {
    return this.collectionDate;
  }

  public void setCollectionDate(String collectionDate) {
    this.collectionDate = collectionDate;
  }

  public String getRecievedDate() {
    return this.recievedDate;
  }

  public void setRecievedDate(String recievedDate) {
    this.recievedDate = recievedDate;
  }

  public String getSelectedTest() {
    return this.selectedTest;
  }

  public void setSelectedTest(String selectedTest) {
    this.selectedTest = selectedTest;
  }

  public List getTestSelections() {
    return this.testSelections;
  }

  public void setTestSelections(List testSelections) {
    this.testSelections = testSelections;
  }

  public String getSelectedAnalysisStatus() {
    return this.selectedAnalysisStatus;
  }

  public void setSelectedAnalysisStatus(String selectedAnalysisStatus) {
    this.selectedAnalysisStatus = selectedAnalysisStatus;
  }

  public List getAnalysisStatusSelections() {
    return this.analysisStatusSelections;
  }

  public void setAnalysisStatusSelections(List analysisStatusSelections) {
    this.analysisStatusSelections = analysisStatusSelections;
  }

  public String getSelectedSampleStatus() {
    return this.selectedSampleStatus;
  }

  public void setSelectedSampleStatus(String selectedSampleStatus) {
    this.selectedSampleStatus = selectedSampleStatus;
  }

  public List getSampleStatusSelections() {
    return this.sampleStatusSelections;
  }

  public void setSampleStatusSelections(List sampleStatusSelections) {
    this.sampleStatusSelections = sampleStatusSelections;
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
