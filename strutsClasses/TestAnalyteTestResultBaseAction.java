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
package org.openelisglobal.testanalyte.action;

import java.util.ArrayList;
import java.util.List;

import org.openelisglobal.common.action.BaseAction;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.result.dao.ResultDAO;
import org.openelisglobal.result.daoimpl.ResultDAOImpl;
import org.openelisglobal.result.valueholder.Result;
import org.openelisglobal.testreflex.dao.TestReflexDAO;
import org.openelisglobal.testreflex.daoimpl.TestReflexDAOImpl;
import org.openelisglobal.testresult.valueholder.TestResult;

public abstract class TestAnalyteTestResultBaseAction extends BaseAction {

	public TestAnalyteTestResultBaseAction() {

	}

	/**
	 * @param testResultIdList
	 * @return
	 */
	protected boolean isTestLockedByReflex(List testResultIdList) {
		TestReflexDAO reflexDAO = new TestReflexDAOImpl();

		List reflexes = new ArrayList();
		for (int i = 0; i < testResultIdList.size(); i++) {
			TestResult testResult = new TestResult();
			testResult.setId((String) testResultIdList.get(i));

			reflexes = reflexDAO.getTestReflexesByTestResult(testResult);
			if (reflexes != null && reflexes.size() > 0) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @param testResultIdList
	 * @return
	 */
	protected boolean isTestLockedByResult(List testResultIdList) {
		ResultDAO resultDAO = new ResultDAOImpl();

		for (int i = 0; i < testResultIdList.size(); i++) {
			TestResult testResult = new TestResult();
			testResult.setId((String) testResultIdList.get(i));

			Result result = new Result();
			resultDAO.getResultByTestResult(result, testResult);
			if (!StringUtil.isNullorNill(result.getId())) {
				// System.out.println("FOUND RESULT " + result.getId());
				return true;
			}

		}

		return false;
	}

}