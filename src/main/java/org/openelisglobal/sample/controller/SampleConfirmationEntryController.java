package org.openelisglobal.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.validator.BaseErrors;
import org.openelisglobal.sample.form.SampleConfirmationEntryForm;

@Controller
public class SampleConfirmationEntryController extends BaseController {
    @RequestMapping(value = "/SampleConfirmationEntry", method = RequestMethod.GET)
    public ModelAndView showSampleConfirmationEntry(HttpServletRequest request,
            @ModelAttribute("form") SampleConfirmationEntryForm form) {
        String forward = FWD_SUCCESS;
        if (form == null) {
            form = new SampleConfirmationEntryForm();
        }
        form.setFormAction("");
        Errors errors = new BaseErrors();

        return findForward(forward, form);
    }

    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "sampleConfirmationEntryDefinition";
        } else {
            return "PageNotFound";
        }
    }

    protected String getPageTitleKey() {
        return null;
    }

    protected String getPageSubtitleKey() {
        return null;
    }
}
