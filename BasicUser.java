//import all from java.util
import java.util.*;
public class BasicUser{
    protected String firstName;
    protected String lastName;
    protected Address address;
    protected String email;
    protected String birthday;
    protected ArrayList<Purchase> purchases;

    protected CreditCard creditCard;
    protected String status="basicUser";

    public BasicUser(String firstName, String lastName, Address address, String email, CreditCard creditCard, String birthday, ArrayList<Purchase> purchases){
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.email=email;
        this.creditCard=creditCard;
        this.birthday=birthday;
        this.purchases=purchases;

    }

    //getters
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public Address getAddress(){
        return address;
    }
    public String getEmail(){
        return email;
    }
    public CreditCard getCreditCard(){
        return creditCard;
    }
    public String getStatus(){
        return status;
    }
    public String getBirthday(){
        return birthday;
    }
    public ArrayList<Purchase> getPurchases(){
        return purchases;
    }


    //setters

    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public void setAddress(Address address){
        this.address=address;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setCreditCard(CreditCard creditCard){
        this.creditCard=creditCard;
    }
    // public void setStatus(String status){
    //     this.status=status;
    // }
    public void setBirthday(String birthday){
        this.birthday=birthday;
    }
    public void searchFlights(Location origin, Location destination, Date date){
        //need to fix this
       
    }
    public void bookFlight(Flight flight){
        //need to fix this
    }
    public void cancelFlight(Flight flight){
        //need to fix this
    }
    public Ticket getTicket(){
        // for (int i=0; i<purchases.size(); i++){
        //     for(int j=0;j<purchases.get(i).getTickets().size();j++){
        //         System.out.println(purchases.get(i).getTickets().get(j).toString());
        //     }
        // }
        return null;

    //need to fix this
    }
    public void selectSeat(Flight flight){
        //need to fix this
    }


}