package FlightSystem.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import FlightSystem.data.DatabaseSingleton;

public class AirportSingleton {
    private DatabaseSingleton dbConnection;
    private static AirportSingleton onlyInstance;
    private HashMap<String, Airport> airports;

    private AirportSingleton()
    {
        if (airports == null) {
            try {
                airports = new HashMap<String, Airport>(dbConnection.getAirportTable());
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Failed to get airports table!");
            }
        }
    }

    public void addAirport(String code, Airport newAirport)
    {
        airports.put(code, newAirport);
    }

    public void removeAirport(String code, Airport removeAirport) 
    {
        airports.remove(code, removeAirport);
    }

    public static AirportSingleton getOnlyInstance() 
    {
        if(onlyInstance == null)
        {
            onlyInstance = new AirportSingleton();
        }
        return onlyInstance;
    }

    public List<Airport> getAirports()
    {
        return new ArrayList<>(airports.values());
    }

}
