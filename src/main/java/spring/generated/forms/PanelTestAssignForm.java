package spring.generated.forms;

import java.lang.String;
import java.util.List;
import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.testconfiguration.action.PanelTests;

public class PanelTestAssignForm extends BaseForm {
  private List panelList;

  private PanelTests selectedPanel;

  private List panelTestList;

  private String panelId = "";

  private String deactivatePanelId = "";

  private String[] currentTests;

  private String[] availableTests;

  public List getPanelList() {
    return this.panelList;
  }

  public void setPanelList(List panelList) {
    this.panelList = panelList;
  }

  public PanelTests getSelectedPanel() {
    return this.selectedPanel;
  }

  public void setSelectedPanel(PanelTests selectedPanel) {
    this.selectedPanel = selectedPanel;
  }

  public List getPanelTestList() {
    return this.panelTestList;
  }

  public void setPanelTestList(List panelTestList) {
    this.panelTestList = panelTestList;
  }

  public String getPanelId() {
    return this.panelId;
  }

  public void setPanelId(String panelId) {
    this.panelId = panelId;
  }

  public String getDeactivatePanelId() {
    return this.deactivatePanelId;
  }

  public void setDeactivatePanelId(String deactivatePanelId) {
    this.deactivatePanelId = deactivatePanelId;
  }

  public String[] getCurrentTests() {
    return this.currentTests;
  }

  public void setCurrentTests(String[] currentTests) {
    this.currentTests = currentTests;
  }

  public String[] getAvailableTests() {
    return this.availableTests;
  }

  public void setAvailableTests(String[] availableTests) {
    this.availableTests = availableTests;
  }
}
