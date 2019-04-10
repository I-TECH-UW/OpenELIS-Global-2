package spring.generated.testconfiguration.form;

import java.util.List;

import spring.mine.common.form.BaseForm;

public class TestSectionOrderForm extends BaseForm {
	private List testSectionList;

	private String jsonChangeList = "";

	public TestSectionOrderForm() {
		setFormName("testSectionOrderForm");
	}

	public List getTestSectionList() {
		return testSectionList;
	}

	public void setTestSectionList(List testSectionList) {
		this.testSectionList = testSectionList;
	}

	public String getJsonChangeList() {
		return jsonChangeList;
	}

	public void setJsonChangeList(String jsonChangeList) {
		this.jsonChangeList = jsonChangeList;
	}
}
