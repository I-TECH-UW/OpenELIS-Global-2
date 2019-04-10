package spring.generated.testconfiguration.form;

import java.util.List;
import java.util.Map;

import spring.mine.common.form.BaseForm;

public class PanelCreateForm extends BaseForm {
	private List existingPanelList;

	private List inactivePanelList;

	private Map existingPanelMap;

	private Map inactivePanelMap;

	private List existingSampleTypeList;

	private String existingEnglishNames;

	private String existingFrenchNames;

	private String panelEnglishName;

	private String panelFrenchName;

	private String sampleTypeId;

	public PanelCreateForm() {
		setFormName("panelCreateForm");
	}

	public List getExistingPanelList() {
		return existingPanelList;
	}

	public void setExistingPanelList(List existingPanelList) {
		this.existingPanelList = existingPanelList;
	}

	public List getInactivePanelList() {
		return inactivePanelList;
	}

	public void setInactivePanelList(List inactivePanelList) {
		this.inactivePanelList = inactivePanelList;
	}

	public Map getExistingPanelMap() {
		return existingPanelMap;
	}

	public void setExistingPanelMap(Map existingPanelMap) {
		this.existingPanelMap = existingPanelMap;
	}

	public Map getInactivePanelMap() {
		return inactivePanelMap;
	}

	public void setInactivePanelMap(Map inactivePanelMap) {
		this.inactivePanelMap = inactivePanelMap;
	}

	public List getExistingSampleTypeList() {
		return existingSampleTypeList;
	}

	public void setExistingSampleTypeList(List existingSampleTypeList) {
		this.existingSampleTypeList = existingSampleTypeList;
	}

	public String getExistingEnglishNames() {
		return existingEnglishNames;
	}

	public void setExistingEnglishNames(String existingEnglishNames) {
		this.existingEnglishNames = existingEnglishNames;
	}

	public String getExistingFrenchNames() {
		return existingFrenchNames;
	}

	public void setExistingFrenchNames(String existingFrenchNames) {
		this.existingFrenchNames = existingFrenchNames;
	}

	public String getPanelEnglishName() {
		return panelEnglishName;
	}

	public void setPanelEnglishName(String panelEnglishName) {
		this.panelEnglishName = panelEnglishName;
	}

	public String getPanelFrenchName() {
		return panelFrenchName;
	}

	public void setPanelFrenchName(String panelFrenchName) {
		this.panelFrenchName = panelFrenchName;
	}

	public String getSampleTypeId() {
		return sampleTypeId;
	}

	public void setSampleTypeId(String sampleTypeId) {
		this.sampleTypeId = sampleTypeId;
	}
}
