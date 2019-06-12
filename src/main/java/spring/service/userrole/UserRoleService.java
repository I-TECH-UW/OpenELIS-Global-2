package spring.service.userrole;

import java.util.Collection;
import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.userrole.valueholder.UserRole;
import us.mn.state.health.lims.userrole.valueholder.UserRolePK;

public interface UserRoleService extends BaseObjectService<UserRole, UserRolePK> {

	List<String> getRoleIdsForUser(String userId);

	boolean userInRole(String userId, String roleName);

	boolean userInRole(String userId, Collection<String> roleNames);

}
