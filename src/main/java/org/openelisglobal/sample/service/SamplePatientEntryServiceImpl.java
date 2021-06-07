package org.openelisglobal.sample.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.address.service.OrganizationAddressService;
import org.openelisglobal.address.valueholder.OrganizationAddress;
import org.openelisglobal.analysis.service.AnalysisService;
import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.formfields.FormFields;
import org.openelisglobal.common.formfields.FormFields.Field;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.SampleAddService.SampleTestCollection;
import org.openelisglobal.common.services.StatusService.AnalysisStatus;
import org.openelisglobal.common.services.TableIdService;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.dataexchange.service.order.ElectronicOrderService;
import org.openelisglobal.notification.service.AnalysisNotificationConfigService;
import org.openelisglobal.notification.service.TestNotificationConfigService;
import org.openelisglobal.notification.valueholder.AnalysisNotificationConfig;
import org.openelisglobal.notification.valueholder.NotificationConfigOption;
import org.openelisglobal.notification.valueholder.NotificationConfigOption.NotificationMethod;
import org.openelisglobal.notification.valueholder.NotificationConfigOption.NotificationNature;
import org.openelisglobal.notification.valueholder.NotificationConfigOption.NotificationPersonType;
import org.openelisglobal.notification.valueholder.TestNotificationConfig;
import org.openelisglobal.observationhistory.service.ObservationHistoryService;
import org.openelisglobal.observationhistory.valueholder.ObservationHistory;
import org.openelisglobal.organization.service.OrganizationService;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.panel.valueholder.Panel;
import org.openelisglobal.patient.action.bean.PatientManagementInfo;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.provider.service.ProviderService;
import org.openelisglobal.referral.action.beanitems.ReferralItem;
import org.openelisglobal.referral.service.ReferralResultService;
import org.openelisglobal.referral.service.ReferralService;
import org.openelisglobal.referral.service.ReferralSetService;
import org.openelisglobal.referral.service.ReferralTypeService;
import org.openelisglobal.referral.valueholder.Referral;
import org.openelisglobal.referral.valueholder.ReferralResult;
import org.openelisglobal.referral.valueholder.ReferralSet;
import org.openelisglobal.referral.valueholder.ReferralStatus;
import org.openelisglobal.referral.valueholder.ReferralType;
import org.openelisglobal.requester.service.SampleRequesterService;
import org.openelisglobal.requester.valueholder.SampleRequester;
import org.openelisglobal.result.service.ResultService;
import org.openelisglobal.result.valueholder.Result;
import org.openelisglobal.sample.action.util.SamplePatientUpdateData;
import org.openelisglobal.sample.form.SamplePatientEntryForm;
import org.openelisglobal.sample.valueholder.SampleAdditionalField;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.sampleitem.service.SampleItemService;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.test.service.TestSectionService;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.test.valueholder.Test;
import org.openelisglobal.test.valueholder.TestSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SamplePatientEntryServiceImpl implements SamplePatientEntryService {

    private static final String DEFAULT_ANALYSIS_TYPE = "MANUAL";

    @Autowired
    private OrganizationAddressService organizationAddressService;
    @Autowired
    private TestSectionService testSectionService;
    @Autowired
    private ElectronicOrderService electronicOrderService;
    @Autowired
    private ObservationHistoryService observationHistoryService;
    @Autowired
    private PersonService personService;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private SampleService sampleService;
    @Autowired
    private SampleHumanService sampleHumanService;
    @Autowired
    private SampleItemService sampleItemService;
    @Autowired
    private AnalysisService analysisService;
    @Autowired
    private TestService testService;
    @Autowired
    private SampleRequesterService sampleRequesterService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private TestNotificationConfigService testNotificationConfigService;
    @Autowired
    private AnalysisNotificationConfigService analysisNotificationConfigService;
    @Autowired
    private ReferralSetService referralSetService;
    @Autowired
    private ReferralService referralService;
    @Autowired
    private ReferralResultService referralResultService;
    @Autowired
    private ReferralTypeService referralTypeService;
    @Autowired
    private ResultService resultService;

    private String REFERRAL_CONFORMATION_ID;

    @PostConstruct
    public void init() {
        ReferralType referralType = referralTypeService.getReferralTypeByName("Confirmation");
        if (referralType != null) {
            REFERRAL_CONFORMATION_ID = referralType.getId();
        } else {
            REFERRAL_CONFORMATION_ID = null;
        }
    }

    @Transactional
    @Override
    public void persistData(SamplePatientUpdateData updateData, PatientManagementUpdate patientUpdate,
            PatientManagementInfo patientInfo, SamplePatientEntryForm form, HttpServletRequest request) {
        boolean useInitialSampleCondition = FormFields.getInstance().useField(Field.InitialSampleCondition);
        boolean useSampleNature = FormFields.getInstance().useField(Field.SampleNature);

        persistOrganizationData(updateData);

        if (updateData.isSavePatient()) {
            patientUpdate.persistPatientData(patientInfo);
        }

        updateData.setPatientId(patientUpdate.getPatientId(form));

        persistProviderData(updateData);
        persistSampleData(updateData);
        persistRequesterData(updateData);
        if (useInitialSampleCondition) {
            persistInitialSampleConditions(updateData);
        }
        if (useSampleNature) {
            persistSampleNature(updateData);
        }

        persistObservations(updateData);

        if (form.getUseReferral()) {
            List<Referral> referrals = new ArrayList<>();
            List<ReferralSet> referralSets = new ArrayList<>();
            for (ReferralItem referralItem: form.getReferralItems()) {
                Result result = new Result();
                result.setSysUserId("1");

                Referral referral = new Referral();
                referral.setFhirUuid(UUID.randomUUID());
                referral.setStatus(ReferralStatus.SENT);
                referral.setSysUserId(updateData.getCurrentUserId());
                referral.setReferralTypeId(REFERRAL_CONFORMATION_ID);

                referral.setRequestDate(new Timestamp(new Date().getTime()));
                referral.setSentDate(
                        DateUtil.convertStringDateToTruncatedTimestamp(referralItem.getReferredSendDate()));
                referral.setRequesterName(referralItem.getReferrer());
                referral.setOrganization(organizationService.get(referralItem.getReferredInstituteId()));
                for (SampleTestCollection sampleItemTest : updateData.getSampleItemsTests()) {
                    for (Analysis analysis : sampleItemTest.analysises) {
                        if (referralItem.getReferredTestId().equals(analysis.getTest().getId())) {
                            referral.setAnalysis(analysis);

                            String testResultType = testService.getResultType(analysis.getTest());
                            result.setResultType(testResultType);
                            result.setAnalysis(analysis);
                        }
                    }
                }
                referral.setReferralReasonId(referralItem.getReferralReasonId());

                referralService.insert(referral);
                resultService.insert(result);
                referrals.add(referral);
                ReferralResult referralResult = new ReferralResult();
                referralResult.setReferralId(referral.getId());
                referralResult.setSysUserId(updateData.getCurrentUserId());
                referralResult.setTestId(referralItem.getReferredTestId());
                referralResult.setResult(result);
                referralResultService.insert(referralResult);

                ReferralSet referralSet = new ReferralSet();
                referralSet.setReferral(referral);
                referralSet.setExistingReferralResults(Arrays.asList(referralResult));
                referralSets.add(referralSet);
            }
            referralSetService.updateReferralSets(referralSets, new ArrayList<>(), new HashSet<>(), new ArrayList<>(),
                    updateData.getCurrentUserId());
        }

        request.getSession().setAttribute("lastAccessionNumber", updateData.getAccessionNumber());
        request.getSession().setAttribute("lastPatientId", updateData.getPatientId());

    }

    private void persistObservations(SamplePatientUpdateData updateData) {

        for (ObservationHistory observation : updateData.getObservations()) {
            observation.setSampleId(updateData.getSample().getId());
            observation.setPatientId(updateData.getPatientId());
            observationHistoryService.insert(observation);
        }
    }

    private void persistOrganizationData(SamplePatientUpdateData updateData) {
        Organization newOrganization = updateData.getNewOrganization();
        if (newOrganization != null) {
            organizationService.insert(newOrganization);
            organizationService.linkOrganizationAndType(newOrganization,
                    TableIdService.getInstance().REFERRING_ORG_TYPE_ID);
            if (updateData.getRequesterSite() != null) {
                updateData.getRequesterSite().setRequesterId(newOrganization.getId());
            }

            for (OrganizationAddress address : updateData.getOrgAddressExtra()) {
                address.setOrganizationId(newOrganization.getId());
                organizationAddressService.insert(address);
            }
        }

        if (updateData.getCurrentOrganization() != null) {
            organizationService.update(updateData.getCurrentOrganization());
        }
//        newOrganization = updateData.getNewOrganizationDepartment();
//        if (newOrganization != null) {
//            organizationService.insert(newOrganization);
//            organizationService.linkOrganizationAndType(newOrganization,
//                    TableIdService.getInstance().REFERRING_ORG_TYPE_ID);
//            if (updateData.getRequesterSite() != null) {
//                updateData.getRequesterSite().setRequesterId(newOrganization.getId());
//            }
//
//            for (OrganizationAddress address : updateData.getOrgAddressExtra()) {
//                address.setOrganizationId(newOrganization.getId());
//                organizationAddressService.insert(address);
//            }
//        }
//
//        if (updateData.getCurrentOrganizationDepartment() != null) {
//            organizationService.update(updateData.getCurrentOrganizationDepartment());
//        }

    }

    private void persistProviderData(SamplePatientUpdateData updateData) {
        if (updateData.getProviderPerson() != null && updateData.getProvider() != null) {

            personService.insert(updateData.getProviderPerson());
            updateData.getProvider().setPerson(updateData.getProviderPerson());

            providerService.insert(updateData.getProvider());
        }
    }

    private void persistSampleData(SamplePatientUpdateData updateData) {
        String analysisRevision = SystemConfiguration.getInstance().getAnalysisDefaultRevision();

        updateData.getSample().setFhirUuid(UUID.randomUUID());
        sampleService.insertDataWithAccessionNumber(updateData.getSample());

        for (SampleAdditionalField field : updateData.getSampleFields()) {
            field.setSample(updateData.getSample());
            sampleService.saveSampleAdditionalField(field);
        }
        // if (!GenericValidator.isBlankOrNull(projectId)) {
        // persistSampleProject();
        // }

        for (SampleTestCollection sampleTestCollection : updateData.getSampleItemsTests()) {
            if (GenericValidator.isBlankOrNull(sampleTestCollection.item.getFhirUuidAsString())) {
                sampleTestCollection.item.setFhirUuid(UUID.randomUUID());
            }
            sampleItemService.insert(sampleTestCollection.item);
            sampleTestCollection.analysises = new ArrayList<>();
            for (Test test : sampleTestCollection.tests) {
                test = testService.get(test.getId());

                Analysis analysis = populateAnalysis(analysisRevision, sampleTestCollection, test,
                        sampleTestCollection.testIdToUserSectionMap.get(test.getId()),
                        sampleTestCollection.testIdToUserSampleTypeMap.get(test.getId()), updateData);
                analysisService.insert(analysis);
                sampleTestCollection.analysises.add(analysis);

                if (updateData.getCustomNotificationLogic()) {
                    persistAnalysisNotificationConfigs(analysis, updateData);
                }
            }

        }

        updateData.buildSampleHuman();

        sampleHumanService.insert(updateData.getSampleHuman());

        if (updateData.getElectronicOrder() != null) {
            electronicOrderService.update(updateData.getElectronicOrder());
        }
    }

    /*
     * private void persistSampleProject() throws LIMSRuntimeException {
     * SampleProjectDAO sampleProjectDAO = new SampleProjectDAOImpl(); ProjectDAO
     * projectDAO = new ProjectDAOImpl(); Project project = new Project(); //
     * project.setId(projectId); projectDAO.getData(project);
     *
     * SampleProject sampleProject = new SampleProject();
     * sampleProject.setProject(project); sampleProject.setSample(sample);
     * sampleProject.setSysUserId(getSysUserId(request));
     * sampleProjectDAO.insertData(sampleProject); }
     */

    private void persistAnalysisNotificationConfigs(Analysis analysis, SamplePatientUpdateData updateData) {
        Optional<TestNotificationConfig> testNotificationConfig = testNotificationConfigService
                .getTestNotificationConfigForTestId(analysis.getTest().getId());
        AnalysisNotificationConfig analysisNotificationConfig = new AnalysisNotificationConfig();
        analysisNotificationConfig.setAnalysis(analysis);
        if (testNotificationConfig.isPresent()) {
            analysisNotificationConfig
                    .setDefaultPayloadTemplate(testNotificationConfig.get().getDefaultPayloadTemplate());
        }

        this.persistAnalysisNotificationConfig(analysis, updateData.getPatientEmailNotificationTestIds(),
                analysisNotificationConfig, testNotificationConfig, NotificationMethod.EMAIL,
                NotificationPersonType.PATIENT);
        this.persistAnalysisNotificationConfig(analysis, updateData.getPatientSMSNotificationTestIds(),
                analysisNotificationConfig, testNotificationConfig, NotificationMethod.SMS,
                NotificationPersonType.PATIENT);
        this.persistAnalysisNotificationConfig(analysis, updateData.getProviderEmailNotificationTestIds(),
                analysisNotificationConfig, testNotificationConfig, NotificationMethod.EMAIL,
                NotificationPersonType.PROVIDER);
        this.persistAnalysisNotificationConfig(analysis, updateData.getProviderSMSNotificationTestIds(),
                analysisNotificationConfig, testNotificationConfig, NotificationMethod.SMS,
                NotificationPersonType.PROVIDER);
        analysisNotificationConfigService.save(analysisNotificationConfig);
    }

    private void persistAnalysisNotificationConfig(Analysis analysis, List<String> testIds,
            AnalysisNotificationConfig analysisNotificationConfig,
            Optional<TestNotificationConfig> testNotificationConfig, NotificationMethod method,
            NotificationPersonType personType) {
        NotificationNature notificationNature = NotificationNature.RESULT_VALIDATION;
        NotificationConfigOption nto = analysisNotificationConfig.getOptionFor(notificationNature, method, personType);
        nto.setNotificationMethod(method);
        nto.setNotificationNature(notificationNature);
        nto.setNotificationPersonType(personType);
        if (testIds.contains(analysis.getTest().getId())) {
            nto.setActive(true);
        } else {
            nto.setActive(false);
        }

        if (testNotificationConfig.isPresent()) {
            NotificationConfigOption nto2 = testNotificationConfig.get().getOptionFor(notificationNature, method,
                    personType);
            nto.setPayloadTemplate(nto2.getPayloadTemplate());
            nto.setAdditionalContacts(new ArrayList<>());
            nto.getAdditionalContacts().addAll(nto2.getAdditionalContacts());
        }

    }

    private void persistRequesterData(SamplePatientUpdateData updateData) {
        if (updateData.getProviderPerson() != null && !org.apache.commons.validator.GenericValidator
                .isBlankOrNull(updateData.getProviderPerson().getId())) {
            SampleRequester sampleRequester = new SampleRequester();
            sampleRequester.setRequesterId(updateData.getProviderPerson().getId());
            sampleRequester.setRequesterTypeId(TableIdService.getInstance().PROVIDER_REQUESTER_TYPE_ID);
            sampleRequester.setSampleId(Long.parseLong(updateData.getSample().getId()));
            sampleRequester.setSysUserId(updateData.getCurrentUserId());
            sampleRequesterService.insert(sampleRequester);
        }

        if (updateData.getRequesterSite() != null) {
            updateData.getRequesterSite().setSampleId(Long.parseLong(updateData.getSample().getId()));
            if (updateData.getNewOrganization() != null) {
                updateData.getRequesterSite().setRequesterId(updateData.getNewOrganization().getId());
            }
            sampleRequesterService.insert(updateData.getRequesterSite());
        }

        if (updateData.getRequesterSiteDepartment() != null) {
            updateData.getRequesterSiteDepartment().setSampleId(Long.parseLong(updateData.getSample().getId()));
//            if (updateData.getNewOrganizationDepartment() != null) {
//                updateData.getRequesterSite().setRequesterId(updateData.getNewOrganizationDepartment().getId());
//            }
            sampleRequesterService.insert(updateData.getRequesterSiteDepartment());
        }
    }

    private void persistInitialSampleConditions(SamplePatientUpdateData updateData) {

        for (SampleTestCollection sampleTestCollection : updateData.getSampleItemsTests()) {
            List<ObservationHistory> initialConditions = sampleTestCollection.initialSampleConditionIdList;

            if (initialConditions != null) {
                for (ObservationHistory observation : initialConditions) {
                    observation.setSampleId(sampleTestCollection.item.getSample().getId());
                    observation.setSampleItemId(sampleTestCollection.item.getId());
                    observation.setPatientId(updateData.getPatientId());
                    observation.setSysUserId(updateData.getCurrentUserId());
                    observationHistoryService.insert(observation);
                }
            }
        }
    }

    private void persistSampleNature(SamplePatientUpdateData updateData) {

        for (SampleTestCollection sampleTestCollection : updateData.getSampleItemsTests()) {
            ObservationHistory sampleNature = sampleTestCollection.sampleNature;

            if (sampleNature != null) {
                sampleNature.setSampleId(sampleTestCollection.item.getSample().getId());
                sampleNature.setSampleItemId(sampleTestCollection.item.getId());
                sampleNature.setPatientId(updateData.getPatientId());
                sampleNature.setSysUserId(updateData.getCurrentUserId());
                observationHistoryService.insert(sampleNature);
            }
        }
    }

    private Analysis populateAnalysis(String analysisRevision, SampleTestCollection sampleTestCollection, Test test,
            String userSelectedTestSection, String sampleTypeName, SamplePatientUpdateData updateData) {
        java.sql.Date collectionDateTime = DateUtil.convertStringDateTimeToSqlDate(sampleTestCollection.collectionDate);
        TestSection testSection = test.getTestSection();
        if (!org.apache.commons.validator.GenericValidator.isBlankOrNull(userSelectedTestSection)) {
            testSection = testSectionService.get(userSelectedTestSection);
        }

        Panel panel = updateData.getSampleAddService().getPanelForTest(test);

        Analysis analysis = new Analysis();
        analysis.setTest(test);
        analysis.setPanel(panel);
        analysis.setIsReportable(test.getIsReportable());
        analysis.setAnalysisType(DEFAULT_ANALYSIS_TYPE);
        analysis.setSampleItem(sampleTestCollection.item);
        analysis.setSysUserId(sampleTestCollection.item.getSysUserId());
        analysis.setRevision(analysisRevision);
        analysis.setStartedDate(collectionDateTime == null ? DateUtil.getNowAsSqlDate() : collectionDateTime);
        analysis.setStatusId(SpringContext.getBean(IStatusService.class).getStatusID(AnalysisStatus.NotStarted));
        if (!org.apache.commons.validator.GenericValidator.isBlankOrNull(sampleTypeName)) {
            analysis.setSampleTypeName(sampleTypeName);
        }
        analysis.setTestSection(testSection);
        // this will be used as an identifier for the service request as well
        analysis.setFhirUuid(UUID.randomUUID());
        return analysis;
    }
}
