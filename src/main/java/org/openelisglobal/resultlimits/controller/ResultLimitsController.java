package org.openelisglobal.resultlimits.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.validator.BaseErrors;
import org.openelisglobal.resultlimits.form.ResultLimitsForm;

//seemingly unused controller
@Controller
public class ResultLimitsController extends BaseController {
    @RequestMapping(value = "/ResultLimits", method = RequestMethod.GET)
    public ModelAndView showResultLimits(HttpServletRequest request, @ModelAttribute("form") ResultLimitsForm form) {
        String forward = FWD_SUCCESS;
        if (form == null) {
            form = new ResultLimitsForm();
        }
        form.setFormAction("");
        Errors errors = new BaseErrors();

        return findForward(forward, form);
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "resultLimitsDefinition";
        } else if (FWD_FAIL.equals(forward)) {
            return "redirect:/MasterListsPage.do";
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
