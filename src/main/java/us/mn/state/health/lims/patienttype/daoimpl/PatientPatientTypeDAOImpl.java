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
package us.mn.state.health.lims.patienttype.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.patienttype.dao.PatientPatientTypeDAO;
import us.mn.state.health.lims.patienttype.dao.PatientTypeDAO;
import us.mn.state.health.lims.patienttype.valueholder.PatientPatientType;
import us.mn.state.health.lims.patienttype.valueholder.PatientType;

public class PatientPatientTypeDAOImpl implements PatientPatientTypeDAO {

	public boolean insertData(PatientPatientType patientType) throws LIMSRuntimeException {
		try {
			String id = (String) HibernateUtil.getSession().save(patientType);
			patientType.setId(id);

			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = patientType.getSysUserId();
			String tableName = "PATIENT_PATIENT_TYPE";
			auditDAO.saveNewHistory(patientType, sysUserId, tableName);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		} catch (Exception e) {
			LogEvent.logError("PatientPatientTypeDAOImpl", "insertData()", e.toString());
			throw new LIMSRuntimeException("Error in PatientPatientType insertData()", e);
		}

		return true;
	}

	public void updateData(PatientPatientType patientType) throws LIMSRuntimeException {
		PatientPatientType oldData = getCurrentPatientPatientType(patientType.getId());

		// add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = patientType.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "PATIENT_PATIENT_TYPE";
			auditDAO.saveHistory(patientType, oldData, sysUserId, event, tableName);
		} catch (Exception e) {
			LogEvent.logError("PatientPatientTypeDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in PatientPatientTypeAuditTrail updateData()", e);
		}

		try {
			HibernateUtil.getSession().merge(patientType);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(patientType);
			HibernateUtil.getSession().refresh(patientType);
		} catch (Exception e) {
			LogEvent.logError("patientPatientTypeDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in patientPatientType updateData()", e);
		}
	}

	public PatientPatientType getCurrentPatientPatientType(String id) {
		PatientPatientType current = null;
		try {
			current = (PatientPatientType) HibernateUtil.getSession().get(PatientPatientType.class, id);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("PatientPatientTypeDAOImpl", "getCurrentPatientPatientType()", e.toString());
			throw new LIMSRuntimeException("Error in PatientPatientType getCurrentPatientPatientType()", e);
		}

		return current;
	}

	public PatientType getPatientTypeForPatient(String id) {

		PatientPatientType patientPatientType = getPatientPatientTypeForPatient(id);

		if (patientPatientType != null) {
			PatientTypeDAO patientTypeDAO = new PatientTypeDAOImpl();
			PatientType patientType = new PatientType();
			patientType.setId(patientPatientType.getPatientTypeId());
			patientTypeDAO.getData(patientType);

			return patientType;
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public PatientPatientType getPatientPatientTypeForPatient(String patientId) throws LIMSRuntimeException {
		List<PatientPatientType> patientTypes;

		try {
			String sql = "from PatientPatientType pi where pi.patientId = :patientId";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("patientId", new Integer(patientId));

			patientTypes = query.list();

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		} catch (HibernateException he) {
			LogEvent.logError("PatientIdentityDAOImpl", "getPatientPatientTypeForPatient()", he.toString());
			throw new LIMSRuntimeException("Error in PatientIdentityDAOImpl getPatientPatientTypeForPatient()", he);
		}

		if (patientTypes.size() > 0) {
			PatientPatientType patientPatientType = patientTypes.get(0);

			return patientPatientType;
		}

		return null;
	}

}
