package spring.mine.systemuser.form;

import java.util.List;

import spring.mine.common.form.MenuForm;

public class UnifiedSystemUserMenuForm extends MenuForm {
	// for display
	private List menuList;

	// in validator
	private String[] selectedIDs;

	public UnifiedSystemUserMenuForm() {
		setFormName("unifiedSystemUserMenuForm");
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
	public String[] getSelectedIDs() {
		return selectedIDs;
	}

	@Override
	public void setSelectedIDs(String[] selectedIDs) {
		this.selectedIDs = selectedIDs;
	}
}
