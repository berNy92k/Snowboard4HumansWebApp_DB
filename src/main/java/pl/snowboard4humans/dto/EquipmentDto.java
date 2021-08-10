package pl.snowboard4humans.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class EquipmentDto extends AbstractIdDto {

    private String name;
    private ManufacturerDto manufacturer;
    private String shortDescription;
    private String longDescription;
    private String sex;
    private byte[] image;
    private float price;
    private String lengthOrSize;
    private CategoryDto category;
    private Date publishDate;
    private Date lastUpdateTime;

    private String base64Image;
    private MultipartFile multipartFile;

    public EquipmentDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ManufacturerDto getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerDto manufacturer) {
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

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
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

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
