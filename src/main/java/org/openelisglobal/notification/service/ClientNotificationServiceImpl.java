package org.openelisglobal.notification.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.clientresultsview.service.ClientResultsViewInfoService;
import org.openelisglobal.clientresultsview.valueholder.ClientResultsViewBean;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.dictionary.service.DictionaryService;
import org.openelisglobal.notification.service.sender.ClientNotificationSender;
import org.openelisglobal.notification.valueholder.ClientNotification;
import org.openelisglobal.notification.valueholder.ClientResultsViewNotificationPayload;
import org.openelisglobal.notification.valueholder.EmailNotification;
import org.openelisglobal.notification.valueholder.NotificationPayloadTemplate;
import org.openelisglobal.notification.valueholder.NotificationPayloadTemplate.NotificationPayloadType;
import org.openelisglobal.notification.valueholder.SMSNotification;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.result.valueholder.Result;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.typeoftestresult.service.TypeOfTestResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ClientNotificationServiceImpl implements ClientNotificationService {

    @Autowired
    private ClientResultsViewInfoService clientResultsViewInfoService;
    @Autowired
    private NotificationPayloadTemplateService notificationPayloadTemplateService;
    @Autowired
    private SampleHumanService sampleHumanService;
    @Autowired
    private DictionaryService dictionaryService;

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
                    "[testName] testing results have been finalized. If you are not awaiting test results please call XXXXXXXXXXX and delete this notice."
                            + "\n\n" + "[patientFirstName] [patientLastNameInitial]: [testResult]");
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
    @Async
    // requires new ensures that even if sending the notification fails, the
    // operation calling this will still be saved
    public void createAndSendClientNotification(Result result) {
        ClientResultsViewBean resultsViewInfo = new ClientResultsViewBean(result);
        resultsViewInfo.setSysUserId("1");
        resultsViewInfo = clientResultsViewInfoService.save(resultsViewInfo);

        Patient patient = sampleHumanService.getPatientForSample(result.getAnalysis().getSampleItem().getSample());

        String resultForDisplay = "";

        if (TypeOfTestResultServiceImpl.ResultType.isMultiSelectVariant(result.getResultType())) {
            // TODO
        } else if (TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(result.getResultType())) {
            resultForDisplay = dictionaryService.getDataForId(result.getValue()).getDictEntry();
        } else if (TypeOfTestResultServiceImpl.ResultType.isNumeric(result.getResultType())) {
            resultForDisplay = result.getValue();
        } else if (TypeOfTestResultServiceImpl.ResultType.isTextOnlyVariant(result.getResultType())) {
            resultForDisplay = result.getValue();
        }

        try {
            if (!GenericValidator.isBlankOrNull(patient.getPerson().getEmail()) && ConfigurationProperties.getInstance()
                    .getPropertyValue(Property.PATIENT_RESULTS_SMTP_ENABLED).equals(Boolean.TRUE.toString())) {
                EmailNotification emailNotification = new EmailNotification();
                emailNotification.setRecipientEmailAddress(patient.getPerson().getEmail());
                // TODO figure out where to store address and how to retrieve
                emailNotification.setPayload(new ClientResultsViewNotificationPayload(resultsViewInfo.getPassword(),
                        "someAddress", result.getAnalysis().getTest().getName(), resultForDisplay,
                        patient.getPerson().getFirstName(), patient.getPerson().getLastName().substring(0, 1)));

                getSenderForNotification(emailNotification).send(emailNotification);
            }
        } catch (RuntimeException e) {
            // TODO add redundancy mechanism in case can't reach SMTP server
            LogEvent.logError(this.getClass().getName(), "createAndSendClientNotification",
                    "could not send email notification");
            LogEvent.logErrorStack(e);
        }

        try {
            if (!GenericValidator.isBlankOrNull(patient.getPerson().getPrimaryPhone())
                    && (ConfigurationProperties.getInstance().getPropertyValue(Property.PATIENT_RESULTS_BMP_SMS_ENABLED)
                            .equals(Boolean.TRUE.toString())
                            || ConfigurationProperties.getInstance()
                                    .getPropertyValue(Property.PATIENT_RESULTS_SMPP_SMS_ENABLED)
                                    .equals(Boolean.TRUE.toString()))) {
                SMSNotification smsNotification = new SMSNotification();
                String phoneNumber = "";
                for (char ch : patient.getPerson().getPrimaryPhone().toCharArray()) {
                    // 5
                    if (Character.isDigit(ch)) {
                        phoneNumber = phoneNumber + ch;
                    }
                }
                smsNotification.setReceiverPhoneNumber(phoneNumber);
                // TODO figure out where to store address and how to retrieve
                smsNotification.setPayload(new ClientResultsViewNotificationPayload(resultsViewInfo.getPassword(),
                        "someAddress", result.getAnalysis().getTest().getName(), resultForDisplay,
                        patient.getPerson().getFirstName(), patient.getPerson().getLastName().substring(0, 1)));

                getSenderForNotification(smsNotification).send(smsNotification);
            }
        } catch (RuntimeException e) {
            LogEvent.logError(this.getClass().getName(), "createAndSendClientNotification",
                    "could not send sms notification");
            LogEvent.logErrorStack(e);
        }

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
