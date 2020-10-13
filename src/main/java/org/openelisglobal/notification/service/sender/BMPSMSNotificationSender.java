package org.openelisglobal.notification.service.sender;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.notification.valueholder.SMSNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BMPSMSNotificationSender {

    @Autowired
    private CloseableHttpClient httpClient;

    public void send(SMSNotification notification) {
        ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();

        String address = configurationProperties.getPropertyValue(Property.PATIENT_RESULTS_BMP_SMS_ADDRESS);
        String username = configurationProperties.getPropertyValue(Property.PATIENT_RESULTS_BMP_SMS_USERNAME);
        String password = configurationProperties.getPropertyValue(Property.PATIENT_RESULTS_BMP_SMS_PASSWORD);

        String getString = address + "?UserName=" + username + "&PassWord=" + password + "&UserData="
                + notification.getMessage() + "&Concatenated=0&Mode=0&Deferred=false&Number="
                + notification.getReceiverPhoneNumber() + "&Dsr=false";

        HttpGet getRequest = new HttpGet(getString);
        try (CloseableHttpResponse response = httpClient.execute(getRequest)) {
            LogEvent.logDebug(this.getClass().getName(), "send",
                    "response response status from BMP SMS: " + response.getStatusLine().getStatusCode());
        } catch (IOException e) {
            LogEvent.logErrorStack(e);
        }

    }

}
