package spring.generated.forms;

import java.lang.String;
import java.sql.Timestamp;
import java.util.Collection;
import spring.mine.common.form.BaseForm;

public class UnifiedSystemUserForm extends BaseForm {
  private String loginUserId = "";

  private String systemUserId = "";

  private String userLoginName = "";

  private String userPassword1 = "";

  private String userPassword2 = "";

  private String userFirstName = "";

  private String userLastName = "";

  private Collection roles;

  private String[] selectedRoles;

  private String expirationDate;

  private String accountLocked = "N";

  private String accountDisabled = "N";

  private String accountActive = "Y";

  private String timeout;

  private Timestamp lastupdated;

  private Timestamp systemUserLastupdated;

  public String getLoginUserId() {
    return this.loginUserId;
  }

  public void setLoginUserId(String loginUserId) {
    this.loginUserId = loginUserId;
  }

  public String getSystemUserId() {
    return this.systemUserId;
  }

  public void setSystemUserId(String systemUserId) {
    this.systemUserId = systemUserId;
  }

  public String getUserLoginName() {
    return this.userLoginName;
  }

  public void setUserLoginName(String userLoginName) {
    this.userLoginName = userLoginName;
  }

  public String getUserPassword1() {
    return this.userPassword1;
  }

  public void setUserPassword1(String userPassword1) {
    this.userPassword1 = userPassword1;
  }

  public String getUserPassword2() {
    return this.userPassword2;
  }

  public void setUserPassword2(String userPassword2) {
    this.userPassword2 = userPassword2;
  }

  public String getUserFirstName() {
    return this.userFirstName;
  }

  public void setUserFirstName(String userFirstName) {
    this.userFirstName = userFirstName;
  }

  public String getUserLastName() {
    return this.userLastName;
  }

  public void setUserLastName(String userLastName) {
    this.userLastName = userLastName;
  }

  public Collection getRoles() {
    return this.roles;
  }

  public void setRoles(Collection roles) {
    this.roles = roles;
  }

  public String[] getSelectedRoles() {
    return this.selectedRoles;
  }

  public void setSelectedRoles(String[] selectedRoles) {
    this.selectedRoles = selectedRoles;
  }

  public String getExpirationDate() {
    return this.expirationDate;
  }

  public void setExpirationDate(String expirationDate) {
    this.expirationDate = expirationDate;
  }

  public String getAccountLocked() {
    return this.accountLocked;
  }

  public void setAccountLocked(String accountLocked) {
    this.accountLocked = accountLocked;
  }

  public String getAccountDisabled() {
    return this.accountDisabled;
  }

  public void setAccountDisabled(String accountDisabled) {
    this.accountDisabled = accountDisabled;
  }

  public String getAccountActive() {
    return this.accountActive;
  }

  public void setAccountActive(String accountActive) {
    this.accountActive = accountActive;
  }

  public String getTimeout() {
    return this.timeout;
  }

  public void setTimeout(String timeout) {
    this.timeout = timeout;
  }

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }

  public Timestamp getSystemUserLastupdated() {
    return this.systemUserLastupdated;
  }

  public void setSystemUserLastupdated(Timestamp systemUserLastupdated) {
    this.systemUserLastupdated = systemUserLastupdated;
  }
}
