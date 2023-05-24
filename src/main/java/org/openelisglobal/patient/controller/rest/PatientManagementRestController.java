package org.openelisglobal.patient.controller.rest;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.dataexchange.fhir.exception.FhirPersistanceException;
import org.openelisglobal.dataexchange.fhir.exception.FhirTransformationException;
import org.openelisglobal.dataexchange.fhir.service.FhirTransformService;
import org.openelisglobal.patient.action.IPatientUpdate.PatientUpdateStatus;
import org.openelisglobal.patient.action.bean.PatientManagementInfo;
import org.openelisglobal.patient.service.PatientService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.StaleObjectStateException;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.provider.query.PatientSearchResults;
import org.openelisglobal.common.rest.BaseRestController;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.patientidentity.service.PatientIdentityService;
import org.openelisglobal.patientidentity.valueholder.PatientIdentity;
import org.openelisglobal.person.valueholder.Person;
import org.openelisglobal.sample.form.SamplePatientEntryForm;
import org.openelisglobal.search.service.SearchResultsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rest/")
public class PatientManagementRestController extends BaseRestController {
    @Autowired
    SearchResultsService searchService;
    @Autowired
    PatientIdentityService patientIdentityService;
    @Autowired
    PatientService patientService;
    @Autowired
    FhirTransformService fhirTransformService;

    private static final String AMBIGUOUS_DATE_CHAR = ConfigurationProperties.getInstance()
            .getPropertyValue(ConfigurationProperties.Property.AmbiguousDateHolder);
    private static final String AMBIGUOUS_DATE_HOLDER = AMBIGUOUS_DATE_CHAR + AMBIGUOUS_DATE_CHAR;

    @PostMapping(value = "patient-management", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void savepatient(HttpServletRequest request, @Validated(SamplePatientEntryForm.SamplePatientEntry.class) @RequestBody PatientManagementInfo patientInfo ,BindingResult bindingResult)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{

        if (StringUtils.isNotBlank(patientInfo.getPatientPK())) {
            patientInfo.setPatientUpdateStatus(PatientUpdateStatus.UPDATE);
        } else {
            patientInfo.setPatientUpdateStatus(PatientUpdateStatus.ADD);
        }
        Patient patient = new Patient();

        if (patientInfo.getPatientUpdateStatus() != PatientUpdateStatus.NO_ACTION) {
            preparePatientData(bindingResult ,request, patientInfo, patient);
            if(bindingResult.hasErrors()){
                try {
                    throw new BindException(bindingResult);
                }
                catch (BindException e) {
                    LogEvent.logError(e);
                }
            }
            try {
                patientService.persistPatientData(patientInfo, patient, getSysUserId(request));
                fhirTransformService.transformPersistPatient(patientInfo);
            } catch (LIMSRuntimeException e) {

                if (e.getException() instanceof StaleObjectStateException) {

                } else {
                    LogEvent.logDebug(e);

                }
                request.setAttribute(ALLOW_EDITS_KEY, "false");

            } catch (FhirTransformationException | FhirPersistanceException e) {
                LogEvent.logError(e);
            }
        }

    }

    private void preparePatientData(Errors errors ,HttpServletRequest request, PatientManagementInfo patientInfo,
            Patient patient) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        validatePatientInfo(errors, patientInfo);
        if (errors.hasErrors()) {
           return;
        }

        initMembers(patient);
        patientInfo.setPatientIdentities(new ArrayList<PatientIdentity>());

        if (patientInfo.getPatientUpdateStatus() == PatientUpdateStatus.UPDATE) {
            Patient dbPatient = loadForUpdate(patientInfo);
            PropertyUtils.copyProperties(patient, dbPatient);
        }

        copyFormBeanToValueHolders(patientInfo, patient);

        setSystemUserID(patientInfo, patient, request);

        setLastUpdatedTimeStamps(patientInfo, patient);

    }

