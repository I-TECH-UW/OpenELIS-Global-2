package spring.generated.testconfiguration.form;

import java.util.LinkedHashMap;
import java.util.List;

import spring.mine.common.form.BaseForm;

public class TestSectionTestAssignForm extends BaseForm {
	private List testSectionList;

	private LinkedHashMap sectionTestList;

	private String testId = "";

	private String testSectionId = "";

	private String deactivateTestSectionId = "";

	public TestSectionTestAssignForm() {
		setFormName("testSectionTestAssignForm");
	}

	public List getTestSectionList() {
		return testSectionList;
	}

	public void setTestSectionList(List testSectionList) {
		this.testSectionList = testSectionList;
	}

	public LinkedHashMap getSectionTestList() {
		return sectionTestList;
	}

	public void setSectionTestList(LinkedHashMap sectionTestList) {
		this.sectionTestList = sectionTestList;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getTestSectionId() {
		return testSectionId;
	}

	public void setTestSectionId(String testSectionId) {
		this.testSectionId = testSectionId;
	}

	public String getDeactivateTestSectionId() {
		return deactivateTestSectionId;
	}

	public void setDeactivateTestSectionId(String deactivateTestSectionId) {
		this.deactivateTestSectionId = deactivateTestSectionId;
	}
}
