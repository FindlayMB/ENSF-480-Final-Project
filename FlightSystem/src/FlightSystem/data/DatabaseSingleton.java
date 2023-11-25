package FlightSystem.data;

import java.sql.*;
import java.util.*;

import FlightSystem.objects.*;

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
            System.out.println("Database connection made!");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to create database connection!");
        }
    }

    public static synchronized DatabaseSingleton getInstance() {
        if (dbConnect == null)
            dbConnect = new DatabaseSingleton();
        return dbConnect;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = dbConnection.createStatement();
        return statement.executeQuery(query);
    }

    public ResultSet getTable(String tableName) throws SQLException {
        String query = String.format("SELECT * FROM %s", tableName);
        return executeQuery(query);
    }

    public HashMap<Integer, User> getUserTable() throws SQLException {
        HashMap<Integer, User> users = new HashMap<Integer, User>();
        ResultSet table = getTable("Users");

        while (table.next()) {
            users.put(table.getInt(1),
                    new User(
                            table.getInt(1),
                            table.getString(2),
                            table.getString(3),
                            table.getString(4),
                            table.getString(5),
                            table.getString(6),
                            table.getDate(7).toLocalDate(),
                            table.getString(8),
                            table.getString(9)));
        }

        return users;
    }

    public void getCrewFlights(Crew crew, int ID) throws SQLException {
        String query = String.format("""
                SELECT f.FlightID,c.Job FROM flights as f
                JOIN
                (SELECT CrewID,Job FROM crews WHERE CrewMemberID = %d) as c
                ON f.CrewID = c.CrewID;
                """, ID);
        ResultSet table = executeQuery(query);
        while (table.next()) {
            crew.addCrewFlightID(table.getInt(1));
        }
        crew.setJob(table.getString(2));
    }

    public HashMap<String, Airport> getAirportTable() throws SQLException {
        HashMap<String, Airport> airports = new HashMap<String, Airport>();
        ResultSet table = getTable("airports");

        while (table.next()) {
            airports.put(table.getString(1),
                    new Airport(
                            table.getString(1),
                            table.getString(2),
                            table.getString(3),
                            table.getString(4)));
        }
        return airports;
    }

    public HashMap<Integer, Plane> getPlaneTable() throws SQLException {
        HashMap<Integer, Plane> planes = new HashMap<Integer, Plane>();
        ResultSet table = getTable("planes");

        while (table.next()) {
            planes.put(table.getInt(1),
                    new Plane(
                            table.getInt(1),
                            table.getString(2),
                            table.getInt(3),
                            table.getInt(4),
                            table.getInt(5)));
        }
        return planes;
    }

}
