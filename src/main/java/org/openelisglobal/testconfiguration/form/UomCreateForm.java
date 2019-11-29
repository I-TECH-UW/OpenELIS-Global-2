package org.openelisglobal.testconfiguration.form;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.SafeHtml;
import org.openelisglobal.common.form.BaseForm;

public class UomCreateForm extends BaseForm {
    // for display
    private List existingUomList;

    // for display
    private List inactiveUomList;

    // for display
    private String existingEnglishNames;

    // for display
    private String existingFrenchNames;

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    private String uomEnglishName;

    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    private String uomFrenchName;

    public UomCreateForm() {
        setFormName("uomCreateForm");
    }

    public List getExistingUomList() {
        return existingUomList;
    }

    public void setExistingUomList(List existingUomList) {
        this.existingUomList = existingUomList;
    }

    public List getInactiveUomList() {
        return inactiveUomList;
    }

    public void setInactiveUomList(List inactiveUomList) {
        this.inactiveUomList = inactiveUomList;
    }

    public String getExistingEnglishNames() {
        return existingEnglishNames;
    }

    public void setExistingEnglishNames(String existingEnglishNames) {
        this.existingEnglishNames = existingEnglishNames;
    }

    public String getExistingFrenchNames() {
        return existingFrenchNames;
    }

    public void setExistingFrenchNames(String existingFrenchNames) {
        this.existingFrenchNames = existingFrenchNames;
    }

    public String getUomEnglishName() {
        return uomEnglishName;
    }

    public void setUomEnglishName(String uomEnglishName) {
        this.uomEnglishName = uomEnglishName;
    }

    public String getUomFrenchName() {
        return uomFrenchName;
    }

    public void setUomFrenchName(String uomFrenchName) {
        this.uomFrenchName = uomFrenchName;
    }
}
