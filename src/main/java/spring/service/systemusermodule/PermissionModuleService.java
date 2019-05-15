package spring.service.systemusermodule;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.systemusermodule.valueholder.PermissionModule;

public interface PermissionModuleService extends BaseObjectService<PermissionModule> {

	List<PermissionModule> getAllPermissionModulesByAgentId(int parseInt);
}
