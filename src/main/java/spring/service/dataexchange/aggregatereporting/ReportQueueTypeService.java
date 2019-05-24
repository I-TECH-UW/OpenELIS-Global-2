package spring.service.dataexchange.aggregatereporting;

import java.lang.String;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportQueueType;

public interface ReportQueueTypeService extends BaseObjectService<ReportQueueType> {
	ReportQueueType getReportQueueTypeByName(String name);
}
