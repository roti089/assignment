package com.pims.database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/pims";

    private static final String USER =
            "root";

    private static final String PASSWORD =
            "PIMS@089";

    public static java.sql.Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }

    public static boolean testConnection() {

        try (java.sql.Connection connection =
                     getConnection()) {

            return connection != null
                    && !connection.isClosed();

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
}