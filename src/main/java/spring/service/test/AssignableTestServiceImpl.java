package spring.service.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.test.valueholder.AssignableTest;

@Service
public class AssignableTestServiceImpl extends BaseObjectServiceImpl<AssignableTest> implements AssignableTestService {
  @Autowired
  protected BaseDAO<AssignableTest> baseObjectDAO;

  AssignableTestServiceImpl() {
    super(AssignableTest.class);
  }

  @Override
  protected BaseDAO<AssignableTest> getBaseObjectDAO() {
    return baseObjectDAO;}
}
