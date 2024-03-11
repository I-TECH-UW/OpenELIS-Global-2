package org.openelisglobal.common.services;

import org.openelisglobal.role.valueholder.Role;
import org.openelisglobal.systemmodule.valueholder.SystemModule;
import org.openelisglobal.systemmodule.valueholder.SystemModuleUrl;

public interface IPluginPermissionService {

    public SystemModule getOrCreateSystemModule(String action, String description);

    public SystemModule getOrCreateSystemModule(String action, String type, String description);

    public Role getSystemRole(String name);

    public boolean bindRoleToModule(Role role, SystemModule module);

    boolean bindRoleToModule(Role role, SystemModule module, SystemModuleUrl moduleUrl);

    SystemModuleUrl getOrCreateSystemModuleUrl(SystemModule systemModule, String urlPath);
}
