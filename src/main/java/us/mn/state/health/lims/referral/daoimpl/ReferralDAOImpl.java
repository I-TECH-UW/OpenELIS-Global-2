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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.referral.dao.ReferralDAO;
import us.mn.state.health.lims.referral.valueholder.Referral;

/*
 */
@Component
@Transactional
public class ReferralDAOImpl extends BaseDAOImpl<Referral, String> implements ReferralDAO {

	public ReferralDAOImpl() {
		super(Referral.class);
	}

//	@Override
//	public boolean insertData(Referral referral) throws LIMSRuntimeException {
//		try {
//			String id = (String) sessionFactory.getCurrentSession().save(referral);
//			referral.setId(id);
//
//			auditDAO.saveNewHistory(referral, referral.getSysUserId(), "referral");
//			// closeSession(); // CSL remove old
//		} catch (HibernateException e) {
//			handleException(e, "insertData");
//		}
//
//		return true;
//	}

	@Override
	public Referral getReferralById(String referralId) throws LIMSRuntimeException {
		try {
			Referral referral = sessionFactory.getCurrentSession().get(Referral.class, referralId);
			// closeSession(); // CSL remove old
			return referral;
		} catch (HibernateException e) {
			handleException(e, "getReferralById");
		}

		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Referral getReferralByAnalysisId(String analysisId) throws LIMSRuntimeException {

		if (!GenericValidator.isBlankOrNull(analysisId)) {
			String sql = "From Referral r where r.analysis.id = :analysisId";

			try {
				Query query = sessionFactory.getCurrentSession().createQuery(sql);
				query.setInteger("analysisId", Integer.parseInt(analysisId));
				List<Referral> referralList = query.list();
				// closeSession(); // CSL remove old
				return referralList.isEmpty() ? null : referralList.get(referralList.size() - 1);
			} catch (HibernateException e) {
				handleException(e, "getReferralByAnalysisId");
			}
		}

		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Referral> getAllUncanceledOpenReferrals() throws LIMSRuntimeException {
		String sql = "From Referral r where r.resultRecievedDate is NULL and r.canceled = 'false' order by r.id";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			List<Referral> referrals = query.list();
			return referrals;
		} catch (HibernateException e) {
			handleException(e, "getAllUncanceledOpenReferrals");
		}
		return null;
	}

	private Referral readResult(String referralId) {
		try {
			Referral referral = sessionFactory.getCurrentSession().get(Referral.class, referralId);
			// closeSession(); // CSL remove old
			return referral;
		} catch (HibernateException e) {
			handleException(e, "readResult");
		}

		return null;
	}

//	@Override
//	public void updateData(Referral referral) throws LIMSRuntimeException {
//		Referral oldData = readResult(referral.getId());
//
//		try {
//			auditDAO.saveHistory(referral, oldData, referral.getSysUserId(), IActionConstants.AUDIT_TRAIL_UPDATE,
//					"referral");
//		} catch (HibernateException e) {
//			handleException(e, "updateData");
//		}
//
//		try {
//			sessionFactory.getCurrentSession().merge(referral);
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			// sessionFactory.getCurrentSession().evict // CSL remove old(referral);
//			// sessionFactory.getCurrentSession().refresh // CSL remove old(referral);
//		} catch (HibernateException e) {
//			handleException(e, "updateData");
//		}
//
//	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Referral> getAllReferralsBySampleId(String id) throws LIMSRuntimeException {
		if (!GenericValidator.isBlankOrNull(id)) {
			String sql = "FROM Referral r WHERE r.analysis.sampleItem.sample.id = :sampleId";

			try {
				Query query = sessionFactory.getCurrentSession().createQuery(sql);
				query.setInteger("sampleId", Integer.parseInt(id));
				List<Referral> referralList = query.list();
				// closeSession(); // CSL remove old
				return referralList;

			} catch (HibernateException e) {
				handleException(e, "getAllReferralsBySampleId");
			}

		}

		return new ArrayList<>();
	}

	/**
	 * @see us.mn.state.health.lims.referral.dao.ReferralDAO#getAllReferralsByOrganization(java.lang.String,
	 *      java.sql.Date, java.sql.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Referral> getAllReferralsByOrganization(String organizationId, Date lowDate, Date highDate) {
		String sql = "FROM Referral r WHERE r.organization.id = :organizationId AND r.requestDate >= :lowDate AND r.requestDate <= :highDate";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setInteger("organizationId", Integer.parseInt(organizationId));
			query.setDate("lowDate", lowDate);
			query.setDate("highDate", highDate);
			List<Referral> referralList = query.list();
			// closeSession(); // CSL remove old
			return referralList;
		} catch (HibernateException e) {
			handleException(e, "getAllReferralsByOrganization");
		}
		return new ArrayList<>();
	}

}