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
package us.mn.state.health.lims.dataexchange.aggregatereporting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.Transaction;

import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.dataexchange.aggregatereporting.dao.ReportExternalImportDAO;
import us.mn.state.health.lims.dataexchange.aggregatereporting.daoimpl.ReportExternalImportDAOImpl;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportExternalImport;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.login.dao.LoginDAO;
import us.mn.state.health.lims.login.daoimpl.LoginDAOImpl;
import us.mn.state.health.lims.login.valueholder.Login;

public class IndicatorAggregationReportingServlet extends HttpServlet {
	private static ReportExternalImportDAO reportImportDAO = new ReportExternalImportDAOImpl();
	private static LoginDAO loginDAO = new LoginDAOImpl();
	private final String DATE_PATTERN = "yyyy-MM-dd";
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(HttpServletResponse.SC_OK);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		LogEvent.logFatal("IndicatorAggregationReportingServlet", "size", String.valueOf(request.getContentLength()));
		ServletInputStream inputStream = request.getInputStream();

		List<ReportExternalImport> insertableImportReports = new ArrayList<ReportExternalImport>();
		List<ReportExternalImport> updatableImportReports = new ArrayList<ReportExternalImport>();

		Document sentIndicators = getDocument( inputStream, request.getContentLength());

		if (sentIndicators == null) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}

		if (!authenticated(sentIndicators)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

        /*
        This is too handle the problem where either
        1. The same lab sends a report quickly enough to cause a race condition
        2. Two different labs send a report at the same time but one is miss-configured and uses the same site id
         
         In both cases createReportItems considers the report a new one rather than a modification of an existing report
         */
        synchronized( this ){
            createReportItems( sentIndicators, insertableImportReports, updatableImportReports );

            updateReports( insertableImportReports, updatableImportReports );
        }
        
        response.setStatus(HttpServletResponse.SC_OK);
	}

	private Document getDocument(ServletInputStream inputStream, int contentLength) {
		int charCount = 0;
		byte[] byteBuffer = new byte[contentLength];

		while( true){
			try {
				int readLength = inputStream.readLine(byteBuffer, charCount, 1024);
				//LogEvent.logFatal("IndicatorAggregationReportingServlet", String.valueOf(readLength), new String(byteBuffer).trim());

				if( readLength == -1){
					return DocumentHelper.parseText(new String(byteBuffer).trim());
				}else{
					charCount += readLength;
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} catch (DocumentException de) {
				de.printStackTrace();
				return null;
			}
		}
	}


	private void updateReports(List<ReportExternalImport> insertableImportReports, List<ReportExternalImport> updatableImportReports) {
		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			for (ReportExternalImport importReport : insertableImportReports) {
				reportImportDAO.insertReportExternalImport(importReport);
			}

			for (ReportExternalImport importReport : updatableImportReports) {
				reportImportDAO.updateReportExternalImport(importReport);
			}

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
	}

	private void createReportItems(Document aggregateDoc, List<ReportExternalImport> insertableImportReports,
			List<ReportExternalImport> updatableImportReports) {

		String sendingSiteId = (String) aggregateDoc.getRootElement().element("site-id").getData();

		Set<String> eventDateSet = new HashSet<String>(); // to make sure no
															// duplicates

		for (Object reportObj : aggregateDoc.getRootElement().elements("reports")) {
			Element report = (Element) reportObj;
			String eventDate = (String) report.element("event-date").getData();
			eventDate = eventDate.split(" ")[0];

			if (!eventDateSet.contains(eventDate)) {
				eventDateSet.add(eventDate);

				String data = (String) report.element("data").getData();
				ReportExternalImport importReport = createReportExternalImport(sendingSiteId, eventDate, data);

				if (importReport.getId() == null) {
					insertableImportReports.add(importReport);
				} else {
					updatableImportReports.add(importReport);
				}
			}
		}
	}

	private boolean authenticated(Document sentIndicators) {
		Element userElement = sentIndicators.getRootElement().element("user");
		Element passwordElement = sentIndicators.getRootElement().element("drowssap");

		if (userElement == null || passwordElement == null) {
			return false;
		}

		String user = (String) userElement.getData();
		String password = (String) passwordElement.getData();

		Login login = new Login();
		login.setLoginName(user);
		login.setPassword(password);

		Login loginInfo = loginDAO.getValidateLogin(login);

		return loginInfo != null;
	}

	private ReportExternalImport createReportExternalImport(String sendingSiteId, String eventDate, String data) {
		ReportExternalImport importReport = new ReportExternalImport();

		importReport.setEventDate(DateUtil.convertStringDateToTimestampWithPatternNoLocale(eventDate, DATE_PATTERN));
		importReport.setSendingSite(sendingSiteId);
		importReport.setReportType("testIndicators");

		ReportExternalImport rei = reportImportDAO.getReportByEventDateSiteType(importReport);

		if (rei != null) {
			importReport = rei;
		}

		importReport.setData(data);
		importReport.setSysUserId("1");
		importReport.setRecievedDate(DateUtil.getTimestampAtMidnightForDaysAgo(0));

		return importReport;
	}

	private static final long serialVersionUID = 1L;

}
