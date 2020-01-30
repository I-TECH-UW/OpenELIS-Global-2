package org.openelisglobal.login.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.openelisglobal.common.constants.Constants;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.login.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginPageController extends BaseController {

    private static final String[] ALLOWED_FIELDS = new String[] { "loginName", "password" };

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping(value = "/LoginPage", method = RequestMethod.GET)
    public ModelAndView showLoginPage(HttpServletRequest request, Principal principal) {
        if (principal != null) {
            return new ModelAndView(findForward(HOME_PAGE));
        }

        String forward = FWD_SUCCESS;
        LoginForm form = new LoginForm();

        // add flash attributes from other controllers into request
        addFlashMsgsToRequest(request);

        // add error messages from authentication fail controller
        Errors errors = (Errors) request.getSession().getAttribute(Constants.LOGIN_ERRORS);
        if (errors != null) {
            request.setAttribute(Constants.REQUEST_ERRORS, errors);
            request.getSession().removeAttribute(Constants.LOGIN_ERRORS);
        }

        form.setFormAction("ValidateLogin.do");

        return findForward(forward, form);
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "loginPageDefinition";
        } else {
            return "PageNotFound";
        }
    }

    @Override
    protected String getPageTitleKey() {
        return "login.title";
    }

    @Override
    protected String getPageSubtitleKey() {
        return "login.subTitle";
    }
}
