package spring.generated.forms;

import java.util.List;

import spring.mine.common.form.MenuForm;

public class ExternalConnectionsConfigForm extends MenuForm {
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
