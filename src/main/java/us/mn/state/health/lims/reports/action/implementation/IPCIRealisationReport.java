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

/**
 * This file is the result of the Capstone project five for the Cote d'Ivoire OpenElis software developer course
 * made by Kone Constant
 * 
 *
 */



package us.mn.state.health.lims.reports.action.implementation;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.validator.GenericValidator;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.ErrorMessages;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.IPCIRealisationTest;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.daoimpl.TestSectionDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;

public class  IPCIRealisationReport  extends Report {
	
	protected List<IPCIRealisationTest> reportItems;
	
	protected String lowerDateRange;
	protected String upperDateRange;
	protected Date lowDate;
	protected Date highDate;

	private HashMap<String, TestBucket> testIdToBucketList;

	private HashMap<String, TestBucket> concatSection_TestToBucketMap;

	private ArrayList<TestBucket> testBucketList;
	
	private static final String NOT_STARTED_STATUS_ID;
	private static final String FINALIZED_STATUS_ID;
	private static final String TECH_ACCEPT_ID;
	private static final String TECH_REJECT_ID;
	private static final String BIOLOGIST_REJECT_ID;
	private static final String USER_TEST_SECTION_ID;
	
	static {
		NOT_STARTED_STATUS_ID = StatusService.getInstance().getStatusID(AnalysisStatus.NotStarted);
		FINALIZED_STATUS_ID = StatusService.getInstance().getStatusID(AnalysisStatus.Finalized);
		TECH_ACCEPT_ID = StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance);
		TECH_REJECT_ID = StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalRejected);
		BIOLOGIST_REJECT_ID = StatusService.getInstance().getStatusID(AnalysisStatus.BiologistRejected);
		USER_TEST_SECTION_ID = new TestSectionDAOImpl().getTestSectionByName("user").getId();
	}

	
	@Override
	public void initializeReport(BaseActionForm dynaForm) {
		super.initializeReport();
		errorFound = false;	
		
		lowerDateRange = dynaForm.getString("lowerDateRange");		   
		upperDateRange = dynaForm.getString("upperDateRange");
		  

		if (GenericValidator.isBlankOrNull(lowerDateRange)) {
			errorFound = true;
			ErrorMessages msgs = new ErrorMessages();
			msgs.setMsgLine1(StringUtil.getMessageForKey("report.error.message.noPrintableItems"));
			errorMsgs.add(msgs);
		}

		if (GenericValidator.isBlankOrNull(upperDateRange)) {
			upperDateRange = lowerDateRange;
		}

		try {
			lowDate = DateUtil.convertStringDateToSqlDate(lowerDateRange);
			highDate = DateUtil.convertStringDateToSqlDate(upperDateRange);
		} catch (LIMSRuntimeException re) {
			errorFound = true;
			ErrorMessages msgs = new ErrorMessages();
			msgs.setMsgLine1(StringUtil.getMessageForKey("report.error.message.date.format"));
			errorMsgs.add(msgs);
		}
		
		createReportParameters();
		
		initializeReportItems();
		 					
		setTestMapForAllTests();
		
		setAnalysisForDateRange();
		
		setTestAggregates();
		 
		 
		 
	}
	
	protected void initializeReportItems() {
		reportItems = new ArrayList<IPCIRealisationTest>();
	}
	
	private void setTestMapForAllTests() {
		testIdToBucketList = new HashMap<String, TestBucket>();
		concatSection_TestToBucketMap = new HashMap<String, TestBucket>();
		testBucketList = new ArrayList<TestBucket>();

		TestDAO testDAO = new TestDAOImpl();
		List<Test> testList = testDAO.getAllActiveTests(false);

		for (Test test : testList) {
			TestBucket bucket = new TestBucket();
             			   						 
			bucket.testName = TestService.getUserLocalizedTestName( test );
			bucket.testSection = test.getTestSection().getLocalizedName();
			
			testIdToBucketList.put(test.getId(), bucket);
			testBucketList.add(bucket);
			
		}

	}
	
	private void setTestAggregates() {
		reportItems = new ArrayList<IPCIRealisationTest>();
		for (TestBucket bucket : testBucketList) {
			if ((bucket.finishedCount + bucket.notStartedCount + bucket.inProgressCount) > 0) {
		
				IPCIRealisationTest data = new IPCIRealisationTest();

				data.setPerformed(bucket.finishedCount);
				data.setRequired(bucket.notStartedCount +  bucket.inProgressCount + bucket.finishedCount);
				 
				data.setTestName(bucket.testName);
				data.setSectionName(bucket.testSection);
				data.setNoPerformed(data.getRequired() - data.getPerformed());
				reportItems.add(data);
			}
		}
	}
	
	private void setAnalysisForDateRange() {
		AnalysisDAO analysisDAO = new AnalysisDAOImpl();
		List<Analysis> analysisList = analysisDAO.getAnalysisStartedOrCompletedInDateRange(lowDate, highDate);

		for (Analysis analysis : analysisList) {
			Test test = analysis.getTest();

			if (test != null) {
				TestBucket testBucket = null;
				if (USER_TEST_SECTION_ID.equals(analysis.getTestSection().getId())) {
					String concatedName = analysis.getTestSection().getLocalizedName()
							+ TestService.getUserLocalizedTestName( analysis.getTest() );
					testBucket = concatSection_TestToBucketMap.get(concatedName);
					if (testBucket == null) {
						testBucket = new TestBucket();
						testBucket.testName = TestService.getUserLocalizedReportingTestName( test );
						testBucket.testSection = analysis.getTestSection().getLocalizedName();
						concatSection_TestToBucketMap.put(concatedName, testBucket);
					}
				} else {
					testBucket = testIdToBucketList.get(test.getId());
				}

				if (testBucket != null) {
					if (NOT_STARTED_STATUS_ID.equals(analysis.getStatusId())) {
						testBucket.notStartedCount++;
					} else if (inProgress(analysis)) {
						testBucket.inProgressCount++;
					} else if (FINALIZED_STATUS_ID.equals(analysis.getStatusId())) {
						testBucket.finishedCount++;
					}
				}
			}
		}
	}
	
	

	private boolean inProgress(Analysis analysis) {
		return TECH_ACCEPT_ID.equals(analysis.getStatusId()) ||
			   TECH_REJECT_ID.equals(analysis.getStatusId()) ||
			   BIOLOGIST_REJECT_ID.equals(analysis.getStatusId());
	}
	
	@Override
	protected void createReportParameters() {
		super.createReportParameters();

		reportParameters.put("startDate", lowerDateRange);
		reportParameters.put("stopDate", upperDateRange);
		reportParameters.put("date_debut", lowerDateRange);
		reportParameters.put("date_fin", upperDateRange);

	}

	@Override
	public JRDataSource getReportDataSource() throws IllegalStateException {
		return errorFound ? new JRBeanCollectionDataSource(errorMsgs)
		: new JRBeanCollectionDataSource(reportItems);
	}
 
	@Override
	protected String reportFileName() {
		 
		return "IPCIRealisationTest";
	}   
	
	private class TestBucket {
		public String testName = "";
		public String testSection = "";
		public int notStartedCount = 0;
		public int inProgressCount = 0;
		public int finishedCount = 0;
	}

}
