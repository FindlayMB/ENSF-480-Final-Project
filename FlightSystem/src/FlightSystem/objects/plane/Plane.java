package FlightSystem.objects.plane;

import FlightSystem.objects.ToQuery;

/**
 * 
 * @author Findlay Brown
 */
public class Plane implements ToQuery {
    private final int ID;
    private String type;
    private int regularSeatAmt;
    private int comfortSeatAmt;
    private int businessSeatAmt;

    public Plane(int ID, String type, int regularSeatAmt, int comfortSeatAmt, int businessSeatAmt) {
        this.ID = ID;
        this.type = type;
        this.regularSeatAmt = regularSeatAmt;
        this.comfortSeatAmt = comfortSeatAmt;
        this.businessSeatAmt = businessSeatAmt;
    }

    public Plane(int ID, Plane plane) {
        this.ID = ID;
        this.type = plane.type;
        this.regularSeatAmt = plane.regularSeatAmt;
        this.comfortSeatAmt = plane.comfortSeatAmt;
        this.businessSeatAmt = plane.businessSeatAmt;
    }

    public int getID() {
        return ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRegularSeatAmt() {
        return regularSeatAmt;
    }

    public void setRegularSeatAmt(int regularSeatAmt) {
        this.regularSeatAmt = regularSeatAmt;
    }

    public int getComfortSeatAmt() {
        return comfortSeatAmt;
    }

    public void setComfortSeatAmt(int comfortSeatAmt) {
        this.comfortSeatAmt = comfortSeatAmt;
    }

    public int getBusinessSeatAmt() {
        return businessSeatAmt;
    }

    public void setBusinessSeatAmt(int businessSeatAmt) {
        this.businessSeatAmt = businessSeatAmt;
    }

    @Override
    public String toString() {
        String output = String.format("%d: %s  %d\t%d\t%d",
                ID, type, regularSeatAmt, comfortSeatAmt, businessSeatAmt);
        return output;
    }

    public String toQuery() {
        String output = String.format("'%s',%d,%d,%d",
                type, regularSeatAmt, comfortSeatAmt, businessSeatAmt);
        return output;
    }
}
