package spring.generated.forms;

import java.lang.String;
import java.util.List;
import spring.mine.common.form.BaseForm;

public class TestActivationForm extends BaseForm {
  private List activeTestList;

  private List inactiveTestList;

  private String jsonChangeList = "";

  public List getActiveTestList() {
    return this.activeTestList;
  }

  public void setActiveTestList(List activeTestList) {
    this.activeTestList = activeTestList;
  }

  public List getInactiveTestList() {
    return this.inactiveTestList;
  }

  public void setInactiveTestList(List inactiveTestList) {
    this.inactiveTestList = inactiveTestList;
  }

  public String getJsonChangeList() {
    return this.jsonChangeList;
  }

  public void setJsonChangeList(String jsonChangeList) {
    this.jsonChangeList = jsonChangeList;
  }
}
