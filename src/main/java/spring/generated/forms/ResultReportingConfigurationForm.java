package spring.generated.forms;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import spring.mine.common.form.BaseForm;

public class ResultReportingConfigurationForm extends BaseForm {
  private Timestamp lastupdated;

  private ArrayList reports;

  private Collection hourList;

  private Collection minList;

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }

  public ArrayList getReports() {
    return this.reports;
  }

  public void setReports(ArrayList reports) {
    this.reports = reports;
  }

  public Collection getHourList() {
    return this.hourList;
  }

  public void setHourList(Collection hourList) {
    this.hourList = hourList;
  }

  public Collection getMinList() {
    return this.minList;
  }

  public void setMinList(Collection minList) {
    this.minList = minList;
  }
}
