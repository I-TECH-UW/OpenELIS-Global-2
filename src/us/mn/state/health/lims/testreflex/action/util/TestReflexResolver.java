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
 * Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
 *
 * Contributor(s): CIRG, University of Washington, Seattle WA.
 */
package us.mn.state.health.lims.testreflex.action.util;

import java.util.ArrayList;
import java.util.List;

import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.testreflex.dao.TestReflexDAO;
import us.mn.state.health.lims.testreflex.daoimpl.TestReflexDAOImpl;
import us.mn.state.health.lims.testreflex.valueholder.TestReflex;

/*
 * The purpose of this class is to resolve whether a new test should be created for
 * a sample based on the results of previous tests.  There can be the case that more
 * than one test result can trigger a new test or more than one new test is created
 */
public class TestReflexResolver {

	private static TestReflexDAO TEST_REFLEX_DAO = new TestReflexDAOImpl();
	private static AnalysisDAO ANALYSIS_DAO = new AnalysisDAOImpl();
	private static ResultDAO RESULT_DAO = new ResultDAOImpl();

	private Analysis lastValidAnalysis = null;

	public Analysis getLastValidAnalysis() {
		return lastValidAnalysis;
	}


	/*
	 * Gets the test reflex associated with this test. Depends on the analyte,
	 * test result and test. This could return zero or more reflexes. More than
	 * one reflexes will be returned when there is more than one reflex for a
	 * test, analyte and result combo
	 */
	public List<TestReflex> getTestReflexesForResult(Result result) {
		String testResultId = null;
		String testId = null;
		String analyteId = result.getAnalyte() == null ? null : result.getAnalyte().getId();

		if (result.getTestResult() != null) {
			testResultId = result.getTestResult().getId();
			testId = result.getTestResult().getTest() == null ? null : result.getTestResult().getTest().getId();
		}

		List<TestReflex> reflexes = TEST_REFLEX_DAO.getTestReflexsByTestResultAnalyteTest(testResultId, analyteId, testId); 
		return reflexes != null ? reflexes : new ArrayList<TestReflex>();
	}

	
	public ReflexAction getReflexAction(){
		return ReflexActionFactory.getReflexAction();
	}
	
	
	public boolean isSatisfied(TestReflex reflex, Sample sample) {

		List<Analysis> analysisList = ANALYSIS_DAO.getAnalysesBySampleId(sample.getId());

		for (Analysis analysis : analysisList) {
			if (!StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalRejected).equals(analysis.getStatusId())) {
				List<Result> resultList = RESULT_DAO.getResultsByAnalysis(analysis);

				for (Result result : resultList) {
					if (result.getTestResult() != null
                            && reflex.getTestResultId().equals(result.getTestResult().getId())
                            && result.getAnalyte() != null
							&& reflex.getTestAnalyte().getAnalyte().getId().equals(result.getAnalyte().getId())
							&& reflex.getTestId().equals(analysis.getTest().getId())) {

						lastValidAnalysis = analysis;
						return true;
					}

				}
			}
		}

		lastValidAnalysis = null;
		return false;
	}

}
