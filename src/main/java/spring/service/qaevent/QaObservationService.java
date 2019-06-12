package spring.service.qaevent;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.qaevent.valueholder.QaObservation;

public interface QaObservationService extends BaseObjectService<QaObservation, String> {
	QaObservation getQaObservationByTypeAndObserved(String typeName, String observedType, String observedId);
}