    private void validatePatientInfo(Errors errors, PatientManagementInfo patientInfo) {
        if (ConfigurationProperties.getInstance()
                .isPropertyValueEqual(ConfigurationProperties.Property.ALLOW_DUPLICATE_SUBJECT_NUMBERS, "false")) {
            String newSTNumber = org.apache.commons.validator.GenericValidator.isBlankOrNull(patientInfo.getSTnumber())
                    ? null
                    : patientInfo.getSTnumber();
            String newSubjectNumber = org.apache.commons.validator.GenericValidator
                    .isBlankOrNull(patientInfo.getSubjectNumber()) ? null : patientInfo.getSubjectNumber();
            String newNationalId = org.apache.commons.validator.GenericValidator
                    .isBlankOrNull(patientInfo.getNationalId()) ? null : patientInfo.getNationalId();

            List<PatientSearchResults> results = searchService.getSearchResults(null, null, newSTNumber,
                    newSubjectNumber, newNationalId, null, null, null, null, null);

            if (!results.isEmpty()) {
                for (PatientSearchResults result : results) {
                    if (!result.getPatientID().equals(patientInfo.getPatientPK())) {
                        if (newSTNumber != null && newSTNumber.equals(result.getSTNumber())) {
                            errors.reject("error.duplicate.STNumber", "error.duplicate.STNumber");
                        }
                        if (newSubjectNumber != null && newSubjectNumber.equals(result.getSubjectNumber())) {
                            errors.reject("error.duplicate.subjectNumber", "error.duplicate.subjectNumber");
                        }
                        if (newNationalId != null && newNationalId.equals(result.getNationalId())) {
                            errors.reject("error.duplicate.nationalId", "error.duplicate.nationalId");
                        }
                    }
                }
            }
        }

        validateBirthdateFormat(patientInfo, errors);

    }

    private void validateBirthdateFormat(PatientManagementInfo patientInfo, Errors errors) {
        String birthDate = patientInfo.getBirthDateForDisplay();
        boolean validBirthDateFormat = true;

        if (!org.apache.commons.validator.GenericValidator.isBlankOrNull(birthDate)) {
            validBirthDateFormat = birthDate.length() == 10;
            // the regex matches ambiguous day and month or ambiguous day or completely
            // formed date
            if (validBirthDateFormat) {
                validBirthDateFormat = birthDate.matches("(((" + AMBIGUOUS_DATE_HOLDER + "|\\d{2})/\\d{2})|"
                        + AMBIGUOUS_DATE_HOLDER + "/(" + AMBIGUOUS_DATE_HOLDER + "|\\d{2}))/\\d{4}");
            }

            if (!validBirthDateFormat) {
                errors.reject("error.birthdate.format", "error.birthdate.format");
            }
        }
    }

    private void copyFormBeanToValueHolders(PatientManagementInfo patientInfo, Patient patient)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        PropertyUtils.copyProperties(patient, patientInfo);
        PropertyUtils.copyProperties(patient.getPerson(), patientInfo);
    }

    private void setSystemUserID(PatientManagementInfo patientInfo, Patient patient, HttpServletRequest request) {
        patient.setSysUserId(getSysUserId(request));
        patient.getPerson().setSysUserId(getSysUserId(request));

        for (PatientIdentity identity : patientInfo.getPatientIdentities()) {
            identity.setSysUserId(getSysUserId(request));
        }
        patientInfo.getPatientContact().setSysUserId(getSysUserId(request));
    }

    private void initMembers(Patient patient) {
        patient.setPerson(new Person());
    }

    private void setLastUpdatedTimeStamps(PatientManagementInfo patientInfo, Patient patient) {
        String patientUpdate = patientInfo.getPatientLastUpdated();
        if (!org.apache.commons.validator.GenericValidator.isBlankOrNull(patientUpdate)) {
            Timestamp timeStamp = Timestamp.valueOf(patientUpdate);
            patient.setLastupdated(timeStamp);
        }

        String personUpdate = patientInfo.getPersonLastUpdated();
        if (!org.apache.commons.validator.GenericValidator.isBlankOrNull(personUpdate)) {
            Timestamp timeStamp = Timestamp.valueOf(personUpdate);
            patient.getPerson().setLastupdated(timeStamp);
        }
    }

    private Patient loadForUpdate(PatientManagementInfo patientInfo) {
        Patient patient = patientService.get(patientInfo.getPatientPK());
        patientInfo.setPatientIdentities(patientIdentityService.getPatientIdentitiesForPatient(patient.getId()));
        return patient;
    }

}
