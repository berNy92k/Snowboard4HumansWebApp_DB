package pl.snowboard4humans.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer extends AbstractModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "customer_id")
  private Integer id;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Customer customer = (Customer) o;
    return Objects.equals(id, customer.id) &&
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
    return Objects.hash(id, email, password, firstName, lastName, street, homeNumber, city, zipCode, country, phone, registerDate, reviews);
  }
}
