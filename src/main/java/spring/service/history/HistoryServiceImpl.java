package spring.service.history;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	@Transactional(readOnly = true)
	public List getHistoryByRefIdAndRefTableId(History history) throws LIMSRuntimeException {
		return baseObjectDAO.getHistoryByRefIdAndRefTableId(history);
	}

	@Override
	@Transactional(readOnly = true)
	public List getHistoryByRefIdAndRefTableId(String id, String table) throws LIMSRuntimeException {
		return baseObjectDAO.getHistoryByRefIdAndRefTableId(id, table);
	}
}
