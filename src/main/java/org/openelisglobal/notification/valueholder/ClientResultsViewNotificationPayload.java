package org.openelisglobal.notification.valueholder;

import org.openelisglobal.notification.service.NotificationPayloadTemplateService;
import org.openelisglobal.notification.valueholder.NotificationPayloadTemplate.NotificationPayloadType;
import org.openelisglobal.spring.util.SpringContext;

public class ClientResultsViewNotificationPayload implements NotificationPayload {

    private String accessPassword;

    private String accessAddress;

    private String testName;

    private NotificationPayloadTemplate payloadTemplate;

    public ClientResultsViewNotificationPayload(String accessPassword, String accessAddress, String testName) {
        this.accessPassword = accessPassword;
        this.accessAddress = accessAddress;
        this.testName = testName;
        payloadTemplate = SpringContext.getBean(NotificationPayloadTemplateService.class)
                .getForNotificationPayloadType(NotificationPayloadType.CLIENT_RESULTS);
    }

    @Override
    public String getMessage() {
        String message = payloadTemplate.getMessageTemplate();
        message = message.replaceAll("\\[testName\\]", testName);
        message = message.replaceAll("\\[accessPassword\\]", accessPassword);
        message = message.replaceAll("\\[accessAddress\\]", accessAddress);
        return message;
    }

    @Override
    public String getSubject() {
        String subject = payloadTemplate.getSubjectTemplate();
        subject = subject.replaceAll("\\[testName\\]", testName);
        return subject;
    }

}
