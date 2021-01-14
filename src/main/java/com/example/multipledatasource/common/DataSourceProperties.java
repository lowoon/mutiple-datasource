package com.example.multipledatasource.common;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableConfigurationProperties
public class DataSourceProperties {
    @Bean(name = "customerDataSource")
    @Qualifier("customerDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.customer")
    public DataSource customerDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "userDataSource")
    @Qualifier("userDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.user")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
