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
 *
 * Contributor(s): CIRG, University of Washington, Seattle WA.
 */
package us.mn.state.health.lims.systemuser.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Transaction;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.provider.validation.PasswordValidationFactory;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.login.dao.LoginDAO;
import us.mn.state.health.lims.login.daoimpl.LoginDAOImpl;
import us.mn.state.health.lims.login.valueholder.Login;
import us.mn.state.health.lims.systemuser.dao.SystemUserDAO;
import us.mn.state.health.lims.systemuser.daoimpl.SystemUserDAOImpl;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;
import us.mn.state.health.lims.userrole.dao.UserRoleDAO;
import us.mn.state.health.lims.userrole.daoimpl.UserRoleDAOImpl;
import us.mn.state.health.lims.userrole.valueholder.UserRole;

public class UnifiedSystemUserUpdateAction extends BaseAction {

	private static LoginDAO loginDAO = new LoginDAOImpl();
	private static final String RESERVED_ADMIN_NAME = "admin";
	private boolean passwordUpdated = false;
	private String userLoginName = "";

	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "false");
		request.setAttribute(NEXT_DISABLED, "false");

		String id = request.getParameter(ID);

		String forward = FWD_SUCCESS;

		BaseActionForm dynaForm = (BaseActionForm) form;

		String start = (String) request.getParameter("startingRecNo");
		String direction = (String) request.getParameter("direction");

		userLoginName = dynaForm.getString("userLoginName");
		if (userLoginName != null) {
			userLoginName = userLoginName.trim();
		} else {
			userLoginName = "";
		}

		forward = validateAndUpdateSystemUser(mapping, request, dynaForm);

		if (forward.equals(FWD_SUCCESS)) {
			return getForward(mapping.findForward(forward), id, start, direction);
		} else if (forward.equals(FWD_SUCCESS_INSERT)) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("forward", FWD_SUCCESS);
			return getForwardWithParameters(mapping.findForward(forward), params);
		} else {
			return mapping.findForward(forward);
		}
	}

	public String validateAndUpdateSystemUser(ActionMapping mapping, HttpServletRequest request, BaseActionForm dynaForm) {
		String forward = FWD_SUCCESS_INSERT;
		String loginUserId = dynaForm.getString("loginUserId");
		String systemUserId = dynaForm.getString("systemUserId");

		ActionMessages errors = new ActionMessages();

		boolean loginUserNew = GenericValidator.isBlankOrNull(loginUserId);
		boolean systemUserNew = GenericValidator.isBlankOrNull(systemUserId);

		passwordUpdated = passwordHasBeenUpdated(loginUserNew, dynaForm);
		validateUser(dynaForm, errors, loginUserNew, loginUserId);

		if (errors.size() > 0) {
			saveErrors(request, errors);
			request.setAttribute(Globals.ERROR_KEY, errors);
			return FWD_FAIL;
		}

		String loggedOnUserId = getSysUserId(request);

		Login loginUser = createLoginUser(dynaForm, loginUserId, loginUserNew, loggedOnUserId);
		SystemUser systemUser = createSystemUser(dynaForm, systemUserId, systemUserNew, loggedOnUserId);

		String[] selectedRoles = (String[]) dynaForm.get("selectedRoles");

		UserRoleDAO usrRoleDAO = new UserRoleDAOImpl();
		SystemUserDAO systemUserDAO = new SystemUserDAOImpl();

		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {

			if (loginUserNew) {
				loginDAO.insertData(loginUser);
			} else {
				loginDAO.updateData(loginUser, passwordUpdated);
			}

			if (systemUserNew) {
				systemUserDAO.insertData(systemUser);
			} else {
				systemUserDAO.updateData(systemUser);
			}

			List<String> currentUserRoles = usrRoleDAO.getRoleIdsForUser(systemUser.getId());
			List<UserRole> deletedUserRoles = new ArrayList<UserRole>();

			for (int i = 0; i < selectedRoles.length; i++) {
				if (!currentUserRoles.contains(selectedRoles[i])) {
					UserRole userRole = new UserRole();
					userRole.setSystemUserId(systemUser.getId());
					userRole.setRoleId(selectedRoles[i]);
					userRole.setSysUserId(loggedOnUserId);
					usrRoleDAO.insertData(userRole);
				} else {
					currentUserRoles.remove(selectedRoles[i]);
				}
			}

			for (String roleId : currentUserRoles) {
				UserRole userRole = new UserRole();
				userRole.setSystemUserId(systemUser.getId());
				userRole.setRoleId(roleId);
				userRole.setSysUserId(loggedOnUserId);
				deletedUserRoles.add(userRole);
			}

			if (deletedUserRoles.size() > 0) {
				usrRoleDAO.deleteData(deletedUserRoles);
			}
		} catch (LIMSRuntimeException lre) {
			tx.rollback();

			ActionError error = null;
			if (lre.getException() instanceof org.hibernate.StaleObjectStateException) {
				error = new ActionError("errors.OptimisticLockException", null, null);
			} else {
				error = new ActionError("errors.UpdateException", null, null);
			}

			persisteError(request, error);

			disableNavigationButtons(request);
			forward = FWD_FAIL;
		} finally {
			if (!tx.wasRolledBack()) {
				tx.commit();
			}
			HibernateUtil.closeSession();
		}

		selectedRoles = new String[0];

		return forward;
	}

	private boolean passwordHasBeenUpdated(boolean loginUserNew, BaseActionForm dynaForm) {
		if (loginUserNew) {
			return true;
		}

		String password = dynaForm.getString("userPassword1");

		return !StringUtil.containsOnly(password, UnifiedSystemUserAction.DEFAULT_PASSWORD_FILLER);
	}

	private void validateUser(BaseActionForm dynaForm, ActionMessages errors, boolean loginUserIsNew, String loginUserId) {
		boolean checkForDuplicateName = loginUserIsNew || userNameChanged(loginUserId, userLoginName);
		// check login name

		if (GenericValidator.isBlankOrNull(userLoginName)) {
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("errors.loginName.required"));
		}else if (checkForDuplicateName ) {
			Login login = loginDAO.getUserProfile(userLoginName);
			if (login != null) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("errors.loginName.duplicated", new StringBuilder(userLoginName)));
			}
		}

		// check first and last name
		if (GenericValidator.isBlankOrNull(dynaForm.getString("userFirstName"))
				|| GenericValidator.isBlankOrNull(dynaForm.getString("userLastName"))) {
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("errors.userName.required"));
		}

		if (passwordUpdated) {
			// check passwords match
			if (GenericValidator.isBlankOrNull(dynaForm.getString("userPassword1"))
					|| !dynaForm.getString("userPassword1").equals(dynaForm.getString("userPassword2"))) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("errors.password.match"));
			} else if (!passwordValid(dynaForm.getString("userPassword1"))) { // validity
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("login.error.password.requirement"));
			}
		}

		// check expiration date
		if (!GenericValidator.isDate(dynaForm.getString("expirationDate"), SystemConfiguration.getInstance().getDateLocale())) {
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("errors.date", new StringBuilder(dynaForm.getString("expirationDate"))));
		}

		// check timeout
		if (!timeoutValidAndInRange(dynaForm.getString("timeout"))) {
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("errors.timeout.range"));
		}
	}

	private boolean userNameChanged(String loginUserId, String newName) {
		if( GenericValidator.isBlankOrNull(loginUserId)){
			return false;
		}
		
		Login login = new Login();
		login.setId(loginUserId);
		loginDAO.getData(login);
		
		return !newName.equals(login.getLoginName());
	}

	private boolean timeoutValidAndInRange(String timeout) {
		try {
			int timeInMin = Integer.parseInt(timeout);
			return timeInMin > 0 && timeInMin < 601;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	private boolean passwordValid(String password) {
		return PasswordValidationFactory.getPasswordValidator().passwordValid(password);
	}

	private Login createLoginUser(BaseActionForm dynaForm, String loginUserId, boolean loginUserNew, String loggedOnUserId) {

		Login login = new Login();

		if (!loginUserNew) {
			login.setId(dynaForm.getString("loginUserId"));
			loginDAO.getData(login);
		}
		login.setAccountDisabled(dynaForm.getString("accountDisabled"));
		login.setAccountLocked(dynaForm.getString("accountLocked"));
		login.setLoginName(userLoginName);
		if (passwordUpdated) {
			login.setPassword(dynaForm.getString("userPassword1"));
		}
		login.setPasswordExpiredDateForDisplay(dynaForm.getString("expirationDate"));
		if (RESERVED_ADMIN_NAME.equals(userLoginName)) {
			login.setIsAdmin("Y");
		} else {
			login.setIsAdmin("N");
		}
		login.setUserTimeOut(dynaForm.getString("timeout"));
		login.setSysUserId(loggedOnUserId);

		return login;
	}

	private SystemUser createSystemUser(BaseActionForm dynaForm, String systemUserId, boolean systemUserNew, String loggedOnUserId) {

		SystemUser systemUser = new SystemUser();

		if (!systemUserNew) {
			systemUser.setId(systemUserId);
		}

		systemUser.setFirstName(dynaForm.getString("userFirstName"));
		systemUser.setLastName(dynaForm.getString("userLastName"));
		systemUser.setLoginName(userLoginName);
		systemUser.setIsActive(dynaForm.getString("accountActive"));
		systemUser.setIsEmployee("Y");
		systemUser.setExternalId("1");
		String initial = systemUser.getFirstName().substring(0, 1) + systemUser.getLastName().substring(0, 1);
		systemUser.setInitials(initial);
		systemUser.setSysUserId(loggedOnUserId);
		systemUser.setLastupdated((Timestamp) dynaForm.get("systemUserLastupdated"));

		return systemUser;
	}

	private void persisteError(HttpServletRequest request, ActionError error) {
		ActionMessages errors;
		errors = new ActionMessages();

		errors.add(ActionMessages.GLOBAL_MESSAGE, error);
		saveErrors(request, errors);
		request.setAttribute(Globals.ERROR_KEY, errors);
	}

	private void disableNavigationButtons(HttpServletRequest request) {
		request.setAttribute(PREVIOUS_DISABLED, TRUE);
		request.setAttribute(NEXT_DISABLED, TRUE);
	}

	protected String getPageTitleKey() {
		return "systemuserrole.browse.title";
	}

	protected String getPageSubtitleKey() {
		return "systemuserrole.browse.title";
	}
}