package FlightSystem.objects.user;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * 
 * @author Findlay Brown
 */
public class RegisteredUser extends User {

    private String username;
    private String password;
    private final LocalDate signUpDate;
    private CreditCard creditCard;
    private String job;
    private ArrayList<Integer> onFlights = new ArrayList<Integer>();

    public RegisteredUser(User user, String username, String password,
            LocalDate signUpDate, String job, CreditCard creditCard,
            ArrayList<Integer> onFlights) {
        super(user);
        this.username = username;
        this.password = password;
        this.signUpDate = signUpDate;
        this.job = job;
        this.creditCard = creditCard;
        if (onFlights != null) {
            this.onFlights = onFlights;
        }
    }

    @Override
    public String toQuery() {
        if (creditCard == null) {
            String output = String.format("%d,'%s','%s','%s',NULL,NULL,NULL",
                    super.getID(), username, password, signUpDate.toString());
            return output;
        }
        String output = String.format("%d,'%s','%s','%s',%s",
                username, password, signUpDate.toString(), creditCard.toQuery());
        return output;
    }

    public boolean login(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            return true;
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getSignUpDate() {
        return signUpDate;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void addFlight(int flightID) {
        this.onFlights.add(flightID);
    }

    public void addFlights(ArrayList<Integer> flights) {
        this.onFlights = flights;
    }

    public ArrayList<Integer> getFlights() {
        return onFlights;
    }

}
