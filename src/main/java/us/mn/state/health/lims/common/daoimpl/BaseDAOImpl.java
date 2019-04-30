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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.persister.entity.AbstractEntityPersister;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.common.valueholder.BaseObject;
import us.mn.state.health.lims.hibernate.HibernateUtil;

/**
 * @author caleb
 *
 * @param <T>
 */
public class BaseDAOImpl<T extends BaseObject> implements BaseDAO<T>, IActionConstants {

	public static int DEFAULT_PAGE_SIZE;

	{
		DEFAULT_PAGE_SIZE = SystemConfiguration.getInstance().getDefaultPageSize();
	}

	private final Class<T> classType;
	private boolean logAuditTrail = true;

	public BaseDAOImpl(Class<T> clazz) {
		classType = clazz;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Optional<T> get(String id) {
		Session session = startSession();
		T object = (T) session.get(classType, id);
		commitAndCloseSession(session);
		return Optional.ofNullable(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		Session session = startSession();
		List<T> resultList = session.createCriteria(classType).list();
		commitAndCloseSession(session);

		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllWhereMatch(Map<String, Object> columnValues) {
		Session session = startSession();
		Criteria criteria = session.createCriteria(classType);
		for (Entry<String, Object> entrySet : columnValues.entrySet()) {
			criteria.add(Restrictions.eq(entrySet.getKey(), entrySet.getValue()));
		}
		List<T> resultList = criteria.list();
		commitAndCloseSession(session);

		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllOrderedBy(String orderColumn, boolean descending) {
		Session session = startSession();
		Criteria criteria = session.createCriteria(classType);
		if (descending) {
			criteria.addOrder(Order.desc(orderColumn));
		} else {
			criteria.addOrder(Order.asc(orderColumn));
		}
		List<T> resultList = criteria.list();
		commitAndCloseSession(session);

		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllOrderedBy(List<String> orderColumns, boolean descending) {
		Session session = startSession();
		Criteria criteria = session.createCriteria(classType);
		for (String orderColumn : orderColumns) {
			if (descending) {
				criteria.addOrder(Order.desc(orderColumn));
			} else {
				criteria.addOrder(Order.asc(orderColumn));
			}
		}
		List<T> resultList = criteria.list();
		commitAndCloseSession(session);

		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllWhereMatchOrderedBy(Map<String, Object> columnValues, String orderColumn, boolean descending) {
		Session session = startSession();
		Criteria criteria = session.createCriteria(classType);
		for (Entry<String, Object> entrySet : columnValues.entrySet()) {
			criteria.add(Restrictions.eq(entrySet.getKey(), entrySet.getValue()));
		}
		if (descending) {
			criteria.addOrder(Order.desc(orderColumn));
		} else {
			criteria.addOrder(Order.asc(orderColumn));
		}
		List<T> resultList = criteria.list();
		commitAndCloseSession(session);

		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllWhereMatchOrderedBy(Map<String, Object> columnValues, List<String> orderColumns,
			boolean descending) {
		Session session = startSession();
		Criteria criteria = session.createCriteria(classType);
		for (Entry<String, Object> entrySet : columnValues.entrySet()) {
			criteria.add(Restrictions.eq(entrySet.getKey(), entrySet.getValue()));
		}
		for (String orderColumn : orderColumns) {
			if (descending) {
				criteria.addOrder(Order.desc(orderColumn));
			} else {
				criteria.addOrder(Order.asc(orderColumn));
			}
		}
		List<T> resultList = criteria.list();
		commitAndCloseSession(session);

		return resultList;
	}

	@Override
	public String insert(T object) {
		Session session = startSession();
		String id = (String) session.save(object);

		if (logAuditTrail) {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			auditDAO.saveNewHistory(object, object.getSysUserId(), getTableNameAlias());
		}

		commitAndCloseSession(session);

		return id;
	}

	// could be optimized by using single transaction, and calling session.flush
	// session.clear every 20 records before final call to transaction.commit
	@Override
	public List<String> insertAll(List<T> objects) {
		List<String> ids = new ArrayList<>();

		for (T object : objects) {
			ids.add(insert(object));
		}

		return ids;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Optional<T> update(T object) {
		String id = object.getId();
		if (id != null) {
			Optional<T> oldObject = get(id);
			if (oldObject.isPresent()) {
				Session session = startSession();
				Optional<T> newObject = Optional.of((T) session.merge(object));

				if (logAuditTrail) {
					AuditTrailDAO auditDao = new AuditTrailDAOImpl();
					auditDao.saveHistory(newObject.get(), oldObject.get(), object.getSysUserId(), AUDIT_TRAIL_UPDATE,
							getTableNameAlias());
				}
				commitAndCloseSession(session);

				return newObject;
			}
		} else {
			LogEvent.logWarn(this.getClass().getSimpleName(), "update",
					"Cannot update item that does not have an id value");
		}
		return Optional.ofNullable(null);
	}

	// could be optimized by using single transaction, and calling session.flush
	// session.clear every 20 records
	@Override
	public List<Optional<T>> updateAll(List<T> objects) {
		List<Optional<T>> updatedObjects = new ArrayList<>();

		for (T object : objects) {
			updatedObjects.add(update(object));
		}

		return updatedObjects;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(T object) {
		Optional<T> oldObject = get(object.getId());

		Session session = startSession();
		session.delete(object);

		if (logAuditTrail && oldObject.isPresent()) {
			AuditTrailDAO auditDao = new AuditTrailDAOImpl();
			try {
				T newObject = (T) object.getClass().newInstance();
				auditDao.saveHistory(newObject, oldObject.get(), object.getSysUserId(), AUDIT_TRAIL_DELETE,
						getTableNameAlias());
			} catch (InstantiationException | IllegalAccessException e) {
				LogEvent.logErrorStack(this.getClass().getSimpleName(), "delete", e);
				throw new LIMSRuntimeException(e);
			}
		}
		commitAndCloseSession(session);
	}

	@Override
	public void delete(String id) {
		if (logAuditTrail) {
			LogEvent.logError(this.getClass().getSimpleName(), "delete",
					"method unsupported when logAuditTrail is set to true");
			throw new UnsupportedOperationException();
		} else {
			Optional<T> dbObject = get(id);
			if (dbObject.isPresent()) {
				Session session = startSession();
				session.delete(dbObject.get());
				commitAndCloseSession(session);
			} else {
				LogEvent.logWarn(this.getClass().getSimpleName(), "delete()",
						"Cannot find object in database to delete");
			}
		}
	}

	// could be optimized by using single transaction, and calling session.flush
	// session.clear every 20 records
	@Override
	public void deleteAll(List<T> objects) {
		for (T object : objects) {
			delete(object);
		}
	}

	// could be optimized by using single transaction, and calling session.flush
	// session.clear every 20 records
	@Override
	public void deleteAll(String[] objectIds) {
		for (String object : objectIds) {
			delete(object);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Integer getCount() {
		Integer rowCount = 0;

		Session session = startSession();
		Criteria criteria = session.createCriteria(classType);
		criteria.setProjection(Projections.rowCount());
		List results = criteria.list();
		commitAndCloseSession(session);
		if (results != null) {
			rowCount = ((Long) results.get(0)).intValue();
		} else {
			LogEvent.logWarn(this.getClass().getSimpleName(), "getCount()",
					"could not count number of objects for " + classType.getName());
		}

		return rowCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getNext(String id) {
		int start = (Integer.valueOf(id)).intValue();
		String table = getTableNameAlias();

		List<T> list = new ArrayList<>();
		try {
			Session session = startSession();
			String sql = "from " + table + " t order by t.id where id >= " + start;
			Query query = session.createQuery(sql);
			query.setFirstResult(1);
			query.setMaxResults(2);
			list = query.list();
			commitAndCloseSession(session);
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError(this.getClass().getSimpleName(), "getNext()", e.toString());
			throw new LIMSRuntimeException("Error in getNext() for " + table, e);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getPrevious(String id) {
		int start = (Integer.valueOf(id)).intValue();
		String table = getTableNameAlias();

		List<T> list = new ArrayList<>();
		try {
			Session session = startSession();
			String sql = "from " + table + " t order by t.id desc where id <= " + start;
			Query query = session.createQuery(sql);
			query.setFirstResult(1);
			query.setMaxResults(2);
			list = query.list();
			commitAndCloseSession(session);
		} catch (Exception e) {
			LogEvent.logError(this.getClass().getSimpleName(), "getPrevious()", e.toString());
			throw new LIMSRuntimeException("Error in getPrevious() for " + table, e);
		}

		return list;
	}

	/**
	 * @return table name alias that Hibernate uses
	 */
	protected String getTableNameAlias() {
		AbstractEntityPersister persister = (AbstractEntityPersister) HibernateUtil.getSessionFactory()
				.getClassMetadata(classType);
		return persister.getEntityName();
	}

	/**
	 * @return get table name in database
	 */
	protected String getTableName() {
		AbstractEntityPersister persister = (AbstractEntityPersister) HibernateUtil.getSessionFactory()
				.getClassMetadata(classType);
		return persister.getTableName();
	}

	/**
	 * @return an active session with a transaction running
	 */
	protected Session startSession() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		return session;
	}

	/**
	 * Commit the changes, flush the committed changes so they are written to the
	 * database, and clear the Hibernate memory cache before closing the session
	 *
	 * @param session the active session the transaction is running on
	 */
	protected void commitAndCloseSession(Session session) {
		Transaction tx = session.getTransaction();
		try {
			session.flush();
			session.clear();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			LogEvent.logErrorStack("HibernateDao", "commitAndCloseSession()", e);
			throw e;
		} finally {
			session.close();
		}
	}

	// end of new methods, below this point are legacy methods

	@Override
	@Deprecated
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
		int start = (Integer.valueOf(id)).intValue();

		List list = new Vector();
		try {
			String sql = "from " + table + " t where id >= " + start + " order by t.id";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(1);
			query.setMaxResults(2);

			list = query.list();

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("BaseDAOImpl", "getNextRecord()", e.toString());
			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
		}

		return list;
	}

	@Override
	@Deprecated
	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
		int start = (Integer.valueOf(id)).intValue();

		List list = new Vector();
		try {
			String sql = "from " + table + " t order by t.id desc where id <= " + start;
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(1);
			query.setMaxResults(2);

			list = query.list();
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("BaseDAOImpl", "getPreviousRecord()", e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		}

		return list;
	}

	// bugzilla 1411
	@Override
	@Deprecated
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
					count = ((Long) results.get(0)).intValue();
				}
			}

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("BaseDAOImpl", "getTotalCount()", e.toString());
			throw new LIMSRuntimeException("Error in getTotalCount() for " + table, e);
		}

		return count;
	}

	// bugzilla 1427
	public String enquote(String sql) {

		// bugzilla 2316, take care of ' symbol in the sql
		if (sql.indexOf("'") != -1) {
			sql = sql.replaceAll("'", "''");
		}
		return "'" + sql + "'";
	}

	// bugzilla 1427
	public String getTablePrefix(String table) {
		return table.toLowerCase() + ".";
	}

	protected void handleException(Exception e, String method) throws LIMSRuntimeException {
		LogEvent.logError(this.getClass().getSimpleName(), method, e.toString());
		throw new LIMSRuntimeException("Error in " + this.getClass().getSimpleName() + " " + method, e);
	}

	protected void closeSession() {
		HibernateUtil.getSession().flush();
		HibernateUtil.getSession().clear();
	}
}