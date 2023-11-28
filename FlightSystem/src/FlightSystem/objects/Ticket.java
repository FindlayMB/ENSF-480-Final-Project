package FlightSystem.objects;

import FlightSystem.objects.flight.Flight;

public class Ticket {
    private Flight flight;
    private int reservedSeatNum;
    public Ticket(Flight flight, int reservedSeatNum){
        this.flight=flight;
        this.reservedSeatNum=reservedSeatNum;
    }
    public Flight getFlight(){
        return flight;
    }
    public int getReservedSeat(){
        return reservedSeatNum;
    }
    public void setFlight(Flight flight){
        this.flight=flight;
    }
    public void setReservedSeat(Seat reservedSeat){
        this.reservedSeatNum=reservedSeatNum;
    }
    public void displayTicket(){
        //TODO
    }
    
}