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
package us.mn.state.health.lims.login.action;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.login.dao.LoginDAO;
import us.mn.state.health.lims.login.dao.UserModuleDAO;
import us.mn.state.health.lims.login.daoimpl.LoginDAOImpl;
import us.mn.state.health.lims.login.daoimpl.UserModuleDAOImpl;
import us.mn.state.health.lims.login.valueholder.Login;
import us.mn.state.health.lims.login.valueholder.UserSessionData;
import us.mn.state.health.lims.systemuser.dao.SystemUserDAO;
import us.mn.state.health.lims.systemuser.daoimpl.SystemUserDAOImpl;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;
import us.mn.state.health.lims.systemusermodule.dao.PermissionAgentModuleDAO;
import us.mn.state.health.lims.systemusermodule.daoimpl.RoleModuleDAOImpl;
import us.mn.state.health.lims.systemusermodule.valueholder.RoleModule;
import us.mn.state.health.lims.userrole.dao.UserRoleDAO;
import us.mn.state.health.lims.userrole.daoimpl.UserRoleDAOImpl;

/**
 *  @author     Hung Nguyen (Hung.Nguyen@health.state.mn.us)
 *  bugzilla 2286, added password expired day reminder after user login
 *                 added force user to change password after number of days
 *                 added lock/lock user account after number of failed attempts
 */
public class LoginValidateAction extends LoginBaseAction {
	
	static int WARNING_THRESHOLD = 3;

	protected ActionForward performAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String forward = FWD_SUCCESS;
		BaseActionForm dynaForm = (BaseActionForm) form;

		// server-side validation (validation.xml)
		ActionMessages errors = dynaForm.validate(mapping, request);		
		if (errors != null && errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward(FWD_FAIL);
		}		
		
		Login login = new Login();
		PropertyUtils.copyProperties(login, dynaForm);
		login.setLoginName(login.getLoginName().trim());
		
		org.hibernate.Transaction tx = HibernateUtil.getSession().beginTransaction();			
		LoginDAO loginDAO = new LoginDAOImpl();
		Login userInfo = loginDAO.getUserProfile(login.getLoginName());
		Login loginInfo = loginDAO.getValidateLogin(login);
		HibernateUtil.closeSession();
		
