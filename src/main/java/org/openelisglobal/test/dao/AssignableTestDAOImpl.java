package org.openelisglobal.test.dao;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.test.valueholder.AssignableTest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AssignableTestDAOImpl extends BaseDAOImpl<AssignableTest, String>
    implements AssignableTestDAO {
  AssignableTestDAOImpl() {
    super(AssignableTest.class);
  }
}
