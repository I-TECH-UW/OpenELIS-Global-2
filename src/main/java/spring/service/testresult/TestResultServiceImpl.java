package spring.service.testresult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testresult.dao.TestResultDAO;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

@Service
public class TestResultServiceImpl extends BaseObjectServiceImpl<TestResult> implements TestResultService {
	@Autowired
	protected TestResultDAO baseObjectDAO;

	TestResultServiceImpl() {
		super(TestResult.class);
	}

	@Override
	protected TestResultDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional 
	public List<TestResult> getAllActiveTestResultsPerTest(Test test) {
		Map<String, Object> propertyValues = new HashMap<>();
		List<String> orderProperties = new ArrayList<>();
		propertyValues.put("test", test.getId());
		propertyValues.put("isActive", true);
		orderProperties.add("resultGroup");
		orderProperties.add("id");
		return baseObjectDAO.getAllMatchingOrdered(propertyValues, orderProperties, false);
	}

	@Override
	@Transactional 
	public List<TestResult> getActiveTestResultsByTest(String testId) {
		Map<String, Object> propertyValues = new HashMap<>();
		propertyValues.put("test", testId);
		propertyValues.put("isActive", true);
		return baseObjectDAO.getAllMatching(propertyValues);
	}

	@Override
	@Transactional 
	public TestResult getTestResultsByTestAndDictonaryResult(String id, String value) {
		return baseObjectDAO.getTestResultsByTestAndDictonaryResult(id, value);
	}
}
