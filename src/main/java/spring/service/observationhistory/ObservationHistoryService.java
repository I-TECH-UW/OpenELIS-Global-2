package spring.service.observationhistory;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.sample.valueholder.Sample;

public interface ObservationHistoryService extends BaseObjectService<ObservationHistory> {

	List<ObservationHistory> getAll(Patient patient, Sample sample);
}
