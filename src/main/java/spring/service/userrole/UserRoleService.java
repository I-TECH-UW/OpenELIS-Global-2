package spring.service.userrole;

import java.lang.String;
import java.util.Collection;
import java.util.List;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.userrole.valueholder.UserRole;

public interface UserRoleService extends BaseObjectService<UserRole> {
	void getData(UserRole userRole);

	void deleteData(List<UserRole> userRoles);

	void updateData(UserRole userRole);

	boolean insertData(UserRole userRole);

	List getPageOfUserRoles(int startingRecNo);

	List getNextUserRoleRecord(String id);

	List<String> getRoleIdsForUser(String userId);

	List getPreviousUserRoleRecord(String id);

	boolean userInRole(String userId, String roleName);

	boolean userInRole(String userId, Collection<String> roleNames);

	List getAllUserRoles();
}
