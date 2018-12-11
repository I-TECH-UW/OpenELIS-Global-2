package spring.generated.forms;

import java.lang.String;
import java.sql.Timestamp;
import java.util.List;
import spring.mine.common.form.BaseForm;

public class BatchTestReassignment extends BaseForm {
  private List sampleList;

  private String statusChangedSampleType = "";

  private String statusChangedCurrentTest = "";

  private String statusChangedNextTest = "";

  private List statusChangedList;

  private String jsonWad = "";

  private Timestamp lastupdated;

  public List getSampleList() {
    return this.sampleList;
  }

  public void setSampleList(List sampleList) {
    this.sampleList = sampleList;
  }

  public String getStatusChangedSampleType() {
    return this.statusChangedSampleType;
  }

  public void setStatusChangedSampleType(String statusChangedSampleType) {
    this.statusChangedSampleType = statusChangedSampleType;
  }

  public String getStatusChangedCurrentTest() {
    return this.statusChangedCurrentTest;
  }

  public void setStatusChangedCurrentTest(String statusChangedCurrentTest) {
    this.statusChangedCurrentTest = statusChangedCurrentTest;
  }

  public String getStatusChangedNextTest() {
    return this.statusChangedNextTest;
  }

  public void setStatusChangedNextTest(String statusChangedNextTest) {
    this.statusChangedNextTest = statusChangedNextTest;
  }

  public List getStatusChangedList() {
    return this.statusChangedList;
  }

  public void setStatusChangedList(List statusChangedList) {
    this.statusChangedList = statusChangedList;
  }

  public String getJsonWad() {
    return this.jsonWad;
  }

  public void setJsonWad(String jsonWad) {
    this.jsonWad = jsonWad;
  }

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }
}
