package org.openelisglobal.testconfiguration.form;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.SafeHtml;
import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.common.validator.ValidationHelper;

public class TestSectionRenameEntryForm extends BaseForm {
    // for display
    private List testSectionList;

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    private String nameEnglish = "";

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    private String nameFrench = "";

    @NotBlank
    @Pattern(regexp = ValidationHelper.ID_REGEX)
    private String testSectionId = "";

    public TestSectionRenameEntryForm() {
        setFormName("testSectionRenameEntryForm");
    }

    public List getTestSectionList() {
        return testSectionList;
    }

    public void setTestSectionList(List testSectionList) {
        this.testSectionList = testSectionList;
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

    public String getTestSectionId() {
        return testSectionId;
    }

    public void setTestSectionId(String testSectionId) {
        this.testSectionId = testSectionId;
    }
}
