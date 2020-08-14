package org.openelisglobal.notification.service.sender;

import org.openelisglobal.notification.valueholder.ClientNotification;

public interface ClientNotificationSender<T extends ClientNotification> {

    public Class<T> forClass();

    public void send(T notification);

}
