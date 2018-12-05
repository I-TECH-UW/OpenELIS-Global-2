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
package us.mn.state.health.lims.testresult.dao;

import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

/**
 * @author diane benz
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public interface TestResultDAO extends BaseDAO {

	public boolean insertData(TestResult testResult)
			throws LIMSRuntimeException;

	public void deleteData(List testResults) throws LIMSRuntimeException;

	public List getAllTestResults() throws LIMSRuntimeException;

	public List getPageOfTestResults(int startingRecNo)
			throws LIMSRuntimeException;

	public void getData(TestResult testResult) throws LIMSRuntimeException;

	public void updateData(TestResult testResult) throws LIMSRuntimeException;

	public List getNextTestResultRecord(String id) throws LIMSRuntimeException;

	public List getPreviousTestResultRecord(String id)
			throws LIMSRuntimeException;

	public TestResult getTestResultById(TestResult testResult)
			throws LIMSRuntimeException;

	public List getTestResultsByTestAndResultGroup(TestAnalyte testAnalyte)
			throws LIMSRuntimeException;

	public List getAllActiveTestResultsPerTest( Test test ) throws LIMSRuntimeException;

	/*
	 * Finds a TestResult by a test id and dictionary result id
	 */
	public TestResult getTestResultsByTestAndDictonaryResult(String testId, String result)throws LIMSRuntimeException;

	public List<TestResult> getActiveTestResultsByTest( String testId )throws LIMSRuntimeException;

}
