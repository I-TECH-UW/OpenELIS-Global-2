package org.openelisglobal.testconfiguration.form;

import java.util.List;

import org.openelisglobal.common.form.BaseForm;

public class TestSectionOrderForm extends BaseForm {
	// for display
	private List testSectionList;

	// in validator
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
