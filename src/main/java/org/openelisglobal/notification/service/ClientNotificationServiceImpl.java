package org.openelisglobal.notification.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.openelisglobal.clientresultsview.service.ClientResultsViewInfoService;
import org.openelisglobal.clientresultsview.valueholder.ClientResultsViewBean;
import org.openelisglobal.notification.service.sender.ClientNotificationSender;
import org.openelisglobal.notification.valueholder.ClientNotification;
import org.openelisglobal.notification.valueholder.ClientResultsViewNotificationPayload;
import org.openelisglobal.notification.valueholder.EmailNotification;
import org.openelisglobal.notification.valueholder.NotificationPayloadTemplate;
import org.openelisglobal.notification.valueholder.NotificationPayloadTemplate.NotificationPayloadType;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.result.valueholder.Result;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientNotificationServiceImpl implements ClientNotificationService {

    @Autowired
    private ClientResultsViewInfoService clientResultsViewInfoService;
    @Autowired
    private NotificationPayloadTemplateService notificationPayloadTemplateService;
    @Autowired
    private SampleHumanService sampleHumanService;

    @SuppressWarnings("rawtypes")
    @Autowired
    private List<ClientNotificationSender> notificationSenders;

    @PostConstruct
    public void init() {
        ensureNotificationsPayloadTemplatesExist();
    }

    private void ensureNotificationsPayloadTemplatesExist() {
        for (NotificationPayloadType type : NotificationPayloadType.values()) {
            if (notificationPayloadTemplateService.getForNotificationPayloadType(type) == null) {
                createDefaultNotificationPayloadTemplate(type);
            }
        }
    }

    private NotificationPayloadTemplate createDefaultNotificationPayloadTemplate(NotificationPayloadType type) {
        NotificationPayloadTemplate template = new NotificationPayloadTemplate();
        template.setType(type);
        switch (type) {
        case CLIENT_RESULTS:
            template.setMessageTemplate(
                    "[testName] Testing Results have been finalized. If you are not awaiting test results please call XXXXXXXXXXX. and delete this notice. Otherwise, please click the link below and "
                            + "put in the following password: [accessPassword] when prompted. [accessAddress]");
            template.setSubjectTemplate("[testName] Testing Results");
            break;
        default:
            template.setMessageTemplate("");
            template.setSubjectTemplate("");
        }
        template.setSysUserId("1");
        notificationPayloadTemplateService.save(template);
        return template;

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void sendNotification(ClientNotification clientNotification) {
        for (ClientNotificationSender notificationSender : notificationSenders) {
            if (clientNotification.getClass().isAssignableFrom(notificationSender.forClass())) {
                notificationSender.send(clientNotification);
            }
        }
    }

    @Override
    public boolean shouldSendNotification(Result result) {
        // consider reading analysis in from database instead so it is guaranteed to
        // have a test object
        return result.getAnalysis().getTest().isNotifyResults();
    }

    @Override
    public void createAndSendClientNotification(Result result) {
        ClientResultsViewBean resultsViewInfo = new ClientResultsViewBean(result);
        resultsViewInfo.setSysUserId("1");
        resultsViewInfo = clientResultsViewInfoService.save(resultsViewInfo);

        Patient patient = sampleHumanService.getPatientForSample(result.getAnalysis().getSampleItem().getSample());

        // TODO get address somehow ( add externalConnection?)
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.setRecipientEmailAddress(patient.getPerson().getEmail());
        emailNotification.setPayload(new ClientResultsViewNotificationPayload(resultsViewInfo.getPassword(),
                "someAddress", result.getAnalysis().getTestName()));

        getSenderForNotification(emailNotification).send(emailNotification);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private <T extends ClientNotification> ClientNotificationSender<T> getSenderForNotification(
            ClientNotification emailNotification) {
        for (ClientNotificationSender sender : notificationSenders) {
            if (sender.forClass().equals(emailNotification.getClass())) {
                return sender;
            }
        }
        return null;
    }

}
