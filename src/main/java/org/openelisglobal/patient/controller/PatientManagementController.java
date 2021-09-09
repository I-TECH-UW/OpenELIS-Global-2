package org.openelisglobal.patient.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.StaleObjectStateException;
import org.openelisglobal.address.service.AddressPartService;
import org.openelisglobal.address.service.PersonAddressService;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.provider.query.PatientSearchResults;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.dataexchange.fhir.exception.FhirPersistanceException;
import org.openelisglobal.dataexchange.fhir.exception.FhirTransformationException;
import org.openelisglobal.dataexchange.fhir.service.FhirTransformService;
import org.openelisglobal.patient.action.IPatientUpdate.PatientUpdateStatus;
import org.openelisglobal.patient.action.bean.PatientManagementInfo;
import org.openelisglobal.patient.action.bean.PatientSearch;
import org.openelisglobal.patient.service.PatientService;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.patientidentity.service.PatientIdentityService;
import org.openelisglobal.patientidentity.valueholder.PatientIdentity;
import org.openelisglobal.patienttype.service.PatientPatientTypeService;
import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.person.valueholder.Person;
import org.openelisglobal.sample.form.SamplePatientEntryForm;
import org.openelisglobal.sample.validator.SamplePatientEntryFormValidator;
import org.openelisglobal.search.service.SearchResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PatientManagementController extends BaseController {

    private static final String[] ALLOWED_FIELDS = new String[] { "patientProperties.currentDate",
            "patientProperties.patientLastUpdated", "patientProperties.personLastUpdated",
            "patientProperties.patientUpdateStatus", "patientProperties.patientPK", "patientProperties.guid",
            "patientProperties.fhirUuid", "patientProperties.STnumber", "patientProperties.subjectNumber",
            "patientProperties.nationalId", "patientProperties.lastName", "patientProperties.firstName",
            "patientProperties.aka", "patientProperties.mothersName", "patientProperties.mothersInitial",
            "patientProperties.streetAddress", "patientProperties.commune", "patientProperties.city",
            "patientProperties.addressDepartment", "patientProperties.addressDepartment",

            "patientProperties.primaryPhone", "patientProperties.email", "patientProperties.healthRegion",

            "patientProperties.healthDistrict", "patientProperties.birthDateForDisplay", "patientProperties.age",
            "patientProperties.gender", "patientProperties.patientType", "patientProperties.insuranceNumber",
            "patientProperties.occupation", "patientProperties.education", "patientProperties.maritialStatus",
            "patientProperties.nationality", "patientProperties.otherNationality",
            "patientProperties.patientContact.id", "patientProperties.patientContact.person.firstName",
            "patientProperties.patientContact.person.lastName", "patientProperties.patientContact.person.email",
            "patientProperties.patientContact.person.primaryPhone" };

    @Autowired
    SamplePatientEntryFormValidator formValidator;

    private static final String AMBIGUOUS_DATE_CHAR = ConfigurationProperties.getInstance()
            .getPropertyValue(ConfigurationProperties.Property.AmbiguousDateHolder);
    private static final String AMBIGUOUS_DATE_HOLDER = AMBIGUOUS_DATE_CHAR + AMBIGUOUS_DATE_CHAR;

    @Autowired
    AddressPartService addressPartService;
    @Autowired
    PatientPatientTypeService patientPatientTypeService;
    @Autowired
    PatientIdentityService patientIdentityService;
    @Autowired
    PatientService patientService;
    @Autowired
    PersonService personService;
    @Autowired
    PersonAddressService personAddressService;
    @Autowired
    SearchResultsService searchService;
    @Autowired
    protected FhirTransformService fhirTransformService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping(value = "/PatientManagement", method = RequestMethod.GET)
    public ModelAndView showPatientManagement(HttpServletRequest request) {
        SamplePatientEntryForm form = new SamplePatientEntryForm();

        cleanAndSetupRequestForm(form, request);
        addFlashMsgsToRequest(request);

        return findForward(FWD_SUCCESS, form);
    }

    @RequestMapping(value = "/PatientManagement", method = RequestMethod.POST)
    public ModelAndView showPatientManagementUpdate(HttpServletRequest request,
            @ModelAttribute("form") @Validated(SamplePatientEntryForm.SamplePatientEntry.class) SamplePatientEntryForm form,
            BindingResult result, RedirectAttributes redirectAttributes)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        form.setPatientSearch(new PatientSearch());
        formValidator.validate(form, result);
        if (result.hasErrors()) {
            saveErrors(result);
            return findForward(FWD_FAIL_INSERT, form);
        }

        PatientManagementInfo patientInfo = form.getPatientProperties();

        Patient patient = new Patient();
        setPatientUpdateStatus(patientInfo);

        if (patientInfo.getPatientUpdateStatus() != PatientUpdateStatus.NO_ACTION) {

            preparePatientData(result, request, patientInfo, patient);
            if (result.hasErrors()) {
                saveErrors(result);
                return findForward(FWD_FAIL_INSERT, form);
            }
            try {
                patientService.persistPatientData(patientInfo, patient, getSysUserId(request));
                fhirTransformService.transformPersistPatient(patientInfo);
            } catch (LIMSRuntimeException e) {

                if (e.getException() instanceof StaleObjectStateException) {
                    result.reject("errors.OptimisticLockException", "errors.OptimisticLockException");
                } else {
                    LogEvent.logDebug(e);
                    result.reject("errors.UpdateException", "errors.UpdateException");
                }
                request.setAttribute(ALLOW_EDITS_KEY, "false");
                if (result.hasErrors()) {
                    saveErrors(result);
                    return findForward(FWD_FAIL_INSERT, form);
                }

            } catch (FhirTransformationException | FhirPersistanceException e) {
                LogEvent.logError(e);
            }

        }
        redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
        return findForward(FWD_SUCCESS_INSERT, form);
    }

    public void cleanAndSetupRequestForm(SamplePatientEntryForm form, HttpServletRequest request) {
        request.getSession().setAttribute(SAVE_DISABLED, TRUE);
        form.setPatientProperties(new PatientManagementInfo());
        form.setPatientSearch(new PatientSearch());
        form.getPatientProperties().setPatientUpdateStatus(PatientUpdateStatus.ADD);
    }

    public void preparePatientData(Errors errors, HttpServletRequest request, PatientManagementInfo patientInfo,
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

        setSystemUserID(patientInfo, patient);

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

    private void initMembers(Patient patient) {
        patient.setPerson(new Person());
    }

    private Patient loadForUpdate(PatientManagementInfo patientInfo) {
        Patient patient = patientService.get(patientInfo.getPatientPK());
        patientInfo.setPatientIdentities(patientIdentityService.getPatientIdentitiesForPatient(patient.getId()));
        return patient;
    }

    public void setPatientUpdateStatus(PatientManagementInfo patientInfo) {

        /*
         * String status = patientInfo.getPatientProcessingStatus();
         *
         * if ("noAction".equals(status)) {
         * patientInfo.setPatientUpdateStatus(PatientUpdateStatus.NO_ACTION); } else if
         * ("update".equals(status)) {
         * patientInfo.setPatientUpdateStatus(PatientUpdateStatus.UPDATE); } else {
         * patientInfo.setPatientUpdateStatus(PatientUpdateStatus.ADD); }
         */
    }

    private void copyFormBeanToValueHolders(PatientManagementInfo patientInfo, Patient patient)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        PropertyUtils.copyProperties(patient, patientInfo);
        PropertyUtils.copyProperties(patient.getPerson(), patientInfo);
    }

    private void setSystemUserID(PatientManagementInfo patientInfo, Patient patient) {
        patient.setSysUserId(getSysUserId(request));
        patient.getPerson().setSysUserId(getSysUserId(request));

        for (PatientIdentity identity : patientInfo.getPatientIdentities()) {
            identity.setSysUserId(getSysUserId(request));
        }
        patientInfo.getPatientContact().setSysUserId(getSysUserId(request));
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "patientManagementDefinition";
        } else if (FWD_FAIL.equals(forward)) {
            return "redirect:/Dashboard.do";
        } else if (FWD_SUCCESS_INSERT.equals(forward)) {
            return "redirect:/PatientManagement.do";
        } else if (FWD_FAIL_INSERT.equals(forward)) {
            return "patientManagementDefinition";
        } else {
            return "PageNotFound";
        }
    }

    @Override
    protected String getPageTitleKey() {
        return "patient.management.title";
    }

    @Override
    protected String getPageSubtitleKey() {
        return "patient.management.title";
    }
}
