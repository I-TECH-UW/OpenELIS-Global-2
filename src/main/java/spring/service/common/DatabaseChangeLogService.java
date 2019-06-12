package spring.service.common;

import us.mn.state.health.lims.common.valueholder.DatabaseChangeLog;

//public interface DatabaseChangeLogService extends BaseObjectService<DatabaseChangeLog, String> {

public interface DatabaseChangeLogService {
	DatabaseChangeLog getLastExecutedChange();
}
