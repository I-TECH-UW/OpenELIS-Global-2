package spring.generated.forms;

import java.lang.String;
import java.util.List;
import spring.mine.common.form.BaseForm;

public class TestSectionOrderForm extends BaseForm {
  private List testSectionList;

  private String jsonChangeList = "";

  public List getTestSectionList() {
    return this.testSectionList;
  }

  public void setTestSectionList(List testSectionList) {
    this.testSectionList = testSectionList;
  }

  public String getJsonChangeList() {
    return this.jsonChangeList;
  }

  public void setJsonChangeList(String jsonChangeList) {
    this.jsonChangeList = jsonChangeList;
  }
}
