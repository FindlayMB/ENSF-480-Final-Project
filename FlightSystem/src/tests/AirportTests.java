package tests;

import FlightSystem.objects.airport.Airport;
import FlightSystem.objects.airport.AirportsSingleton;
import FlightSystem.objects.flight.FlightsSingleton;

public class AirportTests {

    public static void main(String[] args) {
        // testAirportAdd();

        // testAirportRemove();

        // testAirportRemoveOnFlights();
    }

    // Airport Add works
    public static void testAirportAdd() {
        AirportsSingleton airports = AirportsSingleton.getInstance();

        airports.getAirportList().forEach((a) -> {
            System.out.println(a);
        });

        airports.addAirport(new Airport("AAA", "Test A", "Test A", "Test A"));

        airports.getAirportList().forEach((a) -> {
            System.out.println(a);
        });
    }

    // Airport Remove works
    public static void testAirportRemove() {
        AirportsSingleton airports = AirportsSingleton.getInstance();

        System.out.println("Before");
        airports.getAirportList().forEach((a) -> {
            System.out.println(a);
        });

        airports.removeAirport(airports.getAirport("AAA"));

        System.out.println("\nAfter");
        airports.getAirportList().forEach((a) -> {
            System.out.println(a);
        });
    }

    // Flights removed if airport is removed works
    public static void testAirportRemoveOnFlights() {
        AirportsSingleton airports = AirportsSingleton.getInstance();
        FlightsSingleton flights = FlightsSingleton.getInstance();

        System.out.println("Before");
        flights.getFlightList().forEach((f) -> {
            System.out.println(f);
        });

        airports.removeAirport(airports.getAirport("AAL"));

        System.out.println("\nAfter");
        flights.getFlightList().forEach((f) -> {
            System.out.println(f);
        });
    }
}
