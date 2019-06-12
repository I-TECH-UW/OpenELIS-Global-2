package spring.service.testreflex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import spring.service.test.TestServiceImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;
import us.mn.state.health.lims.testreflex.dao.TestReflexDAO;
import us.mn.state.health.lims.testreflex.valueholder.TestReflex;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

@Service
public class TestReflexServiceImpl extends BaseObjectServiceImpl<TestReflex, String> implements TestReflexService {
	@Autowired
	protected TestReflexDAO baseObjectDAO;

	TestReflexServiceImpl() {
		super(TestReflex.class);
	}

	@Override
	protected TestReflexDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void getData(TestReflex testReflex) {
		getBaseObjectDAO().getData(testReflex);

	}

	@Override
	public List getPageOfTestReflexs(int startingRecNo) {
		return getBaseObjectDAO().getPageOfTestReflexs(startingRecNo);
	}

	@Override
	public List getTestReflexesByTestResult(TestResult testResult) {
		return getBaseObjectDAO().getTestReflexesByTestResult(testResult);
	}

	@Override
	public List getPreviousTestReflexRecord(String id) {
		return getBaseObjectDAO().getPreviousTestReflexRecord(id);
	}

	@Override
	public List<TestReflex> getTestReflexsByTestAndFlag(String testId, String flag) {
		return getBaseObjectDAO().getTestReflexsByTestAndFlag(testId, flag);
	}

	@Override
	public List getNextTestReflexRecord(String id) {
		return getBaseObjectDAO().getNextTestReflexRecord(id);
	}

	@Override
	public Integer getTotalTestReflexCount() {
		return getBaseObjectDAO().getTotalTestReflexCount();
	}

	@Override
	public List getAllTestReflexs() {
		return getBaseObjectDAO().getAllTestReflexs();
	}

	@Override
	public boolean isReflexedTest(Analysis analysis) {
		return getBaseObjectDAO().isReflexedTest(analysis);
	}

	@Override
	public List<TestReflex> getFlaggedTestReflexesByTestResult(TestResult testResult, String flag) {
		return getBaseObjectDAO().getFlaggedTestReflexesByTestResult(testResult, flag);
	}

	@Override
	public List getTestReflexesByTestResultAndTestAnalyte(TestResult testResult, TestAnalyte testAnalyte) {
		return getBaseObjectDAO().getTestReflexesByTestResultAndTestAnalyte(testResult, testAnalyte);
	}

	@Override
	public List<TestReflex> getTestReflexsByTestResultAnalyteTest(String testResultId, String analyteId,
			String testId) {
		return getBaseObjectDAO().getTestReflexsByTestResultAnalyteTest(testResultId, analyteId, testId);
	}

	@Override
	public String insert(TestReflex testReflex) {
		if (baseObjectDAO.duplicateTestReflexExists(testReflex)) {
			throw new LIMSDuplicateRecordException(
					"Duplicate record exists for " + TestServiceImpl.getUserLocalizedTestName(testReflex.getTest())
							+ IActionConstants.BLANK + testReflex.getTestAnalyte().getAnalyte().getAnalyteName()
							+ IActionConstants.BLANK + testReflex.getTestResult().getValue() + IActionConstants.BLANK
							+ TestServiceImpl.getUserLocalizedTestName(testReflex.getAddedTest()));
		}
		return super.insert(testReflex);
	}

	@Override
	public TestReflex save(TestReflex testReflex) {
		if (baseObjectDAO.duplicateTestReflexExists(testReflex)) {
			throw new LIMSDuplicateRecordException(
					"Duplicate record exists for " + TestServiceImpl.getUserLocalizedTestName(testReflex.getTest())
							+ IActionConstants.BLANK + testReflex.getTestAnalyte().getAnalyte().getAnalyteName()
							+ IActionConstants.BLANK + testReflex.getTestResult().getValue() + IActionConstants.BLANK
							+ TestServiceImpl.getUserLocalizedTestName(testReflex.getAddedTest()));
		}
		return super.save(testReflex);
	}

	@Override
	public TestReflex update(TestReflex testReflex) {
		if (baseObjectDAO.duplicateTestReflexExists(testReflex)) {
			throw new LIMSDuplicateRecordException(
					"Duplicate record exists for " + TestServiceImpl.getUserLocalizedTestName(testReflex.getTest())
							+ IActionConstants.BLANK + testReflex.getTestAnalyte().getAnalyte().getAnalyteName()
							+ IActionConstants.BLANK + testReflex.getTestResult().getValue() + IActionConstants.BLANK
							+ TestServiceImpl.getUserLocalizedTestName(testReflex.getAddedTest()));
		}
		return super.update(testReflex);
	}
}
