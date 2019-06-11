package spring.service.userrole;

import java.util.ArrayList;
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
	@Transactional
	public List<String> getRoleIdsForUser(String userId) {
		List<UserRole> userRoles = baseObjectDAO.getAllMatching("compoundId.systemUserId", userId);
		List<String> userRoleIds = new ArrayList<>();
		for (UserRole userRole : userRoles) {
			userRoleIds.add(userRole.getId().getRoleId());
		}
		return userRoleIds;
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
