package org.openelisglobal.qaevent.controller;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.exception.LIMSInvalidConfigurationException;
import org.openelisglobal.patient.action.bean.PatientSearch;
import org.openelisglobal.qaevent.form.NonConformingEventForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

@Controller
public class CorrectiveActionController extends BaseController {

    @RequestMapping(value = "/CorrectiveAction", method = RequestMethod.GET)
    public ModelAndView showReportNonConformingEvent(HttpServletRequest request) throws LIMSInvalidConfigurationException,
            IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        NonConformingEventForm form = new NonConformingEventForm();
        form.setCurrentUserId(getSysUserId(request));
        form.setPatientSearch(new PatientSearch());
        String labNumber = request.getParameter("labNo");
        String specimenId = request.getParameter("specimenId");
        if (!GenericValidator.isBlankOrNull(labNumber)) {
            // initForm(labNumber, specimenId, form);
        }
        addFlashMsgsToRequest(request);
        request.getSession().setAttribute(SAVE_DISABLED, TRUE);

        return findForward(FWD_SUCCESS, form);
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
            return "correctiveActionDefiniton";
        } else if (FWD_SUCCESS_INSERT.equals(forward)) {
            return "redirect:/CorrectiveAction.do";
        } else if (FWD_FAIL_INSERT.equals(forward)) {
            return "correctiveActionDefiniton";
        } else {
            return "PageNotFound";
        }
    }
}
