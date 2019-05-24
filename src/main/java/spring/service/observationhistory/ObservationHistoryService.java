package spring.service.observationhistory;

import java.lang.String;
import java.util.List;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.sample.valueholder.Sample;

public interface ObservationHistoryService extends BaseObjectService<ObservationHistory> {
	ObservationHistory getById(ObservationHistory observation);

	void updateData(ObservationHistory observation);

	boolean insertData(ObservationHistory observation);

	List<ObservationHistory> getAll(Patient patient, Sample sample, String observationHistoryTypeId);

	List<ObservationHistory> getAll(Patient patient, Sample sample);

	void insertOrUpdateData(ObservationHistory observation);

	ObservationHistory getObservationHistoriesBySampleIdAndType(String sampleId, String observationHistoryTypeId);

	List<ObservationHistory> getObservationHistoriesByPatientIdAndType(String patientId,
			String observationHistoryTypeId);

	List<ObservationHistory> getObservationHistoryByDictonaryValues(String dictionaryValue);

	List<ObservationHistory> getObservationHistoriesBySampleItemId(String sampleItemId);

	List<ObservationHistory> getObservationHistoriesByValueAndType(String value, String typeId, String valueType);

	List<ObservationHistory> getObservationHistoriesBySampleId(String sampleId);
}
