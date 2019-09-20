package org.openelisglobal.qaevent.controller;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.exception.LIMSInvalidConfigurationException;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.patient.action.bean.PatientSearch;
import org.openelisglobal.qaevent.form.NonConformingEventForm;
import org.openelisglobal.qaevent.service.NCEventService;
import org.openelisglobal.qaevent.service.NceCategoryService;
import org.openelisglobal.qaevent.service.NceSpecimenService;
import org.openelisglobal.qaevent.service.NceTypeService;
import org.openelisglobal.qaevent.valueholder.NcEvent;
import org.openelisglobal.qaevent.valueholder.NceCategory;
import org.openelisglobal.qaevent.valueholder.NceSpecimen;
import org.openelisglobal.qaevent.valueholder.NceType;
import org.openelisglobal.qaevent.worker.NonConformingEventWorker;
import org.openelisglobal.sampleitem.service.SampleItemService;
import org.openelisglobal.sampleitem.valueholder.SampleItem;
import org.openelisglobal.systemuser.service.SystemUserService;
import org.openelisglobal.systemuser.valueholder.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ViewNonConformingEventController extends BaseController {

    @Autowired
    private NceCategoryService nceCategoryService;
    @Autowired
    private NceTypeService nceTypeService;
    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private NCEventService ncEventService;
    @Autowired
    private NceSpecimenService nceSpecimenService;
    @Autowired
    private SampleItemService sampleItemService;
    @Autowired
    private NonConformingEventWorker nonConformingEventWorker;

    @RequestMapping(value = "/ViewNonConformingEvent", method = RequestMethod.GET)
    public ModelAndView showReportNonConformingEvent(HttpServletRequest request) throws LIMSInvalidConfigurationException,
            IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        NonConformingEventForm form = new NonConformingEventForm();
        form.setCurrentUserId(getSysUserId(request));
        form.setPatientSearch(new PatientSearch());
        String nceNumber = request.getParameter("nceNumber");

        if (!GenericValidator.isBlankOrNull(nceNumber)) {
           initForm(nceNumber, form);
            form.setNceCategories(nceCategoryService.getAllNceCategories());
            form.setNceTypes(nceTypeService.getAllNceTypes());
            PropertyUtils.setProperty(form, "labComponentList",
                 DisplayListService.getInstance().getList(DisplayListService.ListType.LAB_COMPONENT));
            PropertyUtils.setProperty(form, "severityConsequencesList",
                    DisplayListService.getInstance().getList(DisplayListService.ListType.SEVERITY_CONSEQUENCES_LIST));
            PropertyUtils.setProperty(form, "severityRecurranceList",
                    DisplayListService.getInstance().getList(DisplayListService.ListType.SEVERITY_RECURRENCE_LIST));
        }

        addFlashMsgsToRequest(request);
        request.getSession().setAttribute(SAVE_DISABLED, TRUE);

        return findForward(FWD_SUCCESS, form);
    }

    @RequestMapping(value = "/ViewNonConformingEvent", method = RequestMethod.POST)
    public ModelAndView showReportNonConformingEventUpdate(HttpServletRequest request,
                                                           @ModelAttribute("form") NonConformingEventForm form,
                                                           BindingResult result, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);

        boolean updated = nonConformingEventWorker.updateFollowUp(form);
        if (updated) {
            return findForward(FWD_SUCCESS_INSERT, form);
        } else {
            return findForward(FWD_FAIL_INSERT, form);
        }

    }

    private void initForm(String nceNumber, NonConformingEventForm form) throws LIMSInvalidConfigurationException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        SystemUser systemUser = systemUserService.getUserById(form.getCurrentUserId());
        form.setName(systemUser.getFirstName() + " " + systemUser.getLastName());
        form.setNceNumber(System.currentTimeMillis() + "");
        NcEvent event = ncEventService.getMatch("nceNumber", nceNumber).get();
        if (event != null) {
            form.setReportDate(DateUtil.formatDateAsText(event.getReportDate()));
            form.setDateOfEvent(DateUtil.formatDateAsText(event.getDateOfEvent()));
            form.setName(event.getName());
            form.setReporterName(event.getNameOfReporter());
            form.setPrescriberName(event.getPrescriberName());
            form.setSite(event.getSite());
            form.setDescription(event.getDescription());
            form.setSuspectedCauses(event.getSuspectedCauses());
            form.setProposedAction(event.getProposedAction());
            form.setNceNumber(event.getNceNumber());
            form.setId(event.getId());
            form.setLabOrderNumber(event.getLabOrderNumber());

            List<NceSpecimen> specimenList = nceSpecimenService.getAllMatching("nceId", event.getId());

            List<SampleItem> sampleItems = new ArrayList<>();
            for (NceSpecimen specimen: specimenList) {
                SampleItem si = sampleItemService.getData(specimen.getSampleItemId() + "");
                sampleItems.add(si);
            }
            form.setSpecimens(sampleItems);
        }
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
            return "viewNonConformingEventDefiniton";
        } else if (FWD_SUCCESS_INSERT.equals(forward)) {
            return "redirect:/ViewNonConformingEvent.do";
        } else if (FWD_FAIL_INSERT.equals(forward)) {
            return "viewNonConformingEventDefiniton";
        } else {
            return "PageNotFound";
        }
    }
}
