package spring.mine.dataexchange.order.form;

import java.sql.Timestamp;
import java.util.List;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.dataexchange.order.valueholder.ElectronicOrder;

public class ElectronicOrderViewForm extends BaseForm {
  private Timestamp lastupdated;

  private String sortBy;

  private List<ElectronicOrder> eOrders;

  public ElectronicOrderViewForm() {
    setFormName("ElectronicOrderViewForm");
  }

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

  public List<ElectronicOrder> getEOrders() {
    return this.eOrders;
  }

  public void setEOrders(List<ElectronicOrder> eOrders) {
    this.eOrders = eOrders;
  }
}
