package FlightSystem;

import java.util.*;

import FlightSystem.data.DatabaseSingleton;

import FlightSystem.objects.*;
import FlightSystem.objects.user.*;
import FlightSystem.objects.seats.*;
import FlightSystem.objects.flight.*;
import FlightSystem.objects.plane.*;
import FlightSystem.objects.airport.*;

public class FlightSystem {
    private DatabaseSingleton dbConnection;

    private static AirportsSingleton airports;
    private static UsersSingleton users;
    private static PlaneSingleton planes;
    private static FlightsSingleton flights;

    public FlightSystem() {
        this.dbConnection = DatabaseSingleton.getInstance();

        try {
            dbConnection.addAirport(new Airport("AAA", "Test Name", "Test City", "Test Country"));
        } catch (Exception e) {
            System.out.println(e);
        }
        airports = AirportsSingleton.getInstance();
        planes = PlaneSingleton.getInstance();
        users = UsersSingleton.getInstance();

        flights = FlightsSingleton.getInstance();

        System.out.println("Airports");
        for (Airport a : airports.getAirportList()) {
            System.out.println(a.toString());
        }

        System.out.println("Planes");
        for (Plane p : planes.getPlaneList()) {
            System.out.println(p);
        }

        for (User u : users.getUsersList()) {
            System.out.println(u.toString());
            if (u instanceof RegisteredUser) {
                System.out.println(((RegisteredUser) u).getFlights());
            }
        }

        for (Flight f : flights.getFlightList()) {
            System.out.println(f.getPassengerList());
        }

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

}
