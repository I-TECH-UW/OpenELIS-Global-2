package spring.service.systemuser;

import java.util.List;

import us.mn.state.health.lims.login.valueholder.Login;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;

public interface UserService {

	void updateLoginUser(Login loginUser, boolean loginUserNew, SystemUser systemUser, boolean systemUserNew,
			List<String> selectedRoles, String loggedOnUserId);

}
