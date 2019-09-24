package org.openelisglobal.qaevent.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.exception.LIMSInvalidConfigurationException;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.patient.action.bean.PatientSearch;
import org.openelisglobal.qaevent.form.NonConformingEventForm;
import org.openelisglobal.qaevent.service.NceCategoryService;
import org.openelisglobal.qaevent.service.NceTypeService;
import org.openelisglobal.qaevent.valueholder.NceActionLog;
import org.openelisglobal.qaevent.worker.NonConformingEventWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Controller
public class CorrectiveActionController extends BaseController {

    @Autowired
    private NceCategoryService nceCategoryService;
    @Autowired
    private NonConformingEventWorker nonConformingEventWorker;


    @RequestMapping(value = "/NCECorrectiveAction", method = RequestMethod.GET)
    public ModelAndView showReportNonConformingEvent(HttpServletRequest request) throws LIMSInvalidConfigurationException,
            IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        NonConformingEventForm form = new NonConformingEventForm();
        form.setCurrentUserId(getSysUserId(request));
        form.setPatientSearch(new PatientSearch());
        String nceNumber = request.getParameter("nceNumber");
        if (!GenericValidator.isBlankOrNull(nceNumber)) {
            nonConformingEventWorker.initFormForCorrectiveAction(nceNumber, form);
        }
        addFlashMsgsToRequest(request);
        request.getSession().setAttribute(SAVE_DISABLED, TRUE);

        return findForward(FWD_SUCCESS, form);
    }

    @RequestMapping(value = "/NCECorrectiveAction", method = RequestMethod.POST)
    public ModelAndView showReportNonConformingEventUpdate(HttpServletRequest request,
                                                           @ModelAttribute("form") NonConformingEventForm form,
                                                           BindingResult result, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);

        boolean updated = nonConformingEventWorker.updateCorrectiveAction(form);

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
            return "correctiveActionDefiniton";
        } else if (FWD_SUCCESS_INSERT.equals(forward)) {
            return "redirect:/NCECorrectiveAction.do";
        } else if (FWD_FAIL_INSERT.equals(forward)) {
            return "correctiveActionDefiniton";
        } else {
            return "PageNotFound";
        }
    }
}
