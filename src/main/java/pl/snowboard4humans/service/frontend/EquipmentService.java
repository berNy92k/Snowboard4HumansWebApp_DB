package pl.snowboard4humans.service.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.snowboard4humans.constants.ConstantsFrontendPL;
import pl.snowboard4humans.constants.ConstantsPL;
import pl.snowboard4humans.enums.SexEnum;
import pl.snowboard4humans.model.Equipment;
import pl.snowboard4humans.model.Review;
import pl.snowboard4humans.repository.EquipmentRepo;
import pl.snowboard4humans.repository.ReviewRepo;
import pl.snowboard4humans.service.SuperService;
import pl.snowboard4humans.utils.Utils;

import java.util.LinkedList;
import java.util.List;

@Service
public class EquipmentService extends SuperService {

    private final EquipmentRepo equipmentRepo;
    private final ReviewRepo reviewRepo;

    @Autowired
    public EquipmentService(EquipmentRepo equipmentRepo,
                            ReviewRepo reviewRepo) {

        this.equipmentRepo = equipmentRepo;
        this.reviewRepo = reviewRepo;
    }

    public String equipmentList(Model model,
                                String equipmentCategory,
                                String equipmentSex) {

        List<Equipment> equipments;
        if (equipmentCategory != null && (!Utils.choiceSex(equipmentSex).equals(SexEnum.ZERO.getSex()))) {
            int category = Utils.choiceCategory(equipmentCategory);
            String sex = Utils.choiceSex(equipmentSex);
            equipments = equipmentRepo.findEquipmentByCategoryIdAndSex(category, sex);
        } else {
            equipments = equipmentRepo.findAll();
        }

        if (equipmentCategory == null || equipmentSex == null || (equipments == null || equipments.size() == 0)) {
            model.addAttribute("equipmentCategoryName", ConstantsPL.NULL);
            model.addAttribute("equipmentSex", ConstantsPL.NULL);
            model.addAttribute(ConstantsPL.MESSAGE, ConstantsPL.NULL);
        }

        model.addAttribute("equipmentList", equipments);
        if (equipmentCategory != null) {
            model.addAttribute("equipmentCategoryName", Utils.plVersionOfCategory(equipmentCategory));
        }
        if (equipmentSex != null) {
            model.addAttribute("equipmentSex", Utils.plVersionOfSex(equipmentSex));
        }

        if (equipments != null && equipments.size() == 0) {
            model.addAttribute(ConstantsPL.MESSAGE, ConstantsFrontendPL.LACK_OF_EQUIPMENT_IN_DB);
            model.addAttribute("messageEmpty", true);
        } else {
            if (equipments != null) {
                model.addAttribute("messageEmpty", false);
            }
        }

        return ConstantsFrontendPL.EQUIPMENT_LIST_URL;
    }

    public String viewEquipment(Model model,
                                Integer equipmentId) {

        Equipment equipment = equipmentRepo.getOne(equipmentId);
        List<Review> reviews = reviewRepo.findReviewsByEquipmentId(equipmentId);

        if (equipment != null) {
            model.addAttribute("equipment", equipment);
        }

        if (reviews != null && reviews.size() > 0) {
            model.addAttribute("reviews", reviews);

            int sum = 0;
            for (Review review : reviews) {
                sum = sum + review.getRating();
            }
            float reviewsAverage = (float) sum / reviews.size();

            int fullStar = (int) reviewsAverage;
            model.addAttribute("reviewsAverageFull", fullStar);

            int halfStar = reviewsAverage > fullStar ? 1 : 0;
            model.addAttribute("reviewsAverageHalf", halfStar);
        }

        return ConstantsFrontendPL.EQUIPMENT_VIEW_URL;
    }

    private String equipmentShortList(Model model) {
        List<Equipment> equipments = equipmentRepo.findAll();

        if (equipments != null && equipments.size() < 5 && equipments.size() != 0) {
            model.addAttribute("equipmentShortList", equipments);
            model.addAttribute("messageEmpty", false);
        } else if (equipments != null && equipments.size() > 4) {
            List<Equipment> newShortList = new LinkedList<>();
            for (int i = 0; i < 4; i++) {
                newShortList.add(equipments.get(i));
            }

            model.addAttribute("equipmentShortList", newShortList);
            model.addAttribute("messageEmpty", false);
        } else {
            model.addAttribute("messageEmpty", true);
        }

        return ConstantsFrontendPL.HOMEPAGE_URL;
    }

    public String searchEquipmentList(Model model,
                                      String searchBy) {

        List<Equipment> equipments = null;
        if (searchBy != null && searchBy.length() == 0) {
            return equipmentShortList(model);
        } else if (searchBy == null) {
            return equipmentShortList(model);
        } else {
            String searchByForQuery = "%" + searchBy + "%";
            equipments = equipmentRepo.findEquipmentByNameLikeOrLongDescriptionLikeOrShortDescriptionLike(searchByForQuery, searchByForQuery, searchByForQuery);
        }

        model.addAttribute("equipmentList", equipments);
        model.addAttribute("equipmentCategoryName", ConstantsFrontendPL.SEARCH_BY_WORD);
        model.addAttribute("equipmentSex", searchBy);
        if (equipments != null && equipments.size() == 0) {
            model.addAttribute(ConstantsPL.MESSAGE, ConstantsFrontendPL.LACK_OF_EQUIPMENT_IN_DB);
        }

        return ConstantsFrontendPL.EQUIPMENT_LIST_URL;
    }

}
