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
package us.mn.state.health.lims.dataexchange.malariareporting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.services.IResultSaveService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.registration.interfaces.IResultUpdate;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.dataexchange.resultreporting.ResultReportingCollator;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.result.action.util.ResultSet;
import us.mn.state.health.lims.result.action.util.ResultUtil;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;

public class MalariaReportingUpdate implements IResultUpdate {

	private static Set<String> MALARIA_TEST_IDS;
	private static Set<Integer> REPORTABLE_STATUS_IDS;
	private AnalysisDAO analysisDAO = new AnalysisDAOImpl();
	private ResultDAO resultDAO = new ResultDAOImpl();
	private SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
	private Set<Patient> reportedPatients = new HashSet<Patient>();
	private Set<String> reportedPatientIds = new HashSet<String>();

	static {
		REPORTABLE_STATUS_IDS = new HashSet<Integer>();
		REPORTABLE_STATUS_IDS.add( Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.Finalized) ) );
		
		// N.B. This should be discoverable from the DB, not hard coded by name
		TestDAO testDAO = new TestDAOImpl();
		MALARIA_TEST_IDS = new HashSet<String>();
		
		addMalariaTest(testDAO, "Recherche de plasmodiun - Especes(Sang)");
		addMalariaTest(testDAO, "Recherche de plasmodiun - Trophozoit(Sang)");
		addMalariaTest(testDAO, "Recherche de plasmodiun - Gametocyte(Sang)");
		addMalariaTest(testDAO, "Recherche de plasmodiun - Schizonte(Sang)");
		addMalariaTest(testDAO, "Malaria Test Rapide(Serum)");
		addMalariaTest(testDAO, "Malaria Test Rapide(Plasma)");
		addMalariaTest(testDAO, "Malaria Test Rapide(Sang)");
		addMalariaTest(testDAO, "Malaria(Sang)");
		addMalariaTest(testDAO, "Malaria");					// Old malaria test which may still exist in some older installations
		addMalariaTest(testDAO, "Recherche de Plasmodium");	// Old malaria test which may still exist in some older installations
		
	}

	private static void addMalariaTest(TestDAO testDAO, String description) {
		Test test = testDAO.getTestByDescription(description);
		if (test != null && test.getId() != null) {
			MALARIA_TEST_IDS.add(test.getId());
		}
	}

	@Override
	public void transactionalUpdate(IResultSaveService resultSaveService) throws LIMSRuntimeException {
		// no-op
	}

	@Override
	public void postTransactionalCommitUpdate(IResultSaveService resultSaveService) {
		ResultReportingCollator collator = new ResultReportingCollator();

		for (ResultSet resultSet : resultSaveService.getNewResults()) {
			for (Result malariaResult : malariaResultsForOrder(resultSet)) {
				if (isPositiveResult(malariaResult) && reportablePatient(resultSet.patient.getId())) {
					reportedPatients.add(resultSet.patient);
					reportedPatientIds.add(resultSet.patient.getId());
					break;
				}
			}
		}
		
		for (ResultSet resultSet : resultSaveService.getModifiedResults()) {
			for (Result malariaResult : malariaResultsForOrder(resultSet)) {
				if (isPositiveResult(malariaResult) && reportablePatient(resultSet.patient.getId())) {
					reportedPatients.add(resultSet.patient);
					reportedPatientIds.add(resultSet.patient.getId());
					break;
				}
			}
		}
		
		for (Patient patient : reportedPatients) {
			List<Result> malariaResults = new ArrayList<Result>();
			List<Sample> samples = sampleHumanDAO.getSamplesForPatient(patient.getId());
			for (Sample sample : samples) {
				List<Analysis> analyses = analysisDAO.getAnalysesBySampleId(sample.getId());
				for (Analysis analysis : analyses ){
					if (isMalariaTest(analysis.getTest())){
						List<Result> results = resultDAO.getResultsByAnalysis(analysis);
						for (Result result : results) {
							malariaResults.add(result);
						}
					} 
				}
			}
			for (Result result : malariaResults) {
				if (!collator.addResult(result, patient, false, true)) {
					LogEvent.logError("MalariaReportingUpdate", "postTransactionalCommitUpdate", "Unable to add malaria result to ResultReportingCollator.");
				}
			}
			MalariaReportingTransfer transfer = new MalariaReportingTransfer();
			transfer.sendResults(collator.getResultReport(patient.getId()), malariaResults,
								 ConfigurationProperties.getInstance().getPropertyValue(Property.malariaCaseReportURL));
		}
	}

	private boolean reportablePatient(String patientId) {
		return !reportedPatientIds.contains(patientId);
	}

	private boolean isMalariaTest(Test test) {
		return MALARIA_TEST_IDS.contains(test.getId());
	}

	private boolean isPositiveResult(Result result) {
		return (!ResultUtil.getStringValueOfResult(result).matches("(?i)^neg.*") && !ResultUtil.getStringValueOfResult(result).matches("(?i)^nég.*"));
	}
	
	private List<Result> malariaResultsForOrder(ResultSet resultSet) {
		List<Result> results = new ArrayList<Result>();
		
		List<Analysis> analysisList = analysisDAO.getAnalysesBySampleIdAndStatusId(resultSet.sample.getId(), REPORTABLE_STATUS_IDS);
		
		for( Analysis analysis : analysisList){
			if( isMalariaTest(analysis.getTest())){
				results.addAll(resultDAO.getResultsByAnalysis(analysis));
			}
		}
	
		return results;
	}

}
