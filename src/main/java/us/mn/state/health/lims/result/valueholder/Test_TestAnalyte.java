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
*/
package us.mn.state.health.lims.result.valueholder;

import java.util.List;

import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.valueholder.BaseObject;
import us.mn.state.health.lims.test.valueholder.Test;

public class Test_TestAnalyte extends BaseObject {

	private List testAnalytes;
	private Result [] results;
	private List [] notes;
	private Analysis analysis;
	private Test test;
	//bugzilla 2227
	private String totalNumberOfRevisionsToDisplayInHistoryForATest = "0";
	
	//bugzilla 2028 Qa Events
	private List analysisQaEvents;

	
	//AIS - bugzilla 1872 
	private String testIsReportable;
	public String getTestIsReportable() {
		return testIsReportable;
	}	
	public void setTestIsReportable(String testIsReportable) {
		this.testIsReportable = testIsReportable;
	}	
	
	//bugzilla 1802
	private TestAnalyte_TestResults[] testAnalyteTestResults;


	public Test_TestAnalyte() {
		super();
	}



	public TestAnalyte_TestResults[] getTestAnalyteTestResults() {
		return testAnalyteTestResults;
	}



	public void setTestAnalyteTestResults(
			TestAnalyte_TestResults[] testAnalyteTestResults) {
		this.testAnalyteTestResults = testAnalyteTestResults;
	}



	public Test getTest() {
		return test;
	}


	public void setTest(Test test) {
		this.test = test;
	}


	public Result[] getResults() {
		return results;
	}


	public void setResults(Result[] results) {
		this.results = results;
	}


	public Analysis getAnalysis() {
		return analysis;
	}


	public void setAnalysis(Analysis analysis) {
		this.analysis = analysis;
	}


	public List[] getNotes() {
		return notes;
	}


	public void setNotes(List[] notes) {
		this.notes = notes;
	}


	public List getTestAnalytes() {
		return testAnalytes;
	}


	public void setTestAnalytes(List testAnalytes) {
		this.testAnalytes = testAnalytes;
	}
	public List getAnalysisQaEvents() {
		return analysisQaEvents;
	}
	public void setAnalysisQaEvents(List analysisQaEvents) {
		this.analysisQaEvents = analysisQaEvents;
	}
	//2227
	public String getTotalNumberOfRevisionsToDisplayInHistoryForATest() {
		return totalNumberOfRevisionsToDisplayInHistoryForATest;
	}
	public void setTotalNumberOfRevisionsToDisplayInHistoryForATest(
			String totalNumberOfRevisionsToDisplayInHistoryForATest) {
		this.totalNumberOfRevisionsToDisplayInHistoryForATest = totalNumberOfRevisionsToDisplayInHistoryForATest;
	}
}
