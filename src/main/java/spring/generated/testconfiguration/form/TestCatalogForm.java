package spring.generated.testconfiguration.form;

import java.util.List;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestCatalog;

public class TestCatalogForm extends BaseForm {

	// for display
	private List<Test> testList;

	// for display
	private List<String> testSectionList;

	// for display
	private List<TestCatalog> testCatalogList;

	public TestCatalogForm() {
		setFormName("testCatalogForm");
	}

	public List<Test> getTestList() {
		return testList;
	}

	public void setTestList(List<Test> testList) {
		this.testList = testList;
	}

	public List<String> getTestSectionList() {
		return testSectionList;
	}

	public void setTestSectionList(List<String> testSectionList) {
		this.testSectionList = testSectionList;
	}

	public List<TestCatalog> getTestCatalogList() {
		return testCatalogList;
	}

	public void setTestCatalogList(List<TestCatalog> testCatalogList) {
		this.testCatalogList = testCatalogList;
	}
}
