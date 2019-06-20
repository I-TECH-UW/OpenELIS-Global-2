package spring.service.systemusermodule;

import java.util.List;
import java.util.Set;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.systemusermodule.valueholder.PermissionModule;

public interface PermissionModuleService<T extends PermissionModule> extends BaseObjectService<T, String> {
	void getData(T permissionModule);

	List getAllPermissionModules();

	Integer getTotalPermissionModuleCount();

	List getPageOfPermissionModules(int startingRecNo);

	List getNextPermissionModuleRecord(String id);

	List getAllPermissionModulesByAgentId(int systemUserId);

	boolean doesUserHaveAnyModules(int userId);

	List getPreviousPermissionModuleRecord(String id);

	Set<String> getAllPermittedPagesFromAgentId(int parseInt);
}
