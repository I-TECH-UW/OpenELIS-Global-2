package spring.mine.analyzerimport.form;

import java.util.List;

import spring.mine.common.form.MenuForm;

public class AnalyzerTestNameMenuForm extends MenuForm {
	private List menuList;

	private String[] selectedIDs;

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
	public String[] getSelectedIDs() {
		return selectedIDs;
	}

	@Override
	public void setSelectedIDs(String[] selectedIDs) {
		this.selectedIDs = selectedIDs;
	}
}
