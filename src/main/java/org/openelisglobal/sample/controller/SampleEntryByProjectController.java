package org.openelisglobal.sample.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.DisplayListService.ListType;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.StatusService.SampleStatus;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.dictionary.ObservationHistoryList;
import org.openelisglobal.dictionary.valueholder.Dictionary;
import org.openelisglobal.organization.util.OrganizationTypeList;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.patient.saving.ISampleEntry;
import org.openelisglobal.patient.saving.ISampleEntryAfterPatientEntry;
import org.openelisglobal.patient.saving.ISampleSecondEntry;
import org.openelisglobal.sample.form.SampleEntryByProjectForm;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.sampleitem.valueholder.SampleItem;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.typeofsample.valueholder.TypeOfSample;
import org.owasp.encoder.Encode;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleEntryByProjectController extends BaseSampleEntryController {

    private static final String[] ALLOWED_FIELDS = new String[] { "currentDate", "domain", "project",
            "patientLastUpdated", "personLastUpdated", "patientUpdateStatus", "patientPK", "samplePK",
            "observations.projectFormName", "ProjectData.ARVcenterName", "ProjectData.ARVcenterCode",
            "observations.nameOfDoctor", "receivedDateForDisplay", "receivedTimeForDisplay", "interviewDate",
            "interviewTime", "subjectNumber", "siteSubjectNumber", "labNo", "gender", "birthDateForDisplay",
            "ProjectData.dryTubeTaken", "ProjectData.edtaTubeTaken", "ProjectData.serologyHIVTest",
            "ProjectData.glycemiaTest", "ProjectData.creatinineTest", "ProjectData.transaminaseTest",
            "ProjectData.nfsTest", "ProjectData.cd4cd8Test", "ProjectData.viralLoadTest", "ProjectData.genotypingTest",
            "observations.underInvestigation", "ProjectData.underInvestigationNote", "observations.hivStatus",
            "ProjectData.EIDSiteName", "projectData.EIDsiteCode", "observations.whichPCR",
            "observations.reasonForSecondPCRTest", "observations.nameOfRequestor", "observations.nameOfSampler",
            "observations.eidInfantPTME", "observations.eidTypeOfClinic", "observations.eidHowChildFed",
            "observations.eidStoppedBreastfeeding", "observations.eidInfantSymptomatic", "observations.eidInfantsARV",
            "observations.eidInfantCotrimoxazole", "observations.eidMothersHIVStatus", "observations.eidMothersARV",
            "ProjectData.dbsTaken", "ProjectData.dnaPCR", "ProjectData.INDsiteName", "ProjectData.address",
            "ProjectData.phoneNumber", "ProjectData.faxNumber", "ProjectData.email", "observations.indFirstTestDate",
            "observations.indFirstTestName", "observations.indFirstTestResult", "observations.indSecondTestDate",
            "observations.indSecondTestName", "observations.indSecondTestResult", "observations.indSiteFinalResult",
            "observations.reasonForRequest", "ProjectData.murexTest", "ProjectData.integralTest",
            "ProjectData.vironostikaTest", "ProjectData.innoliaTest", "ProjectData.transaminaseALTLTest",
            "ProjectData.transaminaseASTLTest", "ProjectData.gbTest", "ProjectData.lymphTest", "ProjectData.monoTest",
            "ProjectData.eoTest", "ProjectData.basoTest", "ProjectData.grTest", "ProjectData.hbTest",
            "ProjectData.hctTest", "ProjectData.vgmTest", "ProjectData.tcmhTest", "ProjectData.ccmhTest",
            "ProjectData.plqTest", "ProjectData.cd3CountTest", "ProjectData.cd4CountTest", "observations.vlPregnancy",
            "observations.vlSuckle", "observations.currentARVTreatment", "observations.arvTreatmentInitDate",
            "observations.arvTreatmentRegime", "observations.currentARVTreatmentINNsList*",
            "observations.vlReasonForRequest", "observations.vlOtherReasonForRequest", "observations.initcd4Count",
            "observations.initcd4Percent", "observations.initcd4Date", "observations.demandcd4Count",
            "observations.demandcd4Percent", "observations.demandcd4Date", "observations.vlBenefit",
            "observations.priorVLValue", "observations.priorVLDate" };

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping(value = "/SampleEntryByProject", method = RequestMethod.GET)
    public ModelAndView showSampleEntryByProject(HttpServletRequest request) {
        SampleEntryByProjectForm form = new SampleEntryByProjectForm();

        Date today = Calendar.getInstance().getTime();
        String dateAsText = DateUtil.formatDateAsText(today);
        form.setReceivedDateForDisplay(dateAsText);
        form.setInterviewDate(dateAsText);

        setDisplayLists(form);
        addFlashMsgsToRequest(request);

        return findForward(FWD_SUCCESS, form);
    }

    @RequestMapping(value = "/SampleEntryByProject", method = RequestMethod.POST)
    public ModelAndView postSampleEntryByProject(HttpServletRequest request,
            @ModelAttribute("form") @Valid SampleEntryByProjectForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            saveErrors(result);
            setDisplayLists(form);
            return findForward(FWD_FAIL_INSERT, form);
        }

        String forward;

        ISampleSecondEntry sampleSecondEntry = SpringContext.getBean(ISampleSecondEntry.class);
        sampleSecondEntry.setFieldsFromForm(form);
        sampleSecondEntry.setSysUserId(getSysUserId(request));
        sampleSecondEntry.setRequest(request);
        if (sampleSecondEntry.canAccession()) {
            forward = handleSave(request, sampleSecondEntry, form);
            if (forward != null) {
                if (FWD_SUCCESS_INSERT.equals(forward)) {
                    redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
                } else {
                    setDisplayLists(form);
                }
                return findForward(forward, form);
            }
        }
        ISampleEntry sampleEntry = SpringContext.getBean(ISampleEntry.class);
        sampleEntry.setFieldsFromForm(form);
        sampleEntry.setSysUserId(getSysUserId(request));
        sampleEntry.setRequest(request);
        if (sampleEntry.canAccession()) {
            forward = handleSave(request, sampleEntry, form);
            if (forward != null) {
                if (FWD_SUCCESS_INSERT.equals(forward)) {
                    redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
                } else {
                    setDisplayLists(form);
                }
                return findForward(forward, form);
            }
        }
        ISampleEntryAfterPatientEntry sampleEntryAfterPatientEntry = SpringContext
                .getBean(ISampleEntryAfterPatientEntry.class);
        sampleEntryAfterPatientEntry.setFieldsFromForm(form);
        sampleEntryAfterPatientEntry.setSysUserId(getSysUserId(request));
        sampleEntryAfterPatientEntry.setRequest(request);
        if (sampleEntryAfterPatientEntry.canAccession()) {
            forward = handleSave(request, sampleEntryAfterPatientEntry, form);
            if (forward != null) {
                if (FWD_SUCCESS_INSERT.equals(forward)) {
                    redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
                } else {
                    setDisplayLists(form);
                }
                return findForward(forward, form);
            }
        }
        logAndAddMessage(request, "postSampleEntryByProject", "errors.UpdateException");

        setDisplayLists(form);
        return findForward(FWD_FAIL_INSERT, form);
    }

    public SampleItem getSampleItem(Sample sample, TypeOfSample typeofsample) {
        SampleItem item = new SampleItem();
        item.setSample(sample);
        item.setTypeOfSample(typeofsample);
        item.setSortOrder(Integer.toString(1));
        item.setStatusId(SpringContext.getBean(IStatusService.class).getStatusID(SampleStatus.Entered));

        return item;
    }

    private void setDisplayLists(SampleEntryByProjectForm form) {
        Map<String, List<Dictionary>> formListsMapOfLists = new HashMap<>();
        List<Dictionary> listOfDictionary = new ArrayList<>();
        List<IdValuePair> genders = DisplayListService.getInstance().getList(ListType.GENDERS);

        for (IdValuePair i : genders) {
            Dictionary dictionary = new Dictionary();
            dictionary.setId(i.getId());
            dictionary.setDictEntry(i.getValue());
            listOfDictionary.add(dictionary);
        }

        formListsMapOfLists.put("GENDERS", listOfDictionary);
        form.setFormLists(formListsMapOfLists);

        // Get Lists
        Map<String, List<Dictionary>> observationHistoryMapOfLists = new HashMap<>();
        observationHistoryMapOfLists.put("EID_WHICH_PCR", ObservationHistoryList.EID_WHICH_PCR.getList());
        observationHistoryMapOfLists.put("EID_SECOND_PCR_REASON",
                ObservationHistoryList.EID_SECOND_PCR_REASON.getList());
        observationHistoryMapOfLists.put("EID_TYPE_OF_CLINIC", ObservationHistoryList.EID_TYPE_OF_CLINIC.getList());
        observationHistoryMapOfLists.put("EID_HOW_CHILD_FED", ObservationHistoryList.EID_HOW_CHILD_FED.getList());
        observationHistoryMapOfLists.put("EID_STOPPED_BREASTFEEDING",
                ObservationHistoryList.EID_STOPPED_BREASTFEEDING.getList());
        observationHistoryMapOfLists.put("YES_NO", ObservationHistoryList.YES_NO.getList());
        observationHistoryMapOfLists.put("EID_INFANT_PROPHYLAXIS_ARV",
                ObservationHistoryList.EID_INFANT_PROPHYLAXIS_ARV.getList());
        observationHistoryMapOfLists.put("YES_NO_UNKNOWN", ObservationHistoryList.YES_NO_UNKNOWN.getList());
        observationHistoryMapOfLists.put("EID_MOTHERS_HIV_STATUS",
                ObservationHistoryList.EID_MOTHERS_HIV_STATUS.getList());
        observationHistoryMapOfLists.put("EID_MOTHERS_ARV_TREATMENT",
                ObservationHistoryList.EID_MOTHERS_ARV_TREATMENT.getList());
        observationHistoryMapOfLists.put("HIV_STATUSES", ObservationHistoryList.HIV_STATUSES.getList());
        observationHistoryMapOfLists.put("SPECIAL_REQUEST_REASONS",
                ObservationHistoryList.SPECIAL_REQUEST_REASONS.getList());
        observationHistoryMapOfLists.put("ARV_REGIME", ObservationHistoryList.ARV_REGIME.getList());
        observationHistoryMapOfLists.put("ARV_REASON_FOR_VL_DEMAND",
                ObservationHistoryList.ARV_REASON_FOR_VL_DEMAND.getList());

        form.setDictionaryLists(observationHistoryMapOfLists);

        // Get EID Sites
        Map<String, List<Organization>> organizationTypeMapOfLists = new HashMap<>();
        organizationTypeMapOfLists.put("ARV_ORGS", OrganizationTypeList.ARV_ORGS.getList());
        organizationTypeMapOfLists.put("ARV_ORGS_BY_NAME", OrganizationTypeList.ARV_ORGS_BY_NAME.getList());
        organizationTypeMapOfLists.put("EID_ORGS_BY_NAME", OrganizationTypeList.EID_ORGS_BY_NAME.getList());
        organizationTypeMapOfLists.put("EID_ORGS", OrganizationTypeList.EID_ORGS.getList());
        form.setOrganizationTypeLists(organizationTypeMapOfLists);
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "sampleEntryByProjectDefinition";
        } else if (FWD_FAIL.equals(forward)) {
            return "homePageDefinition";
        } else if (FWD_SUCCESS_INSERT.equals(forward)) {
            return "redirect:/SampleEntryByProject.do?type=" + Encode.forUriComponent(request.getParameter("type"));
        } else if (FWD_FAIL_INSERT.equals(forward)) {
            return "sampleEntryByProjectDefinition";
        } else {
            return "PageNotFound";
        }
    }

    @Override
    protected String getPageTitleKey() {
        return null;
    }

    @Override
    protected String getPageSubtitleKey() {
        return null;
    }
}
