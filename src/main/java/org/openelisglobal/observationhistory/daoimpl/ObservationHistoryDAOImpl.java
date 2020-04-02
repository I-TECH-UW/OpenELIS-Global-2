package org.openelisglobal.observationhistory.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.observationhistory.dao.ObservationHistoryDAO;
import org.openelisglobal.observationhistory.valueholder.ObservationHistory;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.sample.valueholder.Sample;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ObservationHistoryDAOImpl extends BaseDAOImpl<ObservationHistory, String>
        implements ObservationHistoryDAO {

    public ObservationHistoryDAOImpl() {
        super(ObservationHistory.class);
    }

//	@Override
//	public void insertOrUpdateData(ObservationHistory observation) throws LIMSRuntimeException {
//		if (observation.getId() == null) {
//			insertData(observation);
//		} else {
//			updateData(observation);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public List<ObservationHistory> getAll(Patient patient, Sample sample) {
        if (patient != null && sample != null) {
            ObservationHistory dh = new ObservationHistory();
            dh.setPatientId(patient.getId());
            dh.setSampleId(sample.getId());
            return readByExample(dh);
        }

        return new ArrayList<>();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ObservationHistory> getAll(Patient patient, Sample sample, String observationHistoryTypeId) {
        ObservationHistory history = new ObservationHistory();
        if (patient != null) {
            history.setPatientId(patient.getId());
        }
        if (sample != null) {
            history.setSampleId(sample.getId());
        }
        history.setObservationHistoryTypeId(observationHistoryTypeId);
        return readByExample(history);
    }

    @Override
    
    @Transactional(readOnly = true)
    public List<ObservationHistory> getObservationHistoryByDictonaryValues(String dictionaryValue)
            throws LIMSRuntimeException {
        List<ObservationHistory> observationList;
        String sql = "From ObservationHistory oh where oh.valueType = 'D' and oh.value = :value";

        try {

            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("value", dictionaryValue);

            observationList = query.list();

            // closeSession(); // CSL remove old
            return observationList;
        } catch (RuntimeException e) {
            handleException(e, "getObservationHistoryByDictonaryValues");
        }

        return null;
    }

    
    @Override
    @Transactional(readOnly = true)
    public List<ObservationHistory> getObservationHistoriesBySampleItemId(String sampleItemId)
            throws LIMSRuntimeException {
        String sql = "from ObservationHistory oh where oh.sampleItemId = :sampleItemId";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("sampleItemId", Integer.parseInt(sampleItemId));

            List<ObservationHistory> observationList = query.list();

            // closeSession(); // CSL remove old

            return observationList;
        } catch (HibernateException e) {
            handleException(e, "getObservationHistoriesBySampleItemId");
        }
        return null;
    }

    
    @Override
    @Transactional(readOnly = true)
    public List<ObservationHistory> getObservationHistoriesBySampleId(String sampleId) throws LIMSRuntimeException {
        String sql = "from ObservationHistory oh where oh.sampleId = :sampleId";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("sampleId", Integer.parseInt(sampleId));

            List<ObservationHistory> observationList = query.list();

            // closeSession(); // CSL remove old

            return observationList;
        } catch (HibernateException e) {
            handleException(e, "getObservationHistoriesBySampleId");
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ObservationHistory> getObservationHistoriesByPatientIdAndType(String patientId,
            String observationHistoryTypeId) throws LIMSRuntimeException {
        String sql = "from ObservationHistory oh where oh.patientId = :patientId and oh.observationHistoryTypeId = :ohTypeId order by oh.lastupdated desc";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("patientId", Integer.parseInt(patientId));
            query.setInteger("ohTypeId", Integer.parseInt(observationHistoryTypeId));

            List<ObservationHistory> ohList = query.list();

            // closeSession(); // CSL remove old

            return ohList;
        } catch (HibernateException e) {
            handleException(e, "getObservationHistoriesByPatientIdAndType");
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ObservationHistory getObservationHistoriesBySampleIdAndType(String sampleId, String observationHistoryTypeId)
            throws LIMSRuntimeException {

        String sql = "from ObservationHistory oh where oh.sampleId = :sampleId and oh.observationHistoryTypeId = :ohTypeId";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("sampleId", Integer.parseInt(sampleId));
            query.setInteger("ohTypeId", Integer.parseInt(observationHistoryTypeId));

            ObservationHistory oh = (ObservationHistory) query.setMaxResults(1).uniqueResult();

            // closeSession(); // CSL remove old

            return oh;
        } catch (HibernateException e) {
            handleException(e, "getObservationHistoriesBySampleIdAndType");
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ObservationHistory> getObservationHistoriesByValueAndType(String value, String typeId, String valueType)
            throws LIMSRuntimeException {
        String sql = "from ObservationHistory oh where oh.value = :value and oh.observationHistoryTypeId = :typeId and oh.valueType = :valueType";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("typeId", Integer.parseInt(typeId));
            query.setString("value", value);
            query.setString("valueType", valueType);

            List<ObservationHistory> ohList = query.list();

            // closeSession(); // CSL remove old

            return ohList;
        } catch (HibernateException e) {
            handleException(e, "getObservationHistoriesByValueAndType");
        }

        return null;
    }

    /**
     * Read a list of entities which match those fields(members) in the entity which
     * are filled in.
     */
    
    public List<ObservationHistory> readByExample(ObservationHistory entity) throws LIMSRuntimeException {
        List<ObservationHistory> results;
        try {
            results = entityManager.unwrap(Session.class).createCriteria(entity.getClass()).add(Example.create(entity))
                    .list();
        } catch (RuntimeException e) {
            throw createAndLogException("readByExample()", e);
        }
        return results;
    }

    /**
     * Utility routine for (1) logging an error and (2) creating a new
     * RuntimeException
     *
     * @param methodName
     * @param e
     * @return new RuntimeException
     */
    protected LIMSRuntimeException createAndLogException(String methodName, Exception e) {
        LogEvent.logError(e.toString(), e);
        return new LIMSRuntimeException("Error in " + this.getClass().getSimpleName() + " " + methodName, e);
    }
//
//	@Override
//	public boolean insertData(ObservationHistory observation) throws LIMSRuntimeException {
//		insert(observation);
//		return true;
//	}
//
//	@Override
//	public void updateData(ObservationHistory observation) throws LIMSRuntimeException {
//		save(observation);
//	}

    @Override
    @Transactional(readOnly = true)
    public ObservationHistory getById(ObservationHistory observation) throws LIMSRuntimeException {
        return get(observation.getId()).get();
    }
}
