package us.mn.state.health.lims.result.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.result.valueholder.TestResult_AddedReflexTests;

@Component
public class TestResult_AddedReflexTestsDAOImpl extends BaseDAOImpl<TestResult_AddedReflexTests> implements TestResult_AddedReflexTestsDAO {
  TestResult_AddedReflexTestsDAOImpl() {
    super(TestResult_AddedReflexTests.class);
  }
}
