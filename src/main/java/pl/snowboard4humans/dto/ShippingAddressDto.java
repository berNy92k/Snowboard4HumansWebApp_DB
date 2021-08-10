package pl.snowboard4humans.dto;

public class ShippingAddressDto extends AbstractIdDto {

    private String email;
    private String firstName;
    private String lastName;
    private String streetName;
    private String houseOrApartmentNr;
    private String city;
    private String zipCode;
    private String country;
    private String phone;

    public ShippingAddressDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseOrApartmentNr() {
        return houseOrApartmentNr;
    }

    public void setHouseOrApartmentNr(String houseOrApartmentNr) {
        this.houseOrApartmentNr = houseOrApartmentNr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
