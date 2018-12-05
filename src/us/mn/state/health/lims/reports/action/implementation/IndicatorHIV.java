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
package us.mn.state.health.lims.reports.action.implementation;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.analyte.dao.AnalyteDAO;
import us.mn.state.health.lims.analyte.daoimpl.AnalyteDAOImpl;
import us.mn.state.health.lims.analyte.valueholder.Analyte;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.HaitiHIVSummaryData;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;

public class IndicatorHIV extends IndicatorReport implements IReportCreator, IReportParameterSetter {

	private static final long INFANT_TIME = 1000L * 60L * 60L * 24L * 28L;
	protected static final String HIV = "VCT";
	protected static String HIV_POSITIVE_ID;
	protected static String HIV_NEGATIVE_ID;
	protected static String HIV_INDETERMINATE_ID;
	protected static String TEST_HIV_POS_ID;
	protected static String TEST_HIV_NEG_ID;
	protected static String TEST_HIV_IND_ID;
	protected static String CLINICAL_POSITIVE_ID;
	protected static String CLINICAL_NEGATIVE_ID;
	protected static String ANALYTE_CONCULSION_ID;
	protected List<HaitiHIVSummaryData> testData;
	protected List<Analysis> analysisList;
	protected static List<String> HIV_TESTS;

	static {

		DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
		Dictionary dictionary = dictionaryDAO.getDictionaryEntrysByNameAndCategoryDescription("Positive", "Conclusion");
		if (dictionary != null) {
			HIV_POSITIVE_ID = dictionary.getId();
		}

		dictionary = dictionaryDAO.getDictionaryEntrysByNameAndCategoryDescription("Negative", "Conclusion");
		if (dictionary != null) {
			HIV_NEGATIVE_ID = dictionary.getId();
		}

		dictionary = dictionaryDAO.getDictionaryEntrysByNameAndCategoryDescription("Indeterminate", "Conclusion");
		if (dictionary != null) {
			HIV_INDETERMINATE_ID = dictionary.getId();
		}

		dictionary = dictionaryDAO.getDictionaryEntrysByNameAndCategoryDescription("POSITIF", "Haiti Lab");
		if (dictionary != null) {
			TEST_HIV_POS_ID = dictionary.getId();
		}else{
		
			dictionary = dictionaryDAO.getDictionaryEntrysByNameAndCategoryDescription("Positif", "Haiti Lab");
			if (dictionary != null) {
				TEST_HIV_POS_ID = dictionary.getId();
			}
		}

		dictionary = dictionaryDAO.getDictionaryEntrysByNameAndCategoryDescription("NEGATIF", "Haiti Lab");
		if (dictionary != null) {
			TEST_HIV_NEG_ID = dictionary.getId();
		}else{
			dictionary = dictionaryDAO.getDictionaryEntrysByNameAndCategoryDescription("Negatif", "Haiti Lab");
			if (dictionary != null) {
				TEST_HIV_NEG_ID = dictionary.getId();
			}	
		}

		dictionary = dictionaryDAO.getDictionaryEntrysByNameAndCategoryDescription("INDETERMINE", "Haiti Lab");
		if (dictionary != null) {
			TEST_HIV_IND_ID = dictionary.getId();
		}else{
			dictionary = dictionaryDAO.getDictionaryEntrysByNameAndCategoryDescription("Indeterminé", "Haiti Lab");
			if (dictionary != null) {
				TEST_HIV_IND_ID = dictionary.getId();
			}	
		}

		dictionary = dictionaryDAO.getDictionaryEntrysByNameAndCategoryDescription("Positif", "CLINICAL GENERAL");
		if (dictionary != null) {
			CLINICAL_POSITIVE_ID = dictionary.getId();
		}

		dictionary = dictionaryDAO.getDictionaryEntrysByNameAndCategoryDescription("Négatif", "CLINICAL GENERAL");
		if (dictionary != null) {
			CLINICAL_NEGATIVE_ID = dictionary.getId();
		}

		HIV_TESTS = new ArrayList<String>();
		HIV_TESTS.add("CD4 en mm3");
		HIV_TESTS.add("CD4 en %");
		HIV_TESTS.add("Colloidal Gold / Shangai Kehua");
		HIV_TESTS.add("Determine");
		HIV_TESTS.add("HIV test rapide");
		HIV_TESTS.add("VIH Test - Oraquick");
		HIV_TESTS.add("Test de VIH");
		HIV_TESTS.add("Test rapide HIV 1 + HIV 2");
		HIV_TESTS.add("Dénombrement des lymphocytes CD4 (mm3)");
		HIV_TESTS.add("Dénombrement des lymphocytes  CD4 (%)");
		

		AnalyteDAO analyteDAO = new AnalyteDAOImpl();
		Analyte analyte = new Analyte();
		analyte.setAnalyteName("Conclusion");
		analyte = analyteDAO.getAnalyteByName(analyte, false);
		ANALYTE_CONCULSION_ID = analyte.getId();
	}

