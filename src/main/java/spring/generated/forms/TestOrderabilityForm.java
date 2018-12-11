package spring.generated.forms;

import java.lang.String;
import java.util.List;
import spring.mine.common.form.BaseForm;

public class TestOrderabilityForm extends BaseForm {
  private List orderableTestList;

  private String jsonChangeList = "";

  public List getOrderableTestList() {
    return this.orderableTestList;
  }

  public void setOrderableTestList(List orderableTestList) {
    this.orderableTestList = orderableTestList;
  }

  public String getJsonChangeList() {
    return this.jsonChangeList;
  }

  public void setJsonChangeList(String jsonChangeList) {
    this.jsonChangeList = jsonChangeList;
  }
}
