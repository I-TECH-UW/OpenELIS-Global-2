package spring.generated.testconfiguration.form;

import java.util.List;

import spring.mine.common.form.BaseForm;

public class UomCreateForm extends BaseForm {
	private List existingUomList;

	private List inactiveUomList;

	private String existingEnglishNames;

	private String existingFrenchNames;

	private String uomEnglishName;

	private String uomFrenchName;

	public UomCreateForm() {
		setFormName("uomCreateForm");
	}

	public List getExistingUomList() {
		return existingUomList;
	}

	public void setExistingUomList(List existingUomList) {
		this.existingUomList = existingUomList;
	}

	public List getInactiveUomList() {
		return inactiveUomList;
	}

	public void setInactiveUomList(List inactiveUomList) {
		this.inactiveUomList = inactiveUomList;
	}

	public String getExistingEnglishNames() {
		return existingEnglishNames;
	}

	public void setExistingEnglishNames(String existingEnglishNames) {
		this.existingEnglishNames = existingEnglishNames;
	}

	public String getExistingFrenchNames() {
		return existingFrenchNames;
	}

	public void setExistingFrenchNames(String existingFrenchNames) {
		this.existingFrenchNames = existingFrenchNames;
	}

	public String getUomEnglishName() {
		return uomEnglishName;
	}

	public void setUomEnglishName(String uomEnglishName) {
		this.uomEnglishName = uomEnglishName;
	}

	public String getUomFrenchName() {
		return uomFrenchName;
	}

	public void setUomFrenchName(String uomFrenchName) {
		this.uomFrenchName = uomFrenchName;
	}
}
