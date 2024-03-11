package org.openelisglobal.dictionary.form;

import java.util.Collection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.openelisglobal.validation.annotations.SafeHtml;
import org.hibernate.validator.constraints.Length;
import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.common.validator.ValidationHelper;
import org.openelisglobal.dictionarycategory.valueholder.DictionaryCategory;

public class DictionaryForm extends BaseForm {

    @Pattern(regexp = ValidationHelper.ID_REGEX)
    private String id = "";

    @NotBlank
    @Pattern(regexp = ValidationHelper.ID_REGEX)
    private String selectedDictionaryCategoryId = "";

    // for display
    private DictionaryCategory dictionaryCategory;

    // for display
    private Collection categories;

    @NotBlank
    @Pattern(regexp = ValidationHelper.YES_NO_REGEX)
    private String isActive = "";

    @NotBlank
    @SafeHtml(level = SafeHtml.SafeListLevel.NONE)
    private String dictEntry = "";

    @NotBlank
    @SafeHtml(level = SafeHtml.SafeListLevel.NONE)
    @Length(max=10)
    private String localAbbreviation = "";

    // in validator
    private String dirtyFormFields = "";

    public DictionaryForm() {
        setFormName("dictionaryForm");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSelectedDictionaryCategoryId() {
        return selectedDictionaryCategoryId;
    }

    public void setSelectedDictionaryCategoryId(String selectedDictionaryCategoryId) {
        this.selectedDictionaryCategoryId = selectedDictionaryCategoryId;
    }

    public DictionaryCategory getDictionaryCategory() {
        return dictionaryCategory;
    }

    public void setDictionaryCategory(DictionaryCategory dictionaryCategory) {
        this.dictionaryCategory = dictionaryCategory;
    }

    public Collection getCategories() {
        return categories;
    }

    public void setCategories(Collection categories) {
        this.categories = categories;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getDictEntry() {
        return dictEntry;
    }

    public void setDictEntry(String dictEntry) {
        this.dictEntry = dictEntry;
    }

    public String getLocalAbbreviation() {
        return localAbbreviation;
    }

    public void setLocalAbbreviation(String localAbbreviation) {
        this.localAbbreviation = localAbbreviation;
    }

    public String getDirtyFormFields() {
        return dirtyFormFields;
    }

    public void setDirtyFormFields(String dirtyFormFields) {
        this.dirtyFormFields = dirtyFormFields;
    }

}
