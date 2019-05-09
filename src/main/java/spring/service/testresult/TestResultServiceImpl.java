package spring.service.testresult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.testresult.dao.TestResultDAO;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

@Service
public class TestResultServiceImpl extends BaseObjectServiceImpl<TestResult> implements TestResultService {
  @Autowired
  protected TestResultDAO baseObjectDAO;

  TestResultServiceImpl() {
    super(TestResult.class);
  }

  @Override
  protected TestResultDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
