package org.openelisglobal.patienttype.form;

import java.util.List;

import org.openelisglobal.common.form.BaseForm;

public class PatientTypeMenuForm extends BaseForm {
    private List menuList;

    private String[] selectedIDs;

    public PatientTypeMenuForm() {
        setFormName("patientTypeMenuForm");
    }

    public List getMenuList() {
        return this.menuList;
    }

    public void setMenuList(List menuList) {
        this.menuList = menuList;
    }

    public String[] getSelectedIDs() {
        return this.selectedIDs;
    }

    public void setSelectedIDs(String[] selectedIDs) {
        this.selectedIDs = selectedIDs;
    }
}
