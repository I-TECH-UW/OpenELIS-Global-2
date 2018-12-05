package spring.generated.forms;

import java.lang.String;
import java.util.List;
import spring.mine.common.form.BaseForm;

public class TestSectionCreateForm extends BaseForm {
  private List existingTestUnitList;

  private List inactiveTestUnitList;

  private String existingEnglishNames;

  private String existingFrenchNames;

  private String testUnitEnglishName;

  private String testUnitFrenchName;

  public List getExistingTestUnitList() {
    return this.existingTestUnitList;
  }

  public void setExistingTestUnitList(List existingTestUnitList) {
    this.existingTestUnitList = existingTestUnitList;
  }

  public List getInactiveTestUnitList() {
    return this.inactiveTestUnitList;
  }

  public void setInactiveTestUnitList(List inactiveTestUnitList) {
    this.inactiveTestUnitList = inactiveTestUnitList;
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

  public String getTestUnitEnglishName() {
    return this.testUnitEnglishName;
  }

  public void setTestUnitEnglishName(String testUnitEnglishName) {
    this.testUnitEnglishName = testUnitEnglishName;
  }

  public String getTestUnitFrenchName() {
    return this.testUnitFrenchName;
  }

  public void setTestUnitFrenchName(String testUnitFrenchName) {
    this.testUnitFrenchName = testUnitFrenchName;
  }
}
