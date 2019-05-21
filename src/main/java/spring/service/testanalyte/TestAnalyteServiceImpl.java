package spring.service.testanalyte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.testanalyte.dao.TestAnalyteDAO;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;

@Service
public class TestAnalyteServiceImpl extends BaseObjectServiceImpl<TestAnalyte> implements TestAnalyteService {
  @Autowired
  protected TestAnalyteDAO baseObjectDAO;

  TestAnalyteServiceImpl() {
    super(TestAnalyte.class);
  }

  @Override
  protected TestAnalyteDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
