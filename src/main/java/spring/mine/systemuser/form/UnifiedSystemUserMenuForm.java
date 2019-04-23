package spring.mine.systemuser.form;

import java.util.List;

import javax.validation.constraints.Pattern;

import spring.mine.common.form.MenuForm;
import spring.mine.common.validator.ValidationHelper;

public class UnifiedSystemUserMenuForm extends MenuForm {
	// for display
	private List menuList;

	private List<@Pattern(regexp = ValidationHelper.ID_REGEX) String> selectedIDs;

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
	public List<String> getSelectedIDs() {
		return selectedIDs;
	}

	@Override
	public void setSelectedIDs(List<String> selectedIDs) {
		this.selectedIDs = selectedIDs;
	}
}
