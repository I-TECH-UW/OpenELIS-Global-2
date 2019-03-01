package spring.mine.login.controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.login.form.LoginChangePasswordForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.provider.validation.PasswordValidationFactory;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.login.dao.LoginDAO;
import us.mn.state.health.lims.login.daoimpl.LoginDAOImpl;
import us.mn.state.health.lims.login.valueholder.Login;

@Controller
public class ChangePasswordLoginController extends BaseController {

	@RequestMapping(value = "/ChangePasswordLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView showChangePasswordLogin(HttpServletRequest request) {
		String forward = FWD_SUCCESS;
		LoginChangePasswordForm form = new LoginChangePasswordForm();
		form.setFormAction("UpdateLoginChangePassword.do");
		Errors errors = new BaseErrors();

		form.setPassword("");
		form.setNewPassword("");
		form.setConfirmPassword("");

		return findForward(forward, form);
	}

	@RequestMapping(value = "/UpdateLoginChangePassword", method = RequestMethod.POST)
	public ModelAndView showUpdateLoginChangePassword(@Valid @ModelAttribute("form") LoginChangePasswordForm form,
			ModelMap model, HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS_INSERT;

		String newPassword = form.getNewPassword();
		String confirmPassword = form.getConfirmPassword();

		Errors errors = new BaseErrors();
		if (GenericValidator.isBlankOrNull(newPassword) || !newPassword.equals(confirmPassword)) {
			errors.reject("login.error.password.notmatch");
		} else if (!PasswordValidationFactory.getPasswordValidator().passwordValid(newPassword)) {
			errors.reject("login.error.message");
		}

		if (errors.hasErrors()) {
			saveErrors(errors);
			return findForward(FWD_FAIL_INSERT, form);
		}

		Login login = new Login();
		org.hibernate.Transaction tx = HibernateUtil.getSession().beginTransaction();
		// populate valueholder from form
		PropertyUtils.copyProperties(login, form);
		LoginDAO loginDAO = new LoginDAOImpl();
		boolean isSuccess = false;
		try {
			// get user infomation
			Login loginInfo = loginDAO.getValidateLogin(login);
			if (loginInfo == null) {
				tx.rollback();
				errors.reject("login.error.message");
				saveErrors(errors);
				return findForward(FWD_FAIL_INSERT, form);
			} else {

				// validate account disabled
				if (loginInfo.getAccountDisabled().equals(YES)) {
					errors.reject("login.error.account.disable");
					saveErrors(errors);
					return findForward(FWD_FAIL_INSERT, form);
				}
				// validate account locked
				if (loginInfo.getAccountLocked().equals(YES)) {
					errors.reject("login.error.account.disable");
					saveErrors(errors);
					return findForward(FWD_FAIL_INSERT, form);
				}
				// validate password expired day
				// bugzilla 2286
				if (loginInfo.getPasswordExpiredDayNo() <= 0) {
					errors.reject("login.error.password.expired");
					saveErrors(errors);
					return findForward(FWD_FAIL_INSERT, form);
				}
				// validate user id exists in system_user table
				if (loginInfo.getSystemUserId() == 0) {
					errors.reject("login.error.system.user.id", loginInfo.getLoginName());
					saveErrors(errors);
					return findForward(FWD_FAIL_INSERT, form);
				}

				// validate and update password
				loginInfo.setPassword(login.getNewPassword());

				java.util.Calendar rightNow = java.util.Calendar.getInstance();
				rightNow.add(java.util.Calendar.MONTH,
						Integer.parseInt(SystemConfiguration.getInstance().getLoginUserChangePasswordExpiredMonth()));
				loginInfo.setPasswordExpiredDate(new java.sql.Date(rightNow.getTimeInMillis()));

				loginInfo.setSysUserId(String.valueOf(loginInfo.getSystemUserId())); // there is no loggedin user when
																						// you reset your password
				isSuccess = loginDAO.updatePassword(loginInfo);
				if (isSuccess) {
					tx.commit();
					errors.reject("login.success.changePass.message");
					saveErrors(errors);
				} else {
					tx.rollback();
					errors.reject("login.error.password.requirement");
					saveErrors(errors);
					forward = FWD_FAIL_INSERT;
				}
			}

		} catch (LIMSRuntimeException lre) {
			// bugzilla 2154
			LogEvent.logError("LoginChangePasswordUpdateAction", "performAction()", lre.toString());
			tx.rollback();
			errors.reject("login.error.message");
			saveErrors(errors);
			return findForward(FWD_FAIL_INSERT, form);
		} finally {
			HibernateUtil.closeSession();
		}

		form.setFormAction("ValidateLogin.do");
		return findForward(forward, form);
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if (FWD_SUCCESS.equals(forward)) {
			return new ModelAndView("loginChangePasswordDefinition", "form", form);
		} else if (FWD_FAIL.equals(forward)) {
			return new ModelAndView("redirect:/LoginPage.do", "form", form);
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return new ModelAndView("redirect:/LoginPage.do", "form", form);
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return new ModelAndView("redirect:/ChangePasswordLogin.do?error=true", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
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
