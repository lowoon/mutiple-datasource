package com.example.multipledatasource.domain.customer.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "customerEntityManagerFactory",
    transactionManagerRef = "customerTransactionManager",
    basePackages = {"com.example.multipledatasource.domain.customer"}
)
public class CustomerDataSourceConfig {
    @Autowired
    @Qualifier("customerDataSource")
    private DataSource customerDataSource;

    @Bean(name = "customerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean customerEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
            .dataSource(customerDataSource)
            .packages("com.example.multipledatasource.domain.customer")
            .persistenceUnit("customer")
            .build();
    }

    @Bean("customerTransactionManager")
    public PlatformTransactionManager customerTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(customerEntityManagerFactory(builder).getObject());
    }
}
