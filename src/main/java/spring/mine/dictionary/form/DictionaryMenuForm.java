package spring.mine.dictionary.form;

import java.util.List;

import spring.mine.common.form.MenuForm;

public class DictionaryMenuForm extends MenuForm {
	private List menuList;

	private String[] selectedIDs;

	private String searchString = "";

	public DictionaryMenuForm() {
		setFormName("dictionaryMenuForm");
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

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
}
