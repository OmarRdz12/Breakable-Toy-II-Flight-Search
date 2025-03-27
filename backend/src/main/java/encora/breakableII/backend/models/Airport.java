package encora.breakableII.backend.models;

public class Airport {
    String city;
    String code;
    String country;

    public Airport(String city, String code, String country) {
        this.code = code;
        this.country = country;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public String getCode() {
        return code;
    }

    public String getCountry() {
        return country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
