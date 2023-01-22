package pl.snowboard4humans.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class ManufacturerDto extends AbstractIdDto {

  private String manufacturerName;
  private String description;
  private byte[] image;

  private String base64Image;
  private MultipartFile multipartFile;
}
