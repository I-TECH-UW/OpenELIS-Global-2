package spring.unused.patienttype.form;

import spring.mine.common.form.BaseForm;

public class PatientTypeForm extends BaseForm {
	private String id = "";

	private String type = "";

	private String description = "";

	public PatientTypeForm() {
		setFormName("patientTypeForm");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
