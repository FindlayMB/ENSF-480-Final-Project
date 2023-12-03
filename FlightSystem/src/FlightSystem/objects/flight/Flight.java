package FlightSystem.objects.flight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import FlightSystem.data.DatabaseSingleton;
import FlightSystem.objects.Crew;
import FlightSystem.objects.ToQuery;
import FlightSystem.objects.airport.*;
import FlightSystem.objects.plane.*;
import FlightSystem.objects.seats.*;
import FlightSystem.objects.user.*;

/**
 * 
 * @author Findlay Brown
 */
public class Flight implements ToQuery {
    private final int ID;
    private Airport destination;
    private LocalTime arrivalTime;
    private LocalDate arrivalDate;
    private Airport origin;
    private LocalTime departureTime;
    private LocalDate departureDate;
    private Float basePrice;
    private Crew crew;
    private PassengerList passengerList;
    private Plane plane;

    public Flight(int ID,
            Airport destination, LocalTime arrivalTime, LocalDate arrivalDate,
            Airport origin, LocalTime departureTime, LocalDate departureDate,
            Integer crewID, Float basePrice, Plane plane) {
        this.ID = ID;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.arrivalDate = arrivalDate;
        this.origin = origin;
        this.departureTime = departureTime;
        this.departureDate = departureDate;
        this.basePrice = basePrice;
        this.plane = plane;
        try {
            this.passengerList = DatabaseSingleton.getInstance().getPassengerList(ID);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to get passenger list for Flight: " + ID);
        }
        try {
            this.crew = new Crew(ID);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to get crew list for Flight: " + crewID);
        }

    }

    public Flight(int ID, Flight flight) {
        this.ID = ID;
        this.destination = flight.destination;
        this.arrivalTime = flight.arrivalTime;
        this.arrivalDate = flight.arrivalDate;
        this.origin = flight.origin;
        this.departureTime = flight.departureTime;
        this.departureDate = flight.departureDate;
        this.basePrice = flight.basePrice;
        this.plane = flight.plane;

        try {
            this.passengerList = DatabaseSingleton.getInstance().getPassengerList(ID);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to get passenger list for Flight: " + ID);
        }
        try {
            this.crew = new Crew(ID);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Failed to get crew list for Flight: " + ID);
        }
    }

    @Override
    public String toString() {
        String output = String.format(
                "%d: %s @ %s %s to %s @ %s %s",
                ID, origin, departureTime, departureDate, destination, arrivalTime, arrivalDate);
        return output;
    }

    public String toQuery() {
        String output = String.format("'%s','%s','%s','%s','%s','%s',%d,%d,%f",
                destination.getCode(),
                arrivalTime.toString(),
                arrivalDate.toString(),
                origin.getCode(),
                departureTime.toString(),
                departureDate.toString(),
                crew.getCrewID(),
                plane.getID(),
                basePrice);
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

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    public void addCrewMember(RegisteredUser crewMember, String job) {
        crew.addCrewMember(crewMember, job);
    }

    public void removeCrewMember(RegisteredUser crewMember) {
        crew.removeCrewMember(crewMember);
    }

    public PassengerList getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(PassengerList passengerList) {
        this.passengerList = passengerList;
    }

    public void addPassenger(Seat passenger) {
        try {
            DatabaseSingleton.getInstance().addPassenger(passenger, ID);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to add passenger: " + passenger.toString());
        }
        this.passengerList.addPassenger(passenger);
    }

    public void removePassenger(Seat passenger) {
        try {
            DatabaseSingleton.getInstance().removePassenger(passenger, ID);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to remove passenger:" + passenger.toString());
        }
        this.passengerList.removePassenger(passenger);
    }

    public Float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Float basePrice) {
        this.basePrice = basePrice;
    }

    public Plane getPlane() {
        return plane;
    }

}
