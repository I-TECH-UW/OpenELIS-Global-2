package spring.mine.common.controller;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.form.BaseForm;
import us.mn.state.health.lims.common.util.resources.ResourceLocator;
import us.mn.state.health.lims.login.dao.UserModuleDAO;
import us.mn.state.health.lims.login.daoimpl.UserModuleDAOImpl;
import us.mn.state.health.lims.login.valueholder.UserSessionData;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.security.PageIdentityUtil;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;

public abstract class BaseController implements IActionConstants {

	private static final boolean USE_PARAMETERS = true;

	protected String currentUserId;

	protected abstract ModelAndView findLocalForward(String forward, BaseForm form);

	/**
	 * Must be implemented by subclasses to set the title for the requested page.
	 * The value returned should be a key String from the
	 * ApplicationResources.properties file.
	 * 
	 * @return the title key for this page.
	 */
	protected abstract String getPageTitleKey();

	/**
	 * Must be implemented by subclasses to set the subtitle for the requested page.
	 * The value returned should be a key String from the
	 * ApplicationResources.properties file.
	 * 
	 * @return the subtitle key this page.
	 */
	protected abstract String getPageSubtitleKey();

	/**
	 * This getPageTitleKey method accepts a request and form parameter so that a
	 * subclass can override the method and conditionally return different titles.
	 * 
	 * @param request
	 *            the request
	 * @param form
	 *            the form associated with this request.
	 * @return the title key for this page.
	 */
	protected String getPageTitleKey(HttpServletRequest request, BaseForm form) {
		return getPageTitleKey();
	}

	protected String getPageTitleKeyParameter(HttpServletRequest request, BaseForm form) {
		return null;
	}

	/**
	 * This getSubtitleKey method accepts a request and form parameter so that a
	 * subclass can override the method and conditionally return different
	 * subtitles.
	 * 
	 * @param request
	 *            the request
	 * @param form
	 *            the form associated with this request.
	 * @return the subtitle key this page.
	 */
	protected String getPageSubtitleKey(HttpServletRequest request, BaseForm form) {
		return getPageSubtitleKey();
	}

	protected String getPageSubtitleKeyParameter(HttpServletRequest request, BaseForm form) {
		return null;
	}

	/**
	 * Template method to allow subclasses to handle special cases. The default is
	 * to return the message
	 * 
	 * @param message
	 *            The message
	 * @return The message
	 */
	protected String getActualMessage(String message) {
		return message;
	}

	/**
	 * Utility method to simplify the lookup of MessageResource Strings in the
	 * ApplicationResources.properties file for this application.
	 * 
	 * @param messageKey
	 *            the message key to look up
	 */
	protected String getMessageForKey(String messageKey) throws Exception {
		String message = StringUtil.getContextualMessageForKey(messageKey);
		return message == null ? getActualMessage(messageKey) : message;
	}

	/**
	 * Utility method to simplify the lookup of MessageResource Strings in the
	 * ApplicationResources.properties file for this application.
	 * 
	 * @param request
	 *            the HttpServletRequest
	 * @param messageKey
	 *            the message key to look up
	 */
	protected String getMessageForKey(HttpServletRequest request, String messageKey) throws Exception {
		if (null == messageKey)
			return null;
		java.util.Locale locale = (java.util.Locale) request.getSession()
				.getAttribute("org.apache.struts.action.LOCALE");
		// Return the message for the user's locale.
		return ResourceLocator.getInstance().getMessageResources().getMessage(locale, messageKey);
	}

	protected String getMessageForKey(HttpServletRequest request, String messageKey, String arg0) throws Exception {
		if (null == messageKey)
			return null;
		java.util.Locale locale = (java.util.Locale) request.getSession()
				.getAttribute("org.apache.struts.action.LOCALE");
		// Return the message for the user's locale.
		return ResourceLocator.getInstance().getMessageResources().getMessage(locale, messageKey, arg0);
	}

