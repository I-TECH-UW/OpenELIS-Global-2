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
package us.mn.state.health.lims.login.daoimpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.security.PageIdentityUtil;
import us.mn.state.health.lims.login.dao.LoginDAO;
import us.mn.state.health.lims.login.dao.UserModuleService;
import us.mn.state.health.lims.login.valueholder.Login;
import us.mn.state.health.lims.login.valueholder.UserSessionData;
import us.mn.state.health.lims.systemusermodule.dao.PermissionModuleDAO;
import us.mn.state.health.lims.systemusermodule.valueholder.SystemUserModule;

/**
 * @author Hung Nguyen (Hung.Nguyen@health.state.mn.us)
 */
/*
 * N.B. This class has nothing to do with database access
 */
//TODO move to service layer
@Service
public class UserModuleServiceImpl implements UserModuleService, IActionConstants {

	@Autowired
	@Qualifier(value = "RoleModuleDAO")
	PermissionModuleDAO roleModuleDAO;
	@Autowired
	@Qualifier(value = "SystemUserModuleDAO")
	PermissionModuleDAO systemUserModuleDAO;
	@Autowired
	LoginDAO loginDAO;

	@Override
	@Transactional(readOnly = true)
	public boolean isSessionExpired(HttpServletRequest request) throws LIMSRuntimeException {
		if (request.getSession().getAttribute(USER_SESSION_DATA) == null) {
			return true;
		}

		return false;
	}

	/**
	 * Check if the user has any module assign to him/her
	 *
	 * @param request is HttpServletRequest
	 * @return true if found, false otherwise
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean isUserModuleFound(HttpServletRequest request) throws LIMSRuntimeException {
		boolean isFound = false;
		try {
			UserSessionData usd = (UserSessionData) request.getSession().getAttribute(USER_SESSION_DATA);
			isFound = roleModuleDAO.doesUserHaveAnyModules(usd.getSystemUserId());
		} catch (LIMSRuntimeException lre) {
			// bugzilla 2154
			LogEvent.logError("UserModuleServiceImpl", "isUserModuleFound()", lre.toString());
			throw new LIMSRuntimeException("Error in UserModuleServiceImpl isUserModuleFound()", lre);
		}
		return isFound;
	}

	/**
	 * TODO: Setup the user accessible buttons in the user object Preparing and
	 * setting the user module select/add/update/delete disable/enable buttons
	 *
	 * @param request is HttpServletRequest
	 * @return true if success, false otherwise
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean isVerifyUserModule(HttpServletRequest request) throws LIMSRuntimeException {
		boolean isFound = PageIdentityUtil.isMainPage(request);

		if (!isFound) {
			try {
				UserSessionData usd = (UserSessionData) request.getSession().getAttribute(USER_SESSION_DATA);
				List list = systemUserModuleDAO.getAllPermissionModulesByAgentId(usd.getSystemUserId());

				for (int i = 0; i < list.size(); i++) {
					SystemUserModule systemUserModule = (SystemUserModule) list.get(i);
					String userAssignedModule = systemUserModule.getSystemModule().getSystemModuleName();
					String actionName = getActionName(request, userAssignedModule);

					// we want to check only part of action class name
					// because each module/action can have up to 5 or 6 names
					if (actionName.equals(userAssignedModule) || actionName.startsWith(userAssignedModule + "Menu")) {
						isFound = true;
						setupUserButtons(request, systemUserModule, actionName);
						// bugzilla 2154
						LogEvent.logInfo("UserModuleServiceImpl", "isVerifyUserModule()",
								"======> ALLOWED ACCESS TO THIS MODULE");
						LogEvent.logDebug("UserModuleServiceImpl", "isVerifyUserModule()",
								"======> MODULE ID   : " + systemUserModule.getSystemModule().getId());
						LogEvent.logDebug("UserModuleServiceImpl", "isVerifyUserModule()",
								"======> MODULE NAME : " + systemUserModule.getSystemModule().getSystemModuleName());

						LogEvent.logDebug("UserModuleServiceImpl", "isVerifyUserModule()",
								"======> ALLOW_VIEW  : " + systemUserModule.getHasSelect());
						LogEvent.logDebug("UserModuleServiceImpl", "isVerifyUserModule()",
								"======> ALLOW_ADD   : " + systemUserModule.getHasAdd());
						LogEvent.logDebug("UserModuleServiceImpl", "isVerifyUserModule()",
								"======> ALLOW_UPDATE: " + systemUserModule.getHasUpdate());
						LogEvent.logDebug("UserModuleServiceImpl", "isVerifyUserModule()",
								"======> ALLOW_DELETE: " + systemUserModule.getHasDelete());
						LogEvent.logDebug("UserModuleServiceImpl", "isVerifyUserModule()",
								"======> SYSTEM MODULE DEFAULT VALUE");
						LogEvent.logDebug("UserModuleServiceImpl", "isVerifyUserModule()",
								"======> HAS_VIEW  : " + systemUserModule.getSystemModule().getHasSelectFlag());
						LogEvent.logDebug("UserModuleServiceImpl", "isVerifyUserModule()",
								"======> HAS_ADD   : " + systemUserModule.getSystemModule().getHasAddFlag());
						LogEvent.logDebug("UserModuleServiceImpl", "isVerifyUserModule()",
								"======> HAS_UPDATE: " + systemUserModule.getSystemModule().getHasUpdateFlag());
						LogEvent.logDebug("UserModuleServiceImpl", "isVerifyUserModule()",
								"======> HAS_DELETE: " + systemUserModule.getSystemModule().getHasDeleteFlag());

						break;
					}
				}
			} catch (LIMSRuntimeException lre) {
				// bugzilla 2154
				LogEvent.logError("UserModuleServiceImpl", "isVerifyUserModule()", lre.toString());
				throw new LIMSRuntimeException("Error in UserModuleServiceImpl isVerifyUserModule()", lre);
			}
		}
		return isFound;
	}

	/**
	 * Get the user login information bases on the user login name
	 *
	 * @param request is HttpServletRequest
	 * @return user information
	 */
	@Transactional(readOnly = true)
	private Login getUserLogin(HttpServletRequest request) throws LIMSRuntimeException {
		Login login = null;
		try {
			UserSessionData usd = (UserSessionData) request.getSession().getAttribute(USER_SESSION_DATA);
			login = loginDAO.getUserProfile(usd.getLoginName());
		} catch (LIMSRuntimeException lre) {
			// bugzilla 2154
			LogEvent.logError("UserModuleServiceImpl", "getUserLogin()", lre.toString());
			throw new LIMSRuntimeException("Error in UserModuleServiceImpl getUserLogin()", lre);
		}
		return login;
	}

