package org.openelisglobal.testconfiguration.form;

import java.util.List;

import org.openelisglobal.common.form.BaseForm;

public class TestOrderabilityForm extends BaseForm {
	// for display
	private List orderableTestList;

	// in validator
	private String jsonChangeList = "";

	public TestOrderabilityForm() {
		setFormName("testOrderabilityForm");
	}

	public List getOrderableTestList() {
		return orderableTestList;
	}

	public void setOrderableTestList(List orderableTestList) {
		this.orderableTestList = orderableTestList;
	}

	public String getJsonChangeList() {
		return jsonChangeList;
	}

	public void setJsonChangeList(String jsonChangeList) {
		this.jsonChangeList = jsonChangeList;
	}
}
