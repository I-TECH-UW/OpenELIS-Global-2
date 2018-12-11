package spring.generated.forms;

import java.lang.Boolean;
import java.lang.String;
import java.sql.Timestamp;
import java.util.List;
import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.paging.PagingBean;

public class ResultValidationForm extends BaseForm {
  private PagingBean paging;

  private Timestamp lastupdated;

  private String currentDate = "";

  private List resultList;

  private String testSection = "";

  private String testName = "";

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

  public List getResultList() {
    return this.resultList;
  }

  public void setResultList(List resultList) {
    this.resultList = resultList;
  }

  public String getTestSection() {
    return this.testSection;
  }

  public void setTestSection(String testSection) {
    this.testSection = testSection;
  }

  public String getTestName() {
    return this.testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
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
