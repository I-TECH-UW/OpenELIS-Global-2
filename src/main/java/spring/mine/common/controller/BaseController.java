package spring.mine.common.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import spring.mine.common.constants.Constants;
import spring.mine.common.form.BaseForm;
import spring.mine.internationalization.MessageUtil;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.login.dao.UserModuleService;
import us.mn.state.health.lims.login.daoimpl.UserModuleServiceImpl;
import us.mn.state.health.lims.login.valueholder.UserSessionData;

@Component
public abstract class BaseController implements IActionConstants {

	@Autowired
	MessageUtil messageUtil;
	// Request being autowired appears to be threadsafe because of how Spring
	// handles autowiring, despite all controllers being singletons
	// However this is still not best practice and it would be better to rely on
	// Spring's dependency injection into methods for accessing the request
	@Autowired
	protected HttpServletRequest request;

	protected abstract String findLocalForward(String forward);

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
	 * @param request the request
	 * @param form    the form associated with this request.
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
	 * @param request the request
	 * @param form    the form associated with this request.
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
	 * @param message The message
	 * @return The message
	 */
	protected String getActualMessage(String message) {
		return message;
	}

	/**
	 * Utility method to simplify the lookup of MessageResource Strings in the
	 * ApplicationResources.properties file for this application.
	 *
	 * @param messageKey the message key to look up
	 */
	protected String getMessageForKey(String messageKey) throws Exception {
		String message = MessageUtil.getContextualMessage(messageKey);
		return message == null ? getActualMessage(messageKey) : message;
	}

	/**
	 * Utility method to simplify the lookup of MessageResource Strings in the
	 * ApplicationResources.properties file for this application.
	 *
	 * @param request    the HttpServletRequest
	 * @param messageKey the message key to look up
	 */
	protected String getMessageForKey(HttpServletRequest request, String messageKey) throws Exception {
		if (null == messageKey) {
			return null;
		}
		return MessageUtil.getMessage(messageKey);
		// return ResourceLocator.getInstance().getMessageResources().getMessage(locale,
		// messageKey);
	}

	protected String getMessageForKey(HttpServletRequest request, String messageKey, String arg0) throws Exception {
		if (null == messageKey) {
			return null;
		}
		return MessageUtil.getMessage(messageKey);
		// return ResourceLocator.getInstance().getMessageResources().getMessage(locale,
		// messageKey, arg0);
	}

	protected void setPageTitles(HttpServletRequest request, BaseForm form) {

		String pageSubtitle = null;
		String pageTitle = null;
		String pageTitleKey = getPageTitleKey(request, form);
		String pageSubtitleKey = getPageSubtitleKey(request, form);

		String pageTitleKeyParameter = getPageTitleKeyParameter(request, form);
		String pageSubtitleKeyParameter = getPageSubtitleKeyParameter(request, form);

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

		if (null != pageTitle) {
			request.setAttribute(PAGE_TITLE_KEY, pageTitle);
		}
		if (null != pageSubtitle) {
			request.setAttribute(PAGE_SUBTITLE_KEY, pageSubtitle);
		}

	}

	protected String getSysUserId(HttpServletRequest request) {
		UserSessionData usd = (UserSessionData) request.getSession().getAttribute(USER_SESSION_DATA);
		if (usd == null) {
			return null;
		}
		return String.valueOf(usd.getSystemUserId());
	}

	protected void setSuccessFlag(HttpServletRequest request, boolean success) {
		request.setAttribute(FWD_SUCCESS, success);
	}

	protected void setSuccessFlag(HttpServletRequest request) {
		request.setAttribute(FWD_SUCCESS, Boolean.TRUE);
	}

	protected boolean userHasPermissionForModule(HttpServletRequest request, String module) {
		UserModuleService userModuleService = new UserModuleServiceImpl();
		if (!userModuleService.isUserAdmin(request)
				&& SystemConfiguration.getInstance().getPermissionAgent().equals("ROLE")) {
			@SuppressWarnings("rawtypes")
			HashSet accessMap = (HashSet) request.getSession().getAttribute(IActionConstants.PERMITTED_ACTIONS_MAP);
			return accessMap.contains(module);
		}

		return true;
	}

	protected String findForward(String forward) {
		if (LOGIN_PAGE.equals(forward)) {
			return "redirect:LoginPage.do";
		}
		if (HOME_PAGE.equals(forward)) {
			return "redirect:Home.do";
		}
		return findLocalForward(forward);
	}

	protected ModelAndView findForward(String forward, BaseForm form) {
		String realForward = findForward(forward);
		if (realForward.startsWith("redirect:")) {
			return new ModelAndView(realForward);
		} else {
			setPageTitles(request, form);
			// insert global forwards here
			return new ModelAndView(realForward, "form", form);
		}
	}

