package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/Grocery_store_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "5650";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to database!");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Database connection failed", e);
        }
    }
    public static void close(Connection connection) {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
