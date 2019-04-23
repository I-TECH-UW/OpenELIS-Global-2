package spring.mine.result.form;

import java.util.List;

import spring.mine.common.form.BaseForm;
import spring.mine.result.controller.StatusResultsController.DropPair;
import us.mn.state.health.lims.common.paging.PagingBean;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.inventory.form.InventoryKitItem;
import us.mn.state.health.lims.test.beanItems.TestResultItem;

//only used to communicate from server to client
//does not require validation
public class StatusResultsForm extends BaseForm {
	private PagingBean paging;

	private Boolean singlePatient = false;

	private String currentDate = "";

	private String collectionDate = "";

	private String recievedDate = "";

	private String selectedTest = "";

	private List<IdValuePair> testSelections;

	private String selectedAnalysisStatus;

	private List<DropPair> analysisStatusSelections;

	private String selectedSampleStatus;

	private List<DropPair> sampleStatusSelections;

	private Boolean displayTestMethod = true;

	private Boolean displayTestKit = true;

	private List<TestResultItem> testResult;

	private List<InventoryKitItem> inventoryItems;

	private List<String> hivKits;

	private List<String> syphilisKits;

	private Boolean searchFinished = false;

	private String logbookType = "";

	private List<IdValuePair> referralReasons;

	private List<IdValuePair> rejectReasons;

	private Boolean displayTestSections = false;

	private String testSectionId;

	public StatusResultsForm() {
		setFormName("StatusResultsForm");
	}

	public PagingBean getPaging() {
		return paging;
	}

	public void setPaging(PagingBean paging) {
		this.paging = paging;
	}

	public Boolean getSinglePatient() {
		return singlePatient;
	}

	public void setSinglePatient(Boolean singlePatient) {
		this.singlePatient = singlePatient;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(String collectionDate) {
		this.collectionDate = collectionDate;
	}

	public String getRecievedDate() {
		return recievedDate;
	}

	public void setRecievedDate(String recievedDate) {
		this.recievedDate = recievedDate;
	}

	public String getSelectedTest() {
		return selectedTest;
	}

	public void setSelectedTest(String selectedTest) {
		this.selectedTest = selectedTest;
	}

	public List<IdValuePair> getTestSelections() {
		return testSelections;
	}

	public void setTestSelections(List<IdValuePair> testSelections) {
		this.testSelections = testSelections;
	}

	public String getSelectedAnalysisStatus() {
		return selectedAnalysisStatus;
	}

	public void setSelectedAnalysisStatus(String selectedAnalysisStatus) {
		this.selectedAnalysisStatus = selectedAnalysisStatus;
	}

	public List<DropPair> getAnalysisStatusSelections() {
		return analysisStatusSelections;
	}

	public void setAnalysisStatusSelections(List<DropPair> analysisStatusSelections) {
		this.analysisStatusSelections = analysisStatusSelections;
	}

	public String getSelectedSampleStatus() {
		return selectedSampleStatus;
	}

	public void setSelectedSampleStatus(String selectedSampleStatus) {
		this.selectedSampleStatus = selectedSampleStatus;
	}

	public List<DropPair> getSampleStatusSelections() {
		return sampleStatusSelections;
	}

	public void setSampleStatusSelections(List<DropPair> sampleStatusSelections) {
		this.sampleStatusSelections = sampleStatusSelections;
	}

	public Boolean getDisplayTestMethod() {
		return displayTestMethod;
	}

	public void setDisplayTestMethod(Boolean displayTestMethod) {
		this.displayTestMethod = displayTestMethod;
	}

	public Boolean getDisplayTestKit() {
		return displayTestKit;
	}

	public void setDisplayTestKit(Boolean displayTestKit) {
		this.displayTestKit = displayTestKit;
	}

	public List<TestResultItem> getTestResult() {
		return testResult;
	}

	public void setTestResult(List<TestResultItem> testResult) {
		this.testResult = testResult;
	}

	public List<InventoryKitItem> getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(List<InventoryKitItem> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

	public List<String> getHivKits() {
		return hivKits;
	}

	public void setHivKits(List<String> hivKits) {
		this.hivKits = hivKits;
	}

	public List<String> getSyphilisKits() {
		return syphilisKits;
	}

	public void setSyphilisKits(List<String> syphilisKits) {
		this.syphilisKits = syphilisKits;
	}

	public Boolean getSearchFinished() {
		return searchFinished;
	}

	public void setSearchFinished(Boolean searchFinished) {
		this.searchFinished = searchFinished;
	}

	public String getLogbookType() {
		return logbookType;
	}

	public void setLogbookType(String logbookType) {
		this.logbookType = logbookType;
	}

	public List<IdValuePair> getReferralReasons() {
		return referralReasons;
	}

	public void setReferralReasons(List<IdValuePair> referralReasons) {
		this.referralReasons = referralReasons;
	}

	public List<IdValuePair> getRejectReasons() {
		return rejectReasons;
	}

	public void setRejectReasons(List<IdValuePair> rejectReasons) {
		this.rejectReasons = rejectReasons;
	}

	public Boolean getDisplayTestSections() {
		return displayTestSections;
	}

	public void setDisplayTestSections(Boolean displayTestSections) {
		this.displayTestSections = displayTestSections;
	}

	public String getTestSectionId() {
		return testSectionId;
	}

	public void setTestSectionId(String testSectionId) {
		this.testSectionId = testSectionId;
	}
}
