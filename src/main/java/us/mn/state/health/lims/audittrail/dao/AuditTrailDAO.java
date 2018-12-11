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
package us.mn.state.health.lims.audittrail.dao;

import java.sql.Date;
import java.util.List;

import us.mn.state.health.lims.audittrail.valueholder.History;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;

/**
 *  @author         Hung Nguyen
 *  @date created   09/12/2006
 */
public interface AuditTrailDAO extends BaseDAO {
	
	public void saveHistory(Object newObject, Object existingObject, 
							String sysUserId, String event, String tableName) throws LIMSRuntimeException;
		
	public void saveNewHistory(Object newObject, String sysUserId, String tableName) throws LIMSRuntimeException;
		
	public String getXML(String table, String id) throws LIMSRuntimeException;
	
	public String getXMLData(String table, String id) throws LIMSRuntimeException;

    public List getHistoryByRefIdAndRefTableId(History history) throws LIMSRuntimeException;

    public List getHistoryByRefIdAndRefTableId(String refId, String tableId) throws LIMSRuntimeException;
	
	public List getHistoryBySystemUserAndDateAndRefTableId(History history) throws LIMSRuntimeException;
	
	public String retrieveBlobData(String id) throws LIMSRuntimeException;

    public List<History> getHistoryByRefTableIdAndDateRange( String referenceTableId, Date start, Date end) throws LIMSRuntimeException;
}
