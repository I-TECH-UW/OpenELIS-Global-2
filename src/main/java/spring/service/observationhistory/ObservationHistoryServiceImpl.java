package spring.service.observationhistory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import spring.util.SpringContext;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory.ValueType;
import us.mn.state.health.lims.observationhistorytype.dao.ObservationHistoryTypeDAO;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.sample.valueholder.Sample;

@Service
@DependsOn({ "springContext" })
public class ObservationHistoryServiceImpl extends BaseObjectServiceImpl<ObservationHistory> implements ObservationHistoryService {

	public enum ObservationType {
		INITIAL_SAMPLE_CONDITION("initialSampleCondition"), PAYMENT_STATUS("paymentStatus"), REQUEST_DATE("requestDate"), NEXT_VISIT_DATE("nextVisitDate"), REFERRING_SITE("referringSite"), REFERRERS_PATIENT_ID("referrersPatientId"), BILLING_REFERENCE_NUMBER("billingRefNumber"), TEST_LOCATION_CODE("testLocationCode"), TEST_LOCATION_CODE_OTHER("testLocationCodeOther"), PROGRAM("program");

		private String dbName;

		private ObservationType(String dbName) {
			this.dbName = dbName;
		}

		public String getDatabaseName() {
			return dbName;
		}
	}

	private static final Map<ObservationType, String> observationTypeToIdMap = new HashMap<>();

	@Autowired
	private static final ObservationHistoryDAO observationDAO = SpringContext.getBean(ObservationHistoryDAO.class);

	@Autowired
	private static final DictionaryDAO dictionaryDAO = SpringContext.getBean(DictionaryDAO.class);
	@Autowired
	private static ObservationHistoryTypeDAO ohtDAO = SpringContext.getBean(ObservationHistoryTypeDAO.class);

	ObservationHistoryServiceImpl() {
		super(ObservationHistory.class);
	}

	@Override
	protected ObservationHistoryDAO getBaseObjectDAO() {
		return observationDAO;
	}

	@Override
	@Transactional
	public List<ObservationHistory> getAll(Patient patient, Sample sample) {
		return observationDAO.getAll(patient, sample);
	}

	public static String getObservationTypeIdForType(ObservationType type) {
		if (observationTypeToIdMap.isEmpty()) {
			initialize();
		}
		return observationTypeToIdMap.get(type);
	}

	public static List<ObservationHistory> getObservationsByTypeAndValue(ObservationType type, String value) {
		if (observationTypeToIdMap.isEmpty()) {
			initialize();
		}
		String typeId = getObservationTypeIdForType(type);

		if (!GenericValidator.isBlankOrNull(typeId)) {
			return observationDAO.getObservationHistoriesByValueAndType(value, typeId, ValueType.LITERAL.getCode());
		} else {
			return null;
		}
	}

	public static String getValueForSample(ObservationType type, String sampleId) {
		ObservationHistory observation = getObservationForSample(type, sampleId);
		return getValueForObservation(observation);
	}

	private static String getValueForObservation(ObservationHistory observation) {
		if (observation != null) {
			if (observation.getValueType().equals(ObservationHistory.ValueType.LITERAL.getCode())) {
				return observation.getValue();
			} else {
				if (!GenericValidator.isBlankOrNull(observation.getValue())) {
					return dictionaryDAO.getDataForId(observation.getValue()).getLocalizedName();
				}
			}
		}

		return null;
	}

	public static String getMostRecentValueForPatient(ObservationType type, String patientId) {
		ObservationHistory observation = getLastObservationForPatient(type, patientId);
		return getValueForObservation(observation);
	}

	public static String getRawValueForSample(ObservationType type, String sampleId) {
		ObservationHistory observation = getObservationForSample(type, sampleId);
		return observation != null ? observation.getValue() : null;
	}

	public static ObservationHistory getObservationForSample(ObservationType type, String sampleId) {
		if (observationTypeToIdMap.isEmpty()) {
			initialize();
		}
		String typeId = getObservationTypeIdForType(type);

		if (!GenericValidator.isBlankOrNull(typeId)) {
			return observationDAO.getObservationHistoriesBySampleIdAndType(sampleId, typeId);
		} else {
			return null;
		}

	}

	public static ObservationHistory getLastObservationForPatient(ObservationType type, String patientId) {
		if (observationTypeToIdMap.isEmpty()) {
			initialize();
		}

		String typeId = getObservationTypeIdForType(type);

		if (!GenericValidator.isBlankOrNull(typeId)) {
			List<ObservationHistory> observationList = observationDAO.getObservationHistoriesByPatientIdAndType(patientId, typeId);
			if (!observationList.isEmpty()) {
				return observationList.get(0); // sorted descending
			}
		}

		return null;
	}

	private static void initialize() {
		ObservationHistoryType oht;

		for (ObservationType type : ObservationType.values()) {
			oht = ohtDAO.getByName(type.getDatabaseName());
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
        return getBaseObjectDAO().getAll(patient,sample,observationHistoryTypeId);
	}

	@Override
	public void insertOrUpdateData(ObservationHistory observation) {
        getBaseObjectDAO().insertOrUpdateData(observation);

	}

	@Override
	public ObservationHistory getObservationHistoriesBySampleIdAndType(String sampleId, String observationHistoryTypeId) {
        return getBaseObjectDAO().getObservationHistoriesBySampleIdAndType(sampleId,observationHistoryTypeId);
	}

	@Override
	public List<ObservationHistory> getObservationHistoriesByPatientIdAndType(String patientId, String observationHistoryTypeId) {
        return getBaseObjectDAO().getObservationHistoriesByPatientIdAndType(patientId,observationHistoryTypeId);
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
	public List<ObservationHistory> getObservationHistoriesByValueAndType(String value, String typeId, String valueType) {
        return getBaseObjectDAO().getObservationHistoriesByValueAndType(value,typeId,valueType);
	}

	@Override
	public List<ObservationHistory> getObservationHistoriesBySampleId(String sampleId) {
        return getBaseObjectDAO().getObservationHistoriesBySampleId(sampleId);
	}
}
