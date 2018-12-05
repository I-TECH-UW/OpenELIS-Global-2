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
package us.mn.state.health.lims.referencetables.dao;

import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.referencetables.valueholder.ReferenceTables;


/**
 * @author Yi Chen
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public interface ReferenceTablesDAO extends BaseDAO {

	public boolean insertData(ReferenceTables referenceTables) throws LIMSRuntimeException;

	public void deleteData(List referenceTableses) throws LIMSRuntimeException;

	public List getAllReferenceTables() throws LIMSRuntimeException;

	public List getPageOfReferenceTables(int startingRecNo) throws LIMSRuntimeException;

	public void getData(ReferenceTables referenceTables) throws LIMSRuntimeException;

	public void updateData(ReferenceTables referenceTables) throws LIMSRuntimeException;
	
	public List getNextReferenceTablesRecord(String id) throws LIMSRuntimeException;

	public List getPreviousReferenceTablesRecord(String id) throws LIMSRuntimeException;
	
	public Integer getTotalReferenceTablesCount() throws LIMSRuntimeException; 
	
	public List getAllReferenceTablesForHl7Encoding() throws LIMSRuntimeException;
	
	public ReferenceTables getReferenceTableByName(	ReferenceTables referenceTables) throws LIMSRuntimeException;

	public ReferenceTables getReferenceTableByName(String tableName) throws LIMSRuntimeException;
	public Integer getTotalReferenceTableCount() throws LIMSRuntimeException;

}

