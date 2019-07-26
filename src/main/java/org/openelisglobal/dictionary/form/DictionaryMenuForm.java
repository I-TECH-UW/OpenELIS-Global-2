package org.openelisglobal.dictionary.form;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.SafeHtml;

import org.openelisglobal.common.form.MenuForm;
import org.openelisglobal.common.validator.ValidationHelper;

public class DictionaryMenuForm extends MenuForm {
	// for display
	private List menuList;

	private List<@Pattern(regexp = ValidationHelper.ID_REGEX) String> selectedIDs;

	@SafeHtml
	private String searchString = "";

	public DictionaryMenuForm() {
		setFormName("dictionaryMenuForm");
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
	public List<String> getSelectedIDs() {
		return selectedIDs;
	}

	@Override
	public void setSelectedIDs(List<String> selectedIDs) {
		this.selectedIDs = selectedIDs;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
}
