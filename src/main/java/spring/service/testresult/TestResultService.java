package spring.service.testresult;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

public interface TestResultService extends BaseObjectService<TestResult, String> {
	void getData(TestResult testResult);

	List getNextTestResultRecord(String id);

	TestResult getTestResultById(TestResult testResult);

	List getAllActiveTestResultsPerTest(Test test);

	List<TestResult> getActiveTestResultsByTest(String testId);

	List getPageOfTestResults(int startingRecNo);

	List getPreviousTestResultRecord(String id);

	List getAllTestResults();

	TestResult getTestResultsByTestAndDictonaryResult(String testId, String result);

	List getTestResultsByTestAndResultGroup(TestAnalyte testAnalyte);
}
