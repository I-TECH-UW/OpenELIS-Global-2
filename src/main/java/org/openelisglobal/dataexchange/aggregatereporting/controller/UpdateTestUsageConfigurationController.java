package org.openelisglobal.dataexchange.aggregatereporting.controller;

import javax.servlet.http.HttpServletRequest;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.validator.BaseErrors;
import org.openelisglobal.dataexchange.aggregatereporting.form.TestUsageConfigurationForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//seemingly unused controller
@Controller
public class UpdateTestUsageConfigurationController extends BaseController {
    @RequestMapping(value = "/UpdateTestUsageConfiguration", method = RequestMethod.GET)
    public ModelAndView showUpdateTestUsageConfiguration(HttpServletRequest request,
            @ModelAttribute("form") TestUsageConfigurationForm form) {
        String forward = FWD_SUCCESS;
        if (form == null) {
            form = new TestUsageConfigurationForm();
        }
        form.setFormAction("");
        Errors errors = new BaseErrors();

        return findForward(forward, form);
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
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
