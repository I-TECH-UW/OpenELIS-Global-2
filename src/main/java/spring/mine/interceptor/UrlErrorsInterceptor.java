package spring.mine.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.HandlerInterceptor;

import spring.mine.common.validator.BaseErrors;
import us.mn.state.health.lims.common.action.IActionConstants;

@Component
public class UrlErrorsInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		Errors errors = new BaseErrors();
		if ("denied".equals(request.getParameter("access"))) {
			errors.reject("login.error.module.not.allow", "login.error.module.not.allow");
		}
		if ("true".equals(request.getParameter("passReminder"))) {
			errors.reject("login.password.expired.reminder", "login.password.expired.reminder");
		}
		if (errors.hasErrors()) {
			if (request.getAttribute(IActionConstants.URL_REQUEST_ERRORS) == null) {
				request.setAttribute(IActionConstants.URL_REQUEST_ERRORS, errors);
			} else {
				((Errors) request.getAttribute(IActionConstants.URL_REQUEST_ERRORS)).addAllErrors(errors);
			}
		}

		return true;
	}
}
