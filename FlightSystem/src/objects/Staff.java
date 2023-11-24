package objects;

import java.time.LocalDate;

public class Staff extends User {
    private String job;

    public Staff(int ID, String firstName, String lastName, String email,
            LocalDate signUpDate, String creditCardNumber, String role, String job) {
        super(ID, firstName, lastName, email, signUpDate, creditCardNumber, role);
        this.job = job;
    }

}
