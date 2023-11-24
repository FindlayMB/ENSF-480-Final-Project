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

    private Connection dbConnection;

    /**
     * Tries to create a connection to FlightSystem database on localhost
     * saves the connection to dbConnection if successful
     * 
     * @param username -> username for database
     * @param password -> password for database
     * @return returns true if database connection was succesful
     *         otherwise returns false
     */
    public boolean createConnection(String username, String password) {
        try {
            this.dbConnection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/flightsystem",
                    username,
                    password);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
