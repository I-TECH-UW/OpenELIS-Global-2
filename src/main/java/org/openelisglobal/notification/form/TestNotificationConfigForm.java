package org.openelisglobal.notification.form;

import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.notification.valueholder.NotificationPayloadTemplate;
import org.openelisglobal.notification.valueholder.TestNotificationConfig;

public class TestNotificationConfigForm extends BaseForm {

  private static final long serialVersionUID = 8898859579392619556L;

  private TestNotificationConfig config;

  private NotificationPayloadTemplate systemDefaultPayloadTemplate;

  private boolean editSystemDefaultPayloadTemplate;

  public NotificationPayloadTemplate getSystemDefaultPayloadTemplate() {
    return systemDefaultPayloadTemplate;
  }

  public void setSystemDefaultPayloadTemplate(
      NotificationPayloadTemplate systemDefaultPayloadTemplate) {
    this.systemDefaultPayloadTemplate = systemDefaultPayloadTemplate;
  }

  public boolean getEditSystemDefaultPayloadTemplate() {
    return editSystemDefaultPayloadTemplate;
  }

  public void setEditSystemDefaultPayloadTemplate(boolean editSystemDefaultPayloadTemplate) {
    this.editSystemDefaultPayloadTemplate = editSystemDefaultPayloadTemplate;
  }

  public TestNotificationConfig getConfig() {
    return config;
  }

  public void setConfig(TestNotificationConfig config) {
    this.config = config;
  }
}
