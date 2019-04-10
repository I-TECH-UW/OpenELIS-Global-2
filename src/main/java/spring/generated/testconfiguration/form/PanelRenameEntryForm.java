package spring.generated.testconfiguration.form;

import java.util.List;

import spring.mine.common.form.BaseForm;

public class PanelRenameEntryForm extends BaseForm {
	private List panelList;

	private String nameEnglish = "";

	private String nameFrench = "";

	private String panelId = "";

	public PanelRenameEntryForm() {
		setFormName("panelRenameEntryForm");
	}

	public List getPanelList() {
		return panelList;
	}

	public void setPanelList(List panelList) {
		this.panelList = panelList;
	}

	public String getNameEnglish() {
		return nameEnglish;
	}

	public void setNameEnglish(String nameEnglish) {
		this.nameEnglish = nameEnglish;
	}

	public String getNameFrench() {
		return nameFrench;
	}

	public void setNameFrench(String nameFrench) {
		this.nameFrench = nameFrench;
	}

	public String getPanelId() {
		return panelId;
	}

	public void setPanelId(String panelId) {
		this.panelId = panelId;
	}
}
