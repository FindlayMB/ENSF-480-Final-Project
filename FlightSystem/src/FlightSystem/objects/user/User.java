package FlightSystem.objects.user;

import java.time.LocalDate;
import java.util.ArrayList;

import FlightSystem.objects.ToQuery;
import FlightSystem.objects.flight.Flight;
import FlightSystem.objects.flight.FlightsSingleton;

/**
 * 
 * @author Findlay Brown
 */
public class User implements ToQuery {
    private final int ID;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    public User(int ID,
            String firstName, String lastName, String email, String role) {
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

    public User(int ID, User user) {
        this.ID = ID;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
        this.role = user.role;
    }

    public String toQuery() {
        String output = String.format("'%s','%s','%s','%s'",
                firstName, lastName, email, role);
        return output;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s", ID, firstName, lastName);
    }

    public ArrayList<Flight> getUserFlights() {
        ArrayList<Flight> userFlights = new ArrayList<Flight>();
        FlightsSingleton.getInstance().getFlightList().forEach((flight) -> {
            flight.getPassengerList().getPassengers().forEach((passenger) -> {
                if (this.ID == passenger.getPassengerID()) {
                    userFlights.add(flight);
                }
            });
        });
        return userFlights;
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

}
