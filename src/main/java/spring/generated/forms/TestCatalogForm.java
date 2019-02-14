package spring.generated.forms;

import java.util.List;
import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestCatalog;

public class TestCatalogForm extends BaseForm {
	

    private List<Test> testList;
    private List<String> testSectionList;
    private List<TestCatalog> testCatalogList;

  public TestCatalogForm() {
    setFormName("testCatalogForm");
  }

  public List<Test> getTestList() {
    return this.testList;
  }

  public void setTestList(List<Test> testList) {
    this.testList = testList;
  }

  public List<String> getTestSectionList() {
    return this.testSectionList;
  }

  public void setTestSectionList(List<String> testSectionList) {
    this.testSectionList = testSectionList;
  }
  
  public List<TestCatalog> getTestCatalogList() {
	    return this.testCatalogList;
	  }

  public void setTestCatalogList(List<TestCatalog> testCatalogList) {
	    this.testCatalogList = testCatalogList;
	  }
}
