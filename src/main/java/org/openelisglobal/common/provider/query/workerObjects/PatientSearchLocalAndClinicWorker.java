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
 * Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
 *
 * Contributor(s): CIRG, University of Washington, Seattle WA.
 */
package org.openelisglobal.common.provider.query.workerObjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.externalLinks.ExternalPatientSearch;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.provider.query.PatientDemographicsSearchResults;
import org.openelisglobal.common.provider.query.PatientSearchResults;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.patientidentity.service.PatientIdentityService;
import org.openelisglobal.patientidentity.valueholder.PatientIdentity;
import org.openelisglobal.patientidentitytype.util.PatientIdentityTypeMap;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.person.valueholder.Person;
import org.openelisglobal.search.service.SearchResultsService;
import org.openelisglobal.spring.util.SpringContext;

public class PatientSearchLocalAndClinicWorker extends PatientSearchWorker {

    protected PatientService patientService = SpringContext.getBean(PatientService.class);
    protected PatientIdentityService patientIdentityService = SpringContext.getBean(PatientIdentityService.class);
    protected PersonService personService = SpringContext.getBean(PersonService.class);
    protected SearchResultsService searchResultsService = SpringContext.getBean(SearchResultsService.class);

    private final String sysUserId;

    public PatientSearchLocalAndClinicWorker(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    /**
     * @see org.openelisglobal.common.provider.query.workerObjects.PatientSearchWorker#createSearchResultXML(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.StringBuilder)
     */
    @Override
    public String createSearchResultXML(String lastName, String firstName, String STNumber, String subjectNumber,
            String nationalID, String patientID, String guid, StringBuilder xml) {

        // just to make the name shorter
        ConfigurationProperties config = ConfigurationProperties.getInstance();

        String success = IActionConstants.VALID;

        if (GenericValidator.isBlankOrNull(lastName) && GenericValidator.isBlankOrNull(firstName)
                && GenericValidator.isBlankOrNull(STNumber) && GenericValidator.isBlankOrNull(subjectNumber)
                && GenericValidator.isBlankOrNull(nationalID) && GenericValidator.isBlankOrNull(patientID)
                && GenericValidator.isBlankOrNull(guid)) {

            xml.append("No search terms were entered");
            return IActionConstants.INVALID;
        }

        ExternalPatientSearch externalSearch = new ExternalPatientSearch();
        externalSearch.setSearchCriteria(lastName, firstName, STNumber, subjectNumber, nationalID, guid);
        externalSearch.setConnectionCredentials(config.getPropertyValue(Property.PatientSearchURL),
                config.getPropertyValue(Property.PatientSearchUserName),
                config.getPropertyValue(Property.PatientSearchPassword),
                (int) SystemConfiguration.getInstance().getSearchTimeLimit());

        Thread searchThread = new Thread(externalSearch);
        List<PatientSearchResults> localResults = new ArrayList<>();
        List<PatientDemographicsSearchResults> clinicResults = null;
        List<PatientDemographicsSearchResults> newPatientsFromClinic = new ArrayList<>();

        try {
            searchThread.start();
            localResults = searchResultsService.getSearchResults(lastName, firstName, STNumber, subjectNumber,
                    nationalID, nationalID, patientID, guid);

            searchThread.join(SystemConfiguration.getInstance().getSearchTimeLimit() + 500);

            if (externalSearch.getSearchResultStatus() == 200) {
                clinicResults = externalSearch.getSearchResults();
            } else {
                // TODO do something with the errors
            }
        } catch (InterruptedException e) {
            LogEvent.logError(e.getMessage(), e);
        } catch (IllegalStateException e) {
            LogEvent.logError(e.getMessage(), e);
        }

        findNewPatients(localResults, clinicResults, newPatientsFromClinic);
        insertNewPatients(newPatientsFromClinic);
        localResults.addAll(newPatientsFromClinic);
        setLocalSourceIndicators(localResults);

        sortPatients(localResults);

        if (localResults != null && localResults.size() > 0) {
            for (PatientSearchResults singleResult : localResults) {
                appendSearchResultRow(singleResult, xml);
            }
        } else {
            success = IActionConstants.INVALID;

            xml.append("No results were found for search.  Check spelling or remove some of the fields");
        }

        return success;
    }

    private void insertNewPatients(List<PatientDemographicsSearchResults> newPatientsFromClinic) {
        try {
            for (PatientDemographicsSearchResults results : newPatientsFromClinic) {
                insertNewPatients(results);
            }
        } catch (LIMSRuntimeException e) {
            LogEvent.logDebug(e);
        }
    }

    private void insertNewPatients(PatientDemographicsSearchResults results) {
        Patient patient = new Patient();
        Person person = new Person();

        patient.setBirthDateForDisplay(results.getBirthdate());
        patient.setGender(results.getGender());
        patient.setNationalId(results.getNationalId());
        patient.setSysUserId(sysUserId);

        person.setLastName(results.getLastName());
        person.setFirstName(results.getFirstName());
        person.setSysUserId(sysUserId);

        personService.insert(person);
        patient.setPerson(person);
        patientService.insert(patient);

        persistIdentityType(patientIdentityService, results.getStNumber(), "ST", patient.getId());
        persistIdentityType(patientIdentityService, results.getSubjectNumber(), "SUBJECT", patient.getId());
        persistIdentityType(patientIdentityService, results.getMothersName(), "MOTHER", patient.getId());
        persistIdentityType(patientIdentityService, results.getGUID(), "GUID", patient.getId());
        persistIdentityType(patientIdentityService, results.getDataSourceId(), "ORG_SITE", patient.getId());

        results.setPatientID(patient.getId());
    }

    public void persistIdentityType(PatientIdentityService patientIdentityService, String paramValue, String type,
            String patientId) throws LIMSRuntimeException {

        if (!GenericValidator.isBlankOrNull(paramValue)) {

            String typeID = PatientIdentityTypeMap.getInstance().getIDForType(type);

            PatientIdentity patientIdentity = new PatientIdentity();
            patientIdentity.setPatientId(patientId);
            patientIdentity.setIdentityTypeId(typeID);
            patientIdentity.setSysUserId(sysUserId);
            patientIdentity.setIdentityData(paramValue);
            patientIdentity.setLastupdatedFields();
            patientIdentityService.insert(patientIdentity);
        }
    }

    /*
     * This will check to see if the clinic results are in OpenELIS. If they are not
     * then they will be
     */
    private void findNewPatients(List<PatientSearchResults> results,
            List<PatientDemographicsSearchResults> clinicResults,
            List<PatientDemographicsSearchResults> newPatientsFromClinic) {

        if (clinicResults != null) {
            List<String> currentGuids = new ArrayList<>();

            for (PatientSearchResults result : results) {
                if (!GenericValidator.isBlankOrNull(result.getGUID())) {
                    currentGuids.add(result.getGUID());
                }
            }

            for (PatientDemographicsSearchResults clinicResult : clinicResults) {
                if (!currentGuids.contains(clinicResult.getGUID())) {
                    newPatientsFromClinic.add(clinicResult);
                }
            }
        }
    }

    private void setLocalSourceIndicators(List<PatientSearchResults> results) {
        for (PatientSearchResults result : results) {
            String messageKey = GenericValidator.isBlankOrNull(result.getGUID()) ? "patient.local.source"
                    : "patient.imported.source";
            result.setDataSourceName(MessageUtil.getMessage(messageKey));
        }
    }
}
