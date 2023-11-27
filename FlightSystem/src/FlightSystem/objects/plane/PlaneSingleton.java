package FlightSystem.objects.plane;

import FlightSystem.data.DatabaseSingleton;
import java.util.ArrayList;
import java.util.HashMap;

public class PlaneSingleton {
    private DatabaseSingleton dbConnection = DatabaseSingleton.getInstance();
    private static PlaneSingleton instance;
    private HashMap<Integer, Plane> planes;

    private PlaneSingleton() {
        if (planes == null) {
            try {
                planes = new HashMap<Integer, Plane>(dbConnection.getPlaneTable());
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Failed to get airports table!");
            }
        }
    }

    public static PlaneSingleton getInstance() {
        if (instance == null) {
            instance = new PlaneSingleton();
        }
        return instance;
    }

    public HashMap<Integer, Plane> getPlaneMap() {
        return planes;
    }

    public ArrayList<Plane> getPlaneList() {
        return new ArrayList<Plane>(planes.values());
    }

    public Plane getPlane(int planeID) {
        return planes.get(planeID);
    }

}
