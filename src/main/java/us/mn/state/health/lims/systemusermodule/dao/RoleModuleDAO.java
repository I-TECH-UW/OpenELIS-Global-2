package us.mn.state.health.lims.systemusermodule.dao;

import us.mn.state.health.lims.systemusermodule.valueholder.RoleModule;

public interface RoleModuleDAO extends PermissionModuleDAO<RoleModule> {

	RoleModule getRoleModuleByRoleAndModuleId(String roleId, String moduleId);

	boolean duplicateRoleModuleExists(RoleModule roleModule);

}
