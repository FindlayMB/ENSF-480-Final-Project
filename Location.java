public class Location {
    private String airportName;
    private String city;
    private String country;

    public Location(String airportName, String city, String country) {
        this.airportName = airportName;
        this.city = city;
        this.country = country;
    }
    public String getAirportName() {
        return airportName;
    }
    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }
    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    

    
}
