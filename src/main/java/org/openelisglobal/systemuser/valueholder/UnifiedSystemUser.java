package org.openelisglobal.systemuser.valueholder;

public class UnifiedSystemUser {
  private static final String ID_SEPARATOR = "-";

  private String lastName;
  private String firstName;
  private String loginName;
  private String expDate;
  private String locked;
  private String disabled;
  private String active;
  private String timeout;
  private String systemUserId = " ";
  private String loginUserId = " ";

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getExpDate() {
    return expDate;
  }

  public void setExpDate(String expDate) {
    this.expDate = expDate;
  }

  public String getLocked() {
    return locked;
  }

  public void setLocked(String locked) {
    this.locked = locked;
  }

  public String getDisabled() {
    return disabled;
  }

  public void setDisabled(String disabled) {
    this.disabled = disabled;
  }

  public String getTimeout() {
    return timeout;
  }

  public void setTimeout(String timeOut) {
    this.timeout = timeOut;
  }

  public String getSystemUserId() {
    return systemUserId;
  }

  public void setSystemUserId(String systemUserId) {
    this.systemUserId = systemUserId;
  }

  public void setLoginUserId(String loginUserId) {
    this.loginUserId = loginUserId;
  }

  public String getLoginUserId() {
    return loginUserId;
  }

  public void setActive(String active) {
    this.active = active;
  }

  public String getActive() {
    return active;
  }

  public String getCombinedUserID() {
    return getSystemUserId() + ID_SEPARATOR + getLoginUserId();
  }

  public static String getSystemUserIDFromCombinedID(String combinedId) {
    int separatorIndex = combinedId.indexOf(ID_SEPARATOR);

    return separatorIndex == 0 ? null : combinedId.substring(0, separatorIndex);
  }

  public static Integer getLoginUserIDFromCombinedID(String combinedId) {
    int separatorIndex = combinedId.indexOf(ID_SEPARATOR);

    return separatorIndex == combinedId.length() - 1 || combinedId.endsWith(ID_SEPARATOR)
        ? null
        : Integer.parseInt(combinedId.substring(separatorIndex + 1));
  }
}
