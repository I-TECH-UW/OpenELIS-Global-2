package org.openelisglobal.samplebatchentry.controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.jfree.util.Log;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.validator.BaseErrors;
import org.openelisglobal.dictionary.ObservationHistoryList;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.organization.util.OrganizationTypeList;
import org.openelisglobal.patient.valueholder.ObservationData;
import org.openelisglobal.sample.controller.BaseSampleEntryController;
import org.openelisglobal.sample.form.ProjectData;
import org.openelisglobal.samplebatchentry.form.SampleBatchEntryForm;
import org.openelisglobal.samplebatchentry.validator.SampleBatchEntryFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleBatchEntryByProjectController extends BaseSampleEntryController {

    @Autowired
    SampleBatchEntryFormValidator formValidator;

    private static final String ON_DEMAND = "ondemand";
    private static final String PRE_PRINTED = "preprinted";

    @RequestMapping(value = "/SampleBatchEntryByProject", method = RequestMethod.POST)
    public ModelAndView showSampleBatchEntryByProject(HttpServletRequest request,
            @ModelAttribute("form") @Validated(SampleBatchEntryForm.SampleBatchEntrySetup.class) SampleBatchEntryForm form,
            BindingResult result) {
        formValidator.validate(form, result);
        if (result.hasErrors()) {
            saveErrors(result);
            return findForward(FWD_FAIL, form);
        }
        String study = request.getParameter("study");
        try {
            if ("viralLoad".equals(study)) {
                setupViralLoad(form, request);
            } else if ("EID".equals(study)) {
                setupEID(form, request);
            }
            setupCommonFields(form, request);
            return findForward(setForward(form), form);
        } catch (Exception e) {
            Log.error(e.toString());
            e.printStackTrace();
            return findForward(FWD_FAIL, form);
        }

    }

    private void setupEID(SampleBatchEntryForm form, HttpServletRequest request)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        ProjectData projectData = form.getProjectDataEID();
        PropertyUtils.setProperty(form, "programCode", MessageUtil.getMessage("sample.entry.project.LDBS"));
        String sampleTypes = "";
        String tests = "";
        if (projectData.getDryTubeTaken()) {
            sampleTypes = sampleTypes + MessageUtil.getMessage("sample.entry.project.ARV.dryTubeTaken");
        }
        if (projectData.getDbsTaken()) {
            sampleTypes = sampleTypes + " " + MessageUtil.getMessage("sample.entry.project.title.dryBloodSpot");
        }

        if (projectData.getDnaPCR()) {
            tests = tests + MessageUtil.getMessage("sample.entry.project.dnaPCR");
        }
        request.setAttribute("sampleType", sampleTypes);
        request.setAttribute("testNames", tests);
        PropertyUtils.setProperty(form, "projectData", projectData);
        ObservationData observations = new ObservationData();
        observations.setProjectFormName("EID_Id");
        PropertyUtils.setProperty(form, "observations", observations);
    }

    private void setupViralLoad(SampleBatchEntryForm form, HttpServletRequest request)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        ProjectData projectData = form.getProjectDataVL();
        PropertyUtils.setProperty(form, "programCode", MessageUtil.getMessage("sample.entry.project.LART"));
        String sampleTypes = "";
        String tests = "";
        if (projectData.getDryTubeTaken()) {
            sampleTypes = sampleTypes + MessageUtil.getMessage("sample.entry.project.ARV.dryTubeTaken");
        }
        if (projectData.getEdtaTubeTaken()) {
            sampleTypes = sampleTypes + " " + MessageUtil.getMessage("sample.entry.project.ARV.edtaTubeTaken");
        }

        if (projectData.getViralLoadTest()) {
            tests = tests + MessageUtil.getMessage("sample.entry.project.ARV.viralLoadTest");
        }
        request.setAttribute("sampleType", sampleTypes);
        request.setAttribute("testNames", tests);
        PropertyUtils.setProperty(form, "projectData", projectData);
        ObservationData observations = new ObservationData();
        observations.setProjectFormName("VL_Id");
        PropertyUtils.setProperty(form, "observations", observations);
    }

    private void setupCommonFields(SampleBatchEntryForm form, HttpServletRequest request)
            throws LIMSRuntimeException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        PropertyUtils.setProperty(form, "currentDate", request.getParameter("currentDate"));
        PropertyUtils.setProperty(form, "currentTime", request.getParameter("currentTime"));
        addOrganizationLists(form);
    }

    protected void addOrganizationLists(SampleBatchEntryForm form)
            throws LIMSRuntimeException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        // Get ARV Centers
        PropertyUtils.setProperty(form, "projectData.ARVCenters", OrganizationTypeList.ARV_ORGS.getList());
        PropertyUtils.setProperty(form, "organizationTypeLists", OrganizationTypeList.MAP);

        // Get EID Sites
        PropertyUtils.setProperty(form, "projectData.EIDSites", OrganizationTypeList.EID_ORGS.getList());
        PropertyUtils.setProperty(form, "projectData.EIDSitesByName", OrganizationTypeList.EID_ORGS_BY_NAME.getList());

        // Get EID whichPCR List
        // PropertyUtils.setProperty(form, "projectData.eidWhichPCRList",
        // ObservationHistoryList.EID_WHICH_PCR.getList());

        // Get EID secondTestReason List
        PropertyUtils.setProperty(form, "projectData.eidSecondPCRReasonList",
                ObservationHistoryList.EID_SECOND_PCR_REASON.getList());

        // Get SPE Request Reasons
        PropertyUtils.setProperty(form, "projectData.requestReasons",
                ObservationHistoryList.SPECIAL_REQUEST_REASONS.getList());

        PropertyUtils.setProperty(form, "projectData.isUnderInvestigationList",
                ObservationHistoryList.YES_NO.getList());
    }

    private String setForward(SampleBatchEntryForm form) {
        String method = form.getMethod();
        if (method == null) {
            Errors errors = new BaseErrors();
            errors.reject("", "null method of entry");
            saveErrors(errors);
            return FWD_FAIL;
        } else if (method.contains("On") && method.contains("Demand")) {
            return ON_DEMAND;
        } else if (method.contains("Pre") && method.contains("Printed")) {
            return PRE_PRINTED;
        } else {
            Errors errors = new BaseErrors();
            errors.reject("", "method of entry must be On Demand or Pre-Printed");
            saveErrors(errors);
            return FWD_FAIL;
        }
    }

    @Override
    protected String findLocalForward(String forward) {
        if (ON_DEMAND.equals(forward)) {
            return "sampleStudyBatchEntryOnDemandDefinition";
        } else if (PRE_PRINTED.equals(forward)) {
            return "sampleStudyBatchEntryPrePrintedDefinition";
        } else if (FWD_FAIL.equals(forward)) {
            return "sampleBatchEntrySetupDefinition";
        } else {
            return "PageNotFound";
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
