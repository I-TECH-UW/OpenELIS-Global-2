package spring.generated.forms;

import java.lang.Boolean;
import java.lang.String;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.paging.PagingBean;

public class WorkplanForm extends BaseForm {
  private PagingBean paging;

  private Timestamp lastupdated;

  private String currentDate = "";

  private String searchLabel;

  private Collection searchTypes;

  private String selectedSearchID = "";

  private String testTypeID = "";

  private String testName = "";

  private Boolean searchFinished = false;

  private ArrayList workplanTests;

  private ArrayList resultList;

  private String workplanType = "";

  private String searchAction = "";

  private List testSections;

  private List testSectionsByName;

  private String testSectionId;

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

  public String getSearchLabel() {
    return this.searchLabel;
  }

  public void setSearchLabel(String searchLabel) {
    this.searchLabel = searchLabel;
  }

  public Collection getSearchTypes() {
    return this.searchTypes;
  }

  public void setSearchTypes(Collection searchTypes) {
    this.searchTypes = searchTypes;
  }

  public String getSelectedSearchID() {
    return this.selectedSearchID;
  }

  public void setSelectedSearchID(String selectedSearchID) {
    this.selectedSearchID = selectedSearchID;
  }

  public String getTestTypeID() {
    return this.testTypeID;
  }

  public void setTestTypeID(String testTypeID) {
    this.testTypeID = testTypeID;
  }

  public String getTestName() {
    return this.testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }

  public Boolean getSearchFinished() {
    return this.searchFinished;
  }

  public void setSearchFinished(Boolean searchFinished) {
    this.searchFinished = searchFinished;
  }

  public ArrayList getWorkplanTests() {
    return this.workplanTests;
  }

  public void setWorkplanTests(ArrayList workplanTests) {
    this.workplanTests = workplanTests;
  }

  public ArrayList getResultList() {
    return this.resultList;
  }

  public void setResultList(ArrayList resultList) {
    this.resultList = resultList;
  }

  public String getWorkplanType() {
    return this.workplanType;
  }

  public void setWorkplanType(String workplanType) {
    this.workplanType = workplanType;
  }

  public String getSearchAction() {
    return this.searchAction;
  }

  public void setSearchAction(String searchAction) {
    this.searchAction = searchAction;
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
}
