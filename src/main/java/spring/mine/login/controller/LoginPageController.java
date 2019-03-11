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
