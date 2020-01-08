package org.openelisglobal.login.service;

import java.util.Optional;

import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.login.valueholder.Login;

public interface LoginService extends BaseObjectService<Login, String> {

    String DEFAULT_ADMIN_USER_NAME = "admin";

    boolean isUserAdmin(Login login) throws LIMSRuntimeException;

    int getPasswordExpiredDayNo(Login login);

    Login getUserProfile(String loginName);

    int getSystemUserId(Login login);

    void hashPassword(Login login, String password);

    Optional<Login> getValidatedLogin(String loginName, String password);

    boolean defaultAdminExists();

    boolean nonDefaultAdminExists();

    boolean isHashedPassword(String password);

}
