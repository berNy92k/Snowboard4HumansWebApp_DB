package pl.snowboard4humans.mapper;

import org.springframework.stereotype.Component;
import pl.snowboard4humans.dto.AbstractIdDto;
import pl.snowboard4humans.model.AbstractModel;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public abstract class AbstractMapper<DTO extends AbstractIdDto, MODEL extends AbstractModel> {

  public abstract DTO map(final MODEL source);

  public abstract MODEL map(final DTO source);

  public List<DTO> mapToDtoList(final List<MODEL> models) {
    return models.stream().map(this::map).collect(Collectors.toList());
  }

  public List<MODEL> mapToModelList(final List<DTO> dtos) {
    return dtos.stream().map(this::map).collect(Collectors.toList());
  }

  public List<DTO> mapSetToDtoList(final Set<MODEL> models) {
    return models.stream().map(this::map).collect(Collectors.toList());
  }

  public Set<MODEL> mapListToModelSet(final List<DTO> dtos) {
    return dtos.stream().map(this::map).collect(Collectors.toSet());
  }
}
