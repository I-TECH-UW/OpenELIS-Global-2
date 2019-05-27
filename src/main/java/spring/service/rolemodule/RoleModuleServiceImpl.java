package spring.service.rolemodule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import spring.service.systemusermodule.PermissionModuleServiceImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.role.dao.RoleDAO;
import us.mn.state.health.lims.role.valueholder.Role;
import us.mn.state.health.lims.systemusermodule.dao.PermissionModuleDAO;
import us.mn.state.health.lims.systemusermodule.valueholder.PermissionModule;
import us.mn.state.health.lims.systemusermodule.valueholder.RoleModule;

@Service
@Qualifier ("RoleModuleService")
public class RoleModuleServiceImpl extends PermissionModuleServiceImpl implements RoleModuleService {
	@Autowired
	@Qualifier("RoleModuleDAO")
	protected PermissionModuleDAO baseObjectDAO; // is RoleModuleDAO

	RoleModuleServiceImpl() {
		super();
	}

	@Override
	protected PermissionModuleDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public String insert(PermissionModule roleModule) {
		if (baseObjectDAO.duplicateRoleModuleExists((RoleModule) roleModule)) {
			throw new LIMSDuplicateRecordException(
					"Duplicate record exists for " + roleModule.getPermissionAgentId());
		}
		return super.insert(roleModule);
	}
}
