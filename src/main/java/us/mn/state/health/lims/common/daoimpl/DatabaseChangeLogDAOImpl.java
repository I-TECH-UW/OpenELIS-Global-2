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
import java.util.Map;
import java.util.Optional;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.dao.DatabaseChangeLogDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.valueholder.DatabaseChangeLog;
import us.mn.state.health.lims.hibernate.HibernateUtil;

@Component
public class DatabaseChangeLogDAOImpl extends BaseDAOImpl<DatabaseChangeLog> implements DatabaseChangeLogDAO {

	public DatabaseChangeLogDAOImpl() {
		super(DatabaseChangeLog.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public DatabaseChangeLog getLastExecutedChange() throws LIMSRuntimeException {
		List<DatabaseChangeLog> results;

		try {
			String sql = "from DatabaseChangeLog dcl order by dcl.executed desc";
			Query query = HibernateUtil.getSession().createQuery(sql);

			results = query.list();
			// HibernateUtil.getSession().flush(); // CSL remove old
			// HibernateUtil.getSession().clear(); // CSL remove old

			if (results != null && results.get(0) != null) {
				return results.get(0);
			}

		} catch (Exception e) {
			LogEvent.logError("DatabaseChangeLogDAOImpl", "getLastExecutedChange()", e.toString());
			throw new LIMSRuntimeException("Error in DatabaseChangeLogDAOImpl getLastExecutedChange()", e);
		}

		return null;
	}

	// SHOULD NOT NEED BELOW METHODS! Override to hide!
	@Override
	public Optional<DatabaseChangeLog> get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DatabaseChangeLog> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DatabaseChangeLog> getAllMatching(Map<String, Object> columnValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DatabaseChangeLog> getAllOrdered(String orderByColumn, boolean descending) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DatabaseChangeLog> getAllOrdered(List<String> orderByColumns, boolean descending) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DatabaseChangeLog> getAllMatchingOrdered(Map<String, Object> columnValues, String orderByColumn,
			boolean descending) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insert(DatabaseChangeLog object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<DatabaseChangeLog> update(DatabaseChangeLog object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(DatabaseChangeLog object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(List<DatabaseChangeLog> objects) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(String[] objectIds) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DatabaseChangeLog> getNext(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DatabaseChangeLog> getPrevious(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalCount(String table, Class clazz) throws LIMSRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

}
