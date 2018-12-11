package us.mn.state.health.lims.observationhistorytype.dao;

import us.mn.state.health.lims.common.dao.GenericDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;

public interface ObservationHistoryTypeDAO extends GenericDAO<String, ObservationHistoryType> {

	ObservationHistoryType getByName(String name) throws LIMSRuntimeException;
	// insert additional method unique to Demo. History Type here.
}