	protected void setFormAttributes(BaseForm form, HttpServletRequest request) {
		if (null != form) {
			String name = form.getFormName();
			request.setAttribute(IActionConstants.FORM_NAME, name);
			request.setAttribute("formType", form.getClass().toString());

			String actionName = name.substring(1, name.length() - 4);
			actionName = name.substring(0, 1).toUpperCase() + actionName;
			request.setAttribute(IActionConstants.ACTION_KEY, actionName);
			// System.out.println("LoginBaseAction formName = " + name + " actionName " +
			// actionName);
			// bugzilla 2154
			LogEvent.logInfo("BaseController", "setFormAttributes()",
					"BaseController formName = " + name + " actionName " + actionName);
		}
	}

	protected void setPageTitles(HttpServletRequest request, BaseForm form) {

		String pageSubtitle = null;
		String pageTitle = null;
		String pageTitleKey = getPageTitleKey(request, form);
		String pageSubtitleKey = getPageSubtitleKey(request, form);

		String pageTitleKeyParameter = getPageTitleKeyParameter(request, form);
		String pageSubtitleKeyParameter = getPageSubtitleKeyParameter(request, form);

		request.getSession().setAttribute(Globals.LOCALE_KEY, SystemConfiguration.getInstance().getDefaultLocale());

		try {
			if (StringUtil.isNullorNill(pageTitleKeyParameter)) {
				pageTitle = getMessageForKey(request, pageTitleKey);
			} else {
				pageTitle = getMessageForKey(request, pageTitleKey, pageTitleKeyParameter);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogEvent.logError("BaseController", "setPageTitles", "could not get message for key: " + pageTitleKey);
		}

		try {
			if (StringUtil.isNullorNill(pageSubtitleKeyParameter)) {
				pageSubtitle = getMessageForKey(request, pageSubtitleKey);
			} else {
				pageSubtitle = getMessageForKey(request, pageSubtitleKey, pageSubtitleKeyParameter);
			}

		} catch (Exception e) {
			e.printStackTrace();
			LogEvent.logError("BaseController", "setPageTitles", "could not get message for key: " + pageSubtitleKey);
		}

		if (null != pageTitle)
			request.setAttribute(PAGE_TITLE_KEY, pageTitle);
		if (null != pageSubtitle)
			request.setAttribute(PAGE_SUBTITLE_KEY, pageSubtitle);

	}

	protected void setLanguage(HttpServletRequest request) {
		if ("true".equals(ConfigurationProperties.getInstance().getPropertyValue(Property.languageSwitch))) {
			String language = request.getParameter("lang");
			if (language != null) {
				SystemConfiguration.getInstance().setDefaultLocale(language);
			}
		}
	}

	protected String getSysUserId(HttpServletRequest request) {
		UserSessionData usd = (UserSessionData) request.getSession().getAttribute(USER_SESSION_DATA);
		return String.valueOf(usd.getSystemUserId());
	}

	protected void setSuccessFlag(HttpServletRequest request, String forwardFlag) {
		request.setAttribute(FWD_SUCCESS, FWD_SUCCESS.equals(forwardFlag));
	}

	protected void setSuccessFlag(HttpServletRequest request) {
		request.setAttribute(FWD_SUCCESS, Boolean.TRUE);
	}

	protected boolean userHasPermissionForModule(HttpServletRequest request, String module) {
		UserModuleDAO userModuleDAO = new UserModuleDAOImpl();
		if (!userModuleDAO.isUserAdmin(request)
				&& SystemConfiguration.getInstance().getPermissionAgent().equals("ROLE")) {
			@SuppressWarnings("rawtypes")
			HashSet accessMap = (HashSet) request.getSession().getAttribute(IActionConstants.PERMITTED_ACTIONS_MAP);
			return accessMap.contains(module);
		}

		return true;
	}

	protected ModelAndView checkUserAndSetup(BaseForm form, Errors errors, HttpServletRequest request) {

		UserModuleDAO userModuleDAO = new UserModuleDAOImpl();
		if (!isUserAuthenticated(userModuleDAO, errors, request)) {
			return findForward(LOGIN_PAGE, form);
		}

		// Set language to be used
		setLanguage(request);

		currentUserId = getSysUserId(request);

		// Set page titles in request attribute
		setPageTitles(request, form);

		// Set the form attributes
		setFormAttributes(form, request);

		if (!isAccountUsable(userModuleDAO, errors, request)) {
			return findForward(LOGIN_PAGE, form);
		}

		if (!hasPermission(userModuleDAO, errors, request)) {
			errors.reject("login.error.module.not.allow", "login.error.module.not.allow");
			LogEvent.logInfo("BaseController", "execute()", "======> NOT ALLOWED ACCESS TO THIS MODULE");
			return userModuleDAO.isSessionExpired(request) ? findForward(LOGIN_PAGE, form)
					: findForward(HOME_PAGE, form);
		}

		userModuleDAO.setupUserSessionTimeOut(request);

		return new ModelAndView();
	}

	protected ModelAndView findForward(String forward, BaseForm form) {
		if (LOGIN_PAGE.equals(forward)) {
			return new ModelAndView("redirect:LoginPage.html", "errors", form.getErrors());
		}

		
		return findLocalForward(forward, form);
	}

	protected void saveErrors(Errors errors, BaseForm form) {
		form.setErrors(errors.getAllErrors());
	}

	protected boolean isUserAuthenticated(UserModuleDAO userModuleDAO, Errors errors, HttpServletRequest request) {
		// return to login page if user session is not found
		if (userModuleDAO.isSessionExpired(request)) {
			/*
			 * ActionMessages errors = new ActionMessages(); ActionError error = new
			 * ActionError("login.error.session.message", null, null);
			 * errors.add(ActionMessages.GLOBAL_MESSAGE, error); saveErrors(request,
			 * errors); return mapping.findForward(LOGIN_PAGE);
			 */
			errors.reject("login.error.session.message", "login.error.session.message");
			return false;
		}
		return true;
	}

	protected boolean isAccountUsable(UserModuleDAO userModuleDAO, Errors errors, HttpServletRequest request) {
		if (userModuleDAO.isAccountDisabled(request)) {
			// ActionMessages errors = new ActionMessages();
			// ActionError error = new ActionError("login.error.account.disable", null,
			// null);
			// errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			// saveErrors(request, errors);
			errors.reject("login.error.account.disable", "login.error.account.disable");
			return false;
		}

		if (userModuleDAO.isAccountLocked(request)) {
			// ActionMessages errors = new ActionMessages();
			// ActionError error = new ActionError("login.error.account.lock", null, null);
			// errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			// saveErrors(request, errors);
			errors.reject("login.error.account.lock", "login.error.account.lock");
			return false;
		}

		if (userModuleDAO.isPasswordExpired(request)) {
			// ActionMessages errors = new ActionMessages();
			// ActionError error = new ActionError("login.error.password.expired", null,
			// null);
			// errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			errors.reject("login.error.password.expired", "login.error.password.expired");
			// saveErrors(request, errors);
			return false;
		}
		return true;
	}

	protected boolean hasPermission(UserModuleDAO userModuleDAO, Errors errors, HttpServletRequest request) {
		// check for user type (admin or non-admin)
		if (!userModuleDAO.isUserAdmin(request)) {
			if (SystemConfiguration.getInstance().getPermissionAgent().equals("ROLE")) {
				if (!PageIdentityUtil.isMainPage(request)) {

					@SuppressWarnings("rawtypes")
					HashSet accessMap = (HashSet) request.getSession()
							.getAttribute(IActionConstants.PERMITTED_ACTIONS_MAP);

					if (!accessMap.contains(PageIdentityUtil.getActionName(request, USE_PARAMETERS))) {
						return false;
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
