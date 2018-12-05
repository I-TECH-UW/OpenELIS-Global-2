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
package us.mn.state.health.lims.resultlimits.dao;

import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;

public interface ResultLimitDAO extends BaseDAO {

	boolean insertData(ResultLimit resultLimit)
			throws LIMSRuntimeException;

	void deleteData(List resultLimits) throws LIMSRuntimeException;

	List getAllResultLimits() throws LIMSRuntimeException;

	List getPageOfResultLimits(int startingRecNo)
			throws LIMSRuntimeException;

	void getData(ResultLimit resultLimit) throws LIMSRuntimeException;

	void updateData(ResultLimit resultLimit) throws LIMSRuntimeException;

	List getNextResultLimitRecord(String id) throws LIMSRuntimeException;

	List getPreviousResultLimitRecord(String id)
			throws LIMSRuntimeException;

	List<ResultLimit> getAllResultLimitsForTest(String testId) throws LIMSRuntimeException;
	
	ResultLimit getResultLimitById( String resultLimitId) throws LIMSRuntimeException;
}
