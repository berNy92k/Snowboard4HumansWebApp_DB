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
public class EquipmentAdminService extends SuperService {

  private final EquipmentRepo equipmentRepo;
  private final ManufacturerRepo manufacturerRepo;
  private final CategoryRepo categoryRepo;

  @Autowired
  public EquipmentAdminService(final EquipmentRepo equipmentRepo,
                               final ManufacturerRepo manufacturerRepo,
                               final CategoryRepo categoryRepo) {

    this.equipmentRepo = equipmentRepo;
    this.manufacturerRepo = manufacturerRepo;
    this.categoryRepo = categoryRepo;
  }

  public String equipmentList(final Model model,
                              final String equipmentCategory,
                              final String equipmentSex) {

    return equipmentList(model, equipmentCategory, equipmentSex, ConstantsPL.NULL);
  }

  public String equipmentList(final Model model,
                              final String equipmentCategory,
                              final String equipmentSex,
                              final String message) {

    final List<Equipment> equipments;
    if (equipmentCategory != null &&
        Utils.choiceSex(equipmentSex).equals(SexEnum.ALL.getSex())) {

      equipments = equipmentRepo.findEquipmentByCategoryId(Utils.choiceCategory(equipmentCategory));
    } else if (equipmentCategory != null &&
        (!Utils.choiceSex(equipmentSex).equals(SexEnum.ZERO.getSex()) &&
            !Utils.choiceSex(equipmentSex).equals(SexEnum.ALL.getSex()))) {

      final int category = Utils.choiceCategory(equipmentCategory);
      final String sex = Utils.choiceSex(equipmentSex);
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

  public String preCreateNewEquipment(final Model model,
                                      final String eqm,
                                      final String sex) {
    final List<Manufacturer> manufacturers = manufacturerRepo.findAll();
    final List<Category> categories = categoryRepo.findAll();

    final Equipment equipment = Equipment.builder()
        .sex(sex.toUpperCase())
        .category(Category.builder()
            .id(Utils.choiceCategoryByParam(eqm))
            .build())
        .build();

    model.addAttribute(ConstantsPL.MESSAGE, ConstantsFrontendPL.FILL_ALL_FIELDS_TO_CREATE_EQUIPMENT);
    model.addAttribute("equipment", equipment);
    model.addAttribute("manufacturers", manufacturers);
    model.addAttribute("categories", categories);

    return ConstantsAdminENG.EQUIPMENT_CREATE_URL;
  }

  public String addOrUpdateEquipment(final Model model, final Equipment equipment) {
    final List<Equipment> equipments = equipmentRepo.findAllByName(equipment.getName());

    if (equipments.size() > 1) {
      return equipmentIsCreated(model, equipment.getName() + ConstantsAdminENG.EQUIPMENT_ALREADY_EXIST_IN_DB);
    } else if (equipments.size() == 1 && equipment.getId() != null && equipments.get(0).getId().equals(equipment.getId())) {
      return updateEquipment(model, equipment, equipments);
    } else {
      return createEquipment(model, equipment);
    }
  }

  public String viewEquipment(final Model model,
                              final int equipmentId) {

    equipmentRepo
        .findById(equipmentId)
        .ifPresent(equipmentFromDb -> model.addAttribute("equipment", equipmentFromDb));

    return ConstantsAdminENG.EQUIPMENT_VIEW_URL;
  }

  private String equipmentIsCreated(final Model model,
                                    final String message) {

    model.addAttribute(ConstantsPL.MESSAGE, message);
    return ConstantsAdminENG.EQUIPMENT_IS_CREATED_URL;
  }

  public String editEquipment(final Model model,
                              final int equipmentId) {

    final Equipment equipment = equipmentRepo.getOne(equipmentId);
    final List<Category> categories = categoryRepo.findAll();
    final List<Manufacturer> manufacturers = manufacturerRepo.findAll();

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

  private String createEquipment(final Model model,
                                 final Equipment equipment) {

    final Date date = new Date();
    equipment.setPublishDate(date);
    equipment.setLastUpdateTime(date);

    final Equipment equipmentToSave = equipmentRepo.save(equipment);
    if (equipmentToSave.getId() > 0) {
      return equipmentIsCreated(model, ConstantsAdminENG.NEW_EQUIPMENT_WAS_CREATED);
    } else {
      return equipmentIsCreated(model, ConstantsAdminENG.NEW_EQUIPMENT_WAS_NOT_CREATED);
    }
  }

  private String updateEquipment(final Model model,
                                 final Equipment equipment,
                                 final List<Equipment> equipmentsFindByName) {

    final int equipmentId = equipment.getId();
    final Equipment equipmentDb = equipmentRepo.getOne(equipmentId);
    final Date publishDate = equipmentDb.getPublishDate();
    final Date lastUpdateTime = new Date();

    if (equipmentsFindByName.size() > 1
        || (equipmentsFindByName.size() == 1 && equipmentsFindByName.get(0).getId() != equipmentId)) {

      return equipmentIsCreated(model, equipment.getName() + ConstantsAdminENG.EQUIPMENT_ALREADY_EXIST_IN_DB);
    } else {
      equipment.setPublishDate(publishDate);
      equipment.setLastUpdateTime(lastUpdateTime);

      final Equipment savedEquipment = equipmentRepo.save(equipment);
      if (savedEquipment.getId() > 0) {
        return equipmentIsCreated(model, ConstantsAdminENG.EQUIPMENT_WAS_UPDATED);
      } else {
        return equipmentIsCreated(model, ConstantsAdminENG.EQUIPMENT_WAS_NOT_UPDATED);
      }
    }
  }

  public String deleteEquipment(final Model model,
                                final int equipmentId) {
    final Equipment equipment = equipmentRepo.getOne(equipmentId);

    if (equipment != null) {
      equipmentRepo.deleteById(equipmentId);

      final String message = ConstantsAdminENG.EQUIPMENT_WAS_DELETED;
      return equipmentList(model, ConstantsUtils.ADMIN_SNOWBOARDS, ConstantsUtils.ALL, message);
    } else {
      final String message = ConstantsAdminENG.COULD_NOT_FIND_EQUIPMENT_BY_ID + equipmentId + ConstantsAdminENG.DELETED_BY_ANOTHER_EQUIPMENT_ADMIN;
      return equipmentList(model, ConstantsUtils.ADMIN_SNOWBOARDS, ConstantsUtils.ALL, message);
    }
  }

  public String equipmentTypeUrl(final String type) {
    return Utils.getHtmlDependsOnCategoryTypeAdmin(type);
  }


  public String searchEquipmentList(final Model model,
                                    final String searchBy) {

    final List<Equipment> equipments;
    if (searchBy != null && searchBy.length() > 0) {
      String searchByForQuery = "%" + searchBy + "%";
      equipments = equipmentRepo.findEquipmentByNameLikeOrLongDescriptionLikeOrShortDescriptionLike(searchByForQuery, searchByForQuery, searchByForQuery);
    } else {
      return ConstantsUtils.ADMIN_INDEX_HTML;
    }

    model.addAttribute("equipmentList", equipments);
    model.addAttribute("equipmentCategoryName", ConstantsFrontendPL.SEARCH_BY_WORD);
    model.addAttribute("equipmentSex", searchBy);

    if (equipments != null && equipments.size() == 0) {
      model.addAttribute(ConstantsPL.MESSAGE, ConstantsFrontendPL.LACK_OF_EQUIPMENT_IN_DB);
    }

    return ConstantsAdminENG.EQUIPMENT_LIST_URL;
  }

  public List<Equipment> findAll() {
    return equipmentRepo.findAll();
  }
}
