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
* Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.qaevent.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.qaevent.dao.QaObservationDAO;
import us.mn.state.health.lims.qaevent.valueholder.QaObservation;

public class QaObservationDAOImpl extends BaseDAOImpl implements QaObservationDAO {

	@Override
	public void insertData(QaObservation qaObservation) throws LIMSRuntimeException {
		try {
			
			String id = (String) HibernateUtil.getSession().save(qaObservation);
			qaObservation.setId(id);
			
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			auditDAO.saveNewHistory(qaObservation,qaObservation.getSysUserId(),"QA_OBSERVATION");
			
			closeSession();
		} catch (HibernateException e) {
			handleException(e, "insertData");
		}
	}

	@Override
	public void updateData(QaObservation qaObservation) throws LIMSRuntimeException {
		QaObservation oldData = readQaObservation(qaObservation.getId());

		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			auditDAO.saveHistory(qaObservation,oldData,qaObservation.getSysUserId(),IActionConstants.AUDIT_TRAIL_UPDATE,"QA_OBSERVATION");
		}  catch (HibernateException e) {
			LogEvent.logError("QaEventDAOImpl","AuditTrail updateData()",e.toString());
			throw new LIMSRuntimeException("Error in QaObservation AuditTrail updateData()", e);
		}  
					
		try {
			HibernateUtil.getSession().merge(qaObservation);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(qaObservation);
			HibernateUtil.getSession().refresh(qaObservation);
		} catch (Exception e) {
			handleException(e, "updateData");
		}
	}

	@Override
	public QaObservation getQaObservationByTypeAndObserved(String typeName, String observedType, String observedId)
			throws LIMSRuntimeException {
		String sql = "FROM QaObservation o where o.observationType.name = :observationName and o.observedType = :observedType and o.observedId = :observedId ";
		
		try {
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("observationName", typeName);
			query.setString("observedType", observedType);
			query.setInteger("observedId", Integer.parseInt(observedId) );
			QaObservation observation = (QaObservation)query.uniqueResult();
			closeSession();
			return observation;
		} catch (HibernateException e) {
			handleException(e, "getQaObservationByTypeAndObserved");
		}
		return null;
	}

	public QaObservation readQaObservation(String idString) {
		QaObservation qaObservation = null;
		try {
			qaObservation = (QaObservation) HibernateUtil.getSession().get(QaObservation.class,	idString);
			closeSession();
		} catch (Exception e) {
			handleException(e, "readQaObservation");
		}

		return qaObservation;
	}

}
