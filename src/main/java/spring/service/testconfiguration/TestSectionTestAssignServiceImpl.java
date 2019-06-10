package spring.service.testconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.test.TestSectionService;
import spring.service.test.TestService;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;

@Service
public class TestSectionTestAssignServiceImpl implements TestSectionTestAssignService {

	@Autowired
	private TestService testService;
	@Autowired
	private TestSectionService testSectionService;

	@Override
	@Transactional
	public void updateTestAndTestSections(Test test, TestSection testSection, TestSection deActivateTestSection,
			boolean updateTestSection) {
		testService.update(test);

		if (updateTestSection) {
			testSectionService.update(testSection);
		}

		if (deActivateTestSection != null) {
			testSectionService.update(deActivateTestSection);
		}
	}

}
