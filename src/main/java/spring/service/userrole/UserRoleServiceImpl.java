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
	@Transactional
	public List<String> getRoleIdsForUser(String userId) {
		// doesn't work for composite-ids
//		List<UserRole> userRoles = baseObjectDAO.getAllMatching("compoundId.systemUserId", userId);
//		List<String> userRoleIds = new ArrayList<>();
//		for (UserRole userRole : userRoles) {
//			userRoleIds.add(userRole.getId());
//		}
//		return userRoleIds;
		return baseObjectDAO.getRoleIdsForUser(userId);
	}

	@Override
	@Transactional
	public boolean userInRole(String sysUserId, Collection<String> ableToCancelRoleNames) {
		return baseObjectDAO.userInRole(sysUserId, ableToCancelRoleNames);
	}

	@Override
	public void getData(UserRole userRole) {
		getBaseObjectDAO().getData(userRole);

	}

	@Override
	public void deleteData(List<UserRole> userRoles) {
		getBaseObjectDAO().deleteData(userRoles);

	}

	@Override
	public void updateData(UserRole userRole) {
		getBaseObjectDAO().updateData(userRole);

	}

	@Override
	public boolean insertData(UserRole userRole) {
		return getBaseObjectDAO().insertData(userRole);
	}

	@Override
	public List getPageOfUserRoles(int startingRecNo) {
		return getBaseObjectDAO().getPageOfUserRoles(startingRecNo);
	}

	@Override
	public List getNextUserRoleRecord(String id) {
		return getBaseObjectDAO().getNextUserRoleRecord(id);
	}

	@Override
	public List getPreviousUserRoleRecord(String id) {
		return getBaseObjectDAO().getPreviousUserRoleRecord(id);
	}

	@Override
	public boolean userInRole(String userId, String roleName) {
		return getBaseObjectDAO().userInRole(userId, roleName);
	}

	@Override
	public List getAllUserRoles() {
		return getBaseObjectDAO().getAllUserRoles();
	}
}
