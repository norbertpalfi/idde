package edu.bbte.quiz.backend.config;

import java.io.Serializable;

public class Config implements Serializable {
    private String daoType = "mem";
    private String driverClassName = "";
    private String jdbcUrl = "";
    private String username = "";
    private String password = "";
    private Boolean logQueries = false;
    private Boolean logUpdates = true;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDaoType() {
        return daoType;
    }

    public void setDaoType(String daoType) {
        this.daoType = daoType;
    }

    public Boolean getLogQueries() {
        return logQueries;
    }

    public void setLogQueries(Boolean logQueries) {
        this.logQueries = logQueries;
    }

    public Boolean getLogUpdates() {
        return logUpdates;
    }

    public void setLogUpdates(Boolean logUpdates) {
        this.logUpdates = logUpdates;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Config{");
        sb.append("daoType='").append(daoType).append('\'');
        sb.append(", driverClassName='").append(driverClassName).append('\'');
        sb.append(", jdbcUrl='").append(jdbcUrl).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", logQueries=").append(logQueries);
        sb.append(", logUpdates=").append(logUpdates);
        sb.append('}');
        return sb.toString();
    }
}
