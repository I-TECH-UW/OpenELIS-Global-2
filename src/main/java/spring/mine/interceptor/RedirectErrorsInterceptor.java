package spring.mine.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.HandlerInterceptor;

import us.mn.state.health.lims.common.action.IActionConstants;

@Component
public class RedirectErrorsInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		Errors errors = (Errors) request.getSession().getAttribute(IActionConstants.REDIRECT_ERRORS);

		if (errors != null) {
			if (request.getAttribute(IActionConstants.REQUEST_ERRORS) == null) {
				request.setAttribute(IActionConstants.REQUEST_ERRORS, errors);
			} else {
				((Errors) request.getAttribute(IActionConstants.REQUEST_ERRORS)).addAllErrors(errors);
			}
			request.getSession().removeAttribute(IActionConstants.REDIRECT_ERRORS);
		}
		return true;
	}
}
