package com.innovativecore.patientappointment.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
@Profile("!test")
public class DatabaseConfig {
    @Bean
    @Primary
    public DataSource dataSource() {
        // First create database if not exists
        createDatabaseIfNotExists();

        // Then return the actual datasource
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/pams_db");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }

    private void createDatabaseIfNotExists() {
        try {
            String url = "jdbc:mysql://localhost:3306/";
            try (Connection conn = DriverManager.getConnection(url, "root", "");
                 Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS pams_db");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database", e);
        }
    }
}
