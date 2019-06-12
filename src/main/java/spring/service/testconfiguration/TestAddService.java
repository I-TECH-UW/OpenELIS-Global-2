package spring.service.testconfiguration;

import java.util.List;

import spring.generated.testconfiguration.controller.TestAddController.TestSet;
import us.mn.state.health.lims.localization.valueholder.Localization;

public interface TestAddService {

	void addTests(List<TestSet> testSets, Localization nameLocalization, Localization reportingNameLocalization,
			String currentUserId);

}
