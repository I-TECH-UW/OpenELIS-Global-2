package spring.service.reports.send.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.reports.send.sample.dao.TestNameXmitDAO;
import us.mn.state.health.lims.reports.send.sample.valueholder.influenza.TestNameXmit;

@Service
public class TestNameXmitServiceImpl extends BaseObjectServiceImpl<TestNameXmit> implements TestNameXmitService {
  @Autowired
  protected TestNameXmitDAO baseObjectDAO;

  TestNameXmitServiceImpl() {
    super(TestNameXmit.class);
  }

  @Override
  protected TestNameXmitDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
