package spring.mine.login.controller;

import java.lang.String;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.generated.forms.LoginValidateForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.login.form.LoginForm;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.SystemConfiguration;
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

@Controller
public class ValidateLoginController extends BaseController {

	static int WARNING_THRESHOLD = 3;

	@RequestMapping(value = "/ValidateLogin", method = RequestMethod.POST)
	public ModelAndView validateLogin(@Valid @ModelAttribute("form") LoginForm form, BindingResult result,
			HttpServletRequest request) {
		String forward = FWD_SUCCESS;

		Login login = new Login();
		login.setLoginName(form.getLoginName().trim());
		login.setPassword(form.getPassword());

		org.hibernate.Transaction tx = HibernateUtil.getSession().beginTransaction();
		LoginDAO loginDAO = new LoginDAOImpl();
		Login userInfo = loginDAO.getUserProfile(login.getLoginName());
		Login loginInfo = loginDAO.getValidateLogin(login);
		HibernateUtil.closeSession();

		// if invalid loginName entered
		if (userInfo == null) {
			result.reject("login.error.message", "login.error.message");
		} else {
			// if valid loginName entered then continue to check
			if (userInfo.getAccountDisabled().equalsIgnoreCase(IActionConstants.YES)) {
				result.reject("login.error.account.disable", "login.error.account.disable");
			} else if (userInfo.getAccountLocked().equalsIgnoreCase(IActionConstants.YES)) {
				if (request.getSession().getAttribute(IActionConstants.ACCOUNT_LOCK_TIME) != null) {
					lockUnlockUserAccount(result, request, userInfo);
				} else {
					result.reject("login.error.account.lock", "login.error.account.lock");
				}
			} else if (userInfo.getPasswordExpiredDayNo() <= 0) {
				result.reject("login.error.password.expired", "login.error.password.expired");
			} else if (loginInfo == null) {
				int loginUserFailAttemptCount = 0;
				int loginUserFailAttemptCountDefault = Integer
						.parseInt(SystemConfiguration.getInstance().getLoginUserFailAttemptCount());
				if (request.getSession().getAttribute(LOGIN_FAILED_CNT) != null) {
					loginUserFailAttemptCount = Integer
							.parseInt((String) request.getSession().getAttribute(LOGIN_FAILED_CNT));
				}
				loginUserFailAttemptCount++;
				request.getSession().setAttribute(LOGIN_FAILED_CNT, String.valueOf(loginUserFailAttemptCount));
				// lock account after number of failed attempts
				if (loginUserFailAttemptCount == loginUserFailAttemptCountDefault) {
					tx = HibernateUtil.getSession().beginTransaction();
					login = loginDAO.getUserProfile(login.getLoginName());
					login.setAccountLocked(YES);
					loginDAO.lockAccount(login);
					tx.commit();
					HibernateUtil.closeSession();

					result.reject("login.error.account.lock", "login.error.account.lock");

					lockUnlockUserAccount(result, request, userInfo);
					request.getSession().removeAttribute(LOGIN_FAILED_CNT);
				} else if (loginUserFailAttemptCount >= WARNING_THRESHOLD) {
					Object[] errorParameters = new Object[] { String.valueOf(loginUserFailAttemptCount),
							String.valueOf(loginUserFailAttemptCountDefault),
							SystemConfiguration.getInstance().getLoginUserAccountUnlockMinute() };
					result.reject("login.error.attempt.message", errorParameters, "login.error.attempt.message");

					// give same invalid message as when account not found
				} else {
					result.reject("login.error.message", "login.error.message");
				}
			} else {
				if ((loginInfo.getPasswordExpiredDayNo() <= Integer
						.parseInt(SystemConfiguration.getInstance().getLoginUserPasswordExpiredReminderDay()))
						&& (loginInfo.getPasswordExpiredDayNo() > Integer
								.parseInt(SystemConfiguration.getInstance().getLoginUserChangePasswordAllowDay()))) {
					result.reject("login.password.expired.reminder", "login.password.expired.reminder");
				} else if ((loginInfo.getPasswordExpiredDayNo() <= Integer
						.parseInt(SystemConfiguration.getInstance().getLoginUserChangePasswordAllowDay()))
						&& (loginInfo.getPasswordExpiredDayNo() > 0)) {
					result.reject("login.password.expired.force.notice", "login.password.expired.force.notice");
				}
				if (loginInfo.getSystemUserId() == 0) {
					result.reject("login.error.system.user.id", "login.error.system.user.id");
				} else {
					// request a new session (session fixation protection)
					request.getSession(false).invalidate();
					request.getSession();

					SystemUserDAO systemUserDAO = new SystemUserDAOImpl();
					SystemUser su = new SystemUser();
					su.setId(String.valueOf(loginInfo.getSystemUserId()));
					systemUserDAO.getData(su);

					// setup the user timeout in seconds
					int timeOut = Integer.parseInt((String) loginInfo.getUserTimeOut());
					request.getSession().setMaxInactiveInterval(timeOut * 60);

					UserSessionData usd = new UserSessionData();
					usd.setSytemUserId(loginInfo.getSystemUserId());
					usd.setLoginName(loginInfo.getLoginName());
					usd.setElisUserName(su.getNameForDisplay());
					usd.setUserTimeOut(timeOut * 60);
					request.getSession().setAttribute(USER_SESSION_DATA, usd);

					// boolean showAdminMenu = loginInfo.getIsAdmin().equalsIgnoreCase(YES);

					if (SystemConfiguration.getInstance().getPermissionAgent().equals("ROLE")) {
						HashSet<String> permittedPages = getPermittedForms(usd.getSystemUserId());
						request.getSession().setAttribute(IActionConstants.PERMITTED_ACTIONS_MAP, permittedPages);
						// showAdminMenu |= permittedPages.contains("MasterList");
					}

				}
				if (request.getSession().getAttribute(LOGIN_FAILED_CNT) != null)
					request.getSession().removeAttribute(LOGIN_FAILED_CNT);
				if (request.getSession().getAttribute(ACCOUNT_LOCK_TIME) != null)
					request.getSession().removeAttribute(ACCOUNT_LOCK_TIME);

				if (loginInfo.getIsAdmin().equalsIgnoreCase(YES))
					// bugzilla 2154
					LogEvent.logInfo("LoginValidateAction", "performAction()", "======> USER TYPE: ADMIN");
				else {
					// bugzilla 2154
					LogEvent.logInfo("LoginValidateAction", "performAction()", "======> USER TYPE: NON-ADMIN");
					// bugzilla 2160
					UserModuleDAO userModuleDAO = new UserModuleDAOImpl();
					if (!userModuleDAO.isUserModuleFound(request)) {
						result.reject("login.error.no.module");
					}
				}
			}

		}
		if (result.hasErrors()) {
			// model.addAttribute("errors", result.getAllErrors());
			saveErrors(result);
			forward = FWD_FAIL;
		}

		return findForward(forward, form);
	}

	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("redirect:/Dashboard.do", "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("loginPageDefinition", "form", form);
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

	/**
	 * Account is locked/unlock after the user entered wrong password (3 times)
	 * 
	 * @param errors  the ActionMessages
	 * @param request the HttpServletRequest
	 * @param login   the user login object
	 */
	private void lockUnlockUserAccount(BindingResult result, HttpServletRequest request, Login login) {
		java.util.Calendar loginTime = java.util.Calendar.getInstance();

		if (request.getSession().getAttribute(ACCOUNT_LOCK_TIME) != null) {
			loginTime = (java.util.Calendar) request.getSession().getAttribute(ACCOUNT_LOCK_TIME);
		} else {
			int lockMinute = Integer.parseInt(SystemConfiguration.getInstance().getLoginUserAccountUnlockMinute());
			loginTime.add(java.util.Calendar.MINUTE, +lockMinute);
			request.getSession().setAttribute(ACCOUNT_LOCK_TIME, loginTime);
		}

		java.util.Calendar now = java.util.Calendar.getInstance();
		int diff = Integer.parseInt(String.valueOf((loginTime.getTimeInMillis() - now.getTimeInMillis()) / 1000));

		if (diff > 0) {
			int seconds = (int) (diff % 60);
			int minutes = (int) ((diff / 60) % 60);
			int hours = (int) ((diff / 3600) % 24);
			String secondsStr = (seconds < 10 ? "0" : "") + seconds;
			String minutesStr = (minutes < 10 ? "0" : "") + minutes;
			String hoursStr = (hours < 10 ? "0" : "") + hours;
			String unlockTime = hoursStr + ":" + minutesStr + ":" + secondsStr;
			// ActionError error = new ActionError("login.user.account.lock.message",
			// unlockTime, null);
			result.reject("login.user.account.lock.message", new Object[] { unlockTime },
					"login.user.account.lock.message");
		} else {
			request.getSession().removeAttribute(ACCOUNT_LOCK_TIME);
			org.hibernate.Transaction tx = HibernateUtil.getSession().beginTransaction();
			LoginDAO loginDAO = new LoginDAOImpl();
			loginDAO.unlockAccount(login);
			login.setAccountLocked(NO);
			tx.commit();
			HibernateUtil.closeSession();
			// ActionError error = new ActionError("login.user.account.unlock.message",
			// null);
			result.reject("login.user.account.unlock.message", "login.user.account.unlock.message");
		}
	}

	@SuppressWarnings("unchecked")
	private HashSet<String> getPermittedForms(int systemUserId) {
		HashSet<String> permittedPages = new HashSet<String>();

		UserRoleDAO userRoleDAO = new UserRoleDAOImpl();

		List<String> roleIds = userRoleDAO.getRoleIdsForUser(Integer.toString(systemUserId));

		PermissionAgentModuleDAO roleModuleDAO = new RoleModuleDAOImpl();

		for (String roleId : roleIds) {
			List<RoleModule> roleModules = roleModuleDAO.getAllPermissionModulesByAgentId(Integer.parseInt(roleId));

			for (RoleModule roleModule : roleModules) {
				permittedPages.add(roleModule.getSystemModule().getSystemModuleName());
			}
		}

		return permittedPages;
	}
}
