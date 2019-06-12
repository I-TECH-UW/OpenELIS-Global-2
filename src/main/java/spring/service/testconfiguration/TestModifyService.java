package spring.service.testconfiguration;

import java.util.List;

import spring.generated.testconfiguration.controller.TestModifyEntryController.TestAddParams;
import spring.generated.testconfiguration.controller.TestModifyEntryController.TestSet;
import us.mn.state.health.lims.localization.valueholder.Localization;

public interface TestModifyService {

	void updateTestSets(List<TestSet> testSets, TestAddParams testAddParams, Localization nameLocalization,
			Localization reportingNameLocalization, String currentUserId);

}
