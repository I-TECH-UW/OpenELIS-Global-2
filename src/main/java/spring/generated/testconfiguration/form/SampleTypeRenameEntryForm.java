package spring.generated.testconfiguration.form;

import java.util.List;

import spring.mine.common.form.BaseForm;

public class SampleTypeRenameEntryForm extends BaseForm {
	private List sampleTypeList;

	private String nameEnglish = "";

	private String nameFrench = "";

	private String sampleTypeId = "";

	public SampleTypeRenameEntryForm() {
		setFormName("sampleTypeRenameEntryForm");
	}

	public List getSampleTypeList() {
		return sampleTypeList;
	}

	public void setSampleTypeList(List sampleTypeList) {
		this.sampleTypeList = sampleTypeList;
	}

	public String getNameEnglish() {
		return nameEnglish;
	}

	public void setNameEnglish(String nameEnglish) {
		this.nameEnglish = nameEnglish;
	}

	public String getNameFrench() {
		return nameFrench;
	}

	public void setNameFrench(String nameFrench) {
		this.nameFrench = nameFrench;
	}

	public String getSampleTypeId() {
		return sampleTypeId;
	}

	public void setSampleTypeId(String sampleTypeId) {
		this.sampleTypeId = sampleTypeId;
	}
}
