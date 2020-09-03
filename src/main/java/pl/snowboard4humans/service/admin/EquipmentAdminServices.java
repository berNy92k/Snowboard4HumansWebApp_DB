package pl.snowboard4humans.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.snowboard4humans.constants.ConstantsAdminENG;
import pl.snowboard4humans.constants.ConstantsFrontendPL;
import pl.snowboard4humans.constants.ConstantsPL;
import pl.snowboard4humans.constants.ConstantsUtils;
import pl.snowboard4humans.enums.SexEnum;
import pl.snowboard4humans.model.Category;
import pl.snowboard4humans.model.Equipment;
import pl.snowboard4humans.model.Manufacturer;
import pl.snowboard4humans.repository.CategoryRepo;
import pl.snowboard4humans.repository.EquipmentRepo;
import pl.snowboard4humans.repository.ManufacturerRepo;
import pl.snowboard4humans.service.SuperService;
import pl.snowboard4humans.utils.Utils;

import java.util.Date;
import java.util.List;

@Service
public class EquipmentAdminServices extends SuperService {
    private EquipmentRepo equipmentRepo;
    private ManufacturerRepo manufacturerRepo;
    private CategoryRepo categoryRepo;

    @Autowired
    public EquipmentAdminServices(EquipmentRepo equipmentRepo,
                                  ManufacturerRepo manufacturerRepo,
                                  CategoryRepo categoryRepo) {

        this.equipmentRepo = equipmentRepo;
        this.manufacturerRepo = manufacturerRepo;
        this.categoryRepo = categoryRepo;
    }

    public String equipmentList(Model model,
                                String equipmentCategory,
                                String equipmentSex) {

        return equipmentList(model, equipmentCategory, equipmentSex, ConstantsPL.NULL);
    }

    public String equipmentList(Model model,
                                String equipmentCategory,
                                String equipmentSex,
                                String message) {

        List<Equipment> equipments;
        if (equipmentCategory != null &&
                Utils.choiceSex(equipmentSex).equals(SexEnum.ALL.getSex())) {

            int category = Utils.choiceCategory(equipmentCategory);
            equipments = equipmentRepo.findEquipmentByCategoryId(category);
        } else if (equipmentCategory != null &&
                (!Utils.choiceSex(equipmentSex).equals(SexEnum.ZERO.getSex()) &&
                        !Utils.choiceSex(equipmentSex).equals(SexEnum.ALL.getSex()))) {

            int category = Utils.choiceCategory(equipmentCategory);
            String sex = Utils.choiceSex(equipmentSex);
            equipments = equipmentRepo.findEquipmentByCategoryIdAndSex(category, sex);
        } else {
            equipments = equipmentRepo.findAll();
        }

        model.addAttribute("equipmentList", equipments);
        if (equipmentCategory != null) {
            model.addAttribute("equipmentCategoryName", Utils.plVersionOfCategory(equipmentCategory));
            model.addAttribute("eqCat", equipmentCategory);
        }
        if (equipmentSex != null) {
            model.addAttribute("equipmentSex", Utils.plVersionOfSex(equipmentSex));
            model.addAttribute("sex", equipmentSex);
        }

        if (message != null) {
            model.addAttribute(ConstantsPL.MESSAGE, message);
        } else if (equipments != null && equipments.size() == 0) {
            model.addAttribute(ConstantsPL.MESSAGE, ConstantsFrontendPL.LACK_OF_EQUIPMENT_IN_DB);
        }

        return ConstantsAdminENG.EQUIPMENT_LIST_URL;
    }

    public String preCreateNewEquipment(Model model,
                                        String eqm,
                                        String sex) {
        List<Manufacturer> manufacturers = manufacturerRepo.findAll();
        List<Category> categories = categoryRepo.findAll();

        Equipment equipment = new Equipment();
//        equipment.setSex(sex);
//        equipment.setCategory(eqm);

        model.addAttribute(ConstantsPL.MESSAGE, ConstantsFrontendPL.FILL_ALL_FIELDS_TO_CREATE_EQUIPMENT);
        model.addAttribute("equipment", equipment);
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("categories", categories);

        return ConstantsAdminENG.EQUIPMENT_CREATE_URL;
    }

