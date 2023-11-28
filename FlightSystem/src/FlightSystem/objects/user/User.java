package FlightSystem.objects.user;

import java.time.LocalDate;
import java.util.ArrayList;

import FlightSystem.objects.flight.Flight;
import FlightSystem.objects.flight.FlightsSingleton;
import FlightSystem.objects.flight.PassengerList;
import FlightSystem.objects.seats.Seat;

/**
 * 
 * @author Findlay Brown
 */
public class User {
    private final int ID;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private String email;
    private String role;


    public User(int ID,
            String firstName, String lastName, String email,
            LocalDate birthDate, String role) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public User(User user) {
        this.ID = user.ID;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
        this.role = user.role;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s", ID, firstName, lastName);
    }
    
    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<Flight> getUserFlights() {
        FlightsSingleton flightsSingleton = FlightsSingleton.getInstance();
        ArrayList<Flight> userFlights = new ArrayList<Flight>();
        ArrayList<Flight> flights = flightsSingleton.getFlightList();
        for(Flight flight : flights) {
            PassengerList passengerList = flight.getPassengerList();
            for(Seat seat : passengerList.getPassengers()) {
                if(seat.getPassengerID() == this.ID) {
                    userFlights.add(flight);
                }
            }
        }
        return userFlights;
    }
}
