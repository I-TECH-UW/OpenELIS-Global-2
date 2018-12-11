package spring.mine.login.controller;

import java.lang.String;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
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
public class UpdateLoginChangePasswordController extends BaseController {

	@RequestMapping(value = "/UpdateLoginChangePassword", method = RequestMethod.POST)
	public ModelAndView showUpdateLoginChangePassword(@Valid @ModelAttribute("form") LoginChangePasswordForm form,
			BindingResult result, ModelMap model, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;

		String newPassword = form.getNewPassword();
		String confirmPassword = form.getConfirmPassword();

		if (GenericValidator.isBlankOrNull(newPassword) || !newPassword.equals(confirmPassword)) {
			result.reject("login.error.password.notmatch");
		} else if (!PasswordValidationFactory.getPasswordValidator().passwordValid(newPassword)) {
			result.reject("login.error.message");
		}

		if (result.hasErrors()) {
			saveErrors(result, form);
			return findForward(FWD_FAIL, form);
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
				//errors = new ActionMessages();
				//ActionError error = new ActionError("login.error.message", null, null);
				//errors.add(ActionMessages.GLOBAL_MESSAGE, error);
				result.reject("login.error.message");
				saveErrors(result, form);
				return findForward(FWD_FAIL, form);
			} else {

				// validate account disabled
				if (loginInfo.getAccountDisabled().equals(YES)) {
					//errors = new ActionMessages();
					//ActionError error = new ActionError("login.error.account.disable", null, null);
					//errors.add(ActionMessages.GLOBAL_MESSAGE, error);
					//saveErrors(request, errors);
					//return mapping.findForward(IActionConstants.FWD_FAIL);
					result.reject("login.error.account.disable");
					saveErrors(result, form);
					return findForward(FWD_FAIL, form);
				}
				// validate account locked
				if (loginInfo.getAccountLocked().equals(YES)) {
					//errors = new ActionMessages();
					//ActionError error = new ActionError("login.error.account.lock", null, null);
					//errors.add(ActionMessages.GLOBAL_MESSAGE, error);
					//saveErrors(request, errors);
					//return mapping.findForward(FWD_FAIL);
					result.reject("login.error.account.disable");
					saveErrors(result, form);
					return findForward(FWD_FAIL, form);
				}
				// validate password expired day
				// bugzilla 2286
				if (loginInfo.getPasswordExpiredDayNo() <= 0) {
					//errors = new ActionMessages();
					//ActionError error = new ActionError("login.error.password.expired", null, null);
					//errors.add(ActionMessages.GLOBAL_MESSAGE, error);
					//saveErrors(request, errors);
					//return mapping.findForward(FWD_FAIL);
					result.reject("login.error.password.expired");
					saveErrors(result, form);
					return findForward(FWD_FAIL, form);
				}
				/*
				 * if ( loginInfo.getPasswordExpiredDayNo() <=
				 * Integer.parseInt(SystemConfiguration
				 * .getInstance().getLoginUserChangePasswordAllowDay()) ) { errors = new
				 * ActionMessages(); ActionError error = new
				 * ActionError("login.error.password.day", SystemConfiguration.getInstance
				 * ().getLoginUserChangePasswordAllowDay(), null);
				 * errors.add(ActionMessages.GLOBAL_MESSAGE, error); saveErrors(request,
				 * errors); return mapping.findForward(FWD_FAIL); }
				 */
				// validate user id exists in system_user table
				if (loginInfo.getSystemUserId() == 0) {
					//errors = new ActionMessages();
					//ActionError error = new ActionError("login.error.system.user.id", loginInfo.getLoginName(), null);
					//errors.add(ActionMessages.GLOBAL_MESSAGE, error);
					//saveErrors(request, errors);
					//return mapping.findForward(FWD_FAIL);
					result.reject("login.error.system.user.id", loginInfo.getLoginName());
					saveErrors(result, form);
					return findForward(FWD_FAIL, form);
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
					// successfully changed password
					// force user to relogin with the new password
					//errors = new ActionMessages();
					//ActionError error = new ActionError("login.success.changePass.message", null, null);
					//errors.add(ActionMessages.GLOBAL_MESSAGE, error);
					//saveErrors(request, errors);
					result.reject("login.success.changePass.message");
					saveErrors(result, form);
				} else {
					tx.rollback();
					//errors = new ActionMessages();
					//ActionError error = new ActionError("login.error.password.requirement", null, null);
					//errors.add(ActionMessages.GLOBAL_MESSAGE, error);
					//saveErrors(request, errors);
					//forward = FWD_FAIL;
					result.reject("login.error.password.requirement");
					saveErrors(result, form);
					forward = FWD_FAIL;
				}
			}

		} catch (LIMSRuntimeException lre) {
			// bugzilla 2154
			LogEvent.logError("LoginChangePasswordUpdateAction", "performAction()", lre.toString());
			tx.rollback();
			//errors = new ActionMessages();
			//ActionError error = new ActionError("login.error.message", null, null);
			//errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			//saveErrors(request, errors);
			//return mapping.findForward(FWD_FAIL);
			result.reject("login.error.message");
			saveErrors(result, form);
			return findForward(FWD_FAIL, form);
		} finally {
			HibernateUtil.closeSession();
		}

		return findForward(forward, form);
	}

	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("forward:/LoginPage.do", "form", form);
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
