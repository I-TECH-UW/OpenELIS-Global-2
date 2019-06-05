package spring.service.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.test.dao.AssignableTestDAO;
import us.mn.state.health.lims.test.valueholder.AssignableTest;

@Service
public class AssignableTestServiceImpl extends BaseObjectServiceImpl<AssignableTest, String>
		implements AssignableTestService {
	@Autowired
	protected AssignableTestDAO baseObjectDAO;

	public AssignableTestServiceImpl() {
		super(AssignableTest.class);
	}

	@Override
	protected AssignableTestDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
