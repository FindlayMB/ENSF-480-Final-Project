package FlightSystem;

import java.util.*;

import FlightSystem.data.DatabaseSingleton;
import FlightSystem.objects.Airport;
import FlightSystem.objects.Plane;

public class FlightSystem {
    private DatabaseSingleton dbConnection;

    private static HashMap<String, Airport> airports;
    private static HashMap<Integer, Plane> planes;

    public FlightSystem() {
        this.dbConnection = DatabaseSingleton.getInstance();

        airports = getAirports();
        planes = getPlanes();
        System.out.println("Got tables!");

        System.out.println("Airports");
        for (String a : airports.keySet()) {
            System.out.println(airports.get(a).toString());
        }

        System.out.println("Planes");
        for (Integer i : planes.keySet()) {
            System.out.println(planes.get(i).toString());
        }

    }

    public HashMap<Integer, Plane> getPlanes() {
        if (planes == null) {
            try {
                planes = new HashMap<Integer, Plane>(dbConnection.getPlaneTable());
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Failed to get planes table!");
            }

        }
        return planes;
    }

    public HashMap<String, Airport> getAirports() {
        if (airports == null) {
            try {
                airports = new HashMap<String, Airport>(dbConnection.getAirportTable());
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Failed to get airports table!");
            }
        }
        return airports;
    }

}
