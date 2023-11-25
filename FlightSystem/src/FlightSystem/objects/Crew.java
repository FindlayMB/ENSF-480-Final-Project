package FlightSystem.objects;

import java.time.LocalDate;
import java.util.ArrayList;

import FlightSystem.FlightSystem;
import FlightSystem.data.DatabaseSingleton;

public class Crew {
    private String job;

    private ArrayList<Integer> crewFlightIDs;

    public Crew(int ID) {
        try {
            DatabaseSingleton.getInstance().getCrewFlights(this, ID);
        } catch (Exception e) {
            System.out.println("Failed to get crew table!");
        }
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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
