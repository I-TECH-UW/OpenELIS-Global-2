package spring.service.testreflex;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;
import us.mn.state.health.lims.testreflex.valueholder.TestReflex;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

public interface TestReflexService extends BaseObjectService<TestReflex, String> {
	void getData(TestReflex testReflex);

	List getPageOfTestReflexs(int startingRecNo);

	List getTestReflexesByTestResult(TestResult testResult);

	List getPreviousTestReflexRecord(String id);

	List<TestReflex> getTestReflexsByTestAndFlag(String testId, String flag);

	List getNextTestReflexRecord(String id);

	Integer getTotalTestReflexCount();

	List getAllTestReflexs();

	boolean isReflexedTest(Analysis analysis);

	List<TestReflex> getFlaggedTestReflexesByTestResult(TestResult testResult, String flag);

	List getTestReflexesByTestResultAndTestAnalyte(TestResult testResult, TestAnalyte testAnalyte);

	List<TestReflex> getTestReflexsByTestResultAnalyteTest(String testResultId, String analyteId, String testId);
}
