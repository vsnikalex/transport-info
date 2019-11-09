package com.tsystems.transportinfo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.flyway.core.Flyway;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:/application-${spring.profiles.active}.properties")
@EnableTransactionManagement
@ComponentScan({"com.tsystems.transportinfo.data",
        "com.tsystems.transportinfo.service"})
public class HibernateConfig {

    @Autowired
    private Environment env;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setLocations("classpath:/db/migration");
        flyway.setDataSource(dataSource());
        return flyway;
    }

    @Bean @DependsOn("flyway")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(
                "com.tsystems.transportinfo.data.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(
                env.getProperty("database.driver"));
        dataSource.setUrl(
                env.getProperty("database.url"));
        dataSource.setUsername(
                env.getProperty("database.user"));
        dataSource.setPassword(
                env.getProperty("database.pwd"));
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.ddl-auto",
                env.getProperty("hibernate.ddl-auto"));
        hibernateProperties.setProperty("hibernate.dialect",
                env.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql",
                env.getProperty("hibernate.show_sql"));
        hibernateProperties.setProperty("hibernate.format_sql",
                env.getProperty("hibernate.format_sql"));
        hibernateProperties.setProperty("hibernate.enable_lazy_load_no_trans",
                env.getProperty("hibernate.enable_lazy_load_no_trans"));
        return hibernateProperties;
    }

}
