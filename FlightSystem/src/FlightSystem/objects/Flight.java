package FlightSystem.objects;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import FlightSystem.objects.airport.Airport;

public class Flight {
    private final int ID;
    private Airport destination;
    private LocalTime arrivalTime;
    private LocalDate arrivalDate;
    private Airport origin;
    private LocalTime departureTime;
    private LocalDate departureDate;
    private ArrayList<User> crew;

    public Flight(int ID, Airport destination, LocalTime arrivalTime, LocalDate arrivalDate, Airport origin,
            LocalTime departureTime, LocalDate departureDate) {
        this.ID = ID;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.arrivalDate = arrivalDate;
        this.origin = origin;
        this.departureTime = departureTime;
        this.departureDate = departureDate;
    }

    public int getID() {
        return ID;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public ArrayList<User> getCrew() {
        return crew;
    }

    public void setCrew(ArrayList<User> crew) {
        this.crew = crew;
    }

    public void addCrew(User newCrewMember) {
        this.crew.add(newCrewMember);
    }

}
