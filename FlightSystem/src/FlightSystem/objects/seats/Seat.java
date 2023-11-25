package FlightSystem.objects.seats;

/**
 * Seat abstract class
 * Used as a factory for seats
 * 
 * @author Findlay Brown
 */
public abstract class Seat {
    private int seatNumber;
    private int passengerID;
    private boolean isReserved;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.passengerID = 0;
        this.isReserved = false;
    }

    public Seat(int seatNumber, int passengerID) {
        this.seatNumber = seatNumber;
        this.passengerID = passengerID;
        this.isReserved = true;
    }

    public String getSeatType() {

        System.out.println(this.getClass());

        return "";
    }

}
