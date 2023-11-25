public class AirCraft {
    private String model;
    private int seatRows;
    private int seatColumns;
    public AirCraft(String model, int seatRows, int seatColumns) {
        this.model = model;
        this.seatRows = seatRows;
        this.seatColumns = seatColumns;
    }
    public String getModel() {
        return model;
    }
    public int getSeatRows() {
        return seatRows;
    }
    public int getSeatColumns() {
        return seatColumns;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setSeatRows(int seatRows) {
        this.seatRows = seatRows;
    }
    public void setSeatColumns(int seatColumns) {
        this.seatColumns = seatColumns;
    }
    public int getNumberOfSeats() {
        return seatRows * seatColumns;
    }
    public String toString() {
        return "AirCraft [model=" + model + ", seatRows=" + seatRows + ", seatColumns=" + seatColumns + "]";
    }


    
}
