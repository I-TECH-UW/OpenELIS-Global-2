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
package us.mn.state.health.lims.systemmodule.dao;

import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModule;

/**
 *  @author     Hung Nguyen (Hung.Nguyen@health.state.mn.us)
 */
public interface SystemModuleDAO extends BaseDAO {

	public boolean insertData(SystemModule systemModule) throws LIMSRuntimeException;

	public void deleteData(List systemModule) throws LIMSRuntimeException;

	public List getAllSystemModules() throws LIMSRuntimeException;

	public List getPageOfSystemModules(int startingRecNo) throws LIMSRuntimeException;

	public void getData(SystemModule systemModule) throws LIMSRuntimeException;

	public void updateData(SystemModule systemModule) throws LIMSRuntimeException;
	
	public List getNextSystemModuleRecord(String id) throws LIMSRuntimeException;

	public List getPreviousSystemModuleRecord(String id) throws LIMSRuntimeException;
	
	public Integer getTotalSystemModuleCount() throws LIMSRuntimeException;

    public SystemModule getSystemModuleByName( String name ) throws LIMSRuntimeException;
}
