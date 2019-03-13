package spring.generated.forms;

import java.util.List;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.test.beanItems.TestActivationBean;

public class TestActivationForm extends BaseForm {
  private List<TestActivationBean> activeTestList;

  private List<TestActivationBean> inactiveTestList;

  private String jsonChangeList = "";

  public TestActivationForm() {
    setFormName("testActivationForm");
  }

  public List<TestActivationBean> getActiveTestList() {
    return this.activeTestList;
  }

  public void setActiveTestList(List<TestActivationBean> activeTestList) {
    this.activeTestList = activeTestList;
  }

  public List<TestActivationBean> getInactiveTestList() {
    return this.inactiveTestList;
  }

  public void setInactiveTestList(List<TestActivationBean> inactiveTestList) {
    this.inactiveTestList = inactiveTestList;
  }

  public String getJsonChangeList() {
    return this.jsonChangeList;
  }

  public void setJsonChangeList(String jsonChangeList) {
    this.jsonChangeList = jsonChangeList;
  }
}
