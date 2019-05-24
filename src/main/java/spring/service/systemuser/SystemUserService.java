package spring.service.systemuser;

import java.lang.Integer;
import java.lang.String;
import java.util.List;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;

public interface SystemUserService extends BaseObjectService<SystemUser> {
	void getData(SystemUser systemUser);

	void deleteData(List systemUsers);

	void updateData(SystemUser systemUser);

	boolean insertData(SystemUser systemUser);

	List getPageOfSystemUsers(int startingRecNo);

	List getAllSystemUsers();

	List getNextSystemUserRecord(String id);

	List getPreviousSystemUserRecord(String id);

	Integer getTotalSystemUserCount();

	SystemUser getDataForLoginUser(String name);

	SystemUser getUserById(String userId);
}
