package spring.service.referral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.referral.dao.ReferralResultDAO;
import us.mn.state.health.lims.referral.valueholder.ReferralResult;

@Service
public class ReferralResultServiceImpl extends BaseObjectServiceImpl<ReferralResult> implements ReferralResultService {
  @Autowired
  protected ReferralResultDAO baseObjectDAO;

  ReferralResultServiceImpl() {
    super(ReferralResult.class);
  }

  @Override
  protected ReferralResultDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
