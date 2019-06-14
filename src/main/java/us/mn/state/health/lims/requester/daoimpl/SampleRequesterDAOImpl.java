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
package us.mn.state.health.lims.requester.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.requester.dao.SampleRequesterDAO;
import us.mn.state.health.lims.requester.valueholder.SampleRequester;

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
//			sessionFactory.getCurrentSession().save(sampleRequester);
//
//			auditDAO.saveNewHistory(sampleRequester, sampleRequester.getSysUserId(), "SAMPLE_REQUESTER");
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//
//		} catch (Exception e) {
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
//		} catch (Exception e) {
//			LogEvent.logError("SampleRequesterDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in SampleRequester AuditTrail updateData()", e);
//		}
//
//		try {
//			sessionFactory.getCurrentSession().merge(sampleRequester);
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			// sessionFactory.getCurrentSession().evict // CSL remove old(sampleRequester);
//			// sessionFactory.getCurrentSession().refresh // CSL remove
//			// old(sampleRequester);
//		} catch (Exception e) {
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
		sessionFactory.getCurrentSession().delete(sampleRequester);
		// closeSession(); // CSL remove old
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<SampleRequester> getRequestersForSampleId(String sampleId) throws LIMSRuntimeException {
		String sql = "From SampleRequester sr where sr.sampleId = :sampleId";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
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
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setLong("sampleId", sampleId);
			query.setLong("requesterTypeId", requesterTypeId);
			SampleRequester requester = (SampleRequester) query.uniqueResult();
			// closeSession(); // CSL remove old
			return requester;
		} catch (HibernateException e) {
			LogEvent.logError("SampleRequesterDAOImpl", "readOld()", e.toString());
			throw new LIMSRuntimeException("Error in SampleRequester readOld()", e);
		}
	}
}