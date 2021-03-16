/*
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 *
 * The Original Code is OpenELIS code.
 *
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 */

package org.openelisglobal.common.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.formfields.FormFields;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.observationhistory.service.ObservationHistoryService;
import org.openelisglobal.observationhistory.service.ObservationHistoryServiceImpl.ObservationType;
import org.openelisglobal.observationhistory.valueholder.ObservationHistory;
import org.openelisglobal.observationhistory.valueholder.ObservationHistory.ValueType;
import org.openelisglobal.organization.service.OrganizationService;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.person.valueholder.Person;
import org.openelisglobal.requester.valueholder.SampleRequester;
import org.openelisglobal.sample.bean.SampleOrderItem;
import org.openelisglobal.sample.service.SampleService;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.spring.util.SpringContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 */
@Service
@Scope("prototype")
@DependsOn({ "springContext" })
public class SampleOrderService {

    private static SampleService sampleService = SpringContext.getBean(SampleService.class);
    private static OrganizationService orgService = SpringContext.getBean(OrganizationService.class);
    private ObservationHistoryService observationHistoryService = SpringContext
            .getBean(ObservationHistoryService.class);

    boolean needRequesterList = FormFields.getInstance().useField(FormFields.Field.RequesterSiteList);
    private boolean needPaymentOptions = ConfigurationProperties.getInstance()
            .isPropertyValueEqual(ConfigurationProperties.Property.TRACK_PATIENT_PAYMENT, "true");
    private boolean needTestLocationCode = FormFields.getInstance().useField(FormFields.Field.TEST_LOCATION_CODE);
    private SampleOrderItem sampleOrder;
    private Sample sample;
    private boolean readOnly = false;

    public SampleOrderService() {
        sample = null;
    }

    public SampleOrderService(Sample sample) {
        this.sample = sample;
    }

    public SampleOrderService(SampleOrderItem sampleOrder) {
        this.sampleOrder = sampleOrder;
    }

    public SampleOrderService(String accessionNumber, boolean readOnly) {
        sample = sampleService.getSampleByAccessionNumber(accessionNumber);
        this.readOnly = readOnly;
    }

    private SampleOrderItem getBaseSampleOrderItem() {
        SampleOrderItem orderItems = new SampleOrderItem();

        String dateAsText = DateUtil.getCurrentDateAsText();
        orderItems.setReceivedDateForDisplay(dateAsText);
        orderItems.setRequestDate(dateAsText);
        orderItems.setReceivedTime(DateUtil.convertTimestampToStringHourTime(DateUtil.getNowAsTimestamp()));

        if (needRequesterList) {
            orderItems.setReferringSiteList(DisplayListService.getInstance()
                    .getFreshList(DisplayListService.ListType.SAMPLE_PATIENT_REFERRING_CLINIC));
        }

        if (needPaymentOptions) {
            orderItems.setPaymentOptions(DisplayListService.getInstance()
                    .getList(DisplayListService.ListType.SAMPLE_PATIENT_PAYMENT_OPTIONS));
        }

        if (needTestLocationCode) {
            orderItems.setTestLocationCodeList(
                    DisplayListService.getInstance().getList(DisplayListService.ListType.TEST_LOCATION_CODE));
        }

        if (ConfigurationProperties.getInstance().isPropertyValueEqual(ConfigurationProperties.Property.ORDER_PROGRAM,
                "true")) {
            orderItems.setProgramList(DisplayListService.getInstance().getList(DisplayListService.ListType.PROGRAM));
        }

        return orderItems;
    }

