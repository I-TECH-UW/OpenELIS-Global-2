package org.openelisglobal.systemuser.form;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.openelisglobal.common.form.AdminOptionMenuForm;
import org.openelisglobal.common.validator.ValidationHelper;
import org.openelisglobal.systemuser.valueholder.UnifiedSystemUser;

public class UnifiedSystemUserMenuForm extends AdminOptionMenuForm<UnifiedSystemUser> {
    /**
     *
     */
    private static final long serialVersionUID = 4853994882374119915L;

    // for display
    private List<UnifiedSystemUser> menuList;

    private List<@Pattern(regexp = ValidationHelper.ID_REGEX) String> selectedIDs;

    public UnifiedSystemUserMenuForm() {
        setFormName("unifiedSystemUserMenuForm");
    }

    @Override
    public List<UnifiedSystemUser> getMenuList() {
        return menuList;
    }

    @Override
    public void setMenuList(List<UnifiedSystemUser> menuList) {
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
