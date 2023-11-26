package FlightSystem.objects;

import java.time.LocalDate;
import java.util.ArrayList;

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
    private String role;
    private ArrayList<Purchase> purchases;
    private Crew crew = null;

    public User(int ID, String firstName, String lastName, String email ,String role) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.purchases = new ArrayList<Purchase>();
        this.role = role;
        if (role == "employee") {
            this.crew = new Crew(ID);
        }
    }
}
