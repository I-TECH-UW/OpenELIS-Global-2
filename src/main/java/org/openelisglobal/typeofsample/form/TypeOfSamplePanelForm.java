package org.openelisglobal.typeofsample.form;

import java.util.Collection;

import org.openelisglobal.common.form.BaseForm;

public class TypeOfSamplePanelForm extends BaseForm {
    private String id = "";

    private String sample = "";

    private String panel = "";

    private Collection samples;

    private Collection panels;

    public TypeOfSamplePanelForm() {
        setFormName("typeOfSamplePanelForm");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public String getPanel() {
        return panel;
    }

    public void setPanel(String panel) {
        this.panel = panel;
    }

    public Collection getSamples() {
        return samples;
    }

    public void setSamples(Collection samples) {
        this.samples = samples;
    }

    public Collection getPanels() {
        return panels;
    }

    public void setPanels(Collection panels) {
        this.panels = panels;
    }
}
