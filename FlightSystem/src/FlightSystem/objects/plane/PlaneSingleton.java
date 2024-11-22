package FlightSystem.objects.plane;

import FlightSystem.data.DatabaseSingleton;
import FlightSystem.objects.flight.FlightsSingleton;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * 
 * @author Findlay Brown
 */
public class PlaneSingleton {
    private static PlaneSingleton instance;
    private LinkedHashMap<Integer, Plane> planes;

    private PlaneSingleton() {
        if (planes == null) {
            try {
                planes = new LinkedHashMap<Integer, Plane>(DatabaseSingleton.getInstance().getPlaneTable());
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Failed to get airports table!");
            }
        }
    }

    public static synchronized PlaneSingleton getInstance() {
        if (instance == null) {
            instance = new PlaneSingleton();
        }
        return instance;
    }

    public LinkedHashMap<Integer, Plane> getPlaneMap() {
        return planes;
    }

    public ArrayList<Plane> getPlaneList() {
        return new ArrayList<Plane>(planes.values());
    }

    public Plane getPlane(int planeID) {
        return planes.get(planeID);
    }

    public synchronized boolean addPlane(Plane newPlane) {
        try {
            newPlane = DatabaseSingleton.getInstance().addPlane(newPlane);
            planes.put(newPlane.getID(), newPlane);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to insert new plane: " + newPlane.toString());
            return false;
        }
        return true;
    }

    public void removePlane(Plane removePlane) {
        try {
            DatabaseSingleton.getInstance().removePlane(removePlane);
            planes.remove(removePlane.getID());
            FlightsSingleton.getInstance().removeFlights(removePlane);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to remove plane: " + removePlane);
        }
    }

}
