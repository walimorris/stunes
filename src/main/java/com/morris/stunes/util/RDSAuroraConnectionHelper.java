package com.morris.stunes.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RDSAuroraConnectionHelper {
    private final String url;
    private final String username;
    private final String password;
    private static RDSAuroraConnectionHelper instance;

    private RDSAuroraConnectionHelper() {
        username = AuroraSecretsManagerHelper.getAuroraWriterUserName();
        password = AuroraSecretsManagerHelper.getAuroraWriterPassword();
        url = AuroraSecretsManagerHelper.getAuroraWriterUrl();
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
}
