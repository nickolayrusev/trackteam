package com.nrusev.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nikolayrusev on 10/6/16.
 */
@Configuration
@EnableJpaRepositories(transactionManagerRef = "testTransactionManager", basePackages = "com.nrusev.repository.migration",
        entityManagerFactoryRef = "testEntityManagerFactory")
public class SecondaryDatabaseConfig {

    @Bean
    DataSource secondaryDataSource() {
        return DataSourceBuilder.create().driverClassName("org.sqlite.JDBC").url("jdbc:sqlite:C:\\Users\\Nikolay Rusev\\Desktop\\all.db").build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean testEntityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(secondaryDataSource());
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan("com.nrusev.domain.migration");
        factoryBean.setJpaPropertyMap(jpaProperties());
        factoryBean.setPersistenceUnitName("second");
        return factoryBean;
    }

    @Bean
    PlatformTransactionManager testTransactionManager() {
        return new JpaTransactionManager(testEntityManagerFactory().getObject());
    }

    private Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.dialect","com.nrusev.config.SQLiteDialect");
        return props;
    }
}
