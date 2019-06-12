package spring.service.renametestsection;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.renametestsection.valueholder.RenameTestSection;

public interface RenameTestSectionService extends BaseObjectService<RenameTestSection, String> {
	void getData(RenameTestSection testSection);

	List getTestSections(String filter);

	RenameTestSection getTestSectionByName(RenameTestSection testSection);

	List getNextTestSectionRecord(String id);

	List getPageOfTestSections(int startingRecNo);

	Integer getTotalTestSectionCount();

	List getPreviousTestSectionRecord(String id);

	List getAllTestSections();

	RenameTestSection getTestSectionById(String id);
}
