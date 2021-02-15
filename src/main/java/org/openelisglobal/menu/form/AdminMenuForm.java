package org.openelisglobal.menu.form;

import java.util.List;

import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.menu.valueholder.AdminMenuItem;

public class AdminMenuForm extends BaseForm {

    private List<AdminMenuItem> adminMenuItems;

    public List<AdminMenuItem> getAdminMenuItems() {
        return adminMenuItems;
    }

    public void setAdminMenuItems(List<AdminMenuItem> adminMenuItems) {
        this.adminMenuItems = adminMenuItems;
    }
}
