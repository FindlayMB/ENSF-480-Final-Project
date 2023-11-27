package FlightSystem.objects.flight;

import java.util.*;
import FlightSystem.objects.airport.*;
import FlightSystem.data.DatabaseSingleton;

public class FlightsSingleton {
    private DatabaseSingleton dbConnection = DatabaseSingleton.getInstance();
    private static FlightsSingleton flightInstance;
    private HashMap<Integer, Flight> flights;

    private int flightID = 0;

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

    public static FlightsSingleton getInstance() {
        if (flightInstance == null) {
            flightInstance = new FlightsSingleton();
        }
        return flightInstance;
    }

    public HashMap<Integer, Flight> getFlighMap() {
        return flights;
    }

    public ArrayList<Flight> getFlightList() {
        return new ArrayList<Flight>(flights.values());
    }

    public Flight getFlight(int flightID) {
        return flights.get(flightID);
    }

    public void addFlight(Flight newFlight) {
        if (flightID == 0) {
            flights.put(flightID, newFlight);
        } else {
            flightID++;
            flights.put(flightID, newFlight);
        }
    }

    public void removeFlight(Flight removeFlight) {
        flights.remove(flightID, removeFlight);
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
