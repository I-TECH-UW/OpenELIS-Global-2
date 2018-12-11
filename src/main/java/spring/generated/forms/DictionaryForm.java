package spring.generated.forms;

import java.lang.String;
import java.sql.Timestamp;
import java.util.Collection;
import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.dictionarycategory.valueholder.DictionaryCategory;

public class DictionaryForm extends BaseForm {
  private String id = "";

  private String selectedDictionaryCategoryId = "";

  private DictionaryCategory dictionaryCategory;

  private Collection categories;

  private String isActive = "";

  private String dictEntry = "";

  private String localAbbreviation = "";

  private Timestamp lastupdated;

  private String dirtyFormFields = "";

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSelectedDictionaryCategoryId() {
    return this.selectedDictionaryCategoryId;
  }

  public void setSelectedDictionaryCategoryId(String selectedDictionaryCategoryId) {
    this.selectedDictionaryCategoryId = selectedDictionaryCategoryId;
  }

  public DictionaryCategory getDictionaryCategory() {
    return this.dictionaryCategory;
  }

  public void setDictionaryCategory(DictionaryCategory dictionaryCategory) {
    this.dictionaryCategory = dictionaryCategory;
  }

  public Collection getCategories() {
    return this.categories;
  }

  public void setCategories(Collection categories) {
    this.categories = categories;
  }

  public String getIsActive() {
    return this.isActive;
  }

  public void setIsActive(String isActive) {
    this.isActive = isActive;
  }

  public String getDictEntry() {
    return this.dictEntry;
  }

  public void setDictEntry(String dictEntry) {
    this.dictEntry = dictEntry;
  }

  public String getLocalAbbreviation() {
    return this.localAbbreviation;
  }

  public void setLocalAbbreviation(String localAbbreviation) {
    this.localAbbreviation = localAbbreviation;
  }

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }

  public String getDirtyFormFields() {
    return this.dirtyFormFields;
  }

  public void setDirtyFormFields(String dirtyFormFields) {
    this.dirtyFormFields = dirtyFormFields;
  }
}
