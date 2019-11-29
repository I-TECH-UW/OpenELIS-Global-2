package org.openelisglobal.qaevent.controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.exception.LIMSInvalidConfigurationException;
import org.openelisglobal.patient.action.bean.PatientSearch;
import org.openelisglobal.qaevent.form.NonConformingEventForm;
import org.openelisglobal.qaevent.service.NCEventService;
import org.openelisglobal.qaevent.service.NceCategoryService;
import org.openelisglobal.qaevent.service.NceSpecimenService;
import org.openelisglobal.qaevent.service.NceTypeService;
import org.openelisglobal.qaevent.worker.NonConformingEventWorker;
import org.openelisglobal.sampleitem.service.SampleItemService;
import org.openelisglobal.systemuser.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            nonConformingEventWorker.initFormForFollowUp(nceNumber, form);
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
