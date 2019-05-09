package spring.service.referral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.referral.dao.ReferralDAO;
import us.mn.state.health.lims.referral.valueholder.Referral;

@Service
public class ReferralServiceImpl extends BaseObjectServiceImpl<Referral> implements ReferralService {
  @Autowired
  protected ReferralDAO baseObjectDAO;

  ReferralServiceImpl() {
    super(Referral.class);
  }

  @Override
  protected ReferralDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