	@Override
	protected String reportFileName() {
		return "HIVSummary";
	}

	public JRDataSource getReportDataSource() throws IllegalStateException {

		return errorFound ? new JRBeanCollectionDataSource(errorMsgs) : new JRBeanCollectionDataSource(testData);
	}

	public void initializeReport(BaseActionForm dynaForm) {
		super.initializeReport();
		setDateRange(dynaForm);

		findAnalysis();

		setReportParameters();

		setHIVByTest();
	}

	protected void findAnalysis() {
		AnalysisDAO analysisDAO = new AnalysisDAOImpl();
		analysisList = analysisDAO.getAnalysisByTestNamesAndCompletedDateRange(HIV_TESTS, lowDate, highDate);
	}

	protected void setHIVByTest() {
		testData = new ArrayList<HaitiHIVSummaryData>();

		ResultDAO resultDAO = new ResultDAOImpl();
		Map<String, TestBucket> testBuckets = new HashMap<String, TestBucket>();

		for (Analysis analysis : analysisList) {

			String testName = TestService.getUserLocalizedTestName( analysis.getTest() );

			TestBucket bucket = testBuckets.get(testName);

			if (bucket == null) {
				bucket = new TestBucket(testName);
				testBuckets.put(testName, bucket);
			}

			List<Result> resultList = resultDAO.getResultsByAnalysis(analysis);
			
			if(  resultList.isEmpty()){
				continue;
			}
			
			Result firstResult = resultList.get(0);
		
			if( testName.equals("CD4 en mm3") ||
				testName.equals("CD4 en %") ||
				testName.equals("CD4  Compte Abs") ||
				testName.equals("CD4 Compte en %") ||
				testName.equals("Dénombrement des lymphocytes CD4 (mm3)") ||
				testName.equals("Dénombrement des lymphocytes  CD4 (%)") ){
				if( firstResult.getMinNormal() == firstResult.getMaxNormal() ){
					continue;
				}
				
				try {
					Double value = Double.valueOf(firstResult.getValue());	
				
					if( value >= firstResult.getMinNormal() &&
							value <= firstResult.getMaxNormal()){
						bucket.negative++;
					}else{
						bucket.positive++;
					}
				} catch (NumberFormatException e) {
					continue;
				}
				
			}else if (testName.equals("Colloidal Gold / Shangai Kehua") || 
					  testName.equals("Determine") ||
					  testName.equals("HIV test rapide") ||
					  testName.equals("Test Rapide VIH") ||
					  testName.equals("Test rapide HIV 1 + HIV 2")) {
				String value = firstResult.getValue();
				if( isPositive(value)){
					bucket.positive++;
				}else if( TEST_HIV_NEG_ID.equals(value)){
					bucket.negative++;
				}else if(isIndeterminate(value)){
					bucket.indeterminate++;
				}
				
			} else if (testName.equals("Test de VIH")) {
				Result result = resultList.get(0);
				String analyteName = result.getAnalyte() == null ? "" : result.getAnalyte().getAnalyteName();

				if ("Result 1".equals(analyteName)) {
					if (TEST_HIV_NEG_ID.equals(result.getValue())) {
						bucket.negative++;
					} else {
						bucket.pending++;
					}
				} else {
					bucket.pending--;
					if (TEST_HIV_NEG_ID.equals(result.getValue())) {
						bucket.negative++;
					} else if (TEST_HIV_POS_ID.equals(result.getValue())) {
						bucket.positive++;
					}
					if (TEST_HIV_IND_ID.equals(result.getValue())) {
						bucket.indeterminate++;
					}
				}
			} else if (testName.equals("Oraquick")) {
				Result result = resultList.get(0);
				if (CLINICAL_POSITIVE_ID.equals(result.getValue())) {
					bucket.positive++;
				} else if (CLINICAL_NEGATIVE_ID.equals(result.getValue())) {
					bucket.negative++;
				}
			}
		}

		for (TestBucket testBucket : testBuckets.values()) {

			HaitiHIVSummaryData data = new HaitiHIVSummaryData();
			double total = testBucket.pending + testBucket.indeterminate + testBucket.positive + testBucket.negative;
			data.setPending(testBucket.pending);
			data.setIndeterminate(testBucket.indeterminate);
			data.setPositive(testBucket.positive);
			data.setNegative(testBucket.negative);
			data.setTestName(testBucket.testName);
			data.setTotal((int) total);

			if (total > 0) {
				data.setPendingPer(percentage(total, testBucket.pending));
				data.setIndeterminatePer(percentage(total, testBucket.indeterminate));
				data.setPositivePer(percentage(total, testBucket.positive));
				data.setNegativePer(percentage(total, testBucket.negative));
			}

			testData.add(data);
		}
	}

