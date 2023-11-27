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
                    "root",
                    "$");
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
                    table.getString(5)));
        }

        return users;
    }
    

    public void getCrewFlights(Crew crew, int ID) throws SQLException {
        String query = String.format(
        "SELECT f.FlightID, c.Job " +
        "FROM flights AS f " +
        "JOIN (SELECT CrewID, Job FROM crews WHERE CrewMemberID = %d) AS c " +
        "ON f.CrewID = c.CrewID",
        ID);

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

    private Airport getAirport(String airportCode) throws SQLException {
        ResultSet airportResultSet = executeQuery("SELECT * FROM airports WHERE AirportCode = '" + airportCode + "'");
        if (airportResultSet.next()) {
            return new Airport(
                    airportResultSet.getString(1),
                    airportResultSet.getString(2),
                    airportResultSet.getString(3),
                    airportResultSet.getString(4)
            );
        } else {
            throw new SQLException("Airport not found for code: " + airportCode);
        }
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
        HashMap<Integer, Flight> flights = new HashMap<Integer, Flight>();
        ResultSet table = getTable("flights");
        
        while (table.next()) {
            flights.put(table.getInt(1),
                new Flight( 
                        table.getInt(1),
                        getAirport(table.getString(2)),
                        table.getTime(3).toLocalTime(),
                        table.getDate(4).toLocalDate(),
                        getAirport(table.getString(5)),
                        table.getTime(6).toLocalTime(),
                        table.getDate(7).toLocalDate(),
                        getPlane((table.getInt(9))), //plane
                        getPassengers(table.getInt(1)) // passengers in seats
                        ));
            }
            return flights;
    }
    

    private Plane getPlane(int planeID) throws SQLException {
        ResultSet planeResultSet = executeQuery("SELECT * FROM planes WHERE PlaneID = '" + planeID + "'");
        if (planeResultSet.next()) {
            return new Plane(
                    planeResultSet.getInt(1),
                    planeResultSet.getString(2),
                    planeResultSet.getInt(3),
                    planeResultSet.getInt(4),
                    planeResultSet.getInt(5)
            );
        } else {
            throw new SQLException("Plane not found for code: " + planeID);
        }
    }

    private ArrayList<Seat> getPassengers(int flightID) throws SQLException {
        ResultSet passengersResultSet = executeQuery("SELECT * FROM passengerlist WHERE FlightID = '" + flightID + "'");
        ArrayList<Seat> seatList = new ArrayList<>();
    
        while (passengersResultSet.next()) {
            int seatNumber = passengersResultSet.getInt(3);
            User user = getUser(passengersResultSet.getInt(2));
            boolean isReserved = true;
            String seatClass = passengersResultSet.getString(4);
            Seat seat = new Seat(seatNumber, user, isReserved, seatClass); // Need to initialize subclass of seat depending on the class of the seat 
            seatList.add(seat);
        }
    
        if (!seatList.isEmpty()) {
            return seatList;
        } else {
            throw new SQLException("No passengers found for FlightID: " + flightID);
        }
    }

    public void addPassenger(int flightID,int userID, int seatNum,String seatClass,boolean hasInsurance) throws SQLException {
        String sql ="INSERT INTO passengerlist (FlightID, UserID, SeatNumber, SeatType, Insurance) VALUES (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(sql)) {
            //preparedStatement.setInt(1,UserID);
            preparedStatement.setInt(1,flightID);
            preparedStatement.setInt(2,userID);
            preparedStatement.setInt(3,seatNum);
            preparedStatement.setString(4,seatClass);
            preparedStatement.setBoolean(5,hasInsurance);
            preparedStatement.executeUpdate();
        } 
    }

    private User getUser(int userID) throws SQLException {
        ResultSet userResultSet = executeQuery("SELECT * FROM users WHERE UserID = '" + userID + "'");
        if (userResultSet.next()) {
            return new User(
                    userResultSet.getInt(1),
                    userResultSet.getString(2),
                    userResultSet.getString(3),
                    userResultSet.getString(4),
                    userResultSet.getString(5)
            );
        } else {
            throw new SQLException("User not found for code: " + userID);
        }
    }

    public void addUser(User newUser) throws SQLException {
        String sql ="INSERT INTO Users (FirstName,LastName,Email,Role) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(sql)) {
            //preparedStatement.setInt(1,UserID);
            preparedStatement.setString(1,newUser.getFirstName());
            preparedStatement.setString(2,newUser.getLastName());
            preparedStatement.setString(3,newUser.getEmail());
            preparedStatement.setString(4,newUser.getRole());
            preparedStatement.executeUpdate();
        } 
    }

}
