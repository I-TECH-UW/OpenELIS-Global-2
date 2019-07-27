package org.openelisglobal.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//	@ComponentScans(value = { @ComponentScan("com.howtodoinjava.demo.spring")})
public class HibernateConfig {

	@Autowired
	private ApplicationContext context;

	static HibernateTransactionManager transactionManager;
	static LocalSessionFactoryBean factoryBean;

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		if (factoryBean == null) {
			factoryBean = new LocalSessionFactoryBean();
			factoryBean.setConfigLocation(context.getResource("classpath:hibernate/hibernate.cfg.xml"));
		}
		return factoryBean;
	}

	@Bean
	@Primary
	public PlatformTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		if (transactionManager == null) {
			transactionManager = new HibernateTransactionManager();
			transactionManager.setSessionFactory(sessionFactory);
		}
		return transactionManager;
	}

}
