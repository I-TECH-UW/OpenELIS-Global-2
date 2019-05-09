package spring.service.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.result.dao.TestAnalyte_TestResultsDAO;
import us.mn.state.health.lims.result.valueholder.TestAnalyte_TestResults;

@Service
public class TestAnalyte_TestResultsServiceImpl extends BaseObjectServiceImpl<TestAnalyte_TestResults> implements TestAnalyte_TestResultsService {
  @Autowired
  protected TestAnalyte_TestResultsDAO baseObjectDAO;

  TestAnalyte_TestResultsServiceImpl() {
    super(TestAnalyte_TestResults.class);
  }

  @Override
  protected TestAnalyte_TestResultsDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
