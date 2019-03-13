package spring.mine.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.login.dao.LoginDAO;
import us.mn.state.health.lims.login.daoimpl.LoginDAOImpl;
import us.mn.state.health.lims.login.valueholder.Login;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	private LoginDAO loginDao = new LoginDAOImpl();

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String loginName) {

		Login user = loginDao.getUserProfile(loginName);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		boolean disabled = user.getAccountDisabled().equalsIgnoreCase(IActionConstants.YES);
		boolean locked = user.getAccountLocked().equalsIgnoreCase(IActionConstants.YES);
		boolean credentialsExpired = user.getPasswordExpiredDayNo() <= 0;
		return new org.springframework.security.core.userdetails.User(user.getLoginName(), user.getPassword(), !disabled,
				true, !credentialsExpired, !locked, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(Login user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		return authorities;
	}

}
