package org.openelisglobal.qaevent.controller;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.exception.LIMSInvalidConfigurationException;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.RequesterService;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.login.valueholder.UserSessionData;
import org.openelisglobal.patient.action.bean.PatientSearch;
import org.openelisglobal.qaevent.form.NonConformingEventForm;
import org.openelisglobal.qaevent.form.NonConformityForm;
import org.openelisglobal.qaevent.service.NceCategoryService;
import org.openelisglobal.qaevent.valueholder.NcEvent;
import org.openelisglobal.qaevent.valueholder.NceCategory;
import org.openelisglobal.qaevent.worker.NonConformingEventWorker;
import org.openelisglobal.requester.service.SampleRequesterService;
import org.openelisglobal.requester.valueholder.SampleRequester;
import org.openelisglobal.sample.service.SampleService;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.sampleitem.service.SampleItemService;
import org.openelisglobal.sampleitem.valueholder.SampleItem;
import org.openelisglobal.systemuser.service.SystemUserService;
import org.openelisglobal.systemuser.valueholder.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Controller
public class ReportNonConformingEventController extends BaseController {

    @Autowired
    private SampleService sampleService;
    @Autowired
    private SampleItemService sampleItemService;
    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private SampleRequesterService sampleRequesterService;
    @Autowired
    private RequesterService requesterService;
    @Autowired
    private NceCategoryService nceCategoryService;
    @Autowired
    private NonConformingEventWorker nonConformingEventWorker;

    @RequestMapping(value = "/ReportNonConformingEvent", method = RequestMethod.GET)
    public ModelAndView showReportNonConformingEvent(HttpServletRequest request) throws LIMSInvalidConfigurationException,
            IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        NonConformingEventForm form = new NonConformingEventForm();
        form.setCurrentUserId(getSysUserId(request));
        form.setPatientSearch(new PatientSearch());
        String labNumber = request.getParameter("labNo");
        String specimenId = request.getParameter("specimenId");
        if (!GenericValidator.isBlankOrNull(labNumber)) {
            initForm(labNumber, specimenId, form);
        }
        addFlashMsgsToRequest(request);
        request.getSession().setAttribute(SAVE_DISABLED, TRUE);

        return findForward(FWD_SUCCESS, form);
    }

    @RequestMapping(value = "/ReportNonConformingEvent", method = RequestMethod.POST)
    public ModelAndView showReportNonConformingEventUpdate(HttpServletRequest request,
                                                @ModelAttribute("form") NonConformingEventForm form,
                                                BindingResult result, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);

        boolean updated = nonConformingEventWorker.update(form);
        if (updated) {
            return findForward(FWD_SUCCESS_INSERT, form);
        } else {
            return findForward(FWD_FAIL_INSERT, form);
        }

    }

    private void initForm(String labOrderNumber, String sampleItemIds, NonConformingEventForm form) throws LIMSInvalidConfigurationException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        SystemUser systemUser = systemUserService.getUserById(form.getCurrentUserId());
        form.setName(systemUser.getFirstName() + " " + systemUser.getLastName());
        form.setNceNumber(System.currentTimeMillis() + "");
        NcEvent event = nonConformingEventWorker.create(labOrderNumber, Arrays.asList(sampleItemIds.split(",")), systemUser.getId(), form.getNceNumber());
        form.setNceNumber(event.getNceNumber());
        form.setId(event.getId());


        form.setLabOrderNumber(labOrderNumber);
        Sample sample = getSampleForLabNumber(labOrderNumber);
        boolean sampleFound = !(sample == null || GenericValidator.isBlankOrNull(sample.getId()));
        if (sampleFound) {
            List<SampleItem> sampleItems = new ArrayList<>();
            String[] sampleItemIdArray = sampleItemIds.split(",");
            List<String> ids = new ArrayList<>();
            for(String s: sampleItemIdArray) {
                SampleItem si = sampleItemService.getData(s);
                sampleItems.add(si);
                ids.add(si.getId());
            }
            form.setSpecimens(sampleItems);
        }

        PropertyUtils.setProperty(form, "reportingUnits",
                     DisplayListService.getInstance().getList(DisplayListService.ListType.TEST_SECTION));

        requesterService.setSampleId(sample.getId());
        form.setSite(requesterService.getReferringSiteName());
        form.setPrescriberName(requesterService.getRequesterLastFirstName());

        form.setNceCategories(nceCategoryService.getAllNceCategories());

        Date today = Calendar.getInstance().getTime();
        PropertyUtils.setProperty(form, "reportDate", DateUtil.formatDateAsText(today));

    }

    private Sample getSampleForLabNumber(String labNumber) throws LIMSInvalidConfigurationException {
        return sampleService.getSampleByAccessionNumber(labNumber);
    }

    @Override
    protected String getPageSubtitleKey() {
        return "nonconforming.page.title";
    }

    @Override
    protected String getPageTitleKey() {
        return "nonconforming.page.title";
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "reportNonConformingEventDefiniton";
        } else if (FWD_SUCCESS_INSERT.equals(forward)) {
            return "redirect:/ReportNonConformingEvent.do";
        } else if (FWD_FAIL_INSERT.equals(forward)) {
            return "reportNonConformingEventDefiniton";
        } else {
            return "PageNotFound";
        }
    }
}
