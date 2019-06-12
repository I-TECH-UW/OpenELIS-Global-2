package spring.service.systemusersection;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.systemusersection.valueholder.SystemUserSection;

public interface SystemUserSectionService extends BaseObjectService<SystemUserSection, String> {
	void getData(SystemUserSection systemUserSection);

	List getAllSystemUserSections();

	List getPageOfSystemUserSections(int startingRecNo);

	List getNextSystemUserSectionRecord(String id);

	Integer getTotalSystemUserSectionCount();

	List getPreviousSystemUserSectionRecord(String id);

	List getAllSystemUserSectionsBySystemUserId(int systemUserId);
}
