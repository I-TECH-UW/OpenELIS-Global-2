package spring.service.sampleqaevent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleqaevent.dao.SampleQaEventDAO;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;

@Service
public class SampleQaEventServiceImpl extends BaseObjectServiceImpl<SampleQaEvent> implements SampleQaEventService {
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
		return baseObjectDAO.getAllMatching("aqe.sample", sample.getId());
	}
}
