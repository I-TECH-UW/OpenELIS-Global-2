package spring.service.login;

import java.util.Optional;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.login.valueholder.Login;

public interface LoginService extends BaseObjectService<Login> {

	Optional<Login> getValidatedLogin(String loginName, String password);

	void updatePassword(Login login, String string);
}
