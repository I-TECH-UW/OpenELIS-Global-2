package spring.mine.login.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.mine.common.constants.Constants;
import spring.mine.common.controller.BaseController;
import spring.mine.internationalization.MessageUtil;
import spring.mine.login.form.ChangePasswordLoginForm;
import spring.mine.login.validator.ChangePasswordLoginFormValidator;
import spring.mine.login.validator.LoginValidator;
import spring.service.login.LoginService;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.login.valueholder.Login;

@Controller
public class ChangePasswordLoginController extends BaseController {

	@Autowired
	ChangePasswordLoginFormValidator formValidator;
	@Autowired
	LoginValidator loginValidator;
	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/ChangePasswordLogin", method = RequestMethod.GET)
	public ModelAndView showChangePasswordLogin(HttpServletRequest request) {
		ChangePasswordLoginForm form = new ChangePasswordLoginForm();
		form.setFormAction("ChangePasswordLogin.do");
		return findForward(FWD_SUCCESS, form);
	}

	@RequestMapping(value = "/ChangePasswordLogin", method = RequestMethod.POST)
	public ModelAndView showUpdateLoginChangePassword(@ModelAttribute("form") @Valid ChangePasswordLoginForm form,
			BindingResult result, RedirectAttributes redirectAttributes)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		formValidator.validate(form, result);
		if (result.hasErrors()) {
			saveErrors(result);
			return findForward(FWD_FAIL_INSERT, form);
		}

//		Login newLogin = new Login();
//		// populate valueholder from form
//		PropertyUtils.copyProperties(newLogin, form);
		try {
			Login login;
			// get user information if password correct
			Optional<Login> matchedLogin = loginService.getValidatedLogin(form.getLoginName(), form.getPassword());
			if (!matchedLogin.isPresent()) {
				result.reject("login.error.message");
			} else {
				login = matchedLogin.get();
				// update fields of login before validating again
				loginService.hashPassword(login, form.getNewPassword());
				Errors loginResult = new BeanPropertyBindingResult(login, "loginInfo");
				loginValidator.unauthenticatedPasswordUpdateValidate(login, loginResult);

				if (loginResult.hasErrors()) {
					saveErrors(loginResult);
					return findForward(FWD_FAIL_INSERT, form);
				}
				loginService.update(login);
			}

		} catch (LIMSRuntimeException lre) {
			// bugzilla 2154
			LogEvent.logError("LoginChangePasswordUpdateAction", "performAction()", lre.toString());
			result.reject("login.error.message");
		}
		if (result.hasErrors()) {
			saveErrors(result);
			return findForward(FWD_FAIL_INSERT, form);
		}

		redirectAttributes.addFlashAttribute(Constants.SUCCESS_MSG,
				MessageUtil.getMessage("login.success.changePass.message"));
		return findForward(FWD_SUCCESS_INSERT, form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "loginChangePasswordDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/LoginPage.do";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "loginChangePasswordDefinition";
		} else {
			return "PageNotFound";
		}
	}

	@Override
	protected String getPageTitleKey() {
		return "login.changePass";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "login.changePass";
	}
}
