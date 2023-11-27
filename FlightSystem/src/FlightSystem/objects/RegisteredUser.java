package FlightSystem.objects;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.*;
public class RegisteredUser extends User {
    private String username;
    private String password;
    private CreditCard companyCreditCard;
    private LocalDate signUpDate;
    RegisteredUser(int ID, String username, String password, String firstName, String lastName, String email, LocalDate singupDate, CreditCard creditCard){
        super(ID, firstName, lastName, email,"member");
        this.username=username;
        this.password=password;
        this.companyCreditCard=creditCard; // company credit card may be set to NULL they must choose to signup for company credit card
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