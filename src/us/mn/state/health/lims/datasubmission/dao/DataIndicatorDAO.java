package us.mn.state.health.lims.datasubmission.dao;

import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.datasubmission.valueholder.DataIndicator;
import us.mn.state.health.lims.datasubmission.valueholder.TypeOfDataIndicator;

public interface DataIndicatorDAO extends BaseDAO {
	
	public void getData(DataIndicator indicator) throws LIMSRuntimeException;
	
	public DataIndicator getIndicator(String id) throws LIMSRuntimeException;
	
	public DataIndicator getIndicatorByTypeYearMonth(TypeOfDataIndicator type, int year, int month) throws LIMSRuntimeException;

	public List<DataIndicator> getIndicatorsByStatus(String status) throws LIMSRuntimeException;
	
	public boolean insertData(DataIndicator dataIndicator) throws LIMSRuntimeException;
	
	public void updateData(DataIndicator dataIndicator) throws LIMSRuntimeException;

}
