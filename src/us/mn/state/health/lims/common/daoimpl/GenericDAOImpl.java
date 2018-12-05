package us.mn.state.health.lims.common.daoimpl;

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

import java.io.Serializable;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.Example;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.dao.GenericDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.valueholder.SimpleBaseEntity;
import us.mn.state.health.lims.hibernate.HibernateUtil;

// actually BaseDOAImpl assumes keys can be represented as Strings
public abstract class GenericDAOImpl<Key extends Serializable, Entity extends SimpleBaseEntity<Key>> extends BaseDAOImpl implements BaseDAO, GenericDAO<Key, Entity> {

	/**
	 * This is the class of the rows being saved in the database
	 */
	protected Class<Entity> entityClass;

	/**
	 * this is the (simple) name of the rows to persist.
	 */
	protected String entityName;

	/**
	 * The name of the table in the database (with underscores, not camel case).  Actually this is the name as is it spelled in the reference_tables DB table activity log.
	 * In this table older tables from the original OpenElis (MN) are capitalized while newer ones are not.  Both have underscores in their names.
	 */
	protected String tableName;

	/**
	 * This is basic (simple) name of the DAO class which is used in logging/audit messages.
	 */
	protected String daoName;


	/**
	 * Given classes to save and the name of the table (for logging and audit records).
	 * @param entityClass objects of the rows to save using this DAO
	 * @param tableName table name in DB.  There is probably aw way to ask hibernate for this answer, but we can leave this for now.
	 */
	public GenericDAOImpl(Class<Entity> entityClass, String tableName) {
		this.entityClass = entityClass;
		this.tableName = tableName;
		this.daoName = this.getClass().getSimpleName();
		this.entityName = entityClass.getSimpleName();
	}

    public void delete(List<Entity> entities) throws LIMSRuntimeException {
        throw new UnsupportedOperationException(daoName + ".delete(list) not supported");
        // If you want to be able to delete entites override this method and cCall the other delete method with delete(entities, new MyEntity());
        // delete(entities, new ObservationHistory());
    }