	/**
	 * Check if the user account in locked
	 *
	 * @param request is HttpServletRequest
	 * @return true if locked, false otherwise
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean isAccountLocked(HttpServletRequest request) throws LIMSRuntimeException {
		try {
			Login login = getUserLogin(request);
			if (login.getAccountLocked().equalsIgnoreCase(YES)) {
				return true;
			}
		} catch (LIMSRuntimeException lre) {
			// bugzilla 2154
			LogEvent.logError("UserModuleServiceImpl", "isAccountLocked()", lre.toString());
			throw new LIMSRuntimeException("Error in UserModuleServiceImpl isAccountLocked()", lre);
		}
		return false;
	}

	/**
	 * Check if the user account in disabled
	 *
	 * @param request is HttpServletRequest
	 * @return true if disabled, false otherwise
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean isAccountDisabled(HttpServletRequest request) throws LIMSRuntimeException {
		try {
			Login login = getUserLogin(request);
			if (login.getAccountDisabled().equalsIgnoreCase(YES)) {
				return true;
			}
		} catch (LIMSRuntimeException lre) {
			// bugzilla 2154
			LogEvent.logError("UserModuleServiceImpl", "isAccountDisabled()", lre.toString());
			throw new LIMSRuntimeException("Error in UserModuleServiceImpl isAccountDisabled()", lre);
		}
		return false;
	}

	/**
	 * Check if the user password is expired
	 *
	 * @param request is HttpServletRequest
	 * @return true if expired, false otherwise
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean isPasswordExpired(HttpServletRequest request) throws LIMSRuntimeException {
		try {
			Login login = getUserLogin(request);
			if (login.getPasswordExpiredDayNo() <= 0) {
				return true;
			}
		} catch (LIMSRuntimeException lre) {
			// bugzilla 2154
			LogEvent.logError("UserModuleServiceImpl", "isPasswordExpired()", lre.toString());
			throw new LIMSRuntimeException("Error in UserModuleServiceImpl isPasswordExpired()", lre);
		}
		return false;
	}

	/**
	 * Check if the user is admin role
	 *
	 * @param request is HttpServletRequest
	 * @return true if admin, false otherwise
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean isUserAdmin(HttpServletRequest request) throws LIMSRuntimeException {
		try {
			Login login = getUserLogin(request);
			if (login.getIsAdmin().equalsIgnoreCase(YES)) {
				return true;
			}
		} catch (LIMSRuntimeException lre) {
			// bugzilla 2154
			LogEvent.logError("UserModuleServiceImpl", "isUserAdmin()", lre.toString());
			throw new LIMSRuntimeException("Error in UserModuleServiceImpl isUserAdmin()", lre);
		}
		return false;
	}

	/**
	 * Setup the user session time bases on the information in
	 * <table>
	 * LOGIN_USER
	 * </table>
	 *
	 * @param request is HttpServletRequest
	 */
	@Override
	public void setupUserSessionTimeOut(HttpServletRequest request) throws LIMSRuntimeException {
		try {
			Login login = getUserLogin(request);
			int timeOut = Integer.parseInt(login.getUserTimeOut());

			request.getSession().setMaxInactiveInterval(timeOut * 60);
			UserSessionData usd = (UserSessionData) request.getSession().getAttribute(USER_SESSION_DATA);
			usd.setUserTimeOut(timeOut * 60);
			request.getSession().setAttribute(USER_SESSION_DATA, usd);
		} catch (LIMSRuntimeException lre) {
			// bugzilla 2154
			LogEvent.logError("UserModuleServiceImpl", "setupUserSessionTimeOut()", lre.toString());
			throw new LIMSRuntimeException("Error in UserModuleServiceImpl setupUserSessionTimeOut()", lre);
		}
	}

