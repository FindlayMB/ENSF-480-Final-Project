package FlightSystem.objects;

import java.time.LocalDate;

public class User {

    enum Role {
        MEMBER,
        EMPLOYEE,
        ADMIN
    }

    private final int ID;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private final LocalDate signUpDate;
    private String creditCardNumber;
    private String role;
    private Crew crew = null;

    public User(int ID, String userName, String password,
            String firstName, String lastName, String email,
            LocalDate signUpDate, String creditCardNumber, String role) {
        this.ID = ID;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.signUpDate = signUpDate;
        this.creditCardNumber = creditCardNumber;
        this.role = role;
        if (role == "employee") {
            this.crew = new Crew(ID);
        }
    }
    public String getUsername() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

}
