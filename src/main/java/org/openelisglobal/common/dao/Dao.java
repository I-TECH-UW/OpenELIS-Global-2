package org.openelisglobal.common.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.openelisglobal.common.valueholder.BaseObject;

/**
 * @author caleb
 *
 * @param <T>
 */
public interface Dao<T extends BaseObject> {

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
     * @param columnValues Key Value pairs where key is the column name and value is
     *                     the value it must match
     * @return List of all matching entries
     */
    List<T> getAllWhereMatch(Map<String, Object> columnValues);

    /**
     * @param orderByColumn the column to order by
     * @param descending    Set to true to order by descending, false for order by
     *                      ascending
     * @return List of all ordered entries
     */
    List<T> getAllOrderdBy(String orderByColumn, boolean descending);

    /**
     * @param orderByColumns the columns to order by starting with the first entry
     * @param descending     Set to true to order by descending, false for order by
     *                       ascending
     * @return List of all ordered entries
     */
    List<T> getAllOrderdBy(List<String> orderByColumns, boolean descending);

    /**
     * @param columnValues  Key Value pairs where key is the column name and value
     *                      is the value it must match
     * @param orderByColumn the column to order by
     * @param descending    Set to true to order by descending, false for order by
     *                      ascending
     * @return List of all ordered matching entries
     */
    List<T> getAllWhereMatchOrderBy(Map<String, Object> columnValues, String orderByColumn, boolean descending);

    /**
     * @param object the data to insert
     * @return the id of the inserted object
     */
    String insert(T object);

    /**
     * @param object the new data to update the database with. Must have an id
     *               parameter
     * @return the object as it was saved to the database
     */
    Optional<T> update(T object);

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
    void delete(List<T> objects);

    /**
     * @param objectIds of the values to delete
     */
    void delete(String[] objectIds);

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

}
