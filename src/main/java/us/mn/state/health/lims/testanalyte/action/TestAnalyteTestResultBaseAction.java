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
package us.mn.state.health.lims.testanalyte.action;

import java.util.ArrayList;
import java.util.List;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.testreflex.dao.TestReflexDAO;
import us.mn.state.health.lims.testreflex.daoimpl.TestReflexDAOImpl;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

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