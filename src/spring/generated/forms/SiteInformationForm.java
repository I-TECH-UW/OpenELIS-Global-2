package spring.generated.forms;

import java.lang.Boolean;
import java.lang.String;
import java.sql.Timestamp;
import java.util.Collection;
import spring.mine.common.form.BaseForm;

public class SiteInformationForm extends BaseForm {
  private String paramName = "";

  private String description = "";

  private String value = "";

  private boolean encrypted;

  private String valueType = "text";

  private Timestamp lastupdated;

  private String siteInfoDomainName = "SiteInformation";

  private Collection dictionaryValues;

  private Boolean editable = Boolean.TRUE;

  private String tag = "";

  private String descriptionKey = "";

  private String englishValue = "";

  private String frenchValue = "";

  public String getParamName() {
    return this.paramName;
  }

  public void setParamName(String paramName) {
    this.paramName = paramName;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getValue() {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public boolean isEncrypted() {
    return this.encrypted;
  }

  public void setEncrypted(boolean encrypted) {
    this.encrypted = encrypted;
  }

  public String getValueType() {
    return this.valueType;
  }

  public void setValueType(String valueType) {
    this.valueType = valueType;
  }

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }

  public String getSiteInfoDomainName() {
    return this.siteInfoDomainName;
  }

  public void setSiteInfoDomainName(String siteInfoDomainName) {
    this.siteInfoDomainName = siteInfoDomainName;
  }

  public Collection getDictionaryValues() {
    return this.dictionaryValues;
  }

  public void setDictionaryValues(Collection dictionaryValues) {
    this.dictionaryValues = dictionaryValues;
  }

  public Boolean getEditable() {
    return this.editable;
  }

  public void setEditable(Boolean editable) {
    this.editable = editable;
  }

  public String getTag() {
    return this.tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getDescriptionKey() {
    return this.descriptionKey;
  }

  public void setDescriptionKey(String descriptionKey) {
    this.descriptionKey = descriptionKey;
  }

  public String getEnglishValue() {
    return this.englishValue;
  }

  public void setEnglishValue(String englishValue) {
    this.englishValue = englishValue;
  }

  public String getFrenchValue() {
    return this.frenchValue;
  }

  public void setFrenchValue(String frenchValue) {
    this.frenchValue = frenchValue;
  }
}
