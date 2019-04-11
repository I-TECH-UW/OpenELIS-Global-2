package spring.unused.userrole.form;

import java.util.List;

import spring.mine.common.form.BaseForm;

public class UserRoleMenuForm extends BaseForm {
	private List menuList;

	private String[] selectedIDs;

	public UserRoleMenuForm() {
		setFormName("userRoleMenuForm");
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
