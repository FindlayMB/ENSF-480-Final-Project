package FlightSystem.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import FlightSystem.data.DatabaseSingleton;

public class PlaneSingleton {
    private DatabaseSingleton dbConnection;
    private static PlaneSingleton onlyInstance;
    private HashMap<Integer, Plane> planes;

    private int planeId = 0;

    private PlaneSingleton()
    {
        if (planes == null) {
            try {
                planes = new HashMap<Integer, Plane>(dbConnection.getPlaneTable());
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Failed to get airports table!");
            }
        }
    }

    public void addPlane(Plane newPlane)
    {
            planeId++;
            planes.put(planeId, newPlane);
    }

    public void removePlane(Plane removePlane) 
    {
        planes.remove(planeId, removePlane);
    }

    public static PlaneSingleton getOnlyInstance() 
    {
        if(onlyInstance == null)
        {
            onlyInstance = new PlaneSingleton();
        }
        return onlyInstance;
    }

    public List<Plane> getPlanes() {
        return new ArrayList<>(planes.values());
    }
    
}
