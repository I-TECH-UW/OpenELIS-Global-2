package org.openelisglobal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.localization.service.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class CommonPageAttributesInterceptor implements HandlerInterceptor {

    @Autowired
    LocalizationService localizationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("title", localizationService.getLocalizedValueById(
                ConfigurationProperties.getInstance().getPropertyValue(ConfigurationProperties.Property.BANNER_TEXT)));

        request.setAttribute("oeTitle", localizationService
                .getLocalizedValueById(ConfigurationProperties.getInstance().getPropertyValue(Property.BANNER_TEXT)));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) {
        BaseForm form;
        if (modelAndView != null) {
            form = (BaseForm) modelAndView.getModel().get("form");
            if (form != null && !GenericValidator.isBlankOrNull(form.getFormName())) {
                String name = form.getFormName();
                if (name.endsWith("Form")) {
                    String actionName = name.substring(1, name.length() - 4);
                    actionName = name.substring(0, 1).toUpperCase() + actionName;
                    request.setAttribute(IActionConstants.ACTION_KEY, actionName);
                    LogEvent.logDebug("PageAttributesInterceptor", "postHandle()",
                            "PageAttributesInterceptor formName = " + name + " actionName " + actionName);
                }
            }
        }
    }
}
