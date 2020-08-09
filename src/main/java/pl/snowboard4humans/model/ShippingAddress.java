package pl.snowboard4humans.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Shipping_Address")
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_address_id")
    private Integer id;
    @NotNull
    @Column(name = "email")
    private String email;
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    @NotNull
    @Column(name = "street_name")
    private String streetName;
    @NotNull
    @Column(name = "house_or_apartment_number")
    private String houseOrApartmentNr;
    @NotNull
    @Column(name = "city")
    private String city;
    @NotNull
    @Column(name = "zip_code")
    private String zipCode;
    @NotNull
    @Column(name = "country")
    private String country;
    @NotNull
    @Column(name = "phone_number")
    private String phone;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "shippingAddress")
    private Set<Order> order = new HashSet<>();

    public ShippingAddress() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer shippingAddressId) {
        this.id = shippingAddressId;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Order> getOrder() {
        return order;
    }

    public void setOrder(Set<Order> order) {
        this.order = order;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingAddress that = (ShippingAddress) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(email, that.email) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(streetName, that.streetName) &&
                Objects.equals(houseOrApartmentNr, that.houseOrApartmentNr) &&
                Objects.equals(city, that.city) &&
                Objects.equals(zipCode, that.zipCode) &&
                Objects.equals(country, that.country) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName, lastName, streetName, houseOrApartmentNr, city, zipCode, country, phone);
    }
}
