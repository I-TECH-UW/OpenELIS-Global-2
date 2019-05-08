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
import java.util.Map;
import java.util.Optional;

import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.valueholder.BaseObject;

/**
 * @author caleb
 *
 * @param <T> the type of data object that this returns
 */
public interface BaseDAO<T extends BaseObject> {

	/**
	 * @param id
	 * @return the object corresponding with the id
	 */
	Optional<T> get(String id);

	/**
	 * @return all data type for the object type
	 */
	List<T> getAll();

	/**
	 * @param propertyName  the property that must match
	 * @param propertyValue the value the property must equal
	 * @return List of all matching entries
	 */
	List<T> getAllMatching(String propertyName, Object propertyValue);

	/**
	 * @param propertyValues Key Value pairs where key is the property name and
	 *                       value is the value it must match
	 * @return List of all matching entries
	 */
	List<T> getAllMatching(Map<String, Object> propertyValues);

	/**
	 * @param orderProperty the property to order by
	 * @param descending    Set to true to order by descending, false for order by
	 *                      ascending
	 * @return List of all ordered entries
	 */
	List<T> getAllOrdered(String orderProperty, boolean descending);

	/**
	 * @param orderProperties the properties to order by starting with the first
	 *                        entry
	 * @param descending      Set to true to order by descending, false for order by
	 *                        ascending
	 * @return List of all ordered entries
	 */
	List<T> getAllOrdered(List<String> orderProperties, boolean descending);

	/**
	 * @param propertyName  the property that must match
	 * @param propertyValue the value the property must equal
	 * @param orderProperty the property to order by
	 * @param descending    Set to true to order by descending, false for order by
	 *                      ascending
	 * @return List of all ordered matching entries
	 */
	List<T> getAllMatchingOrdered(String propertyName, Object propertyValue, String orderProperty, boolean descending);

	/**
	 * @param propertyName    the property that must match
	 * @param propertyValue   the value the property must equal
	 * @param orderProperties the properties to order by, starting with the first
	 *                        entry
	 * @param descending      Set to true to order by descending, false for order by
	 *                        ascending
	 * @return List of all ordered matching entries
	 */
	List<T> getAllMatchingOrdered(String propertyName, Object propertyValue, List<String> orderProperties,
			boolean descending);

	/**
	 * @param propertyValues Key Value pairs where key is the property name and
	 *                       value is the value it must match
	 * @param orderProperty  the property to order by
	 * @param descending     Set to true to order by descending, false for order by
	 *                       ascending
	 * @return List of all ordered matching entries
	 */
	List<T> getAllMatchingOrdered(Map<String, Object> propertyValues, String orderProperty, boolean descending);

	/**
	 * @param propertyValues  Key Value pairs where key is the property name and
	 *                        value is the value it must match
	 * @param orderProperties the properties to order by, starting with the first
	 *                        entry
	 * @param descending      Set to true to order by descending, false for order by
	 *                        ascending
	 * @return List of all ordered matching entries
	 */
	List<T> getAllMatchingOrdered(Map<String, Object> propertyValues, List<String> orderProperties, boolean descending);

	/**
	 * @param pageNumber 0 indexed page number to get results from
	 * @return A page of results sorted by id. If length is 1 more than page size,
	 *         this signifies there is a next page
	 */
	List<T> getPage(int pageNumber);

	/**
	 * @param propertyName  the property that must match
	 * @param propertyValue the value the property must equal
	 * @param pageNumber    0 indexed page number to get results from
	 * @return A page of results. If length is 1 more than page size, this signifies
	 *         there is a next page
	 */
	List<T> getMatchingPage(String propertyName, Object propertyValue, int pageNumber);

	/**
	 * @param propertyValues Key Value pairs where key is the property name and
	 *                       value is the value it must match
	 * @param pageNumber     0 indexed page number to get results from
	 * @return A page of results. If length is 1 more than page size, this signifies
	 *         there is a next page
	 */
	List<T> getMatchingPage(Map<String, Object> propertyValues, int pageNumber);

	/**
	 * @param orderProperty the property to order by
	 * @param descending    Set to true to order by descending, false for order by
	 *                      ascending
	 * @param pageNumber    0 indexed page number to get results from
	 * @return A page of results. If length is 1 more than page size, this signifies
	 *         there is a next page
	 */
	List<T> getOrderedPage(String orderProperty, boolean descending, int pageNumber);

