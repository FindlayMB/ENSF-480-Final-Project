public class CreditCard {
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
    private String cardType;
    public CreditCard(String cardNumber, String cardHolderName, String expiryDate, String cvv, String cardType) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.cardType = cardType;
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
    public String getCvv() {
        return cvv;
    }
    public String getCardType() {
        return cardType;
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
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    public String toString() {
        return "Card Number: " + cardNumber + "\n" +
                "Card Holder Name: " + cardHolderName + "\n" +
                "Expiry Date: " + expiryDate + "\n" +
                "CVV: " + cvv + "\n" +
                "Card Type: " + cardType + "\n";
    }
    
}