    /**
     * If this service is created with the default no parameter constructor then
     * this will generate a SampleOrderItem populated with the appropriate lists but
     * no other values.
     *
     * @return The SampleOrderItem
     */
    public SampleOrderItem getSampleOrderItem() {
        sampleOrder = getBaseSampleOrderItem();

        if (sample != null) {
            SampleService sampleSampleService = SpringContext.getBean(SampleService.class);
            sampleOrder.setSampleId(sample.getId());
            sampleOrder.setLabNo(sampleSampleService.getAccessionNumber(sample));
            sampleOrder.setReceivedDateForDisplay(sampleSampleService.getReceivedDateForDisplay(sample));
            sampleOrder.setReceivedTime(sampleSampleService.getReceived24HourTimeForDisplay(sample));

            sampleOrder.setRequestDate(
                    observationHistoryService.getValueForSample(ObservationType.REQUEST_DATE, sample.getId()));
            sampleOrder.setReferringPatientNumber(
                    observationHistoryService.getValueForSample(ObservationType.REFERRERS_PATIENT_ID, sample.getId()));
            sampleOrder.setNextVisitDate(
                    observationHistoryService.getValueForSample(ObservationType.NEXT_VISIT_DATE, sample.getId()));
            sampleOrder.setPaymentOptionSelection(
                    observationHistoryService.getRawValueForSample(ObservationType.PAYMENT_STATUS, sample.getId()));
            sampleOrder.setTestLocationCode(
                    observationHistoryService.getRawValueForSample(ObservationType.TEST_LOCATION_CODE, sample.getId()));
            sampleOrder.setOtherLocationCode(observationHistoryService
                    .getValueForSample(ObservationType.TEST_LOCATION_CODE_OTHER, sample.getId()));
            sampleOrder.setBillingReferenceNumber(observationHistoryService
                    .getValueForSample(ObservationType.BILLING_REFERENCE_NUMBER, sample.getId()));
            sampleOrder.setProgram(
                    observationHistoryService.getRawValueForSample(ObservationType.PROGRAM, sample.getId()));

            RequesterService requesterService = new RequesterService(sample.getId());
            sampleOrder.setProviderFirstName(requesterService.getRequesterFirstName());
            sampleOrder.setProviderLastName(requesterService.getRequesterLastName());
            sampleOrder.setProviderWorkPhone(requesterService.getWorkPhone());
            sampleOrder.setProviderFax(requesterService.getFax());
            sampleOrder.setProviderEmail(requesterService.getEmail());
            sampleOrder.setReferringSiteId(requesterService.getReferringSiteId());
            sampleOrder.setReferringSiteCode(requesterService.getReferringSiteCode());
            sampleOrder.setReferringSiteName(requesterService.getReferringSiteName());

            sampleOrder.setReadOnly(readOnly);
        }

        return sampleOrder;
    }

    public SampleOrderPersistenceArtifacts getPersistenceArtifacts(Sample sample, String currentUserId) {
        if (sampleOrder == null) {
            throw new IllegalStateException(
                    "SampleOrderService.getPersistenceArtifacts have used the SampleOrderItem constructor");
        }

        SampleOrderPersistenceArtifacts artifacts = new SampleOrderPersistenceArtifacts();

        if (sampleOrder.getModified()) {
            createSampleArtifacts(sample, currentUserId, artifacts);
            createProviderArtifacts(sampleOrder, currentUserId, artifacts);
        }

        return artifacts;
    }

    private void createSampleArtifacts(Sample sample, String currentUserId, SampleOrderPersistenceArtifacts artifacts) {
        if (sample == null) {
            sample = new Sample();
            sample.setId(sampleOrder.getSampleId());
            sampleService.getData(sample);
            sample.setSysUserId(currentUserId);
        }

        String receivedDate = sampleOrder.getReceivedDateForDisplay();

        if (!GenericValidator.isBlankOrNull(sampleOrder.getReceivedTime())) {
            receivedDate += " " + sampleOrder.getReceivedTime();
        } else {
            receivedDate += " 00:00";
        }

        // TODO check if the receivedDate has changed, if it has then the change has to
        // propagate through the analysis
        // if (useReceiveDateForCollectionDate) {
        // collectionDateFromRecieveDate = receivedDateForDisplay;
        // }

        sample.setReceivedTimestamp(DateUtil.convertStringDateToTimestamp(receivedDate));
        artifacts.setSample(sample);
    }

