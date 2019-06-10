package spring.service.testconfiguration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.generated.testconfiguration.controller.TestAddController.TestSet;
import spring.service.localization.LocalizationService;
import spring.service.panelitem.PanelItemService;
import spring.service.resultlimit.ResultLimitService;
import spring.service.test.TestService;
import spring.service.testresult.TestResultService;
import spring.service.typeofsample.TypeOfSampleTestService;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.panelitem.valueholder.PanelItem;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

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
			}

			for (ResultLimit resultLimit : set.resultLimits) {
				resultLimit.setSysUserId(currentUserId);
				resultLimit.setTestId(set.test.getId());
				resultLimitService.insert(resultLimit);
			}
		}

	}

}
