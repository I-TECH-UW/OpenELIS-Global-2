package org.openelisglobal.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.interceptor.CommonPageAttributesInterceptor;
import org.openelisglobal.interceptor.UrlErrorsInterceptor;
import org.openelisglobal.internationalization.GlobalLocaleResolver;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@EnableWebMvc
@Configuration
@EnableJpaRepositories(basePackages = { "org.itech", "org.ozeki.sms" })
@PropertySource("classpath:application.properties")
@PropertySource("file:/run/secrets/common.properties")
@PropertySource(value = "file:/run/secrets/extra.properties", ignoreResourceNotFound = true)
@ComponentScan(basePackages = { "spring", "org.openelisglobal", "org.itech", "org.ozeki.sms", "oe.plugin" })
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    @Qualifier(value = "ModuleAuthenticationInterceptor")
    HandlerInterceptor moduleAuthenticationInterceptor;
    @Autowired
    UrlErrorsInterceptor urlLocatedErrorsInterceptor;
    @Autowired
    CommonPageAttributesInterceptor pageAttributesInterceptor;
    @Autowired
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Bean
    public ViewResolver viewResolver() {
        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
        viewResolver.setViewClass(TilesView.class);
        viewResolver.setContentType("text/html; charset=UTF-8");
        return viewResolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfig = new TilesConfigurer();
        String[] tilesFiles = new String[] { "classpath:/tiles/tiles-defs.xml",
                "classpath:/tiles/tiles-globalOpenELIS.xml" };
        tilesConfig.setDefinitions(tilesFiles);
        return tilesConfig;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/languages/message");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        MessageUtil.setMessageSource(messageSource);
        return messageSource;
    }

    @Bean("localeResolver")
    public LocaleResolver localeResolver() {
        GlobalLocaleResolver localeResolver = new GlobalLocaleResolver();
        String localeName = ConfigurationProperties.getInstance().getPropertyValue(Property.DEFAULT_LANG_LOCALE);
        localeResolver.setDefaultLocale(Locale.forLanguageTag(localeName));
        LocaleContextHolder.setDefaultLocale(Locale.forLanguageTag(localeName));
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Bean(name = "filterMultipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("utf-8");
        multipartResolver.setMaxUploadSize(20848820);
        multipartResolver.setResolveLazily(false);
        return multipartResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(moduleAuthenticationInterceptor).addPathPatterns("/**")
                .excludePathPatterns(SecurityConfig.OPEN_PAGES)//
                .excludePathPatterns(SecurityConfig.LOGIN_PAGES)//
                .excludePathPatterns(SecurityConfig.RESOURCE_PAGES)//
                .excludePathPatterns(SecurityConfig.AUTH_OPEN_PAGES);
//                .excludePathPatterns(SecurityConfig.CLIENT_CERTIFICATE_PAGES);
        registry.addInterceptor(urlLocatedErrorsInterceptor).addPathPatterns("/**");
        registry.addInterceptor(pageAttributesInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Register resource handlers for css, js, etc.
        registry.addResourceHandler("scripts/**").addResourceLocations("classpath:static/scripts/");
        registry.addResourceHandler("css/**").addResourceLocations("classpath:static/css/");
        registry.addResourceHandler("images/**").addResourceLocations("/static/images/");
        registry.addResourceHandler("favicon/**").addResourceLocations("/static/favicon/");
        registry.addResourceHandler("fontawesome-free-5.13.1-web/**")
                .addResourceLocations("/static/fontawesome-free-5.13.1-web/");
        registry.addResourceHandler("documentation/**").addResourceLocations("classpath:static/documentation/");
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    // prototype scope so we don't have to restart when connection info is edited
    public JavaMailSender getJavaMailSender() {
        ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        String address = configurationProperties.getPropertyValue(Property.PATIENT_RESULTS_SMTP_ADDRESS);
        if (!GenericValidator.isBlankOrNull(address)) {
            try {
                URI uri = new URI(address);
                mailSender.setHost(uri.getHost());
                mailSender.setPort(uri.getPort());
            } catch (URISyntaxException e) {
                LogEvent.logError(e);
            }
        }

        String username = configurationProperties.getPropertyValue(Property.PATIENT_RESULTS_SMTP_USERNAME);
        String password = configurationProperties.getPropertyValue(Property.PATIENT_RESULTS_SMTP_PASSWORD);
        if (!(GenericValidator.isBlankOrNull(username) || GenericValidator.isBlankOrNull(password))) {
            mailSender.setUsername(username);
            mailSender.setPassword(password);
            props.put("mail.smtp.auth", "true");
        } else {
            props.put("mail.smtp.auth", "false");
        }

        return mailSender;
    }
}
