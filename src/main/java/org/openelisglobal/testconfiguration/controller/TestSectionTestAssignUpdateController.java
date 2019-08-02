package org.openelisglobal.testconfiguration.controller;

import javax.servlet.http.HttpServletRequest;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.validator.BaseErrors;
import org.openelisglobal.testconfiguration.form.TestSectionTestAssignForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestSectionTestAssignUpdateController extends BaseController {
    @RequestMapping(value = "/TestSectionTestAssignUpdate", method = RequestMethod.GET)
    public ModelAndView showTestSectionTestAssignUpdate(HttpServletRequest request,
            @ModelAttribute("form") TestSectionTestAssignForm form) {
        String forward = FWD_SUCCESS;
        if (form == null) {
            form = new TestSectionTestAssignForm();
        }
        form.setFormAction("");
        Errors errors = new BaseErrors();

        return findForward(forward, form);
    }

    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "/TestSectionTestAssign.do";
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
