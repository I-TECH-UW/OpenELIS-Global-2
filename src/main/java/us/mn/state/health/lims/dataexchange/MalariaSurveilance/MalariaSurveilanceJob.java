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
package us.mn.state.health.lims.dataexchange.MalariaSurveilance;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.dataexchange.common.ITransmissionResponseHandler;
import us.mn.state.health.lims.dataexchange.common.ReportTransmission;
import us.mn.state.health.lims.dataexchange.common.ReportTransmission.HTTP_TYPE;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.scheduler.dao.CronSchedulerDAO;
import us.mn.state.health.lims.scheduler.daoimpl.CronSchedulerDAOImpl;
import us.mn.state.health.lims.scheduler.valueholder.CronScheduler;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testresult.dao.TestResultDAO;
import us.mn.state.health.lims.testresult.daoimpl.TestResultDAOImpl;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

public class MalariaSurveilanceJob implements Job {
	private static final String CRON_MALARIA_SCHEDULER = "sendMalariaSurviellanceReport";

	private static String MALARIA_TEST_ID;
	private static Set<String> RAPID_TEST_IDS;
	private static Map<String, String> MALARIA_DICTIONARY_ID_VALUE_MAP;
	private static Map<String, String> RAPID_DICTIONARY_ID_VALUE_MAP;
	private static List<String> MALARIA_TEST_NAMES;
	private static List<String> RAPID_TEST_NAMES;
	private static final String FINISHED_STATUS_ID = StatusService.getInstance().getStatusID(AnalysisStatus.Finalized);

	private StringBuffer buffer;
	private AnalysisDAO analysisDAO = new AnalysisDAOImpl();
	private CronSchedulerDAO cronSchedulerDAO = new CronSchedulerDAOImpl();
	private ResultDAO resultDAO = new ResultDAOImpl();

	static {

		TestDAO testDAO = new TestDAOImpl();
		RAPID_TEST_IDS = new HashSet<String>();
		MALARIA_DICTIONARY_ID_VALUE_MAP = new HashMap<String, String>();
		RAPID_DICTIONARY_ID_VALUE_MAP = new HashMap<String, String>();
		MALARIA_TEST_NAMES = new ArrayList<String>();
		RAPID_TEST_NAMES = new ArrayList<String>();

		// N.B. This should be discoverable from the DB, not hard coded by name
		MALARIA_TEST_NAMES.add("Recherche de plasmodiun - Especes(Sang)");
		RAPID_TEST_NAMES.add("Malaria Test Rapide(Serum)");
		RAPID_TEST_NAMES.add("Malaria Test Rapide(Plasma)");
		RAPID_TEST_NAMES.add("Malaria Test Rapide(Sang)");

		for (String name : MALARIA_TEST_NAMES) {
			Test test = testDAO.getTestByDescription(name);
			if (test != null && test.getId() != null) {
				MALARIA_TEST_ID = test.getId();
			}
		}

		for (String name : RAPID_TEST_NAMES) {
			addRapidTest(testDAO, name);
		}

		TestResultDAO testResultDAO = new TestResultDAOImpl();
		DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();

		List<TestResult> malariaResults = testResultDAO.getActiveTestResultsByTest( MALARIA_TEST_ID );

		for (TestResult testResult : malariaResults) {
			Dictionary dictionary = dictionaryDAO.getDictionaryById(testResult.getValue());
			if (dictionary != null) {
				MALARIA_DICTIONARY_ID_VALUE_MAP.put(dictionary.getId(), dictionary.getDictEntry());
			}
		}

		for (String rapidTestId : RAPID_TEST_IDS) {
			List<TestResult> rapidResults = testResultDAO.getActiveTestResultsByTest( rapidTestId );

			for (TestResult testResult : rapidResults) {
				Dictionary dictionary = dictionaryDAO.getDictionaryById(testResult.getValue());
				if (dictionary != null && RAPID_DICTIONARY_ID_VALUE_MAP.get(dictionary.getId()) == null) {
					RAPID_DICTIONARY_ID_VALUE_MAP.put(dictionary.getId(), dictionary.getDictEntry());
				}
			}
		}
	}

