package spring.generated.testconfiguration.form;

import java.util.List;

import spring.mine.common.form.BaseForm;

public class TestOrderabilityForm extends BaseForm {
	private List orderableTestList;

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
