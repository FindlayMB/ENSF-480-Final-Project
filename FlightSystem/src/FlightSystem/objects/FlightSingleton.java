package FlightSystem.objects;

import java.util.HashMap;
import java.util.*;

public class FlightSingleton {
    private static FlightSingleton onlyInstance;
    private HashMap<Integer, Flight> flights;

    private int flightID = 0;


    private FlightSingleton()
    {
        // set flights arrayList equal to flight in DB
    }

    public void addFlight(Flight newFlight)
    {
        flights.put(flightID, newFlight);
    }

    public void removeFlight(Flight removeFlight)
    {
        flights.remove(removeFlight);
    }

    public static FlightSingleton getOnlyInstance() 
    {
        if(onlyInstance == null)
        {
            onlyInstance = new FlightSingleton();
        }
        return onlyInstance;
    }

    public HashMap<Integer, Flight> getFlights(Airport destination)
    {
        // return all flights that go to destination
        return(flights);
    }
    
}
