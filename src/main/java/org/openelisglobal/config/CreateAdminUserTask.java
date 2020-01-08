package org.openelisglobal.config;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;

import javax.annotation.PostConstruct;

import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.common.exception.LIMSException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.provider.validation.PasswordValidationFactory;
import org.openelisglobal.login.service.LoginService;
import org.openelisglobal.login.valueholder.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateAdminUserTask {

    private static String PASSWORD_FILEPATH = "adminPassword.txt";
    private static String PASSWORD_MARKER = LoginService.DEFAULT_ADMIN_USER_NAME + ":";

    @Autowired
    private LoginService loginService;

    @PostConstruct
    private void ensureAdminUserIsCreated() {
        if (!loginService.defaultAdminExists()) {
            if (!loginService.nonDefaultAdminExists()) {
                Login login;
                try {
                    login = createAdminUser();
                    loginService.insert(login);
                } catch (LIMSException e) {
                    LogEvent.logError(e);
                }
            }
        }
    }

    private Login createAdminUser() throws LIMSException {
        Login login = new Login();
        login.setLoginName(LoginService.DEFAULT_ADMIN_USER_NAME);
        login.setPasswordExpiredDate(getExpiredDate());
        login.setAccountLocked(IActionConstants.NO);
        login.setAccountDisabled(IActionConstants.NO);
        login.setIsAdmin(IActionConstants.YES);
        login.setUserTimeOut("220");

        String password;
        try {
            byte[] adminUserInfo = getAdminUserInfo();
            password = new String(Arrays.copyOfRange(adminUserInfo, PASSWORD_MARKER.length(), adminUserInfo.length),
                    StandardCharsets.UTF_8);
            if (!loginService.isHashedPassword(password)) {
                if (PasswordValidationFactory.getPasswordValidator().passwordValid(password)) {
                    loginService.hashPassword(login, password);
                } else {
                    throw new LIMSException("could not create default admin user as password was not complex enough");
                }
            } else {
                login.setPassword(password);
            }
        } catch (IOException | URISyntaxException e) {
            throw new LIMSException("could not create default admin user as password could not be read");
        }

        return login;
    }

    private Date getExpiredDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 5);
        return new Date(calendar.getTimeInMillis());
    }

    private byte[] getAdminUserInfo() throws IOException, URISyntaxException {
        return Files.readAllBytes(Paths.get(getClass().getResource(PASSWORD_FILEPATH).toURI()));
    }

}
