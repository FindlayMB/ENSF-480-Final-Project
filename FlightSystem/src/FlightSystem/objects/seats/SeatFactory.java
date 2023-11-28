package FlightSystem.objects.seats;

/**
 * 
 * @author Findlay Brown
 */
public class SeatFactory {
    public static Seat createSeat(String seatType, int seatNumber) {
        switch (seatType) {
            case "regular":
                return new RegularSeat(seatNumber);
            case "comfort":
                return new ComfortSeat(seatNumber);
            case "business":
                return new BusinessSeat(seatNumber);
            default:
                System.out.println("FAILED TO CREATE SEAT!");
                return null;
        }
    }

    public static Seat createSeat(String seatType, int seatNumber, int passengerID, boolean insurance) {
        switch (seatType) {
            case "regular":
                return new RegularSeat(seatNumber, passengerID, insurance);
            case "comfort":
                return new ComfortSeat(seatNumber, passengerID, insurance);
            case "business":
                return new BusinessSeat(seatNumber, passengerID, insurance);
            default:
                System.out.println("FAILED TO CREATE SEAT!");
                return null;
        }
    }
}
