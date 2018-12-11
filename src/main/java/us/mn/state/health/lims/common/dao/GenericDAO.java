package us.mn.state.health.lims.common.dao;

import java.io.Serializable;
import java.util.List;

import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.valueholder.SimpleBaseEntity;

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