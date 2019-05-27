package spring.service.qaevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.qaevent.dao.QaObservationDAO;
import us.mn.state.health.lims.qaevent.valueholder.QaObservation;

@Service
public class QaObservationServiceImpl extends BaseObjectServiceImpl<QaObservation> implements QaObservationService {
	@Autowired
	protected QaObservationDAO baseObjectDAO;

	QaObservationServiceImpl() {
		super(QaObservation.class);
	}

	@Override
	protected QaObservationDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void updateData(QaObservation qaObservation) {
        getBaseObjectDAO().updateData(qaObservation);

	}

	@Override
	public void insertData(QaObservation qaObservation) {
        getBaseObjectDAO().insertData(qaObservation);

	}

	@Override
	public QaObservation getQaObservationByTypeAndObserved(String typeName, String observedType, String observedId) {
        return getBaseObjectDAO().getQaObservationByTypeAndObserved(typeName,observedType,observedId);
	}
}
