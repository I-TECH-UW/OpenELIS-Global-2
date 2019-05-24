package spring.service.systemusermodule;

import java.lang.Integer;
import java.lang.String;
import java.util.List;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.systemusermodule.valueholder.PermissionModule;

public interface PermissionModuleService extends BaseObjectService<PermissionModule> {
	void getData(PermissionModule permissionModule);

	void deleteData(List permissionModules);

	void updateData(PermissionModule permissionModule);

	boolean insertData(PermissionModule permissionModule);

	List getAllPermissionModules();

	Integer getTotalPermissionModuleCount();

	List getPageOfPermissionModules(int startingRecNo);

	boolean isAgentAllowedAccordingToName(String id, String string);

	List getNextPermissionModuleRecord(String id);

	List getAllPermissionModulesByAgentId(int systemUserId);

	boolean doesUserHaveAnyModules(int userId);

	List getPreviousPermissionModuleRecord(String id);
}
