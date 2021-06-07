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
package org.openelisglobal.referral.daoimpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.validator.GenericValidator;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.referral.dao.ReferralDAO;
import org.openelisglobal.referral.valueholder.Referral;
import org.openelisglobal.referral.valueholder.ReferralStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
//			String id = (String) entityManager.unwrap(Session.class).save(referral);
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
    @Transactional(readOnly = true)
    public Referral getReferralById(String referralId) throws LIMSRuntimeException {
        try {
            Referral referral = entityManager.unwrap(Session.class).get(Referral.class, referralId);
            // closeSession(); // CSL remove old
            return referral;
        } catch (HibernateException e) {
            handleException(e, "getReferralById");
        }

        return null;
    }

    @Override

    @Transactional(readOnly = true)
    public Referral getReferralByAnalysisId(String analysisId) throws LIMSRuntimeException {

        if (!GenericValidator.isBlankOrNull(analysisId)) {
            String sql = "From Referral r where r.analysis.id = :analysisId";

            try {
                Query query = entityManager.unwrap(Session.class).createQuery(sql);
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

    @SuppressWarnings("unused")
    private Referral readResult(String referralId) {
        try {
            Referral referral = entityManager.unwrap(Session.class).get(Referral.class, referralId);
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
//			entityManager.unwrap(Session.class).merge(referral);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(referral);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(referral);
//		} catch (HibernateException e) {
//			handleException(e, "updateData");
//		}
//
//	}

    @Override

    @Transactional(readOnly = true)
    public List<Referral> getAllReferralsBySampleId(String id) throws LIMSRuntimeException {
        if (!GenericValidator.isBlankOrNull(id)) {
            String sql = "FROM Referral r WHERE r.analysis.sampleItem.sample.id = :sampleId";

            try {
                Query query = entityManager.unwrap(Session.class).createQuery(sql);
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
     * @see org.openelisglobal.referral.dao.ReferralDAO#getAllReferralsByOrganization(java.lang.String,
     *      java.sql.Date, java.sql.Date)
     */

    @Override
    @Transactional(readOnly = true)
    public List<Referral> getAllReferralsByOrganization(String organizationId, Date lowDate, Date highDate) {
        String sql = "FROM Referral r WHERE r.organization.id = :organizationId AND r.requestDate >= :lowDate AND r.requestDate <= :highDate";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
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

    @Override
    public List<Referral> getReferralsByStatus(List<ReferralStatus> statuses) {
        String sql = "From Referral r where r.status in (:statuses) order by r.id";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("statuses", statuses.stream().map(e -> e.name()).collect(Collectors.toList()));
            List<Referral> referrals = query.list();
            return referrals;
        } catch (HibernateException e) {
            handleException(e, "getAllReferralsByStatus");
        }
        return null;
    }

}