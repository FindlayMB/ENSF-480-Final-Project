import java.util.HashMap;

import data.DataImport;
import data.DatabaseSingleton;
import objects.Airport;

public class FlightSystem {
    private DatabaseSingleton dbConnection;

    private HashMap<String, Airport> airports = new HashMap<String, Airport>();

    public FlightSystem() {
        this.dbConnection = DatabaseSingleton.getInstance();
    }

}
