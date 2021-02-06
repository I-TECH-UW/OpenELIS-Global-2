/**
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
 *
 */
package org.openelisglobal.dataexchange.order.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.StatusService.ExternalOrderStatus;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.dataexchange.order.valueholder.ElectronicOrder;
import org.openelisglobal.dataexchange.service.order.ElectronicOrderService;
import org.openelisglobal.patient.service.PatientContactService;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.patient.valueholder.PatientContact;
import org.openelisglobal.patientidentity.service.PatientIdentityService;
import org.openelisglobal.patientidentity.valueholder.PatientIdentity;
import org.openelisglobal.patientidentitytype.service.PatientIdentityTypeService;
import org.openelisglobal.patientidentitytype.valueholder.PatientIdentityType;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.person.valueholder.Person;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.systemuser.service.SystemUserService;
import org.openelisglobal.systemuser.valueholder.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope("prototype")
public class DBOrderPersister implements IOrderPersister {

    private String SERVICE_USER_ID;
    private String IDENTITY_GUID_ID;
    private String IDENTITY_STNUMBER_ID;
    private String IDENTITY_OBNUMBER_ID;
    private String IDENTITY_PCNUMBER_ID;

    @Autowired
    private ElectronicOrderService eOrderService;
    @Autowired
    private PatientIdentityTypeService identityTypeService;
    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private PatientIdentityService identityService;
    @Autowired
    private PersonService personService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientContactService patientContactService;

    private Patient patient;

    @PostConstruct
    public void initializeGlobalVariables() {
        SystemUser serviceUser = systemUserService.getDataForLoginUser("serviceUser");
        SERVICE_USER_ID = serviceUser == null ? null : serviceUser.getId();

        IDENTITY_GUID_ID = getIdentityType(identityTypeService, "GUID");
        IDENTITY_STNUMBER_ID = getIdentityType(identityTypeService, "ST");
        IDENTITY_OBNUMBER_ID = getIdentityType(identityTypeService, "OB_NUMBER");
        IDENTITY_PCNUMBER_ID = getIdentityType(identityTypeService, "PC_NUMBER");
    }

    private String getIdentityType(PatientIdentityTypeService identityTypeService, String name) {
        PatientIdentityType type = identityTypeService.getNamedIdentityType(name);
        return type == null ? null : type.getId();
    }

    private void persist(MessagePatient orderPatient) {
        patient = patientService.getPatientForGuid(orderPatient.getGuid());
        patient = patient == null ? patientService.getPatientByExternalId(orderPatient.getExternalId()) : patient;
        if (patient == null) {
            createNewPatient(orderPatient);
        } else {
            updatePatient(orderPatient, patient);
        }
    }

    private void persistContact(MessagePatient orderPatient, Patient patient) {
        PatientContact contact = new PatientContact();
        Person contactPerson = new Person();
        contactPerson.setFirstName(orderPatient.getContactFirstName());
        contactPerson.setLastName(orderPatient.getContactLastName());
        contactPerson.setEmail(orderPatient.getContactEmail());
        contactPerson.setPrimaryPhone(orderPatient.getContactPhone());

        contact.setPatientId(patient.getId());
        contact.setSysUserId(SERVICE_USER_ID);
        contactPerson.setSysUserId(SERVICE_USER_ID);

        contactPerson.setId(personService.insert(contactPerson));
        contact.setPerson(contactPerson);
        patientContactService.insert(contact);
    }

    private void createNewPatient(MessagePatient orderPatient) {
        Person person = new Person();
        person.setFirstName(orderPatient.getFirstName());
        person.setLastName(orderPatient.getLastName());
        person.setStreetAddress(orderPatient.getAddressStreet());
        person.setCity(orderPatient.getAddressVillage());
        person.setCountry(orderPatient.getAddressCountry());
        person.setEmail(orderPatient.getEmail());
        person.setPrimaryPhone(orderPatient.getMobilePhone());
        if (GenericValidator.isBlankOrNull(person.getPrimaryPhone())) {
            person.setPrimaryPhone(orderPatient.getWorkPhone());
        }
        person.setSysUserId(SERVICE_USER_ID);

        patient = new Patient();
        patient.setBirthDateForDisplay(orderPatient.getDisplayDOB());
        patient.setGender(orderPatient.getGender());
        patient.setNationalId(orderPatient.getNationalId());
        patient.setPerson(person);
        patient.setSysUserId(SERVICE_USER_ID);
        patient.setExternalId(orderPatient.getExternalId());

        if (GenericValidator.isBlankOrNull(orderPatient.getGuid())) {
            orderPatient.setGuid(java.util.UUID.randomUUID().toString());
        }

        List<PatientIdentity> identities = new ArrayList<>();
        if (!GenericValidator.isBlankOrNull(orderPatient.getGuid())) {
            addIdentityIfAppropriate(IDENTITY_GUID_ID, orderPatient.getGuid(), identities);
        } else {
            addIdentityIfAppropriate(IDENTITY_GUID_ID, orderPatient.getExternalId(), identities);
        }

        addIdentityIfAppropriate(IDENTITY_STNUMBER_ID, orderPatient.getStNumber(), identities);
        addIdentityIfAppropriate(IDENTITY_OBNUMBER_ID, orderPatient.getObNumber(), identities);
        addIdentityIfAppropriate(IDENTITY_PCNUMBER_ID, orderPatient.getPcNumber(), identities);

        personService.insert(person);
        patientService.insert(patient);

        for (PatientIdentity identity : identities) {
            identity.setPatientId(patient.getId());
            identityService.insert(identity);
        }

        persistContact(orderPatient, patient);
    }

