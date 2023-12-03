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
    private Float pricePaid;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.passengerID = 0;
        this.isReserved = false;
        this.insurance = false;
    }

    public Seat(int seatNumber, int passengerID, boolean insurance, Float pricePaid) {
        this.seatNumber = seatNumber;
        this.passengerID = passengerID;
        this.isReserved = true;
        this.insurance = insurance;
        this.pricePaid = pricePaid;
    }

    public abstract String getSeatType();

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

    public Float getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(Float pricePaid) {
        this.pricePaid = pricePaid;
    }

    public boolean getInsurance() {
        return insurance;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    @Override
    public String toString() {
        String output = String.format("Seat Number:%d  Passenger ID: %d  Seat Type: %s",
                seatNumber, passengerID, getSeatType());
        return output;
    }

    public String toQuery() {
        String output = String.format("%d,%d,'%s',%b,%f",
                getPassengerID(),
                getSeatNumber(),
                getSeatType(),
                getInsurance(),
                getPricePaid());

        return output;
    }

}
