package org.openelisglobal.common.form;

import java.util.List;

public abstract class MenuForm extends BaseForm {

    public abstract List getMenuList();

    public abstract void setMenuList(List menuList);

    public abstract List<String> getSelectedIDs();

    public abstract void setSelectedIDs(List<String> selectedIDs);
}
