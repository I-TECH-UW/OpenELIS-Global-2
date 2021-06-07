package org.openelisglobal.sample.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;

import org.apache.commons.validator.GenericValidator;
import org.hibernate.StaleObjectStateException;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.formfields.FormFields;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.DisplayListService.ListType;
import org.openelisglobal.common.services.SampleOrderService;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.validator.BaseErrors;
import org.openelisglobal.dataexchange.fhir.exception.FhirPersistanceException;
import org.openelisglobal.dataexchange.fhir.exception.FhirTransformationException;
import org.openelisglobal.dataexchange.fhir.service.FhirTransformService;
import org.openelisglobal.login.valueholder.UserSessionData;
import org.openelisglobal.patient.action.IPatientUpdate;
import org.openelisglobal.patient.action.IPatientUpdate.PatientUpdateStatus;
import org.openelisglobal.patient.action.bean.PatientManagementInfo;
import org.openelisglobal.patient.action.bean.PatientSearch;
import org.openelisglobal.referral.action.beanitems.ReferralItem;
import org.openelisglobal.sample.action.util.SamplePatientUpdateData;
import org.openelisglobal.sample.bean.SampleOrderItem;
import org.openelisglobal.sample.form.SamplePatientEntryForm;
import org.openelisglobal.sample.service.PatientManagementUpdate;
import org.openelisglobal.sample.service.SamplePatientEntryService;
import org.openelisglobal.sample.validator.SamplePatientEntryFormValidator;
import org.openelisglobal.sample.valueholder.SampleAdditionalField;
import org.openelisglobal.sample.valueholder.SampleAdditionalField.AdditionalFieldName;
import org.openelisglobal.spring.util.SpringContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SamplePatientEntryController extends BaseSampleEntryController {

    @Value("${org.openelisglobal.requester.lastName:}")
    private String requesterLastName;
    @Value("${org.openelisglobal.requester.firstName:}")
    private String requesterFirstName;
    @Value("${org.openelisglobal.requester.phone:}")
    private String requesterPhone;

    private static final String[] ALLOWED_FIELDS = new String[] { "customNotificationLogic",
            "patientEmailNotificationTestIds",
            "patientSMSNotificationTestIds", "providerEmailNotificationTestIds", "providerSMSNotificationTestIds",
            "patientProperties.currentDate",
            "patientProperties.patientLastUpdated", "patientProperties.personLastUpdated",
            "patientProperties.patientUpdateStatus", "patientProperties.patientPK", "patientProperties.guid",
            "patientProperties.fhirUuid",
            "patientProperties.STnumber", "patientProperties.subjectNumber", "patientProperties.nationalId",
            "patientProperties.lastName", "patientProperties.firstName", "patientProperties.aka",
            "patientProperties.mothersName", "patientProperties.mothersInitial", "patientProperties.streetAddress",
            "patientProperties.commune", "patientProperties.city", "patientProperties.addressDepartment",
            "patientProperties.addressDepartment", "patientPhone", "patientProperties.primaryPhone",
            "patientProperties.email", "patientProperties.healthRegion", "patientProperties.healthDistrict",
            "patientProperties.birthDateForDisplay", "patientProperties.age", "patientProperties.gender",
            "patientProperties.patientType", "patientProperties.insuranceNumber", "patientProperties.occupation",
            "patientProperties.education", "patientProperties.maritialStatus", "patientProperties.nationality",
            "patientProperties.otherNationality", "patientClinicalProperties.stdOther",
            "patientClinicalProperties.tbDiarrhae", "patientClinicalProperties.stdZona",
            "patientClinicalProperties.tbPrurigol", "patientClinicalProperties.stdKaposi",
            "patientClinicalProperties.tbMenigitis", "patientClinicalProperties.stdCandidiasis",
            "patientClinicalProperties.tbCerebral", "patientClinicalProperties.stdColonCancer",
            "patientClinicalProperties.tbExtraPulmanary", "patientClinicalProperties.arvProphyaxixType",
            "patientClinicalProperties.arvTreatmentReceiving", "patientClinicalProperties.arvTreatmentRemembered",
            "patientClinicalProperties.arvTreatment1", "patientClinicalProperties.arvTreatment2",
            "patientClinicalProperties.arvTreatment3", "patientClinicalProperties.arvTreatment4",
            "patientClinicalProperties.cotrimoxazoleReceiving", "patientClinicalProperties.cotrimoxazoleType",
            "patientClinicalProperties.infectionExtraPulmanary", "patientClinicalProperties.stdInfectionColon",
            "patientClinicalProperties.infectionCerebral", "patientClinicalProperties.stdInfectionCandidiasis",
            "patientClinicalProperties.infectionMeningitis", "patientClinicalProperties.stdInfectionKaposi",
            "patientClinicalProperties.infectionPrurigol", "patientClinicalProperties.stdInfectionZona",
            "patientClinicalProperties.infectionOther", "patientClinicalProperties.infectionUnderTreatment",
            "patientClinicalProperties.weight", "patientClinicalProperties.karnofskyScore",
            //
            "initialSampleConditionList", "sampleXML",
            //
            "sampleOrderItems.newRequesterName", "sampleOrderItems.modified", "sampleOrderItems.sampleId",
            "sampleOrderItems.labNo", "sampleOrderItems.requestDate", "sampleOrderItems.receivedDateForDisplay",
            "sampleOrderItems.receivedTime", "sampleOrderItems.nextVisitDate", "sampleOrderItems.requesterSampleID",
            "sampleOrderItems.referringPatientNumber", "sampleOrderItems.referringSiteId",
            "referringSiteDepartmentName", "sampleOrderItems.referringSiteDepartmentId",
            "sampleOrderItems.referringSiteName", "sampleOrderItems.referringSiteCode", "sampleOrderItems.program",
            "sampleOrderItems.providerLastName", "sampleOrderItems.providerFirstName",
            "sampleOrderItems.providerWorkPhone", "sampleOrderItems.providerFax", "sampleOrderItems.providerEmail",
            "sampleOrderItems.facilityAddressStreet", "sampleOrderItems.facilityAddressCommune",
            "sampleOrderItems.facilityPhone", "sampleOrderItems.facilityFax", "sampleOrderItems.paymentOptionSelection",
            "sampleOrderItems.billingReferenceNumber", "sampleOrderItems.testLocationCode",
            "sampleOrderItems.otherLocationCode",
            "sampleOrderItems.contactTracingIndexName", "sampleOrderItems.contactTracingIndexRecordNumber",
            //
            "currentDate", "sampleOrderItems.newRequesterName", "sampleOrderItems.externalOrderNumber",
            // referral
            "referralItems*.additionalTestsXMLWad", "referralItems*.referralResultId", "referralItems*.referralId",
            "referralItems*.referredResultType", "referralItems*.modified", "referralItems*.inLabResultId",
            "referralItems*.referralReasonId", "referralItems*.referrer", "referralItems*.referredInstituteId",
            "referralItems*.referredSendDate", "referralItems*.referredTestId", "referralItems*.referredReportDate",
            "referralItems*.note", "useReferral" };

    @Autowired
    private SamplePatientEntryFormValidator formValidator;
    @Autowired
    private SamplePatientEntryService samplePatientService;
    @Autowired
    private FhirTransformService fhirTransformService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping(value = "/SamplePatientEntry", method = RequestMethod.GET)

    public ModelAndView showSamplePatientEntry(HttpServletRequest request,
            @RequestParam(value = ID, required = false) @Pattern(regexp = "[a-zA-Z0-9 -]*") String externalOrderNumber)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        SamplePatientEntryForm form = new SamplePatientEntryForm();

        request.getSession().setAttribute(SAVE_DISABLED, TRUE);
        SampleOrderService sampleOrderService = new SampleOrderService();
        form.setSampleOrderItems(sampleOrderService.getSampleOrderItem());
        form.getSampleOrderItems().setProviderLastName(requesterLastName);
        form.getSampleOrderItems().setProviderFirstName(requesterFirstName);
        form.getSampleOrderItems().setProviderWorkPhone(requesterPhone);
        form.getSampleOrderItems().setExternalOrderNumber(externalOrderNumber);
        form.setPatientProperties(new PatientManagementInfo());
        form.setPatientSearch(new PatientSearch());
        form.setSampleTypes(DisplayListService.getInstance().getList(ListType.SAMPLE_TYPE_ACTIVE));
        form.setTestSectionList(DisplayListService.getInstance().getList(ListType.TEST_SECTION));
        form.setCurrentDate(DateUtil.getCurrentDateAsText());

        setupReferralOption(form);
        // for (Object program : form.getSampleOrderItems().getProgramList()) {
        // LogEvent.logInfo(this.getClass().getName(), "method unkown", ((IdValuePair)
        // program).getValue());
        // }

        addProjectList(form);
        addBillingLabel();

        if (FormFields.getInstance().useField(FormFields.Field.InitialSampleCondition)) {
            form.setInitialSampleConditionList(
                    DisplayListService.getInstance().getList(ListType.INITIAL_SAMPLE_CONDITION));
        }
        if (FormFields.getInstance().useField(FormFields.Field.SampleNature)) {
            form.setSampleNatureList(DisplayListService.getInstance().getList(ListType.SAMPLE_NATURE));
        }

        addFlashMsgsToRequest(request);
        return findForward(FWD_SUCCESS, form);
    }

    private void setupReferralOption(SamplePatientEntryForm form) {
        ReferralItem referral = new ReferralItem();
        referral.setReferredSendDate(DateUtil.getCurrentDateAsText());
        UserSessionData usd = (UserSessionData) request.getSession().getAttribute(USER_SESSION_DATA);
        if (usd != null) {
            referral.setReferrer(usd.getElisUserName());
        }
        form.setReferralItems(Arrays.asList(referral));
        form.setReferralOrganizations(DisplayListService.getInstance().getList(ListType.REFERRAL_ORGANIZATIONS));
        form.setReferralReasons(DisplayListService.getInstance().getList(ListType.REFERRAL_REASONS));
    }

    @RequestMapping(value = "/SamplePatientEntry", method = RequestMethod.POST)
    public @ResponseBody ModelAndView showSamplePatientEntrySave(HttpServletRequest request,
            @ModelAttribute("form") @Validated(SamplePatientEntryForm.SamplePatientEntry.class) SamplePatientEntryForm form,
            BindingResult result, RedirectAttributes redirectAttributes)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        formValidator.validate(form, result);
        if (result.hasErrors()) {
            saveErrors(result);
            return findForward(FWD_FAIL_INSERT, form);
        }
        SamplePatientUpdateData updateData = new SamplePatientUpdateData(getSysUserId(request));

        PatientManagementInfo patientInfo = form.getPatientProperties();
        SampleOrderItem sampleOrder = form.getSampleOrderItems();

        boolean trackPayments = ConfigurationProperties.getInstance()
                .isPropertyValueEqual(Property.TRACK_PATIENT_PAYMENT, "true");

        String receivedDateForDisplay = sampleOrder.getReceivedDateForDisplay();

        if (!org.apache.commons.validator.GenericValidator.isBlankOrNull(sampleOrder.getReceivedTime())) {
            receivedDateForDisplay += " " + sampleOrder.getReceivedTime();
        } else {
            receivedDateForDisplay += " 00:00";
        }

        updateData.setCollectionDateFromRecieveDateIfNeeded(receivedDateForDisplay);
        updateData.initializeRequester(sampleOrder);

        PatientManagementUpdate patientUpdate = SpringContext.getBean(PatientManagementUpdate.class);
        patientUpdate.setSysUserIdFromRequest(request);
        testAndInitializePatientForSaving(request, patientInfo, patientUpdate, updateData);

        updateData.setAccessionNumber(sampleOrder.getLabNo());
        updateData.setReferringId(sampleOrder.getExternalOrderNumber());
        updateData.initProvider(sampleOrder);
        updateData.initSampleData(form.getSampleXML(), receivedDateForDisplay, trackPayments, sampleOrder);
        updateData.setPatientEmailNotificationTestIds(form.getPatientEmailNotificationTestIds());
        updateData.setPatientSMSNotificationTestIds(form.getPatientSMSNotificationTestIds());
        updateData.setProviderEmailNotificationTestIds(form.getProviderEmailNotificationTestIds());
        updateData.setProviderSMSNotificationTestIds(form.getProviderSMSNotificationTestIds());
        updateData.setCustomNotificationLogic(form.getCustomNotificationLogic());
        if (Boolean.valueOf(ConfigurationProperties.getInstance().getPropertyValue(Property.CONTACT_TRACING))) {
            setContactTracingInfo(updateData, sampleOrder);
        }
        updateData.validateSample(result);

        if (result.hasErrors()) {
            saveErrors(result);
            // setSuccessFlag(request, true);
            return findForward(FWD_FAIL_INSERT, form);
        }

        try {
            samplePatientService.persistData(updateData, patientUpdate, patientInfo, form, request);
            try {
                fhirTransformService.transformPersistOrderEntryFhirObjects(updateData, patientInfo);
            } catch (FhirTransformationException | FhirPersistanceException e) {
                LogEvent.logError(e);
            }
            // String fhir_json = fhirTransformService.CreateFhirFromOESample(updateData,
            // patientUpdate, patientInfo, form, request);
        } catch (LIMSRuntimeException e) {
            // ActionError error;
            if (e.getException() instanceof StaleObjectStateException) {
                // error = new ActionError("errors.OptimisticLockException", null, null);
                result.reject("errors.OptimisticLockException", "errors.OptimisticLockException");
            } else {
                LogEvent.logDebug(e);
                // error = new ActionError("errors.UpdateException", null, null);
                result.reject("errors.UpdateException", "errors.UpdateException");
            }
            LogEvent.logInfo(this.getClass().getName(), "method unkown", result.toString());

            // errors.add(ActionMessages.GLOBAL_MESSAGE, error);
            saveErrors(result);
            request.setAttribute(ALLOW_EDITS_KEY, "false");
            return findForward(FWD_FAIL_INSERT, form);

        }

        redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
        return findForward(FWD_SUCCESS_INSERT, form);
    }

    private void setContactTracingInfo(SamplePatientUpdateData updateData, SampleOrderItem sampleOrder) {
        SampleAdditionalField field;
        if (!GenericValidator.isBlankOrNull(sampleOrder.getContactTracingIndexName())) {
            field = new SampleAdditionalField();
            field.setFieldName(AdditionalFieldName.CONTACT_TRACING_INDEX_NAME);
            field.setFieldValue(sampleOrder.getContactTracingIndexName());
            updateData.addSampleField(field);
        }
        if (!GenericValidator.isBlankOrNull(sampleOrder.getContactTracingIndexRecordNumber())) {
            field = new SampleAdditionalField();
            field.setFieldName(AdditionalFieldName.CONTACT_TRACING_INDEX_RECORD_NUMBER);
            field.setFieldValue(sampleOrder.getContactTracingIndexRecordNumber());
            updateData.addSampleField(field);
        }
    }

    private void testAndInitializePatientForSaving(HttpServletRequest request, PatientManagementInfo patientInfo,
            IPatientUpdate patientUpdate, SamplePatientUpdateData updateData)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        patientUpdate.setPatientUpdateStatus(patientInfo);
        updateData.setSavePatient(patientUpdate.getPatientUpdateStatus() != PatientUpdateStatus.NO_ACTION);

        if (updateData.isSavePatient()) {
            updateData.setPatientErrors(patientUpdate.preparePatientData(request, patientInfo));
        } else {
            updateData.setPatientErrors(new BaseErrors());
        }
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "samplePatientEntryDefinition";
        } else if (FWD_FAIL.equals(forward)) {
            return "homePageDefinition";
        } else if (FWD_SUCCESS_INSERT.equals(forward)) {
            return "redirect:/SamplePatientEntry.do";
        } else if (FWD_FAIL_INSERT.equals(forward)) {
            return "samplePatientEntryDefinition";
        } else {
            return "PageNotFound";
        }
    }
}
