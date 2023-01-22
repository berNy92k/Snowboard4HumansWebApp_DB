package pl.snowboard4humans.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import pl.snowboard4humans.constants.ConstantsPL;
import pl.snowboard4humans.utils.Utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.io.IOException;
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
@Table(name = "equipment",
    uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Equipment extends AbstractModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "equipment_id")
  private Integer id;
  @NotNull
  @Column(name = "name")
  private String name;
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "manufacturer_id")
  private Manufacturer manufacturer;
  @NotNull
  @Column(name = "short_description")
  private String shortDescription;
  @NotNull
  @Column(name = "long_description")
  private String longDescription;
  @NotNull
  @Column(name = "sex")
  private String sex;
  @NotNull
  @Column(name = "image")
  private byte[] image;
  @NotNull
  @Column(name = "price")
  private float price;
  @NotNull
  @Column(name = "length_or_size")
  private String lengthOrSize;
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;
  @NotNull
  @Column(name = "publish_date")
  private Date publishDate;
  @NotNull
  @Column(name = "last_update_time")
  private Date lastUpdateTime;

  @OneToMany(fetch = FetchType.EAGER,
      mappedBy = "equipment")
  private Set<Review> reviews = new HashSet<>();

  @OneToMany(fetch = FetchType.EAGER,
      mappedBy = "equipment")
  private Set<OrderEquipmentDetail> orderDetails = new HashSet<>();

  @Transient
  private String base64Image;

  @Transient
  private MultipartFile multipartFile;

  public String getBase64Image() throws IOException {
    return Utils.getBase64Image(this.image);
  }

  public void setBase64Image(String base64Image) throws IOException {
    this.base64Image = base64Image;
    if (base64Image != null) {
      this.image = Utils.getBytesBase64Image(base64Image);
    }
  }

  public void setMultipartFile(MultipartFile multipartFile) throws IOException {
    if (multipartFile != null
        && multipartFile.getOriginalFilename() != null
        && !multipartFile.getOriginalFilename().equals(ConstantsPL.EMPTY_MESSAGE)) {

      this.image = multipartFile.getBytes();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Equipment equipment = (Equipment) o;
    return Objects.equals(id, equipment.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
