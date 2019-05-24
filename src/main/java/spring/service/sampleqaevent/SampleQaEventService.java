package spring.service.sampleqaevent;

import java.lang.String;
import java.sql.Date;
import java.util.List;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;

public interface SampleQaEventService extends BaseObjectService<SampleQaEvent> {
	void getData(SampleQaEvent sampleQaEvent);

	SampleQaEvent getData(String sampleQaEventId);

	void deleteData(List sampleQaEvents);

	void updateData(SampleQaEvent sampleQaEvent);

	boolean insertData(SampleQaEvent sampleQaEvent);

	List<SampleQaEvent> getAllUncompleatedEvents();

	List<SampleQaEvent> getSampleQaEventsBySample(Sample sample);

	List getSampleQaEventsBySample(SampleQaEvent sampleQaEvent);

	List<SampleQaEvent> getSampleQaEventsByUpdatedDate(Date lowDate, Date highDate);

	SampleQaEvent getSampleQaEventBySampleAndQaEvent(SampleQaEvent sampleQaEvent);
}
