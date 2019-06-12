package spring.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.mn.state.health.lims.common.dao.DatabaseChangeLogDAO;
import us.mn.state.health.lims.common.valueholder.DatabaseChangeLog;

@Service
//public class DatabaseChangeLogServiceImpl extends BaseObjectServiceImpl<DatabaseChangeLog, String> implements DatabaseChangeLogService {
public class DatabaseChangeLogServiceImpl implements DatabaseChangeLogService {
	@Autowired
	protected DatabaseChangeLogDAO baseObjectDAO;

//	public DatabaseChangeLogServiceImpl() {
//		super(DatabaseChangeLog.class);
//	}

//	@Override
	protected DatabaseChangeLogDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public DatabaseChangeLog getLastExecutedChange() {
		return getBaseObjectDAO().getLastExecutedChange();
	}
}
