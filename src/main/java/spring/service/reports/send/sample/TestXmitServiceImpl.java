package spring.service.reports.send.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.reports.send.sample.dao.TestXmitDAO;
import us.mn.state.health.lims.reports.send.sample.valueholder.influenza.TestXmit;

@Service
public class TestXmitServiceImpl extends BaseObjectServiceImpl<TestXmit> implements TestXmitService {
  @Autowired
  protected TestXmitDAO baseObjectDAO;

  TestXmitServiceImpl() {
    super(TestXmit.class);
  }

  @Override
  protected TestXmitDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
