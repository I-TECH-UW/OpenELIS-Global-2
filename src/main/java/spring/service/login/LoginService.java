package spring.service.login;

import java.util.Optional;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.login.valueholder.Login;

public interface LoginService extends BaseObjectService<Login, String> {
	boolean isUserAdmin(Login login) throws LIMSRuntimeException;

	int getPasswordExpiredDayNo(Login login);

	Login getUserProfile(String loginName);

	int getSystemUserId(Login login);

	void hashPassword(Login login, String password);

	Optional<Login> getValidatedLogin(String loginName, String password);

}
