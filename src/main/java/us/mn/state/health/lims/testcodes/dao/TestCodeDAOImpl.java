package us.mn.state.health.lims.testcodes.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import  us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.testcodes.valueholder.TestCode;
import us.mn.state.health.lims.testcodes.valueholder.TestSchemaPK;

@Component
@Transactional
public class TestCodeDAOImpl extends BaseDAOImpl<TestCode, TestSchemaPK> implements TestCodeDAO {
	TestCodeDAOImpl() {
		super(TestCode.class);
	}
}
