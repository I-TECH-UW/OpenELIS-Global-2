package spring.mine.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.log.LogEvent;

//TODO this class may be unnecessary. Unsure of what functionality relies on it since module authentication was changed
@Component
public class PageAttributesInterceptor implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		BaseForm form;
		if (modelAndView != null) {
			form = (BaseForm) modelAndView.getModel().get("form");
			if (form != null && !GenericValidator.isBlankOrNull(form.getFormName())) {
				String name = form.getFormName();
				if (name.endsWith("Form")) {
					String actionName = name.substring(1, name.length() - 4);
					actionName = name.substring(0, 1).toUpperCase() + actionName;
					request.setAttribute(IActionConstants.ACTION_KEY, actionName);
					LogEvent.logInfo("PageAttributesInterceptor", "postHandle()",
							"PageAttributesInterceptor formName = " + name + " actionName " + actionName);
				}
			}
		}
	}

}
