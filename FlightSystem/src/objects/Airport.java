package objects;

public class Airport {
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

}
