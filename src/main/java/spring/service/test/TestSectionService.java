package spring.service.test;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.test.valueholder.TestSection;

public interface TestSectionService extends BaseObjectService<TestSection> {

	List<TestSection> getAllActiveTestSections();
}
