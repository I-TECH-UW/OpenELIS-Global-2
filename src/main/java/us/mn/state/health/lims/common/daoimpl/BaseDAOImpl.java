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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;

import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.common.valueholder.BaseObject;
import us.mn.state.health.lims.hibernate.HibernateUtil;

/**
 * @author Caleb
 *
 * @param <T>
 */
public class BaseDAOImpl<T extends BaseObject> implements BaseDAO<T>, IActionConstants {

	static final int DEFAULT_PAGE_SIZE = SystemConfiguration.getInstance().getDefaultPageSize();

	private final Class<T> classType;

	@Autowired
	protected SessionFactory sessionFactory;

	public BaseDAOImpl(Class<T> clazz) {
		classType = clazz;
	}

	@Override
	public Optional<T> get(String id) {
		Session session = sessionFactory.getCurrentSession();
		T object = session.get(classType, id);
		return Optional.ofNullable(object);
	}

	@Override
	public List<T> getAll() {
		return getAllOrdered("id", false);
	}

	@Override
	public List<T> getAllMatching(String propertyName, Object propertyValue) {
		Map<String, Object> propertyValues = new HashMap<>();
		propertyValues.put(propertyName, propertyValue);

		return getAllMatching(propertyValues);
	}

	@Override
	public List<T> getAllMatching(Map<String, Object> propertyValues) {
		return getAllMatchingOrdered(propertyValues, "id", false);
	}

	@Override
	public List<T> getAllOrdered(String orderProperty, boolean descending) {
		List<String> orderProperties = new ArrayList<>();
		orderProperties.add(orderProperty);

		return getAllOrdered(orderProperties, descending);
	}

	@Override
	public List<T> getAllOrdered(List<String> orderProperties, boolean descending) {
		return getAllMatchingOrdered(new HashMap<>(), orderProperties, descending);
	}

	@Override
	public List<T> getAllMatchingOrdered(String propertyName, Object propertyValue, String orderProperty,
			boolean descending) {
		Map<String, Object> propertyValues = new HashMap<>();
		propertyValues.put(propertyName, propertyValue);
		List<String> orderProperties = new ArrayList<>();
		orderProperties.add(orderProperty);

		return getAllMatchingOrdered(propertyValues, orderProperties, descending);
	}

	@Override
	public List<T> getAllMatchingOrdered(String propertyName, Object propertyValue, List<String> orderProperties,
			boolean descending) {
		Map<String, Object> propertyValues = new HashMap<>();
		propertyValues.put(propertyName, propertyValue);

		return getAllMatchingOrdered(propertyValues, orderProperties, descending);
	}

	@Override
	public List<T> getAllMatchingOrdered(Map<String, Object> propertyValues, String orderProperty, boolean descending) {
		List<String> orderProperties = new ArrayList<>();

		return getAllMatchingOrdered(propertyValues, orderProperties, descending);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllMatchingOrdered(Map<String, Object> propertyValues, List<String> orderProperties,
			boolean descending) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(classType);
		for (Entry<String, Object> entrySet : propertyValues.entrySet()) {
			criteria.add(Restrictions.eq(entrySet.getKey(), entrySet.getValue()));
		}
		for (String orderProperty : orderProperties) {
			addOrder(criteria, orderProperty, descending);
		}
		return criteria.list();
	}

	@Override
	public List<T> getPage(int pageNumber) {
		return getOrderedPage("id", false, pageNumber);
	}

	@Override
	public List<T> getMatchingPage(String propertyName, Object propertyValue, int pageNumber) {
		Map<String, Object> propertyValues = new HashMap<>();
		propertyValues.put(propertyName, propertyValue);
		return getMatchingPage(propertyValues, pageNumber);
	}

	@Override
	public List<T> getMatchingPage(Map<String, Object> propertyValues, int pageNumber) {
		return getMatchingOrderedPage(propertyValues, "id", false, pageNumber);
	}

	@Override
	public List<T> getOrderedPage(String orderProperty, boolean descending, int pageNumber) {
		List<String> orderProperties = new ArrayList<>();
		orderProperties.add(orderProperty);

		return getOrderedPage(orderProperties, descending, pageNumber);
	}

	@Override
	public List<T> getOrderedPage(List<String> orderProperties, boolean descending, int pageNumber) {
		return getMatchingOrderedPage(new HashMap<>(), orderProperties, descending, pageNumber);
	}

