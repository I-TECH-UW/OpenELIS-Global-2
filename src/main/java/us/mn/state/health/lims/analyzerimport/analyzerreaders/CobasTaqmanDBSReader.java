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
import java.util.List;

import org.hibernate.Transaction;

import us.mn.state.health.lims.analyzerimport.util.AnalyzerTestNameCache;
import us.mn.state.health.lims.analyzerimport.util.MappedTestName;
import us.mn.state.health.lims.analyzerresults.valueholder.AnalyzerResults;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.HibernateProxy;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testresult.daoimpl.TestResultDAOImpl;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

public class CobasTaqmanDBSReader extends AnalyzerLineInserter {

	

	private int ORDER_NUMBER = 0;
	private int ORDER_DATE = 0;
	private int RESULT = 0;
	private int SAMPLE_TYPE = 0;

	private static final String TEST_NAME = "HIQCAP48";
	private static final String DELIMITER = "\\t";
	private static final String DATE_PATTERN = "yyyy/MM/dd HH:mm:ss";
	private static final String VALID_PREFIXES = "LART,LDBS,LRTN,LIND,LSPE";
	private static String NEGATIVE_ID;
	private static String POSITIVE_ID;
	
	private AnalyzerReaderUtil readerUtil = new AnalyzerReaderUtil();
	private String error;

	static{
		DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
		Test test = new TestDAOImpl().getActiveTestByName("DNA PCR").get(0);
		List<TestResult> testResults = new TestResultDAOImpl().getActiveTestResultsByTest( test.getId() );
		
		for(TestResult testResult : testResults){
			Dictionary dictionary = dictionaryDAO.getDataForId(testResult.getValue());
			if( "Positive".equals(dictionary.getDictEntry())){
				POSITIVE_ID = dictionary.getId();
			}else if( "Negative".equals(dictionary.getDictEntry())){
				NEGATIVE_ID = dictionary.getId();
			}
		}
		
	}
	
	public boolean insert(List<String> lines, String currentUserId) {
		error = null;
		boolean successful = true;

		List<AnalyzerResults> results = new ArrayList<AnalyzerResults>();

		boolean columnsFound = manageColumns(lines.get(0));

		if (!columnsFound) {
			error = "Cobas Taqman DBS analyzer: Unable to find correct columns in file #";
			return false;
		}

		MappedTestName mappedName = AnalyzerTestNameCache.instance().getMappedTest(AnalyzerTestNameCache.COBAS_DBS, TEST_NAME);

		if (mappedName == null) {
			mappedName = AnalyzerTestNameCache.instance().getEmptyMappedTestName(AnalyzerTestNameCache.COBAS_DBS, TEST_NAME);
		}

		for (int i = 1; i < lines.size(); ++i) {
			createAnalyzerResultFromLine(lines.get(i), results, mappedName);
		}

		if (results.size() > 0) {
			Transaction tx = HibernateProxy.beginTransaction();

			try {
				persistResults(results, currentUserId);
				tx.commit();
			} catch (LIMSRuntimeException lre) {
				tx.rollback();
				error = "Cobas Taqman DBS analyzer: Unable to save to database";
				successful = false;
			} finally {
				HibernateProxy.closeSession();
			}
		}

		return successful;
	}

	private boolean manageColumns(String line) {
		String[] fields = line.split(DELIMITER);

		for (int i = 0; i < fields.length; i++) {
			String header = fields[i].replace("\"", "");

			if ("Order Number".equals(header)) {
				ORDER_NUMBER = i;
			} else if ("Order Date/Time".equals(header)) {
				ORDER_DATE = i;
			} else if ("Result".equals(header)) {
				RESULT = i;
			}else if ("Sample Type".equals(header)) {
				SAMPLE_TYPE = i;
			}
		}

		return ORDER_DATE != 0 && ORDER_NUMBER != 0 && RESULT != 0 && SAMPLE_TYPE != 0;
	}

	private void addValueToResults(List<AnalyzerResults> resultList, AnalyzerResults result) {
		resultList.add(result);

		AnalyzerResults resultFromDB = readerUtil.createAnalyzerResultFromDB(result);
		if (resultFromDB != null) {
			resultList.add(resultFromDB);
		}

	}

	private void createAnalyzerResultFromLine(String line, List<AnalyzerResults> resultList, MappedTestName mappedName) {
		String[] fields = line.split(DELIMITER);

		AnalyzerResults analyzerResults = new AnalyzerResults();

		String result = getAppropriateResults(fields[RESULT]);
		String accessionNumber = fields[ORDER_NUMBER].replace("\"", "").trim();
		
		analyzerResults.setAnalyzerId(mappedName.getAnalyzerId());
		analyzerResults.setResult( result );
		analyzerResults.setCompleteDate(DateUtil.convertStringDateToTimestampWithPattern(fields[ORDER_DATE].replace("\"", "").trim(), DATE_PATTERN));
		analyzerResults.setTestId(mappedName.getTestId());
		analyzerResults.setIsControl(!VALID_PREFIXES.contains(accessionNumber.subSequence(0,3)));
		analyzerResults.setTestName(mappedName.getOpenElisTestName());
		analyzerResults.setResultType("D");

		if( analyzerResults.getIsControl()){
			if( !"S".equals(fields[SAMPLE_TYPE].replace("\"", "").trim())){
				accessionNumber += ":" + fields[SAMPLE_TYPE].replace("\"", "").trim();
			}			
		}else{
			accessionNumber = accessionNumber.substring(0, 9);
		}
		
		analyzerResults.setAccessionNumber(accessionNumber);
		
		addValueToResults(resultList, analyzerResults);
	}

	private String getAppropriateResults(String result) {
		result = result.replace("\"", "").trim();
		if ("Target Not Detected".equals(result)) {
			result = NEGATIVE_ID;
		} else {
			result = POSITIVE_ID;
// save this until we finish w/ requirements
//			String workingResult = result.split("\\(")[0].replace("<", "").replace("E", "");
//			String[] splitResult = workingResult.split("\\+");
//
//			if (Double.parseDouble(splitResult[0]) * Math.pow(10, Double.parseDouble(splitResult[1])) < THREASHOLD) {
//				result = UNDER_THREASHOLD;
//			}
		}

		return result;
	}


	@Override
	public String getError() {
		return error;
	}
}