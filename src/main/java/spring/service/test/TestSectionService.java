package spring.service.test;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.test.valueholder.TestSection;

public interface TestSectionService extends BaseObjectService<TestSection, String> {
	void getData(TestSection testSection);

	List getTestSections(String filter);

	TestSection getTestSectionByName(String testSection);

	TestSection getTestSectionByName(TestSection testSection);

	List getNextTestSectionRecord(String id);

	List getPageOfTestSections(int startingRecNo);

	Integer getTotalTestSectionCount();

	List getPreviousTestSectionRecord(String id);

	List<TestSection> getAllTestSections();

	List getTestSectionsBySysUserId(String filter, int sysUserId);

	List getAllTestSectionsBySysUserId(int sysUserId);

	TestSection getTestSectionById(String testSectionId);

	List<TestSection> getAllInActiveTestSections();

	List<TestSection> getAllActiveTestSections();
}
