package spring.service.referral;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.referral.dao.ReferralDAO;
import us.mn.state.health.lims.referral.valueholder.Referral;

@Service
public class ReferralServiceImpl extends BaseObjectServiceImpl<Referral> implements ReferralService {
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
		return getMatch("analysis.id", id).get();
	}

	@Override
	@Transactional 
	public List<Referral> getAllUncanceledOpenReferrals() {
		return baseObjectDAO.getAllUncanceledOpenReferrals();
	}
}
