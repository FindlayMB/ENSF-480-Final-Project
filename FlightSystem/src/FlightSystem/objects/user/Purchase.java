package FlightSystem.objects.user;

import java.lang.reflect.Array;
import java.util.ArrayList;

import FlightSystem.objects.Ticket;

public class Purchase {
    private ArrayList<Ticket> tickets;
    private CreditCard payment;
    private boolean purchaseInsurance;
    // private Receipt purchaseReceipt;

    Purchase(ArrayList<Ticket> tickets, CreditCard payment, boolean purchaseInsurance){
        this.tickets=tickets;
        this.payment=payment;
        this.purchaseInsurance=purchaseInsurance;
        // this.purchaseReceipt=purchaseReceipt;
    }
    public ArrayList<Ticket> getTickets(){
        return tickets;
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
   
    public void setPayment(CreditCard payment){
        this.payment=payment;
    }
    public void setPurchaseInsurance(boolean purchaseInsurance){
        this.purchaseInsurance=purchaseInsurance;
    }
    //I don't think we need a receipt class since we can generate a receipt from the purchase class  


    
}