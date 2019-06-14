package spring.service.qaevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.qaevent.dao.QaObservationDAO;
import us.mn.state.health.lims.qaevent.valueholder.QaObservation;

@Service
public class QaObservationServiceImpl extends BaseObjectServiceImpl<QaObservation, String>
		implements QaObservationService {
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
	@Transactional(readOnly = true)
	public QaObservation getQaObservationByTypeAndObserved(String typeName, String observedType, String observedId) {
		return getBaseObjectDAO().getQaObservationByTypeAndObserved(typeName, observedType, observedId);
	}
}
