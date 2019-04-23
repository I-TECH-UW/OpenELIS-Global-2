package spring.mine.siteinformation.form;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.SafeHtml;

import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.ValidationHelper;

public class SiteInformationForm extends BaseForm {
	@NotNull
	@SafeHtml
	private String paramName = "";

	@NotNull
	@SafeHtml
	private String description = "";

	@NotNull
	@SafeHtml
	private String value = "";

	// what about this is encrypted?
	private boolean encrypted;

	// in validator
	@SafeHtml
	private String valueType = "text";

	// in validator
	@SafeHtml
	private String siteInfoDomainName;

	// for display
	private List<String> dictionaryValues;

	@NotNull
	private Boolean editable = Boolean.TRUE;

	// in validator
	@SafeHtml
	private String tag = "";

	@Pattern(regexp = ValidationHelper.MESSAGE_KEY_REGEX)
	private String descriptionKey = "";

	@SafeHtml
	private String englishValue = "";

	@SafeHtml
	private String frenchValue = "";

	public SiteInformationForm() {
		setFormName("siteInformationForm");
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
