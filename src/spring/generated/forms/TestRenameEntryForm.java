package spring.generated.forms;

import java.lang.String;
import java.util.List;
import spring.mine.common.form.BaseForm;

public class TestRenameEntryForm extends BaseForm {
  private List testList;

  private String nameEnglish = "";

  private String nameFrench = "";

  private String reportNameEnglish = "";

  private String reportNameFrench = "";

  private String testId = "";

  public List getTestList() {
    return this.testList;
  }

  public void setTestList(List testList) {
    this.testList = testList;
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

  public String getReportNameEnglish() {
    return this.reportNameEnglish;
  }

  public void setReportNameEnglish(String reportNameEnglish) {
    this.reportNameEnglish = reportNameEnglish;
  }

  public String getReportNameFrench() {
    return this.reportNameFrench;
  }

  public void setReportNameFrench(String reportNameFrench) {
    this.reportNameFrench = reportNameFrench;
  }

  public String getTestId() {
    return this.testId;
  }

  public void setTestId(String testId) {
    this.testId = testId;
  }
}
