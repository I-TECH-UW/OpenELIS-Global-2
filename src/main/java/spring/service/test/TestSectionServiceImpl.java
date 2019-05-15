package spring.service.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.test.dao.TestSectionDAO;
import us.mn.state.health.lims.test.valueholder.TestSection;

@Service
public class TestSectionServiceImpl extends BaseObjectServiceImpl<TestSection> implements TestSectionService {
  @Autowired
  protected TestSectionDAO baseObjectDAO;

  TestSectionServiceImpl() {
    super(TestSection.class);
  }

  @Override
  protected TestSectionDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
