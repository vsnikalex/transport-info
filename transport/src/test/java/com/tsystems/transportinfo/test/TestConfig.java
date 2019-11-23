package com.tsystems.transportinfo.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Properties;

@Configuration
@PropertySource("classpath:/application-test.properties")
//@EnableTransactionManagement
@ComponentScan({"com.tsystems.transportinfo.data",
        "com.tsystems.transportinfo.service"})
public class TestConfig {

    @Autowired
    private Environment env;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(
                "com.tsystems.transportinfo.data.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

//    @Bean
//    public DataSource dataSource() {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(
//                env.getProperty("database.driver"));
//        dataSource.setUrl(
//                env.getProperty("database.url"));
//        dataSource.setUsername(
//                env.getProperty("database.user"));
//        dataSource.setPassword(
//                env.getProperty("database.pwd"));
//        return dataSource;
//    }

//    @Bean
//    public PlatformTransactionManager hibernateTransactionManager() {
//        HibernateTransactionManager transactionManager
//                = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory().getObject());
//        return transactionManager;
//    }

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
