package spring.service.referral;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.referral.valueholder.ReferralReason;

public interface ReferralReasonService extends BaseObjectService<ReferralReason, String> {
	void getData(ReferralReason entity);

	List<ReferralReason> getAllReferralReasons();
}
