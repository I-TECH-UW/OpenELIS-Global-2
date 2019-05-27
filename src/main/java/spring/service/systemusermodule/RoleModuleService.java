package spring.service.systemusermodule;

import us.mn.state.health.lims.systemusermodule.valueholder.RoleModule;

public interface RoleModuleService extends PermissionModuleService<RoleModule> {

	RoleModule getRoleModuleByRoleAndModuleId(String roleId, String moduleId);

}
