package spring.unused.role.form;

import java.util.List;

import spring.mine.common.form.BaseForm;

public class RoleMenuForm extends BaseForm {
	private List menuList;

	private String[] selectedIDs;

	public RoleMenuForm() {
		setFormName("roleMenuForm");
	}

	public List getMenuList() {
		return menuList;
	}

	public void setMenuList(List menuList) {
		this.menuList = menuList;
	}

	public String[] getSelectedIDs() {
		return selectedIDs;
	}

	public void setSelectedIDs(String[] selectedIDs) {
		this.selectedIDs = selectedIDs;
	}
}
