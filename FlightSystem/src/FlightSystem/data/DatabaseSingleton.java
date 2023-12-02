package FlightSystem.data;

import java.sql.*;
import java.util.*;

import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import com.mysql.cj.xdevapi.Result;

import FlightSystem.objects.*;
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
    private static DatabaseSingleton dbInstance;
    private Connection dbConnection;
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

    /*
     * Functions that execute queries on the database
     */

    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = dbConnection.createStatement();
        return statement.executeQuery(query);
    }

    public boolean execute(String query) throws SQLException {
        Statement statement = dbConnection.createStatement();
        return statement.execute(query);
    }

    /*
     * Functions to get data from database
     */

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
                    table.getString(5));
            if (table.getString(5).equals("guest") == false) {
                user = new RegisteredUser(user,
                        table.getString(6),
                        table.getString(7),
                        table.getDate(8).toLocalDate(),
                        table.getString(5),
                        new CreditCard(table.getString(9),
                                table.getInt(10),
                                table.getDate(11) != null ? table.getDate(12).toLocalDate() : null),
                        flights.get(table.getInt(1)));
                ((RegisteredUser) user).setPromos(getPromoTable((RegisteredUser) user));
            }
            users.put(table.getInt(1), user);
        }
        return users;
    }

    public HashMap<String, Float> getPromoTable(RegisteredUser user) throws SQLException {
        HashMap<String, Float> promos = new HashMap<String, Float>();
        ResultSet table = getTableWhere("promos", "UserID", user.getID());
        while (table.next()) {
            promos.put(table.getString(2), table.getFloat(3));
        }
        return promos;
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
                            table.getInt(9),
                            table.getFloat(10),
                            getPlane(table.getInt(9))));
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
                    planeResultSet.getInt(5));
        } else {
            throw new SQLException("Plane not found for code: " + planeID);
        }
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

    public void getCrewList(Crew crew, int flightID) throws SQLException {
        UsersSingleton usersSingleton = UsersSingleton.getInstance();
        String query = String.format("""
                SELECT f.FlightID,c.CrewID,c.CrewMemberID,c.Job FROM flights as f
                JOIN
                (SELECT CrewID,CrewMemberID,Job FROM crews) as c
                ON f.CrewID = c.CrewID
                WHERE f.FlightID = %d;
                """, flightID);

        ResultSet table = executeQuery(query);
        ArrayList<RegisteredUser> crewMembers = new ArrayList<RegisteredUser>();

        table.next();
        crew.setCrewID(table.getInt(2));
        do {
            RegisteredUser regUser = usersSingleton.getRegisteredUser(table.getInt(3));
            regUser.setJob(table.getString(4));
            crewMembers.add(regUser);
        } while (table.next());
        crew.setCrew(crewMembers);
    }

    /*
     * Functions to insert objects into the database
     */

    public Integer insertInto(String tableName, String[] columns, String values) throws SQLException {
        String columnString = "";
        for (String s : columns) {
            columnString += s + ",";
        }
        columnString = columnString.substring(0, columnString.length() - 1);
        String query = String.format("""
                INSERT INTO %s (%s)
                VALUES
                (%s);
                """, tableName, columnString, values);
        Statement statement = dbConnection.createStatement();
        statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        ResultSet table = statement.getGeneratedKeys();
        table.next();
        return table.getRow() != 0 ? table.getInt(1) : 0;
    }

    public Integer insertInto(String tableName, String[] columns, ToQuery object) throws SQLException {
        return insertInto(tableName, columns, object.toQuery());
    }

    public Plane addPlane(Plane plane) {
        String[] columns = { "PlaneType", "NumRegular", "NumComfort", "NumBusiness" };
        Plane newPlane = null;
        try {
            newPlane = new Plane(insertInto("planes", columns, plane), plane);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to insert new plane: " + plane.toString());
        }
        return newPlane;
    }

    public void addAirport(Airport airport) throws SQLException {
        String[] columns = { "AirportCode", "AirportName", "City", "Country" };
        insertInto("airports", columns, airport);
    }

    public User addUser(User user) {
        String[] columns = { "FirstName", "LastName", "Email", "Role" };
        User newUser = null;
        try {
            newUser = new User(insertInto("users", columns, user), user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to insert new user: " + user.toString());
        }
        return newUser;
    }

    public int addRegisteredUser(RegisteredUser regUser) throws SQLException {
        String[] columns = { "UserID", "Username", "Password", "SignUpDate", "CreditCardNumber", "CVV", "ExpiryDate" };
        return insertInto("registered", columns, regUser);
    }

    public void addPromo(int userID, String promoCode, Float discountPercent) throws SQLException {
        String query = String.format("""
                INSERT INTO promos (UserID,PromoCode,DiscountPercent)
                VALUES
                (%d,'%s',%f);

                """, userID, promoCode, discountPercent);
        execute(query);
    }

    public void addCrew(Crew crew) {
        String[] columns = { "CrewID", "CrewMemberID", "Job" };
        crew.getCrew().forEach((cMember) -> {
            try {
                insertInto("crews", columns,
                        String.format("%d,%d,'%s'",
                                crew.getCrewID(), cMember.getID(), cMember.getJob()));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to add crewmember" + cMember.getID());
            }
        });
    }

    public Flight addFlight(Flight flight) {
        String[] columns = { "Destination", "ArrivalTime", "ArrivalDate", "Origin", "DepartureTime",
                "DepartureDate", "CrewID", "PlaneID", "BasePrice" };
        Flight newFlight = null;
        try {
            newFlight = new Flight(insertInto("flights", columns, flight), flight);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to insert new flight: " + flight.toString());
        }
        return newFlight;
    }

    public void addPassenger(Seat passenger, int flightID) throws SQLException {
        String[] columns = { "FlightID", "UserID", "SeatNumber", "SeatType", "Insurance" };

        insertInto("passengerlist", columns,
                String.format("%d,%s", flightID, passenger.toQuery()));
    }

    public void addUserWithFields(String Username, String Password, String FirstName, String LastName, String Email,
            String signUpDate, String creditCardNumber, String Role) throws SQLException {
        String sql = "INSERT INTO Users (Username,Password,FirstName,LastName,Email,SignUpDate,creditCardNumber,Role) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(sql)) {
            // preparedStatement.setInt(1,UserID);
            preparedStatement.setString(1, Username);
            preparedStatement.setString(2, Password);
            preparedStatement.setString(3, FirstName);
            preparedStatement.setString(4, LastName);
            preparedStatement.setString(5, Email);
            preparedStatement.setString(6, signUpDate);
            preparedStatement.setString(7, creditCardNumber);
            preparedStatement.setString(8, Role);

            preparedStatement.executeUpdate();
        }
    }

    /*
     * Functions to remove objects from database tables
     */

    public void removeFrom(String table, String column, int value) throws SQLException {
        String query = String.format("""
                DELETE FROM %s WHERE %s = %d
                """, table, column, value);
        execute(query);
    }

    public void removeFrom(String table, String column, String value) throws SQLException {
        String query = String.format("""
                DELETE FROM %s WHERE %s = '%s'
                """, table, column, value);
        execute(query);
    }

    public void removeFrom(String table, String[] columns, int[] values) throws SQLException {
        String query = String.format("""
                DELETE FROM %s WHERE %s = %d and %s = %d;
                """, table, columns[0], values[0], columns[1], values[1]);
        execute(query);
    }

    public void removePlane(Plane plane) throws SQLException {
        removeFrom("planes", "PlaneID", plane.getID());
    }

    public void removeAirport(Airport airport) throws SQLException {
        removeFrom("airports", "AirportCode", airport.getCode());
    }

    public void removeUser(User user) throws SQLException {
        removeFrom("users", "UserID", user.getID());
    }

    public void removeRegisteredUser(RegisteredUser user) throws SQLException {
        removeFrom("registered", "UserID", user.getID());
    }

    public void removeCrew(Crew crew) throws SQLException {
        String query = """
                SET FOREIGN_KEY_CHECKS=0;
                DELETE FROM crews WHERE CrewMemberID = %d;
                SET FOREIGN_KEY_CHECKS=1;
                """;
        crew.getCrew().forEach((cMember) -> {
            try {
                execute(String.format(query, cMember.getID()));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to remove crewID: " + cMember.getID());
            }
        });
    }

    public void removeFlight(Flight flight) throws SQLException {
        removeFrom("flights", "FlightID", flight.getID());
    }

    public void removePassenger(Seat passenger, int flightID) throws SQLException {
        String[] columns = { "FlightID", "UserID" };
        int[] values = { flightID, passenger.getPassengerID() };
        removeFrom("passengerlist", columns, values);
    }

    public void updateFlight(Flight flight) throws SQLException {
        String query = """
                UPDATE flights SET
                Destination = '%s',
                ArrivalTime = '%s',
                ArrivalDate = '%s',
                Origin = '%s',
                DepartureTime = '%s',
                DepartureDate = '%s',
                CrewID = %d,
                PlaneID = %d,
                BasePrice = %f
                WHERE FlightID = %d;""";
        query = String.format(query,
                flight.getOrigin().getCode(),
                flight.getArrivalTime(),
                flight.getArrivalDate(),
                flight.getOrigin().getCode(),
                flight.getDepartureTime(),
                flight.getDepartureDate(),
                flight.getCrew().getCrewID(),
                flight.getPlane().getID(),
                flight.getBasePrice(),
                flight.getID());
        execute(query);
    }
}
