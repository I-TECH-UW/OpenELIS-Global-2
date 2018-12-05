package spring.generated.forms;

import java.lang.String;
import java.sql.Timestamp;
import java.util.Collection;
import spring.mine.common.form.BaseForm;

public class TestUsageConfigurationForm extends BaseForm {
  private Timestamp lastupdated;

  private String enableSending = "enable";

  private String sendHour = "";

  private String sendMin = "";

  private Collection hourList;

  private Collection minList;

  private String url = "";

  private String serviceUserName = "";

  private String servicePassword = "";

  private String lastSent = "Never";

  private String lastAttemptToSend = "Never";

  private String sendStatus = "";

  public Timestamp getLastupdated() {
    return this.lastupdated;
  }

  public void setLastupdated(Timestamp lastupdated) {
    this.lastupdated = lastupdated;
  }

  public String getEnableSending() {
    return this.enableSending;
  }

  public void setEnableSending(String enableSending) {
    this.enableSending = enableSending;
  }

  public String getSendHour() {
    return this.sendHour;
  }

  public void setSendHour(String sendHour) {
    this.sendHour = sendHour;
  }

  public String getSendMin() {
    return this.sendMin;
  }

  public void setSendMin(String sendMin) {
    this.sendMin = sendMin;
  }

  public Collection getHourList() {
    return this.hourList;
  }

  public void setHourList(Collection hourList) {
    this.hourList = hourList;
  }

  public Collection getMinList() {
    return this.minList;
  }

  public void setMinList(Collection minList) {
    this.minList = minList;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getServiceUserName() {
    return this.serviceUserName;
  }

  public void setServiceUserName(String serviceUserName) {
    this.serviceUserName = serviceUserName;
  }

  public String getServicePassword() {
    return this.servicePassword;
  }

  public void setServicePassword(String servicePassword) {
    this.servicePassword = servicePassword;
  }

  public String getLastSent() {
    return this.lastSent;
  }

  public void setLastSent(String lastSent) {
    this.lastSent = lastSent;
  }

  public String getLastAttemptToSend() {
    return this.lastAttemptToSend;
  }

  public void setLastAttemptToSend(String lastAttemptToSend) {
    this.lastAttemptToSend = lastAttemptToSend;
  }

  public String getSendStatus() {
    return this.sendStatus;
  }

  public void setSendStatus(String sendStatus) {
    this.sendStatus = sendStatus;
  }
}
