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

import us.mn.state.health.lims.common.valueholder.BaseObject;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;

public class TestAnalyte_TestResults extends BaseObject {

	private TestAnalyte testAnalyte;

	private List testResults;

	private String selectedTestResultId;
	
	private String resultId;
	
	private List resultNotes;
	
	private List testResultReflexTests;
	//AIS - bugzilla 1797
	private String resultValue;
	
	//bugzilla 1798 if this analysis has a parent - distinguish between 
	//   it being a reflexed test and a linked test (child test/parent test)
	private String childType;
	
	//bugzilla 1798 can this analysis be linked as a child? (show LINK button? does popup have options?)
	private String canBeLinked;
	
	//bugzilla 2532/2627
	private String canBeUnlinked;

	public String getCanBeUnlinked() {
		return canBeUnlinked;
	}

	public void setCanBeUnlinked(String canBeUnlinked) {
		this.canBeUnlinked = canBeUnlinked;
	}

	public String getCanBeLinked() {
		return canBeLinked;
	}

	public void setCanBeLinked(String canBeLinked) {
		this.canBeLinked = canBeLinked;
	}

	public String getChildType() {
		return childType;
	}

	public void setChildType(String childType) {
		this.childType = childType;
	}
	
	public String getResultValue() {
		return resultValue;
	}
	
	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}
	
	//AIS - bugzilla 1872 
	private String resultIsReportable;
	public String getResultIsReportable() {
		return resultIsReportable;
	}	
	public void setResultIsReportable(String resultIsReportable) {
		this.resultIsReportable = resultIsReportable;
	}	

	public TestAnalyte_TestResults() {
		super();
	}

	public TestAnalyte getTestAnalyte() {
		return testAnalyte;
	}

	public String getSelectedTestResultId() {
		return selectedTestResultId;
	}

	public void setSelectedTestResultId(String selectedTestResultId) {
		this.selectedTestResultId = selectedTestResultId;
	}

	public void setTestAnalyte(TestAnalyte testAnalyte) {
		this.testAnalyte = testAnalyte;
	}

	public List getTestResults() {
		return testResults;
	}

	public void setTestResults(List testResults) {
		this.testResults = testResults;
	}

	public List getTestResultReflexTests() {
		return testResultReflexTests;
	}

	public void setTestResultReflexTests(List testResultReflexTests) {
		this.testResultReflexTests = testResultReflexTests;
	}

	public List getResultNotes() {
		return resultNotes;
	}

	public void setResultNotes(List resultNotes) {
		this.resultNotes = resultNotes;
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

}
