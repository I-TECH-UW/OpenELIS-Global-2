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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.SystemConfiguration;

/**
 * @author Hung Nguyen (Hung.Nguyen@health.state.mn.us)
 */
public class LoginPageAction extends LoginBaseAction {

	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = FWD_SUCCESS;

		cleanUpSession(request);

		if ("true".equals(ConfigurationProperties.getInstance().getPropertyValue(Property.languageSwitch))) {
			String language = request.getParameter("lang");

			if (language != null) {
				SystemConfiguration.getInstance().setDefaultLocale(language);
			}
		}
		
		DynaActionForm dynaForm = (DynaActionForm) form;
		// initialize the form
		dynaForm.initialize(mapping);

		return mapping.findForward(forward);
	}

	/**
	 * Cleanup all the session variables
	 * 
	 * @param request
	 *            is HttpServletRequest
	 */
	private void cleanUpSession(HttpServletRequest request) {
		if (request.getSession().getAttribute(USER_SESSION_DATA) != null)
			request.getSession().removeAttribute(USER_SESSION_DATA);
	}

	protected String getPageTitleKey() {
		return "login.title";
	}

	protected String getPageSubtitleKey() {
		return "login.subTitle";
	}
}
