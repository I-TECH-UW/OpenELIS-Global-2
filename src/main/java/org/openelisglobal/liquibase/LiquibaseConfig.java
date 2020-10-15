package org.openelisglobal.liquibase;

import java.util.Optional;

import javax.sql.DataSource;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class LiquibaseConfig {

    @Value("${org.openelisglobal.liquibase.contexts}")
    private Optional<String> contexts;

    @Autowired
    private DataSource dataSource;

    @Bean("liquibase")
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase/base-changelog.xml");
        liquibase.setDataSource(dataSource);
        if (contexts.isEmpty() || GenericValidator.isBlankOrNull(contexts.get().trim())) {
            liquibase.setContexts("general");
        } else {
            liquibase.setContexts(contexts.get());
        }
        return liquibase;

    }

}
