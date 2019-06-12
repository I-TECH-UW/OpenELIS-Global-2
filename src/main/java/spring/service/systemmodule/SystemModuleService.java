package spring.service.systemmodule;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModule;

public interface SystemModuleService extends BaseObjectService<SystemModule, String> {

	void getData(SystemModule systemModule);

	Integer getTotalSystemModuleCount();

	List getPageOfSystemModules(int startingRecNo);

	List getNextSystemModuleRecord(String id);

	List getAllSystemModules();

	List getPreviousSystemModuleRecord(String id);

	SystemModule getSystemModuleByName(String name);

}
