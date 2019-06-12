package spring.service.history;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.audittrail.dao.HistoryDAO;
import us.mn.state.health.lims.audittrail.valueholder.History;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;

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
	
	public List getHistoryByRefIdAndRefTableId(History history) throws LIMSRuntimeException {
		return baseObjectDAO.getHistoryByRefIdAndRefTableId(history);
	}
}
