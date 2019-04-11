package spring.generated.testconfiguration.form;

import java.util.List;

import spring.mine.common.form.BaseForm;

public class SampleTypeOrderForm extends BaseForm {
	// for display
	private List sampleTypeList;

	// in validator
	private String jsonChangeList = "";

	public SampleTypeOrderForm() {
		setFormName("sampleTypeOrderForm");
	}

	public List getSampleTypeList() {
		return sampleTypeList;
	}

	public void setSampleTypeList(List sampleTypeList) {
		this.sampleTypeList = sampleTypeList;
	}

	public String getJsonChangeList() {
		return jsonChangeList;
	}

	public void setJsonChangeList(String jsonChangeList) {
		this.jsonChangeList = jsonChangeList;
	}
}
