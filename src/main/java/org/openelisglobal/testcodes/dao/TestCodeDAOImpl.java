package org.openelisglobal.testcodes.dao;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.testcodes.valueholder.TestCode;
import org.openelisglobal.testcodes.valueholder.TestSchemaPK;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class TestCodeDAOImpl extends BaseDAOImpl<TestCode, TestSchemaPK> implements TestCodeDAO {
    TestCodeDAOImpl() {
        super(TestCode.class);
    }
}
