package org.openelisglobal.notification.service.sender;

import org.openelisglobal.notification.valueholder.SMSNotification;
import org.springframework.stereotype.Component;

@Component
public class SMSNotificationSender implements ClientNotificationSender<SMSNotification> {

    @Override
    public Class<SMSNotification> forClass() {
        return SMSNotification.class;
    }

    @Override
    public void send(SMSNotification notification) {
        // TODO Auto-generated method stub

    }

}
