package spring.generated.forms;

import java.lang.String;
import java.sql.Timestamp;
import java.util.List;
import spring.mine.common.form.BaseForm;

public class AnalyzerTestNameForm extends BaseForm {
  private List analyzerList;

  private String analyzerId;

  private List testList;

  private String testId;

  private String analyzerTestName;

  private Timestamp lastupdated;

  public List getAnalyzerList() {
    return this.analyzerList;
  }

  public void setAnalyzerList(List analyzerList) {
    this.analyzerList = analyzerList;
  }

  public String getAnalyzerId() {
    return this.analyzerId;
  }

  public void setAnalyzerId(String analyzerId) {
    this.analyzerId = analyzerId;
  }

  public List getTestList() {
    return this.testList;
  }

  public void setTestList(List testList) {
    this.testList = testList;
  }

  public String getTestId() {
    return this.testId;
  }

  public void setTestId(String testId) {
    this.testId = testId;
  }

  public String getAnalyzerTestName() {
    return this.analyzerTestName;
  }

  public void setAnalyzerTestName(String analyzerTestName) {
    this.analyzerTestName = analyzerTestName;
  }

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }
}
