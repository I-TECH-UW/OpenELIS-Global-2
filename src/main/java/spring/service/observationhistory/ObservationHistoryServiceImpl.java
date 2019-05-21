package spring.service.observationhistory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.sample.valueholder.Sample;

@Service
public class ObservationHistoryServiceImpl extends BaseObjectServiceImpl<ObservationHistory>
		implements ObservationHistoryService {
	@Autowired
	protected ObservationHistoryDAO baseObjectDAO;

	ObservationHistoryServiceImpl() {
		super(ObservationHistory.class);
	}

	@Override
	protected ObservationHistoryDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional
	public List<ObservationHistory> getAll(Patient patient, Sample sample) {
		return baseObjectDAO.getAll(patient, sample);
	}
}
