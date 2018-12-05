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
package us.mn.state.health.lims.testreflex.action.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.analyte.dao.AnalyteDAO;
import us.mn.state.health.lims.analyte.daoimpl.AnalyteDAOImpl;
import us.mn.state.health.lims.analyte.valueholder.Analyte;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory.ValueType;
import us.mn.state.health.lims.observationhistorytype.dao.ObservationHistoryTypeDAO;
import us.mn.state.health.lims.observationhistorytype.daoImpl.ObservationHistoryTypeDAOImpl;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.scriptlet.valueholder.Scriptlet;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testresult.dao.TestResultDAO;
import us.mn.state.health.lims.testresult.daoimpl.TestResultDAOImpl;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

public class RetroCIReflexActions extends ReflexAction {

    private static TestDAO testDAO = new TestDAOImpl();
	private static final String HIV_D = "HIV D";
	private static final String HIV_2 = "HIV 2";
	private static final String HIV_1 = "HIV 1";
	private static final String HIV_N = "HIV N";
	private static final String HIV_INDETERMINATE = "HIV Indeterminate";
	private static final String HIV_INVALID = "HIV Invalid";
	private static final String HIV_NAME = "hivStatus";
    private static final String VIRONOSTIKA = "Vironostika";
    private static final String INNOLIA = "Innolia";
    private static final String VIRONOSTIKA_OR_INNOLIA = "Vir. or Innolia";
	private static String OBSERVATION_HIV_STATUS_ID;
	private static Analyte ANALYTE_CONCLUSION;
	private static Analyte ANALYTE_CD4_CT_GENERATED;
	private static Analyte ANALYTE_GB_RESULT;
	private static Analyte ANALYTE_LYMPH_PER_RESULT;
	private static Analyte ANALYTE_CD4_PER_RESULT;
	private static TestResult TEST_RESULT_CD4_CALCULATED;

	private static List<Integer> CD4_RESULT_TEST_DEPENDANCIES;
	private static Map<String, String> hivStatusToDictonaryIDMap;

	static {
		ObservationHistoryTypeDAO typeDAO = new ObservationHistoryTypeDAOImpl();
		ObservationHistoryType observationHistoryType = typeDAO.getByName(HIV_NAME);
		OBSERVATION_HIV_STATUS_ID = observationHistoryType == null ? null : observationHistoryType.getId();

		hivStatusToDictonaryIDMap = new HashMap<String, String>();

		DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();

		List<Dictionary> dictionaryList = dictionaryDAO.getDictionaryEntrysByCategoryNameLocalizedSort("HIVResult");

		for (Dictionary dictionary : dictionaryList) {
			if (dictionary.getDictEntry().equals("HIV1")) {
				hivStatusToDictonaryIDMap.put(HIV_1, dictionary.getId());
			} else if (dictionary.getDictEntry().equals("HIV2")) {
				hivStatusToDictonaryIDMap.put(HIV_2, dictionary.getId());
			} else if (dictionary.getDictEntry().equals("HIVD")) {
				hivStatusToDictonaryIDMap.put(HIV_D, dictionary.getId());
			} else if (dictionary.getDictEntry().equals("Negative")) {
				hivStatusToDictonaryIDMap.put(HIV_N, dictionary.getId());
			} else if (dictionary.getDictEntry().equals("Indeterminate")) {
				hivStatusToDictonaryIDMap.put(HIV_INDETERMINATE, dictionary.getId());
			} else if (dictionary.getDictEntry().equals("Invalid")) {
				hivStatusToDictonaryIDMap.put(HIV_INVALID, dictionary.getId());
			}
		}

		AnalyteDAO analyteDAO = new AnalyteDAOImpl();
		Analyte analyte = new Analyte();
		analyte.setAnalyteName("Conclusion");
		ANALYTE_CONCLUSION = analyteDAO.getAnalyteByName(analyte, false);
		analyte.setAnalyteName("CD4 percentage count Result");
		ANALYTE_CD4_PER_RESULT = analyteDAO.getAnalyteByName(analyte, false);
		analyte.setAnalyteName("GB Result");
		ANALYTE_GB_RESULT = analyteDAO.getAnalyteByName(analyte, false);
		analyte.setAnalyteName("Lymph % Result");
		ANALYTE_LYMPH_PER_RESULT = analyteDAO.getAnalyteByName(analyte, false);
		analyte.setAnalyteName("generated CD4 Count");
		ANALYTE_CD4_CT_GENERATED = analyteDAO.getAnalyteByName(analyte, false);

		CD4_RESULT_TEST_DEPENDANCIES = new ArrayList<Integer>();

		Test test = testDAO.getTestByName("GB");
		CD4_RESULT_TEST_DEPENDANCIES.add(Integer.parseInt(test.getId()));
		test = testDAO.getTestByName("Lymph %");
		CD4_RESULT_TEST_DEPENDANCIES.add(Integer.parseInt(test.getId()));
		test = testDAO.getTestByName("CD4 percentage count");
		CD4_RESULT_TEST_DEPENDANCIES.add(Integer.parseInt(test.getId()));
		test = testDAO.getTestByName("CD4 absolute count");

		TestResultDAO testResultDAO = new TestResultDAOImpl();
		@SuppressWarnings("unchecked")
		List<TestResult> resultList = testResultDAO.getAllActiveTestResultsPerTest( test );
		TEST_RESULT_CD4_CALCULATED = resultList.get(0);
	}


