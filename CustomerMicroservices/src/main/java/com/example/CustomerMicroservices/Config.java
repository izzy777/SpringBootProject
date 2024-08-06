package com.example.CustomerMicroservices;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class Config {

    //configuring the database
    //getting driver, url, username, and password
    @Bean
    protected DataSource getDataSource() {
        DataSourceBuilder<?> db = DataSourceBuilder.create()
        .driverClassName("org.hsqldb.jdbc.JDBCDriver")
        .url("jdbc:hsqldb:mem:testdb;DB_CLOSE_DELAY=-1")
        .username("sa")
        .password("");
        return db.build();
    }
}
