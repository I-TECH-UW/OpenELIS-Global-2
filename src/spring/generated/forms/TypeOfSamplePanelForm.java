package spring.generated.forms;

import java.lang.String;
import java.sql.Timestamp;
import java.util.Collection;
import spring.mine.common.form.BaseForm;

public class TypeOfSamplePanelForm extends BaseForm {
  private String id = "";

  private String sample = "";

  private String panel = "";

  private Collection samples;

  private Collection panels;

  private Timestamp lastupdated;

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSample() {
    return this.sample;
  }

  public void setSample(String sample) {
    this.sample = sample;
  }

  public String getPanel() {
    return this.panel;
  }

  public void setPanel(String panel) {
    this.panel = panel;
  }

  public Collection getSamples() {
    return this.samples;
  }

  public void setSamples(Collection samples) {
    this.samples = samples;
  }

  public Collection getPanels() {
    return this.panels;
  }

  public void setPanels(Collection panels) {
    this.panels = panels;
  }

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }
}
