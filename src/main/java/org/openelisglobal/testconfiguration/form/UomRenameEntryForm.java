package org.openelisglobal.testconfiguration.form;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.SafeHtml;
import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.common.validator.ValidationHelper;

public class UomRenameEntryForm extends BaseForm {
    // for display
    private List uomList;

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    private String nameEnglish = "";

    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    private String nameFrench = "";

    @NotBlank
    @Pattern(regexp = ValidationHelper.ID_REGEX)
    private String uomId = "";

    public UomRenameEntryForm() {
        setFormName("uomRenameEntryForm");
    }

    public List getUomList() {
        return uomList;
    }

    public void setUomList(List uomList) {
        this.uomList = uomList;
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

    public String getUomId() {
        return uomId;
    }

    public void setUomId(String uomId) {
        this.uomId = uomId;
    }
}