    private void createProviderArtifacts(SampleOrderItem sampleOrder, String currentUserId,
            SampleOrderPersistenceArtifacts artifacts) {
        RequesterService requesterService = new RequesterService(sampleOrder.getSampleId());
        createPersonProviderArtifacts(sampleOrder, currentUserId, artifacts, requesterService);
        createObservationHistoryArtifacts(sampleOrder, currentUserId, artifacts);
        createOrganizationProviderArtifacts(sampleOrder, currentUserId, artifacts, requesterService);
    }

    private void createPersonProviderArtifacts(SampleOrderItem sampleOrder, String currentUserId,
            SampleOrderPersistenceArtifacts artifacts, RequesterService requesterService) {
        Person providerPerson = requesterService.getPerson();

        if (namesDiffer(providerPerson, sampleOrder)) {
            providerPerson = null;
        }

        if (providerPerson == null) {
            providerPerson = new Person();
            List<SampleRequester> personSampleRequesters = requesterService
                    .getSampleRequestersByType(RequesterService.Requester.PERSON, true);
            SampleRequester samplePersonRequester = personSampleRequesters.size() > 0 ? personSampleRequesters.get(0)
                            : null;
            samplePersonRequester.setSysUserId(currentUserId);
            artifacts.setSamplePersonRequester(samplePersonRequester);
        }

        providerPerson.setFirstName(sampleOrder.getProviderFirstName());
        providerPerson.setLastName(sampleOrder.getProviderLastName());
        providerPerson.setWorkPhone(sampleOrder.getProviderWorkPhone());
        providerPerson.setFax(sampleOrder.getProviderFax());
        providerPerson.setEmail(sampleOrder.getProviderEmail());
        providerPerson.setSysUserId(currentUserId);

        artifacts.setProviderPerson(providerPerson);
    }

    private boolean namesDiffer(Person providerPerson, SampleOrderItem sampleOrder) {
        return providerPerson == null
                || StringUtil.compareWithNulls(providerPerson.getFirstName(), sampleOrder.getProviderFirstName()) != 0
                || StringUtil.compareWithNulls(providerPerson.getLastName(), sampleOrder.getProviderLastName()) != 0;

    }

    private void createObservationHistoryArtifacts(SampleOrderItem sampleOrder, String currentUserId,
            SampleOrderPersistenceArtifacts artifacts) {
        List<ObservationHistory> observations = new ArrayList<>();
        SampleHumanService sampleHumanService = SpringContext.getBean(SampleHumanService.class);
        Patient patient = sampleHumanService.getPatientForSample(artifacts.getSample());
        String patientId = patient.getId();

        createOrUpdateObservation(currentUserId, observations, patientId, ObservationType.REFERRERS_PATIENT_ID,
                sampleOrder.getReferringPatientNumber(), ValueType.LITERAL);
        createOrUpdateObservation(currentUserId, observations, patientId, ObservationType.NEXT_VISIT_DATE,
                sampleOrder.getNextVisitDate(), ValueType.LITERAL);
        createOrUpdateObservation(currentUserId, observations, patientId, ObservationType.TEST_LOCATION_CODE,
                sampleOrder.getTestLocationCode(), ValueType.DICTIONARY);
        createOrUpdateObservation(currentUserId, observations, patientId, ObservationType.TEST_LOCATION_CODE_OTHER,
                sampleOrder.getOtherLocationCode(), ValueType.LITERAL);
        createOrUpdateObservation(currentUserId, observations, patientId, ObservationType.PAYMENT_STATUS,
                sampleOrder.getPaymentOptionSelection(), ValueType.DICTIONARY);
        createOrUpdateObservation(currentUserId, observations, patientId, ObservationType.REQUEST_DATE,
                sampleOrder.getRequestDate(), ValueType.LITERAL);
        createOrUpdateObservation(currentUserId, observations, patientId, ObservationType.PROGRAM,
                sampleOrder.getProgram(), ValueType.DICTIONARY);
        createOrUpdateObservation(currentUserId, observations, patientId, ObservationType.BILLING_REFERENCE_NUMBER,
                sampleOrder.getBillingReferenceNumber(), ValueType.LITERAL);

        artifacts.setObservations(observations);
    }

