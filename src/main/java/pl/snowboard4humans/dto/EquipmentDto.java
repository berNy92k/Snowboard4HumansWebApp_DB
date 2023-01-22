package pl.snowboard4humans.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
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
}
