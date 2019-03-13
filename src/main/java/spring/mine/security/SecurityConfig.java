package spring.mine.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// pages open to anyone regardless of authentication status
	public static final String[] OPEN_PAGES = { "/ChangePasswordLogin.do", "/UpdateLoginChangePassword.do",
			"/LoginPage.do" };
	// pages open to anyone that is authenticated
	public static final String[] AUTH_OPEN_PAGES = { "/Home.do", "/Dashboard.do", "/Logout.do", "/MasterListsPage.do" };
	// resource pages open to everyone
	public static final String[] RESOURCE_PAGES = { "/css/**", "/images/**", "/documentation/**", "/scripts/**",
			"/jsp/**" };

	// whether to reject access to protected pages if no modules are assigned to
	// them
	public static final boolean REQUIRE_MODULE = true;
	
	public static final String CONTENT_SECURITY_POLICY = 
			"default-src 'self'; script-src 'self' 'unsafe-inline' 'unsafe-eval';"
			+ " connect-src 'self'; img-src 'self'; style-src 'self' 'unsafe-inline';"
			+ " frame-src 'self'; object-src 'self';";
	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				// allow all users to access these pages no matter authentication status
				.antMatchers(OPEN_PAGES).permitAll()
				.antMatchers(RESOURCE_PAGES).permitAll()
				// ensure all other requests are authenticated
				.anyRequest().authenticated()
				.and()
			// setup login redirection and logic
			.formLogin()
				.loginPage("/LoginPage.do").permitAll()
				.loginProcessingUrl("/ValidateLogin.do")
				.usernameParameter("loginName")
				.passwordParameter("password")
				.failureHandler(customAuthenticationFailureHandler())
				.successHandler(customAuthenticationSuccessHandler())
				.and()
			// setup logout
			.logout()
				.logoutUrl("/Logout.do")
				.logoutSuccessUrl("/LoginPage.do")
				.invalidateHttpSession(true)
				.and()
			.csrf()
				.and()
			.exceptionHandling()
				.accessDeniedPage("/Access_denied")
				.and()
			// add security headers
			.headers()
				.frameOptions().sameOrigin()
				.contentSecurityPolicy(CONTENT_SECURITY_POLICY);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationFailureHandler customAuthenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}

	@Bean
	public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
		return new CustomAuthenticationSuccessHandler();
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
}