    private void createOrUpdateObservation(String currentUserId, List<ObservationHistory> observations,
            String patientId, ObservationType observationType, String value, ValueType valueType) {
        ObservationHistory observation = observationHistoryService.getObservationForSample(observationType,
                sampleOrder.getSampleId());
        if (observation == null && !GenericValidator.isBlankOrNull(value)) {
            observation = new ObservationHistory();
            observation.setSampleId(sampleOrder.getSampleId());
            observation.setPatientId(patientId);
            observation.setObservationHistoryTypeId(
                    observationHistoryService.getObservationTypeIdForType(observationType));
            observation.setValueType(valueType.getCode());
        }

        if (observation != null) {
            observation.setValue(value);
            observation.setSysUserId(currentUserId);
            observations.add(observation);
        }
    }

    private void createOrganizationProviderArtifacts(SampleOrderItem sampleOrder, String currentUserId,
            SampleOrderPersistenceArtifacts artifacts, RequesterService requesterService) {
        /*
         * These are the possible cases 1. No organizationRequester a. No referring site
         * id or code or new organization name -- nothing to do b. New organization name
         * -- create a new sample requester and organization they will need to be bound
         * after the organization is saved b. A referring site id -- create a new
         * SampleRequester using the referring site id i. no referring code or --
         * nothing to do to organization. We don't want to delete an existing code ii
         * referring code differs from what is in the database -- update the
         * organization iii. referring code is the same as in the database -- nothing to
         * do with the organization 2. Existing organizationRequester a. No referring
         * site id or new organization name -- delete requester b. New Organization --
         * create a new organization they will need to be bound after the organization
         * is saved c. A referring site id which is the same as in organizationRequester
         * i. referring code is the same as in the organization or referring code is
         * blank -- nothing to do ii referring code is different from organization --
         * update organization d. A different site id for that in the requester --
         * update the requester i. referring code is the same as in the organization or
         * referring code is blank -- nothing to do ii referring code is different from
         * organization -- update organization
         */
        SampleRequester orgRequester = sampleService.getOrganizationSampleRequester(sample,
                TableIdService.getInstance().REFERRING_ORG_TYPE_ID);

//        SampleRequester orgRequester = requesterService
//                .getSampleRequesterByType(RequesterService.Requester.ORGANIZATION, false);

        if (orgRequester == null) {
            handleNoExistingOrganizationRequester(sampleOrder, currentUserId, artifacts);
        } else {
            handleExistingOrganizationRequester(sampleOrder, currentUserId, artifacts, orgRequester);
        }
    }

    private void handleNoExistingOrganizationRequester(SampleOrderItem sampleOrder, String currentUserId,
            SampleOrderPersistenceArtifacts artifacts) {
        SampleRequester orgRequester;
        if (GenericValidator.isBlankOrNull(sampleOrder.getReferringSiteId())
                && GenericValidator.isBlankOrNull(sampleOrder.getNewRequesterName())) {
            return;
        }

        orgRequester = new SampleRequester();
        orgRequester.setRequesterId(Long.parseLong(sampleOrder.getReferringSiteId())); // may be overridden latter
        orgRequester.setSampleId(Long.parseLong(sampleOrder.getSampleId()));
        orgRequester.setRequesterTypeId(RequesterService.Requester.ORGANIZATION.getId());
        orgRequester.setSysUserId(currentUserId);
        artifacts.setSampleOrganizationRequester(orgRequester);

        // Either there is an existing org else a new org
        Organization org;
        if (GenericValidator.isBlankOrNull(sampleOrder.getNewRequesterName())) {
            org = orgService.getOrganizationById(sampleOrder.getReferringSiteId());
            // all of these are reasons to have nothing to do with the organization
            if (GenericValidator.isBlankOrNull(sampleOrder.getReferringSiteCode()) || org == null
                    || sampleOrder.getReferringSiteCode().equals(org.getCode())) {
                return;
            }
        } else {
            org = new Organization();
            org.setIsActive("Y");
            org.setMlsSentinelLabFlag("N");
        }

        org.setOrganizationName(sampleOrder.getNewRequesterName());
        org.setCode(sampleOrder.getReferringSiteCode());
        org.setSysUserId(currentUserId);
        artifacts.setProviderOrganization(org);
    }

