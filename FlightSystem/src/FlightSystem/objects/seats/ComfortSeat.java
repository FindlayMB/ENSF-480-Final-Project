package FlightSystem.objects.seats;

/**
 * 
 * @author Findlay Brown
 */
public class ComfortSeat extends Seat {
    private static final double priceMultipler = 1.5;

    public ComfortSeat(int seatNumber) {
        super(seatNumber);
    }

    public ComfortSeat(int seatNumber, int passengerID, boolean insurance, float pricePaid) {
        super(seatNumber, passengerID, insurance, pricePaid);
    }

    @Override
    public String getSeatType() {
        return "comfort";
    }

    public static double getPriceMultipler() {
        return priceMultipler;
    }
}
