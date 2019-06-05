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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Vector;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.common.valueholder.BaseObject;

/**
 * @author Caleb
 *
 *
 * @param <T>
 */
@Component
@Transactional
public abstract class BaseDAOImpl<T extends BaseObject<PK>, PK extends Serializable>
		implements BaseDAO<T, PK>, IActionConstants {

	protected static final int DEFAULT_PAGE_SIZE = SystemConfiguration.getInstance().getDefaultPageSize();
	private static final int RANDOM_ALIAS_LENGTH = 5;
	private static final String MULTI_NESTED_MARKING = ",";

	private final Class<T> classType;

	@Autowired
	protected SessionFactory sessionFactory;

	@Autowired
	public BaseDAOImpl(Class<T> clazz) {
		classType = clazz;
	}

	@Override
	public Optional<T> get(PK id) {
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
	public List<T> getAllLike(String propertyName, String propertyValue) {
		Map<String, String> propertyValues = new HashMap<>();
		propertyValues.put(propertyName, propertyValue);

		return getAllLike(propertyValues);
	}

	@Override
	public List<T> getAllLike(Map<String, String> propertyValues) {
		return getAllLikeOrdered(propertyValues, "id", false);
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
		Map<String, String> aliases = new HashMap<>();

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(classType);
		for (Entry<String, Object> entrySet : propertyValues.entrySet()) {
			addRestriction(criteria, entrySet.getKey(), entrySet.getValue(), aliases);
		}
		for (String orderProperty : orderProperties) {
			addOrder(criteria, orderProperty, descending, aliases);
		}
		return criteria.list();
	}

	@Override
	public List<T> getAllLikeOrdered(String propertyName, String propertyValue, String orderProperty,
			boolean descending) {
		Map<String, String> propertyValues = new HashMap<>();
		propertyValues.put(propertyName, propertyValue);
		List<String> orderProperties = new ArrayList<>();
		orderProperties.add(orderProperty);

		return getAllLikeOrdered(propertyValues, orderProperties, descending);
	}

	@Override
	public List<T> getAllLikeOrdered(String propertyName, String propertyValue, List<String> orderProperties,
			boolean descending) {
		Map<String, String> propertyValues = new HashMap<>();
		propertyValues.put(propertyName, propertyValue);

		return getAllLikeOrdered(propertyValues, orderProperties, descending);
	}

	@Override
	public List<T> getAllLikeOrdered(Map<String, String> propertyValues, String orderProperty, boolean descending) {
		List<String> orderProperties = new ArrayList<>();

		return getAllLikeOrdered(propertyValues, orderProperties, descending);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllLikeOrdered(Map<String, String> propertyValues, List<String> orderProperties,
			boolean descending) {
		Map<String, String> aliases = new HashMap<>();

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(classType);
		for (Entry<String, String> entrySet : propertyValues.entrySet()) {
			addLikeRestriction(criteria, entrySet.getKey(), entrySet.getValue(), aliases);
		}
		for (String orderProperty : orderProperties) {
			addOrder(criteria, orderProperty, descending, aliases);
		}
		return criteria.list();
	}

	@Override
	public List<T> getPage(int startingRecNo) {
		return getOrderedPage("id", false, startingRecNo);
	}

	@Override
	public List<T> getMatchingPage(String propertyName, Object propertyValue, int startingRecNo) {
		Map<String, Object> propertyValues = new HashMap<>();
		propertyValues.put(propertyName, propertyValue);
		return getMatchingPage(propertyValues, startingRecNo);
	}

	@Override
	public List<T> getMatchingPage(Map<String, Object> propertyValues, int startingRecNo) {
		return getMatchingOrderedPage(propertyValues, "id", false, startingRecNo);
	}

	@Override
	public List<T> getLikePage(String propertyName, String propertyValue, int startingRecNo) {
		Map<String, Object> propertyValues = new HashMap<>();
		propertyValues.put(propertyName, propertyValue);
		return getMatchingPage(propertyValues, startingRecNo);
	}

	@Override
	public List<T> getLikePage(Map<String, String> propertyValues, int startingRecNo) {
		return getLikeOrderedPage(propertyValues, "id", false, startingRecNo);
	}

	@Override
	public List<T> getOrderedPage(String orderProperty, boolean descending, int startingRecNo) {
		List<String> orderProperties = new ArrayList<>();
		orderProperties.add(orderProperty);

		return getOrderedPage(orderProperties, descending, startingRecNo);
	}

	@Override
	public List<T> getOrderedPage(List<String> orderProperties, boolean descending, int startingRecNo) {
		return getMatchingOrderedPage(new HashMap<>(), orderProperties, descending, startingRecNo);
	}

	@Override
	public List<T> getMatchingOrderedPage(String propertyName, Object propertyValue, String orderProperty,
			boolean descending, int startingRecNo) {
		List<String> orderProperties = new ArrayList<>();
		orderProperties.add(orderProperty);
		Map<String, Object> propertyValues = new HashMap<>();
		propertyValues.put(propertyName, propertyValue);

		return getMatchingOrderedPage(propertyValues, orderProperties, descending, startingRecNo);
	}

	@Override
	public List<T> getMatchingOrderedPage(String propertyName, Object propertyValue, List<String> orderProperties,
			boolean descending, int startingRecNo) {
		Map<String, Object> propertyValues = new HashMap<>();
		propertyValues.put(propertyName, propertyValue);

		return getMatchingOrderedPage(propertyValues, orderProperties, descending, startingRecNo);
	}

	@Override
	public List<T> getMatchingOrderedPage(Map<String, Object> propertyValues, String orderProperty, boolean descending,
			int startingRecNo) {
		List<String> orderProperties = new ArrayList<>();
		orderProperties.add(orderProperty);

		return getMatchingOrderedPage(propertyValues, orderProperties, descending, startingRecNo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getMatchingOrderedPage(Map<String, Object> propertyValues, List<String> orderProperties,
			boolean descending, int startingRecNo) {
		Map<String, String> aliases = new HashMap<>();

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(classType);
		for (Entry<String, Object> entrySet : propertyValues.entrySet()) {
			addRestriction(criteria, entrySet.getKey(), entrySet.getValue(), aliases);
		}
		for (String orderProperty : orderProperties) {
			addOrder(criteria, orderProperty, descending, aliases);
		}
		criteria.setFirstResult(startingRecNo - 1);
		criteria.setMaxResults(DEFAULT_PAGE_SIZE + 1);
		return criteria.list();
	}

	@Override
	public List<T> getLikeOrderedPage(String propertyName, String propertyValue, String orderProperty,
			boolean descending, int startingRecNo) {
		List<String> orderProperties = new ArrayList<>();
		orderProperties.add(orderProperty);
		Map<String, String> propertyValues = new HashMap<>();
		propertyValues.put(propertyName, propertyValue);

		return getLikeOrderedPage(propertyValues, orderProperties, descending, startingRecNo);
	}

	@Override
	public List<T> getLikeOrderedPage(String propertyName, String propertyValue, List<String> orderProperties,
			boolean descending, int startingRecNo) {
		Map<String, String> propertyValues = new HashMap<>();
		propertyValues.put(propertyName, propertyValue);

		return getLikeOrderedPage(propertyValues, orderProperties, descending, startingRecNo);
	}

	@Override
	public List<T> getLikeOrderedPage(Map<String, String> propertyValues, String orderProperty, boolean descending,
			int startingRecNo) {
		List<String> orderProperties = new ArrayList<>();
		orderProperties.add(orderProperty);

		return getLikeOrderedPage(propertyValues, orderProperties, descending, startingRecNo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getLikeOrderedPage(Map<String, String> propertyValues, List<String> orderProperties,
			boolean descending, int startingRecNo) {
		Map<String, String> aliases = new HashMap<>();

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(classType);
		for (Entry<String, String> entrySet : propertyValues.entrySet()) {
			addLikeRestriction(criteria, entrySet.getKey(), entrySet.getValue(), aliases);
		}
		for (String orderProperty : orderProperties) {
			addOrder(criteria, orderProperty, descending, aliases);
		}
		criteria.setFirstResult(startingRecNo - 1);
		criteria.setMaxResults(DEFAULT_PAGE_SIZE + 1);
		return criteria.list();
	}

	private void addRestriction(Criteria criteria, String propertyName, Object propertyValue,
			Map<String, String> aliases) {
		String aliasedProperty = createAliasIfNeeded(criteria, propertyName, aliases);
		criteria.add(Restrictions.eq(aliasedProperty, propertyValue));
	}

	private void addLikeRestriction(Criteria criteria, String propertyName, String propertyValue,
			Map<String, String> aliases) {
		String aliasedProperty = createAliasIfNeeded(criteria, propertyName, aliases);
		criteria.add(Restrictions.ilike(aliasedProperty, propertyValue, MatchMode.ANYWHERE));
	}

	private void addOrder(Criteria criteria, String orderProperty, boolean descending, Map<String, String> aliases) {
		String aliasedProperty = createAliasIfNeeded(criteria, orderProperty, aliases);
		if (descending) {
			criteria.addOrder(Order.desc(aliasedProperty));
		} else {
			criteria.addOrder(Order.asc(aliasedProperty));
		}

	}

	private String createAliasIfNeeded(Criteria criteria, String propertyName, Map<String, String> aliases) {
		int dotCount = StringUtils.countMatches(propertyName, '.');
		if (dotCount > 2 || (dotCount == 2 && !propertyName.endsWith(".id"))) {
			// multi nesting detected, need special consideration to generate multiple
			// aliases unless there are only 2 levels and the third property is the id field
			return createMultiNestedAliases(criteria, propertyName, aliases);
		} else if (dotCount == 1) {
			// alias needed, one level of nesting is used
			String nestedProperty = propertyName.substring(0, propertyName.indexOf('.'));
			Optional<String> alias = createAlias(nestedProperty, aliases);
			// alias is new and needs to be added
			if (alias.isPresent()) {
				criteria.createAlias(nestedProperty, alias.get());
				aliases.put(nestedProperty, alias.get());
			}
			return aliases.get(nestedProperty) + propertyName.substring(propertyName.indexOf('.'));
		} else {
			// no aliasing needed, use the property name as is
			return propertyName;
		}
	}

	private Optional<String> createAlias(String nestedProperty, Map<String, String> aliases) {
		return createAlias(nestedProperty, 1, aliases);
	}

	private Optional<String> createAlias(String nestedProperty, int i, Map<String, String> aliases) {
		String alias;
		if (aliases.containsKey(nestedProperty)) {
			// signal alias does not need to be created, it already exists
			return Optional.empty();
		} else {
			if (nestedProperty.length() > i) {
				// use substring of property as alias
				alias = nestedProperty.substring(0, i);
			} else {
				// reached max length for the property without finding a unique alias, using
				// random alias
				LogEvent.logWarn(this.getClass().getSimpleName(), "createAlias()",
						"this alias is going to be a poorly named alias as the string length has been reached.");
				alias = createRandomAlias();
			}
			if (aliases.containsValue(alias)) {
				// recurse to try and find an unused alias,
				return createAlias(nestedProperty, ++i, aliases);
			} else {
				// unique alias found, end recursion
				return Optional.of(alias);
			}
		}

	}

	private String createRandomAlias() {
		return RandomStringUtils.randomAlphabetic(RANDOM_ALIAS_LENGTH);
	}

	private String createMultiNestedAliases(Criteria criteria, String propertyName, Map<String, String> aliases) {
		String alias;
		String newPropertyName = propertyName;
		while (true) {
			String[] properties = newPropertyName.split("\\.");
			// create alias for one level of nesting
			alias = createMarkedAlias(criteria, properties[0] + "." + properties[1], aliases);
			// mark nesting level that has just been completed
			newPropertyName = newPropertyName.replaceFirst("\\.", MULTI_NESTED_MARKING);
			// check if there is another level of nesting
			if (newPropertyName.contains(".")) {
				// replace the property name with it's alias
				newPropertyName = alias.replaceFirst("\\.", MULTI_NESTED_MARKING)
						+ newPropertyName.substring(newPropertyName.indexOf('.'));
			} else {
				break;
			}
		}
		return alias;
	}

	private String createMarkedAlias(Criteria criteria, String propertyName, Map<String, String> aliases) {
		String nestedProperty = propertyName.substring(0, propertyName.indexOf('.'));
		// replace properties that have been marked as done with "." for properly adding
		// the criteria
		nestedProperty = nestedProperty.replace(MULTI_NESTED_MARKING, ".");
		Optional<String> alias = createMarkedAlias(nestedProperty, aliases);
		// alias is new and needs to be added
		if (alias.isPresent()) {
			criteria.createAlias(nestedProperty, alias.get());
			aliases.put(nestedProperty, alias.get());
		}
		return aliases.get(nestedProperty) + propertyName.substring(propertyName.indexOf('.'));

	}

	private Optional<String> createMarkedAlias(String nestedProperty, Map<String, String> aliases) {
		return createMarkedAlias(nestedProperty, 1, aliases);
	}

	private Optional<String> createMarkedAlias(String nestedProperty, int i, Map<String, String> aliases) {
		String alias;
		if (aliases.containsKey(nestedProperty)) {
			// signal alias does not need to be created, it already exists
			return Optional.empty();
		} else {
			if (nestedProperty.length() > i) {
				if (nestedProperty.contains(".")) {
					// use substring of second property as alias (property after the first ".")
					alias = nestedProperty.substring(nestedProperty.indexOf('.') + 1,
							nestedProperty.indexOf('.') + 1 + i);
				} else {
					// use substring of property as alias
					alias = nestedProperty.substring(0, i);
				}
			} else {
				// reached max length for the property without finding a unique alias, using
				// random alias
				LogEvent.logWarn(this.getClass().getSimpleName(), "createMultiAlias()",
						"this alias is going to be a poor alias as the string length has been reached.");
				alias = createRandomAlias();

			}
			if (aliases.containsValue(alias)) {
				// recurse to try and find an unused alias,
				return createAlias(nestedProperty, ++i, aliases);
			} else {
				// unique alias found, end recursion
				return Optional.of(alias);
			}
		}
	}

	@Override
	public PK insert(T object) {
		Session session = sessionFactory.getCurrentSession();
		return (PK) session.save(object);
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
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
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
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);
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
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(sql);

			List results = query.list();
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old

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
	@Deprecated
	public String getTablePrefix(String table) {
		return table.toLowerCase() + ".";
	}

	protected void handleException(Exception e, String method) throws LIMSRuntimeException {
		LogEvent.logErrorStack(this.getClass().getSimpleName(), method, e);
		throw new LIMSRuntimeException("Error in " + this.getClass().getSimpleName() + " " + method, e);
	}

	@Deprecated
	protected void closeSession() {
		// sessionFactory.getCurrentSession().flush(); // CSL remove old
		// sessionFactory.getCurrentSession().clear(); // CSL remove old
	}
}