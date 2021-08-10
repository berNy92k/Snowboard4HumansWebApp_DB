package pl.snowboard4humans.model;

import com.sun.istack.internal.NotNull;
import org.springframework.web.multipart.MultipartFile;
import pl.snowboard4humans.constants.ConstantsPL;
import pl.snowboard4humans.utils.Utils;

import javax.persistence.*;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    public Equipment() {
    }

    public Equipment(String name, Manufacturer manufacturer, String shortDescription, String longDescription,
                     String sex, byte[] image, float price, String lengthOrSize, Category category) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.sex = sex;
        this.image = image;
        this.price = price;
        this.lengthOrSize = lengthOrSize;
        this.category = category;
    }

    public Equipment(String name, Manufacturer manufacturer, String shortDescription, String longDescription,
                     String sex, byte[] image, float price, String lengthOrSize, Category category,
                     Date publishDate, Date lastUpdateTime) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.sex = sex;
        this.image = image;
        this.price = price;
        this.lengthOrSize = lengthOrSize;
        this.category = category;
        this.publishDate = publishDate;
        this.lastUpdateTime = lastUpdateTime;
    }

    public Equipment(Integer equipmentId, String name, Manufacturer manufacturer, String shortDescription,
                     String longDescription, String sex, byte[] image, float price, String lengthOrSize,
                     Category category, Date publishDate, Date lastUpdateTime) {
        this.id = equipmentId;
        this.name = name;
        this.manufacturer = manufacturer;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.sex = sex;
        this.image = image;
        this.price = price;
        this.lengthOrSize = lengthOrSize;
        this.category = category;
        this.publishDate = publishDate;
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer equipmentId) {
        this.id = equipmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getLengthOrSize() {
        return lengthOrSize;
    }

    public void setLengthOrSize(String lengthOrSize) {
        this.lengthOrSize = lengthOrSize;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public String getBase64Image() throws IOException {
        return Utils.getBase64Image(this.image);
    }

    public void setBase64Image(String base64Image) throws IOException {
        this.base64Image = base64Image;
        if (base64Image != null) {
            this.image = Utils.getBytesBase64Image(base64Image);
        }
    }
    public MultipartFile getMultipartFile() {
        return multipartFile;
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
