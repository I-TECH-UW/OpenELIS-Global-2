package spring.service.referral;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.referral.valueholder.ReferralResult;

public interface ReferralResultService extends BaseObjectService<ReferralResult> {

	List<ReferralResult> getReferralResultsForReferral(String id);
}
