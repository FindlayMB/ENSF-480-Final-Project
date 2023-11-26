package FlightSystem.objects;

public class CreditCard {
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String csv;
    public CreditCard(String cardNumber, String cardHolderName, String expiryDate, String csv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.csv = csv;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public String getCardHolderName() {
        return cardHolderName;
    }
    public String getExpiryDate() {
        return expiryDate;
    }
  
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    public void setcsv(String csv) {
        this.csv = csv;
    }
   
    public String toString() {
        return "Card Number: " + cardNumber + "\n" +
                "Card Holder Name: " + cardHolderName + "\n" +
                "Expiry Date: " + expiryDate + "\n" +
                "csv: " + csv + "\n";
    }
    
}