package FlightSystem.objects;

import java.util.ArrayList;
import java.util.*;
public class RegisteredUser extends User {
    private String username;
    private String password;
    private CreditCard companyCreditCard;
    RegisteredUser(int ID, String firstName, String lastName, String email, CreditCard creditCard, Address address ,ArrayList<Purchase> purchases, String username, String password){
        super(ID, firstName, lastName, email,"registered user");
        this.username=username;
        this.password=password;
        this.companyCreditCard=creditCard;
    }
    // RegisteredUser(User User, String username, String password){
    //     super(User.getFirstName(), User.getLastName(), User.getAddress(), User.getEmail(), User.getCreditCard(), User.getBirthday(), User.getPurchases());
    //     this.username=username;
    //     this.password=password;
    //     super.status="RegisteredUser";
    // }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void getMonthlyNews(){
        //TODO
    }
    public void useCompanionTicket(){
        //TODO
    }
    public void update(String news){
        //TODO
    }
    
}