package us.mn.state.health.lims.patientidentity.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.patientidentity.dao.PatientIdentityDAO;
import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;

public class PatientIdentityDAOImpl extends BaseDAOImpl implements PatientIdentityDAO {

	@SuppressWarnings("unchecked")
	public List<PatientIdentity> getPatientIdentitiesForPatient(String id) {

		List<PatientIdentity> identities;

		try {
			String sql = "from PatientIdentity pi where pi.patientId = :Id";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("Id", new Integer(id));

			identities = query.list();

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		} catch (Exception e) {
			LogEvent.logError("PatientIdentityDAOImpl", "getPatientIdentitiesForPatient()", e.toString());
			throw new LIMSRuntimeException("Error in PatientIdentityDAOImpl getPatientIdentitiesForPatient()", e);
		}

		return identities;
	}

	public boolean insertData(PatientIdentity patientIdentity) throws LIMSRuntimeException {
		try {
			String id = (String) HibernateUtil.getSession().save(patientIdentity);
			patientIdentity.setId(id);

			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = patientIdentity.getSysUserId();
			String tableName = "PATIENT_IDENTITY";
			auditDAO.saveNewHistory(patientIdentity, sysUserId, tableName);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		} catch (Exception e) {
			LogEvent.logError("PatientIdentityDAOImpl", "insertData()", e.toString());
			throw new LIMSRuntimeException("Error in PatientIdentity insertData()", e);
		}

		return true;
	}

	public void updateData(PatientIdentity patientIdentity) throws LIMSRuntimeException {
		PatientIdentity oldData = getCurrentPatientIdentity(patientIdentity.getId());

		// add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = patientIdentity.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "PATIENT_IDENTITY";
			auditDAO.saveHistory(patientIdentity, oldData, sysUserId, event, tableName);
		} catch (Exception e) {
			LogEvent.logError("PatientIdentityDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in PatientIdentity AuditTrail updateData()", e);
		}

		try {
			HibernateUtil.getSession().merge(patientIdentity);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(patientIdentity);
			HibernateUtil.getSession().refresh(patientIdentity);
		} catch (Exception e) {
			LogEvent.logError("patientIdentityDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in patientIdentity updateData()", e);
		}
	}

	public PatientIdentity getCurrentPatientIdentity(String id) {
		PatientIdentity current = null;
		try {
			current = (PatientIdentity) HibernateUtil.getSession().get(PatientIdentity.class, id);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("PatientIdentityDAOImpl", "readSampleHuman()", e.toString());
			throw new LIMSRuntimeException("Error in PatientIdentity getCurrentPatientIdentity()", e);
		}

		return current;
	}

	public void delete(String patientIdentityId, String activeUserId) throws LIMSRuntimeException {
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();

			PatientIdentity oldData = readPatientIdentity(patientIdentityId);
			Patient newData = new Patient();

			String event = IActionConstants.AUDIT_TRAIL_DELETE;
			String tableName = "PATIENT_IDENTITY";
			auditDAO.saveHistory(newData, oldData, activeUserId, event, tableName);

			HibernateUtil.getSession().delete(oldData);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		} catch (Exception e) {

			LogEvent.logError("PatientIdentityDAOImpl", "delete()", e.toString());
			throw new LIMSRuntimeException("Error in PatientIdentity delete()", e);
		}
	}

	public PatientIdentity readPatientIdentity(String idString) {

		PatientIdentity patientIdentity = null;
		try {
			patientIdentity = (PatientIdentity) HibernateUtil.getSession().get(PatientIdentity.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("PatientIdentityDAOImpl", "readPatientIdentity()", e.toString());
			throw new LIMSRuntimeException("Error in PatientIdentity readPatientIdentity()", e);
		}

		return patientIdentity;
	}

	@SuppressWarnings("unchecked")
	public List<PatientIdentity> getPatientIdentitiesByValueAndType(String value, String identityType) throws LIMSRuntimeException {
		String sql = "From PatientIdentity pi where pi.identityData = :value and pi.identityTypeId = :identityType";

		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("value", value);
			query.setInteger("identityType", Integer.parseInt(identityType));

			List<PatientIdentity> identities = query.list();

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			return identities;
		} catch (Exception e) {
			LogEvent.logError("PatientIdentityDAOImpl", "getPatientIdentitiesByValueAndType()", e.toString());
			throw new LIMSRuntimeException("Error in PatientIdentity getPatientIdentitiesByValueAndType()", e);
		}
	}

	@Override
	public PatientIdentity getPatitentIdentityForPatientAndType(String patientId, String identityTypeId)
			throws LIMSRuntimeException {

		String sql = "from PatientIdentity pi where pi.patientId = :patientId and pi.identityTypeId = :typeId";
		
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("patientId", Integer.parseInt(patientId));
			query.setInteger("typeId", Integer.parseInt(identityTypeId));
			
			PatientIdentity pi = (PatientIdentity)query.uniqueResult();
			
			closeSession();
			
			return pi;
		}catch(HibernateException e){
			handleException(e, "getPatitentIdentityForPatientAndType");
		}
		
		return null;
	}
}
