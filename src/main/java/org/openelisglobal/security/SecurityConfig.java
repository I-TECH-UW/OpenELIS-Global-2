package org.openelisglobal.security;

import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.text.AES256TextEncryptor;
import org.jasypt.util.text.TextEncryptor;
import org.openelisglobal.config.condition.ConditionalOnProperty;
import org.openelisglobal.spring.util.SpringContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistration.Builder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.saml2.core.Saml2X509Credential;
import org.springframework.security.saml2.core.Saml2X509Credential.Saml2X509CredentialType;
import org.springframework.security.saml2.provider.service.registration.InMemoryRelyingPartyRegistrationRepository;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistration;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.MultipartFilter;

@EnableWebSecurity
public class SecurityConfig {

    // TODO should we move these to the properties files?
    // pages that have special security constraints
    public static final String[] OPEN_PAGES = { "/ChangePasswordLogin", "/UpdateLoginChangePassword" };
    public static final String[] LOGIN_PAGES = { "/LoginPage", "/ValidateLogin" };
    public static final String[] AUTH_OPEN_PAGES = { "/Home", "/Dashboard", "/Logout", "/MasterListsPage" };
    public static final String[] RESOURCE_PAGES = { "/css/**", "/favicon/**", "/images/**", "/documentation/**",
            "/scripts/**", "/jsp/**" };
//    public static final String[] HTTP_BASIC_SERVLET_PAGES = { "/pluginServlet/**", "/importAnalyzer", "/fhir/**" };
//    public static final String[] CLIENT_CERTIFICATE_PAGES = {};

    private static final String CONTENT_SECURITY_POLICY = "default-src 'self'; script-src 'self' 'unsafe-inline' 'unsafe-eval';"
            + " connect-src 'self'; img-src 'self'; style-src 'self' 'unsafe-inline';"
            + " frame-src *.openlmis.org 'self'; object-src 'self';";

    @Value("${encryption.general.password:dev}")
    private String encryptionPassword;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Configuration
    @Order(1)
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
    @Order(2)
    @ConditionalOnProperty(property = "org.itech.login.basic", havingValue = "true", matchIfMissing = true)
    public static class httpBasicServletSecurityConfiguration extends WebSecurityConfigurerAdapter {

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
            http.requestMatcher(new BasicAuthRequestedMatcher()).authorizeRequests().anyRequest()
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

    @Configuration
    @Order(3)
    @ConditionalOnProperty(property = "org.itech.login.oauth", havingValue = "true")
    public static class oauthSecurityConfiguration extends WebSecurityConfigurerAdapter {

        private static List<String> clients = Arrays.asList("keycloak");

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
            http.requestMatcher(new OAuthRequestedMatcher()).authorizeRequests().anyRequest()
                    // ensure they are authenticated
                    .authenticated().and()
                    // ensure they authenticate with http basic
                    .oauth2Login().clientRegistrationRepository(clientRegistrationRepository()).and()
                    // disable csrf as it is not needed for oauth
                    .csrf().disable()//
//                    .addFilterAt(SpringContext.getBean(BasicAuthFilter.class), BasicAuthenticationFilter.class)
                    // add security headers
                    .headers().frameOptions().sameOrigin().contentSecurityPolicy(CONTENT_SECURITY_POLICY);
        }

        @Bean
        public ClientRegistrationRepository clientRegistrationRepository() {
            List<ClientRegistration> registrations = clients.stream().map(c -> getRegistration(c))
                    .filter(registration -> registration != null).collect(Collectors.toList());

            return new InMemoryClientRegistrationRepository(registrations);
        }

        @Bean
        public OAuth2AuthorizedClientService authorizedClientService() {

            return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
        }

        private static String CLIENT_PROPERTY_KEY = "spring.security.oauth2.client.registration.";

        @Autowired
        private Environment env;

