package FlightSystem.data;

import java.sql.*;
import java.util.*;

import FlightSystem.objects.airport.*;
import FlightSystem.objects.flight.*;
import FlightSystem.objects.plane.*;
import FlightSystem.objects.seats.*;
import FlightSystem.objects.user.*;

/**
 * Database connection singleton
 * Create a singleton that stores the connection to the database.
 * Also houses functions that are used to query the database.
 * 
 * @author Findlay Brown
 */
public class DatabaseSingleton {
    private static DatabaseSingleton dbInstance = null;
    private Connection dbConnection = null;
    // private AirportsSingleton airports = AirportsSingleton.getInstance();

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
        if (dbInstance == null)
            dbInstance = new DatabaseSingleton();
        return dbInstance;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = dbConnection.createStatement();
        return statement.executeQuery(query);
    }

    public boolean execute(String query) throws SQLException {
        Statement statement = dbConnection.createStatement();
        return statement.execute(query);
    }

    public ResultSet getTable(String tableName) throws SQLException {
        String query = String.format("SELECT * FROM %s", tableName);
        return executeQuery(query);
    }

    public ResultSet getTableWhere(String tableName, String columnName, String value) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE %s = %s", tableName, columnName, value);
        return executeQuery(query);
    }

    public ResultSet getTableWhere(String tableName, String columnName, int value) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE %s = %d", tableName, columnName, value);
        return executeQuery(query);
    }

    /**
     * Gets all users in database
     * 
     * @return a hashmap that stores all the User objects with the key equal
     *         to the UserID
     * @throws SQLException
     */
    public HashMap<Integer, User> getUserTable() throws SQLException {
        HashMap<Integer, ArrayList<Integer>> flights = getUserFlights();
        String query = """
                SELECT u.*,r.Username,r.Password,r.SignUpDate,r.CreditCardNumber,r.CVV,r.ExpiryDate FROM users as u
                LEFT JOIN
                (SELECT UserID,Username,Password,SignUpDate,CreditCardNumber,CVV,ExpiryDate FROM registered) as r
                ON u.UserID = r.UserID;""";
        ResultSet table = executeQuery(query);
        HashMap<Integer, User> users = new HashMap<Integer, User>();

        while (table.next()) {
            User user = new User(
                    table.getInt(1), // UserID
                    table.getString(2), //
                    table.getString(3),
                    table.getString(4),
                    table.getDate(5).toLocalDate(),
                    table.getString(6));
            if (table.getString(6).equals("guest") == false) {
                user = new RegisteredUser(user,
                        table.getString(7),
                        table.getString(8),
                        table.getDate(9).toLocalDate(),
                        table.getString(6),
                        new CreditCard(table.getString(10),
                                table.getInt(11),
                                table.getDate(12) != null ? table.getDate(12).toLocalDate() : null),
                        flights.get(table.getInt(1)));
            }
            users.put(table.getInt(1), user);
        }
        return users;
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

    public HashMap<Integer, Flight> getFlightsTable() throws SQLException {
        AirportsSingleton airports = AirportsSingleton.getInstance();
        HashMap<Integer, Flight> flights = new HashMap<Integer, Flight>();
        ResultSet table = getTable("flights");
        while (table.next()) {
            flights.put(table.getInt(1),
                    new Flight(
                            table.getInt(1),
                            airports.getAirport(table.getString(2)),
                            table.getTime(3).toLocalTime(),
                            table.getDate(4).toLocalDate(),
                            airports.getAirport(table.getString(5)),
                            table.getTime(6).toLocalTime(),
                            table.getDate(7).toLocalDate(),
                            table.getInt(8),
                            table.getInt(9),
                            table.getFloat(10)));
        }
        return flights;
    }

    public HashMap<Integer, ArrayList<Integer>> getUserFlights() throws SQLException {
        String query = """
                SELECT f.FlightID,c.CrewMemberID FROM flights as f
                JOIN
                (SELECT CrewID,CrewMemberID FROM crews) as c
                ON f.CrewID = c.CrewID;
                """;
        HashMap<Integer, ArrayList<Integer>> flights = new HashMap<Integer, ArrayList<Integer>>();
        ResultSet table = executeQuery(query);
        while (table.next()) {
            flights.putIfAbsent(table.getInt(2), new ArrayList<Integer>());
            flights.get(table.getInt(2)).add(table.getInt(1));
        }
        return flights;
    }

    public PassengerList getPassengerList(int flightID) throws SQLException {
        ResultSet table = getTableWhere("passengerlist", "FlightID", flightID);
        ArrayList<Seat> passengers = new ArrayList<Seat>();
        while (table.next()) {
            passengers.add(SeatFactory.createSeat(
                    table.getString("SeatType"),
                    table.getInt("SeatNumber"),
                    table.getInt("UserID"),
                    table.getBoolean("Insurance")));
        }
        return new PassengerList(passengers);
    }

    public ArrayList<RegisteredUser> getCrewList(int flightID) throws SQLException {
        UsersSingleton usersSingleton = UsersSingleton.getInstance();
        String query = String.format("""
                SELECT f.FlightID,c.CrewMemberID FROM flights as f
                JOIN
                (SELECT CrewID,CrewMemberID FROM crews) as c
                ON f.CrewID = c.CrewID
                WHERE f.FlightID = %d;
                """, flightID);
        ResultSet table = executeQuery(query);
        ArrayList<RegisteredUser> crewMembers = new ArrayList<RegisteredUser>();

        while (table.next()) {
            crewMembers.add(usersSingleton.getRegisteredUser(table.getInt(2)));
        }
        return crewMembers;
    }

    public void addAirport(Airport airport) throws SQLException {
        String query = String.format("""
                INSERT INTO airports (AirportCode, AirportName, City, Country)
                VALUES
                ('%s','%s','%s','%s');""",
                airport.getCode(), airport.getName(),
                airport.getCity(), airport.getCountry());
        execute(query);

        System.out.println("Added AAA");

    }

}
