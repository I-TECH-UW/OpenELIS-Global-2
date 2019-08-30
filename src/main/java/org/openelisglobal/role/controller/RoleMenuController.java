package org.openelisglobal.role.controller;

import javax.servlet.http.HttpServletRequest;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.validator.BaseErrors;
import org.openelisglobal.role.form.RoleMenuForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//seemingly unused controller
@Controller
public class RoleMenuController extends BaseController {
    @RequestMapping(value = "/RoleMenu", method = RequestMethod.GET)
    public ModelAndView showRoleMenu(HttpServletRequest request, @ModelAttribute("form") RoleMenuForm form) {
        String forward = FWD_SUCCESS;
        if (form == null) {
            form = new RoleMenuForm();
        }
        form.setFormAction("");
        Errors errors = new BaseErrors();

        return findForward(forward, form);
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "haitiMasterListsPageDefinition";
        } else if (FWD_FAIL.equals(forward)) {
            return "/MasterListsPage.do";
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
