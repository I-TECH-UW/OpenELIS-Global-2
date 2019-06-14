package spring.service.userrole;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.userrole.dao.UserRoleDAO;
import us.mn.state.health.lims.userrole.valueholder.UserRole;
import us.mn.state.health.lims.userrole.valueholder.UserRolePK;

@Service
public class UserRoleServiceImpl extends BaseObjectServiceImpl<UserRole, UserRolePK> implements UserRoleService {
	@Autowired
	protected UserRoleDAO baseObjectDAO;

	UserRoleServiceImpl() {
		super(UserRole.class);
	}

	@Override
	protected UserRoleDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> getRoleIdsForUser(String userId) {
		return baseObjectDAO.getRoleIdsForUser(userId);
	}

	@Override
	@Transactional
	public boolean userInRole(String sysUserId, Collection<String> ableToCancelRoleNames) {
		return baseObjectDAO.userInRole(sysUserId, ableToCancelRoleNames);
	}

	@Override
	public boolean userInRole(String userId, String roleName) {
		return getBaseObjectDAO().userInRole(userId, roleName);
	}
}
