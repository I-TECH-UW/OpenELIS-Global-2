package org.openelisglobal.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//	@ComponentScans(value = { @ComponentScan("com.howtodoinjava.demo.spring")})
public class HibernateConfig {

    @Autowired
    private ApplicationContext context;

    static JpaTransactionManager transactionManager;
    static LocalContainerEntityManagerFactoryBean emf;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        if (emf == null) {
            emf = new LocalContainerEntityManagerFactoryBean();
//            emf.setDataSource(dataSource);
            emf.setPersistenceXmlLocation("classpath:persistence/persistence.xml");
        }

        return emf;
    }

    @Bean
    @Primary
    public PlatformTransactionManager getTransactionManager(EntityManagerFactory entityManagerFactory) {
        if (transactionManager == null) {
            transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(entityManagerFactory);
        }
        return transactionManager;
    }

//    @Bean(destroyMethod = "close")
//    public DataSource dataSource() {
//        JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
//        dsLookup.setResourceRef(true);
//        DataSource dataSource = dsLookup.getDataSource("jdbc/LimsDS");
//        return dataSource;
//    }
}
