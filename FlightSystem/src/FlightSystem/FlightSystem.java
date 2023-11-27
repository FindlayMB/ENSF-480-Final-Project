package FlightSystem;

import java.util.*;

import javax.swing.SwingUtilities;

import FlightSystem.data.*;
import FlightSystem.objects.*;
import FlightSystem.GUI.*;

public class FlightSystem {
    private DatabaseSingleton dbConnection;

    private AirportSingleton airportSingleton;
    private FlightSingleton flightSingleton;
    private PlaneSingleton planeSingleton;
    private UserSingleton userSingleton;


    public FlightSystem() {
        this.dbConnection = DatabaseSingleton.getInstance();
        this.airportSingleton = AirportSingleton.getOnlyInstance();
        this.flightSingleton = FlightSingleton.getOnlyInstance();
        this.planeSingleton = PlaneSingleton.getOnlyInstance();
        this.userSingleton = UserSingleton.getOnlyInstance();

    }

    public static void main(String[] args) {
        FlightSystem fs = new FlightSystem();

        User user = null;
        SwingUtilities.invokeLater(() -> {
            HomePage gui = new HomePage(user);
            gui.setVisible(true);
        });
    }

    

}
