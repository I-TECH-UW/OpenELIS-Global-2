package org.openelisglobal.patient;

import static org.mockito.Mockito.mock;

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
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan(basePackages = { "org.openelisglobal.spring", "org.openelisglobal.patient",
        "org.openelisglobal.patientidentity", "org.openelisglobal.gender", "org.openelisglobal.patientidentitytype",
        "org.openelisglobal.patienttype", "org.openelisglobal.address", "org.openelisglobal.dictionary",
        "org.openelisglobal.person" }, excludeFilters = {
                @Filter(type = FilterType.REGEX, pattern = "org.openelisglobal.*.controller.*"),
                @Filter(type = FilterType.REGEX, pattern = "org.openelisglobal.config.*"),
                @Filter(type = FilterType.REGEX, pattern = "org.openelisglobal.fhir.*"),
                @Filter(type = FilterType.REGEX, pattern = "org.openelisglobal.*.fhir.*") })
public class PatientTestConfig {

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
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/languages/message");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        MessageUtil.setMessageSource(messageSource);
        return messageSource;
    }
}
