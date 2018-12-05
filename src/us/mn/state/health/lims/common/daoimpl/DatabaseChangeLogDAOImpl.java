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
package us.mn.state.health.lims.common.daoimpl;

import java.util.List;

import org.hibernate.Query;

import us.mn.state.health.lims.common.dao.DatabaseChangeLogDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.valueholder.DatabaseChangeLog;
import us.mn.state.health.lims.hibernate.HibernateUtil;

public class DatabaseChangeLogDAOImpl implements DatabaseChangeLogDAO {

	@SuppressWarnings("unchecked")
	public DatabaseChangeLog getLastExecutedChange() throws LIMSRuntimeException {
		List<DatabaseChangeLog> results;
		
		 try {
			String sql = "from DatabaseChangeLog dcl order by dcl.executed desc";
			Query query = HibernateUtil.getSession().createQuery(sql);
       	
			results = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			
			
			if (results != null && results.get(0) != null) {
				return results.get(0);
			}
			
       } catch (Exception e) {
			LogEvent.logError("DatabaseChangeLogDAOImpl","getLastExecutedChange()",e.toString());
			throw new LIMSRuntimeException("Error in DatabaseChangeLogDAOImpl getLastExecutedChange()", e);
       }
		

		return null;
	}

}
