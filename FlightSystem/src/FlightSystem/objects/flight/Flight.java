package FlightSystem.objects.flight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import FlightSystem.data.DatabaseSingleton;
import FlightSystem.objects.airport.*;
import FlightSystem.objects.user.*;

/**
 * 
 * @author Findlay Brown
 */
public class Flight {
    private final int ID;
    private Airport destination;
    private LocalTime arrivalTime;
    private LocalDate arrivalDate;
    private Airport origin;
    private LocalTime departureTime;
    private LocalDate departureDate;
    private Float basePrice;
    private ArrayList<RegisteredUser> crew;
    private PassengerList passengerList;

    public Flight(int ID,
            Airport destination, LocalTime arrivalTime, LocalDate arrivalDate,
            Airport origin, LocalTime departureTime, LocalDate departureDate,
            Integer crewID, Integer planeID, Float basePrice) {
        this.ID = ID;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.arrivalDate = arrivalDate;
        this.origin = origin;
        this.departureTime = departureTime;
        this.departureDate = departureDate;
        this.basePrice = basePrice;
        try {
            this.passengerList = DatabaseSingleton.getInstance().getPassengerList(ID);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to get passenger list for Flight: " + ID);
        }
        try {
            this.crew = DatabaseSingleton.getInstance().getCrewList(ID);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to get crew list for Flight: " + crewID);
        }

    }

    @Override
    public String toString() {
        String output = String.format("""
                %s @ %s %s to %s @ %s %s
                """, origin, departureTime, departureDate, destination, arrivalTime, arrivalDate);
        return output;
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

    public ArrayList<RegisteredUser> getCrew() {
        return crew;
    }

    public void setCrew(ArrayList<RegisteredUser> crew) {
        this.crew = crew;
    }

    public void addCrew(RegisteredUser newCrewMember) {
        this.crew.add(newCrewMember);
    }

    public PassengerList getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(PassengerList passengerList) {
        this.passengerList = passengerList;
    }

    public Float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Float basePrice) {
        this.basePrice = basePrice;
    }

}
