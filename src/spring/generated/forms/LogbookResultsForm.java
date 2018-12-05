package spring.generated.forms;

import java.lang.Boolean;
import java.lang.String;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.paging.PagingBean;

public class LogbookResultsForm extends BaseForm {
  private PagingBean paging;

  private Boolean singlePatient = false;

  private String currentDate = "";

  private Timestamp lastupdated;

  private Boolean displayTestMethod = true;

  private Boolean displayTestKit = true;

  private ArrayList testResult;

  private ArrayList inventoryItems;

  private String logbookType = "";

  private Collection referralReasons;

  private Collection rejectReasons;

  private List testSections;

  private List testSectionsByName;

  private String testSectionId;

  private Boolean displayTestSections = true;

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

  public ArrayList getInventoryItems() {
    return this.inventoryItems;
  }

  public void setInventoryItems(ArrayList inventoryItems) {
    this.inventoryItems = inventoryItems;
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

  public List getTestSections() {
    return this.testSections;
  }

  public void setTestSections(List testSections) {
    this.testSections = testSections;
  }

  public List getTestSectionsByName() {
    return this.testSectionsByName;
  }

  public void setTestSectionsByName(List testSectionsByName) {
    this.testSectionsByName = testSectionsByName;
  }

  public String getTestSectionId() {
    return this.testSectionId;
  }

  public void setTestSectionId(String testSectionId) {
    this.testSectionId = testSectionId;
  }

  public Boolean getDisplayTestSections() {
    return this.displayTestSections;
  }

  public void setDisplayTestSections(Boolean displayTestSections) {
    this.displayTestSections = displayTestSections;
  }
}
