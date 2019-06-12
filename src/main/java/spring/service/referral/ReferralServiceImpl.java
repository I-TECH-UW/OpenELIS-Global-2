package spring.service.referral;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.referral.dao.ReferralDAO;
import us.mn.state.health.lims.referral.valueholder.Referral;

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
	@Transactional
	public Referral getReferralByAnalysisId(String id) {
		return getMatch("analysis.id", id).orElse(null);
	}

	@Override
	@Transactional
	public List<Referral> getAllUncanceledOpenReferrals() {
		return baseObjectDAO.getAllUncanceledOpenReferrals();
	}

	@Override
	public Referral getReferralById(String referralId) {
		return getBaseObjectDAO().getReferralById(referralId);
	}

	@Override
	public List<Referral> getAllReferralsBySampleId(String id) {
		return getBaseObjectDAO().getAllReferralsBySampleId(id);
	}

	@Override
	public List<Referral> getAllReferralsByOrganization(String organizationId, Date lowDate, Date highDate) {
		return getBaseObjectDAO().getAllReferralsByOrganization(organizationId, lowDate, highDate);
	}
}
