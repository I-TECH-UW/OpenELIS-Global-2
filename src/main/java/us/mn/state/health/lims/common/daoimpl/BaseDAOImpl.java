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
package us.mn.state.health.lims.common.daoimpl;

import java.util.List;
import java.util.Vector;

import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;

public class BaseDAOImpl implements BaseDAO, IActionConstants{

	public static int DEFAULT_PAGE_SIZE;

	{
		DEFAULT_PAGE_SIZE = SystemConfiguration.getInstance().getDefaultPageSize();
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see us.mn.state.health.lims.common.dao.BaseDAO#getNextRecord(java.lang.String,
	 *      java.lang.String, java.lang.Class) passing in id of current record ->
	 *      get next and check if there will be more (next button enabled?)
	 */
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
		int start = (Integer.valueOf(id)).intValue();

		List list = new Vector();
		try {
			String sql = "from "+table+" t where id >= "+start+" order by t.id";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(1);
			query.setMaxResults(2);

			list = query.list();

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("BaseDAOImpl","getNextRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
		}

		return list;
	}

	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
		int start = (Integer.valueOf(id)).intValue();

		List list = new Vector();
		try {
			String sql = "from "+table+" t order by t.id desc where id <= "+start;
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(1);
			query.setMaxResults(2);

			list = query.list();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("BaseDAOImpl","getPreviousRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		}

		return list;
	}

	//bugzilla 1411
	public Integer getTotalCount(String table, Class clazz) throws LIMSRuntimeException {
		Integer count = null;
		 try {
			String sql = "select count(*) from " + table;
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);

			List results = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();


			if (results != null && results.get(0) != null) {
				if (results.get(0) != null) {
					count = (Integer)results.get(0);
				}
			}

        } catch (Exception e) {
        	//bugzilla 2154
			LogEvent.logError("BaseDAOImpl","getTotalCount()",e.toString());
			throw new LIMSRuntimeException("Error in getTotalCount() for " + table, e);
        }

		return count;
	}

	//bugzilla 1427
	public String enquote(String sql) {

      //bugzilla 2316, take care of ' symbol in the sql
		if (sql.indexOf("'")!= -1 )
	    {
		   sql = sql.replaceAll ("'", "''");
		}
		return "'" + sql + "'";
	}

	//bugzilla 1427
	public String getTablePrefix(String table) {
		return table.toLowerCase() + ".";
	}

	protected void handleException( Exception e, String method) throws LIMSRuntimeException {
		LogEvent.logError( this.getClass().getSimpleName(), method, e.toString());
		throw new LIMSRuntimeException("Error in " + this.getClass().getSimpleName() + " " + method, e);
	}

	protected void closeSession(){
		HibernateUtil.getSession().flush();
		HibernateUtil.getSession().clear();
	}
}