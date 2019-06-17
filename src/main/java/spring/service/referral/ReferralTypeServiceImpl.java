package spring.service.referral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.referral.dao.ReferralTypeDAO;
import us.mn.state.health.lims.referral.valueholder.ReferralType;

@Service
public class ReferralTypeServiceImpl extends BaseObjectServiceImpl<ReferralType, String> implements ReferralTypeService {
	@Autowired
	protected ReferralTypeDAO baseObjectDAO;

	ReferralTypeServiceImpl() {
		super(ReferralType.class);
	}

	@Override
	protected ReferralTypeDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public ReferralType getReferralTypeByName(String name) {
		return getMatch("name", name).orElse(null);
	}
}
