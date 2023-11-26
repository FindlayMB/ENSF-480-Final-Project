package FlightSystem.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import FlightSystem.objects.User;

public class UserSingleton {
    private DatabaseSingleton dbConnection = DatabaseSingleton.getInstance();
    private static UserSingleton onlyInstance;
    private HashMap<Integer, User> users;

    private int userId = 0;

    private UserSingleton()
    {
        if (users == null) {
            try {
                users = new HashMap<Integer, User>(dbConnection.getUserTable());
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Failed to get airports table!");
            }
        }
    }

    public void addUser(User newUser)
    {
            userId++;
            users.put(userId, newUser);
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

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }
    
}
