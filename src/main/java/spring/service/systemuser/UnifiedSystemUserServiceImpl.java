package spring.service.systemuser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.login.LoginService;
import spring.service.userrole.UserRoleService;
import us.mn.state.health.lims.login.valueholder.Login;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;
import us.mn.state.health.lims.userrole.valueholder.UserRole;

@Service
public class UnifiedSystemUserServiceImpl implements UnifiedSystemUserService {

	@Autowired
	SystemUserService systemUserService;
	@Autowired
	LoginService loginService;
	@Autowired
	UserRoleService userRoleService;

	@Override
	@Transactional
	public void deleteData(List<UserRole> userRoles, List<SystemUser> systemUsers, List<Login> loginUsers,
			String sysUserId) {
		userRoleService.deleteAll(userRoles);

		for (SystemUser systemUser : systemUsers) {
			// we're not going to actually delete them to preserve auditing
			systemUser = systemUserService.get(systemUser.getId());
			systemUser.setSysUserId(sysUserId);
			systemUserService.delete(systemUser);
		}

		loginService.deleteAll(loginUsers);
	}

}
