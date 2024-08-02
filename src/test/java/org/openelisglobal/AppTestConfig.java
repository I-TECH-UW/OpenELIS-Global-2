package org.openelisglobal;

import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import org.openelisglobal.audittrail.dao.AuditTrailService;
import org.openelisglobal.common.util.Versioning;
import org.openelisglobal.dataexchange.fhir.service.FhirPersistanceService;
import org.openelisglobal.externalconnections.service.BasicAuthenticationDataService;
import org.openelisglobal.externalconnections.service.ExternalConnectionService;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.localization.dao.LocalizationDAO;
import org.openelisglobal.localization.service.LocalizationServiceImpl;
import org.openelisglobal.siteinformation.service.SiteInformationService;
import org.openelisglobal.test.dao.TestDAO;
import org.openelisglobal.test.service.TestServiceImpl;
import org.openelisglobal.testresult.service.TestResultService;
import org.openelisglobal.typeofsample.service.TypeOfSampleService;
import org.openelisglobal.typeofsample.service.TypeOfSampleTestService;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = { "org.openelisglobal.spring", "org.openelisglobal.patient",
        "org.openelisglobal.patientidentity", "org.openelisglobal.gender", "org.openelisglobal.patientidentitytype",
        "org.openelisglobal.patienttype", "org.openelisglobal.address", "org.openelisglobal.dictionary",
        "org.openelisglobal.person", "org.openelisglobal.dictionary.controller.rest",
        "org.openelisglobal.dictionary.service", "org.openelisglobal.dictionarycategory.service",
        "org.openelisglobal.dictionary.daoimpl", "org.openelisglobal.dictionarycategory.daoimpl",
        "org.openelisglobal.audittrail.daoimpl", "org.openelisglobal.referencetables.service",
        "org.openelisglobal.referencetables.daoimpl", "org.openelisglobal.history.service",
        "org.openelisglobal.menu.service", "org.openelisglobal.menu.daoimpl", "org.openelisglobal.login.daoimpl",
        "org.openelisglobal.systemusermodule.service", "org.openelisglobal.rolemodule.service",
        "org.openelisglobal.systemusermodule.daoimpl", "org.openelisglobal.systemusermodule.service",
        "org.openelisglobal.login.service", "org.openelisglobal.view", "org.openelisglobal.search.service",
        "org.openelisglobal.sample.daoimpl","org.openelisglobal.menu.MenuServiceTest", "org.openelisglobal.menu" }, excludeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "org.openelisglobal.patient.controller.*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "org.openelisglobal.dictionary.controller.*.java"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "org.openelisglobal.config.*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "org.openelisglobal.fhir.*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "org.openelisglobal.*.fhir.*") })
@EnableWebMvc
public class AppTestConfig implements WebMvcConfigurer {

    // mock Beans
    @Bean()
    @Profile("test")
    public FhirPersistanceService fhirPesistence() {
        return mock(FhirPersistanceService.class);
    }

    @Bean()
    @Profile("test")
    public LocalizationServiceImpl localise() {
        return mock(LocalizationServiceImpl.class);
    }

    @Bean()
    @Profile("test")
    public LocalizationDAO localiseDao() {
        return mock(LocalizationDAO.class);
    }

    @Bean()
    @Profile("test")
    public ExternalConnectionService externalConnectService() {
        return mock(ExternalConnectionService.class);
    }

    @Bean()
    @Profile("test")
    public BasicAuthenticationDataService basicAuthDataService() {
        return mock(BasicAuthenticationDataService.class);
    }

    @Bean()
    @Profile("test")
    public TestServiceImpl testServiceImpl() {
        return mock(TestServiceImpl.class);
    }

    @Bean()
    @Profile("test")
    public AuditTrailService auditTrailService() {
        return mock(AuditTrailService.class);
    }

    @Bean()
    @Profile("test")
    public TestDAO testDao() {
        return mock(TestDAO.class);
    }

    @Bean()
    @Profile("test")
    public TestResultService testResultService() {
        return mock(TestResultService.class);
    }

    @Bean()
    @Profile("test")
    public TypeOfSampleTestService typeOfSampleTestService() {
        return mock(TypeOfSampleTestService.class);
    }

    @Bean()
    @Profile("test")
    public TypeOfSampleService typeOfSample() {
        return mock(TypeOfSampleService.class);
    }

    @Bean()
    @Profile("test")
    public Versioning versioning() {
        return mock(Versioning.class);
    }

    @Bean()
    @Profile("test")
    public SiteInformationService siteInformationService() {
        return mock(SiteInformationService.class);
    }

    @Bean
    @Profile("test")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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

    @Bean
    public MappingJackson2HttpMessageConverter jsonConverter() {
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);

        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter(builder.build());
        jsonConverter.setSupportedMediaTypes(supportedMediaTypes);
        return jsonConverter;
    }

    @Override
    public void configureMessageConverters(@NonNull List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);
        converters.add(new StringHttpMessageConverter());
        converters.add(jsonConverter());
    }
}
