package spring.generated.forms;

import java.lang.String;
import java.util.List;
import spring.mine.common.form.BaseForm;

public class PanelRenameEntryForm extends BaseForm {
  private List panelList;

  private String nameEnglish = "";

  private String nameFrench = "";

  private String panelId = "";

  public List getPanelList() {
    return this.panelList;
  }

  public void setPanelList(List panelList) {
    this.panelList = panelList;
  }

  public String getNameEnglish() {
    return this.nameEnglish;
  }

  public void setNameEnglish(String nameEnglish) {
    this.nameEnglish = nameEnglish;
  }

  public String getNameFrench() {
    return this.nameFrench;
  }

  public void setNameFrench(String nameFrench) {
    this.nameFrench = nameFrench;
  }

  public String getPanelId() {
    return this.panelId;
  }

  public void setPanelId(String panelId) {
    this.panelId = panelId;
  }
}
