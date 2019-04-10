package spring.mine.dev.form;

import spring.mine.common.form.BaseForm;

public class WebTestInfoForm extends BaseForm {
	private String xmlWad = "";

	public WebTestInfoForm() {
		setFormName("webTestInfoForm");
	}

	public String getXmlWad() {
		return xmlWad;
	}

	public void setXmlWad(String xmlWad) {
		this.xmlWad = xmlWad;
	}
}
