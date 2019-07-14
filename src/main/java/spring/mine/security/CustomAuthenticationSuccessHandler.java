package spring.mine.security;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import spring.service.login.LoginService;
import spring.service.systemuser.SystemUserService;
import spring.service.systemusermodule.PermissionModuleService;
import spring.service.userrole.UserRoleService;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.login.valueholder.Login;
import us.mn.state.health.lims.login.valueholder.UserSessionData;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;
import us.mn.state.health.lims.systemusermodule.valueholder.PermissionModule;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler, IActionConstants {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	private LoginService loginService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private PermissionModuleService<PermissionModule> permissionModuleService;
	@Autowired
	private SystemUserService systemUserService;

	public static final int DEFAULT_SESSION_TIMEOUT_IN_MINUTES = 20;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String homePath = "/Dashboard.do";
		Login loginInfo = loginService.getMatch("loginName", request.getParameter("loginName")).get();
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
		SystemUser su = systemUserService.get(String.valueOf(loginInfo.getSystemUserId()));
		// create usersessiondata and store in session
		UserSessionData usd = new UserSessionData();
		usd.setSytemUserId(loginInfo.getSystemUserId());
		usd.setLoginName(loginInfo.getLoginName());
		usd.setElisUserName(su.getNameForDisplay());
		usd.setUserTimeOut(timeout * 60);
		usd.setAdmin(loginService.isUserAdmin(loginInfo));
		request.getSession().setAttribute(IActionConstants.USER_SESSION_DATA, usd);

		// get permitted actions map (available modules for the current user)
		if (SystemConfiguration.getInstance().getPermissionAgent().equals("ROLE")) {
			Set<String> permittedPages = getPermittedForms(usd.getSystemUserId());
			request.getSession().setAttribute(IActionConstants.PERMITTED_ACTIONS_MAP, permittedPages);
			// showAdminMenu |= permittedPages.contains("MasterList");
		}
	}

	private Set<String> getPermittedForms(int systemUserId) {
		Set<String> allPermittedPages = new HashSet<>();

		List<String> roleIds = userRoleService.getRoleIdsForUser(Integer.toString(systemUserId));

		for (String roleId : roleIds) {
			Set<String> permittedPagesForRole = permissionModuleService
					.getAllPermittedPagesFromAgentId(Integer.parseInt(roleId));
			allPermittedPages.addAll(permittedPagesForRole);
		}

		return allPermittedPages;
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
