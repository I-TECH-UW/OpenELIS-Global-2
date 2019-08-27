package org.openelisglobal.testresult.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.test.valueholder.Test;
import org.openelisglobal.testanalyte.valueholder.TestAnalyte;
import org.openelisglobal.testresult.dao.TestResultDAO;
import org.openelisglobal.testresult.valueholder.TestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestResultServiceImpl extends BaseObjectServiceImpl<TestResult, String> implements TestResultService {
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
    @Transactional(readOnly = true)
    public List<TestResult> getAllActiveTestResultsPerTest(Test test) {
        Map<String, Object> propertyValues = new HashMap<>();
        List<String> orderProperties = new ArrayList<>();
        propertyValues.put("test.id", test.getId());
        propertyValues.put("isActive", true);
        orderProperties.add("resultGroup");
        orderProperties.add("id");
        return baseObjectDAO.getAllMatchingOrdered(propertyValues, orderProperties, false);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestResult> getActiveTestResultsByTest(String testId) {
        Map<String, Object> propertyValues = new HashMap<>();
        propertyValues.put("test.id", testId);
        propertyValues.put("isActive", true);
        return baseObjectDAO.getAllMatching(propertyValues);
    }

    @Override
    @Transactional(readOnly = true)
    public TestResult getTestResultsByTestAndDictonaryResult(String id, String value) {
        return baseObjectDAO.getTestResultsByTestAndDictonaryResult(id, value);
    }

    @Override
    @Transactional(readOnly = true)
    public void getData(TestResult testResult) {
        getBaseObjectDAO().getData(testResult);

    }

    @Override
    @Transactional(readOnly = true)
    public List getNextTestResultRecord(String id) {
        return getBaseObjectDAO().getNextTestResultRecord(id);
    }

    @Override
    @Transactional(readOnly = true)
    public TestResult getTestResultById(TestResult testResult) {
        return getBaseObjectDAO().getTestResultById(testResult);
    }

    @Override
    @Transactional(readOnly = true)
    public List getPageOfTestResults(int startingRecNo) {
        return getBaseObjectDAO().getPageOfTestResults(startingRecNo);
    }

    @Override
    @Transactional(readOnly = true)
    public List getPreviousTestResultRecord(String id) {
        return getBaseObjectDAO().getPreviousTestResultRecord(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List getAllTestResults() {
        return getBaseObjectDAO().getAllTestResults();
    }

    @Override
    @Transactional(readOnly = true)
    public List getTestResultsByTestAndResultGroup(TestAnalyte testAnalyte) {
        return getBaseObjectDAO().getTestResultsByTestAndResultGroup(testAnalyte);
    }
}
