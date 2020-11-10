package org.openelisglobal.notification.service;

import org.openelisglobal.result.valueholder.Result;

public interface ClientNotificationService {

    boolean shouldSendNotification(Result result);

    void createAndSendClientNotification(Result result);

}
