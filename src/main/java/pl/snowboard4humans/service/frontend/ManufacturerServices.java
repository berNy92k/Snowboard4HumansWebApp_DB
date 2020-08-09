package pl.snowboard4humans.service.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.snowboard4humans.constants.ConstantsFrontendPL;
import pl.snowboard4humans.constants.ConstantsPL;
import pl.snowboard4humans.model.Manufacturer;
import pl.snowboard4humans.repository.ManufacturerRepo;
import pl.snowboard4humans.service.SuperService;
import pl.snowboard4humans.utils.Utils;

import java.util.List;

@Service
public class ManufacturerServices extends SuperService {
    private ManufacturerRepo manufacturerRepo;

    @Autowired
    public ManufacturerServices(ManufacturerRepo manufacturerRepo) {
        this.manufacturerRepo = manufacturerRepo;
    }

    public String manufacturerList(Model model) {
        String message = ConstantsPL.EMPTY_MESSAGE;

        List<Manufacturer> manufacturers = manufacturerRepo.findAll();

        // if manufacturers list is empty then message change
        if (Utils.isEmpty(manufacturers)) {
            message = ConstantsFrontendPL.LACK_OF_MANUFACTURER_IN_DB;
        }

        return getRequestDispatcher(model,
                message,
                ConstantsFrontendPL.MANUFACTURER_LIST_OBJECT,
                manufacturers,
                ConstantsFrontendPL.MANUFACTURER_LIST_URL);
    }
}
