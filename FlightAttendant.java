import java.util.ArrayList;

public class FlightAttendant extends BasicUser{
    private int employeeID;
    //Should fix this sinxe flightAttendant need to login as
    private String username;
    private String password;
    FlightAttendant(String firstName, String lastName, Address address, String email, CreditCard creditCard, String birthday, ArrayList<Purchase> purchases, int employeeID, String username, String password){
        super(firstName, lastName, address, email, creditCard, birthday, purchases);
        this.employeeID=employeeID;
        this.username=username;
        this.password=password;
        super.status="flightAttendant";
    }
    FlightAttendant(BasicUser basicUser, int employeeID, String username, String password){
        super(basicUser.getFirstName(), basicUser.getLastName(), basicUser.getAddress(), basicUser.getEmail(), basicUser.getCreditCard(), basicUser.getBirthday(), basicUser.getPurchases());
        this.employeeID=employeeID;
        this.username=username;
        this.password=password;
        super.status="flightAttendant";
    }
    public int getEmployeeID(){
        return employeeID;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public void setEmployeeID(int employeeID){
        this.employeeID=employeeID;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void getFlightList(Flight flights){
        //TODO
    }
    


}
