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
package us.mn.state.health.lims.test.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.test.valueholder.Test;

/**
 * @author diane benz
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public interface TestDAO extends BaseDAO {

	public boolean insertData(Test test) throws LIMSRuntimeException;

	public void deleteData(List tests) throws LIMSRuntimeException;

	
	public List<Test> getAllTests(boolean onlyTestsFullySetup) throws LIMSRuntimeException;

	public List<Test> getAllActiveTests(boolean onlyTestsFullySetup) throws LIMSRuntimeException;

	public List<Test> getAllActiveOrderableTests() throws LIMSRuntimeException;
	
	public List<Test> getAllOrderBy(String columnName) throws LIMSRuntimeException;

	
	public List getAllTestsBySysUserId(int sysUserId, boolean onlyTestsFullySetup) throws LIMSRuntimeException;

	public List getPageOfTests(int startingRecNo) throws LIMSRuntimeException;

	public List getPageOfTestsBySysUserId(int startingRecNo, int sysUserId) throws LIMSRuntimeException;

	public void getData(Test test) throws LIMSRuntimeException;

	public void updateData(Test test) throws LIMSRuntimeException;

    
	public List getTests(String filter, boolean onlyTestsFullySetup) throws LIMSRuntimeException;

	public List getNextTestRecord(String id) throws LIMSRuntimeException;

	public List getPreviousTestRecord(String id) throws LIMSRuntimeException;

	public Test getTestByName(Test test) throws LIMSRuntimeException;
	
	public Test getTestByName(String testName) throws LIMSRuntimeException;

    public Test getTestByUserLocalizedName(String testName) throws LIMSRuntimeException;

	public List<Test> getActiveTestByName(String testName) throws LIMSRuntimeException;

	public Test getTestById(Test test) throws LIMSRuntimeException;

	public Test getActiveTestById(Integer id) throws LIMSRuntimeException;

	public List getMethodsByTestSection(String filter) throws LIMSRuntimeException;

	public List getTestsByTestSection(String filter) throws LIMSRuntimeException;

	public List getTestsByMethod(String filter) throws LIMSRuntimeException;

	public List getTestsByTestSectionAndMethod(String filter, String filter2) throws LIMSRuntimeException;

	public Integer getTotalTestCount() throws LIMSRuntimeException;

	
	public boolean isTestFullySetup(Test test) throws LIMSRuntimeException;

	
	public List getPageOfSearchedTests(int startingRecNo, String searchString)
	throws LIMSRuntimeException;

   
    public Integer getAllSearchedTotalTestCount (HttpServletRequest request, String searchString)
	 throws LIMSRuntimeException;

    
	public Integer getTotalSearchedTestCountBySysUserId(int sysUserId, String searchString)
	throws LIMSRuntimeException;

	
	public Integer getTotalSearchedTestCount (String searchString)
	throws LIMSRuntimeException;

	
	public List<Test> getPageOfSearchedTestsBySysUserId(int startingRecNo, int sysUserId, String searchString)
	throws LIMSRuntimeException;
	
	public Integer getNextAvailableSortOrderByTestSection(Test test) throws LIMSRuntimeException;

	public Test getTestById(String testId) throws LIMSRuntimeException;

	public Test getTestByDescription(String description) throws LIMSRuntimeException;

    public List<Test> getTestsByTestSectionId(String id) throws LIMSRuntimeException;

    public Test getTestByGUID( String guid) throws LIMSRuntimeException;

	public List<Test> getTestsByLoincCode(String loincCode);

	public List<Test> getActiveTestsByLoinc(String loincCode);
}