	@Override
	public List<T> getMatchingOrderedPage(String propertyName, Object propertyValue, String orderProperty,
			boolean descending, int pageNumber) {
		List<String> orderProperties = new ArrayList<>();
		orderProperties.add(orderProperty);
		Map<String, Object> propertyValues = new HashMap<>();
		propertyValues.put(propertyName, propertyValue);

		return getMatchingOrderedPage(propertyValues, orderProperties, descending, pageNumber);
	}

	@Override
	public List<T> getMatchingOrderedPage(String propertyName, Object propertyValue, List<String> orderProperties,
			boolean descending, int pageNumber) {
		Map<String, Object> propertyValues = new HashMap<>();
		propertyValues.put(propertyName, propertyValue);

		return getMatchingOrderedPage(propertyValues, orderProperties, descending, pageNumber);
	}

	@Override
	public List<T> getMatchingOrderedPage(Map<String, Object> propertyValues, String orderProperty, boolean descending,
			int pageNumber) {
		List<String> orderProperties = new ArrayList<>();
		orderProperties.add(orderProperty);

		return getMatchingOrderedPage(propertyValues, orderProperties, descending, pageNumber);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getMatchingOrderedPage(Map<String, Object> propertyValues, List<String> orderProperties,
			boolean descending, int pageNumber) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(classType);
		for (Entry<String, Object> entrySet : propertyValues.entrySet()) {
			criteria.add(Restrictions.eq(entrySet.getKey(), entrySet.getValue()));
		}
		for (String orderProperty : orderProperties) {
			addOrder(criteria, orderProperty, descending);
		}
		criteria.setFirstResult(pageNumber * DEFAULT_PAGE_SIZE);
		criteria.setMaxResults(DEFAULT_PAGE_SIZE + 1);
		return criteria.list();
	}

	// TODO can only have one alias with the same name, find way to avoid collision
	// TODO only supports one level of nesting, expand to multi-level if needed
	private void addOrder(Criteria criteria, String orderProperty, boolean descending) {
		// nested property detection
		int dotCount = StringUtils.countMatches(orderProperty, '.');
		if (dotCount > 1) {
			throw new UnsupportedOperationException(
					"BaseDAOImpl addOrder() does not support orders using multi-nested \".\" properties");
		} else if (dotCount == 1) {
			String nestedPropertyAlias = orderProperty.substring(0, orderProperty.indexOf('.'));
			criteria.createAlias(nestedPropertyAlias, nestedPropertyAlias);
		}
		if (descending) {
			criteria.addOrder(Order.desc(orderProperty));
		} else {
			criteria.addOrder(Order.asc(orderProperty));
		}

	}

	@Override
	public String insert(T object) {
		Session session = sessionFactory.getCurrentSession();
		return (String) session.save(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T save(T object) {
		Session session = sessionFactory.getCurrentSession();
		return (T) session.merge(object);
	}

	@Override
	public void delete(T object) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getCount() {
		Integer rowCount = 0;

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(classType);
		criteria.setProjection(Projections.rowCount());
		List<Long> results = criteria.list();

		if (results != null) {
			rowCount = results.get(0).intValue();
		} else {
			LogEvent.logError(this.getClass().getSimpleName(), "getCount()",
					"could not count number of objects for class" + classType.getName());
			rowCount = -1;
		}

		return rowCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Optional<T> getNext(String id) {
		int start = (Integer.valueOf(id)).intValue();
		String table = getObjectName();

		List<T> list = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "from " + table + " t order by t.id where id > " + start;
		Query query = session.createQuery(sql);
		query.setFirstResult(0);
		query.setMaxResults(1);
		list = query.list();
		if (list.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(list.get(0));
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public Optional<T> getPrevious(String id) {
		int start = (Integer.valueOf(id)).intValue();
		String table = getObjectName();

		List<T> list = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "from " + table + " t order by t.id desc where id < " + start;
		Query query = session.createQuery(sql);
		query.setFirstResult(0);
		query.setMaxResults(1);
		list = query.list();
		if (list.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(list.get(0));
		}

	}

	/**
	 * @return object name that Hibernate uses
	 */
	protected String getObjectName() {
		return classType.getSimpleName();
	}

	@Override
	public String getTableName() {
		AbstractEntityPersister persister = (AbstractEntityPersister) sessionFactory.getClassMetadata(classType);
		String tableName = persister.getTableName();
		return tableName.substring(tableName.indexOf('.') + 1);
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