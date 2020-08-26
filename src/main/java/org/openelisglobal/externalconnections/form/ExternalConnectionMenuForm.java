package org.openelisglobal.externalconnections.form;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.openelisglobal.common.form.MenuForm;
import org.openelisglobal.common.validator.ValidationHelper;
import org.openelisglobal.externalconnections.valueholder.ExternalConnection;

public class ExternalConnectionMenuForm extends MenuForm {
    private String lastupdated;

    private List<ExternalConnection> menuList;

    private List<@Pattern(regexp = ValidationHelper.ID_REGEX) String> selectedIDs;

    public ExternalConnectionMenuForm() {
        setFormName("ExternalConnectionMenuForm");
    }

    public String getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(String lastupdated) {
        this.lastupdated = lastupdated;
    }

    @Override
    public List<ExternalConnection> getMenuList() {
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
}
