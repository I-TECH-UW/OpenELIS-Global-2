package org.openelisglobal.testreflex.service;

import java.util.List;

import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.testanalyte.valueholder.TestAnalyte;
import org.openelisglobal.testreflex.valueholder.TestReflex;
import org.openelisglobal.testresult.valueholder.TestResult;

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
