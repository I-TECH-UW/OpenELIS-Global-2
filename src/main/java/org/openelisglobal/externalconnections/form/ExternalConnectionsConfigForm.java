package org.openelisglobal.externalconnections.form;

import java.util.List;

import org.openelisglobal.common.form.BaseForm;

public class ExternalConnectionsConfigForm extends BaseForm {
	private String lastupdated;

	private List menuList;

	private String[] selectedIDs;

	public ExternalConnectionsConfigForm() {
		setFormName("ExternalConnectionsConfigForm");
	}

	public String getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(String lastupdated) {
		this.lastupdated = lastupdated;
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