        private ClientRegistration getRegistration(String client) {
            String clientId = env.getProperty(CLIENT_PROPERTY_KEY + client + ".client-id");

            if (clientId == null) {
                return null;
            }

            String clientSecret = env.getProperty(CLIENT_PROPERTY_KEY + client + ".client-secret");

            if (client.equals("google")) {
                return CommonOAuth2Provider.GOOGLE.getBuilder(client).clientId(clientId).clientSecret(clientSecret)
                        .build();
            }
            if (client.equals("facebook")) {
                return CommonOAuth2Provider.FACEBOOK.getBuilder(client).clientId(clientId).clientSecret(clientSecret)
                        .build();
            }
            if (client.equals("okta")) {
                return CommonOAuth2Provider.OKTA.getBuilder(client).clientId(clientId).clientSecret(clientSecret)
                        .build();
            }
            if (client.equals("keycloak")) {
                return UncommonOAuth2Provider.KEYCLOAK.getBuilder(client).clientId(clientId).clientSecret(clientSecret)
                        .build();
            }
            return null;
        }

        public enum UncommonOAuth2Provider {
            KEYCLOAK {

                @Override
                public Builder getBuilder(String registrationId) {
                    ClientRegistration.Builder builder = getBuilder(registrationId, ClientAuthenticationMethod.POST,
                            DEFAULT_REDIRECT_URL);
                    builder.scope("openid", "profile");
                    builder.authorizationUri(
                            "http://host.openelis.org:8093/auth/realms/OE/protocol/openid-connect/auth");
                    builder.tokenUri("http://host.openelis.org:8093/auth/realms/OE/protocol/openid-connect/token");
                    builder.userInfoUri(
                            "http://host.openelis.org:8093/auth/realms/OE/protocol/openid-connect/userInfo");
//                    builder.userNameAttributeName(IdTokenClaimNames.SUB);
                    builder.clientName("Keycloak");
                    return builder;
                }
            };

            private static final String DEFAULT_REDIRECT_URL = "{baseUrl}/{action}/oauth2/code/{registrationId}";

            protected final ClientRegistration.Builder getBuilder(String registrationId,
                    ClientAuthenticationMethod method, String redirectUri) {
                ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId);
                builder.clientAuthenticationMethod(method);
                builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
                builder.redirectUriTemplate(redirectUri);
                return builder;
            }

