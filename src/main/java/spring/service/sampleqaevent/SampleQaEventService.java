package spring.service.sampleqaevent;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;

public interface SampleQaEventService extends BaseObjectService<SampleQaEvent> {

	List<SampleQaEvent> getSampleQaEventsBySample(Sample sample);
}
