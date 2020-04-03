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
* Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package org.openelisglobal.requester.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.requester.dao.SampleRequesterDAO;
import org.openelisglobal.requester.valueholder.SampleRequester;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/*
 */
@Component
@Transactional
public class SampleRequesterDAOImpl extends BaseDAOImpl<SampleRequester, String> implements SampleRequesterDAO {

    public SampleRequesterDAOImpl() {
        super(SampleRequester.class);
    }

//	@Override
//	public boolean insertData(SampleRequester sampleRequester) throws LIMSRuntimeException {
//		try {
//			entityManager.unwrap(Session.class).save(sampleRequester);
//
//			auditDAO.saveNewHistory(sampleRequester, sampleRequester.getSysUserId(), "SAMPLE_REQUESTER");
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			LogEvent.logError("SampleRequesterDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in SampleRequester insertData()", e);
//		}
//
//		return true;
//	}

//	@Override
//	public void updateData(SampleRequester sampleRequester) throws LIMSRuntimeException {
//		SampleRequester oldData = readOld(sampleRequester.getSampleId(), sampleRequester.getRequesterTypeId());
//
//		try {
//
//			String sysUserId = sampleRequester.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "SAMPLE_REQUESTER";
//			auditDAO.saveHistory(sampleRequester, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			LogEvent.logError("SampleRequesterDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SampleRequester AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(sampleRequester);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(sampleRequester);
//			// entityManager.unwrap(Session.class).refresh // CSL remove
//			// old(sampleRequester);
//		} catch (RuntimeException e) {
//			LogEvent.logError("SampleRequesterDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SampleRequester updateData()", e);
//		}
//	}

//	@Override
//	public void insertOrUpdateData(SampleRequester samplePersonRequester) throws LIMSRuntimeException {
//		if (samplePersonRequester.getLastupdated() == null) {
//			insertData(samplePersonRequester);
//		} else {
//			updateData(samplePersonRequester);
//		}
//	}

    @Override
    public void delete(SampleRequester sampleRequester) throws LIMSRuntimeException {
        entityManager.unwrap(Session.class).delete(sampleRequester);
        // closeSession(); // CSL remove old
    }

    
    @Override
    @Transactional(readOnly = true)
    public List<SampleRequester> getRequestersForSampleId(String sampleId) throws LIMSRuntimeException {
        String sql = "From SampleRequester sr where sr.sampleId = :sampleId";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setLong("sampleId", Long.parseLong(sampleId));
            List<SampleRequester> requester = query.list();

            // closeSession(); // CSL remove old

            return requester;

        } catch (HibernateException e) {
            handleException(e, "getRequesterForSampleId");
        }
        return null;
    }

    public SampleRequester readOld(long sampleId, long requesterTypeId) {
        String sql = "From SampleRequester sr where sr.sampleId = :sampleId and sr.requesterTypeId = :requesterTypeId";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setLong("sampleId", sampleId);
            query.setLong("requesterTypeId", requesterTypeId);
            SampleRequester requester = (SampleRequester) query.uniqueResult();
            // closeSession(); // CSL remove old
            return requester;
        } catch (HibernateException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in SampleRequester readOld()", e);
        }
    }
}