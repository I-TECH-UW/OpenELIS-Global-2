package us.mn.state.health.lims.datasubmission.dao;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.datasubmission.valueholder.DataResource;

public interface DataResourceDAO extends BaseDAO {
	
	public void getData(DataResource resource) throws LIMSRuntimeException;
	
	public DataResource getDataResource(String id) throws LIMSRuntimeException;
	
	public boolean insertData(DataResource resource) throws LIMSRuntimeException;
	
	public void updateData(DataResource resource) throws LIMSRuntimeException;

}
