package spring.generated.forms;

import java.lang.String;
import java.util.List;
import spring.mine.common.form.BaseForm;

public class SampleTypeOrderForm extends BaseForm {
  private List sampleTypeList;

  private String jsonChangeList = "";

  public List getSampleTypeList() {
    return this.sampleTypeList;
  }

  public void setSampleTypeList(List sampleTypeList) {
    this.sampleTypeList = sampleTypeList;
  }

  public String getJsonChangeList() {
    return this.jsonChangeList;
  }

  public void setJsonChangeList(String jsonChangeList) {
    this.jsonChangeList = jsonChangeList;
  }
}
