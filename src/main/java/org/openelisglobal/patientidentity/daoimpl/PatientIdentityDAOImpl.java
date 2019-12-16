package org.openelisglobal.patientidentity.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.patientidentity.dao.PatientIdentityDAO;
import org.openelisglobal.patientidentity.valueholder.PatientIdentity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PatientIdentityDAOImpl extends BaseDAOImpl<PatientIdentity, String> implements PatientIdentityDAO {

    public PatientIdentityDAOImpl() {
        super(PatientIdentity.class);
    }

    @Override
    
    @Transactional(readOnly = true)
    public List<PatientIdentity> getPatientIdentitiesForPatient(String id) {

        List<PatientIdentity> identities;

        try {
            String sql = "from PatientIdentity pi where pi.patientId = :Id";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("Id", new Integer(id));

            identities = query.list();

            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in PatientIdentityDAOImpl getPatientIdentitiesForPatient()", e);
        }

        return identities;
    }

//	@Override
//	public boolean insertData(PatientIdentity patientIdentity) throws LIMSRuntimeException {
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(patientIdentity);
//			patientIdentity.setId(id);
//
//			String sysUserId = patientIdentity.getSysUserId();
//			String tableName = "PATIENT_IDENTITY";
//			auditDAO.saveNewHistory(patientIdentity, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			LogEvent.logError("PatientIdentityDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in PatientIdentity insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(PatientIdentity patientIdentity) throws LIMSRuntimeException {
//		PatientIdentity oldData = getCurrentPatientIdentity(patientIdentity.getId());
//
//		// add to audit trail
//		try {
//
//			String sysUserId = patientIdentity.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "PATIENT_IDENTITY";
//			auditDAO.saveHistory(patientIdentity, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			LogEvent.logError("PatientIdentityDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in PatientIdentity AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(patientIdentity);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(patientIdentity);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(patientIdentity);
//		} catch (RuntimeException e) {
//			LogEvent.logError("patientIdentityDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in patientIdentity updateData()", e);
//		}
//	}

    @Transactional(readOnly = true)
    public PatientIdentity getCurrentPatientIdentity(String id) {
        PatientIdentity current = null;
        try {
            current = entityManager.unwrap(Session.class).get(PatientIdentity.class, id);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in PatientIdentity getCurrentPatientIdentity()", e);
        }

        return current;
    }

//	@Override
//	public void delete(String patientIdentityId, String activeUserId) throws LIMSRuntimeException {
//		try {
//
//			PatientIdentity oldData = readPatientIdentity(patientIdentityId);
//			Patient newData = new Patient();
//
//			String event = IActionConstants.AUDIT_TRAIL_DELETE;
//			String tableName = "PATIENT_IDENTITY";
//			auditDAO.saveHistory(newData, oldData, activeUserId, event, tableName);
//
//			entityManager.unwrap(Session.class).delete(oldData);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//
//			LogEvent.logError("PatientIdentityDAOImpl", "delete()", e.toString());
//			throw new LIMSRuntimeException("Error in PatientIdentity delete()", e);
//		}
//	}

    public PatientIdentity readPatientIdentity(String idString) {

        PatientIdentity patientIdentity = null;
        try {
            patientIdentity = entityManager.unwrap(Session.class).get(PatientIdentity.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in PatientIdentity readPatientIdentity()", e);
        }

        return patientIdentity;
    }

    @Override
    
    @Transactional(readOnly = true)
    public List<PatientIdentity> getPatientIdentitiesByValueAndType(String value, String identityType)
            throws LIMSRuntimeException {
        String sql = "From PatientIdentity pi where pi.identityData = :value and pi.identityTypeId = :identityType";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("value", value);
            query.setInteger("identityType", Integer.parseInt(identityType));

            List<PatientIdentity> identities = query.list();

            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            return identities;
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in PatientIdentity getPatientIdentitiesByValueAndType()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PatientIdentity getPatitentIdentityForPatientAndType(String patientId, String identityTypeId)
            throws LIMSRuntimeException {

        String sql = "from PatientIdentity pi where pi.patientId = :patientId and pi.identityTypeId = :typeId";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("patientId", Integer.parseInt(patientId));
            query.setInteger("typeId", Integer.parseInt(identityTypeId));

            PatientIdentity pi = (PatientIdentity) query.uniqueResult();

            // closeSession(); // CSL remove old

            return pi;
        } catch (HibernateException e) {
            handleException(e, "getPatitentIdentityForPatientAndType");
        }

        return null;
    }
}
