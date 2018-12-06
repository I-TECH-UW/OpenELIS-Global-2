package spring.generated.login.controller;

import java.lang.String;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.generated.forms.LoginChangePasswordForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;

@Controller
public class ChangePasswordLoginController extends BaseController {
  @RequestMapping(
      value = "/ChangePasswordLogin",
      method = RequestMethod.GET
  )
  public ModelAndView showChangePasswordLogin(HttpServletRequest request) {
    String forward = FWD_SUCCESS;
    LoginChangePasswordForm form = new LoginChangePasswordForm();
    form.setFormName("loginChangePasswordForm");
    form.setFormAction("");
    BaseErrors errors = new BaseErrors();
    ModelAndView mv = checkUserAndSetup(form, errors, request);

    if (errors.hasErrors()) {
    	return mv;
    }

    return findForward(forward, form);}

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("success".equals(forward)) {
      return new ModelAndView("loginChangePasswordDefinition", "form", form);
    } else if ("fail".equals(forward)) {
      return new ModelAndView("loginChangePasswordDefinition", "form", form);
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
}
