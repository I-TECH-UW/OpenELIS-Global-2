package spring.service.systemusermodule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.systemusermodule.dao.PermissionModuleDAO;
import us.mn.state.health.lims.systemusermodule.valueholder.PermissionModule;

@Service
public class PermissionModuleServiceImpl extends BaseObjectServiceImpl<PermissionModule> implements PermissionModuleService {
	@Autowired
	@Qualifier("RoleModuleDAO")
	protected PermissionModuleDAO baseObjectDAO;

	PermissionModuleServiceImpl() {
		super(PermissionModule.class);
	}

	@Override
	protected PermissionModuleDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PermissionModule> getAllPermissionModulesByAgentId(int agentId) {
		return baseObjectDAO.getAllMatching("role.id", agentId);
	}

	@Override
	public void getData(PermissionModule permissionModule) {
        getBaseObjectDAO().getData(permissionModule);

	}

	@Override
	public void deleteData(List permissionModules) {
        getBaseObjectDAO().deleteData(permissionModules);

	}

	@Override
	public void updateData(PermissionModule permissionModule) {
        getBaseObjectDAO().updateData(permissionModule);

	}

	@Override
	public boolean insertData(PermissionModule permissionModule) {
        return getBaseObjectDAO().insertData(permissionModule);
	}

	@Override
	public List getAllPermissionModules() {
        return getBaseObjectDAO().getAllPermissionModules();
	}

	@Override
	public Integer getTotalPermissionModuleCount() {
        return getBaseObjectDAO().getTotalPermissionModuleCount();
	}

	@Override
	public List getPageOfPermissionModules(int startingRecNo) {
        return getBaseObjectDAO().getPageOfPermissionModules(startingRecNo);
	}

	@Override
	public boolean isAgentAllowedAccordingToName(String id, String string) {
        return getBaseObjectDAO().isAgentAllowedAccordingToName(id,string);
	}

	@Override
	public List getNextPermissionModuleRecord(String id) {
        return getBaseObjectDAO().getNextPermissionModuleRecord(id);
	}

	@Override
	public boolean doesUserHaveAnyModules(int userId) {
        return getBaseObjectDAO().doesUserHaveAnyModules(userId);
	}

	@Override
	public List getPreviousPermissionModuleRecord(String id) {
        return getBaseObjectDAO().getPreviousPermissionModuleRecord(id);
	}
}
