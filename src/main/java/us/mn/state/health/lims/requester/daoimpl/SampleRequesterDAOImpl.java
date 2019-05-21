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

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.requester.dao.SampleRequesterDAO;
import us.mn.state.health.lims.requester.valueholder.SampleRequester;

/*
 */
@Component
@Transactional
public class SampleRequesterDAOImpl extends BaseDAOImpl<SampleRequester> implements SampleRequesterDAO {

	public SampleRequesterDAOImpl() {
		super(SampleRequester.class);
	}

	@Override
	public boolean insertData(SampleRequester sampleRequester) throws LIMSRuntimeException {
		try {
			HibernateUtil.getSession().save(sampleRequester);

			new AuditTrailDAOImpl().saveNewHistory(sampleRequester, sampleRequester.getSysUserId(), "SAMPLE_REQUESTER");
			// HibernateUtil.getSession().flush(); // CSL remove old
			// HibernateUtil.getSession().clear(); // CSL remove old

		} catch (Exception e) {
			LogEvent.logError("SampleRequesterDAOImpl", "insertData()", e.toString());
			throw new LIMSRuntimeException("Error in SampleRequester insertData()", e);
		}

		return true;
	}

	@Override
	public void updateData(SampleRequester sampleRequester) throws LIMSRuntimeException {
		SampleRequester oldData = readOld(sampleRequester.getSampleId(), sampleRequester.getRequesterTypeId());

		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = sampleRequester.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "SAMPLE_REQUESTER";
			auditDAO.saveHistory(sampleRequester, oldData, sysUserId, event, tableName);
		} catch (Exception e) {
			LogEvent.logError("SampleRequesterDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in SampleRequester AuditTrail updateData()", e);
		}

		try {
			HibernateUtil.getSession().merge(sampleRequester);
			// HibernateUtil.getSession().flush(); // CSL remove old
			// HibernateUtil.getSession().clear(); // CSL remove old
			// HibernateUtil.getSession().evict // CSL remove old(sampleRequester);
			// HibernateUtil.getSession().refresh // CSL remove old(sampleRequester);
		} catch (Exception e) {
			LogEvent.logError("SampleRequesterDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in SampleRequester updateData()", e);
		}
	}

	@Override
	public void insertOrUpdateData(SampleRequester samplePersonRequester) throws LIMSRuntimeException {
		if (samplePersonRequester.getLastupdated() == null) {
			insertData(samplePersonRequester);
		} else {
			updateData(samplePersonRequester);
		}
	}

	@Override
	public void delete(SampleRequester sampleRequester) throws LIMSRuntimeException {
		HibernateUtil.getSession().delete(sampleRequester);
		// closeSession(); // CSL remove old
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SampleRequester> getRequestersForSampleId(String sampleId) throws LIMSRuntimeException {
		String sql = "From SampleRequester sr where sr.sampleId = :sampleId";

		try {
			Query query = HibernateUtil.getSession().createQuery(sql);
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
			Query query = HibernateUtil.getSession().createQuery(sql);
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