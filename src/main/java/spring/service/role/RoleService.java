package spring.service.role;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.role.valueholder.Role;

public interface RoleService extends BaseObjectService<Role> {
	void getData(Role role);

	void deleteData(List<Role> roles);

	void updateData(Role role);

	boolean insertData(Role role);

	List getNextRoleRecord(String id);

	List getPreviousRoleRecord(String id);

	List<Role> getAllActiveRoles();

	List<Role> getReferencingRoles(Role role);

	List<Role> getPageOfRoles(int startingRecNo);

	Role getRoleByName(String name);

	List<Role> getAllRoles();

	Role getRoleById(String roleId);
}
