package spring.service.referral;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.referral.dao.ReferralReasonDAO;
import us.mn.state.health.lims.referral.valueholder.ReferralReason;

@Service
public class ReferralReasonServiceImpl extends BaseObjectServiceImpl<ReferralReason, String> implements ReferralReasonService {
	@Autowired
	protected ReferralReasonDAO baseObjectDAO;

	ReferralReasonServiceImpl() {
		super(ReferralReason.class);
	}

	@Override
	protected ReferralReasonDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public void getData(ReferralReason entity) {
        getBaseObjectDAO().getData(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public List<ReferralReason> getAllReferralReasons() {
        return getBaseObjectDAO().getAllReferralReasons();
	}
}
