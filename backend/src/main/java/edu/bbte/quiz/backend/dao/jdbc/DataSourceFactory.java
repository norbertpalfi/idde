package edu.bbte.quiz.backend.dao.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import edu.bbte.quiz.backend.config.Config;
import edu.bbte.quiz.backend.config.ConfigurationFactory;

public class DataSourceFactory {
    private static HikariDataSource hikariDataSource;
    private boolean logQueries;
    private boolean logUpdates;

    public static synchronized Boolean getLogQueries() {
        Config config = ConfigurationFactory.getMainConfiguration();
        return config.getLogQueries();
    }
    public static synchronized Boolean getLogUpdates() {
        Config config = ConfigurationFactory.getMainConfiguration();
        return config.getLogUpdates();
    }

    public static synchronized HikariDataSource getHikariDataSource() {
        Config config = ConfigurationFactory.getMainConfiguration();
        if (hikariDataSource == null) {
            hikariDataSource = new HikariDataSource();
            hikariDataSource.setDriverClassName(config.getDriverClassName());
            hikariDataSource.setJdbcUrl(config.getJdbcUrl());
            hikariDataSource.setUsername(config.getUsername());
            hikariDataSource.setPassword(config.getPassword());
        }
        return hikariDataSource;
    }
}
