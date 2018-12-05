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
* 
* Contributor(s): CIRG, University of Washington, Seattle WA.
*/
package us.mn.state.health.lims.testanalyte.dao;

import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;

/**
 * @author diane benz
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public interface TestAnalyteDAO extends BaseDAO {

	public boolean insertData(TestAnalyte testAnalyte)
			throws LIMSRuntimeException;

	/**
	 * @param testAnalytes
	 * @throws LIMSRuntimeException
	 */
	public void deleteData(List testAnalytes) throws LIMSRuntimeException;

	/**
	 * @return
	 * @throws LIMSRuntimeException
	 */
	public List getAllTestAnalytes() throws LIMSRuntimeException;

	/**
	 * @param startingRecNo
	 * @return
	 * @throws LIMSRuntimeException
	 */
	public List getPageOfTestAnalytes(int startingRecNo)
			throws LIMSRuntimeException;

	/**
	 * @param testAnalyte
	 * @throws LIMSRuntimeException
	 */
	public TestAnalyte getData(TestAnalyte testAnalyte) throws LIMSRuntimeException;

	/**
	 * @param testAnalyte
	 * @throws LIMSRuntimeException
	 */
	public void updateData(TestAnalyte testAnalyte) throws LIMSRuntimeException;

	/**
	 * @param filter
	 * @return
	 * @throws LIMSRuntimeException
	 */
	public List getTestAnalytes(String filter) throws LIMSRuntimeException;

	/**
	 * @param id
	 * @return
	 * @throws LIMSRuntimeException
	 */
	public List getNextTestAnalyteRecord(String id) throws LIMSRuntimeException;

	/**
	 * @param id
	 * @return
	 * @throws LIMSRuntimeException
	 */
	public List getPreviousTestAnalyteRecord(String id)
			throws LIMSRuntimeException;

	/**
	 * @param testAnalyte
	 * @return
	 * @throws LIMSRuntimeException
	 */
	public TestAnalyte getTestAnalyteById(TestAnalyte testAnalyte)
			throws LIMSRuntimeException;

	/**
	 * @param test
	 * @return
	 * @throws LIMSRuntimeException
	 */
	public List getAllTestAnalytesPerTest(Test test)
			throws LIMSRuntimeException;
	
}