    // TODO - add update
    public String addOrUpdateEquipment(Model model, Equipment equipment) {
        List<Equipment> equipments = equipmentRepo.findAllByName(equipment.getName());

        if (equipments.size() > 1) {
            return equipmentIsCreated(model, equipment.getName() + ConstantsAdminENG.EQUIPMENT_ALREADY_EXIST_IN_DB);
        } else if (equipments.size() == 1 && equipment.getId() != null && equipments.get(0).getId().equals(equipment.getId())) {
            return updateEquipment(model, equipment, equipments);
        } else {
            return createEquipment(model, equipment);
        }
    }

    public String viewEquipment(Model model,
                                int equipmentId) {

        equipmentRepo
                .findById(equipmentId)
                .ifPresent(equipmentFromDb -> model.addAttribute("equipment", equipmentFromDb));

        return ConstantsAdminENG.EQUIPMENT_VIEW_URL;
    }

    private String equipmentIsCreated(Model model,
                                      String message) {
        model.addAttribute(ConstantsPL.MESSAGE, message);

        return ConstantsAdminENG.EQUIPMENT_IS_CREATED_URL;
    }

    public String editEquipment(Model model,
                                int equipmentId) {

        Equipment equipment = equipmentRepo.getOne(equipmentId);
        List<Category> categories = categoryRepo.findAll();
        List<Manufacturer> manufacturers = manufacturerRepo.findAll();

        if (equipment != null) {
            model.addAttribute(ConstantsPL.MESSAGE, ConstantsFrontendPL.FILL_ALL_FIELDS_TO_EDIT_EQUIPMENT);
            model.addAttribute("equipment", equipment);
            model.addAttribute("categories", categories);
            model.addAttribute("manufacturers", manufacturers);

            return ConstantsAdminENG.EQUIPMENT_CREATE_URL;
        } else {
            return equipmentIsCreated(model, ConstantsAdminENG.COULD_NOT_FIND_EQUIPMENT_BY_ID + equipmentId);
        }
    }

    private String createEquipment(Model model,
                                   Equipment equipment) {

        Date date = new Date();
        equipment.setPublishDate(date);
        equipment.setLastUpdateTime(date);

        Equipment equipmentToSave = equipmentRepo.save(equipment);
        if (equipmentToSave.getId() > 0) {
            return equipmentIsCreated(model, ConstantsAdminENG.NEW_EQUIPMENT_WAS_CREATED);
        } else {
            return equipmentIsCreated(model, ConstantsAdminENG.NEW_EQUIPMENT_WAS_NOT_CREATED);
        }
    }

    private String updateEquipment(Model model,
                                   Equipment equipment, List<Equipment> equipmentsfindByName) {

        int equipmentId = equipment.getId();
        Equipment equipmentFromDB_AlreadyExist = equipmentRepo.getOne(equipmentId);

        Date publishDate = equipmentFromDB_AlreadyExist.getPublishDate();
        Date lastUpdateTime = new Date();

        if (equipmentsfindByName.size() > 1 || (equipmentsfindByName.size() == 1 && equipmentsfindByName.get(0).getId() != equipmentId)) {
            return equipmentIsCreated(model, equipment.getName() + ConstantsAdminENG.EQUIPMENT_ALREADY_EXIST_IN_DB);
        } else {
            equipment.setPublishDate(publishDate);
            equipment.setLastUpdateTime(lastUpdateTime);

            Equipment savedEquipment = equipmentRepo.save(equipment);
            if (savedEquipment.getId() > 0) {
                return equipmentIsCreated(model, ConstantsAdminENG.EQUIPMENT_WAS_UPDATED);
            } else {
                return equipmentIsCreated(model, ConstantsAdminENG.EQUIPMENT_WAS_NOT_UPDATED);
            }
        }
    }

    public String deleteEquipment(Model model,
                                  int equipmentId) {
        Equipment equipment = equipmentRepo.getOne(equipmentId);

        if (equipment != null) {
            equipmentRepo.deleteById(equipmentId);

            String message = ConstantsAdminENG.EQUIPMENT_WAS_DELETED;
            return equipmentList(model, ConstantsUtils.ADMIN_SNOWBOARDS, ConstantsUtils.ALL, message);
        } else {
            String message = ConstantsAdminENG.COULD_NOT_FIND_EQUIPMENT_BY_ID + equipmentId + ConstantsAdminENG.DELETED_BY_ANOTHER_EQUIPMENT_ADMIN;
            return equipmentList(model, ConstantsUtils.ADMIN_SNOWBOARDS, ConstantsUtils.ALL, message);
        }
    }

    public String equipmentTypeUrl(String type) {
        return Utils.getHtmlDependsOnCategoryTypeAdmin(type);
    }
}