	protected boolean isIndeterminate(String value) {
		return TEST_HIV_IND_ID.equals(value);
	}

	protected boolean isPositive(String value) {
		return TEST_HIV_POS_ID.equals(value);
	}

	protected double percentage(double total, int value) {
		return value == 0 ? 0.0 : ((int) ((double) value / total * 10000.0)) / 100.0;
	}

	protected void setReportParameters() {
		super.createReportParameters();

		SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
		Set<String> patientSet = new HashSet<String>();
		List<PatientTestDate> patientTestList = new ArrayList<PatientTestDate>();

		for (Analysis analysis : analysisList) {
			Patient patient = sampleHumanDAO.getPatientForSample(analysis.getSampleItem().getSample());
			if (!patientSet.contains(patient.getId())) {
				patientSet.add(patient.getId());
				patientTestList.add(new PatientTestDate(patient, analysis.getCompletedDate()));
			}
		}
		// This is dependent on the outcome of the results
		int male = 0;
		int female = 0;
		int infant = 0;

		for (PatientTestDate patientTestDate : patientTestList) {
			if ("M".equals(patientTestDate.patient.getGender())) {
				male++;
			} else {
				female++;
			}

			if (patientIsEnfant(patientTestDate)) {
				infant++;
			}
		}

		reportParameters.put("male", String.valueOf(male));
		reportParameters.put("female", String.valueOf(female));
		reportParameters.put("infant", String.valueOf(infant));
		reportParameters.put("populationTotal", String.valueOf(patientSet.size()));
	}

	private boolean patientIsEnfant(PatientTestDate patientTestDate) {
		if (patientTestDate.patient == null || patientTestDate.patient.getBirthDate() == null) {
			return false;
		}

		long birthDate = patientTestDate.patient.getBirthDate().getTime();
		long testDate = patientTestDate.testDate.getTime();

		return (testDate - birthDate) <= INFANT_TIME;
	}

	private class PatientTestDate {
		public Patient patient;
		public Date testDate;

		public PatientTestDate(Patient patient, Date testDate) {
			this.patient = patient;
			this.testDate = testDate;
		}
	}

	class TestBucket {
		public String testName;
		public int positive = 0;
		public int negative = 0;
		public int indeterminate = 0;
		public int pending = 0;

		public TestBucket(String name) {
			testName = name;
		}
	}

	@Override
	protected String getNameForReportRequest() {
		return StringUtil.getContextualMessageForKey("openreports.hiv.aggregate");
	}

	@Override
	protected String getNameForReport() {
		return StringUtil.getMessageForKey("openreports.hiv.aggregate");
	}

	@Override
	protected String getLabNameLine1() {
		return ConfigurationProperties.getInstance().getPropertyValue(Property.SiteName);
	}

	@Override
	protected String getLabNameLine2() {
		return "";
	}
}
