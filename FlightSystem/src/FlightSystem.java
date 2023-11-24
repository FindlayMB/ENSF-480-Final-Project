import java.util.HashMap;

import data.DataImport;
import objects.Airport;

public class FlightSystem {
    private DataImport dbImport = new DataImport();

    private HashMap<String, Airport> airports = new HashMap<String, Airport>();

    private void login() {
        if (dbImport.createConnection("admin", "admin")) {
            System.out.println("Database connection successfully made!");
            return;
        }
        System.out.println("Failed to create connection to database!");
    }

    public FlightSystem() {
        this.login();
    }

}
