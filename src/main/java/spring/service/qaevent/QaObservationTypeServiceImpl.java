package spring.service.qaevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.qaevent.dao.QaObservationTypeDAO;
import us.mn.state.health.lims.qaevent.valueholder.QaObservationType;

@Service
public class QaObservationTypeServiceImpl extends BaseObjectServiceImpl<QaObservationType, String> implements QaObservationTypeService {
	@Autowired
	protected QaObservationTypeDAO baseObjectDAO;

	QaObservationTypeServiceImpl() {
		super(QaObservationType.class);
	}

	@Override
	protected QaObservationTypeDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public QaObservationType getQaObservationTypeByName(String typeName) {
        return getBaseObjectDAO().getQaObservationTypeByName(typeName);
	}
}
