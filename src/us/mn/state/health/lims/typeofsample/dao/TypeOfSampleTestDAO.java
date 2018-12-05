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
package us.mn.state.health.lims.typeofsample.dao;

import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSampleTest;

public interface TypeOfSampleTestDAO extends BaseDAO {

	public boolean insertData(TypeOfSampleTest typeOfSample)
			throws LIMSRuntimeException;

	public void deleteData(String[] typeOfSampleTestIds, String currentUserId) throws LIMSRuntimeException;

	public List getAllTypeOfSampleTests() throws LIMSRuntimeException;

	public List getPageOfTypeOfSampleTests(int startingRecNo)
			throws LIMSRuntimeException;

	public void getData(TypeOfSampleTest typeOfSampleTest) throws LIMSRuntimeException;

	public List getNextTypeOfSampleTestRecord(String id) throws LIMSRuntimeException;

	public List getPreviousTypeOfSampleRecord(String id) throws LIMSRuntimeException;

	public Integer getTotalTypeOfSampleTestCount() throws LIMSRuntimeException;

	public List<TypeOfSampleTest> getTypeOfSampleTestsForSampleType(String sampleTypeId) throws LIMSRuntimeException;

	public TypeOfSampleTest getTypeOfSampleTestForTest(String testId) throws LIMSRuntimeException;
	
	public List<TypeOfSampleTest> getTypeOfSampleTestsForTest(String testId) throws LIMSRuntimeException;
}
