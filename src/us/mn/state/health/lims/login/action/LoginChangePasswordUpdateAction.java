/**
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/ 
 * 
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 * 
 * The Original Code is OpenELIS code.
 * 
 * Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
 */
package us.mn.state.health.lims.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.provider.validation.PasswordValidationFactory;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.login.dao.LoginDAO;
import us.mn.state.health.lims.login.daoimpl.LoginDAOImpl;
import us.mn.state.health.lims.login.valueholder.Login;

/**
 * @author Hung Nguyen (Hung.Nguyen@health.state.mn.us)
 */
public class LoginChangePasswordUpdateAction extends LoginBaseAction {

	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String forward = FWD_SUCCESS;
		BaseActionForm dynaForm = (BaseActionForm) form;

		// server-side validation (validation.xml) //regex does not match
		// instructions and can not be varied by installation
		// delete after merge
		ActionMessages errors = new ActionMessages();
		// dynaForm.validate(mapping, request);

		/*
		 * if (errors != null && errors.size() > 0) { saveErrors(request,
		 * errors); return mapping.findForward(FWD_FAIL); }
		 */

		String newPassword = dynaForm.getString("newPassword");
		String confirmPassword = dynaForm.getString("confirmPassword");

		if (GenericValidator.isBlankOrNull(newPassword) || !newPassword.equals(confirmPassword)) {
			ActionError error = new ActionError("login.error.password.notmatch", null, null);
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
		} else if (!PasswordValidationFactory.getPasswordValidator().passwordValid(newPassword)) {

			ActionError error = new ActionError("login.error.message", null, null);
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward(FWD_FAIL);
		}

		Login login = new Login();
		org.hibernate.Transaction tx = HibernateUtil.getSession().beginTransaction();
		// populate valueholder from form
		PropertyUtils.copyProperties(login, dynaForm);
		LoginDAO loginDAO = new LoginDAOImpl();
		boolean isSuccess = false;
		try {
			// get user infomation
			Login loginInfo = loginDAO.getValidateLogin(login);
			if (loginInfo == null) {
				tx.rollback();
				errors = new ActionMessages();
				ActionError error = new ActionError("login.error.message", null, null);
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
				saveErrors(request, errors);
				return mapping.findForward(FWD_FAIL);
			} else {

				// validate account disabled
				if (loginInfo.getAccountDisabled().equals(YES)) {
					errors = new ActionMessages();
					ActionError error = new ActionError("login.error.account.disable", null, null);
					errors.add(ActionMessages.GLOBAL_MESSAGE, error);
					saveErrors(request, errors);
					return mapping.findForward(IActionConstants.FWD_FAIL);
				}
				// validate account locked
				if (loginInfo.getAccountLocked().equals(YES)) {
					errors = new ActionMessages();
					ActionError error = new ActionError("login.error.account.lock", null, null);
					errors.add(ActionMessages.GLOBAL_MESSAGE, error);
					saveErrors(request, errors);
					return mapping.findForward(FWD_FAIL);
				}
				// validate password expired day
				// bugzilla 2286
				if (loginInfo.getPasswordExpiredDayNo() <= 0) {
					errors = new ActionMessages();
					ActionError error = new ActionError("login.error.password.expired", null, null);
					errors.add(ActionMessages.GLOBAL_MESSAGE, error);
					saveErrors(request, errors);
					return mapping.findForward(FWD_FAIL);
				}
				/*
				 * if ( loginInfo.getPasswordExpiredDayNo() <=
				 * Integer.parseInt(SystemConfiguration
				 * .getInstance().getLoginUserChangePasswordAllowDay()) ) {
				 * errors = new ActionMessages(); ActionError error = new
				 * ActionError("login.error.password.day",
				 * SystemConfiguration.getInstance
				 * ().getLoginUserChangePasswordAllowDay(), null);
				 * errors.add(ActionMessages.GLOBAL_MESSAGE, error);
				 * saveErrors(request, errors); return
				 * mapping.findForward(FWD_FAIL); }
				 */
				// validate user id exists in system_user table
				if (loginInfo.getSystemUserId() == 0) {
					errors = new ActionMessages();
					ActionError error = new ActionError("login.error.system.user.id", loginInfo.getLoginName(), null);
					errors.add(ActionMessages.GLOBAL_MESSAGE, error);
					saveErrors(request, errors);
					return mapping.findForward(FWD_FAIL);
				}

				// validate and update password
				loginInfo.setPassword(login.getNewPassword());

				java.util.Calendar rightNow = java.util.Calendar.getInstance();
				rightNow.add(java.util.Calendar.MONTH, Integer.parseInt(SystemConfiguration.getInstance()
						.getLoginUserChangePasswordExpiredMonth()));
				loginInfo.setPasswordExpiredDate(new java.sql.Date(rightNow.getTimeInMillis()));

				loginInfo.setSysUserId(String.valueOf(loginInfo.getSystemUserId())); //there is no loggedin user when you reset your password
				isSuccess = loginDAO.updatePassword(loginInfo);
				if (isSuccess) {
					tx.commit();
					// successfully changed password
					// force user to relogin with the new password
					errors = new ActionMessages();
					ActionError error = new ActionError("login.success.changePass.message", null, null);
					errors.add(ActionMessages.GLOBAL_MESSAGE, error);
					saveErrors(request, errors);
				} else {
					tx.rollback();
					errors = new ActionMessages();
					ActionError error = new ActionError("login.error.password.requirement", null, null);
					errors.add(ActionMessages.GLOBAL_MESSAGE, error);
					saveErrors(request, errors);
					forward = FWD_FAIL;
				}
			}

		} catch (LIMSRuntimeException lre) {
			// bugzilla 2154
			LogEvent.logError("LoginChangePasswordUpdateAction", "performAction()", lre.toString());
			tx.rollback();
			errors = new ActionMessages();
			ActionError error = new ActionError("login.error.message", null, null);
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);
			return mapping.findForward(FWD_FAIL);
		} finally {
			HibernateUtil.closeSession();
		}

		return mapping.findForward(forward);
	}

	protected String getPageTitleKey() {
		return null;
	}

	protected String getPageSubtitleKey() {
		return null;
	}
}