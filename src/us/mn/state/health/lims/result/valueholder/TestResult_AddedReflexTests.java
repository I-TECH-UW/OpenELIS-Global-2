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
import us.mn.state.health.lims.testresult.valueholder.TestResult;

public class TestResult_AddedReflexTests extends BaseObject {

	private TestResult testResult;

	private List addedReflexTests;
	
	private String sortTestResultValue;

	public String getSortTestResultValue() {
		return sortTestResultValue;
	}

	public void setSortTestResultValue(String sortTestResultValue) {
		this.sortTestResultValue = sortTestResultValue;
	}

	public List getAddedReflexTests() {
		return addedReflexTests;
	}

	public void setAddedReflexTests(List addedReflexTests) {
		this.addedReflexTests = addedReflexTests;
	}

	public TestResult getTestResult() {
		return testResult;
	}

	public void setTestResult(TestResult testResult) {
		this.testResult = testResult;
	}


}
