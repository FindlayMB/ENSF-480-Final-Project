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

        users.forEach((key, value) -> {
            if (value instanceof RegisteredUser) {
                registeredUsers.put(key, (RegisteredUser) value);
            }
        });
    }

    public static synchronized UsersSingleton getInstance() {
        if (usersInstance == null) {
            usersInstance = new UsersSingleton();
        }
        return usersInstance;
    }

    public synchronized User addUser(User newUser) {
        newUser = DatabaseSingleton.getInstance().addUser(newUser);
        users.put(newUser.getID(), newUser);
        return newUser;
    }

    public synchronized RegisteredUser addRegisteredUser(RegisteredUser user) {
        try {
            registeredUsers.put(DatabaseSingleton.getInstance().addRegisteredUser(user), user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to add registered user: " + user.toString());
        }
        return user;
    }

    public boolean login(String username, String password) {
        for (RegisteredUser u : registeredUsers.values()) {
            if (u.login(username, password)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkUsername(String username) {
        for (RegisteredUser u : registeredUsers.values()) {
            if (u.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkEmail(String email) {
        for (User u : users.values()) {
            if (u.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public User getUserByNameAndEmail(String firstName, String lastName, String email) {
        for (User user : users.values()) {
            if (user.getFirstName().equals(firstName)
                    && user.getLastName().equals(lastName)
                    && user.getEmail().equals(email)
                    && user.getRole().equals("guest")) {
                return user;
            }
        }
        return null;
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
