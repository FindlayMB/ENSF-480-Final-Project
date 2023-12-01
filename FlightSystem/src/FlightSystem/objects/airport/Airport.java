package FlightSystem.objects.airport;

import FlightSystem.objects.ToQuery;

/**
 * 
 * @author Findlay Brown
 */
public class Airport implements ToQuery {
    private String Code;
    private String Name;
    private String City;
    private String Country;

    public Airport(String Code, String Name, String City, String Country) {
        this.Code = Code;
        this.Name = Name;
        this.City = City;
        this.Country = Country;
    }

    public String getCode() {
        return this.Code;
    }

    public void setCode(String code) {
        this.Code = code;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getCity() {
        return this.City;
    }

    public void setCity(String city) {
        this.City = city;
    }

    public String getCountry() {
        return this.Country;
    }

    public void setCountry(String country) {
        this.Country = country;
    }

    @Override
    public String toString() {
        String output = String.format("%s: %s  %s, %s", Code, Name, City, Country);
        return output;
    }

    public String toQuery() {
        String output = String.format("'%s','%s','%s','%s'",
                Code, Name, City, Country);
        return output;
    }
}
