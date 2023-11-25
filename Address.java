public class Address {
    public int streetNumber;
    public String streetName;
    public String city;
    public String postalCode;
    public String provinceOrState;
    public String country;
    public Address(int streetNumber, String streetName, String city, String postalCode, String provinceOrState, String country){
        this.streetNumber=streetNumber;
        this.streetName=streetName;
        this.city=city;
        this.postalCode=postalCode;
        this.provinceOrState=provinceOrState;
        this.country=country;
    }
    public int getStreetNumber(){
        return streetNumber;
    }
    public String getStreetName(){
        return streetName;
    }
    public String getCity(){
        return city;
    }
    public String getPostalCode(){
        return postalCode;
    }
    public String getProvinceOrState(){
        return provinceOrState;
    }
    public String getCountry(){
        return country;
    }
    public void setStreetNumber(int streetNumber){
        this.streetNumber=streetNumber;
    }
    public void setStreetName(String streetName){
        this.streetName=streetName;
    }
    public void setCity(String city){
        this.city=city;
    }
    public void setPostalCode(String postalCode){
        this.postalCode=postalCode;
    }
    public void setProvinceOrState(String provinceOrState){
        this.provinceOrState=provinceOrState;
    }
    public void setCountry(String country){
        this.country=country;
    }


    public String getAddress(){
        return streetNumber+" "+streetName+" "+city+" "+postalCode+" "+provinceOrState+" "+country;
    }


    
}
