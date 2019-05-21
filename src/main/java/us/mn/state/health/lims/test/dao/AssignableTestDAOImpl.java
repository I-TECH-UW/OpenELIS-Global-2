package us.mn.state.health.lims.test.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.test.valueholder.AssignableTest;

@Component
@Transactional
public class AssignableTestDAOImpl extends BaseDAOImpl<AssignableTest> implements AssignableTestDAO {
  AssignableTestDAOImpl() {
    super(AssignableTest.class);
  }
}
