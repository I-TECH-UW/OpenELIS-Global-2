package spring.service.systemusersection;

import java.lang.Integer;
import java.lang.String;
import java.util.List;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.systemusersection.valueholder.SystemUserSection;

public interface SystemUserSectionService extends BaseObjectService<SystemUserSection> {
	void getData(SystemUserSection systemUserSection);

	void deleteData(List systemUserSection);

	void updateData(SystemUserSection systemUserSection);

	boolean insertData(SystemUserSection systemUserSection);

	List getAllSystemUserSections();

	List getPageOfSystemUserSections(int startingRecNo);

	List getNextSystemUserSectionRecord(String id);

	Integer getTotalSystemUserSectionCount();

	List getPreviousSystemUserSectionRecord(String id);

	List getAllSystemUserSectionsBySystemUserId(int systemUserId);
}
