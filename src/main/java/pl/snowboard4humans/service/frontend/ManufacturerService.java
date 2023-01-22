package pl.snowboard4humans.service.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.snowboard4humans.constants.ConstantsFrontendPL;
import pl.snowboard4humans.constants.ConstantsPL;
import pl.snowboard4humans.dto.MsgAndListDto;
import pl.snowboard4humans.model.Manufacturer;
import pl.snowboard4humans.repository.ManufacturerRepo;
import pl.snowboard4humans.service.SuperService;
import pl.snowboard4humans.utils.Utils;

import java.util.List;

@Service
public class ManufacturerService extends SuperService {

  private final ManufacturerRepo manufacturerRepo;

  @Autowired
  public ManufacturerService(final ManufacturerRepo manufacturerRepo) {
    this.manufacturerRepo = manufacturerRepo;
  }

  public MsgAndListDto<Manufacturer> manufacturerList() {
    final List<Manufacturer> manufacturers = manufacturerRepo.findAll();

    final String message;
    if (Utils.isEmpty(manufacturers)) {
      message = ConstantsFrontendPL.LACK_OF_MANUFACTURER_IN_DB;
    } else {
      message = ConstantsPL.EMPTY_MESSAGE;
    }

    return new MsgAndListDto<>(message, manufacturers);
  }
}
