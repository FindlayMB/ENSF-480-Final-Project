package FlightSystem.objects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;


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
    
    public String getEmail() {
        return email;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getRole() {
        return role;
    }

    public int getID() {
        return ID;
    }

    public void setPurchase(Flight selectedFlight, Set<Integer> selectedSeats, boolean hasInsurance, String creditCardNumber, LocalDate expiryDate, String CSV)
    {
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        for(int seatNum : selectedSeats) {
            tickets.add(new Ticket(selectedFlight, seatNum));
        }
        Purchase newPurchase = new Purchase(tickets, new CreditCard(creditCardNumber, firstName, lastName ,expiryDate, CSV), hasInsurance);
        purchases.add(newPurchase);
    }
     public ArrayList<Purchase> getPurchase()
    {
        return purchases;
    }

}
