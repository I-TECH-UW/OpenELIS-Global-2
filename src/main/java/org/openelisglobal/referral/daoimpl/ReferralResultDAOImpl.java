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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.referral.dao.ReferralResultDAO;
import org.openelisglobal.referral.valueholder.ReferralResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/*
 */
@Component
@Transactional
public class ReferralResultDAOImpl extends BaseDAOImpl<ReferralResult, String> implements ReferralResultDAO {

    public ReferralResultDAOImpl() {
        super(ReferralResult.class);
    }

//	@Override
//	public boolean insertData(ReferralResult referralResult) throws LIMSRuntimeException {
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(referralResult);
//			referralResult.setId(id);
//
//			auditDAO.saveNewHistory(referralResult, referralResult.getSysUserId(), "referral_result");
//			// closeSession(); // CSL remove old
//		} catch (HibernateException e) {
//			handleException(e, "insertData");
//		}
//
//		return true;
//	}

    @Override
    @Transactional(readOnly = true)
    public ReferralResult getReferralResultById(String referralResultId) throws LIMSRuntimeException {
        if (!GenericValidator.isBlankOrNull(referralResultId)) {
            try {
                ReferralResult referralResult = entityManager.unwrap(Session.class).get(ReferralResult.class,
                        referralResultId);
                // closeSession(); // CSL remove old
                return referralResult;
            } catch (HibernateException e) {
                handleException(e, "getReferralResultById");
            }
        }
        return null;
    }

    @Override
    
    @Transactional(readOnly = true)
    public List<ReferralResult> getReferralResultsForReferral(String referralId) throws LIMSRuntimeException {
        if (!GenericValidator.isBlankOrNull(referralId)) {
            String sql = "from ReferralResult rr where rr.referralId = :referralId order by rr.id";

            try {
                Query query = entityManager.unwrap(Session.class).createQuery(sql);
                query.setInteger("referralId", Integer.parseInt(referralId));
                List<ReferralResult> resultList = query.list();

                // closeSession(); // CSL remove old

                return resultList;

            } catch (HibernateException e) {
                handleException(e, "getReferralResultsForReferral");
            }
        }

        return new ArrayList<>();
    }

    private ReferralResult readReferralResult(String referralResultId) {
        if (!GenericValidator.isBlankOrNull(referralResultId)) {
            try {
                ReferralResult referralResult = entityManager.unwrap(Session.class).get(ReferralResult.class,
                        referralResultId);
                // closeSession(); // CSL remove old
                return referralResult;
            } catch (HibernateException e) {
                handleException(e, "readResult");
            }
        }

        return null;
    }

//	@Override
//	public void updateData(ReferralResult referralResult) throws LIMSRuntimeException {
//		ReferralResult oldData = readReferralResult(referralResult.getId());
//
//		try {
//			auditDAO.saveHistory(referralResult, oldData, referralResult.getSysUserId(),
//					IActionConstants.AUDIT_TRAIL_UPDATE, "referral_result");
//		} catch (HibernateException e) {
//			handleException(e, "updateData");
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(referralResult);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(referralResult);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(referralResult);
//		} catch (HibernateException e) {
//			handleException(e, "updateData");
//		}
//
//	}

//	@Override
//	public void deleteData(ReferralResult referralResult) throws LIMSRuntimeException {
//		ReferralResult oldData = readReferralResult(referralResult.getId());
//
//		try {
//
//			auditDAO.saveHistory(new ReferralResult(), oldData, referralResult.getSysUserId(),
//					IActionConstants.AUDIT_TRAIL_DELETE, "referral_result");
//		} catch (HibernateException e) {
//			handleException(e, "AuditTrail deleteData");
//		}
//
//		try {
//			entityManager.unwrap(Session.class).delete(oldData);
//			// closeSession(); // CSL remove old
//		} catch (HibernateException e) {
//			handleException(e, "deleteData");
//		}
//	}

    @Override
    
    @Transactional(readOnly = true)
    public List<ReferralResult> getReferralsByResultId(String resultId) throws LIMSRuntimeException {
        String sql = "From ReferralResult rr where rr.result.id= :resultId";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("resultId", Integer.parseInt(resultId));
            List<ReferralResult> referralResults = query.list();

            // closeSession(); // CSL remove old

            return referralResults;
        } catch (HibernateException e) {
            handleException(e, "getReferralsByResultId");
        }

        return null;
    }
}