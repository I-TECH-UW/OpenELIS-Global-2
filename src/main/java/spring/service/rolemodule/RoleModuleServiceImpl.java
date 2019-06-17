package spring.service.rolemodule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.systemusermodule.dao.RoleModuleDAO;
import us.mn.state.health.lims.systemusermodule.valueholder.RoleModule;

@Service
public class RoleModuleServiceImpl extends BaseObjectServiceImpl<RoleModule, String> implements RoleModuleService {

	@Autowired
	RoleModuleDAO baseObjectDAO;

	public RoleModuleServiceImpl() {
		super(RoleModule.class);
	}

	@Override
	protected RoleModuleDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public void getData(RoleModule roleModule) {
		baseObjectDAO.getData(roleModule);
	}

	@Override
	@Transactional(readOnly = true)
	public List getAllPermissionModules() {
		return baseObjectDAO.getAllPermissionModules();
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getTotalPermissionModuleCount() {
		return baseObjectDAO.getTotalPermissionModuleCount();
	}

	@Override
	@Transactional(readOnly = true)
	public List getPageOfPermissionModules(int startingRecNo) {
		return baseObjectDAO.getPageOfPermissionModules(startingRecNo);
	}

	@Override
	@Transactional(readOnly = true)
	public List getNextPermissionModuleRecord(String id) {
		return baseObjectDAO.getNextPermissionModuleRecord(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List getAllPermissionModulesByAgentId(int systemUserId) {
		return baseObjectDAO.getAllPermissionModulesByAgentId(systemUserId);
	}

	@Override
	public boolean doesUserHaveAnyModules(int userId) {
		return baseObjectDAO.doesUserHaveAnyModules(userId);
	}

	@Override
	@Transactional(readOnly = true)
	public List getPreviousPermissionModuleRecord(String id) {
		return baseObjectDAO.getPreviousPermissionModuleRecord(id);
	}

	@Override
	@Transactional(readOnly = true)
	public RoleModule getRoleModuleByRoleAndModuleId(String roleId, String moduleId) {
		return baseObjectDAO.getRoleModuleByRoleAndModuleId(roleId, moduleId);
	}

	@Override
	public String insert(RoleModule roleModule) {
		if (getBaseObjectDAO().duplicateRoleModuleExists(roleModule)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + roleModule.getPermissionAgentId());
		}
		return super.insert(roleModule);
	}

	@Override
	public RoleModule save(RoleModule roleModule) {
		if (getBaseObjectDAO().duplicateRoleModuleExists(roleModule)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + roleModule.getPermissionAgentId());
		}
		return super.save(roleModule);
	}

	@Override
	public RoleModule update(RoleModule roleModule) {
		if (getBaseObjectDAO().duplicateRoleModuleExists(roleModule)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + roleModule.getPermissionAgentId());
		}
		return super.update(roleModule);
	}

}
