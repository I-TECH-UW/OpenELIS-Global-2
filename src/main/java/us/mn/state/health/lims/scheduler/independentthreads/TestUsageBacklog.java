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
package us.mn.state.health.lims.scheduler.independentthreads;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.json.simple.JSONObject;

import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.dataexchange.aggregatereporting.dao.ReportExternalExportDAO;
import us.mn.state.health.lims.dataexchange.aggregatereporting.daoimpl.ReportExternalExportDAOImpl;
import us.mn.state.health.lims.dataexchange.aggregatereporting.daoimpl.ReportQueueTypeDAOImpl;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportExternalExport;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportQueueType;
import us.mn.state.health.lims.hibernate.HibernateUtil;

public class TestUsageBacklog extends Thread {

	private ReportExternalExportDAO reportExportDAO = new ReportExternalExportDAOImpl();
	private AnalysisDAO analysisDAO = new AnalysisDAOImpl();
	private static String TEST_UTALIZATION_ID;

	static{
		ReportQueueType queueType = new ReportQueueTypeDAOImpl().getReportQueueTypeByName("labIndicator");
		
		if( queueType != null){
			TEST_UTALIZATION_ID = queueType.getId();
		}
	}

	@Override
	public void run() {
		if( ConfigurationProperties.getInstance().isPropertyValueEqual(Property.testUsageReporting, "true")){
			handleBacklog();
		}
	}

	
	private void handleBacklog() {
		System.out.println("Gathering triggered: " + DateUtil.getCurrentDateAsText("dd-MM-yyyy hh:mm"));
		LogEvent.logInfo("TestUsagebacklog", "handleBacklog", "Gathering triggered: " + DateUtil.getCurrentDateAsText("dd-MM-yyyy hh:mm"));

		Timestamp latestCollectionDate = getLatestCollectionDate();

		if (latestCollectionDate == null) {
			for (int i = 120; i >= 0; i--) {
				writeReportForDayPeriod(i);
			}
		} else {
			int daysInPast = DateUtil.getDaysInPastForDate(latestCollectionDate) - 1;
			for( int i = daysInPast; i >= 0; i--){
				writeReportForDayPeriod(i);
			}
		}

	}


	private Timestamp getLatestCollectionDate() {
		ReportExternalExport report = new ReportExternalExportDAOImpl().getLatestEventReportExport(TEST_UTALIZATION_ID);
		if( report != null){
			return report.getEventDate();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private void writeReportForDayPeriod(int daysAgo) {
		Timestamp dayOne = DateUtil.getTimestampAtMidnightForDaysAgo(daysAgo);
		Timestamp dayTwo = DateUtil.getTimestampAtMidnightForDaysAgo(daysAgo - 1);

		List<Analysis> analysisList = analysisDAO.getAnalysisCompleteInRange(dayOne, dayTwo);

		Map<String, Integer> testBucket = new HashMap<String, Integer>();
		for (String key : TestService.getMap( TestService.Entity.TEST_AUGMENTED_NAME ).keySet()) {
			testBucket.put(key, 0);
		}

		for (Analysis analysis : analysisList) {
			if ("Y".equals(analysis.getTest().getIsActive())) {
				String testId = analysis.getTest().getId();
				testBucket.put(testId, testBucket.get(testId).intValue() + 1); 
			}
		}

		JSONObject json = new JSONObject();
		for (String id : testBucket.keySet()) {
			if( testBucket.get(id).intValue() > 0){
				json.put(TestService.getMap(TestService.Entity.TEST_AUGMENTED_NAME ).get(id), testBucket.get(id));
			}
		}

		StringWriter out = new StringWriter();
		try {
			json.writeJSONString(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String jsonText = out.toString().replace("\n", "");

		
		ReportExternalExport report = new ReportExternalExport();
		report.setEventDate(dayOne);
		report.setTypeId(TEST_UTALIZATION_ID);

		report = reportExportDAO.getReportByEventDateAndType(report);
		report.setSend(true);
		report.setData(jsonText); //buffer.toString());
		report.setCollectionDate(DateUtil.getNowAsTimestamp());
		report.setSysUserId("1");

		Transaction tx = HibernateUtil.getSession().beginTransaction();
		try {
			if (report.getId() == null) {
				reportExportDAO.insertReportExternalExport(report);
			} else {
				reportExportDAO.updateReportExternalExport(report);
			}

			tx.commit();
		} catch (LIMSRuntimeException e) {
			tx.rollback();
		}
	}
}
