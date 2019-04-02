package spring.mine.security;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.login.dao.LoginDAO;
import us.mn.state.health.lims.login.daoimpl.LoginDAOImpl;
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

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler, IActionConstants {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public static final int DEFAULT_SESSION_TIMEOUT_IN_MINUTES = 20;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String homePath = "/Dashboard.do";
		LoginDAO loginDao = new LoginDAOImpl();
		Login loginInfo = loginDao.getUserProfile(request.getParameter("loginName"));
		setupUserSession(request, loginInfo);

		if (passwordExpiringSoon(loginInfo)) {
			homePath += "?passReminder=true";
		}

		redirectStrategy.sendRedirect(request, response, homePath);
		clearAuthenticationAttributes(request);

	}

	private void setupUserSession(HttpServletRequest request, Login loginInfo) {
		int timeout;
		if (loginInfo.getUserTimeOut() != null) {
			timeout = Integer.parseInt(loginInfo.getUserTimeOut()) * 60;
		} else {
			timeout = DEFAULT_SESSION_TIMEOUT_IN_MINUTES * 60;
		}
		request.getSession().setMaxInactiveInterval(timeout);

		// get system user and link to login user
		SystemUserDAO systemUserDAO = new SystemUserDAOImpl();
		SystemUser su = new SystemUser();
		su.setId(String.valueOf(loginInfo.getSystemUserId()));
		systemUserDAO.getData(su);
		// create usersessiondata and store in session
		UserSessionData usd = new UserSessionData();
		usd.setSytemUserId(loginInfo.getSystemUserId());
		usd.setLoginName(loginInfo.getLoginName());
		usd.setElisUserName(su.getNameForDisplay());
		usd.setUserTimeOut(timeout * 60);
		request.getSession().setAttribute(IActionConstants.USER_SESSION_DATA, usd);

		// get permitted actions map (available modules for the current user)
		if (SystemConfiguration.getInstance().getPermissionAgent().equals("ROLE")) {
			HashSet<String> permittedPages = getPermittedForms(usd.getSystemUserId());
			request.getSession().setAttribute(IActionConstants.PERMITTED_ACTIONS_MAP, permittedPages);
			// showAdminMenu |= permittedPages.contains("MasterList");
		}
	}

	@SuppressWarnings("unchecked")
	private HashSet<String> getPermittedForms(int systemUserId) {
		HashSet<String> permittedPages = new HashSet<>();

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

	private boolean passwordExpiringSoon(Login loginInfo) {
		return loginInfo.getPasswordExpiredDayNo() <= Integer
				.parseInt(SystemConfiguration.getInstance().getLoginUserPasswordExpiredReminderDay())
				&& (loginInfo.getPasswordExpiredDayNo() > Integer
						.parseInt(SystemConfiguration.getInstance().getLoginUserChangePasswordAllowDay()));
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute("login_errors");
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}
