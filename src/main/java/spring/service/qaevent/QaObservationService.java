package spring.service.qaevent;

import java.lang.String;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.qaevent.valueholder.QaObservation;

public interface QaObservationService extends BaseObjectService<QaObservation> {
	void updateData(QaObservation qaObservation);

	void insertData(QaObservation qaObservation);

	QaObservation getQaObservationByTypeAndObserved(String typeName, String observedType, String observedId);
}
