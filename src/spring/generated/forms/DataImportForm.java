package spring.generated.forms;

import java.lang.String;
import java.sql.Timestamp;
import java.util.List;
import spring.mine.common.form.BaseForm;

public class DataImportForm extends BaseForm {
  private Timestamp lastupdated;

  private List updateData;

  private List controlData;

  private String updateDate = "";

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }

  public List getUpdateData() {
    return this.updateData;
  }

  public void setUpdateData(List updateData) {
    this.updateData = updateData;
  }

  public List getControlData() {
    return this.controlData;
  }

  public void setControlData(List controlData) {
    this.controlData = controlData;
  }

  public String getUpdateDate() {
    return this.updateDate;
  }

  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }
}
