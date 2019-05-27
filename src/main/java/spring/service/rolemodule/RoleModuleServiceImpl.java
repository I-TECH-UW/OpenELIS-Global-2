package spring.service.rolemodule;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.systemusermodule.dao.RoleModuleDAO;
import us.mn.state.health.lims.systemusermodule.valueholder.RoleModule;

@Service
public class RoleModuleServiceImpl extends BaseObjectServiceImpl<RoleModule> implements RoleModuleService {

	@Autowired
	RoleModuleDAO baseObjectDAO;

	public RoleModuleServiceImpl() {
		super(RoleModule.class);
	}

	@Override
	protected BaseDAO<RoleModule> getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void getData(RoleModule roleModule) {
		baseObjectDAO.getData(roleModule);
	}

	@Override
	public void deleteData(List roleModules) {
		baseObjectDAO.deleteData(roleModules);
	}

	@Override
	public void updateData(RoleModule roleModule) {
		baseObjectDAO.updateData(roleModule);
	}

	@Override
	public boolean insertData(RoleModule roleModule) {
		return baseObjectDAO.insert(roleModule) != null;
	}

	@Override
	public Serializable insert(RoleModule roleModule) {
		if (baseObjectDAO.duplicateRoleModuleExists(roleModule)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + roleModule.getPermissionAgentId());
		}
		return super.insert(roleModule);

	}

	@Override
	public List getAllPermissionModules() {
		return baseObjectDAO.getAllPermissionModules();
	}

	@Override
	public Integer getTotalPermissionModuleCount() {
		return baseObjectDAO.getTotalPermissionModuleCount();
	}

	@Override
	public List getPageOfPermissionModules(int startingRecNo) {
		return baseObjectDAO.getPageOfPermissionModules(startingRecNo);
	}

	@Override
	public boolean isAgentAllowedAccordingToName(String id, String string) {
		return baseObjectDAO.isAgentAllowedAccordingToName(id, string);
	}

	@Override
	public List getNextPermissionModuleRecord(String id) {
		return baseObjectDAO.getNextPermissionModuleRecord(id);
	}

	@Override
	public List getAllPermissionModulesByAgentId(int systemUserId) {
		return baseObjectDAO.getAllPermissionModulesByAgentId(systemUserId);
	}

	@Override
	public boolean doesUserHaveAnyModules(int userId) {
		return baseObjectDAO.doesUserHaveAnyModules(userId);
	}

	@Override
	public List getPreviousPermissionModuleRecord(String id) {
		return baseObjectDAO.getPreviousPermissionModuleRecord(id);
	}

	@Override
	public RoleModule getRoleModuleByRoleAndModuleId(String roleId, String moduleId) {
		return baseObjectDAO.getRoleModuleByRoleAndModuleId(roleId, moduleId);
	}

}
