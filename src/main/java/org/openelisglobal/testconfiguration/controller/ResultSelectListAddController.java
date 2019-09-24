package org.openelisglobal.testconfiguration.controller;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.testconfiguration.form.PanelCreateForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ResultSelectListAddController extends BaseController {

    @RequestMapping(value = "/ResultSelectListAdd", method = RequestMethod.GET)
    public ModelAndView showPanelCreate(HttpServletRequest request) {
        return findForward(FWD_SUCCESS, null);
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "resultSelectListDefinition";
        } else if (FWD_SUCCESS_INSERT.equals(forward)) {
            return "redirect:/ResultSelectListAdd.do";
        } else if (FWD_FAIL_INSERT.equals(forward)) {
            return "resultSelectListDefinition";
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
