package spring.service.referral;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.referral.dao.ReferralResultDAO;
import us.mn.state.health.lims.referral.valueholder.ReferralResult;

@Service
public class ReferralResultServiceImpl extends BaseObjectServiceImpl<ReferralResult, String>
		implements ReferralResultService {
	@Autowired
	protected ReferralResultDAO baseObjectDAO;

	ReferralResultServiceImpl() {
		super(ReferralResult.class);
	}

	@Override
	protected ReferralResultDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReferralResult> getReferralResultsForReferral(String id) {
		return baseObjectDAO.getAllMatchingOrdered("referralId", id, "id", false);
	}

	@Override
	@Transactional(readOnly = true)
	public ReferralResult getReferralResultById(String referralResultId) {
		return getBaseObjectDAO().getReferralResultById(referralResultId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReferralResult> getReferralsByResultId(String resultId) {
		return getBaseObjectDAO().getReferralsByResultId(resultId);
	}
}
