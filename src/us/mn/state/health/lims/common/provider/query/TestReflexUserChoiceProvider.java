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
package us.mn.state.health.lims.common.provider.query;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.servlet.validation.AjaxServlet;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testreflex.action.util.TestReflexUtil;
import us.mn.state.health.lims.testreflex.dao.TestReflexDAO;
import us.mn.state.health.lims.testreflex.daoimpl.TestReflexDAOImpl;
import us.mn.state.health.lims.testreflex.valueholder.TestReflex;
import us.mn.state.health.lims.testresult.dao.TestResultDAO;
import us.mn.state.health.lims.testresult.daoimpl.TestResultDAOImpl;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

public class TestReflexUserChoiceProvider extends BaseQueryProvider {

    private static final String ID_SEPERATOR = ",";
	protected AjaxServlet ajaxServlet = null;
    private static final AnalysisDAO ANALYSIS_DAO = new AnalysisDAOImpl();
    private static final TestReflexDAO TEST_REFLEX_DAO = new TestReflexDAOImpl();
    private static final TestResultDAO TEST_RESULT_DAO = new TestResultDAOImpl();
    private static final ResultDAO RESULT_DAO = new ResultDAOImpl();

    @Override
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String resultIds = request.getParameter("resultIds");
		String analysisIds = request.getParameter("analysisIds");
		String testIds = request.getParameter("testIds");
		String rowIndex = request.getParameter("rowIndex");
		String accessionNumber = request.getParameter("accessionNumber");

