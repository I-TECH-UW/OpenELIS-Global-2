package org.openelisglobal.security;

import javax.servlet.ServletContext;

import org.jasypt.util.text.AES256TextEncryptor;
import org.jasypt.util.text.TextEncryptor;
import org.openelisglobal.spring.util.SpringContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.MultipartFilter;

@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    // pages that have special security constraints
    public static final String[] OPEN_PAGES = { "/ChangePasswordLogin.do",
            "/UpdateLoginChangePassword.do" };
    public static final String[] LOGIN_PAGES = { "/LoginPage.do", "/ValidateLogin.do" };
    public static final String[] AUTH_OPEN_PAGES = { "/Home.do", "/Dashboard.do", "/Logout.do", "/MasterListsPage.do" };
    public static final String[] RESOURCE_PAGES = { "/css/**", "/favicon/**", "/images/**", "/documentation/**",
            "/scripts/**", "/jsp/**" };
    public static final String[] HTTP_BASIC_SERVLET_PAGES = { "/pluginServlet/**", "/importAnalyzer",
            "/fhir/**" };
//    public static final String[] CLIENT_CERTIFICATE_PAGES = {};

    private static final String CONTENT_SECURITY_POLICY = "default-src 'self'; script-src 'self' 'unsafe-inline' 'unsafe-eval';"
            + " connect-src 'self'; img-src 'self'; style-src 'self' 'unsafe-inline';"
            + " frame-src *.openlmis.org 'self'; object-src 'self';";

    @Value("${encryption.general.password:dev}")
    private String encryptionPassword;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Configuration
    @Order(1)
    public static class httpBasicServletSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            CharacterEncodingFilter filter = new CharacterEncodingFilter();
            filter.setEncoding("UTF-8");
            filter.setForceEncoding(true);
            http.addFilterBefore(filter, CsrfFilter.class);
            MultipartFilter multipartFilter = new MultipartFilter();
            multipartFilter.setServletContext(SpringContext.getBean(ServletContext.class));
            http.addFilterBefore(multipartFilter, CsrfFilter.class);
            // for all requests going to a http basic page, use this security configuration
            http.requestMatchers().antMatchers(HTTP_BASIC_SERVLET_PAGES).and().authorizeRequests().anyRequest()
                    // ensure they are authenticated
                    .authenticated().and()
                    // ensure they authenticate with http basic
                    .httpBasic().and()
                    // disable csrf as it is not needed for httpBasic
                    .csrf().disable()//
                    .addFilterAt(SpringContext.getBean(BasicAuthFilter.class), BasicAuthenticationFilter.class)
                    // add security headers
                    .headers().frameOptions().sameOrigin().contentSecurityPolicy(CONTENT_SECURITY_POLICY);
        }

    }

//    @Configuration
//    @Order(2)
//    public static class clientCertificateSecurityConfiguration extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            CharacterEncodingFilter filter = new CharacterEncodingFilter();
//            filter.setEncoding("UTF-8");
//            filter.setForceEncoding(true);
//            http.addFilterBefore(filter, CsrfFilter.class);
//
//            // for all requests going to a client cert page, use this security configuration
//            http.requestMatchers().antMatchers(CLIENT_CERTIFICATE_PAGES).and().authorizeRequests().anyRequest()
//                    // ensure they are authenticated
//                    .authenticated().and().x509().subjectPrincipalRegex("CN=(.*?)(?:,|$)")
//                    .userDetailsService(allowAllUserDetailsService()).and()
//                    // disable csrf as it is not needed for httpBasic
//                    .csrf().disable();
//        }
//    }

    @Configuration
    @Order(2)
    public static class openSecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            CharacterEncodingFilter filter = new CharacterEncodingFilter();
            filter.setEncoding("UTF-8");
            filter.setForceEncoding(true);
            http.addFilterBefore(filter, CsrfFilter.class);
            MultipartFilter multipartFilter = new MultipartFilter();
            multipartFilter.setServletContext(SpringContext.getBean(ServletContext.class));
            http.addFilterBefore(multipartFilter, CsrfFilter.class);

            // for all requests going to open pages, use this security configuration
            http.requestMatchers().antMatchers(OPEN_PAGES).and().authorizeRequests().anyRequest().permitAll().and()
                    // disable csrf as it is not needed for open pages
                    .csrf().disable().headers().frameOptions().sameOrigin()
                    .contentSecurityPolicy(CONTENT_SECURITY_POLICY);
        }

    }

    @Configuration
    public static class defaultSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            CharacterEncodingFilter filter = new CharacterEncodingFilter();
            filter.setEncoding("UTF-8");
            filter.setForceEncoding(true);
            http.addFilterBefore(filter, CsrfFilter.class);
            MultipartFilter multipartFilter = new MultipartFilter();
            multipartFilter.setServletContext(SpringContext.getBean(ServletContext.class));
            http.addFilterBefore(multipartFilter, CsrfFilter.class);

            http.authorizeRequests()
                    // allow all users to access these pages no matter authentication status
                    .antMatchers(LOGIN_PAGES).permitAll().antMatchers(RESOURCE_PAGES).permitAll()
                    // ensure all other requests are authenticated
                    .anyRequest().authenticated().and()
                    // setup login redirection and logic
                    .formLogin().loginPage("/LoginPage.do").permitAll().loginProcessingUrl("/ValidateLogin.do")
                    .usernameParameter("loginName").passwordParameter("password")
                    .failureHandler(customAuthenticationFailureHandler())
                    .successHandler(customAuthenticationSuccessHandler()).and()
                    // setup logout
                    .logout().logoutUrl("/Logout.do").logoutSuccessUrl("/LoginPage.do").invalidateHttpSession(true)
                    .and().sessionManagement().invalidSessionUrl("/LoginPage.do").sessionFixation().migrateSession()
                    .and().csrf().and()
                    // add security headers
                    .headers().frameOptions().sameOrigin().contentSecurityPolicy(CONTENT_SECURITY_POLICY);
        }

        @Bean
        public AuthenticationFailureHandler customAuthenticationFailureHandler() {
            return new CustomAuthenticationFailureHandler();
        }

        @Bean
        public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
            return new CustomFormAuthenticationSuccessHandler();
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public static UserDetailsService allowAllUserDetailsService() {
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) {
//                return new User("falseIdol", "", new ArrayList<>());
//            }
//        };
//    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public TextEncryptor textEncryptor() {
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword(encryptionPassword);
        return textEncryptor;
    }
}
