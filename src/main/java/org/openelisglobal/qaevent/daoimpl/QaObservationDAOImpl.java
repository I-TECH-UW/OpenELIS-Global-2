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
package org.openelisglobal.qaevent.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.qaevent.dao.QaObservationDAO;
import org.openelisglobal.qaevent.valueholder.QaObservation;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class QaObservationDAOImpl extends BaseDAOImpl<QaObservation, String> implements QaObservationDAO {

    public QaObservationDAOImpl() {
        super(QaObservation.class);
    }

//	@Override
//	public void insertData(QaObservation qaObservation) throws LIMSRuntimeException {
//		try {
//
//			String id = (String) entityManager.unwrap(Session.class).save(qaObservation);
//			qaObservation.setId(id);
//
//			auditDAO.saveNewHistory(qaObservation, qaObservation.getSysUserId(), "QA_OBSERVATION");
//
//			// closeSession(); // CSL remove old
//		} catch (HibernateException e) {
//			handleException(e, "insertData");
//		}
//	}

//	@Override
//	public void updateData(QaObservation qaObservation) throws LIMSRuntimeException {
//		QaObservation oldData = readQaObservation(qaObservation.getId());
//
//		try {
//
//			auditDAO.saveHistory(qaObservation, oldData, qaObservation.getSysUserId(),
//					IActionConstants.AUDIT_TRAIL_UPDATE, "QA_OBSERVATION");
//		} catch (HibernateException e) {
//			LogEvent.logError("QaEventDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in QaObservation AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(qaObservation);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(qaObservation);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(qaObservation);
//		} catch (RuntimeException e) {
//			handleException(e, "updateData");
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public QaObservation getQaObservationByTypeAndObserved(String typeName, String observedType, String observedId)
            throws LIMSRuntimeException {
        String sql = "FROM QaObservation o where o.observationType.name = :observationName and o.observedType = :observedType and o.observedId = :observedId ";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("observationName", typeName);
            query.setString("observedType", observedType);
            query.setInteger("observedId", Integer.parseInt(observedId));
            QaObservation observation = (QaObservation) query.uniqueResult();
            // closeSession(); // CSL remove old
            return observation;
        } catch (HibernateException e) {
            handleException(e, "getQaObservationByTypeAndObserved");
        }
        return null;
    }

    public QaObservation readQaObservation(String idString) {
        QaObservation qaObservation = null;
        try {
            qaObservation = entityManager.unwrap(Session.class).get(QaObservation.class, idString);
            // closeSession(); // CSL remove old
        } catch (RuntimeException e) {
            handleException(e, "readQaObservation");
        }

        return qaObservation;
    }

}
