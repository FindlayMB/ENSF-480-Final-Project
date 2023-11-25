import java.util.ArrayList;
import java.util.*;
public class RegisterUser extends BasicUser implements Observer {
    private String username;
    private String password;
    RegisterUser(String firstName, String lastName, Address address, String email, CreditCard creditCard, String birthday, ArrayList<Purchase> purchases, String username, String password){
        super(firstName, lastName, address, email, creditCard, birthday, purchases);
        this.username=username;
        this.password=password;
        super.status="registerUser";
    }
    RegisterUser(BasicUser basicUser, String username, String password){
        super(basicUser.getFirstName(), basicUser.getLastName(), basicUser.getAddress(), basicUser.getEmail(), basicUser.getCreditCard(), basicUser.getBirthday(), basicUser.getPurchases());
        this.username=username;
        this.password=password;
        super.status="registerUser";
    }
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
