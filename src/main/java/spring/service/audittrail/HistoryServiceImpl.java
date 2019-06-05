package spring.service.audittrail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.audittrail.dao.HistoryDAO;
import us.mn.state.health.lims.audittrail.valueholder.History;

@Service
public class HistoryServiceImpl extends BaseObjectServiceImpl<History, String> implements HistoryService {
	@Autowired
	protected HistoryDAO baseObjectDAO;

	HistoryServiceImpl() {
		super(History.class);
	}

	@Override
	protected HistoryDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