        String jResult;
        JSONObject jsonResult =new JSONObject();
        String jString;
		if (GenericValidator.isBlankOrNull(resultIds) || 
				GenericValidator.isBlankOrNull(testIds) || 
				GenericValidator.isBlankOrNull(rowIndex) ||
				(GenericValidator.isBlankOrNull(analysisIds) && GenericValidator.isBlankOrNull(accessionNumber))) {
			jResult = INVALID;
			jString = "Internal error, please contact Admin and file bug report";
		} else {
            jResult = createJsonTestReflex( resultIds, analysisIds, testIds, accessionNumber, rowIndex, jsonResult );
            StringWriter out = new StringWriter();
            try{
                jsonResult.writeJSONString( out );
                jString = out.toString();
            }catch( IOException e ){
                e.printStackTrace();
                jResult = INVALID;
                jString = "Internal error, please contact Admin and file bug report";
            }
        }
		ajaxServlet.sendData(jString, jResult, request, response);

	}

    private String createJsonTestReflex( String resultIds, String analysisIds, String testIds, String accessionNumber, String rowIndex, JSONObject jsonResult ){

        TestReflexUtil reflexUtil = new TestReflexUtil();
        String[] resultIdSeries = resultIds.split(ID_SEPERATOR);

		/*
		 * Here's the deal. If the UC test reflex has both an add_test_id and a
		 * scriptlet_id then we are done. If it has only one Then we need to
		 * look for the other
		 */
        ArrayList<TestReflex> selectableReflexes = new ArrayList<TestReflex>( );
        HashSet<String> reflexTriggers = new HashSet<String>(  );
        HashSet<String> reflexTriggerIds = new HashSet<String>(  );
        // Both test given results on client
        if (resultIdSeries.length > 1) {
/*			String[] testIdSeries = testIds.split(ID_SEPERATOR);

			List<TestReflex> testReflexesForResultOne = reflexUtil.getTestReflexsForDictioanryResultTestId(resultIdSeries[0],
					testIdSeries[0], true);

			if (!testReflexesForResultOne.isEmpty()) {
				List<TestReflex> sibTestReflexList = reflexUtil.getTestReflexsForDictioanryResultTestId(resultIdSeries[1],
						testIdSeries[1], true);

				boolean allChoicesFound = false;
				for (TestReflex reflexFromResultOne : testReflexesForResultOne) {
					for (TestReflex sibReflex : sibTestReflexList) {
						if (areSibs(reflexFromResultOne, sibReflex)
								&& TestReflexUtil.isUserChoiceReflex( reflexFromResultOne )) {
							if (reflexFromResultOne.getActionScriptlet() != null) {
								testReflexOne = reflexFromResultOne;
								allChoicesFound = true;
								break;
							} else if (testReflexOne == null) {
								testReflexOne = reflexFromResultOne;
							} else {
								testReflexTwo = reflexFromResultOne;
								allChoicesFound = true;
								break;
							}
						}
						if (allChoicesFound) {
							break;
						}
					}
				}
			}
*/			// One test given results on client, the other is in the DB
        } else {
            // for each reflex we are going to try and find a sibling reflex
            // which is currently satisfied
            // get their common sample

            Sample sample = getSampleForKnownTest(analysisIds, accessionNumber, ANALYSIS_DAO );
            Dictionary dictionaryResult = new DictionaryDAOImpl().getDictionaryById( resultIds );

            List<Analysis> analysisList = ANALYSIS_DAO.getAnalysesBySampleId( sample.getId() );

            List<TestReflex> candidateReflexList = reflexUtil.getTestReflexsForDictioanryResultTestId(resultIds, testIds, true);

            for (TestReflex candidateReflex : candidateReflexList) {
                if (TestReflexUtil.isUserChoiceReflex( candidateReflex )) {
                    if (GenericValidator.isBlankOrNull( candidateReflex.getSiblingReflexId() )) {
                        selectableReflexes.add( candidateReflex );
                        reflexTriggers.add( TestService.getUserLocalizedTestName( candidateReflex.getTest() ) + ":" + dictionaryResult.getDictEntry() );
          //              reflexTriggerIds.add( candidateReflex.getTest().getId() );
                    } else {
                        // find if the sibling reflex is satisfied
                        TestReflex sibTestReflex = new TestReflex();
                        sibTestReflex.setId(candidateReflex.getSiblingReflexId() );

                        TEST_REFLEX_DAO.getData( sibTestReflex );

                        TestResult sibTestResult = new TestResult();
                        sibTestResult.setId(sibTestReflex.getTestResultId());
                        TEST_RESULT_DAO.getData( sibTestResult );

                        for (Analysis analysis : analysisList) {
                            List<Result> resultList = RESULT_DAO.getResultsByAnalysis( analysis );
                            Test test = analysis.getTest();

                            for (Result result : resultList) {
                                TestResult testResult = TEST_RESULT_DAO.getTestResultsByTestAndDictonaryResult( test.getId(),
                                        result.getValue() );
                                if (testResult != null && testResult.getId().equals(sibTestReflex.getTestResultId())) {
                                    selectableReflexes.add(candidateReflex);
                                    reflexTriggers.add( TestService.getUserLocalizedTestName( candidateReflex.getTest() ) + ":" + dictionaryResult.getDictEntry() );
                       //             reflexTriggerIds.add( candidateReflex.getTest().getId() );
                                }
                            }
                        }
                    }
                }
            }


        }

        if (  selectableReflexes.size() > 1) {
            jsonResult.put( "rowIndex", rowIndex );
            createTriggerList( reflexTriggers, resultIds, jsonResult);
            createChoiceElement(selectableReflexes, jsonResult);
            createPlaceholderForSelectedReflexes(jsonResult) ;
            return VALID;
        }

        return INVALID;
    }

    private void createPlaceholderForSelectedReflexes( JSONObject jsonResult ){
        jsonResult.put( "selected", new JSONArray());
    }

    private void createTriggerList( HashSet<String> reflexTriggers, String reflexTriggerIds, JSONObject jsonResult ){
        StringBuilder triggers = new StringBuilder( );
        for( String trigger : reflexTriggers){
            triggers.append( trigger );
            triggers.append( "," );
        }
        jsonResult.put( "triggers", triggers.deleteCharAt( triggers.length() -1 ).toString() );

        triggers = new StringBuilder( );

        String[] sortedTriggerIds = reflexTriggerIds.split( "," );
        Arrays.sort( sortedTriggerIds);

        for( String trigger : sortedTriggerIds){
            triggers.append( trigger.trim() );
            triggers.append( "_" );
        }
        jsonResult.put( "triggerIds", triggers.deleteCharAt( triggers.length() -1 ).toString() );
    }

    private Sample getSampleForKnownTest(String analysisIds, String accessionNumber, AnalysisDAO analysisDAO) {
		//We use the analysisId for logbook results and accessionNumber for analysis results, we should accessionNumber for both.
		if( GenericValidator.isBlankOrNull(analysisIds)){
			return new SampleDAOImpl().getSampleByAccessionNumber(accessionNumber);
		}else{
			Analysis knownAnalysis = new Analysis();
			knownAnalysis.setId(analysisIds);
			analysisDAO.getData(knownAnalysis);

			return knownAnalysis.getSampleItem().getSample();
		}
	}

	private boolean areSibs(TestReflex testReflex, TestReflex sibTestReflex) {
		return !GenericValidator.isBlankOrNull(testReflex.getSiblingReflexId())
				&& !GenericValidator.isBlankOrNull(sibTestReflex.getSiblingReflexId())
				&& testReflex.getSiblingReflexId().equals(sibTestReflex.getId())
				&& sibTestReflex.getSiblingReflexId().equals(testReflex.getId());

	}

    private void createChoiceElement(List<TestReflex> reflexList, JSONObject jsonResult) {
        JSONArray jsonArray = new JSONArray();
        for(TestReflex reflex : reflexList){
            if( reflex.getActionScriptlet() != null){
                JSONObject selectionObject = new JSONObject();
                selectionObject.put( "name", TestReflexUtil.makeReflexScriptName( reflex ) );
                selectionObject.put( "value", TestReflexUtil.makeReflexScriptValue( reflex ));
                jsonArray.add( selectionObject );
            }

            if(reflex.getAddedTest() != null){
                JSONObject selectionObject = new JSONObject();
                selectionObject.put( "name", TestReflexUtil.makeReflexTestName( reflex ) );
                selectionObject.put( "value", TestReflexUtil.makeReflexTestValue( reflex ));
                jsonArray.add( selectionObject );
            }
        }

        jsonResult.put( "selections", jsonArray );
    }
	@Override
	public void setServlet(AjaxServlet as) {
		this.ajaxServlet = as;
	}

	@Override
	public AjaxServlet getServlet() {
		return this.ajaxServlet;
	}

}
