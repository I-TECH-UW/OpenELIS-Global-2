package org.openelisglobal.testconfiguration.form;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.SafeHtml;
import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.common.validator.ValidationHelper;

public class SampleTypeRenameEntryForm extends BaseForm {
    // for display
    private List sampleTypeList;

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    private String nameEnglish = "";

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    private String nameFrench = "";

    @NotBlank
    @Pattern(regexp = ValidationHelper.ID_REGEX)
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
