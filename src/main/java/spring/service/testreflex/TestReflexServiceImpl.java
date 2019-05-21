package spring.service.testreflex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.testreflex.dao.TestReflexDAO;
import us.mn.state.health.lims.testreflex.valueholder.TestReflex;

@Service
public class TestReflexServiceImpl extends BaseObjectServiceImpl<TestReflex> implements TestReflexService {
  @Autowired
  protected TestReflexDAO baseObjectDAO;

  TestReflexServiceImpl() {
    super(TestReflex.class);
  }

  @Override
  protected TestReflexDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
