package spring.service.login;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.login.dao.LoginDAO;
import us.mn.state.health.lims.login.valueholder.Login;
import us.mn.state.health.lims.security.PasswordUtil;

@Service
public class LoginServiceImpl extends BaseObjectServiceImpl<Login> implements LoginService {
	@Autowired
	protected LoginDAO baseObjectDAO;

	LoginServiceImpl() {
		super(Login.class);
	}

	@Override
	protected LoginDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional 
	public Login get(String id) {
		Login login = super.get(id);
		inferExtraData(login);
		return login;

	}

	@Override
	@Transactional 
	public Optional<Login> getMatch(String propertyName, Object propertyValue) {
		Optional<Login> login = super.getMatch(propertyName, propertyValue);
		if (login.isPresent()) {
			inferExtraData(login.get());
		}
		return login;
	}

	@Override
	@Transactional 
	public Optional<Login> getMatch(Map<String, Object> propertyValues) {
		Optional<Login> login = super.getMatch(propertyValues);
		if (login.isPresent()) {
			inferExtraData(login.get());
		}
		return login;
	}

	@Transactional 
	private void inferExtraData(Login login) {
		login.setSystemUserId(baseObjectDAO.getSystemUserId(login));
		login.setPasswordExpiredDayNo(baseObjectDAO.getPasswordExpiredDayNo(login));
	}

	@Override
	@Transactional 
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
	@Transactional 
	public void updatePassword(Login login, String newPassword) {
		PasswordUtil passUtil = new PasswordUtil();
		login.setPassword(passUtil.hashPassword(newPassword));
		Calendar passwordExpiredDate = Calendar.getInstance();
		passwordExpiredDate.add(Calendar.MONTH,
				Integer.parseInt(SystemConfiguration.getInstance().getLoginUserChangePasswordExpiredMonth()));
		login.setPasswordExpiredDate(new Date(passwordExpiredDate.getTimeInMillis()));
		login.setPasswordExpiredDayNo(baseObjectDAO.getPasswordExpiredDayNo(login));

	}
}
