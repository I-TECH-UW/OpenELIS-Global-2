package spring.service.dataexchange.aggregatereporting;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportQueueType;

public interface ReportQueueTypeService extends BaseObjectService<ReportQueueType, String> {
	ReportQueueType getReportQueueTypeByName(String name);
}
