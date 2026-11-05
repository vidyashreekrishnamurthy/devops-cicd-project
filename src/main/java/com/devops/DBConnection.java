
package com.devops;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC driver not found.", e);
        }

        String url = System.getenv().getOrDefault(
            "DB_URL",
            "jdbc:postgresql://localhost:5432/devopsdb"
        );

        String user = System.getenv().getOrDefault(
            "DB_USER",
            "devopsuser"
        );

        String password = System.getenv("DB_PASSWORD");

        if (password == null || password.isBlank()) {
            throw new SQLException("DB_PASSWORD environment variable is not set.");
        }

        return DriverManager.getConnection(url, user, password);
    }
}