	protected ModelAndView findForward(String forward, Map<String, Object> requestObjects, BaseForm form) {
		ModelAndView mv = findForward(forward, form);
		mv.addAllObjects(requestObjects);
		return mv;
	}

	protected ModelAndView getForward(ModelAndView mv, String id, String startingRecNo, String direction) {
		LogEvent.logInfo("BaseAction", "getForward()", "This is forward " + mv.getViewName());

		if (id != null) {
			mv.addObject(ID, id);
		}
		if (startingRecNo != null) {
			mv.addObject("startingRecNo", startingRecNo);
		}
		if (direction != null) {
			mv.addObject("direction", direction);
		}

		return mv;
	}

	protected ModelAndView getForwardWithParameters(ModelAndView mv, Map<String, String> params) {
		mv.addAllObjects(params);
		return mv;
	}

	protected void saveErrors(Errors errors) {
		if (request.getAttribute(Constants.REQUEST_ERRORS) == null) {
			request.setAttribute(Constants.REQUEST_ERRORS, errors);
		} else {
			Errors previousErrors = (Errors) request.getAttribute(Constants.REQUEST_ERRORS);
			if (previousErrors.hasErrors() && previousErrors.getObjectName().equals(errors.getObjectName())) {
				previousErrors.addAllErrors(errors);
			} else {
				request.setAttribute(Constants.REQUEST_ERRORS, errors);
			}
		}
	}

	public void writeErrorsToResponse(BindingResult result, HttpServletResponse response) {
		ServletOutputStream servletOutputStream;
		try {
			servletOutputStream = response.getOutputStream();
			StringBuilder errorMsgBuilder = new StringBuilder();
			for (ObjectError error : result.getGlobalErrors()) {
				errorMsgBuilder.append(MessageUtil.getMessageOrDefault(error.getCode(), error.getArguments(),
						error.getDefaultMessage()));
				errorMsgBuilder.append(System.lineSeparator());
			}
			for (FieldError error : result.getFieldErrors()) {
				errorMsgBuilder.append(error.getField() + ": ");
				errorMsgBuilder.append(MessageUtil.getMessageOrDefault(error.getCode(), error.getArguments(),
						error.getDefaultMessage()));
				errorMsgBuilder.append(System.lineSeparator());
			}

			byte[] errorMsg = errorMsgBuilder.toString().getBytes();
			response.setContentType("text/plain");
			response.setContentLength(errorMsg.length);
			servletOutputStream.write(errorMsg);

		} catch (IOException e) {
			LogEvent.logError("PrintWorkplanReportController", "writeErrorsToResponse", e.getMessage());
		}

	}

	protected Errors getErrors() {
		return (Errors) request.getAttribute(Constants.REQUEST_ERRORS);
	}

	// move flash attributes into request
	protected void addFlashMsgsToRequest(HttpServletRequest request) {
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inputFlashMap != null) {
			Boolean success = (Boolean) inputFlashMap.get(FWD_SUCCESS);
			request.setAttribute(FWD_SUCCESS, success);

			String successMessage = (String) inputFlashMap.get(Constants.SUCCESS_MSG);
			request.setAttribute(Constants.SUCCESS_MSG, successMessage);

			Errors errors = (Errors) inputFlashMap.get(Constants.REQUEST_ERRORS);
			request.setAttribute(Constants.SUCCESS_MSG, errors);

			List<String> messages = (List<String>) inputFlashMap.get(Constants.REQUEST_MESSAGES);
			request.setAttribute(Constants.REQUEST_MESSAGES, messages);

			List<String> warnings = (List<String>) inputFlashMap.get(Constants.REQUEST_WARNINGS);
			request.setAttribute(Constants.SUCCESS_MSG, warnings);
		}
	}

	// re-initialize form object in the request
	protected <T extends BaseForm> T resetFormSessionObject(String objectName, T form) {
		try {
			@SuppressWarnings("unchecked")
			T newForm = (T) form.getClass().newInstance();
			request.getSession().setAttribute(objectName, newForm);
			return newForm;
		} catch (InstantiationException | IllegalAccessException e) {
			LogEvent.logError("BaseController", "resetFormSessionObject", e.getMessage());
			return form;
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseForm> T resetFormToType(BaseForm form, Class<T> classType) {
		try {
			T newForm = classType.newInstance();
			request.getSession().setAttribute("form", newForm);
			return newForm;
		} catch (InstantiationException | IllegalAccessException e) {
			LogEvent.logError("BaseController", "resetFormToType", e.getMessage());
			return null;
		}
	}

}
