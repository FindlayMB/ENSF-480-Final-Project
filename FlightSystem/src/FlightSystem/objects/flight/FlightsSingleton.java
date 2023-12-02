package FlightSystem.objects.flight;

import java.util.*;
import FlightSystem.objects.airport.*;
import FlightSystem.objects.plane.Plane;
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

    public void updateFlight(Flight updatedFlight) {

    }

    public void removeFlight(Flight removeFlight) {
        try {
            DatabaseSingleton.getInstance().removeFlight(removeFlight);
            flights.remove(removeFlight.getID(), removeFlight);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to remove flight: " + removeFlight.toString());
        }
    }

    public void removeFlight(ArrayList<Flight> toRemove) {
        try {
            for (Flight f : toRemove) {
                DatabaseSingleton.getInstance().removeFlight(f);
                flights.remove(f.getID());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to remove flights");
        }
    }

    /**
     * If an airport is removed, remove all flights that go to
     * or depart from.
     * 
     * @param removedAirport
     */
    public void removeFlights(Airport removedAirport) {
        ArrayList<Flight> toRemove = new ArrayList<Flight>();
        flights.values().forEach((f) -> {
            if (f.getDestination().equals(removedAirport) || f.getOrigin().equals(removedAirport)) {
                toRemove.add(f);
            }
        });
        removeFlight(toRemove);
    }

    /**
     * If a plane is removed, remove all flights that
     * use that plane.
     * 
     * @param removePlane
     */
    public void removeFlights(Plane removePlane) {
        ArrayList<Flight> toRemove = new ArrayList<Flight>();
        for (Flight f : flights.values()) {
            if (f.getPlane().getID() == removePlane.getID()) {
                toRemove.add(f);
            }
        }
        removeFlight(toRemove);
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
