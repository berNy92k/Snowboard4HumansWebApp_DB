package pl.snowboard4humans.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;
    @NotNull
    @Column(name = "email")
    private String email;
    @NotNull
    @Column(name = "password")
    private String password;
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    @NotNull
    @Column(name = "street")
    private String street;
    @NotNull
    @Column(name = "home_number")
    private String homeNumber;
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
    @NotNull
    @Column(name = "register_date")
    private Date registerDate;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "customer")
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();

    public Customer() {
    }

    public Customer(String email, String password, String firstName,
                    String lastName, String street, String homeNumber, String city,
                    String zipCode, String country, String phone) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.homeNumber = homeNumber;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
        this.phone = phone;
    }

    public Customer(String email, String password, String firstName,
                    String lastName, String street, String homeNumber, String city,
                    String zipCode, String country, String phone, Date registerDate) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.homeNumber = homeNumber;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
        this.phone = phone;
        this.registerDate = registerDate;
    }

    public Customer(Integer customerId, String email, String password, String firstName,
                    String lastName, String street, String homeNumber, String city,
                    String zipCode, String country, String phone, Date registerDate) {
        this.customerId = customerId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.homeNumber = homeNumber;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
        this.phone = phone;
        this.registerDate = registerDate;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
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

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Order> getOrders() {

        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(password, customer.password) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(street, customer.street) &&
                Objects.equals(homeNumber, customer.homeNumber) &&
                Objects.equals(city, customer.city) &&
                Objects.equals(zipCode, customer.zipCode) &&
                Objects.equals(country, customer.country) &&
                Objects.equals(phone, customer.phone) &&
                Objects.equals(registerDate, customer.registerDate) &&
                Objects.equals(reviews, customer.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, email, password, firstName, lastName, street, homeNumber, city, zipCode, country, phone, registerDate, reviews);
    }
}
