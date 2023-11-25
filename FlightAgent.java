public class FlightAgent extends BasicUser {

    private String username;
    private String password;
    FlightAgent(String firstName, String lastName, Address address, String email, String username, String password){
        super(firstName, null, address, email, null, null, null);
        this.username=username;
        this.password=password;
        super.status="flightAgent";
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
    public void getFlightList(Flight flights){
        //TODO
    }
    public void addPassenger(){
        //TODO
    }
    public void removePassenger(){
        //TODO
    }
}
