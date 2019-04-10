package spring.generated.testconfiguration.form;

import java.util.List;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.testconfiguration.action.PanelTests;

public class PanelTestAssignForm extends BaseForm {
	private List panelList;

	private PanelTests selectedPanel = new PanelTests();

	private List panelTestList;

	private String panelId = "";

	private String deactivatePanelId = "";

	private String[] currentTests;

	private String[] availableTests;

	public PanelTestAssignForm() {
		setFormName("panelTestAssignForm");
	}

	public List getPanelList() {
		return panelList;
	}

	public void setPanelList(List panelList) {
		this.panelList = panelList;
	}

	public PanelTests getSelectedPanel() {
		return selectedPanel;
	}

	public void setSelectedPanel(PanelTests selectedPanel) {
		this.selectedPanel = selectedPanel;
	}

	public List getPanelTestList() {
		return panelTestList;
	}

	public void setPanelTestList(List panelTestList) {
		this.panelTestList = panelTestList;
	}

	public String getPanelId() {
		return panelId;
	}

	public void setPanelId(String panelId) {
		this.panelId = panelId;
	}

	public String getDeactivatePanelId() {
		return deactivatePanelId;
	}

	public void setDeactivatePanelId(String deactivatePanelId) {
		this.deactivatePanelId = deactivatePanelId;
	}

	public String[] getCurrentTests() {
		return currentTests;
	}

	public void setCurrentTests(String[] currentTests) {
		this.currentTests = currentTests;
	}

	public String[] getAvailableTests() {
		return availableTests;
	}

	public void setAvailableTests(String[] availableTests) {
		this.availableTests = availableTests;
	}
}
