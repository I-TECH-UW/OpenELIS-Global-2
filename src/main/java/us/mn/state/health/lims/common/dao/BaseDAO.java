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
package us.mn.state.health.lims.common.dao;

import java.util.List;

import us.mn.state.health.lims.common.exception.LIMSRuntimeException;

public interface BaseDAO {
	
	public List getNextRecord(String id, String table, Class clazz)
	   throws LIMSRuntimeException;
	
	public List getPreviousRecord(String id, String table, Class clazz)
	   throws LIMSRuntimeException;
	
	//bugzilla 1411
	public Integer getTotalCount(String table, Class clazz) throws LIMSRuntimeException;

}