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
package us.mn.state.health.lims.testreflex.dao;

import java.util.List;

import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;
import us.mn.state.health.lims.testreflex.valueholder.TestReflex;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

/**
 * @author diane benz
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public interface TestReflexDAO extends BaseDAO {

	/**
	 * @param testReflex
	 * @return
	 * @throws LIMSRuntimeException
	 */
	public boolean insertData(TestReflex testReflex)
			throws LIMSRuntimeException;

	/**
	 * @param testReflexs
	 * @throws LIMSRuntimeException
	 */
	public void deleteData(List testReflexs) throws LIMSRuntimeException;

	/**
	 * @return
	 * @throws LIMSRuntimeException
	 */
	public List getAllTestReflexs() throws LIMSRuntimeException;

	/**
	 * @param startingRecNo
	 * @return
	 * @throws LIMSRuntimeException
	 */
	public List getPageOfTestReflexs(int startingRecNo)
			throws LIMSRuntimeException;

	/**
	 * @param testReflex
	 * @throws LIMSRuntimeException
	 */
	public void getData(TestReflex testReflex) throws LIMSRuntimeException;

	/**
	 * @param testReflex
	 * @throws LIMSRuntimeException
	 */
	public void updateData(TestReflex testReflex) throws LIMSRuntimeException;

	/**
	 * @param id
	 * @return
	 * @throws LIMSRuntimeException
	 */
	public List getNextTestReflexRecord(String id) throws LIMSRuntimeException;

	/**
	 * @param id
	 * @return
	 * @throws LIMSRuntimeException
	 */
	public List getPreviousTestReflexRecord(String id)
			throws LIMSRuntimeException;

	/**
	 * @param testReflex
	 * @param testResult
	 * @throws LIMSRuntimeException
	 */
	public List getTestReflexesByTestResult(TestResult testResult) throws LIMSRuntimeException;

	/**
	 * @param testReflex
	 * @param testResult
	 * @param testAnalyte
	 * @throws LIMSRuntimeException
	 */
	public List getTestReflexesByTestResultAndTestAnalyte(TestResult testResult, TestAnalyte testAnalyte) throws LIMSRuntimeException;

	//bugzilla 1411
	/**
	 * @return
	 * @throws LIMSRuntimeException
	 */
	public Integer getTotalTestReflexCount() throws LIMSRuntimeException;

	/**
     * bugzilla 1798
	 * @param analysis
	 * @throws LIMSRuntimeException
	 */
	public boolean isReflexedTest(Analysis analysis) throws LIMSRuntimeException;

	/**
	 * Gets the ReflexTest for the analysis if there is one, otherwise returns null.
	 * @param analysis
	 * @throws LIMSRuntimeException
	 */
	public List<TestReflex> getTestReflexsByTestResultAnalyteTest(String testResultId, String analyteId, String testId) throws LIMSRuntimeException;

	/*
	 * Gets all test reflexs which may be triggered by this test and have this flag.
	 * Intended use was to get testReflexs which the user decides what the action is.
	 *
	 * @param testId The testId for which we want the reflexes
	 * @param flag The value of the flag field.  May be null
	 */
	public List<TestReflex>  getTestReflexsByTestAndFlag(String testId, String flag)  throws LIMSRuntimeException;

	public List<TestReflex> getFlaggedTestReflexesByTestResult(TestResult testResult, String flag)  throws LIMSRuntimeException;
}
