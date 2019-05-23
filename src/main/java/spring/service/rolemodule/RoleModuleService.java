package spring.service.rolemodule;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.systemusermodule.valueholder.RoleModule;

public interface RoleModuleService extends BaseObjectService<RoleModule> {

	void insertData(RoleModule workplanResultModule);
}
