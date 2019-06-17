package spring.service.testcodes;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.testcodes.dao.TestCodeDAO;
import us.mn.state.health.lims.testcodes.valueholder.TestCode;
import us.mn.state.health.lims.testcodes.valueholder.TestSchemaPK;

@Service
public class TestCodeServiceImpl extends BaseObjectServiceImpl<TestCode, TestSchemaPK> implements TestCodeService {
	@Autowired
	protected TestCodeDAO baseObjectDAO;

	TestCodeServiceImpl() {
		super(TestCode.class);
		defaultSortOrder = new ArrayList<>();
	}

	@Override
	protected TestCodeDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
