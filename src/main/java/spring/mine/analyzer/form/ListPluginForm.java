package spring.mine.analyzer.form;

import java.sql.Timestamp;
import java.util.List;
import spring.mine.common.form.BaseForm;

public class ListPluginForm extends BaseForm {
  private Timestamp lastupdated;

  private List<String> pluginList;

  public ListPluginForm() {
    setFormName("listPluginForm");
  }

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }

  public List<String> getPluginList() {
    return this.pluginList;
  }

  public void setPluginList(List<String> pluginList) {
    this.pluginList = pluginList;
  }
}
