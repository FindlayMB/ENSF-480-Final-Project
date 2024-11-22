package FlightSystem.objects.user;

import java.time.LocalDate;

public class CreditCard {
    private String creditCardNumber;
    private int cvv;
    private LocalDate expiryDate;

    public CreditCard(String creditCardNumber, int cvv, LocalDate expiryDate) {
        this.creditCardNumber = creditCardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public String toQuery() {
        String output = String.format("'%s',%d,'%s'",
                creditCardNumber, cvv, expiryDate.toString());
        return output;
    }
}
