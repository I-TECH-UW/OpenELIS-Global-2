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
package us.mn.state.health.lims.scriptlet.dao;

import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.scriptlet.valueholder.Scriptlet;

/**
 * @author diane benz
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public interface ScriptletDAO extends BaseDAO {

	public boolean insertData(Scriptlet scriptlet) throws LIMSRuntimeException;

	public void deleteData(List scriptlets) throws LIMSRuntimeException;

	public List getAllScriptlets() throws LIMSRuntimeException;

	public List getPageOfScriptlets(int startingRecNo)
			throws LIMSRuntimeException;

	public void getData(Scriptlet scriptlet) throws LIMSRuntimeException;

	public void updateData(Scriptlet scriptlet) throws LIMSRuntimeException;

	public List getScriptlets(String filter) throws LIMSRuntimeException;

	public List getNextScriptletRecord(String id) throws LIMSRuntimeException;

	public List getPreviousScriptletRecord(String id)
			throws LIMSRuntimeException;

	public Scriptlet getScriptletByName(Scriptlet scriptlet)
			throws LIMSRuntimeException;

	public Integer getTotalScriptletCount() throws LIMSRuntimeException; 
	
	public Scriptlet getScriptletById(String scriptletId)  throws LIMSRuntimeException;
}
