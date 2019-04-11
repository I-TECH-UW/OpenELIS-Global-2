package spring.mine.result.form;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.ValidationHelper;
import spring.mine.validation.annotations.ValidDate;
import us.mn.state.health.lims.common.paging.PagingBean;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator.DateRelation;
import us.mn.state.health.lims.inventory.form.InventoryKitItem;
import us.mn.state.health.lims.test.beanItems.TestResultItem;

public class LogbookResultsForm extends BaseForm {
	// for display
	private PagingBean paging;

	@NotNull
	private Boolean singlePatient = false;

	@ValidDate(relative = DateRelation.TODAY)
	private String currentDate = "";

	private Timestamp lastupdated;

	@NotNull
	private Boolean displayTestMethod = true;

	@NotNull
	private Boolean displayTestKit = true;

	// TODO
	@Valid
	private List<TestResultItem> testResult;

	// TODO
	@Valid
	private List<InventoryKitItem> inventoryItems;

	// for display
	private List<String> hivKits;

	// for display
	private List<String> syphilisKits;

	// for display
	private String logbookType = "";

	// for display
	private List<IdValuePair> referralReasons;

	// for display
	private List<IdValuePair> rejectReasons;

	// for display
	private List<IdValuePair> testSections;

	// for display
	private List<IdValuePair> testSectionsByName;

	@NotBlank
	@Pattern(regexp = ValidationHelper.ID_REGEX)
	private String testSectionId;

	@NotNull
	private Boolean displayTestSections = true;

	public LogbookResultsForm() {
		setFormName("LogbookResultsForm");
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

	public Timestamp getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
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

	public Boolean getDisplayTestSections() {
		return displayTestSections;
	}

	public void setDisplayTestSections(Boolean displayTestSections) {
		this.displayTestSections = displayTestSections;
	}
}
