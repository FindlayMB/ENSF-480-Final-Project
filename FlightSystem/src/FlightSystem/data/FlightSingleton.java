package FlightSystem.data;

import java.util.*;

import FlightSystem.objects.Airport;
import FlightSystem.objects.Flight;

public class FlightSingleton {
    private DatabaseSingleton dbConnection = DatabaseSingleton.getInstance();
    private static FlightSingleton onlyInstance;
    private HashMap<Integer, Flight> flights;

    private int flightID = 0;

    private FlightSingleton()
    {
        if (flights == null) {
            try {
                flights = new HashMap<Integer, Flight>(dbConnection.getFlightsTable());
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Failed to get planes table!");
            }

        }
    }

    public void addFlight(Flight newFlight)
    {
        if (flightID == 0){
            flights.put(flightID, newFlight);
        }
        else{
            flightID++;
            flights.put(flightID, newFlight);
        }
    }

    public void removeFlight(Flight removeFlight)
    {
        flights.remove(flightID, removeFlight);
    }

    public static FlightSingleton getOnlyInstance() 
    {
        if(onlyInstance == null)
        {
            onlyInstance = new FlightSingleton();
        }
        return onlyInstance;
    }

    public ArrayList<Flight> getFlights(String destination)
    {
        // return all flights that go to destination
        HashMap<Integer, Flight> filteredFlights = new HashMap<>();
        for (Flight flight : flights.values()) {
            if (flight.getDestination().getCode().equals(destination)) {
                filteredFlights.put(flight.getID(), flight);
            }
        }
        return new ArrayList<>(filteredFlights.values());
    }
    public ArrayList<Flight> getFlights()
    {
        // return all flights that go to destination
        return new ArrayList<>(flights.values());
    }
    
}
