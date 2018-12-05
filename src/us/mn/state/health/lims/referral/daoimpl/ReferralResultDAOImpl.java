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
package us.mn.state.health.lims.referral.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.referral.dao.ReferralResultDAO;
import us.mn.state.health.lims.referral.valueholder.ReferralResult;

/*
 */
public class ReferralResultDAOImpl extends BaseDAOImpl implements ReferralResultDAO {

	protected AuditTrailDAO auditDAO = new AuditTrailDAOImpl();

	public boolean insertData(ReferralResult referralResult) throws LIMSRuntimeException {
		try {
			String id = (String) HibernateUtil.getSession().save(referralResult);
			referralResult.setId(id);

			auditDAO.saveNewHistory(referralResult, referralResult.getSysUserId(), "referral_result");
			closeSession();
		} catch (HibernateException e) {
			handleException(e, "insertData");
		}

		return true;
	}

	public ReferralResult getReferralResultById(String referralResultId) throws LIMSRuntimeException {
		if (!GenericValidator.isBlankOrNull(referralResultId)) {
			try {
				ReferralResult referralResult = (ReferralResult) HibernateUtil.getSession().get(ReferralResult.class, referralResultId);
				closeSession();
				return referralResult;
			} catch (HibernateException e) {
				handleException(e, "getReferralResultById");
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ReferralResult> getReferralResultsForReferral(String referralId) throws LIMSRuntimeException {
		if (!GenericValidator.isBlankOrNull(referralId)) {
			String sql = "from ReferralResult rr where rr.referralId = :referralId order by rr.id";

			try {
				Query query = HibernateUtil.getSession().createQuery(sql);
				query.setInteger("referralId", Integer.parseInt(referralId));
				List<ReferralResult> resultList = query.list();

				closeSession();

				return resultList;

			} catch (HibernateException e) {
				handleException(e, "getReferralResultsForReferral");
			}
		}

		return new ArrayList<ReferralResult>();
	}

	private ReferralResult readReferralResult(String referralResultId) {
		if (!GenericValidator.isBlankOrNull(referralResultId)) {
			try {
				ReferralResult referralResult = (ReferralResult) HibernateUtil.getSession().get(ReferralResult.class, referralResultId);
				closeSession();
				return referralResult;
			} catch (HibernateException e) {
				handleException(e, "readResult");
			}
		}

		return null;
	}

	public void updateData(ReferralResult referralResult) throws LIMSRuntimeException {
		ReferralResult oldData = readReferralResult(referralResult.getId());

		try {
			auditDAO.saveHistory(referralResult, oldData, referralResult.getSysUserId(), IActionConstants.AUDIT_TRAIL_UPDATE,
					"referral_result");
		} catch (HibernateException e) {
			handleException(e, "updateData");
		}

		try {
			HibernateUtil.getSession().merge(referralResult);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(referralResult);
			HibernateUtil.getSession().refresh(referralResult);
		} catch (HibernateException e) {
			handleException(e, "updateData");
		}

	}

	public void deleteData(ReferralResult referralResult) throws LIMSRuntimeException {
		ReferralResult oldData = readReferralResult(referralResult.getId());

		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			auditDAO.saveHistory(new ReferralResult(), oldData, referralResult.getSysUserId(), IActionConstants.AUDIT_TRAIL_DELETE,
					"referral_result");
		} catch (HibernateException e) {
			handleException(e, "AuditTrail deleteData");
		}

		try {
			HibernateUtil.getSession().delete(oldData);
			closeSession();
		} catch (HibernateException e) {
			handleException(e, "deleteData");
		}
	}

	@SuppressWarnings("unchecked")
	public List<ReferralResult> getReferralsByResultId(String resultId) throws LIMSRuntimeException {
		String sql = "From ReferralResult rr where rr.result.id= :resultId";

		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("resultId", Integer.parseInt(resultId));
			List<ReferralResult> referralResults = query.list();

			closeSession();

			return referralResults;
		}catch( HibernateException e){
			handleException(e, "getReferralsByResultId");
		}

		return null;
	}
}