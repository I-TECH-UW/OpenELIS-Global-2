package spring.service.renametestsection;

import java.lang.Integer;
import java.lang.String;
import java.util.List;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.renametestsection.valueholder.RenameTestSection;

public interface RenameTestSectionService extends BaseObjectService<RenameTestSection> {
	void getData(RenameTestSection testSection);

	void deleteData(List testSections);

	void updateData(RenameTestSection testSection);

	boolean insertData(RenameTestSection testSection);

	List getTestSections(String filter);

	RenameTestSection getTestSectionByName(RenameTestSection testSection);

	List getNextTestSectionRecord(String id);

	List getPageOfTestSections(int startingRecNo);

	Integer getTotalTestSectionCount();

	List getPreviousTestSectionRecord(String id);

	List getAllTestSections();
}
