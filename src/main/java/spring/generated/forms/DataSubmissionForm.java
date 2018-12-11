package spring.generated.forms;

import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;
import java.util.List;
import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

public class DataSubmissionForm extends BaseForm {
  private Timestamp lastupdated;

  private SiteInformation dataSubUrl;

  private List indicators;

  private Integer month;

  private Integer year;

  private String siteId;

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }

  public SiteInformation getDataSubUrl() {
    return this.dataSubUrl;
  }

  public void setDataSubUrl(SiteInformation dataSubUrl) {
    this.dataSubUrl = dataSubUrl;
  }

  public List getIndicators() {
    return this.indicators;
  }

  public void setIndicators(List indicators) {
    this.indicators = indicators;
  }

  public Integer getMonth() {
    return this.month;
  }

  public void setMonth(Integer month) {
    this.month = month;
  }

  public Integer getYear() {
    return this.year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public String getSiteId() {
    return this.siteId;
  }

  public void setSiteId(String siteId) {
    this.siteId = siteId;
  }
}
