package FlightSystem.objects.seats;

/**
 * 
 * @author Findlay Brown
 */
public class ComfortSeat extends Seat {
    public ComfortSeat(int seatNumber) {
        super(seatNumber);
    }

    public ComfortSeat(int seatNumber, int passengerID, boolean insurance) {
        super(seatNumber, passengerID, insurance);
    }

    @Override
    public String getSeatType() {
        return "comfort";
    }
}