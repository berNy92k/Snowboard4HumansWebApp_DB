package pl.snowboard4humans.model;

import com.sun.istack.internal.NotNull;
import org.springframework.web.multipart.MultipartFile;
import pl.snowboard4humans.constants.ConstantsPL;
import pl.snowboard4humans.utils.Utils;

import javax.persistence.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "manufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacturer_id")
    private Integer id;
    @NotNull
    @Column(name = "manufacturer_name")
    private String manufacturerName;
    @NotNull
    @Column(name = "description")
    private String description;
    @NotNull
    @Column(name = "image")
    private byte[] image;

    @NotNull
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "manufacturer"
    )
    private Set<Equipment> equipments = new HashSet<>();

    @Transient
    private String base64Image;

    @Transient
    private MultipartFile multipartFile;

    public Manufacturer() {
    }

    public Manufacturer(String manufacturerName, String description, byte[] image) {
        this.manufacturerName = manufacturerName;
        this.description = description;
        this.image = image;
    }

    public Manufacturer(Integer manufacturerId, String manufacturerName, String description, byte[] image) {
        this.id = manufacturerId;
        this.manufacturerName = manufacturerName;
        this.description = description;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer manufacturerId) {
        this.id = manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Set<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(Set<Equipment> equipments) {
        this.equipments = equipments;
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
        Manufacturer that = (Manufacturer) o;
        return Objects.equals(manufacturerName, that.manufacturerName) &&
                Objects.equals(description, that.description) &&
                Arrays.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(manufacturerName, description);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
