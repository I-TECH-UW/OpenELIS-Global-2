package org.openelisglobal.notification.service;

import org.openelisglobal.notification.valueholder.TestNotificationConfigOption.NotificationNature;
import org.openelisglobal.result.valueholder.Result;

public interface TestNotificationService {

    void createAndSendNotificationsToConfiguredSources(NotificationNature resultValidation, Result result);

}
