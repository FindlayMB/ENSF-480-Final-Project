package FlightSystem.objects.flight;

import java.util.ArrayList;

import FlightSystem.objects.airport.Airport;

/**
 * 
 * @author Findlay Brown
 */
public class FlightFilter {

    /**
     * @param flights
     * @param destination
     * @return
     */
    public ArrayList<Flight> filterByDestination(ArrayList<Flight> flights, Airport destination) {

        @SuppressWarnings("unchecked")
        ArrayList<Flight> filteredFlights = (ArrayList<Flight>) flights.stream()
                .filter(f -> f.getDestination().equals(destination));

        return filteredFlights;
    }

}
