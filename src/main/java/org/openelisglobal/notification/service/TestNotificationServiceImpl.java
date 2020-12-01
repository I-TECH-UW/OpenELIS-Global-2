package org.openelisglobal.notification.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.clientresultsview.service.ClientResultsViewInfoService;
import org.openelisglobal.clientresultsview.valueholder.ClientResultsViewBean;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.dictionary.service.DictionaryService;
import org.openelisglobal.dictionary.valueholder.Dictionary;
import org.openelisglobal.notification.service.sender.ClientNotificationSender;
import org.openelisglobal.notification.valueholder.ClientNotification;
import org.openelisglobal.notification.valueholder.ClientResultsViewNotificationPayload;
import org.openelisglobal.notification.valueholder.EmailNotification;
import org.openelisglobal.notification.valueholder.NotificationPayloadTemplate;
import org.openelisglobal.notification.valueholder.NotificationPayloadTemplate.NotificationPayloadType;
import org.openelisglobal.notification.valueholder.SMSNotification;
import org.openelisglobal.notification.valueholder.TestNotificationConfig;
import org.openelisglobal.notification.valueholder.TestNotificationConfigOption;
import org.openelisglobal.notification.valueholder.TestNotificationConfigOption.NotificationMethod;
import org.openelisglobal.notification.valueholder.TestNotificationConfigOption.NotificationNature;
import org.openelisglobal.notification.valueholder.TestNotificationConfigOption.NotificationPersonType;
import org.openelisglobal.person.valueholder.Person;
import org.openelisglobal.result.valueholder.Result;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.typeoftestresult.service.TypeOfTestResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestNotificationServiceImpl implements TestNotificationService {

    @Autowired
    private ClientResultsViewInfoService clientResultsViewInfoService;
    @Autowired
    private NotificationPayloadTemplateService notificationPayloadTemplateService;
    @Autowired
    private SampleHumanService sampleHumanService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private TestNotificationConfigService testNotificationConfigService;

    @Value("${org.openelisglobal.ozeki.active:false}")
    private Boolean ozekiActive;

    @SuppressWarnings("rawtypes")
    @Autowired
    private List<ClientNotificationSender> notificationSenders;

    @PostConstruct
    public void init() {
        ensureNotificationsPayloadTemplatesExist(NotificationPayloadType.TEST_RESULT);
    }

    private void ensureNotificationsPayloadTemplatesExist(NotificationPayloadType testResult) {
        if (notificationPayloadTemplateService.getSystemDefaultPayloadTemplateForType(testResult) == null) {
            createSystemDefaultNotificationPayloadTemplate(NotificationPayloadType.TEST_RESULT);
        }
    }

    private NotificationPayloadTemplate createSystemDefaultNotificationPayloadTemplate(NotificationPayloadType type) {
        NotificationPayloadTemplate template = new NotificationPayloadTemplate();
        template.setMessageTemplate(
                "[testName] testing results have been finalized. If you are not awaiting test results please call XXXXXXXXXXX and delete this notice."
                        + "\n\n" + "[patientFirstName] [patientLastNameInitial]: [testResult]");
        template.setSubjectTemplate("[testName] Testing Results");
        template.setSysUserId("1");
        template.setType(type);
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
    @Async
    @Transactional(readOnly = true)
    public void createAndSendNotificationsToConfiguredSources(NotificationNature nature, Result result) {
        Optional<TestNotificationConfig> testNotificationConfig = testNotificationConfigService
                .getTestNotificationConfigForTestId(result.getAnalysis().getTest().getId());
        if (testNotificationConfig.isEmpty()) {
            // test hasn't been configured to send notifications
            return;
        }

        switch (nature) {
        case RESULT_VALIDATION:
            createAndSendResultsNotificationsToConfiguredSources(nature, result, testNotificationConfig);
        default:
        }

    }

    private void createAndSendResultsNotificationsToConfiguredSources(NotificationNature nature, Result result,
            Optional<TestNotificationConfig> testNotificationConfig) {
        ClientResultsViewBean resultsViewInfo = new ClientResultsViewBean(result);
        resultsViewInfo.setSysUserId("1");
        resultsViewInfo = clientResultsViewInfoService.save(resultsViewInfo);

        String resultForDisplay = "";

        if (TypeOfTestResultServiceImpl.ResultType.isMultiSelectVariant(result.getResultType())) {
            // TODO
        } else if (TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(result.getResultType())) {
            Dictionary dictionary = dictionaryService.getDataForId(result.getValue());
            resultForDisplay = dictionary.getLocalizedName();

            if ("unknown".equals(resultForDisplay)) {
                resultForDisplay = GenericValidator.isBlankOrNull(dictionary.getLocalAbbreviation())
                        ? dictionary.getDictEntry()
                        : dictionary.getLocalAbbreviation();
            }
//        resultForDisplay = dictionaryService.getDataForId(result.getValue()).getDictEntry();
        } else if (TypeOfTestResultServiceImpl.ResultType.isNumeric(result.getResultType())) {
            resultForDisplay = result.getValue();
        } else if (TypeOfTestResultServiceImpl.ResultType.isTextOnlyVariant(result.getResultType())) {
            resultForDisplay = result.getValue();
        }
        for (NotificationMethod methodType : NotificationMethod.values()) {
            if (systemEnabledForMethod(methodType)) {
                createAndSendNotificationsConfiguredForTest(nature, methodType, testNotificationConfig.get(),
                        resultForDisplay, resultsViewInfo);
            }
        }
    }

    private void createAndSendNotificationsConfiguredForTest(NotificationNature nature, NotificationMethod methodType,
            TestNotificationConfig testNotificationConfig, String resultForDisplay,
            ClientResultsViewBean resultsViewInfo) {
        for (NotificationPersonType personType : NotificationPersonType.values()) {
            TestNotificationConfigOption option = testNotificationConfig.getOptionFor(nature, methodType, personType);
            if (option.getActive()) {
                createAndSendNotificationToPerson(nature, methodType, personType, option, resultForDisplay,
                        resultsViewInfo);
            }

        }

    }

    private void createAndSendNotificationToPerson(NotificationNature nature, NotificationMethod methodType,
            NotificationPersonType personType, TestNotificationConfigOption option, String resultForDisplay,
            ClientResultsViewBean resultsViewInfo) {
        Person testPerson = sampleHumanService
                .getPatientForSample(resultsViewInfo.getResult().getAnalysis().getSampleItem().getSample()).getPerson();
        Person receiverPerson = null;
        if (NotificationPersonType.CLIENT.equals(personType)) {
            receiverPerson = testPerson;
        } else if (NotificationPersonType.PROVIDER.equals(personType)) {
            receiverPerson = sampleHumanService
                    .getProviderForSample(resultsViewInfo.getResult().getAnalysis().getSampleItem().getSample())
                    .getPerson();
        }
        if (NotificationMethod.EMAIL.equals(methodType) && canSendEmail(receiverPerson)) {
            createAndSendResultsNotificationEmail(testPerson, receiverPerson, option, resultForDisplay,
                    resultsViewInfo);
        } else if (NotificationMethod.SMS.equals(methodType) && canSendSMS(receiverPerson)) {
            createAndSendResultsNotificationSMS(testPerson, receiverPerson, option, resultForDisplay, resultsViewInfo);
        }

    }

    private void createAndSendResultsNotificationSMS(Person testPerson, Person receiverPerson,
            TestNotificationConfigOption option,
            String resultForDisplay, ClientResultsViewBean resultsViewInfo) {
        try {
            SMSNotification smsNotification = new SMSNotification();
            String phoneNumber = "";
            for (char ch : receiverPerson.getPrimaryPhone().toCharArray()) {
                // 5
                if (Character.isDigit(ch)) {
                    phoneNumber = phoneNumber + ch;
                }
            }
            smsNotification.setReceiverPhoneNumber(phoneNumber);

            NotificationPayloadTemplate template = option.getPayloadTemplate() == null
                    ? option.getTestNotificationConfig().getDefaultPayloadTemplate()
                    : option.getPayloadTemplate();
            // TODO figure out where to store address and how to retrieve
            smsNotification.setPayload(new ClientResultsViewNotificationPayload(resultsViewInfo.getPassword(),
                    "someAddress", resultsViewInfo.getResult().getAnalysis().getTest().getName(), resultForDisplay,
                    testPerson.getFirstName(), testPerson.getLastName().substring(0, 1), template));

            sendNotification(smsNotification);
//                    getSenderForNotification(smsNotification).send(smsNotification);
        } catch (RuntimeException e) {
            LogEvent.logError(this.getClass().getName(), "createAndSendResultsNotificationSMS",
                    "could not send sms notification");
            LogEvent.logErrorStack(e);
        }

    }

    private void createAndSendResultsNotificationEmail(Person testPerson, Person receiverPerson,
            TestNotificationConfigOption option,
            String resultForDisplay, ClientResultsViewBean resultsViewInfo) {
        try {
            EmailNotification emailNotification = new EmailNotification();
            emailNotification.setRecipientEmailAddress(receiverPerson.getEmail());

            NotificationPayloadTemplate template;
            if (option.getPayloadTemplate() == null
                    && option.getTestNotificationConfig().getDefaultPayloadTemplate() == null) {
                template = notificationPayloadTemplateService
                        .getSystemDefaultPayloadTemplateForType(NotificationPayloadType.TEST_RESULT);
            } else if (option.getPayloadTemplate() == null) {
                template = option.getTestNotificationConfig().getDefaultPayloadTemplate();
            } else {
                template = option.getPayloadTemplate();
            }
            // TODO figure out where to store address and how to retrieve
            emailNotification.setPayload(new ClientResultsViewNotificationPayload(resultsViewInfo.getPassword(),
                    "someAddress", resultsViewInfo.getResult().getAnalysis().getTest().getName(), resultForDisplay,
                    testPerson.getFirstName(), testPerson.getLastName().substring(0, 1), template));

            sendNotification(emailNotification);
        } catch (RuntimeException e) {
            // TODO add redundancy mechanism in case can't reach SMTP server
            LogEvent.logError(this.getClass().getName(), "createAndSendResultsNotificationEmail",
                    "could not send email notification");
            LogEvent.logError(e);
        }
    }

    private boolean systemEnabledForMethod(NotificationMethod methodType) {
        switch (methodType) {
        case EMAIL:
            return emailEnabledForSystem();
        case SMS:
            return smsEnabledForSystem();
        default:
            return false;
        }
    }

    private boolean smsEnabledForSystem() {
        return ConfigurationProperties.getInstance().getPropertyValue(Property.PATIENT_RESULTS_BMP_SMS_ENABLED)
                .equals(Boolean.TRUE.toString())
                || ConfigurationProperties.getInstance().getPropertyValue(Property.PATIENT_RESULTS_SMPP_SMS_ENABLED)
                        .equals(Boolean.TRUE.toString())
                || ozekiActive;
    }

    private boolean emailEnabledForSystem() {
        return ConfigurationProperties.getInstance().getPropertyValue(Property.PATIENT_RESULTS_SMTP_ENABLED)
                .equals(Boolean.TRUE.toString());
    }

    private boolean canSendSMS(Person person) {
        boolean canSend = person != null && !GenericValidator.isBlankOrNull(person.getPrimaryPhone());
        if (!canSend) {
            LogEvent.logWarn(this.getClass().getName(), "canSendSMS",
                    "can't send SMS to person as they have no phone on file");
        }
        return canSend;
    }

    private boolean canSendEmail(Person person) {
        boolean canSend = person != null && !GenericValidator.isBlankOrNull(person.getEmail());
        if (!canSend) {
            LogEvent.logWarn(this.getClass().getName(), "canSendEmail",
                    "can't send email to person as they have no email on file");
        }
        return canSend;
    }

}
