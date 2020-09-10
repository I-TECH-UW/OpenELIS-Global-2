package org.openelisglobal.menu.controller;

import javax.servlet.http.HttpServletRequest;

import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.form.MainForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OpenlmisPageController extends BaseController {

    private static final String[] ALLOWED_FIELDS = new String[] {};

    @Value("${org.openelisglobal.inventoryUrl:https://test.openlmis.org/#!/login}")
    private String inventoryUrl;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping(value = "/OpenlmisPage", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView showOpenlmisPage(HttpServletRequest request) {

        request.setAttribute("inventoryUrl", inventoryUrl);
        MainForm form = new MainForm();
        return findForward(FWD_SUCCESS, form);
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "openlmisPageDefinition";
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
