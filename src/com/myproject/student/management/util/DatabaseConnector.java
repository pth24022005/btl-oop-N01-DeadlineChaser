package com.myproject.student.management.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing database connections.
 * This class centralizes the connection logic to the student_management database.
 */
public class DatabaseConnector {

    // --- Database Connection Parameters ---
    // The URL points to the MySQL server and the specific database.
    private static final String DB_URL = "jdbc:mysql://localhost:3306/student_management";
    
    // Default username for local MySQL server. Change if yours is different.
    private static final String USER = "root";
    
    // Default password for local MySQL server. Change if you have set one.
    private static final String PASS = "";

    /**
     * Establishes and returns a connection to the database.
     * * @return a Connection object to the database, or null if an error occurs.
     */
    public static Connection getConnection() {
        try {
            // Attempt to establish the connection using the defined parameters.
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            return connection;
        } catch (SQLException e) {
            // If a database access error occurs, print the stack trace for debugging.
            // In a real application, this should be handled by a logging framework.
            e.printStackTrace();
            return null; // Return null to indicate connection failure.
        }
    }
}