	/**
	 * TOD): will move hardcoded action names to the property file
	 *
	 * @param request
	 * @param userAssignedModule
	 * @return actionName the name of the action form
	 */
	private String getActionName(HttpServletRequest request, String userAssignedModule) throws LIMSRuntimeException {

		String actionName = null;
		try {
			actionName = (String) request.getAttribute(ACTION_KEY);

			// bugzilla 2154
			LogEvent.logDebug("UserModuleServiceImpl", "getActionName()",
					"======> USER ASSIGNED MODULE: " + userAssignedModule);
			LogEvent.logDebug("UserModuleServiceImpl", "getActionName()",
					"======> ACTION MODULE NAME  : " + actionName);

			// N.B. The effect of this first if is that the first module on the list for the
			// user becomes the
			// actionName. This does not seem correct.
			if ((actionName == null) || (actionName.length() == 0)) {
				actionName = userAssignedModule;
			} else if (actionName.equals("QuickEntryAddTestPopup")) {
				actionName = "QuickEntry";
			} else if (actionName.equals("TestManagementAddTestPopup")) {
				actionName = "TestManagement";
				// bugzilla 1844: removing HumanSampleOneAddTestPopup
			} else if (actionName.equals("TestAnalyteTestResultAddDictionaryRGPopup")
					|| actionName.equals("TestAnalyteTestResultAddNonDictionaryRGPopup")
					|| actionName.equals("TestAnalyteTestResultAddRGPopup")
					|| actionName.equals("TestAnalyteTestResultAssignRGPopup")
					|| actionName.equals("TestAnalyteTestResultEditDictionaryRGPopup")
					|| actionName.equals("TestAnalyteTestResultEditDictionaryRGPopup")
					|| actionName.equals("TestAnalyteTestResultEditNonDictionaryRGPopup")) {
				actionName = "TestAnalyteTestResult";
			} else if (actionName.equals("QaEventsEntryAddQaEventsToTestsPopup")
					|| actionName.equals("QaEventsEntryAddActionsToQaEventsPopup")) {
				actionName = "QaEventsEntry";
			}
			// bugzilla 2204
			else if (actionName.equals("NotesPopup")) {
				if (userAssignedModule.equals("QaEventsEntry")) {
					actionName = "QaEventsEntry";
				}
				if (userAssignedModule.equals("ResultsEntry")) {
					actionName = "ResultsEntry";
				}
			}
		} catch (LIMSRuntimeException lre) {
			// bugzilla 2154
			LogEvent.logError("UserModuleServiceImpl", "getActionName()", lre.toString());
			throw new LIMSRuntimeException("Error in UserModuleServiceImpl getActionName()", lre);
		}
		return actionName;
	}

