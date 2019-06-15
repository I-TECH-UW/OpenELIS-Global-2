package us.mn.state.health.lims.common.services;

import us.mn.state.health.lims.role.valueholder.Role;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModule;

public interface IPluginPermissionService {

	public SystemModule getOrCreateSystemModule(String action, String description);

	public SystemModule getOrCreateSystemModule(String action, String type, String description);

	public Role getSystemRole(String name);

	public boolean bindRoleToModule(Role role, SystemModule module);
}
