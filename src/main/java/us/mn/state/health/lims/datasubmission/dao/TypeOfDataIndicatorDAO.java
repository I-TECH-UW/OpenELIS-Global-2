package us.mn.state.health.lims.datasubmission.dao;

import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.datasubmission.valueholder.TypeOfDataIndicator;

public interface TypeOfDataIndicatorDAO extends BaseDAO {

	public void getData(TypeOfDataIndicator typeOfIndicator) throws LIMSRuntimeException;
	
	public TypeOfDataIndicator getTypeOfDataIndicator(String id) throws LIMSRuntimeException;
	
	public List<TypeOfDataIndicator> getAllTypeOfDataIndicator() throws LIMSRuntimeException;
	
	public boolean insertData(TypeOfDataIndicator typeOfIndicator) throws LIMSRuntimeException;
	
	public void updateData(TypeOfDataIndicator typeOfIndicator) throws LIMSRuntimeException;
}