		//if invalid loginName entered
		if ( userInfo == null ) {
			HibernateUtil.closeSession();
			errors = new ActionMessages();
			ActionError error = new ActionError("login.error.message", null, null);
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);
			return mapping.findForward(FWD_FAIL);					
		} else {
			
			//if valid loginName entered then continue to check
			if ( userInfo.getAccountDisabled().equalsIgnoreCase(YES) ) {
				errors = new ActionMessages();
				ActionError error = new ActionError("login.error.account.disable", null, null);
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
				saveErrors(request, errors);
				return mapping.findForward(FWD_FAIL);				
			}	
			if ( userInfo.getAccountLocked().equalsIgnoreCase(YES) ) {
				errors = new ActionMessages();
				//ActionError error = new ActionError("login.error.account.lock", null, null);
				//errors.add(ActionMessages.GLOBAL_MESSAGE, error);
				//saveErrors(request, errors);
				//return mapping.findForward(FWD_FAIL); 				
				if ( request.getSession().getAttribute(ACCOUNT_LOCK_TIME) != null ) {
					lockUnlockUserAccount(errors,request,userInfo);
				} else {
					ActionError error = new ActionError("login.error.account.lock", null, null);
					errors.add(ActionMessages.GLOBAL_MESSAGE, error);
					saveErrors(request, errors);	
				}
				return mapping.findForward(FWD_FAIL); 
			}		
			if ( userInfo.getPasswordExpiredDayNo() <= 0 ) {
				errors = new ActionMessages();
				ActionError error = new ActionError("login.error.password.expired", null, null);
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
				saveErrors(request, errors);
				return mapping.findForward(FWD_FAIL);			
			}	
		
			//verify loginName and password
			loginInfo = loginDAO.getValidateLogin(login);	
			if ( loginInfo == null ) {				
				int loginUserFailAttemptCount = 0;
				int loginUserFailAttemptCountDefault = Integer.parseInt(SystemConfiguration.getInstance().getLoginUserFailAttemptCount());
				if (request.getSession().getAttribute(LOGIN_FAILED_CNT) != null) {
					loginUserFailAttemptCount = Integer.parseInt((String)request.getSession().getAttribute(LOGIN_FAILED_CNT));
				}	
				loginUserFailAttemptCount++;
				request.getSession().setAttribute(LOGIN_FAILED_CNT, String.valueOf(loginUserFailAttemptCount));

				//lock account after number of failed attempts
				if ( loginUserFailAttemptCount == loginUserFailAttemptCountDefault ) {
					tx = HibernateUtil.getSession().beginTransaction();				
					login = loginDAO.getUserProfile(login.getLoginName());
					login.setAccountLocked(YES);
					loginDAO.lockAccount(login);
					tx.commit();
					HibernateUtil.closeSession();
					errors = new ActionMessages();	
					
					ActionError error = new ActionError("login.error.account.lock", null); 			
					errors.add(ActionMessages.GLOBAL_MESSAGE, error);
					saveErrors(request, errors);
					
					lockUnlockUserAccount(errors,request,userInfo);					
					request.getSession().removeAttribute(LOGIN_FAILED_CNT);
					return mapping.findForward(FWD_FAIL);
				//notify user of lockout when approaching max attempts
				} else if ( loginUserFailAttemptCount >= WARNING_THRESHOLD ) {
					errors = new ActionMessages();		
					ActionError error = new ActionError("login.error.attempt.message",
														String.valueOf(loginUserFailAttemptCount),
														String.valueOf(loginUserFailAttemptCountDefault), 
														SystemConfiguration.getInstance().getLoginUserAccountUnlockMinute(), null);			
					errors.add(ActionMessages.GLOBAL_MESSAGE, error);
					saveErrors(request, errors);
					return mapping.findForward(FWD_FAIL);
				//give same invalid message as when account not found
				} else {
					errors = new ActionMessages();
					ActionError error = new ActionError("login.error.message", null, null);
					errors.add(ActionMessages.GLOBAL_MESSAGE, error);
					saveErrors(request, errors);
					return mapping.findForward(FWD_FAIL);
				}
				
				
				
				
			} else {
				if ( (loginInfo.getPasswordExpiredDayNo() <= Integer.parseInt(SystemConfiguration.getInstance().getLoginUserPasswordExpiredReminderDay())) 
					&&
					 (loginInfo.getPasswordExpiredDayNo() > Integer.parseInt(SystemConfiguration.getInstance().getLoginUserChangePasswordAllowDay())) ) {
					errors = new ActionMessages();
					ActionError error = new ActionError("login.password.expired.reminder", loginInfo.getPasswordExpiredDayNo(), null);
					errors.add(ActionMessages.GLOBAL_MESSAGE, error);
					saveErrors(request, errors);					
				} else if ( (loginInfo.getPasswordExpiredDayNo() <= Integer.parseInt(SystemConfiguration.getInstance().getLoginUserChangePasswordAllowDay()))
					    && (loginInfo.getPasswordExpiredDayNo() > 0) ) {
					errors = new ActionMessages();
					ActionError error = new ActionError("login.password.expired.force.notice", loginInfo.getPasswordExpiredDayNo(), loginInfo.getPasswordExpiredDayNo(), null);
					errors.add(ActionMessages.GLOBAL_MESSAGE, error);
					saveErrors(request, errors);
					return mapping.findForward(FWD_CHANGE_PASS);
				}			
				if ( loginInfo.getSystemUserId() == 0 ) {
					errors = new ActionMessages();
					ActionError error = new ActionError("login.error.system.user.id", loginInfo.getLoginName(), null);
					errors.add(ActionMessages.GLOBAL_MESSAGE, error);
					saveErrors(request, errors);
					return mapping.findForward(FWD_FAIL);					
				} else {
					
					//request a new session (session fixation protection)
					request.getSession(false).invalidate();
					request.getSession();
					
					SystemUserDAO systemUserDAO = new SystemUserDAOImpl();
					SystemUser su = new SystemUser();
					su.setId(String.valueOf(loginInfo.getSystemUserId()));
					systemUserDAO.getData(su);
				
					//setup the user timeout in seconds
					int timeOut = Integer.parseInt((String)loginInfo.getUserTimeOut());
					request.getSession().setMaxInactiveInterval(timeOut*60);
				
					UserSessionData usd = new UserSessionData();
					usd.setSytemUserId(loginInfo.getSystemUserId());
					usd.setLoginName(loginInfo.getLoginName());
					usd.setElisUserName(su.getNameForDisplay());
					usd.setUserTimeOut(timeOut*60);
					request.getSession().setAttribute(USER_SESSION_DATA,usd);
					
					boolean showAdminMenu = loginInfo.getIsAdmin().equalsIgnoreCase(YES);

					if( SystemConfiguration.getInstance().getPermissionAgent().equals("ROLE")){
						HashSet<String> permittedPages = getPermittedForms(usd.getSystemUserId());
						request.getSession().setAttribute(IActionConstants.PERMITTED_ACTIONS_MAP, permittedPages);
						showAdminMenu |= permittedPages.contains("MasterList");
					}
					
				}
			
				//cleanup session
				if (request.getSession().getAttribute(LOGIN_FAILED_CNT) != null)
					request.getSession().removeAttribute(LOGIN_FAILED_CNT);
				if (request.getSession().getAttribute(ACCOUNT_LOCK_TIME) != null )
					request.getSession().removeAttribute(ACCOUNT_LOCK_TIME);
				
				if ( loginInfo.getIsAdmin().equalsIgnoreCase(YES) )
					//bugzilla 2154
					LogEvent.logInfo("LoginValidateAction","performAction()","======> USER TYPE: ADMIN");
				else {
					//bugzilla 2154
					LogEvent.logInfo("LoginValidateAction","performAction()","======> USER TYPE: NON-ADMIN");
					//bugzilla 2160
					UserModuleDAO userModuleDAO = new UserModuleDAOImpl();
					if ( !userModuleDAO.isUserModuleFound(request) ) {
						errors = new ActionMessages();
						ActionError error = new ActionError("login.error.no.module", null, null);
						errors.add(ActionMessages.GLOBAL_MESSAGE, error);
						saveErrors(request, errors);
						return mapping.findForward(FWD_FAIL);
					}
				}
			}	
		}
		
		return mapping.findForward(forward);
	}
	
	@SuppressWarnings("unchecked")
	private HashSet<String> getPermittedForms(int systemUserId) {
		HashSet<String> permittedPages = new HashSet<String>();
		
		UserRoleDAO userRoleDAO = new UserRoleDAOImpl();
		
		List<String> roleIds = userRoleDAO.getRoleIdsForUser( Integer.toString(systemUserId));
		
		PermissionAgentModuleDAO roleModuleDAO = new RoleModuleDAOImpl();

		for( String roleId : roleIds){
			List<RoleModule> roleModules = roleModuleDAO.getAllPermissionModulesByAgentId(Integer.parseInt(roleId));
			
			for( RoleModule roleModule : roleModules){
				permittedPages.add( roleModule.getSystemModule().getSystemModuleName());
			}
		}
		
		return permittedPages;
	}

	/**
	 * Account is locked/unlock after the user entered wrong password (3 times)
	 * @param errors the ActionMessages
	 * @param request the HttpServletRequest
	 * @param login the user login object
	 */
	private void lockUnlockUserAccount(ActionMessages errors, HttpServletRequest request, Login login) {		
		java.util.Calendar loginTime = java.util.Calendar.getInstance();                       
		
		if ( request.getSession().getAttribute(ACCOUNT_LOCK_TIME) != null ) {
			loginTime = (java.util.Calendar)request.getSession().getAttribute(ACCOUNT_LOCK_TIME);
		} else {
			int lockMinute = Integer.parseInt(SystemConfiguration.getInstance().getLoginUserAccountUnlockMinute());
			loginTime.add(java.util.Calendar.MINUTE, +lockMinute);
			request.getSession().setAttribute(ACCOUNT_LOCK_TIME, loginTime);
		} 
		
		java.util.Calendar now = java.util.Calendar.getInstance();
		int diff = Integer.parseInt(String.valueOf((loginTime.getTimeInMillis()-now.getTimeInMillis())/1000));
		
		if ( diff > 0 ) {
	        int seconds = (int)(diff % 60);
	        int minutes = (int)((diff/60) % 60);
	        int hours = (int)((diff/3600) % 24);
	        String secondsStr = (seconds<10 ? "0" : "")+ seconds;
	        String minutesStr = (minutes<10 ? "0" : "")+ minutes;
	        String hoursStr = (hours<10 ? "0" : "")+ hours;		
			String unlockTime = hoursStr + ":" + minutesStr + ":" + secondsStr;
			ActionError error = new ActionError("login.user.account.lock.message", unlockTime, null);
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);
		} else {			
			request.getSession().removeAttribute(ACCOUNT_LOCK_TIME);
			org.hibernate.Transaction tx = HibernateUtil.getSession().beginTransaction();			
			LoginDAO loginDAO = new LoginDAOImpl();
			loginDAO.unlockAccount(login);
			login.setAccountLocked(NO);
			tx.commit();
			HibernateUtil.closeSession();
			ActionError error = new ActionError("login.user.account.unlock.message", null);
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);	
		}						
	}
	
	protected String getPageTitleKey() {
		return null;
	}

	protected String getPageSubtitleKey() {
		return null;
	}
}