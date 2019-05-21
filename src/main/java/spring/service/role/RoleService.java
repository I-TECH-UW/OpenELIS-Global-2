package spring.service.role;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.role.valueholder.Role;

public interface RoleService extends BaseObjectService<Role> {

	List<Role> getAllActiveRoles();
}
