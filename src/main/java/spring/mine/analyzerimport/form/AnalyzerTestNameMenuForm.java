package spring.mine.analyzerimport.form;

import java.util.List;

import org.hibernate.validator.constraints.SafeHtml;

import spring.mine.common.form.MenuForm;

public class AnalyzerTestNameMenuForm extends MenuForm {
	// for display
	private List menuList;

	private List<@SafeHtml String> selectedIDs;

	public AnalyzerTestNameMenuForm() {
		setFormName("analyzerTestNameMenuForm");
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
