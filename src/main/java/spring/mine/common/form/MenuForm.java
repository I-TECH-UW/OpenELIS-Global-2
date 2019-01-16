package spring.mine.common.form;

import java.util.List;

public abstract class MenuForm extends BaseForm {

	public abstract List getMenuList();

	public abstract void setMenuList(List menuList);

	public abstract String[] getSelectedIDs();

	public abstract void setSelectedIDs(String[] selectedIDs);
}
