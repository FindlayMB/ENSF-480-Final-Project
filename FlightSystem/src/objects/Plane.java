package objects;

public class Plane {
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

}
