package spring.service.referral;

import java.lang.String;
import java.util.List;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.referral.valueholder.ReferralResult;

public interface ReferralResultService extends BaseObjectService<ReferralResult> {
	void deleteData(ReferralResult referralResult);

	void updateData(ReferralResult referralResult);

	boolean insertData(ReferralResult referralResult);

	ReferralResult getReferralResultById(String referralResultId);

	List<ReferralResult> getReferralResultsForReferral(String referralId);

	List<ReferralResult> getReferralsByResultId(String resultId);
}