    private void handleExistingOrganizationRequester(SampleOrderItem sampleOrder, String currentUserId,
            SampleOrderPersistenceArtifacts artifacts, SampleRequester orgRequester) {
        if (GenericValidator.isBlankOrNull(sampleOrder.getReferringSiteId())
                && GenericValidator.isBlankOrNull(sampleOrder.getNewRequesterName())) {
            artifacts.setDeletableSampleOrganizationRequester(orgRequester);
            return;
        }

        Organization org;
        if (!GenericValidator.isBlankOrNull(sampleOrder.getNewRequesterName())) {
            org = new Organization();
            org.setIsActive("Y");
            org.setMlsSentinelLabFlag("N");
            org.setOrganizationName(sampleOrder.getNewRequesterName());
            org.setCode(sampleOrder.getReferringSiteCode());
            org.setSysUserId(currentUserId);
            artifacts.setProviderOrganization(org);
            orgRequester.setSysUserId(currentUserId);
            artifacts.setSampleOrganizationRequester(orgRequester);
        } else {
            org = orgService.getOrganizationById(String.valueOf(orgRequester.getRequesterId()));
            if (String.valueOf(orgRequester.getRequesterId()).equals(sampleOrder.getReferringSiteId())) {
                if (org == null || sampleOrder.getReferringSiteCode() == null
                        || sampleOrder.getReferringSiteCode().equals(org.getCode())) {
                    return;
                }

                updateExistingOrganizationCode(sampleOrder, currentUserId, artifacts, org);
            } else {
                orgRequester.setRequesterId(sampleOrder.getReferringSiteId());
                orgRequester.setSysUserId(currentUserId);
                artifacts.setSampleOrganizationRequester(orgRequester);

                updateExistingOrganizationCode(sampleOrder, currentUserId, artifacts, org);
            }
        }
    }

    private void updateExistingOrganizationCode(SampleOrderItem sampleOrder, String currentUserId,
            SampleOrderPersistenceArtifacts artifacts, Organization org) {
        if (sampleOrder.getReferringSiteCode() != null && !sampleOrder.getReferringSiteCode().equals(org.getCode())) {
            org.setCode(sampleOrder.getReferringSiteCode());
            org.setSysUserId(currentUserId);
            artifacts.setProviderOrganization(org);
        }
    }

    public class SampleOrderPersistenceArtifacts {
        private Sample sample;
        private Person providerPerson;
        private Organization providerOrganization;
        private SampleRequester deletableSampleOrganizationRequester;
        private List<ObservationHistory> observations = new ArrayList<>();
        private SampleRequester sampleOrganizationRequester;
        private SampleRequester samplePersonRequester;

        public Sample getSample() {
            return sample;
        }

        public void setSample(Sample sample) {
            this.sample = sample;
        }

        public Person getProviderPerson() {
            return providerPerson;
        }

        public void setProviderPerson(Person providerPerson) {
            this.providerPerson = providerPerson;
        }

        public Organization getProviderOrganization() {
            return providerOrganization;
        }

        public void setProviderOrganization(Organization providerOrganization) {
            this.providerOrganization = providerOrganization;
        }

        public List<ObservationHistory> getObservations() {
            return observations;
        }

        public void setObservations(List<ObservationHistory> observations) {
            this.observations = observations;
        }

        public SampleRequester getSampleOrganizationRequester() {
            return sampleOrganizationRequester;
        }

        public void setSampleOrganizationRequester(SampleRequester sampleRequester) {
            sampleOrganizationRequester = sampleRequester;
        }

        public SampleRequester getDeletableSampleOrganizationRequester() {
            return deletableSampleOrganizationRequester;
        }

        public void setDeletableSampleOrganizationRequester(SampleRequester deletableSampleOrganizationRequester) {
            this.deletableSampleOrganizationRequester = deletableSampleOrganizationRequester;
        }

        public SampleRequester getSamplePersonRequester() {
            return samplePersonRequester;
        }

        public void setSamplePersonRequester(SampleRequester samplePersonRequester) {
            this.samplePersonRequester = samplePersonRequester;
        }
    }
}