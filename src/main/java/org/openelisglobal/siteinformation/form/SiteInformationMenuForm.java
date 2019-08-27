package org.openelisglobal.siteinformation.form;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.openelisglobal.common.form.MenuForm;
import org.openelisglobal.common.validator.ValidationHelper;

public class SiteInformationMenuForm extends MenuForm {
    // for display
    private List menuList;

    private List<@Pattern(regexp = ValidationHelper.ID_REGEX) String> selectedIDs;

    // in validator
    private String siteInfoDomainName;// = "SiteInformation";

    public SiteInformationMenuForm() {
        // setFormName("siteInformationMenuForm");
    }

    @Override
    public List getMenuList() {
        return menuList;
    }

    @Override
    public void setMenuList(List menuList) {
        this.menuList = menuList;
    }

    @Override
    public List<String> getSelectedIDs() {
        return selectedIDs;
    }

    @Override
    public void setSelectedIDs(List<String> selectedIDs) {
        this.selectedIDs = selectedIDs;
    }

    public String getSiteInfoDomainName() {
        return siteInfoDomainName;
    }

    public void setSiteInfoDomainName(String siteInfoDomainName) {
        this.siteInfoDomainName = siteInfoDomainName;
    }
}
