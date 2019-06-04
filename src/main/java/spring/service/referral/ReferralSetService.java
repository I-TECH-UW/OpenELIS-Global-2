package spring.service.referral;

import java.util.List;
import java.util.Set;

import us.mn.state.health.lims.referral.valueholder.ReferralResult;
import us.mn.state.health.lims.referral.valueholder.ReferralSet;
import us.mn.state.health.lims.sample.valueholder.Sample;

public interface ReferralSetService {

	void updateRefreralSets(List<ReferralSet> referralSetList, List<Sample> modifiedSamples, Set<Sample> parentSamples,
			List<ReferralResult> removableReferralResults, String sysUserId);

}
