package spring.service.systemusermodule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.systemusermodule.dao.PermissionModuleDAO;
import us.mn.state.health.lims.systemusermodule.valueholder.PermissionModule;
import us.mn.state.health.lims.systemusermodule.valueholder.RoleModule;

@Service
@Qualifier("PermissionModuleService")
public class PermissionModuleServiceImpl extends BaseObjectServiceImpl<PermissionModule>
		implements PermissionModuleService {
	
	@Autowired
	@Qualifier("RoleModuleDAO")
	protected PermissionModuleDAO baseObjectDAO;

	protected PermissionModuleServiceImpl() {
		super(PermissionModule.class);
	}

	public PermissionModuleServiceImpl(Class<PermissionModule> class1) {
		super(class1);
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
}
