package spring.service.testconfiguration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.generated.testconfiguration.controller.TestModifyEntryController.TestAddParams;
import spring.generated.testconfiguration.controller.TestModifyEntryController.TestSet;
import spring.service.localization.LocalizationService;
import spring.service.panelitem.PanelItemService;
import spring.service.resultlimit.ResultLimitService;
import spring.service.test.TestService;
import spring.service.test.TestServiceImpl;
import spring.service.testresult.TestResultService;
import spring.service.typeofsample.TypeOfSampleTestService;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.panelitem.valueholder.PanelItem;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testresult.valueholder.TestResult;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSampleTest;

@Service
public class TestModifyServiceImpl implements TestModifyService {

	@Autowired
	private TypeOfSampleTestService typeOfSampleTestService;
	@Autowired
	private PanelItemService panelItemService;
	@Autowired
	private TestService testService;
	@Autowired
	private ResultLimitService resultLimitService;
	@Autowired
	private TestResultService testResultService;
	@Autowired
	private LocalizationService localizationService;

	@Override
	public void updateTestSets(List<TestSet> testSets, TestAddParams testAddParams, Localization nameLocalization,
			Localization reportingNameLocalization, String currentUserId) {
		List<TypeOfSampleTest> typeOfSampleTest = typeOfSampleTestService
				.getTypeOfSampleTestsForTest(testAddParams.testId);
		String[] typeOfSamplesTestIDs = new String[typeOfSampleTest.size()];
		for (int i = 0; i < typeOfSampleTest.size(); i++) {
			typeOfSamplesTestIDs[i] = typeOfSampleTest.get(i).getId();
			typeOfSampleTestService.delete(typeOfSamplesTestIDs[i], currentUserId);
		}

		List<PanelItem> panelItems = panelItemService.getPanelItemByTestId(testAddParams.testId);
		for (PanelItem item : panelItems) {
			item.setSysUserId(currentUserId);
		}
		panelItemService.delete(panelItems);

		List<ResultLimit> resultLimitItems = resultLimitService.getAllResultLimitsForTest(testAddParams.testId);
		for (ResultLimit item : resultLimitItems) {
			item.setSysUserId(currentUserId);
			resultLimitService.delete(item);
		}
//		resultLimitService.delete(resultLimitItems);

		for (TestSet set : testSets) {
			set.test.setSysUserId(currentUserId);
			set.test.setLocalizedTestName(nameLocalization);
			set.test.setLocalizedReportingName(reportingNameLocalization);

//gnr: based on testAddUpdate,
//added existing testId to process in createTestSets using testAddParams.testId, delete then insert to modify for most elements

			for (Test test : set.sortedTests) {
				test.setSysUserId(currentUserId);
				// if (!test.getId().equals( set.test.getId() )) {
				testService.update(test);
				// }
			}

			updateTestNames(testAddParams.testId, nameLocalization.getEnglish(), nameLocalization.getFrench(),
					reportingNameLocalization.getEnglish(), reportingNameLocalization.getFrench(), currentUserId);
			updateTestEntities(testAddParams.testId, testAddParams.loinc, currentUserId);

			set.sampleTypeTest.setSysUserId(currentUserId);
			set.sampleTypeTest.setTestId(set.test.getId());
			typeOfSampleTestService.insert(set.sampleTypeTest);

			for (PanelItem item : set.panelItems) {
				item.setSysUserId(currentUserId);
				Test nonTransiantTest = testService.getTestById(set.test.getId());
				item.setTest(nonTransiantTest);
				panelItemService.insert(item);
			}

			for (TestResult testResult : set.testResults) {
				testResult.setSysUserId(currentUserId);
				Test nonTransiantTest = testService.getTestById(set.test.getId());
				testResult.setTest(nonTransiantTest);
				testResultService.insert(testResult);
			}

			for (ResultLimit resultLimit : set.resultLimits) {
				resultLimit.setSysUserId(currentUserId);
				resultLimit.setTestId(set.test.getId());
				resultLimitService.insert(resultLimit);
			}
		}
	}

	private void updateTestEntities(String testId, String loinc, String userId) {
		Test test = new TestServiceImpl(testId).getTest();

		if (test != null) {
			test.setSysUserId(userId);
			test.setLoinc(loinc);
			testService.update(test);
		}
	}

	private void updateTestNames(String testId, String nameEnglish, String nameFrench, String reportNameEnglish,
			String reportNameFrench, String userId) {
		Test test = new TestServiceImpl(testId).getTest();

		if (test != null) {
			Localization name = test.getLocalizedTestName();
			Localization reportingName = test.getLocalizedReportingName();
			name.setEnglish(nameEnglish.trim());
			name.setFrench(nameFrench.trim());
			name.setSysUserId(userId);
			reportingName.setEnglish(reportNameEnglish.trim());
			reportingName.setFrench(reportNameFrench.trim());
			reportingName.setSysUserId(userId);

			localizationService.update(name);
			localizationService.update(reportingName);

		}

		// Refresh test names
		DisplayListService.getInstance().getFreshList(DisplayListService.ListType.ALL_TESTS);
		DisplayListService.getInstance().getFreshList(DisplayListService.ListType.ORDERABLE_TESTS);
	}

}
