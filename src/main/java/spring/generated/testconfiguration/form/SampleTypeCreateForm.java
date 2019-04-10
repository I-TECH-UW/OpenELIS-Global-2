package spring.generated.testconfiguration.form;

import java.util.List;

import spring.mine.common.form.BaseForm;

public class SampleTypeCreateForm extends BaseForm {
	private List existingSampleTypeList;

	private List inactiveSampleTypeList;

	private String existingEnglishNames;

	private String existingFrenchNames;

	private String sampleTypeEnglishName;

	private String sampleTypeFrenchName;

	public SampleTypeCreateForm() {
		setFormName("sampleTypeCreateForm");
	}

	public List getExistingSampleTypeList() {
		return existingSampleTypeList;
	}

	public void setExistingSampleTypeList(List existingSampleTypeList) {
		this.existingSampleTypeList = existingSampleTypeList;
	}

	public List getInactiveSampleTypeList() {
		return inactiveSampleTypeList;
	}

	public void setInactiveSampleTypeList(List inactiveSampleTypeList) {
		this.inactiveSampleTypeList = inactiveSampleTypeList;
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

	public String getSampleTypeEnglishName() {
		return sampleTypeEnglishName;
	}

	public void setSampleTypeEnglishName(String sampleTypeEnglishName) {
		this.sampleTypeEnglishName = sampleTypeEnglishName;
	}

	public String getSampleTypeFrenchName() {
		return sampleTypeFrenchName;
	}

	public void setSampleTypeFrenchName(String sampleTypeFrenchName) {
		this.sampleTypeFrenchName = sampleTypeFrenchName;
	}
}
