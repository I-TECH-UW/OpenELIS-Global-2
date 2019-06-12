package spring.service.testanalyte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testanalyte.dao.TestAnalyteDAO;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;

@Service
public class TestAnalyteServiceImpl extends BaseObjectServiceImpl<TestAnalyte, String> implements TestAnalyteService {
	@Autowired
	protected TestAnalyteDAO baseObjectDAO;

	TestAnalyteServiceImpl() {
		super(TestAnalyte.class);
	}

	@Override
	protected TestAnalyteDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public TestAnalyte getData(TestAnalyte testAnalyte) {
		return getBaseObjectDAO().getData(testAnalyte);
	}

	@Override
	public List getAllTestAnalytes() {
		return getBaseObjectDAO().getAllTestAnalytes();
	}

	@Override
	public List getPageOfTestAnalytes(int startingRecNo) {
		return getBaseObjectDAO().getPageOfTestAnalytes(startingRecNo);
	}

	@Override
	public List getNextTestAnalyteRecord(String id) {
		return getBaseObjectDAO().getNextTestAnalyteRecord(id);
	}

	@Override
	public List getPreviousTestAnalyteRecord(String id) {
		return getBaseObjectDAO().getPreviousTestAnalyteRecord(id);
	}

	@Override
	public TestAnalyte getTestAnalyteById(TestAnalyte testAnalyte) {
		return getBaseObjectDAO().getTestAnalyteById(testAnalyte);
	}

	@Override
	public List getAllTestAnalytesPerTest(Test test) {
		return getBaseObjectDAO().getAllTestAnalytesPerTest(test);
	}

	@Override
	public List getTestAnalytes(String filter) {
		return getBaseObjectDAO().getTestAnalytes(filter);
	}
}
