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
package us.mn.state.health.lims.analyzerimport.analyzerreaders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;
import org.hibernate.Transaction;

import us.mn.state.health.lims.analyzerimport.util.AnalyzerTestNameCache;
import us.mn.state.health.lims.analyzerimport.util.MappedTestName;
import us.mn.state.health.lims.analyzerresults.valueholder.AnalyzerResults;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.HibernateProxy;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;

public class CobasReader extends AnalyzerLineInserter {
    private static final String COBAS_INTEGRA400_NAME = "Cobas Integra";
	private static String ASTL_ID;
	private static String ALTL_ID;
	private static String CRE_ID;
	private static String GLU_ID;
	private static Map<String, Integer> testIdToPresentation;

	// private static final int ID = 0;
	private static final int DATE = 1;
	private static final int TEST = 2;
	// private static final int BLANK1 = 3;
	private static final int ACCESSION = 4;
	// private static final int BLANK2 = 5;
	// private static final int SAMPLE_TYPE = 6;
	private static final int UNITS = 7;
	// private static final int BLANK3 = 8;
	private static final int MAJOR_RESULTS = 9;
	// private static final int MINOR_RESULTS = 10;
	// private static final int INDICATOR_1 = 11;
	// private static final int INDICATOR_2 = 12;

	private static final String DELIMITER = "\\t";
	private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private AnalyzerReaderUtil readerUtil = new AnalyzerReaderUtil();

	static{
		TestDAO testDAO = new TestDAOImpl();
		ASTL_ID = testDAO.getActiveTestByName("Transaminases ASTL").get(0).getId();
		ALTL_ID = testDAO.getActiveTestByName("Transaminases ALTL").get(0).getId();
		CRE_ID = testDAO.getActiveTestByName("Créatininémie").get(0).getId();
		GLU_ID = testDAO.getActiveTestByName("Glycémie").get(0).getId();

		testIdToPresentation = new HashMap<String, Integer>();
		testIdToPresentation.put(ALTL_ID, 0);
		testIdToPresentation.put(ASTL_ID, 1);
		testIdToPresentation.put(CRE_ID, 2);
		testIdToPresentation.put(GLU_ID, 3);

	}
	public boolean insert(List<String> lines, String currentUserId) {
		boolean successful = true;
		
		if( lines == null ){
			return true;
		}
		
		if( GenericValidator.isBlankOrNull(currentUserId)){
			return false;
		}

		List<AnalyzerResults> results = new ArrayList<AnalyzerResults>();
		List<AnalyzerResults> notMatchedResults = new ArrayList<AnalyzerResults>();

		Map<String, AnalyzerResults[]> accessionToResultMap = new HashMap<String, AnalyzerResults[]>();
		List<String> accessionOrder = new ArrayList<String>();
		
		for( String line : lines ){
			createAnalyzerResultFromLine(line, accessionToResultMap, accessionOrder, notMatchedResults);
		}
		
		for( String accessionNumber : accessionOrder){
			
			AnalyzerResults[] resultSet = accessionToResultMap.get(accessionNumber);
			
			for( int i = 0; i < 4; i++){
				if( resultSet[i] != null){
					addValueToResults(results, resultSet[i]);
				}
			}
			
			for( AnalyzerResults analyzerResult : notMatchedResults){
				addValueToResults(results, analyzerResult);
			}
		}

		if (results.size() > 0) {

			Transaction tx = HibernateProxy.beginTransaction();

			try {
				persistResults(results, currentUserId);
				tx.commit();
			} catch (LIMSRuntimeException lre) {
				tx.rollback();
				successful = false;
			} finally {
				HibernateProxy.closeSession();
			}
		}

		return successful;
	}

	private void addValueToResults(List<AnalyzerResults> resultList, AnalyzerResults result) {
		resultList.add(result);

		AnalyzerResults resultFromDB = readerUtil.createAnalyzerResultFromDB(result);
		if (resultFromDB != null) {
			resultList.add(resultFromDB);
		}

	}

	private void createAnalyzerResultFromLine(String line, Map<String, AnalyzerResults[]> accessionToResultMap, List<String> accessionOrder, List<AnalyzerResults> notMatchedResults) {
		String[] fields = line.split(DELIMITER);

		AnalyzerResults analyzerResults = new AnalyzerResults();
		MappedTestName mappedName = AnalyzerTestNameCache.instance().getMappedTest(COBAS_INTEGRA400_NAME, fields[TEST]);

		if (mappedName == null) {
			mappedName = AnalyzerTestNameCache.instance().getEmptyMappedTestName(COBAS_INTEGRA400_NAME, fields[TEST]);
		}

		analyzerResults.setAnalyzerId(mappedName.getAnalyzerId());
		analyzerResults.setResult(fields[MAJOR_RESULTS]);
		analyzerResults.setUnits(fields[UNITS]);
		analyzerResults.setCompleteDate(DateUtil.convertStringDateToTimestampWithPattern(fields[DATE], DATE_PATTERN));
		analyzerResults.setAccessionNumber(fields[ACCESSION]);
		analyzerResults.setTestId(mappedName.getTestId());

		if (analyzerResults.getAccessionNumber() != null) {
			analyzerResults.setIsControl(analyzerResults.getAccessionNumber().trim().contains(" "));
		} else {
			analyzerResults.setIsControl(false);
		}

		analyzerResults.setTestName(mappedName.getOpenElisTestName());

		if( analyzerResults.getTestId() != null){
			int bufferIndex = testIdToPresentation.get(analyzerResults.getTestId()).intValue();
			AnalyzerResults[] buffer = accessionToResultMap.get(analyzerResults.getAccessionNumber());
			
			if( buffer == null){
				buffer = new AnalyzerResults[4];
				accessionToResultMap.put(analyzerResults.getAccessionNumber(), buffer);
				accessionOrder.add(analyzerResults.getAccessionNumber());
			}
			buffer[bufferIndex] = analyzerResults;
		}else{
			notMatchedResults.add(analyzerResults);
		}
	}

	
	@Override
	public String getError() {
			return "Cobas Intgra 400 error writting to database";
	}

	
}