    private void addIdentityIfAppropriate(String typeId, String value, List<PatientIdentity> identities) {
        if (typeId != null && value != null) {
            PatientIdentity identity = new PatientIdentity();
            identity.setIdentityData(value);
            identity.setIdentityTypeId(typeId);
            identity.setSysUserId(SERVICE_USER_ID);
            identities.add(identity);
        }
    }

    private void updatePatient(MessagePatient orderPatient, Patient patient) {
        Person person = patientService.getPerson(patient);

        updatePersonIfNeeded(orderPatient, patient, person);
        updatePatientIfNeeded(orderPatient, patient);

        List<PatientIdentity> identityList = patientService.getIdentityList(patient);
        updateIdentityIfNeeded(IDENTITY_OBNUMBER_ID, orderPatient.getObNumber(), patient.getId(), identityList,
                identityService);
        updateIdentityIfNeeded(IDENTITY_STNUMBER_ID, orderPatient.getStNumber(), patient.getId(), identityList,
                identityService);
        updateIdentityIfNeeded(IDENTITY_PCNUMBER_ID, orderPatient.getPcNumber(), patient.getId(), identityList,
                identityService);
        updateIdentityIfNeeded(IDENTITY_GUID_ID, orderPatient.getGuid(), patient.getId(), identityList,
                identityService);
    }

    private void updateIdentityIfNeeded(String identityTypeId, String newIdentityValue, String patientId,
            List<PatientIdentity> identityList, PatientIdentityService identityService) {

        if (!GenericValidator.isBlankOrNull(newIdentityValue)) {
            boolean assigned = false;
            for (PatientIdentity identity : identityList) {
                if (identity.getIdentityTypeId().equals(identityTypeId)) {
                    if (!newIdentityValue.equals(identity.getIdentityData())) {
                        identity.setIdentityData(newIdentityValue);
                        identity.setSysUserId(SERVICE_USER_ID);
                        identityService.update(identity);
                    }
                    assigned = true;
                    break;
                }
            }

            if (!assigned) {
                PatientIdentity identity = new PatientIdentity();
                identity.setIdentityTypeId(identityTypeId);
                identity.setIdentityData(newIdentityValue);
                identity.setPatientId(patientId);
                identity.setSysUserId(SERVICE_USER_ID);
                identityService.insert(identity);
            }
        }
    }

    private void updatePatientIfNeeded(MessagePatient orderPatient, Patient patient) {
        boolean updatePatient = false;

        if (needsUpdating(orderPatient.getDisplayDOB(), patientService.getBirthdayForDisplay(patient))) {
            patient.setBirthDateForDisplay(orderPatient.getDisplayDOB());
            updatePatient = true;
        }

        if (needsUpdating(orderPatient.getGender(), patientService.getGender(patient))) {
            patient.setGender(orderPatient.getGender());
            updatePatient = true;
        }

        if (needsUpdating(orderPatient.getNationalId(), patientService.getNationalId(patient))) {
            patient.setNationalId(orderPatient.getNationalId());
            updatePatient = true;
        }

        if (updatePatient) {
            patient.setSysUserId(SERVICE_USER_ID);
            patientService.update(patient);
        }
    }

    private void updatePersonIfNeeded(MessagePatient orderPatient, Patient patient, Person person) {
        boolean updatePerson = false;

        if (needsUpdating(orderPatient.getFirstName(), patientService.getFirstName(patient))) {
            person.setFirstName(orderPatient.getFirstName());
            updatePerson = true;
        }

        if (needsUpdating(orderPatient.getLastName(), patientService.getLastName(patient))) {
            person.setLastName(orderPatient.getLastName());
            updatePerson = true;
        }
        if (needsUpdating(orderPatient.getAddressStreet(), patientService.getPerson(patient).getStreetAddress())) {
            person.setStreetAddress(orderPatient.getAddressStreet());
            updatePerson = true;
        }
        if (needsUpdating(orderPatient.getAddressVillage(), patientService.getPerson(patient).getCity())) {
            person.setCity(orderPatient.getAddressVillage());
            updatePerson = true;
        }

        if (updatePerson) {
            person.setSysUserId(SERVICE_USER_ID);
            personService.update(person);
        }
    }

    private boolean needsUpdating(String orderPatientValue, String currentPatientValue) {
        return !GenericValidator.isBlankOrNull(orderPatientValue)
                && StringUtil.compareWithNulls(currentPatientValue, orderPatientValue) != 0;
    }

    @Override
    @Transactional
    public void persist(MessagePatient orderPatient, ElectronicOrder eOrder) {
        try {
            persist(orderPatient);
            eOrder.setPatient(patient);
            eOrderService.insert(eOrder);
        } catch (RuntimeException e) {
            LogEvent.logErrorStack(e);
            throw e;
        }
    }

    @Override
    public String getServiceUserId() {
        return SERVICE_USER_ID;
    }

    @Override
    public void cancelOrder(String referringOrderNumber) {
        if (!GenericValidator.isBlankOrNull(referringOrderNumber)) {
            List<ElectronicOrder> eOrders = eOrderService.getElectronicOrdersByExternalId(referringOrderNumber);

            if (eOrders != null && !eOrders.isEmpty()) {
                ElectronicOrder eOrder = eOrders.get(eOrders.size() - 1);
                eOrder.setStatusId(
                        SpringContext.getBean(IStatusService.class).getStatusID(ExternalOrderStatus.Cancelled));
                eOrder.setSysUserId(SERVICE_USER_ID);
                try {
                    eOrderService.update(eOrder);
                } catch (RuntimeException e) {
                    LogEvent.logErrorStack(e);
                }

            }
        }
    }

}