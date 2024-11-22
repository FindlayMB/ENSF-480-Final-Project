package FlightSystem.objects.seats;

/**
 * 
 * @author Findlay Brown
 */
public class BusinessSeat extends Seat {
    private static final double priceMultipler = 2;

    public BusinessSeat(int seatNumber) {
        super(seatNumber);
    }

    public BusinessSeat(int seatNumber, int passengerID, boolean insurance, Float pricePaid) {
        super(seatNumber, passengerID, insurance, pricePaid);
    }

    @Override
    public String getSeatType() {
        return "business";
    }

    public static double getPriceMultipler() {
        return priceMultipler;
    }
}
