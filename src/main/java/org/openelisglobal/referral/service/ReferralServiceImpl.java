package org.openelisglobal.referral.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.referral.dao.ReferralDAO;
import org.openelisglobal.referral.valueholder.Referral;

@Service
public class ReferralServiceImpl extends BaseObjectServiceImpl<Referral, String> implements ReferralService {
	@Autowired
	protected ReferralDAO baseObjectDAO;

	ReferralServiceImpl() {
		super(Referral.class);
	}

	@Override
	protected ReferralDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public Referral getReferralByAnalysisId(String id) {
		return getMatch("analysis.id", id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Referral> getAllUncanceledOpenReferrals() {
		return baseObjectDAO.getAllUncanceledOpenReferrals();
	}

	@Override
	@Transactional(readOnly = true)
	public Referral getReferralById(String referralId) {
		return getBaseObjectDAO().getReferralById(referralId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Referral> getAllReferralsBySampleId(String id) {
		return getBaseObjectDAO().getAllReferralsBySampleId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Referral> getAllReferralsByOrganization(String organizationId, Date lowDate, Date highDate) {
		return getBaseObjectDAO().getAllReferralsByOrganization(organizationId, lowDate, highDate);
	}
}
