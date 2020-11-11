package org.openelisglobal.notification.service.sender;

import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.notification.valueholder.SMSNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SMSNotificationSender implements ClientNotificationSender<SMSNotification> {

    @Autowired
    private SMPPNotificationSender smppNotificationSender;
    @Autowired
    private BMPSMSNotificationSender bmpSMSNotificationSender;

    @Override
    public Class<SMSNotification> forClass() {
        return SMSNotification.class;
    }

    @Override
    public void send(SMSNotification notification) {
        ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
        if (Boolean.TRUE.toString().equalsIgnoreCase(
                configurationProperties.getPropertyValue(Property.PATIENT_RESULTS_SMPP_SMS_ENABLED))) {
            smppNotificationSender.send(notification);
        } else if (Boolean.TRUE.toString()
                .equalsIgnoreCase(configurationProperties.getPropertyValue(Property.PATIENT_RESULTS_BMP_SMS_ENABLED))) {
            bmpSMSNotificationSender.send(notification);
        }
    }

}