	@Override
	protected void handleScriptletAction(Scriptlet scriptlet) {
		if (scriptlet != null && INTERPERET_TYPE.equals(scriptlet.getCodeType())) {
			String action = scriptlet.getCodeSource();

			if (GenericValidator.isBlankOrNull(action)) {
				return;
			}

			if (action.equals(HIV_INDETERMINATE) || action.equals(HIV_N) || action.equals(HIV_1) || action.equals(HIV_2)
					|| action.equals(HIV_D)) {
				addHIVObservation(action);

			} else if (action.equals("Calc CD4")) {
				finalResult = getCD4CalculationResult(result.getAnalysis().getSampleItem().getSample());
			} else if( action.equals(VIRONOSTIKA_OR_INNOLIA)){
                Test test = testDAO.getTestByName(VIRONOSTIKA);
                if( !test.isActive()){
                    test = testDAO.getTestByName(INNOLIA);
                }
                createReflexedAnalysis(test);
            }
		}

	}


	public Result getCD4CalculationResult(Sample sample) {
		Result calculatedResult = null;
		List<Analysis> analysisList = ANALYSIS_DAO.getAnalysisBySampleAndTestIds(sample.getId(), CD4_RESULT_TEST_DEPENDANCIES);

		List<Integer> analysisIDList = new ArrayList<Integer>();
		for (Analysis analysis : analysisList) {
			analysisIDList.add(Integer.parseInt(analysis.getId()));
		}

		ResultDAO resultDAO = new ResultDAOImpl();

		Result CD4Result = resultDAO.getResultForAnalyteInAnalysisSet(ANALYTE_CD4_PER_RESULT.getId(), analysisIDList);
		Result GBResult = resultDAO.getResultForAnalyteInAnalysisSet(ANALYTE_GB_RESULT.getId(), analysisIDList);
		Result LymphResult = resultDAO.getResultForAnalyteInAnalysisSet(ANALYTE_LYMPH_PER_RESULT.getId(), analysisIDList);

		if (CD4Result != null && GBResult != null && LymphResult != null) {
			try {
				double result = Double.parseDouble(CD4Result.getValue()) * 
								Double.parseDouble(GBResult.getValue()) * 
								Double.parseDouble(LymphResult.getValue()) * 0.1;
				result = Math.rint(result);

				calculatedResult = resultDAO.getResultForAnalyteInAnalysisSet(ANALYTE_CD4_CT_GENERATED.getId(), analysisIDList);
				if( calculatedResult == null){
					calculatedResult = new Result();
					calculatedResult.setResultType("N");
					calculatedResult.setIsReportable("T");
					calculatedResult.setTestResult(TEST_RESULT_CD4_CALCULATED);
					calculatedResult.setAnalyte(ANALYTE_CD4_CT_GENERATED);
					calculatedResult.setAnalysis(CD4Result.getAnalysis());
					calculatedResult.setSortOrder( nextSortOrder(CD4Result));
				}
				
				calculatedResult.setValue(String.valueOf(result));
				
			} catch (NumberFormatException e) {
				//no op final result should be null.  Handles the case of "XXXX"
			}
		}
		
		return calculatedResult;
	}

	private String nextSortOrder(Result result) {
		if( result == null || GenericValidator.isBlankOrNull(result.getSortOrder())){
			return "0";
		}

		return String.valueOf(Integer.parseInt(result.getSortOrder()) + 1);
	}

	private void addHIVObservation(String action) {
		observation = new ObservationHistory();
		observation.setValue(hivStatusToDictonaryIDMap.get(action));
		observation.setValueType(ValueType.DICTIONARY);
		observation.setObservationHistoryTypeId(OBSERVATION_HIV_STATUS_ID);

		finalResult = new Result();
		finalResult.setValue(hivStatusToDictonaryIDMap.get(action));
		finalResult.setResultType("D");
		finalResult.setIsReportable("T");
		finalResult.setAnalyte(ANALYTE_CONCLUSION);
	}
}
