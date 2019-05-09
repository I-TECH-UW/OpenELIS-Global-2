package us.mn.state.health.lims.result.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.result.valueholder.TestAnalyte_TestResults;

@Component
public class TestAnalyte_TestResultsDAOImpl extends BaseDAOImpl<TestAnalyte_TestResults> implements TestAnalyte_TestResultsDAO {
  TestAnalyte_TestResultsDAOImpl() {
    super(TestAnalyte_TestResults.class);
  }
}
