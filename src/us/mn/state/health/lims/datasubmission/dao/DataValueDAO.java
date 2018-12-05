package us.mn.state.health.lims.datasubmission.dao;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.datasubmission.valueholder.DataValue;

public interface DataValueDAO extends BaseDAO {
	
	public void getData(DataValue dataValue) throws LIMSRuntimeException;
	
	public DataValue getDataValue(String id) throws LIMSRuntimeException;
	
	public boolean insertData(DataValue dataValue) throws LIMSRuntimeException;
	
	public void updateData(DataValue dataValue) throws LIMSRuntimeException;

}
