package FlightSystem.objects.user;

public class Address {
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    public Address(String streetAddress, String city, String state, String country, String zipCode) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        String output = String.format("%s %s, %s %s", streetAddress, city, state, zipCode);
        return output;
    }

}
