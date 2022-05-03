package org.openelisglobal.testconfiguration.form;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.SafeHtml;
import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.common.validator.ValidationHelper;

public class MethodRenameEntryForm extends BaseForm {
    // for display
    private List methodList;

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    private String nameEnglish = "";

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    private String nameFrench = "";

    @NotBlank
    @Pattern(regexp = ValidationHelper.ID_REGEX)
    private String methodId = "";

    public MethodRenameEntryForm() {
        setFormName("methodRenameEntryForm");
    }

    public List getMethodList() {
        return methodList;
    }

    public void setMethodList(List methodList) {
        this.methodList = methodList;
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

    public String getMethodId() {
        return methodId;
    }

    public void setMethodSectionId(String methodId) {
        this.methodId = methodId;
    }
}
