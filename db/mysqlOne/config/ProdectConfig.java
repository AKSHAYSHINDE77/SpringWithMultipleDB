package com.ak.db.mysqlOne.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryBean",
        basePackages = {"com.ak.db.mysqlOne.repo"},
        transactionManagerRef = "platformTransactionManager"
)
public class ProdectConfig {

    @Autowired
    private Environment environment;

    //datasource
    @Bean
    @Primary
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));

        return dataSource;
    }


    //entity manager factory

    @Bean("entityManagerFactoryBean")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){

        LocalContainerEntityManagerFactoryBean bean= new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource());

        JpaVendorAdapter adapter= new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(adapter);

        Map<String, String> props= new HashMap<>();
        props.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        props.put("hibernate.show_sql","true");
        props.put("hibernate.hbm2ddl.auto","update");

        bean.setJpaPropertyMap(props);
        bean.setPackagesToScan("com.ak.db.mysqlOne.entities");

        return bean;
    }

    //platform transaction manager
    @Primary
    @Bean("platformTransactionManager")
    public PlatformTransactionManager platformTransactionManager(){
        JpaTransactionManager manager= new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return manager;
    }
}