            /**
             * Create a new
             * {@link org.springframework.security.oauth2.client.registration.ClientRegistration.Builder
             * ClientRegistration.Builder} pre-configured with provider defaults.
             *
             * @param registrationId the registration-id used with the new builder
             * @return a builder instance
             */
            public abstract ClientRegistration.Builder getBuilder(String registrationId);
        }
    }

    @Configuration
    @Order(4)
    @ConditionalOnProperty(property = "org.itech.login.saml", havingValue = "true")
    public static class samlSecurityConfiguration extends WebSecurityConfigurerAdapter {


        @Bean
        public RelyingPartyRegistrationRepository relyingPartyRegistrationRepository() {
            final String idpEntityId = "https://host.openelis.org:8094/auth/realms/OE";
            final String webSSOEndpoint = "https://host.openelis.org:8094/auth/realms/OE/protocol/saml";
            final String registrationId = "keycloak";
            final String localEntityIdTemplate = "{baseUrl}/saml2/service-provider-metadata" + "/{registrationId}";
            final String acsUrlTemplate = "{baseUrl}/login/saml2/sso/{registrationId}";
            Saml2X509Credential idpVerificationCertificate;
            try (InputStream pub = new ClassPathResource("credentials/idp.cer").getInputStream()) {
                X509Certificate c = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(pub);
                idpVerificationCertificate = new Saml2X509Credential(c, Saml2X509CredentialType.VERIFICATION);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            RelyingPartyRegistration relyingPartyRegistration = RelyingPartyRegistration
                    .withRegistrationId(registrationId).assertingPartyDetails(config -> config.entityId(idpEntityId))
                    .assertingPartyDetails(config -> config.singleSignOnServiceLocation(webSSOEndpoint))
                    .assertingPartyDetails(config -> config.wantAuthnRequestsSigned(false))
                    .assertingPartyDetails(
                            config -> config.verificationX509Credentials(c -> c.add(idpVerificationCertificate)))
                    .assertionConsumerServiceLocation(acsUrlTemplate).build();

            // SAML configuration
            // Mapping this application to one or more Identity Providers
            return new InMemoryRelyingPartyRegistrationRepository(relyingPartyRegistration);
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

            http.requestMatcher(new SamlRequestedMatcher()).authorizeRequests().anyRequest().authenticated().and()
                    .saml2Login()
                    .successHandler(customAuthenticationSuccessHandler())
                    .relyingPartyRegistrationRepository(relyingPartyRegistrationRepository()).and();
        }

        @Bean("samlAuthenticationSuccessHandler")
        public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
            return new CustomFormAuthenticationSuccessHandler();
        }
    }

    @Configuration
    @Order(5)
    @ConditionalOnProperty(property = "org.itech.login.certificate", havingValue = "true")
    public static class clientCertificateSecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            CharacterEncodingFilter filter = new CharacterEncodingFilter();
            filter.setEncoding("UTF-8");
            filter.setForceEncoding(true);
            http.addFilterBefore(filter, CsrfFilter.class);

            http.requestMatcher(new CertificateAuthRequestedMatcher()).authorizeRequests().anyRequest()
                    // ensure they are authenticated
                    .authenticated().and().x509().subjectPrincipalRegex("CN=(.*?)(?:,|$)")
                    .userDetailsService(SpringContext.getBean(UserDetailsService.class)).and()
                    // disable csrf as it is not needed for httpBasic
                    .csrf().disable();
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
                    .formLogin().loginPage("/LoginPage").permitAll().loginProcessingUrl("/ValidateLogin")
                    .usernameParameter("loginName").passwordParameter("password")
                    .failureHandler(customAuthenticationFailureHandler())
                    .successHandler(customAuthenticationSuccessHandler()).and()
                    // setup logout
                    .logout().logoutUrl("/Logout").logoutSuccessUrl("/LoginPage").invalidateHttpSession(true).and()
                    .sessionManagement().invalidSessionUrl("/LoginPage").sessionFixation().migrateSession().and().csrf()
                    .and()
                    // add security headers
                    .headers().frameOptions().sameOrigin().contentSecurityPolicy(CONTENT_SECURITY_POLICY);
        }

        @Bean
        public AuthenticationFailureHandler customAuthenticationFailureHandler() {
            return new CustomAuthenticationFailureHandler();
        }

        @Bean
        @Primary
        public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
            return new CustomFormAuthenticationSuccessHandler();
        }

        @Override
        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
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

//    @Bean
//    @ConditionalOnProperty(property = "org.itech.authProvider.useADLDAP", havingValue = "true")
//    public AuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
//        LdapAuthenticationProvider adProvider = new LdapAuthenticationProvider(new LdapAuthenticator()
//
//        return adProvider;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
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

    private static class OAuthRequestedMatcher implements RequestMatcher {
        @Override
        public boolean matches(HttpServletRequest request) {
            String auth = request.getHeader("Authorization");
            boolean haveOauth2Token = (auth != null) && auth.startsWith("Bearer");
            return haveOauth2Token;
        }
    }

    private static class SamlRequestedMatcher implements RequestMatcher {
        @Override
        public boolean matches(HttpServletRequest request) {
            String auth = request.getHeader("Authorization");
            boolean useSAML = (auth != null) && auth.startsWith("SAML")
                    || "true".equals(request.getParameter("useSAML")) || request.getRequestURI().contains("saml2");
            return useSAML ;
        }
    }

    private static class BasicAuthRequestedMatcher implements RequestMatcher {
        @Override
        public boolean matches(HttpServletRequest request) {
            String auth = request.getHeader("Authorization");
            boolean haveBasicAuth = (auth != null) && auth.startsWith("Basic");
            return haveBasicAuth;
        }
    }

    private static class CertificateAuthRequestedMatcher implements RequestMatcher {
        @Override
        public boolean matches(HttpServletRequest request) {
            String auth = request.getHeader("Authorization");
            boolean haveCertificateAuth = (auth != null) && auth.startsWith("Cert");
            return haveCertificateAuth;
        }
    }
}
