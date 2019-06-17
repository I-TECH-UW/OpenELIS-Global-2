package spring.service.sampleqaevent;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleqaevent.dao.SampleQaEventDAO;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;

@Service
public class SampleQaEventServiceImpl extends BaseObjectServiceImpl<SampleQaEvent, String>
		implements SampleQaEventService {
	@Autowired
	protected SampleQaEventDAO baseObjectDAO;

	SampleQaEventServiceImpl() {
		super(SampleQaEvent.class);
	}

	@Override
	protected SampleQaEventDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<SampleQaEvent> getSampleQaEventsBySample(Sample sample) {
		return baseObjectDAO.getAllMatching("sample.id", sample.getId());
	}

	@Override
	@Transactional(readOnly = true)
	public void getData(SampleQaEvent sampleQaEvent) {
		getBaseObjectDAO().getData(sampleQaEvent);

	}

	@Override
	@Transactional(readOnly = true)
	public SampleQaEvent getData(String sampleQaEventId) {
		return getBaseObjectDAO().getData(sampleQaEventId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SampleQaEvent> getAllUncompleatedEvents() {
		return getBaseObjectDAO().getAllUncompleatedEvents();
	}

	@Override
	@Transactional(readOnly = true)
	public List getSampleQaEventsBySample(SampleQaEvent sampleQaEvent) {
		return getBaseObjectDAO().getSampleQaEventsBySample(sampleQaEvent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SampleQaEvent> getSampleQaEventsByUpdatedDate(Date lowDate, Date highDate) {
		return getBaseObjectDAO().getSampleQaEventsByUpdatedDate(lowDate, highDate);
	}

	@Override
	@Transactional(readOnly = true)
	public SampleQaEvent getSampleQaEventBySampleAndQaEvent(SampleQaEvent sampleQaEvent) {
		return getBaseObjectDAO().getSampleQaEventBySampleAndQaEvent(sampleQaEvent);
	}
}
