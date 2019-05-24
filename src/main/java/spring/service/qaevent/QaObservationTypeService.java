package spring.service.qaevent;

import java.lang.String;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.qaevent.valueholder.QaObservationType;

public interface QaObservationTypeService extends BaseObjectService<QaObservationType> {
	QaObservationType getQaObservationTypeByName(String typeName);
}
