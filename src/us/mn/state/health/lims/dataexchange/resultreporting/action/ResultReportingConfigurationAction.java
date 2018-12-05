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
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 *
 */
package us.mn.state.health.lims.dataexchange.resultreporting.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.services.ExchangeConfigurationService;
import us.mn.state.health.lims.common.services.ExchangeConfigurationService.ConfigurationDomain;

public class ResultReportingConfigurationAction extends BaseAction {

	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "true");
		request.setAttribute(NEXT_DISABLED, "true");
		request.getSession().setAttribute(SAVE_DISABLED, "false");

		DynaActionForm dynaForm = (DynaActionForm) (form);
		dynaForm.initialize(mapping);

		ExchangeConfigurationService configService = new ExchangeConfigurationService( ConfigurationDomain.REPORT);
		
		PropertyUtils.setProperty(dynaForm, "reports", configService.getConfigurations());
		PropertyUtils.setProperty(dynaForm, "hourList", DisplayListService.getList(ListType.HOURS));
		PropertyUtils.setProperty(dynaForm, "minList", DisplayListService.getList(ListType.MINS));
		return mapping.findForward(FWD_SUCCESS);
	}



	@Override
	protected String getPageTitleKey() {
		return "resultreporting.browse.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "resultreporting.browse.title";
	}

}
