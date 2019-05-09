package spring.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.mn.state.health.lims.common.dao.DatabaseChangeLogDAO;
import us.mn.state.health.lims.common.valueholder.DatabaseChangeLog;

@Service
public class DatabaseChangeLogServiceImpl extends BaseObjectServiceImpl<DatabaseChangeLog> implements DatabaseChangeLogService {
  @Autowired
  protected DatabaseChangeLogDAO baseObjectDAO;

  DatabaseChangeLogServiceImpl() {
    super(DatabaseChangeLog.class);
  }

  @Override
  protected DatabaseChangeLogDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
