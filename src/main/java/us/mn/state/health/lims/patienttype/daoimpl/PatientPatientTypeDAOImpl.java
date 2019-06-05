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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.patienttype.dao.PatientPatientTypeDAO;
import us.mn.state.health.lims.patienttype.dao.PatientTypeDAO;
import us.mn.state.health.lims.patienttype.valueholder.PatientPatientType;
import us.mn.state.health.lims.patienttype.valueholder.PatientType;

@Component
@Transactional
public class PatientPatientTypeDAOImpl extends BaseDAOImpl<PatientPatientType, String>
		implements PatientPatientTypeDAO {

	public PatientPatientTypeDAOImpl() {
		super(PatientPatientType.class);
	}

	@Autowired
	private AuditTrailDAO auditDAO;

	@Override
	public boolean insertData(PatientPatientType patientType) throws LIMSRuntimeException {
		try {
			String id = (String) sessionFactory.getCurrentSession().save(patientType);
			patientType.setId(id);

			String sysUserId = patientType.getSysUserId();
			String tableName = "PATIENT_PATIENT_TYPE";
			auditDAO.saveNewHistory(patientType, sysUserId, tableName);

			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old

		} catch (Exception e) {
			LogEvent.logError("PatientPatientTypeDAOImpl", "insertData()", e.toString());
			throw new LIMSRuntimeException("Error in PatientPatientType insertData()", e);
		}

		return true;
	}

	@Override
	public void updateData(PatientPatientType patientType) throws LIMSRuntimeException {
		PatientPatientType oldData = getCurrentPatientPatientType(patientType.getId());

		// add to audit trail
		try {

			String sysUserId = patientType.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "PATIENT_PATIENT_TYPE";
			auditDAO.saveHistory(patientType, oldData, sysUserId, event, tableName);
		} catch (Exception e) {
			LogEvent.logError("PatientPatientTypeDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in PatientPatientTypeAuditTrail updateData()", e);
		}

		try {
			sessionFactory.getCurrentSession().merge(patientType);
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
			// sessionFactory.getCurrentSession().evict // CSL remove old(patientType);
			// sessionFactory.getCurrentSession().refresh // CSL remove old(patientType);
		} catch (Exception e) {
			LogEvent.logError("patientPatientTypeDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in patientPatientType updateData()", e);
		}
	}

	public PatientPatientType getCurrentPatientPatientType(String id) {
		PatientPatientType current = null;
		try {
			current = sessionFactory.getCurrentSession().get(PatientPatientType.class, id);
			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old
		} catch (Exception e) {
			LogEvent.logError("PatientPatientTypeDAOImpl", "getCurrentPatientPatientType()", e.toString());
			throw new LIMSRuntimeException("Error in PatientPatientType getCurrentPatientPatientType()", e);
		}

		return current;
	}

	@Override
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

	@Override
	@SuppressWarnings("unchecked")
	public PatientPatientType getPatientPatientTypeForPatient(String patientId) throws LIMSRuntimeException {
		List<PatientPatientType> patientTypes;

		try {
			String sql = "from PatientPatientType pi where pi.patientId = :patientId";
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setInteger("patientId", new Integer(patientId));

			patientTypes = query.list();

			// sessionFactory.getCurrentSession().flush(); // CSL remove old
			// sessionFactory.getCurrentSession().clear(); // CSL remove old

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
