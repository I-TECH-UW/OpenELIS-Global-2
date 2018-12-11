package spring.generated.forms;

import java.lang.Boolean;
import java.lang.String;
import java.sql.Timestamp;
import java.util.List;
import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.paging.PagingBean;

public class AnalyzerResultsForm extends BaseForm {
  private PagingBean paging;

  private Timestamp lastupdated;

  private List resultList;

  private String notFoundMsg = "";

  private String analyzerType = "";

  private Boolean missingTestMsg;

  public PagingBean getPaging() {
    return this.paging;
  }

  public void setPaging(PagingBean paging) {
    this.paging = paging;
  }

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }

  public List getResultList() {
    return this.resultList;
  }

  public void setResultList(List resultList) {
    this.resultList = resultList;
  }

  public String getNotFoundMsg() {
    return this.notFoundMsg;
  }

  public void setNotFoundMsg(String notFoundMsg) {
    this.notFoundMsg = notFoundMsg;
  }

  public String getAnalyzerType() {
    return this.analyzerType;
  }

  public void setAnalyzerType(String analyzerType) {
    this.analyzerType = analyzerType;
  }

  public Boolean getMissingTestMsg() {
    return this.missingTestMsg;
  }

  public void setMissingTestMsg(Boolean missingTestMsg) {
    this.missingTestMsg = missingTestMsg;
  }
}
