package data;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 *
 * @author Findlay Brown
 *         DataImport.java
 */
public class DataImport {

    private static Connection dbConnection = null;

    /**
     * Tries to create a connection to FlightSystem database on localhost
     * saves the connection to dbConnection if successful
     * 
     * @param username -> username for database
     * @param password -> password for database
     * @return returns true if database connection was succesful
     *         otherwise returns false
     */
    private boolean createConnection(String username, String password) {
        try {
            dbConnection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/flightsystem",
                    username,
                    password);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static synchronized DataImport getConnection() {
        if (dbConnection == null) {
            if (createConnection("admin", "admin")) {
                System.out.println("Database connection successfully made!");
            } else {
                System.out.println("Failed to create connection to database!");
            }
        }
        return dbConnection;
    }

}
