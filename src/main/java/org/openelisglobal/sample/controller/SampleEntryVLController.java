package org.openelisglobal.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.openelisglobal.sample.form.SampleEntryByProjectForm;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.validator.BaseErrors;

@Controller
public class SampleEntryVLController extends BaseController {
    @RequestMapping(value = "/SampleEntryVL", method = RequestMethod.GET)
    public ModelAndView showSampleEntryVL(HttpServletRequest request,
            @ModelAttribute("form") SampleEntryByProjectForm form) {
        String forward = FWD_SUCCESS;
        if (form == null) {
            form = new SampleEntryByProjectForm();
        }
        form.setFormAction("");
        Errors errors = new BaseErrors();

        return findForward(forward, form);
    }

    protected String findLocalForward(String forward) {
        if ("vl_entry".equals(forward)) {
            return "sampleEntryVLDefinition";
        } else if (FWD_FAIL.equals(forward)) {
            return "homePageDefinition";
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
