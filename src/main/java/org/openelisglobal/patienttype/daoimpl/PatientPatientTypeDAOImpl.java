/**
* The contents of this file are subject to the Mozilla Public License
* Version 1.1 (the "License"); you may not use this file except in
* compliance with the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS"
* basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
* License for the specific language governing rights and limitations under
* the License.
*
* The Original Code is OpenELIS code.
*
* Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
*
* Contributor(s): CIRG, University of Washington, Seattle WA.
*/
package org.openelisglobal.patienttype.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.patienttype.dao.PatientPatientTypeDAO;
import org.openelisglobal.patienttype.valueholder.PatientPatientType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PatientPatientTypeDAOImpl extends BaseDAOImpl<PatientPatientType, String>
        implements PatientPatientTypeDAO {

    public PatientPatientTypeDAOImpl() {
        super(PatientPatientType.class);
    }

//	@Override
//	public boolean insertData(PatientPatientType patientType) throws LIMSRuntimeException {
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(patientType);
//			patientType.setId(id);
//
//			String sysUserId = patientType.getSysUserId();
//			String tableName = "PATIENT_PATIENT_TYPE";
//			auditDAO.saveNewHistory(patientType, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			LogEvent.logError("PatientPatientTypeDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in PatientPatientType insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(PatientPatientType patientType) throws LIMSRuntimeException {
//		PatientPatientType oldData = getCurrentPatientPatientType(patientType.getId());
//
//		// add to audit trail
//		try {
//
//			String sysUserId = patientType.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "PATIENT_PATIENT_TYPE";
//			auditDAO.saveHistory(patientType, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			LogEvent.logError("PatientPatientTypeDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in PatientPatientTypeAuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(patientType);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(patientType);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(patientType);
//		} catch (RuntimeException e) {
//			LogEvent.logError("patientPatientTypeDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in patientPatientType updateData()", e);
//		}
//	}

    @Transactional(readOnly = true)
    public PatientPatientType getCurrentPatientPatientType(String id) {
        PatientPatientType current = null;
        try {
            current = entityManager.unwrap(Session.class).get(PatientPatientType.class, id);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in PatientPatientType getCurrentPatientPatientType()", e);
        }

        return current;
    }

//	@Override
//	public PatientType getPatientTypeForPatient(String id) {
//
//		PatientPatientType patientPatientType = getPatientPatientTypeForPatient(id);
//
//		if (patientPatientType != null) {
//			PatientType patientType = new PatientType();
//			patientType.setId(patientPatientType.getPatientTypeId());
//			patientTypeDAO.getData(patientType);
//
//			return patientType;
//		}
//
//		return null;
//	}

    @Override
    
    @Transactional(readOnly = true)
    public PatientPatientType getPatientPatientTypeForPatient(String patientId) throws LIMSRuntimeException {
        List<PatientPatientType> patientTypes;

        try {
            String sql = "from PatientPatientType pi where pi.patientId = :patientId";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("patientId", new Integer(patientId));

            patientTypes = query.list();

            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

        } catch (HibernateException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in PatientIdentityDAOImpl getPatientPatientTypeForPatient()", e);
        }

        if (patientTypes.size() > 0) {
            PatientPatientType patientPatientType = patientTypes.get(0);

            return patientPatientType;
        }

        return null;
    }

}
