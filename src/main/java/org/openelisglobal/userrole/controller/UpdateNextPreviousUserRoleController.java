package org.openelisglobal.userrole.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.validator.BaseErrors;
import org.openelisglobal.userrole.form.UserRoleForm;

//seemingly unused controller
@Controller
public class UpdateNextPreviousUserRoleController extends BaseController {
    @RequestMapping(value = "/UpdateNextPreviousUserRole", method = RequestMethod.GET)
    public ModelAndView showUpdateNextPreviousUserRole(HttpServletRequest request,
            @ModelAttribute("form") UserRoleForm form) {
        String forward = FWD_SUCCESS;
        if (form == null) {
            form = new UserRoleForm();
        }
        form.setFormAction("");
        Errors errors = new BaseErrors();

        return findForward(forward, form);
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "/UserRole.do";
        } else if (FWD_FAIL.equals(forward)) {
            return "userRoleDefinition";
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
