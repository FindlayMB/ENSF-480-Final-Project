package FlightSystem.objects.user;

import java.time.LocalDate;

/**
 * 
 * @author Findlay Brown
 */
public class User {
    private final int ID;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private String email;
    private String role;


    public User(int ID,
            String firstName, String lastName, String email,
            LocalDate birthDate, String role) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public User(User user) {
        this.ID = user.ID;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
        this.role = user.role;
    }

    @Override
    public String toString() {
        return String.format("""
                %d %s %s""", ID, firstName, lastName);
    }

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
