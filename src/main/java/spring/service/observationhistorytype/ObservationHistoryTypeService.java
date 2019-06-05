package spring.service.observationhistorytype;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;

public interface ObservationHistoryTypeService extends BaseObjectService<ObservationHistoryType, String> {
	ObservationHistoryType getByName(String name);
}
