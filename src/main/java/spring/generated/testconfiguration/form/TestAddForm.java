package spring.generated.testconfiguration.form;

import java.util.List;

import spring.mine.common.form.BaseForm;

public class TestAddForm extends BaseForm {
	private String jsonWad = "";

	private List sampleTypeList;

	private List panelList;

	private List uomList;

	private List resultTypeList;

	private List ageRangeList;

	private List labUnitList;

	private List dictionaryList;

	private List groupedDictionaryList;

	public TestAddForm() {
		setFormName("testAddForm");
	}

	public String getJsonWad() {
		return jsonWad;
	}

	public void setJsonWad(String jsonWad) {
		this.jsonWad = jsonWad;
	}

	public List getSampleTypeList() {
		return sampleTypeList;
	}

	public void setSampleTypeList(List sampleTypeList) {
		this.sampleTypeList = sampleTypeList;
	}

	public List getPanelList() {
		return panelList;
	}

	public void setPanelList(List panelList) {
		this.panelList = panelList;
	}

	public List getUomList() {
		return uomList;
	}

	public void setUomList(List uomList) {
		this.uomList = uomList;
	}

	public List getResultTypeList() {
		return resultTypeList;
	}

	public void setResultTypeList(List resultTypeList) {
		this.resultTypeList = resultTypeList;
	}

	public List getAgeRangeList() {
		return ageRangeList;
	}

	public void setAgeRangeList(List ageRangeList) {
		this.ageRangeList = ageRangeList;
	}

	public List getLabUnitList() {
		return labUnitList;
	}

	public void setLabUnitList(List labUnitList) {
		this.labUnitList = labUnitList;
	}

	public List getDictionaryList() {
		return dictionaryList;
	}

	public void setDictionaryList(List dictionaryList) {
		this.dictionaryList = dictionaryList;
	}

	public List getGroupedDictionaryList() {
		return groupedDictionaryList;
	}

	public void setGroupedDictionaryList(List groupedDictionaryList) {
		this.groupedDictionaryList = groupedDictionaryList;
	}
}
