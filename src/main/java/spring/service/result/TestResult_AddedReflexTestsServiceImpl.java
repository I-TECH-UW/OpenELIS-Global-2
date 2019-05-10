package spring.service.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.result.dao.TestResult_AddedReflexTestsDAO;
import us.mn.state.health.lims.result.valueholder.TestResult_AddedReflexTests;

@Service
public class TestResult_AddedReflexTestsServiceImpl extends BaseObjectServiceImpl<TestResult_AddedReflexTests> implements TestResult_AddedReflexTestsService {
  @Autowired
  protected TestResult_AddedReflexTestsDAO baseObjectDAO;

  TestResult_AddedReflexTestsServiceImpl() {
    super(TestResult_AddedReflexTests.class);
  }

  @Override
  protected TestResult_AddedReflexTestsDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
