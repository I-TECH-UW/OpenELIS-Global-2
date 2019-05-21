package spring.service.referral;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.referral.valueholder.Referral;

public interface ReferralService extends BaseObjectService<Referral> {

	Referral getReferralByAnalysisId(String id);

	List<Referral> getAllUncanceledOpenReferrals();
}
