package org.openelisglobal.testresult.service;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.test.valueholder.Test;
import org.openelisglobal.testanalyte.valueholder.TestAnalyte;
import org.openelisglobal.testresult.valueholder.TestResult;

public interface TestResultService extends BaseObjectService<TestResult, String> {
    void getData(TestResult testResult);



    TestResult getTestResultById(TestResult testResult);

    List getAllActiveTestResultsPerTest(Test test);

    List<TestResult> getActiveTestResultsByTest(String testId);

    List getPageOfTestResults(int startingRecNo);



    List getAllTestResults();

    TestResult getTestResultsByTestAndDictonaryResult(String testId, String result);

    List getTestResultsByTestAndResultGroup(TestAnalyte testAnalyte);

    List getAllSortedTestResults();
}
