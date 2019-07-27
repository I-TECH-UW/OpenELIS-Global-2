package org.openelisglobal.common.dao;

import java.io.Serializable;
import java.util.List;

import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.valueholder.SimpleBaseEntity;

public interface GenericDAO<Key extends Serializable, Entity extends SimpleBaseEntity<Key>> {

	void delete(List<Entity> entities) throws LIMSRuntimeException;

	List<Entity> getAll() throws LIMSRuntimeException;
	
	List<Entity> getAllOrderBy(String columnName) throws LIMSRuntimeException;

	Entity getById(Entity analyzer) throws LIMSRuntimeException;

	void getData(Entity analyzer) throws LIMSRuntimeException;

	boolean insertData(Entity analyzer) throws LIMSRuntimeException;

	void updateData(Entity analyzer) throws LIMSRuntimeException;

	Entity readEntity(Key id) throws LIMSRuntimeException;

	List<Entity> readByExample(Entity entity) throws LIMSRuntimeException;	
}