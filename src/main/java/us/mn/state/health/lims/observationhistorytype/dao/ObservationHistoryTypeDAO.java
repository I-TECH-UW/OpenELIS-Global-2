package us.mn.state.health.lims.observationhistorytype.dao;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;

public interface ObservationHistoryTypeDAO extends BaseDAO<ObservationHistoryType> {

	ObservationHistoryType getByName(String name) throws LIMSRuntimeException;
	// insert additional method unique to Demo. History Type here.
}