package pl.snowboard4humans.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.snowboard4humans.constants.ConstantsAdminENG;
import pl.snowboard4humans.constants.ConstantsPL;
import pl.snowboard4humans.model.Manufacturer;
import pl.snowboard4humans.repository.ManufacturerRepo;
import pl.snowboard4humans.service.SuperService;
import pl.snowboard4humans.utils.Utils;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerAdminServices extends SuperService {

    private final ManufacturerRepo manufacturerRepo;

    @Autowired
    public ManufacturerAdminServices(ManufacturerRepo manufacturerRepo) {
        this.manufacturerRepo = manufacturerRepo;
    }

    public String getManufacturers(Model model) {
        String message = ConstantsPL.EMPTY_MESSAGE;

        List<Manufacturer> manufacturerList = manufacturerRepo.findAll();

        // if manufacturers list is empty then message change
        if (Utils.isEmpty(manufacturerList)) {
            message = ConstantsAdminENG.LACK_OF_MANUFACTURER_IN_DB;
        }

        return getRequestDispatcherWithDefaultMessage(model,
                message,
                ConstantsAdminENG.MANUFACTURER_LIST_OBJECT,
                manufacturerList,
                ConstantsAdminENG.MANUFACTURER_LIST_URL);
    }

    public String addOrUpdateManufacturer(Model model,
                                          Manufacturer manufacturer) {
        String message;

        Integer manufacturerId = manufacturer.getId();
        Manufacturer manufacturerSaved = manufacturerRepo.save(manufacturer);

        List<Manufacturer> manufacturerList = manufacturerRepo.findAll();
        if (manufacturerId == null && manufacturerSaved != null && manufacturerSaved.getId() != null) {
            message = ConstantsAdminENG.NEW_MANUFACTURER_WAS_CREATED;
        } else if (manufacturerId != null && manufacturerSaved != null && manufacturerSaved.getId() != null) {
            message = ConstantsAdminENG.MANUFACTURER_WAS_UPDATED;
        } else if (manufacturerId == null && manufacturerSaved == null) {
            message = ConstantsAdminENG.NEW_MANUFACTURER_WAS_NOT_CREATED;
        } else {
            message = ConstantsAdminENG.MANUFACTURER_WAS_NOT_UPDATED;
        }

        return getRequestDispatcherWithDefaultMessage(model,
                message,
                ConstantsAdminENG.MANUFACTURER_LIST_OBJECT,
                manufacturerList,
                ConstantsAdminENG.MANUFACTURER_LIST_URL);
    }

    public String getEditManufactureScreen(int manufacturerId,
                                           Model model) {
        Optional<Manufacturer> manufacturerOptional = manufacturerRepo.findById(manufacturerId);
        Manufacturer manufacturer = manufacturerOptional.orElseGet(Manufacturer::new);

        return getRequestDispatcherWithDefaultMessage(model,
                ConstantsAdminENG.UPDATE_MODE_CHANGE_FIELDS_MANUFACTURER_ADMIN,
                ConstantsAdminENG.MANUFACTURER_OBJECT,
                manufacturer,
                ConstantsAdminENG.MANUFACTURER_CREATE_OR_UPDATE_URL);
    }

    public String getAddNewManufacturerScreen(Model model) {
        return getRequestDispatcherWithDefaultMessage(model,
                ConstantsAdminENG.CREATE_MODE_FILL_FIELDS_MANUFACTURER_ADMIN,
                ConstantsAdminENG.MANUFACTURER_OBJECT,
                new Manufacturer(),
                ConstantsAdminENG.MANUFACTURER_CREATE_OR_UPDATE_URL);
    }

    public String deleteManufacturer(Model model,
                                     int manufacturerId) {
        String message;
        Optional<Manufacturer> manufacturerOptional = manufacturerRepo.findById(manufacturerId);
        if (manufacturerOptional.isPresent()) {
            manufacturerRepo.deleteById(manufacturerId);
            message = ConstantsAdminENG.MANUFACTURER_WAS_DELETED;
        } else {
            message = ConstantsAdminENG.COULD_NOT_FIND_MANUFACTURER_BY_ID + ConstantsAdminENG.DELETED_BY_ANOTHER_MANUFACTURER_ADMIN;
        }

        List<Manufacturer> manufacturerList = manufacturerRepo.findAll();

        // if manufacturers list is empty then message change
        if (Utils.isEmpty(manufacturerList)) {
            message = ConstantsAdminENG.LACK_OF_MANUFACTURER_IN_DB + message;
        }

        return getRequestDispatcherWithDefaultMessage(model,
                message,
                ConstantsAdminENG.MANUFACTURER_LIST_OBJECT,
                manufacturerList,
                ConstantsAdminENG.MANUFACTURER_LIST_URL);
    }
}
