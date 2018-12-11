package spring.generated.forms;

import java.lang.String;
import java.util.List;
import spring.mine.common.form.BaseForm;

public class TestSectionRenameEntryForm extends BaseForm {
  private List testSectionList;

  private String nameEnglish = "";

  private String nameFrench = "";

  private String testSectionId = "";

  public List getTestSectionList() {
    return this.testSectionList;
  }

  public void setTestSectionList(List testSectionList) {
    this.testSectionList = testSectionList;
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

  public String getTestSectionId() {
    return this.testSectionId;
  }

  public void setTestSectionId(String testSectionId) {
    this.testSectionId = testSectionId;
  }
}
