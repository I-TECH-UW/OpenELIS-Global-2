package spring.service.referral;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.referral.dao.ReferringTestResultDAO;
import us.mn.state.health.lims.referral.valueholder.ReferringTestResult;

@Service
public class ReferringTestResultServiceImpl extends BaseObjectServiceImpl<ReferringTestResult, String>
		implements ReferringTestResultService {
	@Autowired
	protected ReferringTestResultDAO baseObjectDAO;

	ReferringTestResultServiceImpl() {
		super(ReferringTestResult.class);
	}

	@Override
	protected ReferringTestResultDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReferringTestResult> getReferringTestResultsForSampleItem(String id) {
		return baseObjectDAO.getReferringTestResultsForSampleItem(id);
	}
}
