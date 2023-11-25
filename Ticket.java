public class Ticket {
    private Flight flight;
    private Seat reservedSeat;
    public Ticket(Flight flight, Seat reservedSeat){
        this.flight=flight;
        this.reservedSeat=reservedSeat;
    }
    public Flight getFlight(){
        return flight;
    }
    public Seat getReservedSeat(){
        return reservedSeat;
    }
    public void setFlight(Flight flight){
        this.flight=flight;
    }
    public void setReservedSeat(Seat reservedSeat){
        this.reservedSeat=reservedSeat;
    }
    public void displayTicket(){
        //TODO
    }
    
}
