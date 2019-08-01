package org.openelisglobal.externalconnections.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.openelisglobal.common.constants.Constants;
import org.openelisglobal.common.controller.BaseMenuController;
import org.openelisglobal.common.form.MenuForm;
import org.openelisglobal.common.validator.BaseErrors;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.externalconnections.form.ExternalConnectionsConfigForm;
import org.openelisglobal.common.formfields.AdminFormFields;
import org.openelisglobal.common.formfields.AdminFormFields.Field;
import org.openelisglobal.display.URLForDisplay;

@Controller
public class ExternalConnectionsConfigMenuController extends BaseMenuController {
    // result reporting config directly in menu as it was originally
    // @RequestMapping(value = "/ExternalConnectionsConfigMenu", method =
    // RequestMethod.GET)
    public ModelAndView showExternalConnectionsConfigMenu(HttpServletRequest request,
            RedirectAttributes redirectAttributes)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String forward = FWD_SUCCESS;
        ExternalConnectionsConfigForm form = new ExternalConnectionsConfigForm();

        // forward = performMenuAction(form, request);
        if (FWD_FAIL.equals(forward)) {
            Errors errors = new BaseErrors();
            errors.reject("error.generic");
            redirectAttributes.addFlashAttribute(Constants.REQUEST_ERRORS, errors);
            return findForward(forward, form);
        } else {
            return findForward(forward, form);
        }

    }

    @Override
    protected List createMenuList(MenuForm form, HttpServletRequest request) throws Exception {
        AdminFormFields adminFields = AdminFormFields.getInstance();
        List<URLForDisplay> menuList = new ArrayList<>();
        URLForDisplay url = new URLForDisplay();
        if (adminFields.useField(Field.RESULT_REPORTING_CONFIGURATION)) {
            url.setDisplayKey("resultreporting.browse.title");
            url.setUrlAddress(request.getContextPath() + "/ResultReportingConfiguration.do");
            menuList.add(url);
        }
        return menuList;
    }

    @Override
    protected String getPageTitleKey() {
        return MessageUtil.getMessage("externalconnect.browse.title");
    }

    @Override
    protected String getPageSubtitleKey() {
        return MessageUtil.getMessage("externalconnect.browse.title");
    }

    @Override
    protected String getDeactivateDisabled() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "externalConnectionsConfigMenuDefinition";
        } else if (FWD_FAIL.equals(forward)) {
            return "/MasterListsPage.do";
        } else {
            return "PageNotFound";
        }
    }
}
