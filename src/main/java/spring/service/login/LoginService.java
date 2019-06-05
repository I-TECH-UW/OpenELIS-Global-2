package spring.service.login;

import java.util.List;
import java.util.Optional;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.login.valueholder.Login;

public interface LoginService extends BaseObjectService<Login, String> {
	boolean isUserAdmin(Login login) throws LIMSRuntimeException;

	void getData(Login login);

	void deleteData(List login);

	void updateData(Login login, boolean passwordUpdated);

	boolean insertData(Login login);

	List getNextLoginUserRecord(String id);

	List getPreviousLoginUserRecord(String id);

	int getPasswordExpiredDayNo(Login login);

	Integer getTotalLoginUserCount();

	List getPageOfLoginUsers(int startingRecNo);

	boolean lockAccount(Login login);

	Login getValidateLogin(Login login);

	Login getUserProfile(String loginName);

	boolean unlockAccount(Login login);

	int getSystemUserId(Login login);

	List getAllLoginUsers();

	void updatePassword(Login login);

	void updatePassword(Login login, String string);

	Optional<Login> getValidatedLogin(String loginName, String password);

}