	/**
	 * @param orderProperties the properties to order by, starting with the first
	 *                        entry
	 * @param descending      Set to true to order by descending, false for order by
	 *                        ascending
	 * @param pageNumber      0 indexed page number to get results from
	 * @return A page of results. If length is 1 more than page size, this signifies
	 *         there is a next page
	 */
	List<T> getOrderedPage(List<String> orderProperties, boolean descending, int pageNumber);

	/**
	 * @param propertyName  the property that must match
	 * @param propertyValue the value the property must equal
	 * @param orderProperty the property to order by
	 * @param descending    Set to true to order by descending, false for order by
	 *                      ascending
	 * @param pageNumber    0 indexed page number to get results from
	 * @return A page of results. If length is 1 more than page size, this signifies
	 *         there is a next page
	 */
	List<T> getMatchingOrderedPage(String propertyName, Object propertyValue, String orderProperty, boolean descending,
			int pageNumber);

	/**
	 * @param propertyName    the property that must match
	 * @param propertyValue   the value the property must equal
	 * @param orderProperties the properties to order by, starting with the first
	 *                        entry
	 * @param descending      Set to true to order by descending, false for order by
	 *                        ascending
	 * @param pageNumber      0 indexed page number to get results from
	 * @return A page of results. If length is 1 more than page size, this signifies
	 *         there is a next page
	 */
	List<T> getMatchingOrderedPage(String propertyName, Object propertyValue, List<String> orderProperties,
			boolean descending, int pageNumber);

	/**
	 * @param propertyValues Key Value pairs where key is the property name and
	 *                       value is the value it must match
	 * @param orderProperty  the property to order by
	 * @param descending     Set to true to order by descending, false for order by
	 *                       ascending
	 * @param pageNumber     0 indexed page number to get results from
	 * @return A page of results. If length is 1 more than page size, this signifies
	 *         there is a next page
	 */
	List<T> getMatchingOrderedPage(Map<String, Object> propertyValues, String orderProperty, boolean descending,
			int pageNumber);

	/**
	 * @param propertyValues  Key Value pairs where key is the property name and
	 *                        value is the value it must match
	 * @param orderProperties the properties to order by, starting with the first
	 *                        entry
	 * @param descending      Set to true to order by descending, false for order by
	 *                        ascending
	 * @param pageNumber      0 indexed page number to get results from
	 * @return A page of results. If length is 1 more than page size, this signifies
	 *         there is a next page
	 */
	List<T> getMatchingOrderedPage(Map<String, Object> propertyValues, List<String> orderProperties, boolean descending,
			int pageNumber);

	/**
	 * @param object the data to insert
	 * @return the id of the inserted object
	 */
	String insert(T object);

	/**
	 * @param objects the data to insert
	 * @return the ids of the inserted objects
	 */
	List<String> insertAll(List<T> objects);

	/**
	 * @param object the new data to update the database with. Must have an id
	 *               parameter
	 * @return the object as it was saved to the database
	 */
	Optional<T> update(T object);

	/**
	 * @param objects the new data to update the database with. Must have an id
	 *                parameter
	 * @return the objects as they were saved to the database
	 */
	List<Optional<T>> updateAll(List<T> objects);

	/**
	 * @param object the data to delete from the database. Must have primary key
	 *               fields filled in
	 */
	void delete(T object);

	/**
	 * @param id of the value to delete
	 */
	void delete(String id);

	/**
	 * @param objects List of all objects to delete from the database. Must have
	 *                primary key fields filled in
	 */
	void deleteAll(List<T> objects);

	/**
	 * @param objectIds of the values to delete
	 */
	void deleteAll(String[] objectIds);

	/**
	 * @return the number of rows
	 */
	public Integer getCount();

	/**
	 * @param id the id to start from
	 * @return list of the object corresponding to the next two ids ( if they exist)
	 */
	public List<T> getNext(String id);

	/**
	 * @param id the id to start from
	 * @return list of the object corresponding to the previous two ids ( if they
	 *         exist)
	 */
	public List<T> getPrevious(String id);

	/**
	 * @deprecated (simpler method replacing this one, call getNext(id) instead
	 */
	@Deprecated
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException;

	/**
	 * @deprecated (simpler method replacing this one, call getPrevious(id) instead
	 */
	@Deprecated
	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException;

	/**
	 * @deprecated (simpler method replacing this one, call getCount() instead
	 */
	@Deprecated
	// bugzilla 1411
	public Integer getTotalCount(String table, Class clazz) throws LIMSRuntimeException;

}