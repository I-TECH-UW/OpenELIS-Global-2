package spring.service.observationhistorytype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.observationhistorytype.dao.ObservationHistoryTypeDAO;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;

@Service
public class ObservationHistoryTypeServiceImpl extends BaseObjectServiceImpl<ObservationHistoryType, String> implements ObservationHistoryTypeService {
	@Autowired
	protected ObservationHistoryTypeDAO baseObjectDAO;

	public ObservationHistoryTypeServiceImpl() {
		super(ObservationHistoryType.class);
	}

	@Override
	protected ObservationHistoryTypeDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public ObservationHistoryType getByName(String name) {
		return getBaseObjectDAO().getByName(name);
	}
}