	private static void addRapidTest(TestDAO testDAO, String description) {
		Test test = testDAO.getTestByDescription(description);
		if (test != null && test.getId() != null) {
			RAPID_TEST_IDS.add(test.getId());
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("MalariaSurveilance triggered: " + DateUtil.getCurrentDateAsText("dd-MM-yyyy hh:mm"));
		LogEvent.logInfo("MalariaSurveilance", "execute()", "Gathering triggered: " + DateUtil.getCurrentDateAsText("dd-MM-yyyy hh:mm"));

		boolean sendReport = true;
		Timestamp latestCollectionDate = getLatestCollectionDate();

		buffer = new StringBuffer();

		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		buffer.append("<OpenElisServeillance>\n");
		buffer.append("<version>1</version>\n");
		buffer.append("<site name='");
		buffer.append(ConfigurationProperties.getInstance().getPropertyValue(Property.SiteName));
		buffer.append("' code='");
		buffer.append(ConfigurationProperties.getInstance().getPropertyValue(Property.SiteCode));
		buffer.append("' />\n");
		buffer.append("<reports date='");
		buffer.append(DateUtil.getCurrentDateAsText());
		buffer.append("'>\n");
		
		Timestamp nowTimestamp = DateUtil.getNowAsTimestamp();
		if (latestCollectionDate == null) {
			for (int i = 120; i > 0; i--) {
				writeReportForDayPeriod(i);
			}
		} else {
			int missedDays = DateUtil.getDaysInPastForDate(new Date(latestCollectionDate.getTime()));
			if (missedDays > 0) {
				for (int i = missedDays; i > 0; i--) {
					writeReportForDayPeriod(i);
				}
			} else {
				sendReport = false;
			}
		}

		buffer.append("</reports>\n");
		buffer.append("</OpenElisServeillance>\n");
		
		if(sendReport){
			boolean sendAsychronously = false;
			String url = ConfigurationProperties.getInstance().getPropertyValue(Property.malariaSurveillanceReportURL);
			ResponseHandler responseHandler = new ResponseHandler();
			responseHandler.setRunTime(nowTimestamp);
			
		    new ReportTransmission().sendRawReport(buffer.toString(), url, sendAsychronously, responseHandler, HTTP_TYPE.POST);

		}


	}


	private void writeReportForDayPeriod(int daysAgo) {
		Timestamp dayOne = DateUtil.getTimestampAtMidnightForDaysAgo(daysAgo );
		Timestamp dayTwo = DateUtil.getTimestampAtMidnightForDaysAgo(daysAgo - 1);

		Map<String, Integer> malairaBucket = new HashMap<String, Integer>();
		Map<String, Integer> rapidBucket = new HashMap<String, Integer>();

		buffer.append("<report type='Malaria' startDate='");
		buffer.append(DateUtil.convertTimestampToStringDate(dayOne));
		buffer.append("' endDate='");
		buffer.append(DateUtil.convertTimestampToStringDate(dayOne));
		buffer.append("' >\n");

		fillMalariaBuckets(dayOne, dayTwo, MALARIA_TEST_NAMES, malairaBucket, MALARIA_DICTIONARY_ID_VALUE_MAP);
		fillMalariaBuckets(dayOne, dayTwo, RAPID_TEST_NAMES, rapidBucket, RAPID_DICTIONARY_ID_VALUE_MAP);

		writeMalariaResults(malairaBucket, "Recherche de plasmodiun - Especes", MALARIA_DICTIONARY_ID_VALUE_MAP);
		writeMalariaResults(rapidBucket, "Malaria Test Rapide", RAPID_DICTIONARY_ID_VALUE_MAP);
		buffer.append("</report>\n");
	}

	private void writeMalariaResults(Map<String, Integer> bucket, String name, Map<String, String> idValueMap) {
		buffer.append("<indicator name='");
		buffer.append(name);
		buffer.append("' >\n");
		for (String id : bucket.keySet()) {
			buffer.append("\t<result name='");
			buffer.append(idValueMap.get(id));
			buffer.append("' count='");
			buffer.append(bucket.get(id));
			buffer.append("' />\n");
		}
		buffer.append("</indicator>\n");
	}

	private void fillMalariaBuckets(Timestamp dayOne, Timestamp dayTwo, List<String> testNames, Map<String, Integer> bucket,
			Map<String, String> idValueMap) {

		java.sql.Date sqlDayOne = DateUtil.convertTimestampToSqlDate(dayOne);
		java.sql.Date sqlDayTwo = DateUtil.convertTimestampToSqlDate(dayTwo);
		List<Analysis> analysisList = analysisDAO.getAnalysisByTestDescriptionAndCompletedDateRange(testNames, sqlDayOne , sqlDayTwo);

		for (String key : idValueMap.keySet()) {
			bucket.put(key, 0);
		}

		List<Integer> analysisIdList = new ArrayList<Integer>();
		for (Analysis analysis : analysisList) {
			if (FINISHED_STATUS_ID.equals(analysis.getStatusId())) {
				analysisIdList.add(Integer.parseInt(analysis.getId()));
			}
		}
		List<Result> results = resultDAO.getResultsForAnalysisIdList(analysisIdList);

		if (results != null) {
			for (Result result : results) {
				if (result.getValue() != null) {
					Integer count = bucket.get(result.getValue());

					if (count != null) {
						count++;
						bucket.put(result.getValue(), count);
					}
				}
			}
		}
	}

	private Timestamp getLatestCollectionDate() {
		return cronSchedulerDAO.getCronScheduleByJobName(CRON_MALARIA_SCHEDULER).getLastRun();
	}
	
	class ResponseHandler implements ITransmissionResponseHandler {
		private Timestamp runTime;
		
		public void setRunTime(Timestamp runTime){
			this.runTime = runTime;
		}
		
		@Override
		public void handleResponse(int httpReturnStatus, List<String> errors, String msg) {
			if( httpReturnStatus == HttpServletResponse.SC_OK){
				
			}
			switch (httpReturnStatus) {
			case HttpServletResponse.SC_OK:
				setJobTimestamp(runTime);
				break;
			default:
				System.out.println(errors );
			}
		}
		
		private void setJobTimestamp(Timestamp nowTimestamp) {
			CronScheduler gatherScheduler = cronSchedulerDAO.getCronScheduleByJobName(CRON_MALARIA_SCHEDULER);
			gatherScheduler.setLastRun(nowTimestamp);
			gatherScheduler.setSysUserId("1");

			Transaction tx = HibernateUtil.getSession().beginTransaction();

			try {
				cronSchedulerDAO.update(gatherScheduler);
				tx.commit();
			} catch (HibernateException e) {
				LogEvent.logError("AggregateGatherJob", "execute", e.toString());
			}
		}
		
	}
	
}
