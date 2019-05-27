package spring.service.systemusermodule;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.systemusermodule.valueholder.PermissionModule;

public interface PermissionModuleService<T extends PermissionModule> extends BaseObjectService<T> {
	void getData(T permissionModule);

	void deleteData(List permissionModules);

	void updateData(T permissionModule);

	boolean insertData(T permissionModule);

	List getAllPermissionModules();

	Integer getTotalPermissionModuleCount();

	List getPageOfPermissionModules(int startingRecNo);

	boolean isAgentAllowedAccordingToName(String id, String string);

	List getNextPermissionModuleRecord(String id);

	List getAllPermissionModulesByAgentId(int systemUserId);

	boolean doesUserHaveAnyModules(int userId);

	List getPreviousPermissionModuleRecord(String id);
}
