package spring.generated.forms;

import java.sql.Timestamp;
import java.util.List;
import spring.mine.common.form.BaseForm;

public class ListPluginForm extends BaseForm {
  private Timestamp lastupdated;

  private List pluginList;

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }

  public List getPluginList() {
    return this.pluginList;
  }

  public void setPluginList(List pluginList) {
    this.pluginList = pluginList;
  }
}
