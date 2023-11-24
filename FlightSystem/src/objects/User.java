package objects;

import java.time.LocalDate;

public class User {

    enum Role {
        MEMBER,
        EMPLOYEE,
        ADMIN
    }

    private final int ID;
    private String firstName;
    private String lastName;
    private String email;
    private final LocalDate signUpDate;
    private String creditCardNumber;
    private String role;

    public User(int ID, String firstName, String lastName, String email,
            LocalDate signUpDate, String creditCardNumber, String role) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.signUpDate = signUpDate;
        this.creditCardNumber = creditCardNumber;
        this.role = role;
    }

}
