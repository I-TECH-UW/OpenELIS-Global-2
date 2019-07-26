package org.openelisglobal.resultlimits.form;

import java.util.List;

import org.openelisglobal.common.form.BaseForm;

public class ResultLimitsMenuForm extends BaseForm {
	private List menuList;

	private String[] selectedIDs;

	public ResultLimitsMenuForm() {
		setFormName("resultLimitsMenuForm");
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
