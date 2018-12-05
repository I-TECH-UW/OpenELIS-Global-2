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
* Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.reports.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.reports.action.implementation.IReportParameterSetter;
import us.mn.state.health.lims.reports.action.implementation.ReportImplementationFactory;

public class CommonReportAction extends BaseAction {

	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		BaseActionForm dynaForm = (BaseActionForm) form;

		dynaForm.initialize(mapping);
		PropertyUtils.setProperty(dynaForm, "reportType", request.getParameter("type"));
		PropertyUtils.setProperty(dynaForm, "reportRequest", request.getParameter("report"));
		IReportParameterSetter setter = ReportImplementationFactory.getParameterSetter(request.getParameter("report"));

		if( setter != null){
			setter.setRequestParameters(dynaForm);
		}

		return mapping.findForward(FWD_SUCCESS);
	}

	@Override
	protected String getPageSubtitleKey() {
		return "reports.add.params";
	}

	@Override
	protected String getPageTitleKey() {
		return "reports.add.params";
	}
}
