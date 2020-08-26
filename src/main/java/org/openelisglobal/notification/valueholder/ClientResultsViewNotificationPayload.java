package org.openelisglobal.notification.valueholder;

import org.openelisglobal.notification.service.NotificationPayloadTemplateService;
import org.openelisglobal.notification.valueholder.NotificationPayloadTemplate.NotificationPayloadType;
import org.openelisglobal.spring.util.SpringContext;

public class ClientResultsViewNotificationPayload implements NotificationPayload {

    private String accessPassword;

    private String accessAddress;

    private String testName;

    private String testResult;

    private String patientFirstName;

    private String patientLastNameInitial;

    private NotificationPayloadTemplate payloadTemplate;

    public ClientResultsViewNotificationPayload(String accessPassword, String accessAddress, String testName,
            String testResult, String patientFirstName, String patientLastNameInitial) {
        this.accessPassword = accessPassword;
        this.accessAddress = accessAddress;
        this.testName = testName;
        this.testResult = testResult;
        this.patientFirstName = patientFirstName;
        this.patientLastNameInitial = patientLastNameInitial;
        payloadTemplate = SpringContext.getBean(NotificationPayloadTemplateService.class)
                .getForNotificationPayloadType(NotificationPayloadType.CLIENT_RESULTS);
    }

    @Override
    public String getMessage() {
        String message = payloadTemplate.getMessageTemplate();
        message = message.replaceAll("\\[testName\\]", testName);
        message = message.replaceAll("\\[accessPassword\\]", accessPassword);
        message = message.replaceAll("\\[accessAddress\\]", accessAddress);
        message = message.replaceAll("\\[testResult\\]", testResult);
        message = message.replaceAll("\\[patientFirstName\\]", patientFirstName);
        message = message.replaceAll("\\[patientLastNameInitial\\]", patientLastNameInitial);
        return message;
    }

    @Override
    public String getSubject() {
        String subject = payloadTemplate.getSubjectTemplate();
        subject = subject.replaceAll("\\[testName\\]", testName);
        return subject;
    }

}
