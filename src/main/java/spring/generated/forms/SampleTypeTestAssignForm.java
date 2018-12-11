package spring.generated.forms;

import java.lang.String;
import java.util.LinkedHashMap;
import java.util.List;
import spring.mine.common.form.BaseForm;

public class SampleTypeTestAssignForm extends BaseForm {
  private List sampleTypeList;

  private LinkedHashMap sampleTypeTestList;

  private String testId = "";

  private String sampleTypeId = "";

  private String deactivateSampleTypeId = "";

  public List getSampleTypeList() {
    return this.sampleTypeList;
  }

  public void setSampleTypeList(List sampleTypeList) {
    this.sampleTypeList = sampleTypeList;
  }

  public LinkedHashMap getSampleTypeTestList() {
    return this.sampleTypeTestList;
  }

  public void setSampleTypeTestList(LinkedHashMap sampleTypeTestList) {
    this.sampleTypeTestList = sampleTypeTestList;
  }

  public String getTestId() {
    return this.testId;
  }

  public void setTestId(String testId) {
    this.testId = testId;
  }

  public String getSampleTypeId() {
    return this.sampleTypeId;
  }

  public void setSampleTypeId(String sampleTypeId) {
    this.sampleTypeId = sampleTypeId;
  }

  public String getDeactivateSampleTypeId() {
    return this.deactivateSampleTypeId;
  }

  public void setDeactivateSampleTypeId(String deactivateSampleTypeId) {
    this.deactivateSampleTypeId = deactivateSampleTypeId;
  }
}
