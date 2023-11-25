public class Seat {
    private int seatNumber;
    private boolean isReserved=false;
    private BasicUser passenger=null;
    
    public void setPassenger(BasicUser passenger){
        this.passenger=passenger;
        isReserved=true;
    }
    public void setSeatNyumber(int seatNumber){
        this.seatNumber=seatNumber;
    }
    public int getSeatNumber(){
        return seatNumber;
    }
    public boolean isReserved(){
        return isReserved;
    }
    public BasicUser getPassenger(){
        return passenger;
    }
    public void performDisplaySeat(){
        //TODO
        System.out.println("Seat Number: "+seatNumber+"\n"+"Passenger: "+passenger.getFirstName()+" "+passenger.getLastName());
        
    }



}
