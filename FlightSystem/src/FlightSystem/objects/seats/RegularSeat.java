package FlightSystem.objects.seats;

/**
 * 
 * @author Findlay Brown
 */
public class RegularSeat extends Seat {

    public RegularSeat(int seatNumber) {
        super(seatNumber);
    }

    public RegularSeat(int seatNumber, int passengerID, boolean insurance) {
        super(seatNumber, passengerID, insurance);
    }

    @Override
    public String getSeatType() {
        return "regular";
    }

}