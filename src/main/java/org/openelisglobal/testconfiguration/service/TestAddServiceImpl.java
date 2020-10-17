package org.openelisglobal.testconfiguration.service;

import java.util.List;

import org.openelisglobal.localization.service.LocalizationService;
import org.openelisglobal.localization.valueholder.Localization;
import org.openelisglobal.panelitem.service.PanelItemService;
import org.openelisglobal.panelitem.valueholder.PanelItem;
import org.openelisglobal.resultlimit.service.ResultLimitService;
import org.openelisglobal.resultlimits.valueholder.ResultLimit;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.test.valueholder.Test;
import org.openelisglobal.testconfiguration.controller.TestAddController.TestSet;
import org.openelisglobal.testresult.service.TestResultService;
import org.openelisglobal.testresult.valueholder.TestResult;
import org.openelisglobal.typeofsample.service.TypeOfSampleService;
import org.openelisglobal.typeofsample.service.TypeOfSampleTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestAddServiceImpl implements TestAddService {

    @Autowired
    private TypeOfSampleTestService typeOfSampleTestService;
    @Autowired
    private LocalizationService localizationService;
    @Autowired
    private PanelItemService panelItemService;
    @Autowired
    private TestService testService;
    @Autowired
    private ResultLimitService resultLimitService;
    @Autowired
    private TestResultService testResultService;
    @Autowired
    private TypeOfSampleService typeOfSampleService;

    @Override
    @Transactional
    public void addTests(List<TestSet> testSets, Localization nameLocalization, Localization reportingNameLocalization,
            String currentUserId) {
        nameLocalization.setSysUserId(currentUserId);
        localizationService.insert(nameLocalization);
        reportingNameLocalization.setSysUserId(currentUserId);
        localizationService.insert(reportingNameLocalization);

        for (TestSet set : testSets) {
            set.test.setSysUserId(currentUserId);
            set.test.setLocalizedTestName(nameLocalization);
            set.test.setLocalizedReportingName(reportingNameLocalization);
            testService.insert(set.test);

            for (Test test : set.sortedTests) {
                test.setSysUserId(currentUserId);
                testService.update(test);
            }

            set.typeOfSample.setSysUserId(currentUserId);
            typeOfSampleService.update(set.typeOfSample);

            set.sampleTypeTest.setSysUserId(currentUserId);
            set.sampleTypeTest.setTestId(set.test.getId());
            typeOfSampleTestService.insert(set.sampleTypeTest);

            for (PanelItem item : set.panelItems) {
                item.setSysUserId(currentUserId);
                item.setTest(set.test);
                panelItemService.insert(item);
            }

            for (TestResult testResult : set.testResults) {
                testResult.setSysUserId(currentUserId);
                testResult.setTest(set.test);
                testResultService.insert(testResult);
                if (testResult.getDefault()) {
                    set.test.setDefaultTestResult(testResult);
                }
            }


            for (ResultLimit resultLimit : set.resultLimits) {
                resultLimit.setSysUserId(currentUserId);
                resultLimit.setTestId(set.test.getId());
                resultLimitService.insert(resultLimit);
            }
        }

    }

}
