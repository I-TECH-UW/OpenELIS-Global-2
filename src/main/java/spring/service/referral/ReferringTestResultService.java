package spring.service.referral;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.referral.valueholder.ReferringTestResult;

public interface ReferringTestResultService extends BaseObjectService<ReferringTestResult, String> {

	List<ReferringTestResult> getReferringTestResultsForSampleItem(String id);
}
