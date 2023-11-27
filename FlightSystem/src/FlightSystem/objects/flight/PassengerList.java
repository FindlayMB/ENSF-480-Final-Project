package FlightSystem.objects.flight;

import java.util.ArrayList;

import FlightSystem.objects.seats.*;

public class PassengerList {
    private ArrayList<Seat> passengers;

    public PassengerList(ArrayList<Seat> passengers) {
        this.passengers = new ArrayList<Seat>(passengers);
    }

    public void addPassenger() {

    }

    @Override
    public String toString() {
        return passengers.toString();
    }
}
