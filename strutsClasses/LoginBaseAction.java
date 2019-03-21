/**
* The contents of this file are subject to the Mozilla Public License
* Version 1.1 (the "License"); you may not use this file except in
* compliance with the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/ 
* 
* Software distributed under the License is distributed on an "AS IS"
* basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
* License for the specific language governing rights and limitations under
* the License.
* 
* The Original Code is OpenELIS code.
* 
* Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
*/
package us.mn.state.health.lims.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.common.util.resources.ResourceLocator;

public abstract class LoginBaseAction extends Action implements IActionConstants {
	String pageSubtitle = null;

	String pageTitle = null;

	public LoginBaseAction() {}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String pageSubtitle = null;
		String pageTitle = null;

		ActionForward forward = performAction(mapping, form, request, response);
		String pageTitleKey = getPageTitleKey(request, form);
		String pageSubtitleKey = getPageSubtitleKey(request, form);

		String pageTitleKeyParameter = getPageTitleKeyParameter(request, form);
		String pageSubtitleKeyParameter = getPageSubtitleKeyParameter(request,form);
	
		request.getSession().setAttribute(Globals.LOCALE_KEY, SystemConfiguration.getInstance().getDefaultLocale());
		
		if (StringUtil.isNullorNill(pageTitleKeyParameter)) {
			pageTitle = getMessageForKey(request, pageTitleKey);
		} else {
			pageTitle = getMessageForKey(request, pageTitleKey,
					pageTitleKeyParameter);
		}

		if (StringUtil.isNullorNill(pageSubtitleKeyParameter)) {
			pageSubtitle = getMessageForKey(request, pageSubtitleKey);
		} else {
			pageSubtitle = getMessageForKey(request, pageSubtitleKey,
					pageSubtitleKeyParameter);
		}

		if (null != pageTitle)
			request.setAttribute(PAGE_TITLE_KEY, pageTitle);
		if (null != pageSubtitle)
			request.setAttribute(PAGE_SUBTITLE_KEY, pageSubtitle);

		// Set the form attributes
		setFormAttributes(form, request);

		//System.out.println("Returning this forward from LoginBaseAction " + forward);
		return forward;
	}

	/**
	 * Abstract method that sub classes must implement to perform the desired
	 * action
	 */
	protected abstract ActionForward performAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	/**
	 * Must be implemented by subclasses to set the title for the requested
	 * page. The value returned should be a key String from the
	 * ApplicationResources.properties file.
	 * 
	 * @return the title key for this page.
	 */
	protected abstract String getPageTitleKey();

	/**
	 * Must be implemented by subclasses to set the subtitle for the requested
	 * page. The value returned should be a key String from the
	 * ApplicationResources.properties file.
	 * 
	 * @return the subtitle key this page.
	 */
	protected abstract String getPageSubtitleKey();

	/**
	 * This getPageTitleKey method accepts a request and form parameter so that
	 * a subclass can override the method and conditionally return different
	 * titles.
	 * 
	 * @param request
	 *            the request
	 * @param form
	 *            the form associated with this request.
	 * @return the title key for this page.
	 */
	protected String getPageTitleKey(HttpServletRequest request, ActionForm form) {
		return getPageTitleKey();
	}

	protected String getPageTitleKeyParameter(HttpServletRequest request,
			ActionForm form) {
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
	protected String getPageSubtitleKey(HttpServletRequest request,
			ActionForm form) {
		return getPageSubtitleKey();
	}

	protected String getPageSubtitleKeyParameter(HttpServletRequest request,
			ActionForm form) {
		return null;
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
	protected String getMessageForKey(HttpServletRequest request,
			String messageKey) throws Exception {
		if (null == messageKey)
			return null;
		java.util.Locale locale = (java.util.Locale) request.getSession()
				.getAttribute("org.apache.struts.action.LOCALE");
		// Return the message for the user's locale.
		return ResourceLocator.getInstance().getMessageResources().getMessage(
				locale, messageKey);
	}

	protected String getMessageForKey(HttpServletRequest request,
			String messageKey, String arg0) throws Exception {
		if (null == messageKey)
			return null;
		java.util.Locale locale = (java.util.Locale) request.getSession()
				.getAttribute("org.apache.struts.action.LOCALE");
		// Return the message for the user's locale.
		return ResourceLocator.getInstance().getMessageResources().getMessage(
				locale, messageKey, arg0);
	}

	protected void setFormAttributes(ActionForm form, HttpServletRequest request)
			throws Exception {
		try {
			if (null != form) {
				DynaActionForm theForm = (DynaActionForm) form;
				theForm.getDynaClass().getName();
				String name = theForm.getDynaClass().getName().toString();
				//use IActionConstants!
				request.setAttribute(IActionConstants.FORM_NAME, name);
				request.setAttribute("formType", theForm.getClass().toString());
				String actionName = name.substring(1, name.length() - 4);
				actionName = name.substring(0, 1).toUpperCase() + actionName;
				request.setAttribute(IActionConstants.ACTION_KEY, actionName);
				//System.out.println("LoginBaseAction formName = " + name 	+ " actionName " + actionName);
				//bugzilla 2154
				LogEvent.logInfo("LoginBaseAction","setFormAttributes()","LoginBaseAction formName = " + name 	+ " actionName " + actionName);
			}
		} catch (ClassCastException e) {
			//bugzilla 2154
			LogEvent.logError("LoginBaseAction","setFormAttributes()",e.toString());
			throw new ClassCastException("Error Casting form into DynaForm");
		}
	}
}