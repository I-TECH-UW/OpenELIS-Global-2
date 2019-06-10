package spring.service.testconfiguration;

import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;

public interface TestSectionTestAssignService {

	void updateTestAndTestSections(Test test, TestSection testSection, TestSection deActivateTestSection,
			boolean updateTestSection);

}