	/***
	 * Delete all the entities in the list and record the auditing information.
	 * @param entities all the entities to delete.
	 * @param oneNew - pass a new empty object for use in the history audit logging processing
	 */
	public void delete(List<Entity> entities, Entity oneNewData) throws LIMSRuntimeException {
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (Entity data: entities) {
				Entity oldData = readEntity(data.getId());

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				auditDAO.saveHistory(oneNewData,oldData,sysUserId,event,tableName);
			}

			for (Entity data: entities) {
				data = readEntity(data.getId());
				HibernateUtil.getSession().delete(data);
				flushAndClear();
			}
		} catch (Exception e) {
			throw createAndLogException("deleteData()", e);
		}
	}

	/**
	 * Just because I hate to say the same thing many times.
	 */
	protected void flushAndClear() {
		HibernateUtil.getSession().flush();
		HibernateUtil.getSession().clear();
	}

	/**
	 * Read all entities from the database.
	 */
	@SuppressWarnings("unchecked")
	public List<Entity> getAll() throws LIMSRuntimeException {
		List<Entity> entities;
		try {
			String sql = "from " + this.entityName;
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			entities = query.list();
			flushAndClear();
		} catch (Exception e) {
			throw createAndLogException("getAll()", e);
		}

		return entities;
	}
	
    /**
     * Read all entities from the database sorted by an appropriate column
     */
    @SuppressWarnings("unchecked")
    public List<Entity> getAllOrderBy(String columnName) throws LIMSRuntimeException {
        List<Entity> entities;
        try {
            String sql = "from " + this.entityName + " ORDER BY " + columnName;
            org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
            entities = query.list();
            flushAndClear();
        } catch (Exception e) {
            throw createAndLogException("getAllOrderBy()", e);
        }

        return entities;
    }	

	/**
	 * Find one entity given an ID
	 */
	@SuppressWarnings("unchecked")
	public Entity getById(Entity entity) throws LIMSRuntimeException {
		try {
			Entity re = (Entity)HibernateUtil.getSession().get(entityClass, entity.getId());
			flushAndClear();
			return re;
		} catch (Exception e) {
			throw createAndLogException("getEntityById()", e);
		}
	}

	/***
	 *  get one entity, by the ID contained in the given Entity, filling in the given entity with the values.
	 */
	public void getData(Entity entity) throws LIMSRuntimeException {
		Entity tmpEntity = readEntity(entity.getId());  // PAH reused readEntity instead of doing the code again.
		if (tmpEntity != null) {
			try {
				PropertyUtils.copyProperties(entity, tmpEntity);
			} catch (Exception e) {
				throw createAndLogException("getData()", e);
			}
		} else {
			entity.setId(null);
		}
	}

	/***
	 * save the given entity into the database
	 */
	@SuppressWarnings("unchecked")
	public boolean insertData(Entity entity) throws LIMSRuntimeException {
		try {
			Key id = (Key) HibernateUtil.getSession().save(entity);
			entity.setId(id);

			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = entity.getSysUserId();
			auditDAO.saveNewHistory(entity,sysUserId,tableName);

			flushAndClear();
		} catch (Exception e) {
			throw createAndLogException("insertData()", e);
		}

		return true;
	}

	/***
	 * update the given existing entity in the database
	 */
	public void updateData(Entity entity) throws LIMSRuntimeException {
		Entity oldData = readEntity(entity.getId());
		Entity newData = entity;

		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = entity.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);

			HibernateUtil.getSession().merge(entity);
			flushAndClear();
			HibernateUtil.getSession().evict(entity);
			HibernateUtil.getSession().refresh(entity);
		} catch (Exception e) {
			throw createAndLogException("updateData()", e);
		}
	}

	/**
	 * Read one entity
	 */
	@SuppressWarnings("unchecked")
	public Entity readEntity(Key id) throws LIMSRuntimeException{
		Entity data = null;
		try {
			data = (Entity)HibernateUtil.getSession().get(entityClass, id);
			flushAndClear();
		} catch (Exception e) {
			throw createAndLogException("readEntity()", e);
		}
		return data;
	}

	/**
	 * Read a list of entities which match those fields(members) in the entity which are filled in.
	 */
	@SuppressWarnings("unchecked")
	public List<Entity> readByExample(Entity entity) throws LIMSRuntimeException {
		List<Entity> results;
		try {
			results = (List<Entity>)HibernateUtil.getSession().createCriteria(entityClass).add(Example.create(entity)).list();
		} catch (Exception e) {
			throw createAndLogException("readByExample()", e);
		}
		return results;
	}

    /**
     * Given a bit of HQL and few other bits find a bunch of the right types of Entities
     * @param the whole HQL with one or more params in the some statement " id = param1 AND title = :param2" etc. in it.
     * @param fieldValue one value to match with
     * @param orderByClause more HQL to finish the entire statement (aka the order by clause).
     * @return a list of
     * @throws LIMSRuntimeException
     */
    protected List<Entity> readByHQL(String hql, String[] fieldValues) throws LIMSRuntimeException {
        try {
            org.hibernate.Query query = HibernateUtil.getSession().createQuery(hql);
            for (int i = 0; i <= fieldValues.length; i++) {
                query.setParameter("param1", fieldValues[i]);
            }

            @SuppressWarnings("unchecked")
            List<Entity> list = query.list();
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();

            return list;

        } catch (Exception e) {
            throw createAndLogException("findByHQL( \"" + hql + "\")", e);
        }
    }


	/**
	 * Utility routine for (1) logging an error and (2) creating a new RuntimeException
	 * @param methodName
	 * @param e
	 * @return new RuntimeException
	 */
	protected LIMSRuntimeException createAndLogException(String methodName, Exception e) {
		LogEvent.logError(daoName, methodName, e.toString());
		// PAHill original code said " Error in " + entityName , but that isn't actually correct.
		return new LIMSRuntimeException("Error in " + daoName + " " + methodName, e);
	}
}
