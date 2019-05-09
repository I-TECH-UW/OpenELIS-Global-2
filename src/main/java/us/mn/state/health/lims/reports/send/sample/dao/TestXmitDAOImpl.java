package us.mn.state.health.lims.reports.send.sample.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.reports.send.sample.valueholder.influenza.TestXmit;

@Component
public class TestXmitDAOImpl extends BaseDAOImpl<TestXmit> implements TestXmitDAO {
  TestXmitDAOImpl() {
    super(TestXmit.class);
  }
}
