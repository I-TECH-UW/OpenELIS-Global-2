package spring.generated.forms;

import java.lang.String;
import java.sql.Timestamp;
import spring.mine.common.form.BaseForm;

public class LoginChangePasswordForm extends BaseForm {
  private String loginName = "";

  private String password = "";

  private String newPassword = "";

  private String confirmPassword = "";

  private Timestamp passwordExpiredDate;

  private String accountLock = "";

  private String accountDisabled = "";

  private Timestamp lastupdated;

  public String getLoginName() {
    return this.loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNewPassword() {
    return this.newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public String getConfirmPassword() {
    return this.confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public Timestamp getPasswordExpiredDate() {
    return this.passwordExpiredDate;
  }

  public void setPasswordExpiredDate(Timestamp passwordExpiredDate) {
    this.passwordExpiredDate = passwordExpiredDate;
  }

  public String getAccountLock() {
    return this.accountLock;
  }

  public void setAccountLock(String accountLock) {
    this.accountLock = accountLock;
  }

  public String getAccountDisabled() {
    return this.accountDisabled;
  }

  public void setAccountDisabled(String accountDisabled) {
    this.accountDisabled = accountDisabled;
  }

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }
}
