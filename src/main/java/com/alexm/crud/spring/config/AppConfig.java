package com.alexm.crud.spring.config;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import static org.hibernate.cfg.Environment.*;

import com.alexm.crud.spring.service.HibernateSearchService;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("com.alexm.crud.spring.dao"),
      @ComponentScan("com.alexm.crud.spring.service") })
public class AppConfig {

   @Autowired
   private Environment env;

   @Autowired
   @Qualifier(value = "entityManager")
   private EntityManager bentityManager;

   @Bean
   public LocalSessionFactoryBean getSessionFactory() {
      LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

      Properties props = new Properties();
      // Setting JDBC properties
      props.put(DRIVER, env.getProperty("pgsql.driver"));
      props.put(URL, env.getProperty("pgsql.url"));
      props.put(USER, env.getProperty("pgsql.user"));
      props.put(PASS, env.getProperty("pgsql.password"));

      // Setting Hibernate properties
      props.put(DIALECT, env.getProperty("pgsql.dialect"));
      props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
      props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));

      // Setting C3P0 properties
      props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
      props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
      props.put(C3P0_ACQUIRE_INCREMENT, 
            env.getProperty("hibernate.c3p0.acquire_increment"));
      props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
      props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));

      factoryBean.setHibernateProperties(props);
      factoryBean.setPackagesToScan("com.alexm.crud.spring.model");

      return factoryBean;
   }

   @Bean
   public HibernateTransactionManager getTransactionManager() {
      HibernateTransactionManager transactionManager = new HibernateTransactionManager();
      transactionManager.setSessionFactory(getSessionFactory().getObject());
      return transactionManager;
   }

   @Bean
   @Qualifier(value = "entityManager")
   public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
      return entityManagerFactory.createEntityManager();
   }

   @Bean
   public HibernateSearchService hibernateSearchService() {
      HibernateSearchService hibernateSearchService = new HibernateSearchService(bentityManager);
      hibernateSearchService.initializeHibernateSearch();
      return hibernateSearchService;
   }
}
