package spring.service.observationhistorytype;

import java.lang.String;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;

public interface ObservationHistoryTypeService extends BaseObjectService<ObservationHistoryType> {
	ObservationHistoryType getByName(String name);
}
