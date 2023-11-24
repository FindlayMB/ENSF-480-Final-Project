package objects;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Flight {
    private final int ID;
    private Airport destination;
    private LocalTime arrivalTime;
    private LocalDate arrivalDate;
    private Airport origin;
    private LocalTime departureTime;
    private LocalDate departureDate;
    private ArrayList<Staff> crew;

    public Flight(int ID, Airport destination, LocalTime arrivalTime) {
        this.ID = ID;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
    }

}
