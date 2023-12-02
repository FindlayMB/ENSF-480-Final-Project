package FlightSystem.objects.seats;

import FlightSystem.objects.ToQuery;

/**
 * Seat abstract class
 * Used as a factory for seats
 * 
 * @author Findlay Brown
 */
public abstract class Seat implements ToQuery {
    private int seatNumber;
    private int passengerID;
    private boolean isReserved;
    private boolean insurance;
    private double price;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.passengerID = 0;
        this.isReserved = false;
        this.insurance = false;
    }

    public Seat(int seatNumber, int passengerID, boolean insurance) {
        this.seatNumber = seatNumber;
        this.passengerID = passengerID;
        this.isReserved = true;
        this.insurance = insurance;
    }

    public abstract String getSeatType();

    public abstract double getPriceMultipler();

    public int getSeatNumber() {
        return seatNumber;
    }

    public int getPassengerID() {
        return passengerID;
    }

    /**
     * Set a seat to have a passenger
     * If passengerID is -1 then the seat is not reserved
     * 
     * @param passengerID
     */
    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
        this.isReserved = (passengerID != -1);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean getInsurance() {
        return insurance;
    }

    @Override
    public String toString() {
        String output = String.format("Seat Number:%d  Passenger ID: %d  Seat Type: %s",
                seatNumber, passengerID, getSeatType());
        return output;
    }

    public String toQuery() {
        String output = String.format("%d,%d,'%s',%b",
                getPassengerID(),
                getSeatNumber(),
                getSeatType(),
                getInsurance());

        return output;
    }

}
