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
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Shipping_Address")
public class ShippingAddress extends AbstractModel {

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
