package spring.generated.forms;

import java.lang.String;
import java.sql.Timestamp;
import java.util.List;
import spring.mine.common.form.BaseForm;

public class InventoryForm extends BaseForm {
  private String currentDate = "";

  private Timestamp lastupdated;

  private List inventoryItems;

  private List sources;

  private List kitTypes;

  private String newKitsXML = "";

  public String getCurrentDate() {
    return this.currentDate;
  }

  public void setCurrentDate(String currentDate) {
    this.currentDate = currentDate;
  }

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }

  public List getInventoryItems() {
    return this.inventoryItems;
  }

  public void setInventoryItems(List inventoryItems) {
    this.inventoryItems = inventoryItems;
  }

  public List getSources() {
    return this.sources;
  }

  public void setSources(List sources) {
    this.sources = sources;
  }

  public List getKitTypes() {
    return this.kitTypes;
  }

  public void setKitTypes(List kitTypes) {
    this.kitTypes = kitTypes;
  }

  public String getNewKitsXML() {
    return this.newKitsXML;
  }

  public void setNewKitsXML(String newKitsXML) {
    this.newKitsXML = newKitsXML;
  }
}
