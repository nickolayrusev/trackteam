package com.nrusev.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nikolay Rusev on 5.10.2016 г..
 */
@Configuration
@EnableJpaRepositories(transactionManagerRef = "primaryTransactionManager",
        entityManagerFactoryRef = "primaryEntityManagerFactory", basePackages = "com.nrusev.repository")
public class PrimaryDatabaseConfig {

    @Bean
    @ConfigurationProperties(prefix="datasource.primary")
    @Primary
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(primaryDataSource());
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan("com.nrusev.domain");
        factoryBean.setPersistenceUnitName("default");

        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager primaryTransactionManager() {
        return new JpaTransactionManager(primaryEntityManagerFactory().getObject());
    }

}
