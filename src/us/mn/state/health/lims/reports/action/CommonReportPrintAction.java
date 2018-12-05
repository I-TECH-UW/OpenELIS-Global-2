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

import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.services.ReportTrackingService;
import us.mn.state.health.lims.common.services.ReportTrackingService.ReportType;
import us.mn.state.health.lims.reports.action.implementation.IReportCreator;
import us.mn.state.health.lims.reports.action.implementation.ReportImplementationFactory;

public class CommonReportPrintAction extends BaseAction {

	private static String reportPath = null;
	private static String imagesPath = null;


	@SuppressWarnings("unchecked")
	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BaseActionForm dynaForm = (BaseActionForm) form;

		PropertyUtils.setProperty(dynaForm, "reportType", request.getParameter("type"));

		IReportCreator reportCreator = ReportImplementationFactory.getReportCreator(request.getParameter("report"));

		String forward = FWD_FAIL;

		if (reportCreator != null) {
			reportCreator.setRequestedReport(request.getParameter("report"));
			reportCreator.initializeReport(dynaForm);
			reportCreator.setReportPath(getReportPath());

			HashMap<String, String> parameterMap = (HashMap<String, String>) reportCreator.getReportParameters();
			parameterMap.put("SUBREPORT_DIR", getReportPath());
			parameterMap.put("imagesPath", getImagesPath());
			
			try {

				response.setContentType(reportCreator.getContentType());
				String responseHeaderName = reportCreator.getResponseHeaderName();
				String responseHeaderContent = reportCreator.getResponseHeaderContent();
				if( !GenericValidator.isBlankOrNull(responseHeaderName) && !GenericValidator.isBlankOrNull(responseHeaderContent) ){
					response.setHeader(responseHeaderName, responseHeaderContent);
				}
				
				byte[] bytes = reportCreator.runReport();

				response.setContentLength(bytes.length);

				ServletOutputStream servletOutputStream = response.getOutputStream();

				servletOutputStream.write(bytes, 0, bytes.length);
				servletOutputStream.flush();
				servletOutputStream.close();
			} catch (Exception e) {
				LogEvent.logErrorStack("CommonReportPrintAction", "performAction", e);
				e.printStackTrace();
			}
		}

		if("patient".equals(request.getParameter("type"))){
			trackReports( reportCreator, request.getParameter("report"), ReportType.PATIENT);
		}
		
		return mapping.findForward(forward);
	}

	private void trackReports(IReportCreator reportCreator, String reportName, ReportType reportType) {
		new ReportTrackingService().addReports(reportCreator.getReportedOrders(), reportType, reportName, currentUserId);
	}

	@Override
	protected String getPageSubtitleKey() {
		return "qaevent.add.title";
	}

	@Override
	protected String getPageTitleKey() {
		return "qaevent.add.title";
	}

	public String getReportPath() {
		if (reportPath == null) {
			reportPath = getServlet().getServletContext().getRealPath("") + "/WEB-INF/reports/";
		}
		return reportPath;
	}
	
	public String getImagesPath() {
		if (imagesPath == null) {
			imagesPath = getServlet().getServletContext().getRealPath("") + "/images/" ;
		}
		return imagesPath;
	}
}
