package org.openelisglobal.testreflex.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.openelisglobal.analysis.valueholder.Analysis;
import org.openelisglobal.analyte.service.AnalyteService;
import org.openelisglobal.analyte.valueholder.Analyte;
import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.common.exception.LIMSDuplicateRecordException;
import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.dictionary.service.DictionaryService;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.test.service.TestServiceImpl;
import org.openelisglobal.test.valueholder.Test;
import org.openelisglobal.testanalyte.service.TestAnalyteService;
import org.openelisglobal.testanalyte.valueholder.TestAnalyte;
import org.openelisglobal.testreflex.action.bean.ReflexRule;
import org.openelisglobal.testreflex.action.bean.ReflexRuleCondition;
import org.openelisglobal.testreflex.dao.ReflexRuleDAO;
import org.openelisglobal.testreflex.dao.TestReflexDAO;
import org.openelisglobal.testreflex.valueholder.TestReflex;
import org.openelisglobal.testresult.service.TestResultService;
import org.openelisglobal.testresult.valueholder.TestResult;
import org.openelisglobal.typeofsample.service.TypeOfSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.openelisglobal.testreflex.action.bean.ReflexRuleAction;

@Service
public class TestReflexServiceImpl extends BaseObjectServiceImpl<TestReflex, String> implements TestReflexService {
    @Autowired
    protected TestReflexDAO baseObjectDAO;
    @Autowired
    protected ReflexRuleDAO reflexRuleDAO ;
    @Autowired
    TestReflexService reflexService;
    @Autowired
    private TestService testService;
    @Autowired
    private TestResultService testResultService;
    @Autowired
    DictionaryService dictionaryService;
    @Autowired
    TypeOfSampleService typeOfSampleService;
    @Autowired
    AnalyteService analyteService;
    @Autowired
    TestAnalyteService testAnalyteService;
    
    final static String REFLEX_RESULT_GROUP = "30" ;
    final static String REFLEX_RESULT_TYPE = "R" ;

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

    @Override
    @Transactional()
    public void saveOrUpdateReflexRule(ReflexRule reflexRule) {
        if (reflexRule.getId() == null) {
            reflexRuleDAO.insert(reflexRule);
            processReflexRule(reflexRule);
        } else {
            reflexRuleDAO.update(reflexRule);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReflexRule> getAllReflexRules() {
        return reflexRuleDAO.getAll();
    }

    @Override
    public void deactivateReflexRule(String id) {
        Optional<ReflexRule> rule = reflexRuleDAO.get(Integer.valueOf(id));
        if(rule.isPresent()){
            rule.get().setActive(false);
            reflexRuleDAO.update(rule.get());
        }   
    }

    private void processReflexRule(ReflexRule rule) {
        Analyte analyte = new Analyte();
        analyte.setAnalyteName(rule.getRuleName());
        analyte.setIsActive(IActionConstants.YES);
        analyte = analyteService.save(analyte);

        for (ReflexRuleCondition condition : rule.getConditions()) {
            if (testAndSampleMatches(condition.getTestId(), condition.getSampleId())) {
                TestAnalyte testAnalyte = new TestAnalyte();
                testAnalyte.setAnalyte(analyte);
                Test triggerTest = testService.getTestById(condition.getTestId());
                testAnalyte.setTest(triggerTest);
                testAnalyte.setResultGroup(REFLEX_RESULT_GROUP);
                testAnalyte.setTestAnalyteType(REFLEX_RESULT_TYPE);
                testAnalyte =  testAnalyteService.save(testAnalyte);
                for (ReflexRuleAction action : rule.getActions()){
                    TestReflex reflex = new TestReflex();
                    //TestResult result = testResultService.get(condition.getValue());
                    List<TestResult> results = testResultService.getActiveTestResultsByTest(triggerTest.getId());
                    Optional<TestResult> result = results.stream().filter(res -> res.getValue().equals(condition.getValue())).findFirst();
                    reflex.setTestResult(result.get());
                    reflex.setTestAnalyte(testAnalyte);
                    reflex.setTest(triggerTest);
                    Test reflexTest = testService.getTestById(action.getReflexTestId());
                    reflex.setAddedTest(reflexTest);
                    reflexService.save(reflex);
                }
            }
        }
    }


    private Boolean testAndSampleMatches(String testId, String sampleTypeId) {
        List<Test> testList = typeOfSampleService.getActiveTestsBySampleTypeId(sampleTypeId, false);
        List<String> testIdList = new ArrayList<>();
        testList.forEach(test -> {
            testIdList.add(test.getId());
        });
        return testIdList.contains(testId);
    }

}
