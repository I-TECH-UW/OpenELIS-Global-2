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

import javax.annotation.PostConstruct;

import org.apache.commons.validator.GenericValidator;

import spring.service.dictionary.DictionaryService;
import spring.service.test.TestService;
import spring.service.testresult.TestResultService;
import spring.util.SpringContext;
import us.mn.state.health.lims.analyzerimport.util.AnalyzerTestNameCache;
import us.mn.state.health.lims.analyzerimport.util.MappedTestName;
import us.mn.state.health.lims.analyzerresults.valueholder.AnalyzerResults;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

@SuppressWarnings("unused")
public class EvolisReader extends AnalyzerLineInserter {

	protected DictionaryService dictionaryService = SpringContext.getBean(DictionaryService.class);
	protected TestService testService = SpringContext.getBean(TestService.class);
	protected TestResultService testResultService = SpringContext.getBean(TestResultService.class);

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

	@PostConstruct
	private void initialize() {
		Test test = new Test();
		test.setTestName("Integral"); // integral and murex use the same dictionary values
		test = testService.getTestByName(test);

		List<TestResult> testResults = testResultService.getActiveTestResultsByTest(test.getId());

		for (TestResult testResult : testResults) {
			String dictionaryValue = dictionaryService.getDictionaryById(testResult.getValue()).getDictEntry();

			if ("Positive".equals(dictionaryValue)) {
				POSITIVE_DICTIONARY_ID = testResult.getValue();
			} else if ("Negative".equals(dictionaryValue)) {
				NEGATIVE_DICTIONARY_ID = testResult.getValue();
			} else if ("Indeterminate".equals(dictionaryValue)) {
				INDETERMINATE_DICTIONARY_ID = testResult.getValue();
			}
		}
	}

	@Override
	public boolean insert(List<String> lines, String currentUserId) {

		boolean successful = true;

		List<AnalyzerResults> results = new ArrayList<>();

		for (int i = 1; i < lines.size(); i++) {
			addAnalyzerResultFromLine(results, lines.get(i));
		}

		if (results.size() > 0) {

//			Transaction tx = HibernateProxy.beginTransaction();
			try {
				persistResults(results, currentUserId);
//				tx.commit();
			} catch (LIMSRuntimeException lre) {
//				tx.rollback();
				lre.printStackTrace();
				successful = false;
			}

//			finally {
//				HibernateProxy.closeSession();
//			}
		}
		return successful;

	}

	private void addAnalyzerResultFromLine(List<AnalyzerResults> results, String line) {
		line = line.replace("\"", "").replace(DELIMITER, ":");
		String[] fields = line.split(":");

		String analyzerAccessionNumber = fields[Id];

		if (fields.length == 7 && !GenericValidator.isBlankOrNull(analyzerAccessionNumber)
				&& analyzerAccessionNumber.length() > 6 && fields[assay].length() > 5) {

			MappedTestName mappedName = AnalyzerTestNameCache.instance().getMappedTest(AnalyzerTestNameCache.EVOLIS,
					fields[assay]);
			AnalyzerResults analyzerResults = new AnalyzerResults();
			analyzerResults.setAnalyzerId(mappedName.getAnalyzerId());
			analyzerResults.setResult(getDictioanryValueForResult(fields[result]));
			analyzerResults.setResultType("D");
			analyzerResults.setCompleteDate(new Timestamp(new Date().getTime()));
			analyzerResults.setTestId(mappedName.getTestId());
			analyzerResults.setAccessionNumber(analyzerAccessionNumber);
			analyzerResults.setTestName(mappedName.getOpenElisTestName());
			analyzerResults.setIsControl(false);
			results.add(analyzerResults);

			AnalyzerResults resultFromDB = readerUtil.createAnalyzerResultFromDB(analyzerResults);
			if (resultFromDB != null) {
				results.add(resultFromDB);
			}

		}
	}

	private String getDictioanryValueForResult(String result) {
		if ("NEG".equals(result)) {
			return NEGATIVE_DICTIONARY_ID;
		} else if ("REACTIVE".equals(result)) {
			return POSITIVE_DICTIONARY_ID;
		} else if ("*".equals(result)) {
			return INDETERMINATE_DICTIONARY_ID;
		}

		return null;
	}

	@Override
	public String getError() {
		return "Evolis analyzer unable to write to database";
	}
}