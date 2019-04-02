package spring.mine.siteinformation.form;

import java.util.List;

import spring.mine.common.form.BaseForm;

public class SiteInformationForm extends BaseForm {
	private String paramName = "";

	private String description = "";

	private String value = "";

	private boolean encrypted;

	private String valueType = "text";

	private String siteInfoDomainName;

	private List<String> dictionaryValues;

	private Boolean editable = Boolean.TRUE;

	private String tag = "";

	private String descriptionKey = "";

	private String englishValue = "";

	private String frenchValue = "";

	public SiteInformationForm() {
		// setFormName("siteInformationForm");
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isEncrypted() {
		return encrypted;
	}

	public void setEncrypted(boolean encrypted) {
		this.encrypted = encrypted;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getSiteInfoDomainName() {
		return siteInfoDomainName;
	}

	public void setSiteInfoDomainName(String siteInfoDomainName) {
		this.siteInfoDomainName = siteInfoDomainName;
	}

	public List<String> getDictionaryValues() {
		return dictionaryValues;
	}

	public void setDictionaryValues(List<String> dictionaryValues) {
		this.dictionaryValues = dictionaryValues;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDescriptionKey() {
		return descriptionKey;
	}

	public void setDescriptionKey(String descriptionKey) {
		this.descriptionKey = descriptionKey;
	}

	public String getEnglishValue() {
		return englishValue;
	}

	public void setEnglishValue(String englishValue) {
		this.englishValue = englishValue;
	}

	public String getFrenchValue() {
		return frenchValue;
	}

	public void setFrenchValue(String frenchValue) {
		this.frenchValue = frenchValue;
	}
}
