package spring.mine.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.login.form.LoginForm;

@Controller
public class LoginPageController extends BaseController {

	@RequestMapping(value = "/LoginPage", method = RequestMethod.GET)
	public ModelAndView showLoginPage(@ModelAttribute("form") LoginForm form, HttpServletRequest request) {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new LoginForm();
		}

		form.setFormAction("ValidateLogin.do");

		return findForward(forward, form);
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("loginPageDefinition", "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("loginPageDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
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
