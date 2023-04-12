package com.morris.stunes.util;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RDSAuroraConnectionHelper {
    private final String url;
    private final String username;
    private final String password;

    private static RDSAuroraConnectionHelper instance;

    public RDSAuroraConnectionHelper() {
        AuroraSecretsManagerHelper auroraSecretsManagerHelper = new AuroraSecretsManagerHelper();
        username = auroraSecretsManagerHelper.getAuroraWriterUserName();
        password = auroraSecretsManagerHelper.getAuroraWriterPassword();
        url = auroraSecretsManagerHelper.getAuroraWriterUrl();
    }

    public static Connection getConnection() throws SQLException {
        if (instance == null) {
            instance = new RDSAuroraConnectionHelper();
        }

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            return DriverManager.getConnection(instance.url, instance.username, instance.password);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.getStackTrace();
        }
        return null;
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DataSource RDSAuroraDatasource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        driverManagerDataSource.setUrl(this.url);
        driverManagerDataSource.setUsername(this.username);
        driverManagerDataSource.setPassword(this.password);
        return driverManagerDataSource;
    }
}
