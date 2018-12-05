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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.hibernate.Transaction;

import us.mn.state.health.lims.analyzerimport.util.AnalyzerTestNameCache;
import us.mn.state.health.lims.analyzerimport.util.MappedTestName;
import us.mn.state.health.lims.analyzerresults.valueholder.AnalyzerResults;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.HibernateProxy;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testresult.dao.TestResultDAO;
import us.mn.state.health.lims.testresult.daoimpl.TestResultDAOImpl;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

@SuppressWarnings("unused")
public class EvolisReader extends AnalyzerLineInserter {

	private static String NEGATIVE_DICTIONARY_ID = null;
	private static String POSITIVE_DICTIONARY_ID = null;
	private static String INDETERMINATE_DICTIONARY_ID = null;
	private static String DELIMITER = "|";
	private static int Id = 0;
	private static int assay = 1;
	private static int well = 2;
	private static int flag = 3;
	private static int value = 4;
	private static int S_CO = 5;
	private static int result = 6;
	
	private static AnalyzerReaderUtil readerUtil = new AnalyzerReaderUtil();
	
	static{
		TestDAO testDAO = new TestDAOImpl();
		TestResultDAO testResultDAO = new TestResultDAOImpl();
		DictionaryDAO dictioanryDAO = new DictionaryDAOImpl();
		
		Test test = new Test();
		test.setTestName("Integral");  //integral and murex use the same dictionary values
		test = testDAO.getTestByName(test);
		
		
		List<TestResult> testResults = testResultDAO.getActiveTestResultsByTest( test.getId() );
		
		for( TestResult testResult : testResults){
			String dictionaryValue = dictioanryDAO.getDictionaryById(testResult.getValue()).getDictEntry();
			
			if( "Positive".equals(dictionaryValue)){
				POSITIVE_DICTIONARY_ID = testResult.getValue();
			}else if("Negative".equals(dictionaryValue)){
				NEGATIVE_DICTIONARY_ID = testResult.getValue();
			}else if("Indeterminate".equals(dictionaryValue)){
				INDETERMINATE_DICTIONARY_ID = testResult.getValue();
			}
		}
	}
	
	public boolean insert(List<String> lines, String currentUserId) {

		boolean successful = true;

		List<AnalyzerResults> results = new ArrayList<AnalyzerResults>();

		for (int i = 1; i < lines.size(); i++) {
			addAnalyzerResultFromLine(results, lines.get(i));
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

	private void addAnalyzerResultFromLine(List<AnalyzerResults> results, String line) {
		line = line.replace("\"", "").replace(DELIMITER, ":");
		String[] fields = line.split(":");
		
		String analyzerAccessionNumber = fields[Id];

		if( fields.length == 7 && 
				!GenericValidator.isBlankOrNull(analyzerAccessionNumber) &&
				analyzerAccessionNumber.length() > 6 &&
				fields[assay].length() > 5){
			
			MappedTestName mappedName = AnalyzerTestNameCache.instance().getMappedTest(AnalyzerTestNameCache.EVOLIS, fields[assay]);
			AnalyzerResults analyzerResults = new AnalyzerResults();
			analyzerResults.setAnalyzerId(mappedName.getAnalyzerId());
			analyzerResults.setResult(getDictioanryValueForResult( fields[result]));
			analyzerResults.setResultType("D");
			analyzerResults.setCompleteDate(new Timestamp(new Date().getTime()));
			analyzerResults.setTestId(mappedName.getTestId());
			analyzerResults.setAccessionNumber(analyzerAccessionNumber);
			analyzerResults.setTestName(mappedName.getOpenElisTestName());
			analyzerResults.setIsControl(false);
			results.add(analyzerResults);
			
			AnalyzerResults resultFromDB = readerUtil.createAnalyzerResultFromDB(analyzerResults);
			if( resultFromDB != null){
				results.add(resultFromDB);
			}

		}
	}

	private String getDictioanryValueForResult(String result) {
		if( "NEG".equals(result)){
			return NEGATIVE_DICTIONARY_ID;
		}else if( "REACTIVE".equals(result)){
			return POSITIVE_DICTIONARY_ID;
		}else if( "*".equals(result)){
			return INDETERMINATE_DICTIONARY_ID;
		}
		
		return null;
	}

	@Override
	public String getError() {
		return "Evolis analyzer unable to write to database";
	}
}