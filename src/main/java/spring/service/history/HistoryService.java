package spring.service.history;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.audittrail.valueholder.History;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;

public interface HistoryService extends BaseObjectService<History, String> {
	
	public List getHistoryByRefIdAndRefTableId(String Id, String Table) throws LIMSRuntimeException;
	public List getHistoryByRefIdAndRefTableId(History history) throws LIMSRuntimeException;
}
