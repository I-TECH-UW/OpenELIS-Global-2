package spring.service.observationhistory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import spring.service.dictionary.DictionaryService;
import spring.service.observationhistorytype.ObservationHistoryTypeService;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory.ValueType;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.sample.valueholder.Sample;

@Service
@DependsOn({ "springContext" })
public class ObservationHistoryServiceImpl extends BaseObjectServiceImpl<ObservationHistory>
		implements ObservationHistoryService {

	public enum ObservationType {
		INITIAL_SAMPLE_CONDITION("initialSampleCondition"), PAYMENT_STATUS("paymentStatus"),
		REQUEST_DATE("requestDate"), NEXT_VISIT_DATE("nextVisitDate"), REFERRING_SITE("referringSite"),
		REFERRERS_PATIENT_ID("referrersPatientId"), BILLING_REFERENCE_NUMBER("billingRefNumber"),
		TEST_LOCATION_CODE("testLocationCode"), TEST_LOCATION_CODE_OTHER("testLocationCodeOther"), PROGRAM("program");

		private String dbName;

		private ObservationType(String dbName) {
			this.dbName = dbName;
		}

		public String getDatabaseName() {
			return dbName;
		}

	}

	private static ObservationHistoryService INSTANCE;

	private final Map<ObservationType, String> observationTypeToIdMap = new HashMap<>();

	@Autowired
	private ObservationHistoryDAO baseObjectDAO;

	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private ObservationHistoryTypeService ohtService;

	ObservationHistoryServiceImpl() {
		super(ObservationHistory.class);
	}

	@PostConstruct
	private void registerInstance() {
		INSTANCE = this;
	}

	public static ObservationHistoryService getInstance() {
		return INSTANCE;
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

	@Override
	public String getObservationTypeIdForType(ObservationType type) {
		if (observationTypeToIdMap.isEmpty()) {
			initialize();
		}
		return observationTypeToIdMap.get(type);
	}

	@Override
	public List<ObservationHistory> getObservationsByTypeAndValue(ObservationType type, String value) {
		if (observationTypeToIdMap.isEmpty()) {
			initialize();
		}
		String typeId = getObservationTypeIdForType(type);

		if (!GenericValidator.isBlankOrNull(typeId)) {
			return baseObjectDAO.getObservationHistoriesByValueAndType(value, typeId, ValueType.LITERAL.getCode());
		} else {
			return null;
		}
	}

	@Override
	public String getValueForSample(ObservationType type, String sampleId) {
		ObservationHistory observation = getObservationForSample(type, sampleId);
		return getValueForObservation(observation);
	}

	private String getValueForObservation(ObservationHistory observation) {
		if (observation != null) {
			if (observation.getValueType().equals(ObservationHistory.ValueType.LITERAL.getCode())) {
				return observation.getValue();
			} else {
				if (!GenericValidator.isBlankOrNull(observation.getValue())) {
					return dictionaryService.getDataForId(observation.getValue()).getLocalizedName();
				}
			}
		}

		return null;
	}

	@Override
	public String getMostRecentValueForPatient(ObservationType type, String patientId) {
		ObservationHistory observation = getLastObservationForPatient(type, patientId);
		return getValueForObservation(observation);
	}

	@Override
	public String getRawValueForSample(ObservationType type, String sampleId) {
		ObservationHistory observation = getObservationForSample(type, sampleId);
		return observation != null ? observation.getValue() : null;
	}

	@Override
	public ObservationHistory getObservationForSample(ObservationType type, String sampleId) {
		if (observationTypeToIdMap.isEmpty()) {
			initialize();
		}
		String typeId = getObservationTypeIdForType(type);

		if (!GenericValidator.isBlankOrNull(typeId)) {
			return baseObjectDAO.getObservationHistoriesBySampleIdAndType(sampleId, typeId);
		} else {
			return null;
		}

	}

	@Override
	public ObservationHistory getLastObservationForPatient(ObservationType type, String patientId) {
		if (observationTypeToIdMap.isEmpty()) {
			initialize();
		}

		String typeId = getObservationTypeIdForType(type);

		if (!GenericValidator.isBlankOrNull(typeId)) {
			List<ObservationHistory> observationList = baseObjectDAO
					.getObservationHistoriesByPatientIdAndType(patientId, typeId);
			if (!observationList.isEmpty()) {
				return observationList.get(0); // sorted descending
			}
		}

		return null;
	}

	private void initialize() {
		ObservationHistoryType oht;

		for (ObservationType type : ObservationType.values()) {
			oht = ohtService.getByName(type.getDatabaseName());
			if (oht != null) {
				observationTypeToIdMap.put(type, oht.getId());
			}
		}
	}

	@Override
	public ObservationHistory getById(ObservationHistory observation) {
		return getBaseObjectDAO().getById(observation);
	}

	@Override
	public void updateData(ObservationHistory observation) {
		getBaseObjectDAO().updateData(observation);

	}

	@Override
	public boolean insertData(ObservationHistory observation) {
		return getBaseObjectDAO().insertData(observation);
	}

	@Override
	public List<ObservationHistory> getAll(Patient patient, Sample sample, String observationHistoryTypeId) {
		return getBaseObjectDAO().getAll(patient, sample, observationHistoryTypeId);
	}

	@Override
	public void insertOrUpdateData(ObservationHistory observation) {
		getBaseObjectDAO().insertOrUpdateData(observation);

	}

	@Override
	public ObservationHistory getObservationHistoriesBySampleIdAndType(String sampleId,
			String observationHistoryTypeId) {
		return getBaseObjectDAO().getObservationHistoriesBySampleIdAndType(sampleId, observationHistoryTypeId);
	}

	@Override
	public List<ObservationHistory> getObservationHistoriesByPatientIdAndType(String patientId,
			String observationHistoryTypeId) {
		return getBaseObjectDAO().getObservationHistoriesByPatientIdAndType(patientId, observationHistoryTypeId);
	}

	@Override
	public List<ObservationHistory> getObservationHistoryByDictonaryValues(String dictionaryValue) {
		return getBaseObjectDAO().getObservationHistoryByDictonaryValues(dictionaryValue);
	}

	@Override
	public List<ObservationHistory> getObservationHistoriesBySampleItemId(String sampleItemId) {
		return getBaseObjectDAO().getObservationHistoriesBySampleItemId(sampleItemId);
	}

	@Override
	public List<ObservationHistory> getObservationHistoriesByValueAndType(String value, String typeId,
			String valueType) {
		return getBaseObjectDAO().getObservationHistoriesByValueAndType(value, typeId, valueType);
	}

	@Override
	public List<ObservationHistory> getObservationHistoriesBySampleId(String sampleId) {
		return getBaseObjectDAO().getObservationHistoriesBySampleId(sampleId);
	}
}
