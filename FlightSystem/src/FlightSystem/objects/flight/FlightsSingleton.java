package FlightSystem.objects.flight;

import java.util.*;
import FlightSystem.objects.airport.*;
import FlightSystem.data.DatabaseSingleton;

/**
 * 
 * @author Findlay Brown
 */
public class FlightsSingleton {
    private DatabaseSingleton dbConnection = DatabaseSingleton.getInstance();
    private static FlightsSingleton flightInstance;
    private HashMap<Integer, Flight> flights;

    private FlightsSingleton() {
        if (flights == null) {
            try {
                flights = new HashMap<Integer, Flight>(dbConnection.getFlightsTable());
                // System.out.println("Got Flights!");
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Failed to get flights table!");
            }

        }
    }

    public static synchronized FlightsSingleton getInstance() {
        if (flightInstance == null) {
            flightInstance = new FlightsSingleton();
        }
        return flightInstance;
    }

    public HashMap<Integer, Flight> getFlightMap() {
        return flights;
    }

    public ArrayList<Flight> getFlightList() {
        return new ArrayList<Flight>(flights.values());
    }

    public Flight getFlight(int flightID) {
        return flights.get(flightID);
    }

    public ArrayList<Flight> getFlight(String destination) {
        // return all flights that go to destination
        HashMap<Integer, Flight> filteredFlights = new HashMap<Integer, Flight>();
        for (Flight flight : flights.values()) {
            if (flight.getDestination().getCode().equals(destination)) {
                filteredFlights.put(flight.getID(), flight);
            }
        }
        return new ArrayList<Flight>(filteredFlights.values());
    }

    public void addFlight(Flight newFlight) {
        newFlight = DatabaseSingleton.getInstance().addFlight(newFlight);
        flights.put(newFlight.getID(), newFlight);
    }

    public void removeFlight(Flight removeFlight) {
        flights.remove(removeFlight.getID(), removeFlight);
    }

    /**
     * If an airport is removed, remove all flights that go to
     * or depart from.
     * 
     * @param removedAirport
     */
    public void removeFlights(Airport removedAirport) {
        for (Flight f : flights.values()) {
            if (f.getDestination().equals(removedAirport) || f.getOrigin().equals(removedAirport)) {
                flightInstance.removeFlight(f);
            }
        }
    }

    /**
     * Filter flights by value
     * 
     * @param flag  data member to filter along
     * @param value Value to filter by
     * @return
     */
    public ArrayList<Flight> getFlights(String dataMember, String value) {
        // Return all flights that go to airportCode
        ArrayList<Flight> filteredFlights = new ArrayList<Flight>();

        switch (dataMember) {
            case "destinationCode":
                for (Flight flight : flights.values()) {
                    if (flight.getDestination().getCode().equals(value)) {
                        filteredFlights.add(flight);
                    }
                }
                break;
            case "originCode":
                for (Flight flight : flights.values()) {
                    if (flight.getOrigin().getCode().equals(value)) {
                        filteredFlights.add(flight);
                    }
                }
                break;
            case "arrivalDate":
                for (Flight flight : flights.values()) {
                    if (flight.getDestination().getCode().equals(value)) {
                        filteredFlights.add(flight);
                    }
                }
                break;
            default:
                break;
        }

        return filteredFlights;
    }

}
