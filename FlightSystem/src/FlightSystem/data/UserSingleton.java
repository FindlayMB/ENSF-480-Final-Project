package FlightSystem.data;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import FlightSystem.objects.*;

public class UserSingleton {
    private DatabaseSingleton dbConnection = DatabaseSingleton.getInstance();
    private static UserSingleton onlyInstance;
    private HashMap<Integer, User> users;

    private static int userId = 0;

    private UserSingleton()
    {
        if (users == null) {
            try {
                users = new HashMap<Integer, User>(dbConnection.getUserTable());
                userId = users.size()+1;
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Failed to get airports table!");
            }
        }
    }

    public User addUser(String firstName, String lastName, String email, String userType) throws SQLException
    {
            User newUser = new User(userId, firstName, lastName, email, userType);
            users.put(userId, newUser); // add user to hashmap
            dbConnection.addUser(newUser); // add user to DB
            userId++;
            return(newUser); // return user ID of user that was just added
    }

    public void removeUser(User removeUser) 
    {
        users.remove(userId, removeUser);
    }

    public static UserSingleton getOnlyInstance() 
    {
        if(onlyInstance == null)
        {
            onlyInstance = new UserSingleton();
        }
        return onlyInstance;
    }

    public ArrayList<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public void setPurchase(User modUser, Flight selectedFlight, Set<Integer> selectedSeats, boolean hasInsurance, String creditCardNumber, LocalDate expiryDate, String CSV){
        users.get(modUser.getID()).setPurchase(selectedFlight, selectedSeats, hasInsurance, creditCardNumber, expiryDate, CSV);
    }
    
}
