import java.lang.reflect.Array;
import java.util.ArrayList;

public class Purchase {
    private ArrayList<Ticket> tickets;
    private ArrayList<Seat> seats;
    private CreditCard payment;
    private boolean purchaseInsurance;
    // private Receipt purchaseReceipt;

    Purchase(ArrayList<Ticket> tickets, ArrayList<Seat> seats, CreditCard payment, boolean purchaseInsurance){
        this.tickets=tickets;
        this.seats=seats;
        this.payment=payment;
        this.purchaseInsurance=purchaseInsurance;
        // this.purchaseReceipt=purchaseReceipt;
    }
    public ArrayList<Ticket> getTickets(){
        return tickets;
    }
    public ArrayList<Seat> getSeats(){
        return seats;
    }
    public CreditCard getPayment(){
        return payment;
    }
    public boolean getPurchaseInsurance(){
        return purchaseInsurance;
    }
    // public Receipt getPurchaseReceipt(){
    //     return purchaseReceipt;
    // }
    public void setTickets(ArrayList<Ticket> tickets){
        this.tickets=tickets;
    }
    public void setSeats(ArrayList<Seat> seats){
        this.seats=seats;
    }
    public void setPayment(CreditCard payment){
        this.payment=payment;
    }
    public void setPurchaseInsurance(boolean purchaseInsurance){
        this.purchaseInsurance=purchaseInsurance;
    }
    //I don't think we need a receipt class since we can generate a receipt from the purchase class  


    
}
