package spring.mine.login.controller;

import java.lang.String;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.generated.forms.LoginForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;

@Controller
public class LoginPageController extends BaseController {
  @RequestMapping(
      value = "/LoginPage",
      method = RequestMethod.GET
  )
  public ModelAndView showLoginPage(HttpServletRequest request) {
    String forward = FWD_SUCCESS;
    LoginForm form = new LoginForm();
    form.setFormName("loginForm");
	form.setFormAction("ValidateLogin.html");
    BaseErrors errors = new BaseErrors();
    cleanUpSession(request);

	// Set language to be used
	setLanguage(request);

	// Set page titles in request attribute
	setPageTitles(request, form);

	// Set the form attributes
	setFormAttributes(form, request);

    return findForward(forward, form);
  }

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("success".equals(forward)) {
      return new ModelAndView("loginPageDefinition", "form", form);
    } else if ("fail".equals(forward)) {
      return new ModelAndView("loginPageDefinition", "form", form);
    } else {
      return new ModelAndView("PageNotFound");
    }
  }

  protected String getPageTitleKey() {
    return null;
  }

  protected String getPageSubtitleKey() {
    return null;
  }
  
  /**
   * Cleanup all the session variables
   * 
   * @param request
   *            is HttpServletRequest
   */
  private void cleanUpSession(HttpServletRequest request) {
	if (request.getSession().getAttribute(USER_SESSION_DATA) != null)
	    request.getSession().removeAttribute(USER_SESSION_DATA);
  }
}
