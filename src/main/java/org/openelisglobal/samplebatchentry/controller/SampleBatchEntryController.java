package org.openelisglobal.samplebatchentry.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.StaleObjectStateException;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.services.SampleOrderService;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.common.validator.BaseErrors;
import org.openelisglobal.dataexchange.fhir.exception.FhirPersistanceException;
import org.openelisglobal.dataexchange.fhir.exception.FhirTransformationException;
import org.openelisglobal.dataexchange.fhir.service.FhirTransformService;
import org.openelisglobal.organization.service.OrganizationService;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.patient.action.IPatientUpdate;
import org.openelisglobal.patient.action.IPatientUpdate.PatientUpdateStatus;
import org.openelisglobal.patient.action.bean.PatientManagementInfo;
import org.openelisglobal.patient.action.bean.PatientSearch;
import org.openelisglobal.sample.action.util.SamplePatientUpdateData;
import org.openelisglobal.sample.bean.SampleOrderItem;
import org.openelisglobal.sample.form.SamplePatientEntryForm;
import org.openelisglobal.sample.service.PatientManagementUpdate;
import org.openelisglobal.sample.service.SamplePatientEntryService;
import org.openelisglobal.sample.validator.SamplePatientEntryFormValidator;
import org.openelisglobal.samplebatchentry.form.SampleBatchEntryForm;
import org.openelisglobal.samplebatchentry.validator.SampleBatchEntryFormValidator;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.test.service.TestServiceImpl;
import org.openelisglobal.typeofsample.service.TypeOfSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleBatchEntryController extends BaseController {

    private static final String[] ALLOWED_FIELDS = new String[] { "patientProperties.currentDate",
            "patientProperties.patientLastUpdated", "patientProperties.personLastUpdated",
            "patientProperties.patientUpdateStatus", "patientProperties.patientPK", "patientProperties.guid",
            "patientProperties.STnumber", "patientProperties.subjectNumber", "patientProperties.nationalId",
            "patientProperties.lastName", "patientProperties.firstName", "patientProperties.aka",
            "patientProperties.birthDateForDisplay", "patientProperties.age", "patientProperties.gender",
            //
            "sampleOrderItems.labNo",
            //
            "sampleOrderItems.newRequesterName", "sampleOrderItems.referringSiteId",
            "sampleOrderItems.referringSiteDepartmentId", "form.sampleOrderItems.referringSiteName",
            "patientProperties.patientUpdateStatus", "currentDate", "currentTime",
            "sampleOrderItems.receivedDateForDisplay", "sampleOrderItems.receivedTime", "sampleXML",
            "sampleOrderItems.referringSiteId", "sampleOrderItems.referringSiteId",
            //
            "method", "facilityIDCheck", "facilityID", "patientInfoCheck" };

    @Autowired
    SampleBatchEntryFormValidator formValidator;

    @Autowired
    TestService testService;
    @Autowired
    TypeOfSampleService typeOfSampleService;
    @Autowired
    OrganizationService organizationService;

    @Autowired
    SamplePatientEntryFormValidator entryFormValidator;

    @Autowired
    private SamplePatientEntryService samplePatientEntryService;

    protected FhirTransformService fhirTransformService = SpringContext.getBean(FhirTransformService.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping(value = { "/SampleBatchEntry" }, method = RequestMethod.POST)
    public ModelAndView showSampleBatchEntry(HttpServletRequest request,
            @ModelAttribute("form") @Valid SampleBatchEntryForm form, BindingResult result) throws DocumentException {
        formValidator.validate(form, result);
        if (result.hasErrors()) {
            saveErrors(result);
            return findForward(FWD_FAIL, form);
        }

        String sampleXML = form.getSampleXML();
        SampleOrderService sampleOrderService = new SampleOrderService();
        SampleOrderItem soi = sampleOrderService.getSampleOrderItem();
        // preserve fields that are already in form in refreshed object
        soi.setReceivedTime(form.getSampleOrderItems().getReceivedTime());
        soi.setReceivedDateForDisplay(form.getSampleOrderItems().getReceivedDateForDisplay());
        soi.setNewRequesterName(form.getSampleOrderItems().getNewRequesterName());
        soi.setReferringSiteId(form.getFacilityID());
        soi.setReferringSiteDepartmentId(form.getSampleOrderItems().getReferringSiteDepartmentId());

        form.setSampleOrderItems(soi);

        form.setLocalDBOnly(ConfigurationProperties.getInstance()
                .getPropertyValueLowerCase(Property.UseExternalPatientInfo).equals("false"));
        /*
         * errors = validate(request); if (errors.hasErrors()) { saveErrors(errors,
         * form); request.setAttribute(IActionConstants.FWD_SUCCESS, false); forward =
         * FWD_FAIL; return findForward(forward, form); }
         */

        // get summary of tests selected to place in common fields section
        Document sampleDom = DocumentHelper.parseText(sampleXML);
        Element sampleItem = sampleDom.getRootElement().element("sample");
        String testIDs = sampleItem.attributeValue("tests");
        StringTokenizer tokenizer = new StringTokenizer(testIDs, ",");
        StringBuilder sBuilder = new StringBuilder();
        String seperator = "";
        while (tokenizer.hasMoreTokens()) {
            sBuilder.append(seperator);
            sBuilder.append(TestServiceImpl.getUserLocalizedTestName(testService.get(tokenizer.nextToken().trim())));
            seperator = "<br>";
        }
        String sampleType = typeOfSampleService.get(sampleItem.attributeValue("sampleID")).getLocalAbbreviation();
        String testNames = sBuilder.toString();
        request.setAttribute("sampleType", sampleType);
        request.setAttribute("testNames", testNames);

        // get facility name from id
        String facilityName = "";
        if (!StringUtil.isNullorNill(form.getFacilityID())) {
            Organization organization = organizationService.get(form.getFacilityID());
            facilityName = organization.getOrganizationName();
        } else if (!StringUtil.isNullorNill(form.getSampleOrderItems().getNewRequesterName())) {
            facilityName = form.getSampleOrderItems().getNewRequesterName();
        }
        String departmentName = "";
        if (!StringUtil.isNullorNill(form.getSampleOrderItems().getReferringSiteDepartmentId())) {
            Organization organization = organizationService
                    .get(form.getSampleOrderItems().getReferringSiteDepartmentId());
            departmentName = organization.getOrganizationName();
        }
        request.setAttribute("facilityName", facilityName);
        request.setAttribute("departmentName", departmentName);
        form.setPatientSearch(new PatientSearch());

        return findForward(form.getMethod(), form);
    }

    @RequestMapping(value = "/SamplePatientEntryBatch", method = RequestMethod.POST)
    public @ResponseBody ModelAndView showSamplePatientEntrySave(HttpServletRequest request,
            @ModelAttribute("form") @Validated(SamplePatientEntryForm.SamplePatientEntryBatch.class) SamplePatientEntryForm form,
            BindingResult result, RedirectAttributes redirectAttributes)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        entryFormValidator.validate(form, result);
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
        updateData.initProvider(sampleOrder);
        updateData.initSampleData(form.getSampleXML(), receivedDateForDisplay, trackPayments, sampleOrder);
        updateData.validateSample(result);

        if (result.hasErrors()) {
            saveErrors(result);
            // setSuccessFlag(request, true);
            return findForward(FWD_FAIL_INSERT, form);
        }

        try {
            samplePatientEntryService.persistData(updateData, patientUpdate, patientInfo, form, request);
            try {
                fhirTransformService.transformPersistOrderEntryFhirObjects(updateData, patientInfo, false, null);
            } catch (FhirTransformationException | FhirPersistanceException e) {
                LogEvent.logError(e);
            }
            // String fhir_json = fhirTransformService.CreateFhirFromOESample(updateData,
            // patientUpdate, patientInfo, form, request);
        } catch (LIMSRuntimeException e) {
            // ActionError error;
            if (e.getCause() instanceof StaleObjectStateException) {
                // error = new ActionError("errors.OptimisticLockException", null, null);
                result.reject("errors.OptimisticLockException", "errors.OptimisticLockException");
            } else {
                LogEvent.logDebug(e);
                // error = new ActionError("errors.UpdateException", null, null);
                result.reject("errors.UpdateException", "errors.UpdateException");
            }
            LogEvent.logInfo(this.getClass().getSimpleName(), "showSamplePatientEntrySave", result.toString());

            // errors.add(ActionMessages.GLOBAL_MESSAGE, error);
            saveErrors(result);
            request.setAttribute(ALLOW_EDITS_KEY, "false");
            return findForward(FWD_FAIL_INSERT, form);
        }

        redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
        return findForward(FWD_SUCCESS_INSERT, form);
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
        if ("On Demand".equals(forward)) {
            return "sampleBatchEntryOnDemandDefinition";
        } else if ("Pre-Printed".equals(forward)) {
            return "sampleBatchEntryPrePrintedDefinition";
        } else if (FWD_FAIL.equals(forward)) {
            return "sampleBatchEntrySetupDefinition";
        } else if (FWD_FAIL_INSERT.equals(forward)) {
            return "sampleBatchEntrySetupDefinition";
        } else if (FWD_SUCCESS_INSERT.equals(forward)) {
            return "redirect:/SamplePatientEntry";
        } else {
            return "redirect:/SampleBatchEntrySetup";
        }
    }

    @Override
    protected String getPageTitleKey() {
        return "sample.batchentry.title";
    }

    @Override
    protected String getPageSubtitleKey() {
        return "sample.batchentry.title";
    }
}
