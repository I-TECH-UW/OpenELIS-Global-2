package spring.generated.forms;

import java.lang.String;
import java.sql.Timestamp;
import java.util.List;
import spring.mine.common.form.BaseForm;

public class ElectronicOrderViewForm extends BaseForm {
  private Timestamp lastupdated;

  private String sortBy;

  private List eOrders;

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }

  public String getSortBy() {
    return this.sortBy;
  }

  public void setSortBy(String sortBy) {
    this.sortBy = sortBy;
  }

  public List getEOrders() {
    return this.eOrders;
  }

  public void setEOrders(List eOrders) {
    this.eOrders = eOrders;
  }
}
