package FlightSystem.objects.seats;

/**
 * 
 * @author Findlay Brown
 */
public class BusinessSeat extends Seat {
    public BusinessSeat(int seatNumber) {
        super(seatNumber);
    }

    public BusinessSeat(int seatNumber, int passengerID, boolean insurance) {
        super(seatNumber, passengerID, insurance);
    }

    @Override
    public String getSeatType() {
        return "business";
    }
}
