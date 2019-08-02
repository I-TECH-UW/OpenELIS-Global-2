package org.openelisglobal.login.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.common.exception.LIMSDuplicateRecordException;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.login.dao.LoginDAO;
import org.openelisglobal.login.valueholder.Login;
import org.openelisglobal.security.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl extends BaseObjectServiceImpl<Login, String> implements LoginService {

    @Autowired
    protected LoginDAO baseObjectDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}"); // make sure this
                                                                                                // variable is current

    LoginServiceImpl() {
        super(Login.class);
    }

    @Override
    protected LoginDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUserAdmin(Login login) throws LIMSRuntimeException {
        return login.getIsAdmin().equalsIgnoreCase(IActionConstants.YES);
    }

    @Override
    @Transactional(readOnly = true)
    public Login get(String id) {
        Login login = super.get(id);
        inferExtraData(login);
        return login;

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Login> getMatch(String propertyName, Object propertyValue) {
        Optional<Login> login = super.getMatch(propertyName, propertyValue);
        if (login.isPresent()) {
            inferExtraData(login.get());
        }
        return login;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Login> getMatch(Map<String, Object> propertyValues) {
        Optional<Login> login = super.getMatch(propertyValues);
        if (login.isPresent()) {
            inferExtraData(login.get());
        }
        return login;
    }

    private void inferExtraData(Login login) {
        login.setSystemUserId(baseObjectDAO.getSystemUserId(login));
        login.setPasswordExpiredDayNo(baseObjectDAO.getPasswordExpiredDayNo(login));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Login> getValidatedLogin(String loginName, String password) {
        PasswordUtil passUtil = new PasswordUtil();
        List<Login> logins = baseObjectDAO.getAllMatching("loginName", loginName);

        if (logins.size() == 1) {
            Login login = logins.get(0);
            if (passUtil.checkPassword(password, login.getPassword())) {
                inferExtraData(login);
                login.setSysUserId(String.valueOf(login.getSystemUserId()));
                return Optional.of(login);
            }
        }
        return Optional.empty();
    }

    @Override
    public void hashPassword(Login login, String newPassword) {
        PasswordUtil passUtil = new PasswordUtil();
        login.setPassword(passUtil.hashPassword(newPassword));
        Calendar passwordExpiredDate = Calendar.getInstance();
        passwordExpiredDate.add(Calendar.MONTH,
                Integer.parseInt(SystemConfiguration.getInstance().getLoginUserChangePasswordExpiredMonth()));
        login.setPasswordExpiredDate(new Date(passwordExpiredDate.getTimeInMillis()));
        login.setPasswordExpiredDayNo(baseObjectDAO.getPasswordExpiredDayNo(login));

    }

    @Override
    public String insert(Login login) {
        if (getBaseObjectDAO().duplicateLoginNameExists(login)) {
            throw new LIMSDuplicateRecordException("Duplicate record exists for " + login.getLoginName());
        }
        if (!isHashedPassword(login.getPassword())) {
            LogEvent.logWarn(this.getClass().getSimpleName(), "insert",
                    "The password saved does not look like a valid hashed password."
                            + " Ensure that passwords are being hashed before they are stored"
                            + " because storing encrypted or plaintext passwords is disallowed");
        }
        return super.insert(login);
    }

    @Override
    public Login save(Login login) {
        if (getBaseObjectDAO().duplicateLoginNameExists(login)) {
            throw new LIMSDuplicateRecordException("Duplicate record exists for " + login.getLoginName());
        }
        if (!isHashedPassword(login.getPassword())) {
            LogEvent.logWarn(this.getClass().getSimpleName(), "insert",
                    "The password saved does not look like a valid hashed password."
                            + " Ensure that passwords are being hashed before they are stored"
                            + " because storing encrypted or plaintext passwords is disallowed");
        }
        return super.save(login);
    }

    @Override
    public Login update(Login login) {
        if (getBaseObjectDAO().duplicateLoginNameExists(login)) {
            throw new LIMSDuplicateRecordException("Duplicate record exists for " + login.getLoginName());
        }
        if (!isHashedPassword(login.getPassword())) {
            LogEvent.logWarn(this.getClass().getSimpleName(), "insert",
                    "The password saved does not look like a valid hashed password."
                            + " Ensure that passwords are being hashed before they are stored"
                            + " because storing encrypted or plaintext passwords is disallowed");
        }
        return super.update(login);
    }

    @Override
    @Transactional(readOnly = true)
    public int getPasswordExpiredDayNo(Login login) {
        return getBaseObjectDAO().getPasswordExpiredDayNo(login);
    }

    @Override
    @Transactional(readOnly = true)
    public Login getUserProfile(String loginName) {
        return getMatch("loginName", loginName).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public int getSystemUserId(Login login) {
        return getBaseObjectDAO().getSystemUserId(login);
    }

    private boolean isHashedPassword(String password) {
        return BCryptPasswordEncoder.class.isInstance(passwordEncoder) && BCRYPT_PATTERN.matcher(password).matches();
    }

}
