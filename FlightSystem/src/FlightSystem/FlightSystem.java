package FlightSystem;

import java.time.LocalDate;
import java.util.*;

import javax.swing.SwingUtilities;

import FlightSystem.GUI.HomePage;
import FlightSystem.data.DatabaseSingleton;

import FlightSystem.objects.*;
import FlightSystem.objects.user.*;
import FlightSystem.objects.seats.*;
import FlightSystem.objects.flight.*;
import FlightSystem.objects.plane.*;
import FlightSystem.objects.airport.*;

public class FlightSystem {
    private static DatabaseSingleton dbConnection;

    private static AirportsSingleton airports;
    private static PlaneSingleton planes;
    private static UsersSingleton users;
    private static FlightsSingleton flights;

    public FlightSystem() {
        dbConnection = DatabaseSingleton.getInstance();
        airports = AirportsSingleton.getInstance();
        planes = PlaneSingleton.getInstance();
        users = UsersSingleton.getInstance();
        flights = FlightsSingleton.getInstance();
        RegisteredUser user = null;
        SwingUtilities.invokeLater(() -> {
            HomePage gui = new HomePage(user); // pass registered user object accross
            // pages to keep track of possible
            // logged in user
            gui.setVisible(true);
        });
        // users.addUser(new User(0, "John", "Doe", "JohnDoe@gmail.com", "guest"));

        // users.addRegisteredUser(new RegisteredUser(
        // users.addUser(new User(0, "John", "Doe", "JohnDoe@gmail.com", "member")),
        // "johnDoe", "doeJohn", LocalDate.now(), "member", null, null));

        // users.getUsersMap().values().forEach((u) -> {
        // System.out.println(u.toString());
        // });
        // try {
        // dbConnection.addAirport(new Airport("AAA", "Test Name", "Test City", "Test
        // Country"));
        // } catch (Exception e) {
        // System.out.println(e);
        // }
        // airports = AirportsSingleton.getInstance();
        // planes = PlaneSingleton.getInstance();
        // users = UsersSingleton.getInstance();

        // flights = FlightsSingleton.getInstance();

        // System.out.println("Airports");
        // for (Airport a : airports.getAirportList()) {
        // System.out.println(a.toString());
        // }

        // System.out.println("Planes");
        // for (Plane p : planes.getPlaneList()) {
        // System.out.println(p);
        // }

        // for (User u : users.getUsersList()) {
        // System.out.println(u.toString());
        // if (u instanceof RegisteredUser) {
        // System.out.println(((RegisteredUser) u).getFlights());
        // }
        // }

        // for (Flight f : flights.getFlightList()) {
        // System.out.println(f.getPassengerList());
        // }

    }

    public User login(String username, String password) {
        for (User u : UsersSingleton.getInstance().getRegisteredUsersList()) {
            if (u instanceof RegisteredUser) {
                if (((RegisteredUser) u).login(username, password)) {
                    return u;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        FlightSystem fs = new FlightSystem();

        
    }

}
