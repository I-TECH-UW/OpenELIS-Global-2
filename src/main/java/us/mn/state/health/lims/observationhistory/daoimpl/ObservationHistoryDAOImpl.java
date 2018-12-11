package us.mn.state.health.lims.observationhistory.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.common.daoimpl.GenericDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.sample.valueholder.Sample;

public class ObservationHistoryDAOImpl extends GenericDAOImpl<String, ObservationHistory> implements ObservationHistoryDAO {

	public ObservationHistoryDAOImpl() {
		super(ObservationHistory.class, "observation_history");
	}


	public void delete(List<ObservationHistory> entities)
			throws LIMSRuntimeException {
		delete(entities, new ObservationHistory());
	}


    @Override
    public void insertOrUpdateData( ObservationHistory observation ) throws LIMSRuntimeException{
        if(observation.getId() == null){
            insertData( observation );
        }else{
            updateData( observation );
        }
    }

    public List<ObservationHistory> getAll(Patient patient, Sample sample) {
        if( patient != null && sample != null){
            ObservationHistory dh = new ObservationHistory();
            dh.setPatientId( patient.getId() );
            dh.setSampleId( sample.getId() );
            return this.readByExample( dh );
        }

        return new ArrayList<ObservationHistory>(  );
	}

	public List<ObservationHistory> getAll(Patient patient, Sample sample, String observationHistoryTypeId) {
	    ObservationHistory history = new ObservationHistory();
	    if (patient != null) {
	        history.setPatientId(patient.getId());
	    }
	    if (sample != null) {
	        history.setSampleId(sample.getId());
	    }
	    history.setObservationHistoryTypeId(observationHistoryTypeId);
	    return this.readByExample(history);
	}


	@SuppressWarnings("unchecked")
	public List<ObservationHistory> getObservationHistoryByDictonaryValues(String dictionaryValue) throws LIMSRuntimeException {
		List<ObservationHistory> observationList;
		String sql = "From ObservationHistory oh where oh.valueType = 'D' and oh.value = :value";

		try {

			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("value", dictionaryValue);

			observationList = query.list();

			closeSession();
			return observationList;
		} catch (Exception e) {
			handleException(e, "getObservationHistoryByDictonaryValues");
		}

		return null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ObservationHistory> getObservationHistoriesBySampleItemId(String sampleItemId) throws LIMSRuntimeException {
		String sql = "from ObservationHistory oh where oh.sampleItemId = :sampleItemId";

		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("sampleItemId", Integer.parseInt(sampleItemId));

			List<ObservationHistory> observationList = query.list();

			closeSession();

			return observationList;
		}catch( HibernateException e){
			handleException(e, "getObservationHistoriesBySampleItemId");
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ObservationHistory> getObservationHistoriesBySampleId(String sampleId) throws LIMSRuntimeException {
		String sql = "from ObservationHistory oh where oh.sampleId = :sampleId";

		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("sampleId", Integer.parseInt(sampleId));

			List<ObservationHistory> observationList = query.list();

			closeSession();

			return observationList;
		}catch( HibernateException e){
			handleException(e, "getObservationHistoriesBySampleId");
		}
		return null;
	}

    @Override
    public List<ObservationHistory> getObservationHistoriesByPatientIdAndType( String patientId, String observationHistoryTypeId ) throws LIMSRuntimeException{
        String sql = "from ObservationHistory oh where oh.patientId = :patientId and oh.observationHistoryTypeId = :ohTypeId order by oh.lastupdated desc";

        try{
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setInteger("patientId", Integer.parseInt(patientId));
            query.setInteger("ohTypeId", Integer.parseInt(observationHistoryTypeId));

            List<ObservationHistory> ohList = query.list();

            closeSession();

            return ohList;
        }catch(HibernateException e){
            handleException(e, "getObservationHistoriesByPatientIdAndType");
        }

        return null;
    }


    @Override
	public ObservationHistory getObservationHistoriesBySampleIdAndType(String sampleId, String observationHistoryTypeId)
			throws LIMSRuntimeException {
		
		String sql = "from ObservationHistory oh where oh.sampleId = :sampleId and oh.observationHistoryTypeId = :ohTypeId";
		
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("sampleId", Integer.parseInt(sampleId));
			query.setInteger("ohTypeId", Integer.parseInt(observationHistoryTypeId));
			
			ObservationHistory oh = (ObservationHistory)query.setMaxResults(1).uniqueResult();
			
			closeSession();
			
			return oh;
		}catch(HibernateException e){
			handleException(e, "getObservationHistoriesBySampleIdAndType");
		}
		
		return null;
	}

    @Override
    public List<ObservationHistory> getObservationHistoriesByValueAndType( String value, String typeId, String valueType ) throws LIMSRuntimeException{
        String sql = "from ObservationHistory oh where oh.value = :value and oh.observationHistoryTypeId = :typeId and oh.valueType = :valueType";

        try{
            Query query = HibernateUtil.getSession().createQuery(sql);
            query.setInteger("typeId", Integer.parseInt(typeId));
            query.setString( "value", value );
            query.setString( "valueType", valueType );

            List<ObservationHistory> ohList = query.list();

            closeSession();

            return ohList;
        }catch(HibernateException e){
            handleException(e, "getObservationHistoriesByValueAndType");
        }

        return null;
    }
}
