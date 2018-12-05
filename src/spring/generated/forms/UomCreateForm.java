package spring.generated.forms;

import java.lang.String;
import java.util.List;
import spring.mine.common.form.BaseForm;

public class UomCreateForm extends BaseForm {
  private List existingUomList;

  private List inactiveUomList;

  private String existingEnglishNames;

  private String existingFrenchNames;

  private String uomEnglishName;

  private String uomFrenchName;

  public List getExistingUomList() {
    return this.existingUomList;
  }

  public void setExistingUomList(List existingUomList) {
    this.existingUomList = existingUomList;
  }

  public List getInactiveUomList() {
    return this.inactiveUomList;
  }

  public void setInactiveUomList(List inactiveUomList) {
    this.inactiveUomList = inactiveUomList;
  }

  public String getExistingEnglishNames() {
    return this.existingEnglishNames;
  }

  public void setExistingEnglishNames(String existingEnglishNames) {
    this.existingEnglishNames = existingEnglishNames;
  }

  public String getExistingFrenchNames() {
    return this.existingFrenchNames;
  }

  public void setExistingFrenchNames(String existingFrenchNames) {
    this.existingFrenchNames = existingFrenchNames;
  }

  public String getUomEnglishName() {
    return this.uomEnglishName;
  }

  public void setUomEnglishName(String uomEnglishName) {
    this.uomEnglishName = uomEnglishName;
  }

  public String getUomFrenchName() {
    return this.uomFrenchName;
  }

  public void setUomFrenchName(String uomFrenchName) {
    this.uomFrenchName = uomFrenchName;
  }
}
