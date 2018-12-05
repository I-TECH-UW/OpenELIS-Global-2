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
package us.mn.state.health.lims.systemusersection.dao;

import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.systemusersection.valueholder.SystemUserSection;

/**
 *  @author     Hung Nguyen (Hung.Nguyen@health.state.mn.us)
 */
public interface SystemUserSectionDAO extends BaseDAO {

	public boolean insertData(SystemUserSection systemUserSection) throws LIMSRuntimeException;

	public void deleteData(List systemUserSection) throws LIMSRuntimeException;

	public List getAllSystemUserSections() throws LIMSRuntimeException;

	public List getPageOfSystemUserSections(int startingRecNo) throws LIMSRuntimeException;

	public void getData(SystemUserSection systemUserSection) throws LIMSRuntimeException;

	public void updateData(SystemUserSection systemUserSection) throws LIMSRuntimeException;
	
	public List getNextSystemUserSectionRecord(String id) throws LIMSRuntimeException;

	public List getPreviousSystemUserSectionRecord(String id) throws LIMSRuntimeException;
	
	public Integer getTotalSystemUserSectionCount() throws LIMSRuntimeException; 
	
	public List getAllSystemUserSectionsBySystemUserId(int systemUserId) throws LIMSRuntimeException;

}
