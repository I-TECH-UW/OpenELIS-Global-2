package spring.service.observationhistorytype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.observationhistorytype.dao.ObservationHistoryTypeDAO;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;

@Service
public class ObservationHistoryTypeServiceImpl extends BaseObjectServiceImpl<ObservationHistoryType> implements ObservationHistoryTypeService {
	@Autowired
	protected ObservationHistoryTypeDAO baseObjectDAO;

	ObservationHistoryTypeServiceImpl() {
		super(ObservationHistoryType.class);
	}

	@Override
	protected ObservationHistoryTypeDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public ObservationHistoryType getByName(String name) {
        return getBaseObjectDAO().getByName(name);
	}
}
