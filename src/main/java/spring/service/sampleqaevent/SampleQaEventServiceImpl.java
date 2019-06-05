package spring.service.sampleqaevent;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleqaevent.dao.SampleQaEventDAO;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;

@Service
public class SampleQaEventServiceImpl extends BaseObjectServiceImpl<SampleQaEvent, String> implements SampleQaEventService {
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
	public List<SampleQaEvent> getSampleQaEventsBySample(Sample sample) {
		return baseObjectDAO.getAllMatching("sample.id", sample.getId());
	}

	@Override
	public void getData(SampleQaEvent sampleQaEvent) {
		getBaseObjectDAO().getData(sampleQaEvent);

	}

	@Override
	public SampleQaEvent getData(String sampleQaEventId) {
		return getBaseObjectDAO().getData(sampleQaEventId);
	}

	@Override
	public void deleteData(List sampleQaEvents) {
		getBaseObjectDAO().deleteData(sampleQaEvents);

	}

	@Override
	public void updateData(SampleQaEvent sampleQaEvent) {
		getBaseObjectDAO().updateData(sampleQaEvent);

	}

	@Override
	public boolean insertData(SampleQaEvent sampleQaEvent) {
		return getBaseObjectDAO().insertData(sampleQaEvent);
	}

	@Override
	public List<SampleQaEvent> getAllUncompleatedEvents() {
		return getBaseObjectDAO().getAllUncompleatedEvents();
	}

	@Override
	public List getSampleQaEventsBySample(SampleQaEvent sampleQaEvent) {
		return getBaseObjectDAO().getSampleQaEventsBySample(sampleQaEvent);
	}

	@Override
	public List<SampleQaEvent> getSampleQaEventsByUpdatedDate(Date lowDate, Date highDate) {
		return getBaseObjectDAO().getSampleQaEventsByUpdatedDate(lowDate, highDate);
	}

	@Override
	public SampleQaEvent getSampleQaEventBySampleAndQaEvent(SampleQaEvent sampleQaEvent) {
		return getBaseObjectDAO().getSampleQaEventBySampleAndQaEvent(sampleQaEvent);
	}
}
