package FlightSystem.objects.flight;

import java.util.ArrayList;

import FlightSystem.objects.seats.*;

/**
 * 
 * @author Findlay Brown
 */
public class PassengerList {
    private ArrayList<Seat> passengers;

    public PassengerList(ArrayList<Seat> passengers) {
        this.passengers = new ArrayList<Seat>(passengers);
    }

    public void addPassenger() {

    }
    
    public ArrayList<Seat> getPassengers() {
        return passengers;
    }

    @Override
    public String toString() {
        return passengers.toString();
    }
}
