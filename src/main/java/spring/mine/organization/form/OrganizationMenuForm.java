package spring.mine.organization.form;

import java.util.List;

import spring.mine.common.form.MenuForm;

public class OrganizationMenuForm extends MenuForm {
	private List menuList;

	private String[] selectedIDs;

	private String searchString = "";

	public OrganizationMenuForm() {
		setFormName("organizationMenuForm");
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

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
}
