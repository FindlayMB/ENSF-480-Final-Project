package FlightSystem.objects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import FlightSystem.FlightSystem;
import FlightSystem.data.DatabaseSingleton;
import FlightSystem.objects.user.RegisteredUser;

public class Crew {
    private int crewID;
    private ArrayList<RegisteredUser> crew;

    private ArrayList<Integer> crewFlightIDs; // id of flight their on

    public Crew(int flightID) {
        try {
            DatabaseSingleton.getInstance().getCrewList(this, flightID);
        } catch (Exception e) {
            System.out.println("Failed to get crew table!");
        }
    }

    public int getCrewID() {
        return crewID;
    }

    public void setCrewID(int crewID) {
        this.crewID = crewID;
    }

    public ArrayList<Integer> getCrewFlightIDs() {
        return crewFlightIDs;
    }

    public void setCrewFlightIDs(ArrayList<Integer> crewFlightIDs) {
        this.crewFlightIDs = crewFlightIDs;
    }

    public void addCrewFlightID(Integer crewFlightID) {
        this.crewFlightIDs.add(crewFlightID);
    }
}
