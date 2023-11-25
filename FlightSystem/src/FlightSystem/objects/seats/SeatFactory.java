package FlightSystem.objects.seats;

public class SeatFactory {
    public Seat createSeat(String seatType, int seatNumber) {
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

    public Seat createSeat(String seatType, int seatNumber, int passengerID) {
        switch (seatType) {
            case "regular":
                return new RegularSeat(seatNumber, passengerID);
            case "comfort":
                return new ComfortSeat(seatNumber, passengerID);
            case "business":
                return new BusinessSeat(seatNumber, passengerID);
            default:
                System.out.println("FAILED TO CREATE SEAT!");
                return null;
        }
    }
}
