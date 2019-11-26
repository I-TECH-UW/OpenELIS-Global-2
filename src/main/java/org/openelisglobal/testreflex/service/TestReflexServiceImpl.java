package org.openelisglobal.testreflex.service;

import java.util.List;

import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.common.exception.LIMSDuplicateRecordException;
import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.test.service.TestServiceImpl;
import org.openelisglobal.testanalyte.valueholder.TestAnalyte;
import org.openelisglobal.testreflex.dao.TestReflexDAO;
import org.openelisglobal.testreflex.valueholder.TestReflex;
import org.openelisglobal.testresult.valueholder.TestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public void getData(TestReflex testReflex) {
        getBaseObjectDAO().getData(testReflex);

    }

    @Override
    @Transactional(readOnly = true)
    public List<TestReflex> getPageOfTestReflexs(int startingRecNo) {
        return getBaseObjectDAO().getPageOfTestReflexs(startingRecNo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestReflex> getTestReflexesByTestResult(TestResult testResult) {
        return getBaseObjectDAO().getTestReflexesByTestResult(testResult);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestReflex> getTestReflexsByTestAndFlag(String testId, String flag) {
        return getBaseObjectDAO().getTestReflexsByTestAndFlag(testId, flag);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getTotalTestReflexCount() {
        return getBaseObjectDAO().getTotalTestReflexCount();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestReflex> getAllTestReflexs() {
        return getBaseObjectDAO().getAllTestReflexs();
    }

    @Override
    public boolean isReflexedTest(Analysis analysis) {
        return getBaseObjectDAO().isReflexedTest(analysis);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestReflex> getFlaggedTestReflexesByTestResult(TestResult testResult, String flag) {
        return getBaseObjectDAO().getFlaggedTestReflexesByTestResult(testResult, flag);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestReflex> getTestReflexesByTestResultAndTestAnalyte(TestResult testResult, TestAnalyte testAnalyte) {
        return getBaseObjectDAO().getTestReflexesByTestResultAndTestAnalyte(testResult, testAnalyte);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestReflex> getTestReflexsByTestResultAnalyteTest(String testResultId, String analyteId,
            String testId) {
        return getBaseObjectDAO().getTestReflexsByTestResultAnalyteTest(testResultId, analyteId, testId);
    }

    @Override
    public String insert(TestReflex testReflex) {
        if (duplicateTestReflexExists(testReflex)) {
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
        if (duplicateTestReflexExists(testReflex)) {
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
        if (duplicateTestReflexExists(testReflex)) {
            throw new LIMSDuplicateRecordException(
                    "Duplicate record exists for " + TestServiceImpl.getUserLocalizedTestName(testReflex.getTest())
                            + IActionConstants.BLANK + testReflex.getTestAnalyte().getAnalyte().getAnalyteName()
                            + IActionConstants.BLANK + testReflex.getTestResult().getValue() + IActionConstants.BLANK
                            + TestServiceImpl.getUserLocalizedTestName(testReflex.getAddedTest()));
        }
        return super.update(testReflex);
    }

    private boolean duplicateTestReflexExists(TestReflex testReflex) {
        return baseObjectDAO.duplicateTestReflexExists(testReflex);
    }
}
