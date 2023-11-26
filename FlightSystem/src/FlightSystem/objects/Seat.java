package FlightSystem.objects;

public class Seat {
    private int seatNumber;
    private boolean isReserved=false;
    private User passenger=null;
    
    public Seat(int seatNumber, User asignee, boolean isReserved){
        this.seatNumber=seatNumber;
        this.passenger=asignee;
        this.isReserved=isReserved;
    }
    
    public void setPassenger(User passenger){
        this.passenger=passenger;
        isReserved=true;
    }
    public void setSeatNumber(int seatNumber){
        this.seatNumber=seatNumber;
    }
    public int getSeatNumber(){
        return seatNumber;
    }
    public boolean isReserved(){
        return isReserved;
    }
    public User getPassenger(){
        return passenger;
    }
    public void performDisplaySeat(){
        //TODO
        
    }



}