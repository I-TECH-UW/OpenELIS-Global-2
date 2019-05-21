package spring.service.testresult;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

public interface TestResultService extends BaseObjectService<TestResult> {

	List<TestResult> getAllActiveTestResultsPerTest(Test test);

	List<TestResult> getActiveTestResultsByTest(String testId);

	TestResult getTestResultsByTestAndDictonaryResult(String id, String value);
}
