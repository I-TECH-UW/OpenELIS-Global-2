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

import javax.annotation.PostConstruct;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.analysis.AnalysisService;
import spring.service.dataexchange.aggregatereporting.ReportExternalExportService;
import spring.service.dataexchange.aggregatereporting.ReportQueueTypeService;
import spring.service.test.TestServiceImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportExternalExport;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportQueueType;

@Service
public class TestUsageBacklogImpl extends Thread implements TestUsageBacklog {

	@Autowired
	private ReportExternalExportService reportExternalExportService;
	@Autowired
	private AnalysisService analysisService;
	@Autowired
	private ReportQueueTypeService reportQueueTypeService;

	private static String TEST_UTALIZATION_ID;

	@PostConstruct
	public void initializeGlobalVariables() {
		ReportQueueType queueType = reportQueueTypeService.getReportQueueTypeByName("labIndicator");

		if (queueType != null) {
			TEST_UTALIZATION_ID = queueType.getId();
		}
	}

	@Override
	@Transactional
	public void run() {
		if (ConfigurationProperties.getInstance().isPropertyValueEqual(Property.testUsageReporting, "true")) {
			handleBacklog();
		}
	}

	private void handleBacklog() {
		System.out.println("Gathering triggered: " + DateUtil.getCurrentDateAsText("dd-MM-yyyy hh:mm"));
		LogEvent.logInfo("TestUsagebacklog", "handleBacklog",
				"Gathering triggered: " + DateUtil.getCurrentDateAsText("dd-MM-yyyy hh:mm"));

		Timestamp latestCollectionDate = getLatestCollectionDate();

		if (latestCollectionDate == null) {
			for (int i = 120; i >= 0; i--) {
				writeReportForDayPeriod(i);
			}
		} else {
			int daysInPast = DateUtil.getDaysInPastForDate(latestCollectionDate) - 1;
			for (int i = daysInPast; i >= 0; i--) {
				writeReportForDayPeriod(i);
			}
		}

	}

	private Timestamp getLatestCollectionDate() {
		ReportExternalExport report = reportExternalExportService.getLatestEventReportExport(TEST_UTALIZATION_ID);
		if (report != null) {
			return report.getEventDate();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private void writeReportForDayPeriod(int daysAgo) {
		Timestamp dayOne = DateUtil.getTimestampAtMidnightForDaysAgo(daysAgo);
		Timestamp dayTwo = DateUtil.getTimestampAtMidnightForDaysAgo(daysAgo - 1);

		List<Analysis> analysisList = analysisService.getAnalysisCompleteInRange(dayOne, dayTwo);

		Map<String, Integer> testBucket = new HashMap<>();
		for (String key : TestServiceImpl.getMap(TestServiceImpl.Entity.TEST_AUGMENTED_NAME).keySet()) {
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
			if (testBucket.get(id).intValue() > 0) {
				json.put(TestServiceImpl.getMap(TestServiceImpl.Entity.TEST_AUGMENTED_NAME).get(id),
						testBucket.get(id));
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

		report = reportExternalExportService.getReportByEventDateAndType(report);
		report.setSend(true);
		report.setData(jsonText); // buffer.toString());
		report.setCollectionDate(DateUtil.getNowAsTimestamp());
		report.setSysUserId("1");

		try {
			if (report.getId() == null) {
				reportExternalExportService.insert(report);
			} else {
				reportExternalExportService.insert(report);
			}

		} catch (LIMSRuntimeException e) {
			LogEvent.logErrorStack(this.getClass().getSimpleName(), "writeReportForDayPeriod()", e);
			throw e;
		}
	}
}
