import java.util.ArrayList;

public class Admin implements Subject {
    private ArrayList<Flight> flights;
    private String employeeID;
    private String name;
    private String email;
    private String password;
    private String userName;
    private String status ="admin";
    private ArrayList<RegisterUser> registeredUsers;

    public Admin(String employeeID, String name, String email, String password, String userName, ArrayList<Flight> flights, ArrayList<RegisterUser> registeredUsers){
        this.employeeID=employeeID;
        this.name=name;
        this.email=email;
        this.password=password;
        this.userName=userName;
        this.flights=flights;
        this.registeredUsers=registeredUsers;
    }

    public void addFlight(Flight flight){
        //TODO
    }
    public void removeFlight(Flight flight){
        //TODO
    }
    public void addRegisteredUser(RegisterUser user){
        //TODO
    }
    public void removeRegisteredUser(RegisterUser user){
        //TODO
    }
    public void addFlightAgent(FlightAgent agent){
        //TODO
    }
    public void removeFlightAgent(FlightAgent agent){
        //TODO//Bankruptcies
    }
    public void addFlightAttendant(FlightAttendant attendant){
        //TODO
    }
    public void removeFlightAttendant(FlightAttendant attendant){
        //TODO
    }
    public void addDestination(Location destination){
        //TODO
    }
    public void removeDestination(Location destination){
        //TODO
    }
    public void setPromotionalNews(String news){
        //TODO
    }
    public void registerObserver(Observer o){
        //TODO
    }
    public void removeObserver(Observer o){
        //TODO
    }
    public void notifyObserver(){
        //TODO
    }


    
}
