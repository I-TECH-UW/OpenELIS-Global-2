package spring.service.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.valueholder.Test;

@Service
public class TestServiceImpl extends BaseObjectServiceImpl<Test> implements TestService {
  @Autowired
  protected TestDAO baseObjectDAO;

  TestServiceImpl() {
    super(Test.class);
  }

  @Override
  protected TestDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
