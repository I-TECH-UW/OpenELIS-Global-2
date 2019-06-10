package spring.service.systemuser;

import java.util.List;

import us.mn.state.health.lims.login.valueholder.Login;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;
import us.mn.state.health.lims.userrole.valueholder.UserRole;

public interface UnifiedSystemUserService {

	void deleteData(List<UserRole> userRoles, List<SystemUser> systemUsers, List<Login> loginUsers, String sysUserId);

}
