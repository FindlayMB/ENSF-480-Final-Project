package FlightSystem.objects.airport;

import FlightSystem.data.DatabaseSingleton;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Findlay Brown
 */
public class AirportsSingleton {
    private DatabaseSingleton dbConnection = DatabaseSingleton.getInstance();
    private static AirportsSingleton instance;
    private HashMap<String, Airport> airports;

    private AirportsSingleton() {
        if (airports == null) {
            try {
                airports = new HashMap<String, Airport>(dbConnection.getAirportTable());
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Failed to get airports table!");
            }
        }
    }

    public static synchronized AirportsSingleton getInstance() {
        if (instance == null) {
            instance = new AirportsSingleton();
        }
        return instance;
    }

    public void addAirport(Airport newAirport) {
        airports.put(newAirport.getCode(), newAirport);
        try {
            dbConnection.addAirport(newAirport);
        } catch (Exception e) {
            System.out.println("Failed to add airport!");
            System.out.println(e);
        }

    }

    public void removeAirport(Airport removeAirport) {
        airports.remove(removeAirport.getCode(), removeAirport);
    }

    public void removeAirport(String code, Airport removeAirport) {
        airports.remove(code, removeAirport);
    }

    public HashMap<String, Airport> getAirportMap() {
        return airports;
    }

    public ArrayList<Airport> getAirportList() {
        return new ArrayList<Airport>(airports.values());
    }

    public Airport getAirport(String code) {
        return airports.get(code);
    }

}
