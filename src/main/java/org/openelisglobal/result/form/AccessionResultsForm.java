package org.openelisglobal.result.form;

import java.util.List;

import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.common.paging.PagingBean;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.inventory.form.InventoryKitItem;
import org.openelisglobal.test.beanItems.TestResultItem;

//no validation, inly used to send info from server to client
public class AccessionResultsForm extends BaseForm {
	private PagingBean paging;

	private Boolean singlePatient = true;

	private String firstName = "";

	private String lastName = "";

	private String dob = "";

	private String gender = "";

	private String st = "";

	private String subjectNumber = "";

	private String nationalId = "";

	private Boolean displayTestMethod = true;

	private Boolean displayTestKit = false;

	private List<TestResultItem> testResult;

	private List<InventoryKitItem> inventoryItems;

	private List<String> hivKits;

	private List<String> syphilisKits;

	private Boolean searchFinished = false;

	private String logbookType = "";

	private List<IdValuePair> referralReasons;

	private List<IdValuePair> rejectReasons;

	private List<IdValuePair> testSections;

	private List<IdValuePair> testSectionsByName;

	private String testSectionId;

	private Boolean displayTestSections = false;

	public AccessionResultsForm() {
		setFormName("AccessionResultsForm");
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}

	public String getSubjectNumber() {
		return subjectNumber;
	}

	public void setSubjectNumber(String subjectNumber) {
		this.subjectNumber = subjectNumber;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
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
