package spring.mine.interceptor;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import spring.mine.common.validator.BaseErrors;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.security.PageIdentityUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.login.dao.UserModuleDAO;
import us.mn.state.health.lims.login.daoimpl.UserModuleDAOImpl;

@Component
public class ModuleAuthenticationInterceptor extends HandlerInterceptorAdapter {

	private static final boolean USE_PARAMETERS = true;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		UserModuleDAO userModuleDAO = new UserModuleDAOImpl();
		Errors errors = new BaseErrors();
		if (!hasPermission(userModuleDAO, errors, request)) {
			errors.reject("login.error.module.not.allow", "login.error.module.not.allow"); // set the errors that should
																							// be preserved on redirect
			request.getSession().setAttribute(IActionConstants.REDIRECT_ERRORS, errors);
			LogEvent.logInfo("BaseController", "execute()", "======> NOT ALLOWED ACCESS TO THIS MODULE");
			System.out.println("has no permission"); //
			redirectStrategy.sendRedirect(request, response, "/Home.do");
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	protected boolean hasPermission(UserModuleDAO userModuleDAO, Errors errors, HttpServletRequest request) {
		if (!userModuleDAO.isUserAdmin(request)) {
			if (SystemConfiguration.getInstance().getPermissionAgent().equals("ROLE")) {
				if (!PageIdentityUtil.isMainPage(request)) {

					@SuppressWarnings("rawtypes")
					HashSet accessMap = (HashSet) request.getSession()
							.getAttribute(IActionConstants.PERMITTED_ACTIONS_MAP);

					if (!accessMap.contains(PageIdentityUtil.getActionName(request, USE_PARAMETERS))) {
						// TO DO: Uncomment when modules are working again
						// return false;
					}
				}
			} else {
				if (!userModuleDAO.isVerifyUserModule(request)) {
					return false;
				}
			}
		}
		return true;
	}

}
