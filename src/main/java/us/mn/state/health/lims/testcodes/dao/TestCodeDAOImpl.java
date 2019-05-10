package us.mn.state.health.lims.testcodes.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.testcodes.valueholder.TestCode;

@Component
public class TestCodeDAOImpl extends BaseDAOImpl<TestCode> implements TestCodeDAO {
  TestCodeDAOImpl() {
    super(TestCode.class);
  }
}
