package pl.snowboard4humans.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.snowboard4humans.constants.ConstantsAdminENG;
import pl.snowboard4humans.constants.ConstantsPL;
import pl.snowboard4humans.dto.MsgAndListDto;
import pl.snowboard4humans.model.Manufacturer;
import pl.snowboard4humans.repository.ManufacturerRepo;
import pl.snowboard4humans.service.SuperService;
import pl.snowboard4humans.utils.Utils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerAdminService extends SuperService {

  private final ManufacturerRepo manufacturerRepo;

  @Autowired
  public ManufacturerAdminService(final ManufacturerRepo manufacturerRepo) {
    this.manufacturerRepo = manufacturerRepo;
  }

  public MsgAndListDto<Manufacturer> getManufacturers() {
    final List<Manufacturer> manufacturerList = manufacturerRepo.findAll();

    final String message;
    if (Utils.isEmpty(manufacturerList)) {
      message = ConstantsAdminENG.LACK_OF_MANUFACTURER_IN_DB;
    } else {
      message = ConstantsPL.EMPTY_MESSAGE;
    }

    return new MsgAndListDto<>(message, manufacturerList);
  }

  public MsgAndListDto<Manufacturer> addOrUpdateManufacturer(final Manufacturer manufacturer) {
    final Integer manufacturerId = manufacturer.getId();
    final Manufacturer manufacturerSaved = manufacturerRepo.save(manufacturer);

    final String message;
    if (manufacturerId == null && manufacturerSaved != null && manufacturerSaved.getId() != null) {
      message = ConstantsAdminENG.NEW_MANUFACTURER_WAS_CREATED;
    } else if (manufacturerId != null && manufacturerSaved != null && manufacturerSaved.getId() != null) {
      message = ConstantsAdminENG.MANUFACTURER_WAS_UPDATED;
    } else if (manufacturerId == null && manufacturerSaved == null) {
      message = ConstantsAdminENG.NEW_MANUFACTURER_WAS_NOT_CREATED;
    } else {
      message = ConstantsAdminENG.MANUFACTURER_WAS_NOT_UPDATED;
    }

    return new MsgAndListDto<>(message, manufacturerRepo.findAll());
  }

  public MsgAndListDto<Manufacturer> getEditManufactureScreen(final int manufacturerId) {
    final Optional<Manufacturer> manufacturerOptional = manufacturerRepo.findById(manufacturerId);
    final Manufacturer manufacturer = manufacturerOptional.orElseGet(Manufacturer::new);

    return new MsgAndListDto<>(ConstantsAdminENG.UPDATE_MODE_CHANGE_FIELDS_MANUFACTURER_ADMIN, Collections.singletonList(manufacturer));
  }

  public MsgAndListDto<Manufacturer> deleteManufacturer(final int manufacturerId) {
    final Optional<Manufacturer> manufacturerOptional = manufacturerRepo.findById(manufacturerId);

    String message;
    if (manufacturerOptional.isPresent()) {
      manufacturerRepo.deleteById(manufacturerId);
      message = ConstantsAdminENG.MANUFACTURER_WAS_DELETED;
    } else {
      message = ConstantsAdminENG.COULD_NOT_FIND_MANUFACTURER_BY_ID + ConstantsAdminENG.DELETED_BY_ANOTHER_MANUFACTURER_ADMIN;
    }

    final List<Manufacturer> manufacturerList = manufacturerRepo.findAll();
    // if manufacturers list is empty then message change
    if (Utils.isEmpty(manufacturerList)) {
      message = ConstantsAdminENG.LACK_OF_MANUFACTURER_IN_DB + message;
    }

    return new MsgAndListDto<>(message, manufacturerList);

  }
}
