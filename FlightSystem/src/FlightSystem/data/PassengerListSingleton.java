package FlightSystem.data;

import java.util.HashMap;

import FlightSystem.objects.*;
import FlightSystem.objects.seats.*;

public class PassengerListSingleton {
    private DatabaseSingleton dbConnection = DatabaseSingleton.getInstance();
    private static PassengerListSingleton onlyInstance;
    private HashMap<Integer, Seat> passengerList;

    private int flightID = 0;

    private PassengerListSingleton() {
        if (this.passengerList == null) {
            try {
                // passengerList = new HashMap<Integer, Flight>(dbConnection.getFlightsTable());
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Failed to get planes table!");
            }

        }
    }

    // public void addFlight(Flight newFlight)
    // {
    // if (flightID == 0){
    // flights.put(flightID, newFlight);
    // }
    // else{
    // flightID++;
    // flights.put(flightID, newFlight);
    // }
    // }

    // public void removeFlight(Flight removeFlight)
    // {
    // flights.remove(flightID, removeFlight);
    // }

    public static PassengerListSingleton getOnlyInstance() {
        if (onlyInstance == null) {
            onlyInstance = new PassengerListSingleton();
        }
        return onlyInstance;
    }

    // public HashMap<Integer, Flight> getFlights(String destination) {
    // // return all flights that go to destination
    // HashMap<Integer, Flight> filteredFlights = new HashMap<>();
    // for (Flight flight : flights.values()) {
    // if (flight.getDestination().getCode().equals(destination)) {
    // filteredFlights.put(flight.getID(), flight);
    // }
    // }
    // return filteredFlights;
    // }
}
