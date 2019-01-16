package spring.mine.workplan.form;

import java.sql.Timestamp;
import java.util.List;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.paging.PagingBean;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.resultvalidation.bean.AnalysisItem;
import us.mn.state.health.lims.test.beanItems.TestResultItem;

public class WorkplanForm extends BaseForm {
	private PagingBean paging;

	private Timestamp lastupdated;

	private String currentDate = "";

	private String searchLabel;

	private List<IdValuePair> searchTypes;

	private String selectedSearchID = "";

	private String testTypeID = "";

	private String testName = "";

	private Boolean searchFinished = false;

	private List<TestResultItem> workplanTests;

	private List<AnalysisItem> resultList;

	private String workplanType = "";

	private String searchAction = "";

	private List<IdValuePair> testSections;

	private List<IdValuePair> testSectionsByName;

	private String testSectionId;

	public WorkplanForm() {
		setFormName("WorkplanForm");
	}

	public PagingBean getPaging() {
		return paging;
	}

	public void setPaging(PagingBean paging) {
		this.paging = paging;
	}

	public Timestamp getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getSearchLabel() {
		return searchLabel;
	}

	public void setSearchLabel(String searchLabel) {
		this.searchLabel = searchLabel;
	}

	public List<IdValuePair> getSearchTypes() {
		return searchTypes;
	}

	public void setSearchTypes(List<IdValuePair> searchTypes) {
		this.searchTypes = searchTypes;
	}

	public String getSelectedSearchID() {
		return selectedSearchID;
	}

	public void setSelectedSearchID(String selectedSearchID) {
		this.selectedSearchID = selectedSearchID;
	}

	public String getTestTypeID() {
		return testTypeID;
	}

	public void setTestTypeID(String testTypeID) {
		this.testTypeID = testTypeID;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Boolean getSearchFinished() {
		return searchFinished;
	}

	public void setSearchFinished(Boolean searchFinished) {
		this.searchFinished = searchFinished;
	}

	public List<TestResultItem> getWorkplanTests() {
		return workplanTests;
	}

	public void setWorkplanTests(List<TestResultItem> workplanTests) {
		this.workplanTests = workplanTests;
	}

	public List<AnalysisItem> getResultList() {
		return resultList;
	}

	public void setResultList(List<AnalysisItem> resultList) {
		this.resultList = resultList;
	}

	public String getWorkplanType() {
		return workplanType;
	}

	public void setWorkplanType(String workplanType) {
		this.workplanType = workplanType;
	}

	public String getSearchAction() {
		return searchAction;
	}

	public void setSearchAction(String searchAction) {
		this.searchAction = searchAction;
	}

	public List<IdValuePair> getTestSections() {
		return testSections;
	}

	public void setTestSections(List<IdValuePair> testSections) {
		this.testSections = testSections;
	}

	public List<IdValuePair> getTestSectionsByName() {
		return testSectionsByName;
	}

	public void setTestSectionsByName(List<IdValuePair> testSectionsByName) {
		this.testSectionsByName = testSectionsByName;
	}

	public String getTestSectionId() {
		return testSectionId;
	}

	public void setTestSectionId(String testSectionId) {
		this.testSectionId = testSectionId;
	}
}
