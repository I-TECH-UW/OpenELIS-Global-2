package us.mn.state.health.lims.test.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.test.valueholder.AssignableTest;

@Component
public class AssignableTestDAOImpl extends BaseDAOImpl<AssignableTest> implements AssignableTestDAO {
  AssignableTestDAOImpl() {
    super(AssignableTest.class);
  }
}
