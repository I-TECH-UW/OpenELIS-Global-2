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
package us.mn.state.health.lims.result.action.util;

import java.util.List;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.TypeOfTestResultService;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.test.beanItems.TestResultItem;
import us.mn.state.health.lims.testanalyte.dao.TestAnalyteDAO;
import us.mn.state.health.lims.testanalyte.daoimpl.TestAnalyteDAOImpl;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;

public class ResultUtil {
	private static final DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
	private static final TestAnalyteDAO testAnalyteDAO = new TestAnalyteDAOImpl();
	
	public static String getStringValueOfResult( Result result){
		if( TypeOfTestResultService.ResultType.isDictionaryVariant( result.getResultType() )){
			return dictionaryDAO.getDictionaryById(result.getValue()).getLocalizedName();
		}else{
			return result.getValue();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static TestAnalyte getTestAnalyteForResult(Result result) {
        /*
        The logic behind this code is that there is a matching of some analytes to the number of times the test has been
        run.  i.e. if there is a positive HIV test some labs will run it again as a reflex.  This code below is to make
        sure that we don't have an endless loop, but it does not feel very robust.  This is due for a refactoring.

         */
		if (result.getTestResult() != null) {
			List<TestAnalyte> testAnalyteList = testAnalyteDAO.getAllTestAnalytesPerTest(result.getTestResult().getTest());

            if( testAnalyteList.size() == 1){
                return testAnalyteList.get(0);
            }

			if (testAnalyteList.size() > 1) {
				int distanceFromRoot = 0;

				Analysis parentAnalysis = result.getAnalysis().getParentAnalysis();

				while (parentAnalysis != null) {
					distanceFromRoot++;
					parentAnalysis = parentAnalysis.getParentAnalysis();
				}

				int index = Math.min(distanceFromRoot, testAnalyteList.size() - 1);

				return testAnalyteList.get(index);
			}
		}
		return null;
	}
	public static boolean areNotes(TestResultItem item) {
		return !GenericValidator.isBlankOrNull(item.getNote());
	}
	
	public static boolean isReferred(TestResultItem testResultItem) {
		return testResultItem.isShadowReferredOut();
	}
	
    public static boolean isRejected(TestResultItem testResultItem) {
        return testResultItem.isShadowRejected();
    }
	public static boolean areResults(TestResultItem item) {
		return !(GenericValidator.isBlankOrNull(item.getShadowResultValue()) ||
				(TypeOfTestResultService.ResultType.DICTIONARY.matches(item.getResultType()) && "0".equals(item.getShadowResultValue()))) ||
				(TypeOfTestResultService.ResultType.isMultiSelectVariant(item.getResultType()) && !GenericValidator.isBlankOrNull(item.getMultiSelectResultValues()));
	}

	public static boolean isForcedToAcceptance(TestResultItem item){
		return !GenericValidator.isBlankOrNull(item.getForceTechApproval());
	}
}
