package spring.mine.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.login.LoginService;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.login.valueholder.Login;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	LoginService loginService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String loginName) {

		Login user = loginService.getMatch("loginName", loginName).orElseThrow(() -> new UsernameNotFoundException(
				"Unique Username not found, could be duplicates in database or it doesn't exist"));

		boolean disabled = user.getAccountDisabled().equalsIgnoreCase(IActionConstants.YES);
		boolean locked = user.getAccountLocked().equalsIgnoreCase(IActionConstants.YES);
		boolean credentialsExpired = user.getPasswordExpiredDayNo() <= 0;
		return new org.springframework.security.core.userdetails.User(user.getLoginName(), user.getPassword(),
				!disabled, true, !credentialsExpired, !locked, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(Login user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		return authorities;
	}

}
