package spring.generated.forms;

import java.lang.String;
import java.util.List;
import spring.mine.common.form.BaseForm;

public class UnitOfMeasureRenameEntryForm extends BaseForm {
  private List uomList;

  private String nameEnglish = "";

  private String nameFrench = "";

  private String uomId = "";

  public List getUomList() {
    return this.uomList;
  }

  public void setUomList(List uomList) {
    this.uomList = uomList;
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

  public String getUomId() {
    return this.uomId;
  }

  public void setUomId(String uomId) {
    this.uomId = uomId;
  }
}
