package us.mn.state.health.lims.reports.send.sample.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.reports.send.sample.valueholder.influenza.TestNameXmit;

@Component
public class TestNameXmitDAOImpl extends BaseDAOImpl<TestNameXmit> implements TestNameXmitDAO {
  TestNameXmitDAOImpl() {
    super(TestNameXmit.class);
  }
}
