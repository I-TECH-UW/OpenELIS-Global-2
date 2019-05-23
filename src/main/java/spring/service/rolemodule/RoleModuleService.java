package spring.service.rolemodule;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.rolemodule.valueholder.RoleModule;

public interface RoleModuleService extends BaseObjectService<RoleModule> {

	void insertData(RoleModule workplanResultModule);
}
