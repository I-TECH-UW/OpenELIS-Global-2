package spring.generated.forms;

import java.lang.String;
import java.util.List;
import spring.mine.common.form.BaseForm;

public class PanelOrderForm extends BaseForm {
  private List panelList;

  private String jsonChangeList = "";

  private List existingPanelList;

  private List inactivePanelList;

  private List existingSampleTypeList;

  public List getPanelList() {
    return this.panelList;
  }

  public void setPanelList(List panelList) {
    this.panelList = panelList;
  }

  public String getJsonChangeList() {
    return this.jsonChangeList;
  }

  public void setJsonChangeList(String jsonChangeList) {
    this.jsonChangeList = jsonChangeList;
  }

  public List getExistingPanelList() {
    return this.existingPanelList;
  }

  public void setExistingPanelList(List existingPanelList) {
    this.existingPanelList = existingPanelList;
  }

  public List getInactivePanelList() {
    return this.inactivePanelList;
  }

  public void setInactivePanelList(List inactivePanelList) {
    this.inactivePanelList = inactivePanelList;
  }

  public List getExistingSampleTypeList() {
    return this.existingSampleTypeList;
  }

  public void setExistingSampleTypeList(List existingSampleTypeList) {
    this.existingSampleTypeList = existingSampleTypeList;
  }
}
