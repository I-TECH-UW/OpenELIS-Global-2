package spring.generated.testconfiguration.form;

import java.util.List;

import spring.mine.common.form.BaseForm;

public class TestSectionRenameEntryForm extends BaseForm {
	private List testSectionList;

	private String nameEnglish = "";

	private String nameFrench = "";

	private String testSectionId = "";

	public TestSectionRenameEntryForm() {
		setFormName("testSectionRenameEntryForm");
	}

	public List getTestSectionList() {
		return testSectionList;
	}

	public void setTestSectionList(List testSectionList) {
		this.testSectionList = testSectionList;
	}

	public String getNameEnglish() {
		return nameEnglish;
	}

	public void setNameEnglish(String nameEnglish) {
		this.nameEnglish = nameEnglish;
	}

	public String getNameFrench() {
		return nameFrench;
	}

	public void setNameFrench(String nameFrench) {
		this.nameFrench = nameFrench;
	}

	public String getTestSectionId() {
		return testSectionId;
	}

	public void setTestSectionId(String testSectionId) {
		this.testSectionId = testSectionId;
	}
}
