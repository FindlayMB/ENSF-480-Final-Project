package FlightSystem.objects.user;

import FlightSystem.data.DatabaseSingleton;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Findlay Brown
 */
public class UsersSingleton {
    private DatabaseSingleton dbConnection = DatabaseSingleton.getInstance();
    private static UsersSingleton usersInstance;
    private HashMap<Integer, User> users;
    private HashMap<Integer, RegisteredUser> registeredUsers;

    private UsersSingleton() {
        if (users == null) {
            try {
                users = new HashMap<Integer, User>(dbConnection.getUserTable());
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Failed to get users table!");
            }
        }
        registeredUsers = new HashMap<Integer, RegisteredUser>();

        for (int i : users.keySet()) {
            if (users.get(i) instanceof RegisteredUser) {
                registeredUsers.put(i, (RegisteredUser) users.get(i));
            }
        }
    }

    public static UsersSingleton getInstance() {
        if (usersInstance == null) {
            usersInstance = new UsersSingleton();
        }
        return usersInstance;
    }

    public HashMap<Integer, User> getUsersMap() {
        return users;
    }

    public ArrayList<User> getUsersList() {
        return new ArrayList<User>(users.values());
    }

    public User getUserByID(int ID) {
        return users.get(ID);
    }
    public User getUserByNameAndEmail(String firstName, String lastName, String email) {
        for (User user : users.values()) {
            if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName) && user.getEmail().equals(email) && user.getRole().equals("guest")) {
                return user;
            }
        }
        return null;
    }

    public HashMap<Integer, RegisteredUser> getRegisteredUsersMap() {
        return registeredUsers;
    }

    public ArrayList<RegisteredUser> getRegisteredUsersList() {
        return new ArrayList<RegisteredUser>(registeredUsers.values());
    }

    public RegisteredUser getRegisteredUser(int ID) {
        return registeredUsers.get(ID);
    }
}
