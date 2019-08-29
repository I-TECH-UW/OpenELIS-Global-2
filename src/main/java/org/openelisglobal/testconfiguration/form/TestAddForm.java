package org.openelisglobal.testconfiguration.form;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.openelisglobal.common.form.BaseForm;

public class TestAddForm extends BaseForm {

    // additional in validator
    @NotBlank
    private String jsonWad = "";

    // for display
    private List sampleTypeList;

    // for display
    private List panelList;

    // for display
    private List uomList;

    // for display
    private List resultTypeList;

    // for display
    private List ageRangeList;

    // for display
    private List labUnitList;

    // for display
    private List dictionaryList;

    // for display
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
