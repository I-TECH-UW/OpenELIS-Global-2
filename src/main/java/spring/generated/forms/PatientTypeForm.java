package spring.generated.forms;

import java.lang.String;
import java.sql.Timestamp;
import spring.mine.common.form.BaseForm;

public class PatientTypeForm extends BaseForm {
  private String id = "";

  private String type = "";

  private String description = "";

  private Timestamp lastupdated;

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }
}
