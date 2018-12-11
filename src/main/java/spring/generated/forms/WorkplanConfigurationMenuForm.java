package spring.generated.forms;

import java.lang.String;
import java.util.List;
import spring.mine.common.form.BaseForm;

public class WorkplanConfigurationMenuForm extends BaseForm {
  private List menuList;

  private String[] selectedIDs;

  private String siteInfoDomainName = "WorkplanConfiguration";

  public List getMenuList() {
    return this.menuList;
  }

  public void setMenuList(List menuList) {
    this.menuList = menuList;
  }

  public String[] getSelectedIDs() {
    return this.selectedIDs;
  }

  public void setSelectedIDs(String[] selectedIDs) {
    this.selectedIDs = selectedIDs;
  }

  public String getSiteInfoDomainName() {
    return this.siteInfoDomainName;
  }

  public void setSiteInfoDomainName(String siteInfoDomainName) {
    this.siteInfoDomainName = siteInfoDomainName;
  }
}
