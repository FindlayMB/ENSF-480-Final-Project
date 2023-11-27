package FlightSystem.data;

import java.awt.Color;
import java.sql.SQLException;
import java.util.*;

import FlightSystem.objects.Airport;
import FlightSystem.objects.Flight;
import FlightSystem.objects.User;

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
    public void addPassenger(Flight selectedFlight, User user, HashMap<Integer, Color> selectedSeats, boolean hasInsurance) throws SQLException {
        // add passenger to flight
        for(int seatNum : selectedSeats.keySet()) {
            flights.get(selectedFlight.getID()).addPassenger(user, seatNum);
            // update database
            Color seatColor = selectedSeats.get(seatNum);
            String seatClass;
            if(seatColor.equals(Color.GREEN))
            {
                seatClass = "ordinary";
            }
            else if(seatColor.equals(new Color(173, 216, 230)))
            {
                seatClass = "comfort";
            }
            else
            {
                seatClass = "Business";
            }

            dbConnection.addPassenger(selectedFlight.getID(), user.getID(), seatNum, seatClass, hasInsurance);
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

    public HashMap<Integer, Flight> getFlights(String destination) {
        // return all flights that go to destination
        HashMap<Integer, Flight> filteredFlights = new HashMap<>();
        for (Flight flight : flights.values()) {
            if (flight.getDestination().getCode().equals(destination)) {
                filteredFlights.put(flight.getID(), flight);
            }
        }
        return filteredFlights;
    }
    
}
