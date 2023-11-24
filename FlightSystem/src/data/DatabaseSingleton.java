package data;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Database connection singleton
 * Create a singleton that stores the connection to the database.
 * Also houses functions that are used to query the database.
 * 
 * @author Findlay Brown
 */
public class DatabaseSingleton {
    private static DatabaseSingleton dbConnect = null;
    private Connection dbConnection = null;

    /**
     * Tries to create a connection to FlightSystem database on localhost
     * saves the connection to dbConnection if successful
     */
    private DatabaseSingleton() {
        try {
            this.dbConnection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/flightsystem",
                    "admin",
                    "admin");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static synchronized DatabaseSingleton getInstance() {
        if (dbConnect == null)
            dbConnect = new DatabaseSingleton();
        return dbConnect;
    }

}
