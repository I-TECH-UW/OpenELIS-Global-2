package spring.service.citystatezip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.citystatezip.dao.StateViewDAO;
import us.mn.state.health.lims.citystatezip.valueholder.StateView;

@Service
public class StateViewServiceImpl extends BaseObjectServiceImpl<StateView, String> implements StateViewService {
	@Autowired
	protected StateViewDAO baseObjectDAO;

	StateViewServiceImpl() {
		super(StateView.class);
	}

	@Override
	protected StateViewDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