	/**
	 * TODO: Setup the user accessible buttons in the user object Enabled the
	 * buttons for user type admin
	 *
	 * @param request
	 *
	 *                N.B. It is not clear why business rules are in a DAO object.
	 */
	@Override
	public void enabledAdminButtons(HttpServletRequest request) throws LIMSRuntimeException {
		String active = "true";
		if (request.getAttribute(us.mn.state.health.lims.common.action.IActionConstants.DEACTIVATE_DISABLED) != null) {
			active = (String) request
					.getAttribute(us.mn.state.health.lims.common.action.IActionConstants.DEACTIVATE_DISABLED);
		}

		try {
			request.setAttribute(VIEW_DISABLED, FALSE); // enabled view
			request.setAttribute(ADD_DISABLED, FALSE); // enabled add
			String actionName = (String) request.getAttribute(ACTION_KEY);
			if (actionName != null) {
				if (!actionName.equals("HumanSampleTwo")) {
					request.setAttribute(ALLOW_EDITS_KEY, FALSE); // enabled edit
				}

				request.setAttribute(SAVE_DISABLED, FALSE); // enabled save

				// bugzilla 2214
				if (active.equals("false")) {
					request.setAttribute(DEACTIVATE_DISABLED, FALSE); // enabled delete
				} else {
					request.setAttribute(DEACTIVATE_DISABLED, TRUE); // disabled delete
				}
			}
		} catch (LIMSRuntimeException lre) {
			// bugzilla 2154
			LogEvent.logError("UserModuleServiceImpl", "enabledAdminButtons()", lre.toString());
			throw new LIMSRuntimeException("Error in UserModuleServiceImpl enabledAdminButtons()", lre);
		}
	}

	/**
	 * Setup the user buttons
	 *
	 * @param request
	 * @param systemUserModule
	 * @param actionName
	 * @throws LIMSRuntimeException
	 */
	private void setupUserButtons(HttpServletRequest request, SystemUserModule systemUserModule, String actionName)
			throws LIMSRuntimeException {

		// system module default setting (SELECT)
		if (systemUserModule.getSystemModule().getHasSelectFlag().equalsIgnoreCase(YES)) {
			// user module default setting
			if (systemUserModule.getHasSelect().equalsIgnoreCase(YES)) {
				request.setAttribute(VIEW_DISABLED, FALSE);
			} else {
				request.setAttribute(VIEW_DISABLED, TRUE);
			}
		} else {
			request.setAttribute(VIEW_DISABLED, TRUE);
		}

		// system module default setting (ADD)
		if (systemUserModule.getSystemModule().getHasAddFlag().equalsIgnoreCase(YES)) {
			// user module default setting
			if (systemUserModule.getHasAdd().equalsIgnoreCase(YES)) {
				request.setAttribute(ADD_DISABLED, FALSE);
			} else {
				request.setAttribute(ADD_DISABLED, TRUE);
			}
		} else {
			request.setAttribute(ADD_DISABLED, TRUE);
		}

		// system module default setting (UPDATE)
		if (systemUserModule.getSystemModule().getHasUpdateFlag().equalsIgnoreCase(YES)) {
			// user module default setting
			if (systemUserModule.getHasUpdate().equalsIgnoreCase(YES)) {
				if (!actionName.equals("HumanSampleTwo")) {
					request.setAttribute(ALLOW_EDITS_KEY, FALSE); // FALSE = allows??
				}
				request.setAttribute(SAVE_DISABLED, FALSE);
			} else {
				if (!actionName.equals("HumanSampleTwo")) {
					request.setAttribute(ALLOW_EDITS_KEY, TRUE);
				}
				request.setAttribute(SAVE_DISABLED, TRUE);
			}
		} else {
			request.setAttribute(SAVE_DISABLED, TRUE);
		}

		// bugzilla 2214
		String active = "true";
		if (request.getAttribute(us.mn.state.health.lims.common.action.IActionConstants.DEACTIVATE_DISABLED) != null) {
			active = (String) request
					.getAttribute(us.mn.state.health.lims.common.action.IActionConstants.DEACTIVATE_DISABLED);
		}

		if (active.equals("false")) {
			// system module default setting (DELETE)
			if (systemUserModule.getSystemModule().getHasDeleteFlag().equalsIgnoreCase(YES)) {
				// user module default setting
				if (systemUserModule.getHasDelete().equalsIgnoreCase(YES)) {
					request.setAttribute(DEACTIVATE_DISABLED, FALSE);
				} else {
					request.setAttribute(DEACTIVATE_DISABLED, TRUE);
				}
			} else {
				request.setAttribute(DEACTIVATE_DISABLED, TRUE);
			}
		} else {
			request.setAttribute(DEACTIVATE_DISABLED, TRUE);
		}
	}
}